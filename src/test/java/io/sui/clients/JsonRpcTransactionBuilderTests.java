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

package io.sui.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.objects.InputObjectKind.ImmOrOwnedMoveObjectKind;
import io.sui.models.objects.InputObjectKind.MovePackageKind;
import io.sui.models.transactions.TransactionBytes;
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

/**
 * The type Json rpc transaction builder test.
 *
 * @author grapebaba
 * @since 2022.11
 */
class JsonRpcTransactionBuilderTests {

  private static final String BASE_URL = "http://localhost:9001";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static TransactionBuilder transactionBuilder;

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
            if ("/sui_splitCoin".equals(request.getPath())) {
              return getMockResponse("mockdata/splitCoin.json");
            }

            if ("/sui_splitCoinEqual".equals(request.getPath())) {
              return getMockResponse("mockdata/splitCoinEqual.json");
            }

            if ("/sui_mergeCoins".equals(request.getPath())) {
              return getMockResponse("mockdata/mergeCoins.json");
            }

            if ("/sui_pay".equals(request.getPath())) {
              return getMockResponse("mockdata/pay.json");
            }

            if ("/sui_paySui".equals(request.getPath())) {
              return getMockResponse("mockdata/paySui.json");
            }

            if ("/sui_payAllSui".equals(request.getPath())) {
              return getMockResponse("mockdata/payAllSui.json");
            }

            if ("/sui_transferSui".equals(request.getPath())) {
              return getMockResponse("mockdata/transferSui.json");
            }

            if ("/sui_transferObject".equals(request.getPath())) {
              return getMockResponse("mockdata/transferObject.json");
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
    transactionBuilder = new JsonRpcTransactionBuilder(jsonRpcClientProvider);
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
   * Gets split coin.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getSplitCoin.")
  void getSplitCoin() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.splitCoin(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            Lists.newArrayList(90000000000000L, 10000000000000L),
            null,
            1000L);
    System.out.println(res.get());
    assertEquals(
        "VHJhbnNhY3Rpb25EYXRhOjoAAgAAAAAAAAAAAAAAAAAAAAAAAAACAQAAAAAAAAAgrUd+yyNncmiOE+31F"
            + "z+w5YwHA+1hpiSfUqRnZJKhLN4DcGF5CXNwbGl0X3ZlYwEHAAAAAAAAAAAAAAAAAAAAAAAAAAIDc3VpA1NVS"
            + "QACAQB/vLgC0R2DakA050kbtUTd70YAlAAAAAAAAAAAIJfFaD7l53LPrBONn7W6V/cZK3jDOvYFQW2OPD3Rv"
            + "4meABECAKAHwtpRAAAAoHJOGAkAAOp5Rk2GeGt6emPj8T95jyn15llHJOakWhZ0YhPMOqFS4qYieFelgPoAA"
            + "AAAAAAAACBa0Zx/ukxCwIE7a4BAR4R3L2q2kICD45cvWWdBd26GhgEAAAAAAAAA6AMAAAAAAAA=",
        res.get().getTxBytes());
    assertEquals("WtGcf7pMQsCBO2uAQEeEdy9qtpCAg+OXL1lnQXduhoY=", res.get().getGas().getDigest());
    assertEquals(3, res.get().getInputObjects().size());
    assertEquals(
        "l8VoPuXncs+sE42ftbpX9xkreMM69gVBbY48PdG/iZ4=",
        ((ImmOrOwnedMoveObjectKind) res.get().getInputObjects().get(0))
            .getImmOrOwnedMoveObject()
            .getDigest());
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
    assertEquals("0x24e6a45a16746213cc3aa152e2a6227857a580fa", res.get().getGas().getObjectId());
    assertEquals(
        "0x0000000000000000000000000000000000000002",
        ((MovePackageKind) res.get().getInputObjects().get(1)).getMovePackage());
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
    assertEquals(4, res.get().getInputObjects().size());
    assertEquals(
        "WtGcf7pMQsCBO2uAQEeEdy9qtpCAg+OXL1lnQXduhoY=",
        ((ImmOrOwnedMoveObjectKind) res.get().getInputObjects().get(3))
            .getImmOrOwnedMoveObject()
            .getDigest());
  }

  /**
   * Pay.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test pay.")
  void pay() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.pay(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList("0x24e6a45a16746213cc3aa152e2a6227857a580fa"),
            Lists.newArrayList("0x49ef9b602b76a37e0f9177783755c1a190866e72"),
            Lists.newArrayList(100L),
            null,
            1L);
    System.out.println(res.get());
    assertEquals("0x2f88cdfa0d6b60d80f3b9cae0c2decfb13cb33cb", res.get().getGas().getObjectId());
    assertEquals(2, res.get().getInputObjects().size());
  }

  /**
   * Pay sui.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test paySui.")
  void paySui() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.paySui(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList("0x24e6a45a16746213cc3aa152e2a6227857a580fa"),
            Lists.newArrayList("0x49ef9b602b76a37e0f9177783755c1a190866e72"),
            Lists.newArrayList(100L),
            1L);
    System.out.println(res.get());
    assertEquals("0x24e6a45a16746213cc3aa152e2a6227857a580fa", res.get().getGas().getObjectId());
    assertEquals(1, res.get().getInputObjects().size());
  }

  /**
   * Pay all sui.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test payAllSui.")
  void payAllSui() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.payAllSui(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList("0x24e6a45a16746213cc3aa152e2a6227857a580fa"),
            "0x49ef9b602b76a37e0f9177783755c1a190866e72",
            1L);
    System.out.println(res.get());
    assertEquals("0x24e6a45a16746213cc3aa152e2a6227857a580fa", res.get().getGas().getObjectId());
    assertEquals(1, res.get().getInputObjects().size());
  }

  /**
   * Transfer sui.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test transferSui.")
  void transferSui() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.transferSui(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            1L,
            "0x51de405091c9f971fc6085d384f9ba764f268fca",
            20000L);
    System.out.println(res.get());
    assertEquals(
        "Q0eaI2C4oK8sgnTDm+qb+EknfB/Oo+NyWq02lX+Xhn0=",
        ((ImmOrOwnedMoveObjectKind) res.get().getInputObjects().get(0))
            .getImmOrOwnedMoveObject()
            .getDigest());
  }

  /**
   * Transfer object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test transferObject.")
  void transferObject() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.transferObject(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            1L,
            "0x51de405091c9f971fc6085d384f9ba764f268fca");
    System.out.println(res.get());
    assertEquals("Q0eaI2C4oK8sgnTDm+qb+EknfB/Oo+NyWq02lX+Xhn0=", res.get().getGas().getDigest());
    assertEquals(
        "Q0eaI2C4oK8sgnTDm+qb+EknfB/Oo+NyWq02lX+Xhn0=",
        ((ImmOrOwnedMoveObjectKind) res.get().getInputObjects().get(1))
            .getImmOrOwnedMoveObject()
            .getDigest());
  }
}
