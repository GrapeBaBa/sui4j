package io.sui;

import io.sui.clients.LocalTransactionBuilder;
import io.sui.clients.QueryClientImpl;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.objects.SuiObjectRef;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA. Author: kaichen Date: 2023/1/25 Time: 11:01
 */
class LocalTransactionBuilderIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static LocalTransactionBuilder transactionBuilder;

  /**
   * Before all.
   */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    transactionBuilder = new LocalTransactionBuilder(new QueryClientImpl(jsonRpcClientProvider));
  }

  @Test
  @DisplayName("Test select gas.")
  void selectGas() throws ExecutionException, InterruptedException {

//    CompletableFuture<SuiObjectRef> future = transactionBuilder.selectGas(
//        "0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
//        "0x0894790d4cebb8f99d10fd4f79be0d28e3cf6374", 500L, new ArrayList<>());
//    System.out.println(future.get());

    CompletableFuture<SuiObjectRef> future1 = transactionBuilder.selectGas(
        "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
        null, 500L, new ArrayList<>());
    System.out.println(future1.get());
  }
}
