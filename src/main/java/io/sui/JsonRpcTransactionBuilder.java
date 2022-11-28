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

package io.sui;


import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.models.transactions.TransactionBytes;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The type Json rpc transaction builder.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class JsonRpcTransactionBuilder implements TransactionBuilder {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  /**
   * Instantiates a new Json rpc transaction builder.
   *
   * @param jsonRpcClientProvider the json rpc client provider
   */
  public JsonRpcTransactionBuilder(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoin(
      String signer, String coin, List<Long> splitAmounts, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_splitCoin", Lists.newArrayList(signer, coin, splitAmounts, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_splitCoin", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoinEqual(
      String signer, String coin, long splitCount, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_splitCoinEqual", Lists.newArrayList(signer, coin, splitCount, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_splitCoinEqual", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> mergeCoins(
      String signer, String primaryCoin, String toMergeCoin, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_mergeCoins", Lists.newArrayList(signer, primaryCoin, toMergeCoin, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_mergeCoins", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> pay(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      String gas,
      long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_pay", Lists.newArrayList(signer, inputCoins, recipients, amounts, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_pay", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> paySui(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_paySui", Lists.newArrayList(signer, inputCoins, recipients, amounts, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_paySui", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> payAllSui(
      String signer, List<String> inputCoins, String recipient, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_payAllSui", Lists.newArrayList(signer, inputCoins, recipient, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_payAllSui", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> transferSui(
      String signer, String coin, long gasBudget, String recipient, long amount) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_transferSui", Lists.newArrayList(signer, coin, gasBudget, recipient, amount));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_transferSui", request, new TypeToken<TransactionBytes>() {}.getType());
  }
}
