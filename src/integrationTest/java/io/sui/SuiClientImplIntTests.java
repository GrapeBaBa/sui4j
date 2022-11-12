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
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.CoinBalanceChangeEvent;
import io.sui.models.events.CoinBalanceChangeEvent.BalanceChangeType;
import io.sui.models.events.EventKind;
import io.sui.models.events.MoveEvent;
import io.sui.models.objects.GetObjectResponse;
import io.sui.models.objects.GetObjectResponse.ObjectIdResponseDetails;
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
import java.util.List;
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
class SuiClientImplIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static SuiClient client;

  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    client = new SuiClientImpl(jsonRpcClientProvider);
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
}
