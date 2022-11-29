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


import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.transactions.TransactionEffects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Execution client impl int tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ExecutionClientImplIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static ExecutionClient executionClient;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    executionClient = new ExecutionClientImpl(jsonRpcClientProvider);
  }

  /**
   * Dry run transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test dryRunTransaction.")
  void dryRunTransaction() throws ExecutionException, InterruptedException {
    String txBytes =
        "VHJhbnNhY3Rpb25EYXRhOjoAAgAAAAAAAAAAAAAAAAAAAAAAAAACAQAAAAAAAAAgrUd"
            + "+yyNncmiOE+31Fz+w5YwHA+1hpiSfUqRnZJKhLN4DcGF5CXNwbGl0X3ZlYwEH"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAIDc3VpA1NVSQACAQCEu9ogVzV4+ELQHYd3o"
            + "ho7RYywAgAAAAAAAAAAIHYNV0u7CgTzXGBa841Vlwo4YZXBsGF1JySBtaFw6Q"
            + "gXABECAKAHwtpRAAAAoHJOGAkAAOp5Rk2GeGt6emPj8T95jyn15llHJsq1VUH"
            + "ksPNiIR+TlCALfkH9ResAAAAAAAAAACBDR5ojYLigryyCdMOb6pv4SSd8H86j"
            + "43JarTaVf5eGfQEAAAAAAAAA6AMAAAAAAAA=";

    CompletableFuture<TransactionEffects> res = executionClient.dryRunTransaction(txBytes);
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
}
