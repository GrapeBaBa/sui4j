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

import static io.sui.models.objects.MoveFunctionArgType.ObjectValueKindMoveFunctionArgType.ObjectValueKind.ByMutableReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.io.Resources;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventKind.CoinBalanceChangeEventKind;
import io.sui.models.events.EventQuery.TransactionEventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.ObjectValueKindMoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.PureFunctionMoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType;
import io.sui.models.objects.MoveNormalizedType.MutableReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.TypeMoveNormalizedType;
import io.sui.models.objects.MoveVisibility;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectResponse.ObjectIdHigherVersionResponseDetails;
import io.sui.models.objects.ObjectResponse.ObjectIdResponseDetails;
import io.sui.models.objects.ObjectStatus;
import io.sui.models.objects.SuiData;
import io.sui.models.objects.SuiData.MoveObject;
import io.sui.models.objects.SuiObject;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.objects.SuiObjectOwner;
import io.sui.models.objects.SuiObjectOwner.AddressOwner;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.transactions.PaginatedTransactionDigests;
import io.sui.models.transactions.TransactionQuery;
import io.sui.models.transactions.TransactionQuery.AllQuery;
import io.sui.models.transactions.TransactionResponse;
import io.sui.models.transactions.TransactionResponseOptions;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Sui client impl tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
class QueryClientImplTests {

  private static final String BASE_URL = "http://localhost:9001";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static QueryClient client;

  private static MockWebServer mockWebServer;

