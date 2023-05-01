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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.sui.clients.QueryClient;
import io.sui.clients.QueryClientImpl;
import io.sui.json.GsonJsonHandler;
import io.sui.json.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.events.EventQuery.TransactionEventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.CheckpointContents;
import io.sui.models.objects.CheckpointSummary;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveModule;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectDataFilter;
import io.sui.models.objects.ObjectDataFilter.MoveModuleFilter;
import io.sui.models.objects.ObjectDataFilter.StructTypeFilter;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiSystemState;
import io.sui.models.objects.ValidatorMetadata;
import io.sui.models.transactions.PaginatedTransactionResponse;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionBlockResponseQuery;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Sui client impl tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
class QueryClientImplIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final String BASE_FAUCET_URL = "http://localhost:9123";

  private static final String TEST_KEY_STORE_PATH =
      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static QueryClient client;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    client = new QueryClientImpl(jsonRpcClientProvider);
  }

  /**
   * Gets objects owned by address is not empty.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObjectsOwnedByAddress returns not empty list.")
  void getObjectsOwnedByAddressIsNotEmpty() throws ExecutionException, InterruptedException {
    ObjectDataFilter.StructTypeFilter filter = new StructTypeFilter();
    filter.setStructType("0x2::coin::Coin<0x2::sui::SUI>");
    ObjectResponseQuery query = new ObjectResponseQuery();
    query.setFilter(filter);
    ObjectDataOptions objectDataOptions = new ObjectDataOptions();
    objectDataOptions.setShowOwner(true);
    objectDataOptions.setShowPreviousTransaction(true);
    query.setOptions(objectDataOptions);
    CompletableFuture<PaginatedObjectsResponse> res =
        client.getObjectsOwnedByAddress(
            "0xb43d0468fbc80c81931b73a4b9ef4663e671b65a07ae5b336a0e7d8a70ac0646",
            query,
            null,
            null);
    CompletableFuture<Object> future = new CompletableFuture<>();
    res.whenComplete(
        (paginatedObjectsResponse, throwable) -> {
          if (throwable != null) {
            future.complete(throwable);
          } else {
            future.complete(paginatedObjectsResponse);
          }
        });
    System.out.println(future.get());

    ObjectDataFilter.MoveModuleFilter moduleFilter = new MoveModuleFilter();
    MoveModule moveModule = new MoveModule();
    moveModule.setModule("coin");
    moveModule.setSuiPackage("0x0000000000000000000000000000000000000002");
    moduleFilter.setMoveModule(moveModule);
    ObjectResponseQuery query1 = new ObjectResponseQuery();
    query1.setFilter(moduleFilter);
    query1.setOptions(objectDataOptions);
    CompletableFuture<PaginatedObjectsResponse> res1 =
        client.getObjectsOwnedByAddress(
            "0xb43d0468fbc80c81931b73a4b9ef4663e671b65a07ae5b336a0e7d8a70ac0646",
            query1,
            null,
            null);
    CompletableFuture<Object> future1 = new CompletableFuture<>();
    res1.whenComplete(
        (paginatedObjectsResponse, throwable) -> {
          if (throwable != null) {
            future1.complete(throwable);
          } else {
            future1.complete(paginatedObjectsResponse);
          }
        });
    System.out.println(future1.get());
    //    assertTrue(response.size() > 0);
    //    assertEquals(
    //        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //        ((AddressOwner) response.get(0).getOwner()).getAddressOwner());
    //    assertEquals(
    //        "GN9sW4hBVNFIc83VIfyn/J1n4a9tU9sQVq3+UkfgEKU=",
    // response.get(0).getPreviousTransaction());
  }

  /**
   * Gets total transaction number.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTotalTransactionBlocks.")
  void getTotalTransactionNumber() throws ExecutionException, InterruptedException {
    CompletableFuture<Long> res = client.getTotalTransactionBlocks();
    System.out.println(res.get());
    //    assertEquals(2L, res.get());
  }

  /**
   * Gets transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTransactionBlock.")
  void getTransaction() throws ExecutionException, InterruptedException {
    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowEffects(true);
    options.setShowEvents(true);
    options.setShowObjectChanges(true);
    options.setShowInput(true);
    CompletableFuture<TransactionBlockResponse> res =
        client.getTransactionBlock("2zcrJHVMnqjQ47iauQsSqdDzpJVKzTrrohu4mYcGr2JG", options);
    TransactionBlockResponse transactionBlockResponse = res.get();
    System.out.println(transactionBlockResponse);
    //    TransactionResponse transactionResponse = res.get();
    //    System.out.println(transactionResponse);
    //    assertEquals(1,
    // transactionResponse.getCertificate().getAuthSignInfo().getSignature().size());
    //    assertEquals(
    //        "g+aeuIw6zZ08o3PP+qX1G7h+KLfGSbM7Rk3ZLHu2QjbYhZViqRchJOhKVbZw0pQI",
    //        transactionResponse.getCertificate().getAuthSignInfo().getSignature().get(0));
    //    assertEquals(
    //        "AIinOofScNIfh4XjXlN1fhtT4hFyQXDZsr72PBG731kC9Xl++yhAQSxZJqkvSPf3LOCQsLYxovYAXSut"
    //            + "4wb9uAefzp9vXA0ydchzCCVdlo/OyzDxzcQ/iCDrGuPfEkHJiA==",
    //        transactionResponse.getCertificate().getTxSignature());
    //    assertEquals(
    //        "3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=",
    //        transactionResponse.getCertificate().getTransactionDigest());
    //
    //    assertEquals(1,
    // transactionResponse.getCertificate().getData().queryTransactionBlocks().size());
    //    MoveCall call =
    //        ((TransactionKind.CallTransactionKind)
    //
    // transactionResponse.getCertificate().getData().queryTransactionBlocks().get(0))
    //            .getCall();
    //    assertEquals("devnet_nft", call.getModule());
    //    assertEquals("mint", call.getFunction());
    //    assertEquals(3, call.getArguments().size());
    //    assertEquals(
    //        "0x342950ba2451c2f27ed128e591c2b4551e5177c2",
    //        transactionResponse.getCertificate().getData().getGasPayment().getObjectId());
    //    assertEquals(
    //        ExecutionStatusType.success,
    // transactionResponse.getEffects().getStatus().getStatus());
    //    assertEquals(
    //        "3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=",
    //        transactionResponse.getEffects().getTransactionDigest());
    //    assertEquals(
    //        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //        ((AddressOwner) transactionResponse.getEffects().getMutated().get(0).getOwner())
    //            .getAddressOwner());
    //    assertEquals(
    //        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //        ((AddressOwner) transactionResponse.getEffects().getCreated().get(0).getOwner())
    //            .getAddressOwner());
    //    assertEquals(
    //        "0xb5e91320d3acc77b4d9e66a218031441b2be1bb3",
    //        transactionResponse.getEffects().getCreated().get(0).getReference().getObjectId());
    //    assertEquals(3, transactionResponse.getEffects().getEvents().size());
    //    CoinBalanceChangeEvent coinBalanceChangeEvent =
    //        ((EventKind.CoinBalanceChangeEventKind)
    // transactionResponse.getEffects().getEvents().get(0))
    //            .getCoinBalanceChange();
    //    assertEquals(BalanceChangeType.Gas, coinBalanceChangeEvent.getChangeType());
    //    assertEquals(
    //        "0x342950ba2451c2f27ed128e591c2b4551e5177c2",
    // coinBalanceChangeEvent.getCoinObjectId());
    //    MoveEvent moveEvent =
    //        ((EventKind.MoveEventKind) transactionResponse.getEffects().getEvents().get(2))
    //            .getMoveEvent();
    //    assertEquals(
    //        "0xb5e91320d3acc77b4d9e66a218031441b2be1bb3", moveEvent.getFields().get("object_id"));
  }

  /**
   * Gets transaction auth signers.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTransactionAuthSigners.")
  void getTransactionAuthSigners() throws ExecutionException, InterruptedException {
    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowEffects(true);
    options.setShowEvents(true);
    options.setShowObjectChanges(true);
    options.setShowInput(true);
    CompletableFuture<TransactionBlockResponse> res =
        client.getTransactionBlock("49rpBTf2KUkf4aroydtZGAb5rsLGYoutoEPowNu3962q", options);
    CompletableFuture<Object> future = new CompletableFuture<>();
    res.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future.complete(throwable);
          } else {
            future.complete(transactionResponse);
          }
        });
    System.out.println(future.get());
  }

  /**
   * Gets sui system state.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getSuiSystemState.")
  void getSuiSystemState() throws ExecutionException, InterruptedException {
    CompletableFuture<SuiSystemState> res = client.getSuiSystemState();
    System.out.println(res.get());
  }

  /**
   * Gets transactions in range.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTransactionsInRange.")
  void getValidators() throws ExecutionException, InterruptedException {
    CompletableFuture<List<ValidatorMetadata>> res = client.getValidators();
    System.out.println(res.get());
  }

  /**
   * Gets events.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getEvents.")
  void getEvents() throws ExecutionException, InterruptedException {
    TransactionEventQuery query = new TransactionEventQuery();
    query.setTransaction("9HF7ZAfdStA8d9eUuxfKBn4V2vWcvzT8tccs4CAVrFtj");
    CompletableFuture<PaginatedEvents> res = client.getEvents(query, null, 1, false);
    System.out.println(res.get());
  }

  /**
   * Gets normalized move modules by package.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getNormalizedMoveModulesByPackage.")
  void getNormalizedMoveModulesByPackage() throws ExecutionException, InterruptedException {
    CompletableFuture<Map<String, MoveNormalizedModule>> res =
        client.getNormalizedMoveModulesByPackage("0x0000000000000000000000000000000000000002");
    System.out.println(res.get());
  }

  /**
   * Gets committee info.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCommitteeInfo.")
  void getCommitteeInfo() throws ExecutionException, InterruptedException {
    CompletableFuture<CommitteeInfoResponse> res = client.getCommitteeInfo(0L);
    System.out.println(res.get());
    assertEquals(4, res.get().getCommittee_info().size());
    CompletableFuture<CommitteeInfoResponse> res1 = client.getCommitteeInfo(null);
    assertEquals(4, res1.get().getCommittee_info().size());
  }

  /**
   * Gets move function arg types.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getMoveFunctionArgTypes.")
  void getMoveFunctionArgTypes() throws ExecutionException, InterruptedException {
    CompletableFuture<List<MoveFunctionArgType>> res =
        client.getMoveFunctionArgTypes("0x0000000000000000000000000000000000000002", "bag", "add");
    System.out.println(res.get());
    //    assertEquals(ByMutableReference,
    //        ((ObjectValueKindMoveFunctionArgType) res.get().get(0)).getObject());
    //    assertEquals(PureFunctionMoveFunctionArgType.Pure, res.get().get(1));
  }

  /**
   * Gets normalized move function.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getNormalizedMoveFunction.")
  void getNormalizedMoveFunction() throws ExecutionException, InterruptedException {
    CompletableFuture<MoveNormalizedFunction> res =
        client.getNormalizedMoveFunction(
            "0x0000000000000000000000000000000000000002", "bag", "add");
    System.out.println(res.get());
  }

  /**
   * Gets normalized move module.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getNormalizedMoveModule.")
  void getNormalizedMoveModule() throws ExecutionException, InterruptedException {
    CompletableFuture<MoveNormalizedModule> res =
        client.getNormalizedMoveModule("0x0000000000000000000000000000000000000002", "bag");
    System.out.println(res.get());
    //    assertEquals(6, res.get().getFile_format_version());
    //    assertEquals("0x2", res.get().getAddress());
    //    assertEquals(MoveVisibility.Public,
    //        res.get().getExposed_functions().get("borrow").getVisibility());
    //    assertEquals("Store",
    // res.get().getStructs().get("Bag").getAbilities().getAbilities().get(0));
  }

  /**
   * Gets normalized move struct.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getNormalizedMoveStruct.")
  void getNormalizedMoveStruct() throws ExecutionException, InterruptedException {
    CompletableFuture<MoveNormalizedStruct> res =
        client.getNormalizedMoveStruct("0x0000000000000000000000000000000000000002", "bag", "Bag");
    System.out.println(res.get());
    //    assertEquals("Store", res.get().getAbilities().getAbilities().get(0));
    //    assertEquals("size", res.get().getFields().get(1).getName());
    //    assertEquals(TypeMoveNormalizedType.U64, res.get().getFields().get(1).getType_());
  }

  /**
   * Try get past object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test tryGetPastObject.")
  void tryGetPastObject() throws ExecutionException, InterruptedException {
    CompletableFuture<ObjectResponse> res =
        client.tryGetPastObject("0x163e344adfb74793481c77661f463811b990fe2a", 0);
    System.out.println(res.get());

    CompletableFuture<ObjectResponse> res1 =
        client.tryGetPastObject("0x163e344adfb74793481c77661f463811b990fe2a", 1);
    System.out.println(res1.get());
  }

  /**
   * Gets transactions.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test queryTransactionBlocks.")
  void queryTransactionBlocks() throws ExecutionException, InterruptedException {
    TransactionBlockResponseQuery query = new TransactionBlockResponseQuery();
    CompletableFuture<PaginatedTransactionResponse> res =
        client.queryTransactionBlocks(query, null, 10, false);

    CompletableFuture<Object> future = new CompletableFuture<>();
    res.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future.complete(throwable);
          } else {
            future.complete(transactionResponse);
          }
        });
    System.out.println(future.get());
  }

  /**
   * Gets coin metadata.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCoinMetadata.")
  void getCoinMetadata() throws ExecutionException, InterruptedException {
    CompletableFuture<CoinMetadata> res = client.getCoinMetadata("0x2::sui::SUI");
    System.out.println(res.get());
  }

  //  /**
  //   * Gets all balances.
  //   *
  //   * @throws ExecutionException   the execution exception
  //   * @throws InterruptedException the interrupted exception
  //   */
  //  @Test
  //  @DisplayName("Test getAllBalances.")
  //  void getAllBalances() throws ExecutionException, InterruptedException {
  //    Optional<String> address = getFirstAddress();
  //    CompletableFuture<List<Balance>> res = client.getAllBalances(address.get());
  //    System.out.println(res.get());
  //    List<Balance> balances = res.get();
  //    assertTrue(balances.size() != 0);
  //  }

  //  /**
  //   * Gets all coins.
  //   *
  //   * @throws ExecutionException   the execution exception
  //   * @throws InterruptedException the interrupted exception
  //   */
  //  @Test
  //  @DisplayName("Test getAllCoins.")
  //  void getAllCoins() throws ExecutionException, InterruptedException {
  //    Optional<String> address = getFirstAddress();
  //    CompletableFuture<PaginatedCoins> res = client.getAllCoins(address.get(), null, 10);
  //    System.out.println(res.get());
  //    assertNotNull(res.get());
  //  }

  //  /**
  //   * Gets coins.
  //   *
  //   * @throws ExecutionException   the execution exception
  //   * @throws InterruptedException the interrupted exception
  //   */
  //  @Test
  //  @DisplayName("Test getCoins.")
  //  void getCoins() throws ExecutionException, InterruptedException {
  //    Optional<String> address = getFirstAddress();
  //    CompletableFuture<PaginatedCoins> res =
  //        client.getCoins(address.get(), QueryClient.DEFAULT_COIN_TYPE, null, 10);
  //    System.out.println(res.get());
  //  }

  //  /**
  //   * Gets balance.
  //   *
  //   * @throws ExecutionException   the execution exception
  //   * @throws InterruptedException the interrupted exception
  //   */
  //  @Test
  //  @DisplayName("Test getBalance.")
  //  void getBalance() throws ExecutionException, InterruptedException {
  //    Optional<String> address = getFirstAddress();
  //    CompletableFuture<Balance> res =
  //        client.getBalance(address.get(), QueryClient.DEFAULT_COIN_TYPE);
  //    System.out.println(res.get());
  //  }

  /**
   * Gets checkpoint contents.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCheckpointContents.")
  void getCheckpointContents() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointContents> res =
        client.getCheckpointContents(summary.get().getSequence_number());
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  /**
   * Gets checkpoint contents by digest.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCheckpointContentsByDigest.")
  void getCheckpointContentsByDigest() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointContents> res =
        client.getCheckpointContentsByDigest(summary.get().getContent_digest());
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  /**
   * Gets checkpoint summary.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCheckpointSummary.")
  void getCheckpointSummary() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> res = client.getCheckpointSummary(3L);
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  /**
   * Gets checkpoint summary by digest.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCheckpointSummaryByDigest.")
  void getCheckpointSummaryByDigest() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointSummary> res =
        client.getCheckpointSummaryByDigest(summary.get().getPrevious_digest());
    System.out.println(res.get());
    assertNotNull(res.get());
  }
}
