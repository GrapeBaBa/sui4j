/*
 * Copyright 2022 281165273grape@gmail.com
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


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

/**
 * The type Ok http json rpc client provider.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class OkHttpJsonRpcClientProvider implements JsonRpcClientProvider {

  private final String baseUrl;

  private final OkHttpClient client;

  private final JsonHandler jsonHandler;

  /**
   * Instantiates a new Ok http json rpc client provider.
   *
   * @param baseUrl the base url
   * @param jsonHandler the json handler
   */
  public OkHttpJsonRpcClientProvider(String baseUrl, JsonHandler jsonHandler) {
    this.baseUrl = baseUrl;
    this.jsonHandler = jsonHandler;
    this.client = new OkHttpClient().newBuilder().build();
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
              public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final JsonRpc20Response<T> jsonRpc20Response = new JsonRpc20Response<>();
                JsonRpc20Response.Error error = new JsonRpc20Response.Error();
                error.setCode(JsonRpc20Response.Error.ErrorCode.IO_ERROR);
                jsonRpc20Response.setError(error);
                jsonRpc20Response.setThrowable(e);
                future.complete(jsonRpc20Response);
              }

              @Override
              public void onResponse(@NotNull Call call, @NotNull Response response) {
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
}
