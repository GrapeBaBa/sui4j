/*
 * Copyright 281165273grape@gmail.com
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

package io.sui;

import com.google.common.collect.Lists;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.models.GetObjectResponse;
import io.sui.models.SuiApiException;
import java.util.concurrent.CompletableFuture;


/**
 * The type Sui client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiClientImpl implements SuiClient {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  /**
   * Instantiates a new Sui client.
   *
   * @param jsonRpcClientProvider the json rpc client provider
   */
  public SuiClientImpl(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public CompletableFuture<GetObjectResponse> getObject(String id) {
    final JsonRpc20Request request = new JsonRpc20Request();
    request.setId(jsonRpcClientProvider.nextId());
    request.setMethod("sui_getObject");
    request.setParams(Lists.newArrayList(id));
    final CompletableFuture<GetObjectResponse> future = new CompletableFuture<>();
    jsonRpcClientProvider.call(request, "/sui_getObject", GetObjectResponse.class)
        .thenAccept(suiObjectJsonRpc20Response -> {
          if (suiObjectJsonRpc20Response.getError() != null) {
            SuiApiException e = new SuiApiException(suiObjectJsonRpc20Response.getError());
            if (suiObjectJsonRpc20Response.getThrowable() != null) {
              e.setCause(suiObjectJsonRpc20Response.getThrowable());
            }
            future.completeExceptionally(e);
          } else {
            future.complete(suiObjectJsonRpc20Response.getResult());
          }
        }).exceptionally(throwable -> {
          SuiApiException e = new SuiApiException(throwable);
          future.completeExceptionally(e);
          return null;
        });
    return future;
  }
}
