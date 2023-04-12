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


import io.sui.models.transactions.RPCTransactionRequestParams;
import io.sui.models.transactions.TransactionBytes;
import io.sui.models.transactions.TypeTag;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The interface Transaction builder.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface TransactionBuilder {

  /**
   * Split coin completable future.
   *
   * @param signer the signer
   * @param coin the coin object id
   * @param splitAmounts the split amounts
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> splitCoin(
      String signer, String coin, List<Long> splitAmounts, String gas, long gasBudget);

  /**
   * Split coin equal completable future.
   *
   * @param signer the signer
   * @param coin the coin object id
   * @param splitCount the split count
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> splitCoinEqual(
      String signer, String coin, long splitCount, String gas, long gasBudget);

  /**
   * Merge coins completable future.
   *
   * @param signer the signer
   * @param primaryCoin the primary coin
   * @param toMergeCoin the to merge coin
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> mergeCoins(
      String signer, String primaryCoin, String toMergeCoin, String gas, long gasBudget);

  /**
   * Pay completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> pay(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      String gas,
      Long gasBudget);

  /**
   * Pay sui completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> paySui(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      Long gasBudget);

  /**
   * Pay all sui completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipient the recipient
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> payAllSui(
      String signer, List<String> inputCoins, String recipient, Long gasBudget);

  /**
   * Transfer sui completable future.
   *
   * @param signer the signer
   * @param coin the coin
   * @param gasBudget the gas budget
   * @param recipient the recipient
   * @param amount the amount
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> transferSui(
      String signer, String coin, Long gasBudget, String recipient, Long amount);

  /**
   * Transfer object completable future.
   *
   * @param signer the signer
   * @param suiObject the sui object
   * @param recipient the recipient
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> transferObject(
      String signer, String suiObject, String recipient, String gas, Long gasBudget);

  /**
   * Batch transaction completable future.
   *
   * @param signer the signer
   * @param batchTransactionParams the batch transaction params
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> batchTransaction(
      String signer,
      List<RPCTransactionRequestParams> batchTransactionParams,
      String gas,
      long gasBudget);

  /**
   * Move call completable future.
   *
   * @param signer the signer
   * @param packageObjectId the package object id
   * @param module the module
   * @param function the function
   * @param typeArguments the type arguments
   * @param arguments the arguments
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> moveCall(
      String signer,
      String packageObjectId,
      String module,
      String function,
      List<TypeTag> typeArguments,
      List<?> arguments,
      String gas,
      long gasBudget);

  /**
   * Publish completable future.
   *
   * @param signer the signer
   * @param compiledModules the compiled modules
   * @param depIds the dep ids
   * @param gas the gas
   * @param gasBudget the gas budget
   * @return the completable future
   */
  CompletableFuture<TransactionBytes> publish(
      String signer, List<String> compiledModules, List<String> depIds, String gas, Long gasBudget);
}
