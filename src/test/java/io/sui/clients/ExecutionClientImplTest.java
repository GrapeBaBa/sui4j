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

import static io.sui.models.transactions.ExecutionStatus.ExecutionStatusType.success;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.io.Resources;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.transactions.TransactionEffects;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

/** The type Execution client impl test. */
class ExecutionClientImplTest {

  private static final String BASE_URL = "http://localhost:9001";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static ExecutionClient executionClient;

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
            if ("/sui_dryRunTransaction".equals(request.getPath())) {
              return getMockResponse("mockdata/dryRunTransaction.json");
            }

            if ("/sui_executeTransaction".equals(request.getPath())) {
              return getMockResponse("mockdata/executeTransaction.json");
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
    executionClient = new ExecutionClientImpl(jsonRpcClientProvider);
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
    System.out.println(res.get());

    assertEquals(success, res.get().getStatus().getStatus());
    assertEquals(793L, res.get().getGasUsed().getComputationCost().longValue());
    assertEquals(
        "uZnJ/TEjGRaJ29KC5QY+mz7KN/EV4cdeJVLc64KJtZ4=",
        res.get().getMutated().get(0).getReference().getDigest());
  }

  /**
   * Execute transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @SuppressWarnings("checkstyle:CommentsIndentation")
  @Test
  @DisplayName("Test executeTransaction.")
  void executeTransaction() throws ExecutionException, InterruptedException {
    //    String txBytes =
    //        "VHJhbnNhY3Rpb25EYXRhOjoAAx53UvIiKHU+V0X1rIrU7xu8UChFARAn"
    //            + "AAAAAAAA6nlGTYZ4a3p6Y+PxP3mPKfXmWUcbwcTixQTCq+dHPI+U+pEVQZ3E1gEAAAA"
    //            + "AAAAAIALW7A8X9Mops0981ab21+cLcuED5JsfLqA8wlpGlLUpAQAAAAAAAABkAAAAAAAAAA==";
    //    String signature =
    //        "DBbyIDF9YDYuGyDmd5vKoGQAFqw9evRMraG5XZEz7mPUPKEppEHRvsGQ"
    //            + "wG+1u0W4ZEoIr7VaXtc7HAQeJm4oCg==";
    //    String publicKey = "n86fb1wNMnXIcwglXZaPzssw8c3EP4gg6xrj3xJByYg=";
    //    CompletableFuture<ExecuteTransactionResponse> res =
    //        executionClient.executeTransaction(
    //            txBytes,
    //            SignatureScheme.ED25519,
    //            signature,
    //            publicKey,
    //            ExecuteTransactionRequestType.WaitForLocalExecution);
    //    System.out.println(res.get());
    //    assertTrue(((EffectsCertResponse)
    // res.get()).getEffectsCert().isConfirmed_local_execution());
    //    assertEquals(
    //        "AAwW8iAxfWA2Lhsg5nebyqBkABasPXr0TK2huV2RM+5j1DyhKaRB0b7BkMBvtbtF"
    //            + "uGRKCK+1Wl7XOxwEHiZuKAqfzp9vXA0ydchzCCVdlo/OyzDxzcQ/iCDrGuPfEkHJiA==",
    //        ((EffectsCertResponse) res.get()).getEffectsCert().getCertificate().getTxSignature());
    //    assertEquals(
    //        "BsDTb4GLr8J8LqbH4uDDRhdQ4qQ6EtW13HvV1n7NioTx",
    //        ((EffectsCertResponse) res.get())
    //            .getEffectsCert()
    //            .getEffects()
    //            .getEffects()
    //            .getDependencies()
    //            .get(0));
    //    assertEquals(
    //        "wNPqCJBM1Zr0+OVQvGkqORPIgvaLXF/Ip+uyu1KRBLM=",
    //        ((EffectsCertResponse) res.get())
    //            .getEffectsCert()
    //            .getEffects()
    //            .getTransactionEffectsDigest());
  }
}
