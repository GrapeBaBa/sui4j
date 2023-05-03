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

package io.sui.clients;


import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.TransactionBlockEffects;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The type Execution client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ExecutionClientImpl implements ExecutionClient {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  /**
   * Instantiates a new Json rpc transaction builder.
   *
   * @param jsonRpcClientProvider the json rpc client provider
   */
  public ExecutionClientImpl(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public CompletableFuture<TransactionBlockEffects> dryRunTransaction(String txBytes) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_dryRunTransaction", Lists.newArrayList(txBytes));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_dryRunTransaction", request, new TypeToken<TransactionBlockEffects>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBlockResponse> executeTransaction(
      String txBytes,
      List<String> signatures,
      TransactionBlockResponseOptions transactionBlockResponseOptions,
      ExecuteTransactionRequestType requestType) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_executeTransactionBlock",
            Lists.newArrayList(txBytes, signatures, transactionBlockResponseOptions, requestType));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_executeTransactionBlock",
        request,
        new TypeToken<TransactionBlockResponse>() {}.getType());
  }
}
