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
import io.sui.models.CommitteeInfoResponse;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.GetObjectResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.TransactionResponse;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
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
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getObject", Lists.newArrayList(id));
    return call("/sui_getObject", request, new TypeToken<GetObjectResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getObjectsOwnedByAddress", Lists.newArrayList(address));
    return call(
        "/sui_getObjectsOwnedByAddress",
        request,
        new TypeToken<List<SuiObjectInfo>>() {}.getType());
  }

  @Override
  public CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByObject(String objectId) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getObjectsOwnedByObject", Lists.newArrayList(objectId));
    return call(
        "/sui_getObjectsOwnedByObject", request, new TypeToken<List<SuiObjectInfo>>() {}.getType());
  }

  @Override
  public CompletableFuture<GetObjectResponse> getRawObject(String id) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getRawObject", Lists.newArrayList(id));
    return call("/sui_getRawObject", request, new TypeToken<GetObjectResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<Long> getTotalTransactionNumber() {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getTotalTransactionNumber", Lists.newArrayList());
    return call("/sui_getTotalTransactionNumber", request, new TypeToken<Long>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionResponse> getTransaction(String digest) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getTransaction", Lists.newArrayList(digest));
    return call("/sui_getTransaction", request, new TypeToken<TransactionResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<String>> getTransactionsInRange(Long start, Long end) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getTransactionsInRange", Lists.newArrayList(start, end));
    return call("/sui_getTransactionsInRange", request, new TypeToken<List<String>>() {}.getType());
  }

  @Override
  public CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder) {
    final JsonRpc20Request request =
        createJsonRpc20Request(
            "sui_getEvents", Lists.newArrayList(query, cursor, limit, isDescOrder));
    return call("/sui_getEvents", request, new TypeToken<PaginatedEvents>() {}.getType());
  }

  @Override
  public CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId) {
    final JsonRpc20Request request =
        createJsonRpc20Request(
            "sui_getNormalizedMoveModulesByPackage", Lists.newArrayList(packageId));
    return call(
        "/sui_getNormalizedMoveModulesByPackage",
        request,
        new TypeToken<Map<String, MoveNormalizedModule>>() {}.getType());
  }

  @Override
  public CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch) {
    final JsonRpc20Request request =
        createJsonRpc20Request("sui_getCommitteeInfo", Lists.newArrayList(epoch));
    return call(
        "/sui_getCommitteeInfo", request, new TypeToken<CommitteeInfoResponse>() {}.getType());
  }

  @Override
  public CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function) {
    final JsonRpc20Request request =
        createJsonRpc20Request(
            "sui_getMoveFunctionArgTypes", Lists.newArrayList(suiPackage, module, function));
    return call(
        "/sui_getMoveFunctionArgTypes",
        request,
        new TypeToken<List<MoveFunctionArgType>>() {}.getType());
  }

  private JsonRpc20Request createJsonRpc20Request(String method, List<?> params) {
    final JsonRpc20Request request = new JsonRpc20Request();
    request.setId(jsonRpcClientProvider.nextId());
    request.setMethod(method);
    request.setParams(params);
    return request;
  }

  @SuppressWarnings({"checkstyle:WhitespaceAfter", "unchecked"})
  private <T> CompletableFuture<T> call(String url, JsonRpc20Request request, Type typeOfT) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    jsonRpcClientProvider
        .call(request, url, typeOfT)
        .thenAccept(
            suiObjectJsonRpc20Response -> {
              if (suiObjectJsonRpc20Response.getError() != null) {
                SuiApiException e = new SuiApiException(suiObjectJsonRpc20Response.getError());
                if (suiObjectJsonRpc20Response.getThrowable() != null) {
                  e.setCause(suiObjectJsonRpc20Response.getThrowable());
                }
                future.completeExceptionally(e);
              } else {
                future.complete((T) suiObjectJsonRpc20Response.getResult());
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
}
