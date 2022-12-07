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
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.ExecuteTransactionResponse;
import io.sui.models.transactions.TransactionBytes;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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
        .thenCompose(
            (Function<TransactionBytes, CompletableFuture<ExecuteTransactionResponse>>)
                transactionBytes -> {
                  final SuiKeyPair<?> suiKeyPair = keyStore.getByAddress(signer);
                  final String publicKey = suiKeyPair.publicKey();
                  final SignatureScheme signatureScheme = suiKeyPair.signatureScheme();
                  final String txBytes = transactionBytes.getTxBytes();
                  final String signature;
                  try {
                    signature = suiKeyPair.sign(txBytes);
                  } catch (SigningException e) {
                    CompletableFuture<ExecuteTransactionResponse> future =
                        new CompletableFuture<>();
                    future.completeExceptionally(new SuiApiException(e));
                    return future;
                  }
                  return executionClient.executeTransaction(
                      txBytes, signatureScheme, signature, publicKey, requestType);
                });
  }

  /**
   * Gets objects owned by address.
   *
   * @param address the address
   * @return the objects owned by address
   */
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address) {
    return this.queryClient.getObjectsOwnedByAddress(address);
  }

  /**
   * Gets key store.
   *
   * @return the key store
   */
  public KeyStore getKeyStore() {
    return keyStore;
  }

  /**
   * Gets query client.
   *
   * @return the query client
   */
  public QueryClient getQueryClient() {
    return queryClient;
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
   * Gets execution client.
   *
   * @return the execution client
   */
  public ExecutionClient getExecutionClient() {
    return executionClient;
  }
}
