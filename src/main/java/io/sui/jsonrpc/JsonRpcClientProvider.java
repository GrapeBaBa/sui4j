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


import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventEnvelope;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The interface Json rpc client provider.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class JsonRpcClientProvider {

  /** The constant nextId. */
  private final AtomicLong nextId = new AtomicLong();

  /**
   * Next id long.
   *
   * @return the long
   */
  private long nextId() {
    return nextId.incrementAndGet();
  }

  public abstract Disposable subscribe(
      JsonRpc20Request request, Consumer<EventEnvelope> onNext, Consumer<SuiApiException> onError);

  /**
   * Call completable future.
   *
   * @param <T> the type parameter
   * @param request the request
   * @param url the url
   * @param typeOfT the type of t
   * @return the completable future
   */
  public abstract <T> CompletableFuture<JsonRpc20Response<T>> call(
      JsonRpc20Request request, String url, Type typeOfT);

  /**
   * Call and unwrap response completable future.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param request the request
   * @param typeOfT the type of t
   * @return the completable future
   */
  @SuppressWarnings({"checkstyle:WhitespaceAfter", "unchecked"})
  public <T> CompletableFuture<T> callAndUnwrapResponse(
      String url, JsonRpc20Request request, Type typeOfT) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    this.call(request, url, typeOfT)
        .thenAccept(
            jsonRpc20Response -> {
              if (jsonRpc20Response.getError() != null) {
                final SuiApiException e;
                if (jsonRpc20Response.getThrowable() != null) {
                  e = new SuiApiException(jsonRpc20Response.getThrowable());
                  e.setError(jsonRpc20Response.getError());
                } else {
                  e = new SuiApiException(jsonRpc20Response.getError());
                }
                future.completeExceptionally(e);
              } else {
                future.complete((T) jsonRpc20Response.getResult());
              }
            })
        .exceptionally(
            throwable -> {
              SuiApiException e = new SuiApiException(throwable);
              future.completeExceptionally(e);
              return null;
            });
    return future;
  }

  /**
   * Create json rpc 20 request json rpc 20 request.
   *
   * @param method the method
   * @param params the params
   * @return the json rpc 20 request
   */
  public JsonRpc20Request createJsonRpc20Request(String method, List<?> params) {
    final JsonRpc20Request request = new JsonRpc20Request();
    request.setId(nextId());
    request.setMethod(method);
    request.setParams(params);
    return request;
  }
}
