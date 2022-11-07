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

import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Response.Error.ErrorCode;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.GetObjectResponse;
import io.sui.models.GetObjectResponse.ObjectIdResponseDetails;
import io.sui.models.ObjectStatus;
import io.sui.models.SuiApiException;
import io.sui.models.SuiData;
import io.sui.models.SuiObject;
import io.sui.models.SuiObjectOwner;
import io.sui.models.SuiObjectRef;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Sui client impl tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
class SuiClientImplTests {

  private static final String BASE_URL = "http://localhost:9000";

  /**
   * Gets object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test get_object returns existing move object.")
  void getObjectExistingMoveObject() throws ExecutionException, InterruptedException {
    JsonHandler jsonHandler = new GsonJsonHandler();
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    SuiClient client = new SuiClientImpl(jsonRpcClientProvider);
    CompletableFuture<GetObjectResponse> res =
        client.getObject("0xa204b49f2a65eb3d418ccae864b331c524c2fa75");
    GetObjectResponse response = res.get();
    System.out.println(response);
    assertEquals(ObjectStatus.Exists, response.getStatus());
    SuiObject suiObject = (SuiObject) response.getDetails();
    SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
    assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
    SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner) suiObject.getOwner();
    assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947", addressOwner.getAddressOwner());
    assertEquals(BigInteger.valueOf(100000000000000L), moveObject.getFields().get("balance"));
    SuiObjectRef suiObjectRef = suiObject.getReference();
    assertEquals("CxYsqphRB24TVGDgV/973d5+cmgZZoXpGloXw1+rPIU=", suiObjectRef.getDigest());
  }

  /**
   * Gets object no exist.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test get_object returns non existing object.")
  void getObjectNoExist() throws ExecutionException, InterruptedException {
    JsonHandler jsonHandler = new GsonJsonHandler();
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    SuiClient client = new SuiClientImpl(jsonRpcClientProvider);
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
  @DisplayName("Test get_object with invalid params.")
  void getObjectInvalidParams() throws ExecutionException, InterruptedException {
    JsonHandler jsonHandler = new GsonJsonHandler();
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    SuiClient client = new SuiClientImpl(jsonRpcClientProvider);
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
}
