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


import io.sui.clients.ExecutionClient;
import io.sui.clients.ExecutionClientImpl;
import io.sui.clients.JsonRpcTransactionBuilder;
import io.sui.clients.QueryClient;
import io.sui.clients.QueryClientImpl;
import io.sui.clients.TransactionBuilder;
import io.sui.crypto.FileBasedKeyStore;
import io.sui.crypto.KeyStore;
import io.sui.crypto.SignatureScheme;
import io.sui.crypto.SigningException;
import io.sui.crypto.SuiKeyPair;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.ExecuteTransactionResponse;
import io.sui.models.transactions.PaginatedTransactionDigests;
import io.sui.models.transactions.RPCTransactionRequestParams;
import io.sui.models.transactions.TransactionBytes;
import io.sui.models.transactions.TransactionEffects;
import io.sui.models.transactions.TransactionQuery;
import io.sui.models.transactions.TransactionResponse;
import io.sui.models.transactions.TypeTag;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * The type Sui.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class Sui implements QueryClient, ExecutionClient, KeyStore {

  private final KeyStore keyStore;

  private final QueryClient queryClient;

  private final TransactionBuilder transactionBuilder;

  private final ExecutionClient executionClient;

  /**
   * Instantiates a new Sui.
   *
   * @param rpcEndpoint the rpc endpoint
   * @param keyStorePath the key store path
   */
  public Sui(String rpcEndpoint, String keyStorePath) {
    this.keyStore = new FileBasedKeyStore(keyStorePath);
    final JsonHandler jsonHandler = new GsonJsonHandler();
    final JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(rpcEndpoint, jsonHandler);
    this.queryClient = new QueryClientImpl(jsonRpcClientProvider);
    this.transactionBuilder = new JsonRpcTransactionBuilder(jsonRpcClientProvider);
    this.executionClient = new ExecutionClientImpl(jsonRpcClientProvider);
  }

  /**
   * Transfer sui completable future.
   *
   * @param signer the signer
   * @param coin the coin
   * @param gasBudget the gas budget
   * @param recipient the recipient
   * @param amount the amount
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> transferSui(
      String signer,
      String coin,
      long gasBudget,
      String recipient,
      long amount,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .transferSui(signer, coin, gasBudget, recipient, amount)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

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
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> moveCall(
      String signer,
      String packageObjectId,
      String module,
      String function,
      List<TypeTag> typeArguments,
      List<?> arguments,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .moveCall(
            signer, packageObjectId, module, function, typeArguments, arguments, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Batch transaction completable future.
   *
   * @param signer the signer
   * @param batchTransactionParams the batch transaction params
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> batchTransaction(
      String signer,
      List<RPCTransactionRequestParams> batchTransactionParams,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .batchTransaction(signer, batchTransactionParams, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Pay sui completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> paySui(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .paySui(signer, inputCoins, recipients, amounts, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Pay completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> pay(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .pay(signer, inputCoins, recipients, amounts, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Merge coins completable future.
   *
   * @param signer the signer
   * @param primaryCoin the primary coin
   * @param toMergeCoin the to merge coin
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> mergeCoins(
      String signer,
      String primaryCoin,
      String toMergeCoin,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .mergeCoins(signer, primaryCoin, toMergeCoin, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Split coin completable future.
   *
   * @param signer the signer
   * @param coin the coin
   * @param splitAmounts the split amounts
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> splitCoin(
      String signer,
      String coin,
      List<Long> splitAmounts,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .splitCoin(signer, coin, splitAmounts, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Pay all sui completable future.
   *
   * @param signer the signer
   * @param inputCoins the input coins
   * @param recipient the recipient
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> payAllSui(
      String signer,
      List<String> inputCoins,
      String recipient,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .payAllSui(signer, inputCoins, recipient, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Publish completable future.
   *
   * @param signer the signer
   * @param compiledModules the compiled modules
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> publish(
      String signer,
      List<String> compiledModules,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .publish(signer, compiledModules, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Split coin equal completable future.
   *
   * @param signer the signer
   * @param coin the coin
   * @param splitCount the split count
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> splitCoinEqual(
      String signer,
      String coin,
      long splitCount,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .splitCoinEqual(signer, coin, splitCount, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  /**
   * Transfer object completable future.
   *
   * @param signer the signer
   * @param suiObject the sui object
   * @param gas the gas
   * @param gasBudget the gas budget
   * @param recipient the recipient
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> transferObject(
      String signer,
      String suiObject,
      String recipient,
      String gas,
      long gasBudget,
      ExecuteTransactionRequestType requestType) {
    return this.transactionBuilder
        .transferObject(signer, suiObject, recipient, gas, gasBudget)
        .thenCompose(signAndExecuteTransactionFunction(signer, requestType));
  }

  @Override
  public CompletableFuture<ObjectResponse> getObject(String id) {
    return queryClient.getObject(id);
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address) {
    return queryClient.getObjectsOwnedByAddress(address);
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByObject(String objectId) {
    return queryClient.getObjectsOwnedByObject(objectId);
  }

  @Override
  public CompletableFuture<ObjectResponse> getRawObject(String id) {
    return queryClient.getRawObject(id);
  }

  @Override
  public CompletableFuture<Long> getTotalTransactionNumber() {
    return queryClient.getTotalTransactionNumber();
  }

  @Override
  public CompletableFuture<TransactionResponse> getTransaction(String digest) {
    return queryClient.getTransaction(digest);
  }

  @Override
  public CompletableFuture<List<String>> getTransactionsInRange(Long start, Long end) {
    return queryClient.getTransactionsInRange(start, end);
  }

  @Override
  public CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder) {
    return queryClient.getEvents(query, cursor, limit, isDescOrder);
  }

  @Override
  public CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId) {
    return queryClient.getNormalizedMoveModulesByPackage(packageId);
  }

  @Override
  public CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch) {
    return queryClient.getCommitteeInfo(epoch);
  }

  @Override
  public CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function) {
    return queryClient.getMoveFunctionArgTypes(suiPackage, module, function);
  }

  @Override
  public CompletableFuture<MoveNormalizedFunction> getNormalizedMoveFunction(
      String suiPackage, String module, String function) {
    return queryClient.getNormalizedMoveFunction(suiPackage, module, function);
  }

  @Override
  public CompletableFuture<MoveNormalizedModule> getNormalizedMoveModule(
      String suiPackage, String module) {
    return queryClient.getNormalizedMoveModule(suiPackage, module);
  }

  @Override
  public CompletableFuture<MoveNormalizedStruct> getNormalizedMoveStruct(
      String suiPackage, String module, String struct) {
    return queryClient.getNormalizedMoveStruct(suiPackage, module, struct);
  }

  @Override
  public CompletableFuture<ObjectResponse> tryGetPastObject(String objectId, long version) {
    return queryClient.tryGetPastObject(objectId, version);
  }

  @Override
  public CompletableFuture<PaginatedTransactionDigests> getTransactions(
      TransactionQuery query, String cursor, int limit, boolean isDescOrder) {
    return queryClient.getTransactions(query, cursor, limit, isDescOrder);
  }

  @Override
  public CompletableFuture<CoinMetadata> getCoinMetadata(String coinType) {
    return queryClient.getCoinMetadata(coinType);
  }

  @Override
  public CompletableFuture<TransactionEffects> dryRunTransaction(String txBytes) {
    return executionClient.dryRunTransaction(txBytes);
  }

  @Override
  public CompletableFuture<ExecuteTransactionResponse> executeTransaction(
      String txBytes,
      SignatureScheme signatureScheme,
      String signature,
      String publicKey,
      ExecuteTransactionRequestType requestType) {
    return executionClient.executeTransaction(
        txBytes, signatureScheme, signature, publicKey, requestType);
  }

  /**
   * Execute transaction completable future.
   *
   * @param txBytes the tx bytes
   * @param signer the signer
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> executeTransaction(
      String txBytes, String signer, ExecuteTransactionRequestType requestType) {
    final SuiKeyPair<?> suiKeyPair = keyStore.getByAddress(signer);
    final String publicKey = suiKeyPair.publicKey();
    final SignatureScheme signatureScheme = suiKeyPair.signatureScheme();
    return signAndExecuteTransaction(txBytes, requestType, suiKeyPair, publicKey, signatureScheme);
  }

  @Override
  public SuiKeyPair<?> getByAddress(String address) {
    return keyStore.getByAddress(address);
  }

  @Override
  public NavigableSet<String> addresses() {
    return keyStore.addresses();
  }

  /**
   * Gets transaction builder.
   *
   * @return the transaction builder
   */
  public TransactionBuilder getTransactionBuilder() {
    return transactionBuilder;
  }

  private CompletableFuture<ExecuteTransactionResponse> signAndExecuteTransaction(
      String txBytes,
      ExecuteTransactionRequestType requestType,
      SuiKeyPair<?> suiKeyPair,
      String publicKey,
      SignatureScheme signatureScheme) {
    final String signature;
    try {
      signature = suiKeyPair.sign(txBytes);
    } catch (SigningException e) {
      CompletableFuture<ExecuteTransactionResponse> future = new CompletableFuture<>();
      future.completeExceptionally(new SuiApiException(e));
      return future;
    }
    return executionClient.executeTransaction(
        txBytes, signatureScheme, signature, publicKey, requestType);
  }

  @NotNull private Function<TransactionBytes, CompletableFuture<ExecuteTransactionResponse>>
      signAndExecuteTransactionFunction(String signer, ExecuteTransactionRequestType requestType) {
    return transactionBytes -> {
      final SuiKeyPair<?> suiKeyPair = keyStore.getByAddress(signer);
      final String publicKey = suiKeyPair.publicKey();
      final SignatureScheme signatureScheme = suiKeyPair.signatureScheme();
      final String txBytes = transactionBytes.getTxBytes();
      return signAndExecuteTransaction(
          txBytes, requestType, suiKeyPair, publicKey, signatureScheme);
    };
  }
}
