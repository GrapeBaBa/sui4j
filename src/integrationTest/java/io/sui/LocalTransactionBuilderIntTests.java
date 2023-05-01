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


import io.sui.json.GsonJsonHandler;
import io.sui.json.JsonHandler;
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

  //  private static LocalTransactionBuilder transactionBuilder;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    //    transactionBuilder = new LocalTransactionBuilder(new
    // QueryClientImpl(jsonRpcClientProvider));
  }
}
