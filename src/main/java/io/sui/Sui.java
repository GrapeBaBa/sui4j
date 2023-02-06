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

package io.sui;


import com.novi.serde.SerializationError;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sui.bcsgen.Intent;
import io.sui.bcsgen.TransactionData;
import io.sui.clients.BcsSerializationException;
import io.sui.clients.EventClient;
import io.sui.clients.EventClientImpl;
import io.sui.clients.ExecutionClient;
import io.sui.clients.ExecutionClientImpl;
import io.sui.clients.JsonRpcTransactionBuilder;
import io.sui.clients.LocalTransactionBuilder;
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
import io.sui.models.events.EventEnvelope;
import io.sui.models.events.EventFilter;
import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.Balance;
import io.sui.models.objects.CheckpointContents;
import io.sui.models.objects.CheckpointSummary;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.PaginatedCoins;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiSystemState;
import io.sui.models.objects.ValidatorMetadata;
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
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

/**
 * The type Sui.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class Sui {

  private final KeyStore keyStore;

  private final QueryClient queryClient;

  private final TransactionBuilder transactionBuilder;

  private final ExecutionClient executionClient;

  private final EventClient eventClient;

  /**
   * Instantiates a new Sui.
   *
   * @param rpcEndpoint the rpc endpoint
   * @param keyStorePath the key store path
   */
  public Sui(String rpcEndpoint, String keyStorePath) {
    this(rpcEndpoint, keyStorePath, true);
  }

  /**
   * Instantiates a new Sui.
   *
   * @param rpcEndpoint the rpc endpoint
   * @param keyStorePath the key store path
   * @param useLocalTransactionBuilder the use local transaction builder
   */
  public Sui(String rpcEndpoint, String keyStorePath, boolean useLocalTransactionBuilder) {
    this.keyStore = new FileBasedKeyStore(keyStorePath);
    final JsonHandler jsonHandler = new GsonJsonHandler();
    final JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(rpcEndpoint, jsonHandler);
    this.queryClient = new QueryClientImpl(jsonRpcClientProvider);
    this.transactionBuilder =
        useLocalTransactionBuilder
            ? new LocalTransactionBuilder(this.queryClient)
            : new JsonRpcTransactionBuilder(jsonRpcClientProvider);
    this.executionClient = new ExecutionClientImpl(jsonRpcClientProvider);
    this.eventClient = new EventClientImpl(jsonRpcClientProvider);
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
  }

  /**
   * Transfer object completable future.
   *
   * @param signer the signer
   * @param suiObject the sui object
   * @param recipient the recipient
   * @param gas the gas
   * @param gasBudget the gas budget
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
        .thenCompose(signAndExecuteTransactionFunction(signer, defaultIntent(), requestType));
  }

  /**
   * Subscribe event disposable.
   *
   * @param eventFilter the event filter
   * @param onNext the on next
   * @param onError the on error
   * @return the disposable
   */
  public Disposable subscribeEvent(
      EventFilter eventFilter, Consumer<EventEnvelope> onNext, Consumer<SuiApiException> onError) {
    return this.eventClient.subscribeEvent(eventFilter, onNext, onError);
  }

  /**
   * Gets object.
   *
   * @param id the id
   * @return the object
   */
  public CompletableFuture<ObjectResponse> getObject(String id) {
    return queryClient.getObject(id);
  }

  /**
   * Gets objects owned by address.
   *
   * @param address the address
   * @return the objects owned by address
   */
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address) {
    return queryClient.getObjectsOwnedByAddress(address);
  }

  /**
   * Gets objects owned by object.
   *
   * @param objectId the object id
   * @return the objects owned by object
   */
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByObject(String objectId) {
    return queryClient.getObjectsOwnedByObject(objectId);
  }

  /**
   * Gets raw object.
   *
   * @param id the id
   * @return the raw object
   */
  public CompletableFuture<ObjectResponse> getRawObject(String id) {
    return queryClient.getRawObject(id);
  }

  /**
   * Gets total transaction number.
   *
   * @return the total transaction number
   */
  public CompletableFuture<Long> getTotalTransactionNumber() {
    return queryClient.getTotalTransactionNumber();
  }

  /**
   * Gets transaction.
   *
   * @param digest the digest
   * @return the transaction
   */
  public CompletableFuture<TransactionResponse> getTransaction(String digest) {
    return queryClient.getTransaction(digest);
  }

  /**
   * Gets transactions in range.
   *
   * @param start the start
   * @param end the end
   * @return the transactions in range
   */
  public CompletableFuture<List<String>> getTransactionsInRange(Long start, Long end) {
    return queryClient.getTransactionsInRange(start, end);
  }

  /**
   * get the authority public keys that commits to the authority signature of the transaction.
   *
   * @param transactionDigest the digest
   * @return the Transaction auth signers
   */
  public CompletableFuture<TransactionResponse> getTransactionAuthSigners(String transactionDigest) {
    return queryClient.getTransactionAuthSigners(transactionDigest);
  }

  /**
   * get all validators available for stake delegation.
   *
   * @return all validators available for stake delegation.
   */
  public CompletableFuture<List<ValidatorMetadata>> getValidators() {
    return queryClient.getValidators();
  }

  public CompletableFuture<SuiSystemState> getSuiSystemState() {
    return queryClient.getSuiSystemState();
  }

  /**
   * Gets events.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the events
   */
  public CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder) {
    return queryClient.getEvents(query, cursor, limit, isDescOrder);
  }

  /**
   * Gets normalized move modules by package.
   *
   * @param packageId the package id
   * @return the normalized move modules by package
   */
  public CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId) {
    return queryClient.getNormalizedMoveModulesByPackage(packageId);
  }

  /**
   * Gets committee info.
   *
   * @param epoch the epoch
   * @return the committee info
   */
  public CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch) {
    return queryClient.getCommitteeInfo(epoch);
  }

  /**
   * Gets move function arg types.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the move function arg types
   */
  public CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function) {
    return queryClient.getMoveFunctionArgTypes(suiPackage, module, function);
  }

  /**
   * Gets normalized move function.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the normalized move function
   */
  public CompletableFuture<MoveNormalizedFunction> getNormalizedMoveFunction(
      String suiPackage, String module, String function) {
    return queryClient.getNormalizedMoveFunction(suiPackage, module, function);
  }

  /**
   * Gets normalized move module.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @return the normalized move module
   */
  public CompletableFuture<MoveNormalizedModule> getNormalizedMoveModule(
      String suiPackage, String module) {
    return queryClient.getNormalizedMoveModule(suiPackage, module);
  }

  /**
   * Gets normalized move struct.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param struct the struct
   * @return the normalized move struct
   */
  public CompletableFuture<MoveNormalizedStruct> getNormalizedMoveStruct(
      String suiPackage, String module, String struct) {
    return queryClient.getNormalizedMoveStruct(suiPackage, module, struct);
  }

  /**
   * Try get past object completable future.
   *
   * @param objectId the object id
   * @param version the version
   * @return the completable future
   */
  public CompletableFuture<ObjectResponse> tryGetPastObject(String objectId, long version) {
    return queryClient.tryGetPastObject(objectId, version);
  }

  /**
   * Gets transactions.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the transactions
   */
  public CompletableFuture<PaginatedTransactionDigests> getTransactions(
      TransactionQuery query, String cursor, int limit, boolean isDescOrder) {
    return queryClient.getTransactions(query, cursor, limit, isDescOrder);
  }

  /**
   * Gets coin metadata.
   *
   * @param coinType the coin type
   * @return the coin metadata
   */
  public CompletableFuture<CoinMetadata> getCoinMetadata(String coinType) {
    return queryClient.getCoinMetadata(coinType);
  }

  /**
   * Gets reference gas price.
   *
   * @return the reference gas price
   */
  public CompletableFuture<Long> getReferenceGasPrice() {
    return queryClient.getReferenceGasPrice();
  }

  /**
   * Gets object ref.
   *
   * @param id the id
   * @return the object ref
   */
  public CompletableFuture<SuiObjectRef> getObjectRef(String id) {
    return queryClient.getObjectRef(id);
  }

  /**
   * get all balances by address
   *
   * @param address the sui address
   * @return the completable future
   */
  public CompletableFuture<List<Balance>> getAllBalances(String address) {
    return queryClient.getAllBalances(address);
  }

  /**
   * get all coins owned by address
   *
   * @param address the sui address
   * @param cursor the cursor
   * @param limit the limit
   * @return the completable future
   */
  public CompletableFuture<PaginatedCoins> getAllCoins(String address, String cursor, long limit) {
    return queryClient.getAllCoins(address, cursor, limit);
  }

  /**
   * get all Coin with coin_type objects owned by an address.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @param cursor the cursor
   * @param limit the limit
   * @return the completable future
   */
  public CompletableFuture<PaginatedCoins> getCoins(String address, String coinType, String cursor, long limit) {
    return queryClient.getCoins(address, coinType, cursor, limit);
  }

  /**
   * get the total coin balance for one coin type, owned by the address owner.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @return the completable future
   */
  public CompletableFuture<Balance> getBalance(String address, String coinType) {
    return queryClient.getBalance(address, coinType);
  }

  /**
   * get contents of a checkpoint, namely a list of execution digests
   *
   * @param seqNum the sequence number
   * @return the completable future
   */
  public CompletableFuture<CheckpointContents> getCheckpointContents(long seqNum) {
    return queryClient.getCheckpointContents(seqNum);
  }

  /**
   * get contents of a checkpoint based on checkpoint content digest
   *
   * @param checkpointDigest the checkpoint digest
   * @return the completable future
   */
  public CompletableFuture<CheckpointContents> getCheckpointContentsByDigest(String checkpointDigest) {
    return queryClient.getCheckpointContentsByDigest(checkpointDigest);
  }

  /**
   * get a checkpoint summary based on a checkpoint sequence number
   *
   * @param seqNum the checkpoint sequence number
   * @return the completable future
   */
  public CompletableFuture<CheckpointSummary> getCheckpointSummary(Long seqNum) {
    return queryClient.getCheckpointSummary(seqNum);
  }

  /**
   * get a checkpoint summary based on checkpoint digest
   *
   * @param checkpointDigest the checkpoint digest
   * @return the completable future
   */
  public CompletableFuture<CheckpointSummary> getCheckpointSummaryByDigest(String checkpointDigest) {
    return queryClient.getCheckpointSummaryByDigest(checkpointDigest);
  }

  /**
   * Dry run transaction completable future.
   *
   * @param txBytes the tx bytes
   * @return the completable future
   */
  public CompletableFuture<TransactionEffects> dryRunTransaction(String txBytes) {
    return executionClient.dryRunTransaction(txBytes);
  }

  /**
   * Gets by address.
   *
   * @param address the address
   * @return the by address
   */
  public SuiKeyPair<?> getByAddress(String address) {
    return keyStore.getByAddress(address);
  }

  /**
   * Addresses navigable set.
   *
   * @return the navigable set
   */
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

  /**
   * Execute transaction completable future.
   *
   * @param signer the signer
   * @param transactionData the transaction data
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> executeTransaction(
      String signer, TransactionData transactionData, ExecuteTransactionRequestType requestType) {
    return this.executeTransaction(signer, transactionData, defaultIntent(), requestType);
  }

  /**
   * Execute transaction completable future.
   *
   * @param signer the signer
   * @param transactionData the transaction data
   * @param intent the intent
   * @param requestType the request type
   * @return the completable future
   */
  public CompletableFuture<ExecuteTransactionResponse> executeTransaction(
      String signer,
      TransactionData transactionData,
      Intent intent,
      ExecuteTransactionRequestType requestType) {
    final SuiKeyPair<?> suiKeyPair = keyStore.getByAddress(signer);
    final byte[] publicKey = suiKeyPair.publicKeyBytes();
    final SignatureScheme signatureScheme = suiKeyPair.signatureScheme();

    final byte[] txBytes;
    final byte[] intentBytes;
    try {
      txBytes = transactionData.bcsSerialize();
      intentBytes = intent.bcsSerialize();
    } catch (SerializationError e) {
      CompletableFuture<ExecuteTransactionResponse> future = new CompletableFuture<>();
      future.completeExceptionally(new SuiApiException(new BcsSerializationException(e)));
      return future;
    }

    return signAndExecuteTransaction(
        txBytes, intentBytes, requestType, suiKeyPair, publicKey, signatureScheme);
  }

  private CompletableFuture<ExecuteTransactionResponse> signAndExecuteTransaction(
      byte[] transactionData,
      byte[] intentBytes,
      ExecuteTransactionRequestType requestType,
      SuiKeyPair<?> suiKeyPair,
      byte[] publicKey,
      SignatureScheme signatureScheme) {
    final byte[] signature;
    try {
      signature = suiKeyPair.sign(Arrays.concatenate(intentBytes, transactionData));
    } catch (SigningException e) {
      CompletableFuture<ExecuteTransactionResponse> future = new CompletableFuture<>();
      future.completeExceptionally(new SuiApiException(e));
      return future;
    }

    final byte[] serializedSignatureBytes =
        Arrays.concatenate(new byte[] {signatureScheme.getScheme()}, signature, publicKey);
    final String serializedSignature = Base64.toBase64String(serializedSignatureBytes);

    return executionClient.executeTransaction(
        Base64.toBase64String(transactionData), serializedSignature, requestType);
  }

  private Function<TransactionBytes, CompletableFuture<ExecuteTransactionResponse>>
      signAndExecuteTransactionFunction(
          String signer, Intent intent, ExecuteTransactionRequestType requestType) {
    return transactionBytes -> {
      final SuiKeyPair<?> suiKeyPair = keyStore.getByAddress(signer);
      final byte[] publicKey = suiKeyPair.publicKeyBytes();
      final SignatureScheme signatureScheme = suiKeyPair.signatureScheme();
      final TransactionData transactionData = transactionBytes.getLocalTxBytes();

      final byte[] txBytes;
      final byte[] intentBytes;
      try {
        txBytes =
            null != transactionData
                ? transactionData.bcsSerialize()
                : Base64.decode(transactionBytes.getTxBytes());
        intentBytes = intent.bcsSerialize();
      } catch (SerializationError e) {
        CompletableFuture<ExecuteTransactionResponse> future = new CompletableFuture<>();
        future.completeExceptionally(new SuiApiException(new BcsSerializationException(e)));
        return future;
      }

      return signAndExecuteTransaction(
          txBytes, intentBytes, requestType, suiKeyPair, publicKey, signatureScheme);
    };
  }

  private Intent defaultIntent() {
    final Intent.Builder intentBuilder = new Intent.Builder();
    intentBuilder.app_id = 0;
    intentBuilder.scope = 0;
    intentBuilder.version = 0;

    return intentBuilder.build();
  }
}
