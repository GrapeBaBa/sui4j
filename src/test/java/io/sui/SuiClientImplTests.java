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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.io.Resources;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.CommitteeInfoResponse;
import io.sui.models.SuiApiException;
import io.sui.models.events.CoinBalanceChangeEvent;
import io.sui.models.events.CoinBalanceChangeEvent.BalanceChangeType;
import io.sui.models.events.EventKind;
import io.sui.models.events.EventKind.CoinBalanceChangeEventKind;
import io.sui.models.events.EventQuery.TransactionEventQuery;
import io.sui.models.events.MoveEvent;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.GetObjectResponse;
import io.sui.models.objects.GetObjectResponse.ObjectIdResponseDetails;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveVisibility;
import io.sui.models.objects.ObjectStatus;
import io.sui.models.objects.SuiData;
import io.sui.models.objects.SuiObject;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.objects.SuiObjectOwner;
import io.sui.models.objects.SuiObjectOwner.AddressOwner;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.transactions.ExecutionStatus.ExecutionStatusType;
import io.sui.models.transactions.MoveCall;
import io.sui.models.transactions.TransactionKind;
import io.sui.models.transactions.TransactionResponse;
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
class SuiClientImplTests {

  private static final String BASE_URL = "http://localhost:9001";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static SuiClient client;

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
              JsonRpc20Request jsonRpc20Request =
                  ((GsonJsonHandler) jsonHandler).getGson().fromJson(body, JsonRpc20Request.class);
              if ("3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk="
                  .equals(jsonRpc20Request.getParams().get(0))) {
                return getMockResponse("mockdata/getTransaction.json");
              }
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
    client = new SuiClientImpl(jsonRpcClientProvider);
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
    CompletableFuture<GetObjectResponse> res =
        client.getObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
    GetObjectResponse response = res.get();
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
    CompletableFuture<GetObjectResponse> res =
        client.getObject("0xa204b49f2a65eb3d418ccae864b331c524c2fa76");

    GetObjectResponse response = res.get();
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
    CompletableFuture<GetObjectResponse> res = client.getObject("");

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
    CompletableFuture<GetObjectResponse> res =
        client.getRawObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
    GetObjectResponse response = res.get();
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
    CompletableFuture<TransactionResponse> res =
        client.getTransaction("3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=");
    TransactionResponse transactionResponse = res.get();
    System.out.println(transactionResponse);
    assertEquals(1, transactionResponse.getCertificate().getAuthSignInfo().getSignature().size());
    assertEquals(
        "g+aeuIw6zZ08o3PP+qX1G7h+KLfGSbM7Rk3ZLHu2QjbYhZViqRchJOhKVbZw0pQI",
        transactionResponse.getCertificate().getAuthSignInfo().getSignature().get(0));
    assertEquals(
        "AIinOofScNIfh4XjXlN1fhtT4hFyQXDZsr72PBG731kC9Xl++yhAQSxZJqkvSPf3LOCQsLYxovYAXSut"
            + "4wb9uAefzp9vXA0ydchzCCVdlo/OyzDxzcQ/iCDrGuPfEkHJiA==",
        transactionResponse.getCertificate().getTxSignature());
    assertEquals(
        "3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=",
        transactionResponse.getCertificate().getTransactionDigest());

    assertEquals(1, transactionResponse.getCertificate().getData().getTransactions().size());
    MoveCall call =
        ((TransactionKind.CallTransactionKind)
                transactionResponse.getCertificate().getData().getTransactions().get(0))
            .getCall();
    assertEquals("devnet_nft", call.getModule());
    assertEquals("mint", call.getFunction());
    assertEquals(3, call.getArguments().size());
    assertEquals(
        "0x342950ba2451c2f27ed128e591c2b4551e5177c2",
        transactionResponse.getCertificate().getData().getGasPayment().getObjectId());
    assertEquals(
        ExecutionStatusType.success, transactionResponse.getEffects().getStatus().getStatus());
    assertEquals(
        "3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=",
        transactionResponse.getEffects().getTransactionDigest());
    assertEquals(
        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
        ((AddressOwner) transactionResponse.getEffects().getMutated().get(0).getOwner())
            .getAddressOwner());
    assertEquals(
        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
        ((AddressOwner) transactionResponse.getEffects().getCreated().get(0).getOwner())
            .getAddressOwner());
    assertEquals(
        "0xb5e91320d3acc77b4d9e66a218031441b2be1bb3",
        transactionResponse.getEffects().getCreated().get(0).getReference().getObjectId());
    assertEquals(3, transactionResponse.getEffects().getEvents().size());
    CoinBalanceChangeEvent coinBalanceChangeEvent =
        ((EventKind.CoinBalanceChangeEventKind) transactionResponse.getEffects().getEvents().get(0))
            .getCoinBalanceChange();
    assertEquals(BalanceChangeType.Gas, coinBalanceChangeEvent.getChangeType());
    assertEquals(
        "0x342950ba2451c2f27ed128e591c2b4551e5177c2", coinBalanceChangeEvent.getCoinObjectId());
    MoveEvent moveEvent =
        ((EventKind.MoveEventKind) transactionResponse.getEffects().getEvents().get(2))
            .getMoveEvent();
    assertEquals(
        "0xb5e91320d3acc77b4d9e66a218031441b2be1bb3", moveEvent.getFields().get("object_id"));
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
    assertEquals(0, res.get().getNextCursor().getTxSeq());
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
}