  /**
   * Create and start mock server mock web server.
   *
   * @param port the port
   * @return the mock web server
   */
  static MockWebServer createAndStartMockServer(int port) {
    MockWebServer server = new MockWebServer();
    final Dispatcher dispatcher =
        new Dispatcher() {

          @Override
          public MockResponse dispatch(RecordedRequest request) {
            String body = request.getBody().readUtf8();
            if ("/sui_getObject".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ("0x342950ba2451c2f27ed128e591c2b4551e5177c2"
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getObjectExistingMoveObject.json");
              }

              if ("0xa204b49f2a65eb3d418ccae864b331c524c2fa76"
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getObjectNoExist.json");
              }

              if ("".equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getObjectInvalidParams.json");
              }
            }

            if ("/sui_getObjectsOwnedByAddress".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ("0xea79464d86786b7a7a63e3f13f798f29f5e65947"
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getObjectsOwnedByAddressIsNotEmpty.json");
              }
            }

            if ("/sui_getObjectsOwnedByObject".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ("0xde2952390ab3d0cfbb0a0602532480ed5ec99cf3"
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getObjectsOwnedByObjectIsEmpty.json");
              }
            }

            if ("/sui_getRawObject".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ("0x342950ba2451c2f27ed128e591c2b4551e5177c2"
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getRawObjectExistingMoveObject.json");
              }
            }

            if ("/sui_getTotalTransactionNumber".equals(request.getPath())) {
              return getMockResponse("mockdata/getTotalTransactionNumber.json");
            }

            if ("/sui_getTransaction".equals(request.getPath())) {
              return getMockResponse("mockdata/getTransaction.json");
            }

            if ("/sui_getTransactionsInRange".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ((Long) jsonRpc20Request.getParams().get(1) == 100L) {
                return getMockResponse("mockdata/getTransactionsInRange.json");
              }
            }

            if ("/sui_getEvents".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              System.out.println(jsonRpc20Request.getParams().get(0));
              System.out.println(jsonRpc20Request.getParams().get(1));
              System.out.println(jsonRpc20Request.getParams().get(2));
              return getMockResponse("mockdata/getEvents.json");
            }

            if ("/sui_getNormalizedMoveModulesByPackage".equals(request.getPath())) {
              return getMockResponse("mockdata/getNormalizedMoveModulesByPackage.json");
            }

            if ("/sui_getCommitteeInfo".equals(request.getPath())) {
              return getMockResponse("mockdata/getCommitteeInfo.json");
            }

            if ("/sui_getMoveFunctionArgTypes".equals(request.getPath())) {
              return getMockResponse("mockdata/getMoveFunctionArgTypes.json");
            }

            if ("/sui_getNormalizedMoveFunction".equals(request.getPath())) {
              return getMockResponse("mockdata/getNormalizedMoveFunction.json");
            }

            if ("/sui_getNormalizedMoveModule".equals(request.getPath())) {
              return getMockResponse("mockdata/getNormalizedMoveModule.json");
            }

            if ("/sui_getNormalizedMoveStruct".equals(request.getPath())) {
              return getMockResponse("mockdata/getNormalizedMoveStruct.json");
            }

            if ("/sui_tryGetPastObject".equals(request.getPath())) {
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if (jsonRpc20Request.getParams().get(1).equals(1L)) {
                return getMockResponse("mockdata/tryGetPastObjectHigher.json");
              } else {
                return getMockResponse("mockdata/tryGetPastObject.json");
              }
            }

            if ("/sui_getTransactions".equals(request.getPath())) {
              return getMockResponse("mockdata/getTransactions.json");
            }

            if ("/sui_getCoinMetadata".equals(request.getPath())) {
              return getMockResponse("mockdata/getCoinMetadata.json");
            }
            return new MockResponse().setResponseCode(404);
          }
        };
    server.setDispatcher(dispatcher);

    try {
      server.start(port);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return server;
  }

  @NotNull private static MockResponse getMockResponse(String mockdata) {
    URL url = Resources.getResource(mockdata);
    String mockData = "";
    try {
      mockData = Resources.asCharSource(url, StandardCharsets.UTF_8).read();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new MockResponse().setResponseCode(200).setBody(mockData);
  }

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    client = new QueryClientImpl(jsonRpcClientProvider);
    mockWebServer = createAndStartMockServer(9001);
  }

  /**
   * After all.
   *
   * @throws IOException the io exception
   */
  @AfterAll
  static void afterAll() throws IOException {
    mockWebServer.shutdown();
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
    assertEquals(ObjectStatus.Exists, response.getStatus());
    SuiObject suiObject = (SuiObject) response.getDetails();
    SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
    assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
    SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner) suiObject.getOwner();
    assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947", addressOwner.getAddressOwner());
    assertEquals(100000000000000L, moveObject.getFields().get("balance"));
    SuiObjectRef suiObjectRef = suiObject.getReference();
    assertEquals("bWkh6f80oGFCtsPtS3//66LvAvqGJTOVJtKmUJAd5l0=", suiObjectRef.getDigest());
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
    assertEquals(ObjectStatus.NotExists, response.getStatus());
    ObjectIdResponseDetails objectIdResponseDetails =
        (ObjectIdResponseDetails) response.getDetails();
    assertEquals(
        "0xa204b49f2a65eb3d418ccae864b331c524c2fa76", objectIdResponseDetails.getObjectId());
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
    assertTrue(response.size() > 0);
    assertEquals(
        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
        ((AddressOwner) response.get(0).getOwner()).getAddressOwner());
    assertEquals(
        "GN9sW4hBVNFIc83VIfyn/J1n4a9tU9sQVq3+UkfgEKU=", response.get(0).getPreviousTransaction());
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
    assertEquals(0, response.size());
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
    assertEquals(ObjectStatus.Exists, response.getStatus());
    SuiObject suiObject = (SuiObject) response.getDetails();
    SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
    assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
    SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner) suiObject.getOwner();
    assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947", addressOwner.getAddressOwner());
    assertEquals("NClQuiRRwvJ+0SjlkcK0VR5Rd8LQN3oQ81oAAA==", moveObject.getBcs_bytes());
    SuiObjectRef suiObjectRef = suiObject.getReference();
    assertEquals("QZMMmu37jER7FFU3+HhbdwIyZyOwwThNAa07vSsPBGw=", suiObjectRef.getDigest());
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
    assertEquals(2L, res.get());
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
    TransactionResponseOptions options = new TransactionResponseOptions();
    options.setShowEffects(true);
    options.setShowEvents(true);
    options.setShowObjectChanges(true);
    options.setShowInput(true);
    CompletableFuture<TransactionResponse> res =
        client.getTransaction("2zcrJHVMnqjQ47iauQsSqdDzpJVKzTrrohu4mYcGr2JG", options);
    TransactionResponse transactionResponse = res.get();
    System.out.println(transactionResponse);
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
    assertEquals(2, res.get().size());
    assertEquals("GN9sW4hBVNFIc83VIfyn/J1n4a9tU9sQVq3+UkfgEKU=", res.get().get(1));
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
    query.setTransaction("ov1tDrhdOqRW2uFweTbSSTaQbBbnjHWmrsh675lwb0Q=");
    CompletableFuture<PaginatedEvents> res = client.getEvents(query, null, 1, false);
    System.out.println(res.get());
    assertEquals(1, res.get().getData().size());
    assertEquals(
        "ov1tDrhdOqRW2uFweTbSSTaQbBbnjHWmrsh675lwb0Q=", res.get().getNextCursor().getTxDigest());
    assertEquals(1, res.get().getNextCursor().getEventSeq());
    assertEquals(
        BigInteger.valueOf(-1062L),
        ((CoinBalanceChangeEventKind) res.get().getData().get(0).getEvent())
            .getCoinBalanceChange()
            .getAmount());
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
    assertEquals(6, res.get().get("bag").getFile_format_version());
    assertEquals(
        "Store", res.get().get("bag").getStructs().get("Bag").getAbilities().getAbilities().get(0));

