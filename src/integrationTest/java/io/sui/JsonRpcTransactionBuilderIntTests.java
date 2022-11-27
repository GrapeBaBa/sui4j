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
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.transactions.TransactionBytes;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Json rpc transaction builder int tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
class JsonRpcTransactionBuilderIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static TransactionBuilder transactionBuilder;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    transactionBuilder = new JsonRpcTransactionBuilder(jsonRpcClientProvider);
  }

  /**
   * Gets split coin.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test splitCoin.")
  void splitCoin() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.splitCoin(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            Lists.newArrayList(90000000000000L, 10000000000000L),
            null,
            1000L);
    System.out.println(res.get());
  }

  /**
   * Split coin equal.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test splitCoinEqual.")
  void splitCoinEqual() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.splitCoinEqual(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            5L,
            null,
            1000L);
    System.out.println(res.get());
  }

  /**
   * Merge coins.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test mergeCoins.")
  void mergeCoins() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.mergeCoins(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x24e6a45a16746213cc3aa152e2a6227857a580fa",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            "0x24e6a45a16746213cc3aa152e2a6227857a580fa",
            1000L);
    System.out.println(res.get());
  }
}
