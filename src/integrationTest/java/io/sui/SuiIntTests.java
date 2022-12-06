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

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.sui.models.SuiApiException;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.ExecuteTransactionResponse;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** The type Sui int tests. */
public class SuiIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final String TEST_KEY_STORE_PATH = "src/test/resources/config/sui.keystore";

  private static Sui sui;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    sui = new Sui(BASE_URL, Paths.get(TEST_KEY_STORE_PATH).toAbsolutePath().toString());
  }

  /**
   * Gets objects owned by address.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getObjectsOwnedByAddress.")
  void getObjectsOwnedByAddress() throws ExecutionException, InterruptedException {
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> response = res.get();
    System.out.println(response);
  }

  /**
   * Transfer sui.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  void transferSui() throws ExecutionException, InterruptedException {
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(0).getObjectId();
    List<String> addresses = new ArrayList<>(sui.getKeyStore().addresses());

    // ED25519 KEY
    System.out.println(addresses.get(0));
    CompletableFuture<ExecuteTransactionResponse> res2 =
        sui.transferSui(
            addresses.get(0),
            coinObjectId,
            100L,
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            2000L,
            ExecuteTransactionRequestType.WaitForLocalExecution);
    CompletableFuture<Object> future = new CompletableFuture<>();
    res2.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future.complete(throwable);
          } else {
            future.complete(transactionResponse);
          }
        });

    assertTrue(
        ((SuiApiException) ((CompletionException) future.get()).getCause())
            .getError()
            .getMessage()
            .contains(addresses.get(0)));

    // SECP256K1 KEY
    System.out.println(addresses.get(1));
    CompletableFuture<ExecuteTransactionResponse> res3 =
        sui.transferSui(
            addresses.get(1),
            coinObjectId,
            100L,
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            2000L,
            ExecuteTransactionRequestType.WaitForLocalExecution);
    CompletableFuture<Object> future1 = new CompletableFuture<>();
    res3.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future1.complete(throwable);
          } else {
            future1.complete(transactionResponse);
          }
        });

    assertTrue(
        ((SuiApiException) ((CompletionException) future1.get()).getCause())
            .getError()
            .getMessage()
            .contains(addresses.get(1)));
  }
}
