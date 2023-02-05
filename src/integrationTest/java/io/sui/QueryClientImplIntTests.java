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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.sui.clients.QueryClient;
import io.sui.clients.QueryClientImpl;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventQuery.TransactionEventQuery;
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
import io.sui.models.objects.ValidatorMetadata;
import io.sui.models.transactions.PaginatedTransactionDigests;
import io.sui.models.transactions.TransactionQuery;
import io.sui.models.transactions.TransactionQuery.AllQuery;
import io.sui.models.transactions.TransactionQuery.FromAddressQuery;
import io.sui.models.transactions.TransactionResponse;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
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

  private static final String TEST_KEY_STORE_PATH =
      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static QueryClient client;

  private static Sui sui;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    client = new QueryClientImpl(jsonRpcClientProvider);
    sui = new Sui(BASE_URL, TEST_KEY_STORE_PATH, true);
  }

  static Optional<String> getFirstAddress() {
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    return sender;
  }

  /**
   * Gets object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObject returns existing move object.")
  void getObjectExistingMoveObject() throws ExecutionException, InterruptedException {
    CompletableFuture<ObjectResponse> res =
        client.getObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
    ObjectResponse response = res.get();
    System.out.println(response);
    //    assertEquals(ObjectStatus.Exists, response.getStatus());
    //    SuiObject suiObject = (SuiObject) response.getDetails();
    //    SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
    //    assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
    //    SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner)
    // suiObject.getOwner();
    //    assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    // addressOwner.getAddressOwner());
    //    assertEquals(BigInteger.valueOf(100000000000000L), moveObject.getFields().get("balance"));
    //    SuiObjectRef suiObjectRef = suiObject.getReference();
    //    assertEquals("bWkh6f80oGFCtsPtS3//66LvAvqGJTOVJtKmUJAd5l0=", suiObjectRef.getDigest());
  }

  /**
   * Gets object no exist.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObject returns non existing object.")
  void getObjectNoExist() throws ExecutionException, InterruptedException {
    CompletableFuture<ObjectResponse> res =
        client.getObject("0xa204b49f2a65eb3d418ccae864b331c524c2fa76");

    ObjectResponse response = res.get();
    System.out.println(response);
    //    assertEquals(ObjectStatus.NotExists, response.getStatus());
    //    ObjectIdResponseDetails objectIdResponseDetails =
    //        (ObjectIdResponseDetails) response.getDetails();
    //    assertEquals(
    //        "0xa204b49f2a65eb3d418ccae864b331c524c2fa76", objectIdResponseDetails.getObjectId());
  }

  /**
   * Gets object invalid params.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObject with invalid params.")
  void getObjectInvalidParams() throws ExecutionException, InterruptedException {
    CompletableFuture<ObjectResponse> res = client.getObject("");

    CompletableFuture<Throwable> completableFuture = new CompletableFuture<>();
    res.whenComplete(
        (getObjectResponse, throwable) -> {
          if (throwable != null) {
            completableFuture.complete(throwable);
          }
        });

    assertEquals(
        ErrorCode.INVALID_PARAMS, ((SuiApiException) completableFuture.get()).getError().getCode());
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
    CompletableFuture<List<SuiObjectInfo>> res =
        client.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> response = res.get();
    System.out.println(response);
    //    assertTrue(response.size() > 0);
    //    assertEquals(
    //        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //        ((AddressOwner) response.get(0).getOwner()).getAddressOwner());
    //    assertEquals(
    //        "GN9sW4hBVNFIc83VIfyn/J1n4a9tU9sQVq3+UkfgEKU=",
    // response.get(0).getPreviousTransaction());
  }

  /**
   * Gets objects owned by object is empty.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObjectsOwnedByObject returns empty list.")
  void getObjectsOwnedByObjectIsEmpty() throws ExecutionException, InterruptedException {
    CompletableFuture<List<SuiObjectInfo>> res =
        client.getObjectsOwnedByObject("0xde2952390ab3d0cfbb0a0602532480ed5ec99cf3");
    List<SuiObjectInfo> response = res.get();
    System.out.println(response);
    //    assertEquals(0, response.size());
  }

  /**
   * Gets raw object existing move object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getRawObject returns existing move object.")
  void getRawObjectExistingMoveObject() throws ExecutionException, InterruptedException {
    CompletableFuture<ObjectResponse> res =
        client.getRawObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
    ObjectResponse response = res.get();
    System.out.println(response);
    //    assertEquals(ObjectStatus.Exists, response.getStatus());
    //    SuiObject suiObject = (SuiObject) response.getDetails();
    //    SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
    //    assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
    //    SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner)
    // suiObject.getOwner();
    //    assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    // addressOwner.getAddressOwner());
    //    assertEquals("NClQuiRRwvJ+0SjlkcK0VR5Rd8LQN3oQ81oAAA==", moveObject.getBcs_bytes());
    //    SuiObjectRef suiObjectRef = suiObject.getReference();
    //    assertEquals("QZMMmu37jER7FFU3+HhbdwIyZyOwwThNAa07vSsPBGw=", suiObjectRef.getDigest());
  }

  /**
   * Gets total transaction number.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTotalTransactionNumber.")
  void getTotalTransactionNumber() throws ExecutionException, InterruptedException {
    CompletableFuture<Long> res = client.getTotalTransactionNumber();
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
  @DisplayName("Test getTransaction.")
  void getTransaction() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionResponse> res =
        client.getTransaction("3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=");
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
    //    assertEquals(1, transactionResponse.getCertificate().getData().getTransactions().size());
    //    MoveCall call =
    //        ((TransactionKind.CallTransactionKind)
    //                transactionResponse.getCertificate().getData().getTransactions().get(0))
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

  @Test
  @DisplayName("Test getTransactionAuthSigners.")
  void getTransactionAuthSigners() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionResponse> res =
        client.getTransaction("49rpBTf2KUkf4aroydtZGAb5rsLGYoutoEPowNu3962q");
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
   * Gets transactions in range.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTransactionsInRange.")
  void getTransactionsInRange() throws ExecutionException, InterruptedException {
    CompletableFuture<List<String>> res = client.getTransactionsInRange(0L, 100L);
    System.out.println(res.get());
    //    assertEquals(2, res.get().size());
    //    assertEquals("GN9sW4hBVNFIc83VIfyn/J1n4a9tU9sQVq3+UkfgEKU=", res.get().get(1));
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
  @DisplayName("Test getTransactions.")
  void getTransactions() throws ExecutionException, InterruptedException {
    TransactionQuery query = AllQuery.All;
    CompletableFuture<PaginatedTransactionDigests> res =
        client.getTransactions(query, null, 10, false);
    System.out.println(res.get());

    FromAddressQuery query1 = new FromAddressQuery();
    query1.setFromAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    CompletableFuture<PaginatedTransactionDigests> res1 =
        client.getTransactions(query1, null, 10, false);
    System.out.println(res1.get());
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

  @Test
  @DisplayName("Test getAllBalances.")
  void getAllBalances() throws ExecutionException, InterruptedException {
    Optional<String> address = getFirstAddress();
    CompletableFuture<List<Balance>> res = client.getAllBalances(address.get());
    System.out.println(res.get());
    List<Balance> balances = res.get();
    assertTrue(balances.size() != 0);
  }

  @Test
  @DisplayName("Test getAllCoins.")
  void getAllCoins() throws ExecutionException, InterruptedException {
    Optional<String> address = getFirstAddress();
    CompletableFuture<PaginatedCoins> res = client.getAllCoins(address.get(),null,10);
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  @Test
  @DisplayName("Test getCoins.")
  void getCoins() throws ExecutionException, InterruptedException {
    Optional<String> address = getFirstAddress();
    CompletableFuture<PaginatedCoins> res = client.getCoins(address.get(),
        QueryClient.DEFAULT_COIN_TYPE, null, 10);
    System.out.println(res.get());
  }

  @Test
  @DisplayName("Test getBalance.")
  void getBalance() throws ExecutionException, InterruptedException {
    Optional<String> address = getFirstAddress();
    CompletableFuture<Balance> res = client.getBalance(address.get(),QueryClient.DEFAULT_COIN_TYPE);
    System.out.println(res.get());
  }

  @Test
  @DisplayName("Test getCheckpointContents.")
  void getCheckpointContents() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointContents> res = client.getCheckpointContents(summary.get().getSequenceNumber());
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  @Test
  @DisplayName("Test getCheckpointContentsByDigest.")
  void getCheckpointContentsByDigest() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointContents> res =
        client.getCheckpointContentsByDigest(summary.get().getContentDigest());
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  @Test
  @DisplayName("Test getCheckpointSummary.")
  void getCheckpointSummary() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> res = client.getCheckpointSummary(3L);
    System.out.println(res.get());
    assertNotNull(res.get());
  }

  @Test
  @DisplayName("Test getCheckpointSummaryByDigest.")
  void getCheckpointSummaryByDigest() throws ExecutionException, InterruptedException {
    CompletableFuture<CheckpointSummary> summary = client.getCheckpointSummary(3L);
    CompletableFuture<CheckpointSummary> res = client.getCheckpointSummaryByDigest(summary.get().getPreviousDigest());
    System.out.println(res.get());
    assertNotNull(res.get());
  }

}
