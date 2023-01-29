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


import io.sui.clients.LocalTransactionBuilder;
import io.sui.clients.QueryClientImpl;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import org.junit.jupiter.api.BeforeAll;

/**
 * The type Local transaction builder int tests.
 *
 * @author grapebaba
 * @since 2023.1
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
class LocalTransactionBuilderIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static LocalTransactionBuilder transactionBuilder;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    transactionBuilder = new LocalTransactionBuilder(new QueryClientImpl(jsonRpcClientProvider));
  }

  //  @Test
  //  @DisplayName("Test select gas.")
  //  void selectGas() throws ExecutionException, InterruptedException {
  //
  ////    CompletableFuture<SuiObjectRef> future = transactionBuilder.selectGas(
  ////        "0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
  ////        "0x0894790d4cebb8f99d10fd4f79be0d28e3cf6374", 500L, new ArrayList<>());
  ////    System.out.println(future.get());
  //
  //    CompletableFuture<SuiObjectRef> future1 = transactionBuilder.selectGas(
  //        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
  //        null, 500L, new ArrayList<>());
  //    System.out.println(future1.get());
  //  }
}