    assertEquals("0x2", res.get().get("validator_set").getFriends().get(0).getAddress());
    assertEquals(
        MoveVisibility.Friend,
        res.get().get("validator_set").getExposed_functions().get("advance_epoch").getVisibility());
    assertFalse(
        res.get().get("validator_set").getExposed_functions().get("advance_epoch").isIs_entry());
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
    CompletableFuture<CommitteeInfoResponse> res = client.getCommitteeInfo(1L);
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
    assertEquals(
        ByMutableReference, ((ObjectValueKindMoveFunctionArgType) res.get().get(0)).getObject());
    assertEquals(PureFunctionMoveFunctionArgType.Pure, res.get().get(1));
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
    assertEquals(MoveVisibility.Public, res.get().getVisibility());
    assertFalse(res.get().isIs_entry());
    assertEquals("Copy", res.get().getType_parameters().get(0).getAbilities().get(0));
    assertEquals(
        "0x2",
        ((MoveNormalizedStructType)
                ((MutableReferenceMoveNormalizedType) res.get().getParameters().get(0))
                    .getMutableReference())
            .getStruct()
            .getAddress());
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
    assertEquals(6, res.get().getFile_format_version());
    assertEquals("0x2", res.get().getAddress());
    assertEquals(
        MoveVisibility.Public, res.get().getExposed_functions().get("borrow").getVisibility());
    assertEquals("Store", res.get().getStructs().get("Bag").getAbilities().getAbilities().get(0));
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
    assertEquals("Store", res.get().getAbilities().getAbilities().get(0));
    assertEquals("size", res.get().getFields().get(1).getName());
    assertEquals(TypeMoveNormalizedType.U64, res.get().getFields().get(1).getType_());
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
    assertEquals(ObjectStatus.VersionFound, res.get().getStatus());
    assertTrue(
        ((MoveObject) ((SuiObject) res.get().getDetails()).getData()).isHas_public_transfer());

    CompletableFuture<ObjectResponse> res1 =
        client.tryGetPastObject("0x163e344adfb74793481c77661f463811b990fe2a", 1);
    System.out.println(res1.get());
    assertEquals(ObjectStatus.VersionTooHigh, res1.get().getStatus());
    assertEquals(
        0L, ((ObjectIdHigherVersionResponseDetails) res1.get().getDetails()).getLatest_version());
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
    assertEquals("9Kcsc7dJ72oDpyWcwR6ZXqkKXQke4mTGG1UmN2LrVdwj", res.get().getData().get(0));
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
    assertEquals(9, res.get().getDecimals());
    assertEquals("SUI", res.get().getSymbol());
  }
}
