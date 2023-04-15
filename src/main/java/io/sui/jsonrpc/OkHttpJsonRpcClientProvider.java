/*
 * Copyright 2022-2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.sui.jsonrpc;

import static org.apache.commons.lang3.StringUtils.replace;

import com.google.common.collect.Lists;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.sui.jsonrpc.JsonRpc20Response.Error;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.models.SuiApiException;
import io.sui.models.events.SuiEvent;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Ok http json rpc client provider.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class OkHttpJsonRpcClientProvider extends JsonRpcClientProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpJsonRpcClientProvider.class);

  private final String baseUrl;

  private final OkHttpClient client;

  private final JsonHandler jsonHandler;

  private final WebSocket webSocket;

  private final ConcurrentHashMap<Long, CompletableFuture<Object>> requestIdToReplies =
      new ConcurrentHashMap<>();

  private final ConcurrentHashMap<Long, PublishSubject<JsonRpc20WSResponse>> requestIdToSubjects =
      new ConcurrentHashMap<>();

  private final ConcurrentHashMap<Long, PublishSubject<JsonRpc20WSResponse>>
      subscriptionIdToSubjects = new ConcurrentHashMap<>();

  private final ConcurrentHashMap<Long, Long> requestIdToSubscriptionIds =
      new ConcurrentHashMap<>();

  /**
   * Instantiates a new Ok http json rpc client provider.
   *
   * @param baseUrl the base url
   * @param jsonHandler the json handler
   */
  @SuppressWarnings("unchecked")
  public OkHttpJsonRpcClientProvider(String baseUrl, JsonHandler jsonHandler) {
    this.baseUrl = baseUrl;
    this.jsonHandler = jsonHandler;
    this.client =
        new OkHttpClient()
            .newBuilder()
            .pingInterval(Duration.ofSeconds(15))
            .writeTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(15))
            .build();
    final String wsUrl;
    if (StringUtils.startsWith(baseUrl, "https")) {
      wsUrl = replace(baseUrl, "https", "wss");
    } else {
      wsUrl = replace(baseUrl, "http", "ws");
    }
    webSocket =
        this.client.newWebSocket(
            new Request.Builder().url(wsUrl).get().build(),
            new WebSocketListener() {
              @Override
              public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
              }

              @Override
              public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
              }

              @Override
              public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
              }

              @Override
              public void onMessage(WebSocket webSocket, String text) {
                System.out.println(text);
                final Map<String, Object> reply = jsonHandler.fromJsonMap(text);
                if (null != reply.get("id")) {
                  CompletableFuture<Object> replayFuture =
                      requestIdToReplies.get((Long) reply.get("id"));
                  if (reply.get("error") != null) {
                    Map<String, Object> errorInfo = (Map<String, Object>) reply.get("error");
                    Error error = new Error();
                    error.setCode(ErrorCode.valueOfCode((int) errorInfo.get("code")));
                    error.setMessage((String) errorInfo.get("message"));

                    replayFuture.completeExceptionally(new SuiApiException(error));
                  }

                  if (null != requestIdToSubjects.get((Long) reply.get("id"))) {
                    subscriptionIdToSubjects.put(
                        (Long) reply.get("result"),
                        requestIdToSubjects.get((Long) reply.get("id")));
                    requestIdToSubjects.remove((Long) reply.get("id"));
                  }
                  replayFuture.complete(reply.get("result"));
                } else {
                  final JsonRpc20WSResponse message = jsonHandler.fromJson(text);
                  PublishSubject<JsonRpc20WSResponse> publishSubject =
                      subscriptionIdToSubjects.get(message.getParams().getSubscription());
                  publishSubject.onNext(message);
                }
              }

              @Override
              public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
              }

              @Override
              public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
              }
            });
  }

  @SuppressWarnings("checkstyle:Indentation")
  @Override
  public Disposable subscribe(
      JsonRpc20Request request, Consumer<SuiEvent> onNext, Consumer<SuiApiException> onError) {
    final String subscribeRequestBodyJsonStr = this.jsonHandler.toJson(request);
    System.out.println(subscribeRequestBodyJsonStr);
    final CompletableFuture<Object> subscriptionResponseFuture = new CompletableFuture<>();
    this.requestIdToReplies.put(request.getId(), subscriptionResponseFuture);
    PublishSubject<JsonRpc20WSResponse> subject = PublishSubject.create();
    Disposable disposable =
        subject
            .doOnDispose(() -> unsubscribe(request))
            .toFlowable(BackpressureStrategy.BUFFER)
            .subscribe(
                jsonRpc20Response -> onNext.accept(jsonRpc20Response.getParams().getResult()),
                throwable -> onError.accept(new SuiApiException(throwable)));

    this.requestIdToSubjects.put(request.getId(), subject);
    final boolean subscribeRequestIsAccepted = this.webSocket.send(subscribeRequestBodyJsonStr);

    if (!subscribeRequestIsAccepted) {
      LOGGER.warn(String.format("subscribe request id %d send failed", request.getId()));
      this.requestIdToReplies.remove(request.getId());
      this.requestIdToSubjects
          .get(request.getId())
          .onError(new IOException("subscribe request send failed."));
      this.requestIdToSubjects.remove(request.getId());
    } else {
      final Long subscriptionId = (Long) subscriptionResponseFuture.join();
      requestIdToSubscriptionIds.put(request.getId(), subscriptionId);
      this.requestIdToReplies.remove(request.getId());
    }
    return disposable;
  }

  /**
   * Call completable future.
   *
   * @param <T> the type parameter
   * @param request the request
   * @param url the url
   * @param typeOfT the type of t
   * @return the completable future
   */
  public <T> CompletableFuture<JsonRpc20Response<T>> call(
      JsonRpc20Request request, String url, Type typeOfT) {
    final CompletableFuture<JsonRpc20Response<T>> future = new CompletableFuture<>();
    final Request okhttpRequest;
    try {
      final String requestBodyJsonStr = this.jsonHandler.toJson(request);
      final RequestBody requestBody =
          RequestBody.create(requestBodyJsonStr, MediaType.get("application/json; charset=utf-8"));
      okhttpRequest =
          new Request.Builder()
              .url(String.format("%s%s", this.baseUrl, url))
              .post(requestBody)
              .build();
    } catch (Throwable throwable) {
      future.completeExceptionally(throwable);
      return future;
    }

    this.client
        .newCall(okhttpRequest)
        .enqueue(
            new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                final JsonRpc20Response<T> jsonRpc20Response = new JsonRpc20Response<>();
                JsonRpc20Response.Error error = new JsonRpc20Response.Error();
                error.setCode(JsonRpc20Response.Error.ErrorCode.IO_ERROR);
                jsonRpc20Response.setError(error);
                jsonRpc20Response.setThrowable(e);
                future.complete(jsonRpc20Response);
              }

              @Override
              public void onResponse(Call call, Response response) {
                try {
                  final JsonRpc20Response<T> jsonRpc20Response;
                  if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                      jsonRpc20Response = jsonHandler.fromJson(responseBody.string(), typeOfT);
                    } else {
                      jsonRpc20Response = new JsonRpc20Response<>();
                    }
                  } else {
                    jsonRpc20Response = new JsonRpc20Response<>();
                    JsonRpc20Response.Error error = new JsonRpc20Response.Error();
                    error.setCode(JsonRpc20Response.Error.ErrorCode.FAILURE_RESPONSE);
                    jsonRpc20Response.setError(error);
                  }
                  future.complete(jsonRpc20Response);
                } catch (Throwable throwable) {
                  future.completeExceptionally(throwable);
                }
              }
            });

    return future;
  }

  private void unsubscribe(JsonRpc20Request request) {
    final Long subscriptionId = requestIdToSubscriptionIds.get(request.getId());
    final JsonRpc20Request unsubscribeRequest =
        createJsonRpc20Request("suix_unsubscribeEvent", Lists.newArrayList(subscriptionId));
    final String unsubscribeRequestBodyJsonStr = jsonHandler.toJson(unsubscribeRequest);
    final CompletableFuture<Object> unsubscribeResultFuture = new CompletableFuture<>();
    requestIdToReplies.put(unsubscribeRequest.getId(), unsubscribeResultFuture);

    final boolean unsubscribeRequestIsAccepted = webSocket.send(unsubscribeRequestBodyJsonStr);
    if (!unsubscribeRequestIsAccepted) {
      requestIdToReplies.remove(unsubscribeRequest.getId());
      requestIdToSubscriptionIds.remove(request.getId());
      subscriptionIdToSubjects.remove(subscriptionId);
      LOGGER.error(
          String.format(
              "unsubscribe request id %d and subscriptionId %d send failed",
              unsubscribeRequest.getId(), subscriptionId));
    } else {
      final boolean success;
      success = (Boolean) unsubscribeResultFuture.join();
      requestIdToReplies.remove(unsubscribeRequest.getId());
      requestIdToSubscriptionIds.remove(request.getId());
      subscriptionIdToSubjects.remove(subscriptionId);
      if (!success) {
        LOGGER.error(
            String.format(
                "unsubscribe request id %d and subscriptionId %d " + "result false",
                unsubscribeRequest.getId(), subscriptionId));
      }
    }
  }
}
