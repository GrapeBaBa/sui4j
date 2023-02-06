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
import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.Balance;
import io.sui.models.objects.CheckpointContents;
import io.sui.models.objects.CheckpointSummary;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.PaginatedCoins;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiSystemState;
import io.sui.models.objects.ValidatorMetadata;
import io.sui.models.transactions.PaginatedTransactionDigests;
import io.sui.models.transactions.TransactionQuery;
import io.sui.models.transactions.TransactionResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * The type Sui client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class QueryClientImpl implements QueryClient {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  /**
   * Instantiates a new Sui client.
   *
   * @param jsonRpcClientProvider the json rpc client provider
   */
  public QueryClientImpl(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public CompletableFuture<ObjectResponse> getObject(String id) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request("sui_getObject", Lists.newArrayList(id));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getObject", request, new TypeToken<ObjectResponse>() {}.getType());
  }

  public CompletableFuture<SuiObjectRef> getObjectRef(String id) {
    return this.getObject(id).thenApply(ObjectResponse::getObjectRef);
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getObjectsOwnedByAddress", Lists.newArrayList(address));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getObjectsOwnedByAddress",
        request,
        new TypeToken<List<SuiObjectInfo>>() {}.getType());
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByObject(String objectId) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getObjectsOwnedByObject", Lists.newArrayList(objectId));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getObjectsOwnedByObject", request, new TypeToken<List<SuiObjectInfo>>() {}.getType());
  }

  @Override
  public CompletableFuture<ObjectResponse> getRawObject(String id) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getRawObject", Lists.newArrayList(id));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getRawObject", request, new TypeToken<ObjectResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<Long> getTotalTransactionNumber() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTotalTransactionNumber", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTotalTransactionNumber", request, new TypeToken<Long>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionResponse> getTransaction(String digest) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTransaction", Lists.newArrayList(digest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTransaction", request, new TypeToken<TransactionResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionResponse> getTransactionAuthSigners(String transactionDigest) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTransactionAuthSigners", Lists.newArrayList(transactionDigest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTransactionAuthSigners", request, new TypeToken<TransactionResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<String>> getTransactionsInRange(Long start, Long end) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTransactionsInRange", Lists.newArrayList(start, end));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTransactionsInRange", request, new TypeToken<List<String>>() {}.getType());
  }

  @Override
  public CompletableFuture<SuiSystemState> getSuiSystemState() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getSuiSystemState", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getSuiSystemState", request, new TypeToken<SuiSystemState>() {}.getType());
  }

  @Override
  public CompletableFuture<List<ValidatorMetadata>> getValidators() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getValidators", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getValidators", request, new TypeToken<List<ValidatorMetadata>>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getEvents", Lists.newArrayList(query, cursor, limit, isDescOrder));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getEvents", request, new TypeToken<PaginatedEvents>() {}.getType());
  }

  @Override
  public CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getNormalizedMoveModulesByPackage", Lists.newArrayList(packageId));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getNormalizedMoveModulesByPackage",
        request,
        new TypeToken<Map<String, MoveNormalizedModule>>() {}.getType());
  }

  @Override
  public CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCommitteeInfo", Lists.newArrayList(epoch));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCommitteeInfo", request, new TypeToken<CommitteeInfoResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getMoveFunctionArgTypes", Lists.newArrayList(suiPackage, module, function));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getMoveFunctionArgTypes",
        request,
        new TypeToken<List<MoveFunctionArgType>>() {}.getType());
  }

  @Override
  public CompletableFuture<MoveNormalizedFunction> getNormalizedMoveFunction(
      String suiPackage, String module, String function) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getNormalizedMoveFunction", Lists.newArrayList(suiPackage, module, function));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getNormalizedMoveFunction",
        request,
        new TypeToken<MoveNormalizedFunction>() {}.getType());
  }

  @Override
  public CompletableFuture<MoveNormalizedModule> getNormalizedMoveModule(
      String suiPackage, String module) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getNormalizedMoveModule", Lists.newArrayList(suiPackage, module));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getNormalizedMoveModule",
        request,
        new TypeToken<MoveNormalizedModule>() {}.getType());
  }

  @Override
  public CompletableFuture<MoveNormalizedStruct> getNormalizedMoveStruct(
      String suiPackage, String module, String struct) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getNormalizedMoveStruct", Lists.newArrayList(suiPackage, module, struct));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getNormalizedMoveStruct",
        request,
        new TypeToken<MoveNormalizedStruct>() {}.getType());
  }

  @Override
  public CompletableFuture<ObjectResponse> tryGetPastObject(String objectId, long version) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_tryGetPastObject", Lists.newArrayList(objectId, version));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_tryGetPastObject", request, new TypeToken<ObjectResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedTransactionDigests> getTransactions(
      TransactionQuery query, String cursor, int limit, boolean isDescOrder) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTransactions", Lists.newArrayList(query, cursor, limit, isDescOrder));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTransactions", request, new TypeToken<PaginatedTransactionDigests>() {}.getType());
  }

  @Override
  public CompletableFuture<CoinMetadata> getCoinMetadata(String coinType) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCoinMetadata", Lists.newArrayList(coinType));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCoinMetadata", request, new TypeToken<CoinMetadata>() {}.getType());
  }

  @Override
  public CompletableFuture<Long> getReferenceGasPrice() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getReferenceGasPrice", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getReferenceGasPrice", request, new TypeToken<Long>() {}.getType());
  }

  @Override
  public CompletableFuture<List<Balance>> getAllBalances(String address) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getAllBalances", Lists.newArrayList(address));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getAllBalances", request, new TypeToken<List<Balance>>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedCoins> getAllCoins(String address, String cursor, long limit) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getAllCoins", Lists.newArrayList(address, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getAllCoins", request, new TypeToken<PaginatedCoins>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedCoins> getCoins(String address, String coinType, String cursor, long limit) {
    if (StringUtils.isEmpty(coinType)) {
      coinType = DEFAULT_COIN_TYPE;
    }
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getCoins", Lists.newArrayList(address, coinType, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCoins", request, new TypeToken<PaginatedCoins>() {}.getType());
  }

  @Override
  public CompletableFuture<Balance> getBalance(String address, String coinType) {
    if (StringUtils.isEmpty(coinType)) {
      coinType = DEFAULT_COIN_TYPE;
    }
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getBalance", Lists.newArrayList(address, coinType));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getBalance", request, new TypeToken<Balance>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointContents> getCheckpointContents(long sequenceNumber) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getCheckpointContents", Lists.newArrayList(sequenceNumber));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointContents", request, new TypeToken<CheckpointContents>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointContents> getCheckpointContentsByDigest(String checkpointDigest) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getCheckpointContentsByDigest", Lists.newArrayList(checkpointDigest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointContentsByDigest", request, new TypeToken<CheckpointContents>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointSummary> getCheckpointSummary(Long seqNum) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getCheckpointSummary", Lists.newArrayList(seqNum));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointSummary", request, new TypeToken<CheckpointSummary>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointSummary> getCheckpointSummaryByDigest(String checkpointDigest) {
    final JsonRpc20Request request = this.jsonRpcClientProvider.createJsonRpc20Request(
        "sui_getCheckpointSummaryByDigest", Lists.newArrayList(checkpointDigest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointSummaryByDigest", request, new TypeToken<CheckpointSummary>() {}.getType());
  }

}
