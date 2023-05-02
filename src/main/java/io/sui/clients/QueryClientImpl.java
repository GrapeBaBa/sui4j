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
import io.sui.models.coin.Balance;
import io.sui.models.coin.CoinMetadata;
import io.sui.models.coin.CoinSupply;
import io.sui.models.coin.PaginatedCoins;
import io.sui.models.events.EventFilter;
import io.sui.models.events.EventId;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.governance.DelegatedStake;
import io.sui.models.governance.SuiCommitteeInfo;
import io.sui.models.governance.SystemStateSummary;
import io.sui.models.governance.ValidatorsApy;
import io.sui.models.objects.CheckpointContents;
import io.sui.models.objects.CheckpointSummary;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiObjectResponse;
import io.sui.models.objects.SuiSystemState;
import io.sui.models.objects.ValidatorMetadata;
import io.sui.models.transactions.PaginatedTransactionResponse;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionBlockResponseQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.StringUtils;

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
  public CompletableFuture<SuiObjectResponse> getObject(
      String id, ObjectDataOptions objectDataOptions) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getObject", Lists.newArrayList(id, objectDataOptions));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getObject", request, new TypeToken<SuiObjectResponse>() {}.getType());
  }

  public CompletableFuture<SuiObjectRef> getObjectRef(
      String id, ObjectDataOptions objectDataOptions) {
    return this.getObject(id, objectDataOptions).thenApply(SuiObjectResponse::getObjectRef);
  }

  @Override
  public CompletableFuture<PaginatedObjectsResponse> getObjectsOwnedByAddress(
      String address, ObjectResponseQuery query, String cursor, Integer limit) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getOwnedObjects", Lists.newArrayList(address, query, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getOwnedObjects", request, new TypeToken<PaginatedObjectsResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<Long> getTotalTransactionBlocks() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTotalTransactionBlocks", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTotalTransactionBlocks", request, new TypeToken<Long>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBlockResponse> getTransactionBlock(
      String digest, TransactionBlockResponseOptions options) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getTransactionBlock", Lists.newArrayList(digest, options));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getTransactionBlock",
        request,
        new TypeToken<TransactionBlockResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<TransactionBlockResponse>> multiGetTransactionBlocks(
      List<String> digests, TransactionBlockResponseOptions options) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_multiGetTransactionBlocks", Lists.newArrayList(digests, options));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_multiGetTransactionBlocks",
        request,
        new TypeToken<List<TransactionBlockResponse>>() {}.getType());
  }

  @Override
  public CompletableFuture<List<SuiObjectResponse>> multiGetObjects(
      List<String> objectIds, ObjectDataOptions options) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_multiGetObjects", Lists.newArrayList(objectIds, options));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_multiGetObjects", request, new TypeToken<List<SuiObjectResponse>>() {}.getType());
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
  public CompletableFuture<PaginatedEvents> queryEvents(
      EventFilter eventFilter, EventId cursor, Integer limit, boolean isDescOrder) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_queryEvents", Lists.newArrayList(eventFilter, cursor, limit, isDescOrder));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_queryEvents", request, new TypeToken<PaginatedEvents>() {}.getType());
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
  public CompletableFuture<SuiCommitteeInfo> getCommitteeInfo(BigInteger epoch) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getCommitteeInfo", Lists.newArrayList(epoch.toString()));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getCommitteeInfo", request, new TypeToken<SuiCommitteeInfo>() {}.getType());
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
  public CompletableFuture<PaginatedTransactionResponse> queryTransactionBlocks(
      TransactionBlockResponseQuery query, String cursor, Integer limit, boolean isDescOrder) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_queryTransactionBlocks", Lists.newArrayList(query, cursor, limit, isDescOrder));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_queryTransactionBlocks",
        request,
        new TypeToken<PaginatedTransactionResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedObjectsResponse> queryObjects(
      ObjectResponseQuery query, String cursor, Integer limit) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_queryObjects", Lists.newArrayList(query, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_queryObjects", request, new TypeToken<PaginatedObjectsResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<CoinMetadata> getCoinMetadata(String coinType) {
    if (StringUtils.isEmpty(coinType)) {
      coinType = DEFAULT_COIN_TYPE;
    }
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getCoinMetadata", Lists.newArrayList(coinType));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getCoinMetadata", request, new TypeToken<CoinMetadata>() {}.getType());
  }

  @Override
  public CompletableFuture<Long> getReferenceGasPrice() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getReferenceGasPrice", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getReferenceGasPrice", request, new TypeToken<Long>() {}.getType());
  }

  @Override
  public CompletableFuture<List<Balance>> getAllBalances(String address) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getAllBalances", Lists.newArrayList(address));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getAllBalances", request, new TypeToken<List<Balance>>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedCoins> getAllCoins(
      String address, String cursor, Integer limit) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getAllCoins", Lists.newArrayList(address, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getAllCoins", request, new TypeToken<PaginatedCoins>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedCoins> getCoins(
      String address, String coinType, String cursor, long limit) {
    if (StringUtils.isEmpty(coinType)) {
      coinType = DEFAULT_COIN_TYPE;
    }
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getCoins", Lists.newArrayList(address, coinType, cursor, limit));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getCoins", request, new TypeToken<PaginatedCoins>() {}.getType());
  }

  @Override
  public CompletableFuture<Balance> getBalance(String address, String coinType) {
    if (StringUtils.isEmpty(coinType)) {
      coinType = DEFAULT_COIN_TYPE;
    }
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getBalance", Lists.newArrayList(address, coinType));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getBalance", request, new TypeToken<Balance>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointContents> getCheckpointContents(long sequenceNumber) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCheckpointContents", Lists.newArrayList(sequenceNumber));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointContents", request, new TypeToken<CheckpointContents>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointContents> getCheckpointContentsByDigest(
      String checkpointDigest) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCheckpointContentsByDigest", Lists.newArrayList(checkpointDigest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointContentsByDigest",
        request,
        new TypeToken<CheckpointContents>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointSummary> getCheckpointSummary(Long seqNum) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCheckpointSummary", Lists.newArrayList(seqNum));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointSummary", request, new TypeToken<CheckpointSummary>() {}.getType());
  }

  @Override
  public CompletableFuture<CheckpointSummary> getCheckpointSummaryByDigest(
      String checkpointDigest) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_getCheckpointSummaryByDigest", Lists.newArrayList(checkpointDigest));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_getCheckpointSummaryByDigest",
        request,
        new TypeToken<CheckpointSummary>() {}.getType());
  }

  @Override
  public CompletableFuture<ValidatorsApy> getValidatorsApy() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getValidatorsApy", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getValidatorsApy", request, new TypeToken<ValidatorsApy>() {}.getType());
  }

  @Override
  public CompletableFuture<CoinSupply> getTotalSupply(String coin) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getTotalSupply", Lists.newArrayList(coin));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getTotalSupply", request, new TypeToken<CoinSupply>() {}.getType());
  }

  @Override
  public CompletableFuture<List<DelegatedStake>> getStakesByIds(List<String> stakedSuiIds) {
    List<List<String>> params = Lists.newArrayList();
    params.add(stakedSuiIds);
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request("suix_getStakesByIds", params);
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getStakesByIds", request, new TypeToken<List<DelegatedStake>>() {}.getType());
  }

  @Override
  public CompletableFuture<List<DelegatedStake>> getStakes(String owner) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getStakes", Lists.newArrayList(owner));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getStakes", request, new TypeToken<List<DelegatedStake>>() {}.getType());
  }

  @Override
  public CompletableFuture<SystemStateSummary> getLatestSuiSystemState() {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_getLatestSuiSystemState", Lists.newArrayList());
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/suix_getLatestSuiSystemState", request, new TypeToken<SystemStateSummary>() {}.getType());
  }
}
