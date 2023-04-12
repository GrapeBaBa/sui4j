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

//
// import static org.junit.jupiter.api.Assertions.assertNotNull;
//
// import com.google.common.collect.Lists;
// import io.reactivex.rxjava3.disposables.Disposable;
// import io.reactivex.rxjava3.functions.Consumer;
// import io.sui.crypto.ED25519KeyPair;
// import io.sui.crypto.SECP256K1KeyPair;
// import io.sui.models.events.EventEnvelope;
// import io.sui.models.events.EventFilter.EventTypeEventFilter;
// import io.sui.models.events.EventType;
// import io.sui.models.objects.SuiObjectInfo;
// import io.sui.models.transactions.ExecuteTransactionRequestType;
// import io.sui.models.transactions.ExecuteTransactionResponse;
// import io.sui.models.transactions.RPCTransactionRequestParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallRequestParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectRequestParams;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.CompletableFuture;


import com.google.common.collect.Lists;
import io.sui.crypto.KeyResponse;
import io.sui.crypto.SignatureScheme;
import io.sui.models.FaucetResponse;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiObjectResponse;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.PaginatedTransactionResponse;
import io.sui.models.transactions.StructTag;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionBlockResponseQuery;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Sui int tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final String BASE_FAUCET_URL = "http://localhost:9123";
  //  private static final String BASE_FAUCET_URL = "https://faucet.testnet.sui.io";

  //  private static final String TEST_KEY_STORE_PATH =
  //      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";

  private static final String TEST_KEY_STORE_PATH =
      Paths.get("src/integrationTest/sui.keystore").toAbsolutePath().toString();

  private static Sui sui;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    sui = new Sui(BASE_URL, BASE_FAUCET_URL, TEST_KEY_STORE_PATH, true);
  }

  /** New address. */
  @Test
  @DisplayName("Test newAddress.")
  void newAddress() {
    KeyResponse res1 = sui.newAddress(SignatureScheme.ED25519);
    System.out.printf("mnemonic+address:%s%n", res1);
    System.out.println();

    KeyResponse res2 = sui.newAddress(SignatureScheme.ED25519);
    System.out.printf("mnemonic+address:%s%n", res2);
    System.out.println();

    KeyResponse res3 = sui.newAddress(SignatureScheme.ED25519);
    System.out.printf("mnemonic+address:%s%n", res3);
    System.out.println();

    KeyResponse res4 = sui.newAddress(SignatureScheme.Secp256k1);
    System.out.printf("mnemonic+address:%s%n", res4);
    System.out.println();

    KeyResponse res5 = sui.newAddress(SignatureScheme.Secp256k1);
    System.out.printf("mnemonic+address:%s%n", res5);
    System.out.println();
  }

  /** Request sui from faucet. */
  @Test
  @DisplayName("Test requestSuiFromFaucet.")
  void requestSuiFromFaucet() {
    sui.addresses()
        .forEach(
            s -> {
              System.out.printf("address:%s%n", s);
              CompletableFuture<FaucetResponse> res = sui.requestSuiFromFaucet(s);
              try {
                System.out.printf("faucet response: %s%n%n", res.get());
              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Assertions.fail();
              }
            });
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
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(
                                s, new ObjectResponseQuery(), null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    CompletableFuture<PaginatedObjectsResponse> res =
        sui.getObjectsOwnedByAddress(sender.get(), new ObjectResponseQuery(), null, null, null);
    System.out.printf("paginated objects:%s%n", res.get());
  }

  /**
   * Transfer sui.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @SuppressWarnings("checkstyle:CommentsIndentation")
  @Test
  @DisplayName("Test transferSui.")
  void transferSui() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }

    final Optional<String> recipient =
        sui.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
    if (!recipient.isPresent()) {
      Assertions.fail();
    }

    TransactionBlockResponseOptions transactionBlockResponseOptions =
        new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);
    CompletableFuture<TransactionBlockResponse> res2 =
        sui.transferSui(
            sender.get(),
            null,
            recipient.get(),
            2000L,
            null,
            5000L,
            null,
            null,
            transactionBlockResponseOptions,
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

    System.out.println(future.get());
  }

  /**
   * Move call.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test moveCall.")
  void moveCall() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    final SuiObjectResponse suiObjectResponse =
        sui.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null, null)
            .get()
            .getData()
            .get(0);
    TransactionBlockResponseOptions transactionBlockResponseOptions =
        new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);

    final io.sui.models.transactions.TypeTag.StructType structType =
        new io.sui.models.transactions.TypeTag.StructType();
    io.sui.models.transactions.StructTag structTag = new StructTag();
    structTag.setAddress("0x02");
    structTag.setModule("sui");
    structTag.setName("SUI");
    structType.setStructTag(structTag);
    CompletableFuture<TransactionBlockResponse> res =
        sui.moveCall(
            sender.get(),
            "0x02",
            "pay",
            "split",
            Lists.newArrayList(structType),
            Lists.newArrayList(suiObjectResponse.getData().getObjectId(), 10000L),
            null,
            10000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);
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

  /**
   * Merge coin.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test mergeCoin.")
  void mergeCoin() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 3;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }

    TransactionBlockResponseOptions transactionBlockResponseOptions =
        new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);

    String dest =
        sui.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null, null)
            .get()
            .getData()
            .get(0)
            .getData()
            .getObjectId();
    List<String> source =
        sui.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null, null).get()
            .getData().subList(1, 2).stream()
            .map(suiObjectResponse -> suiObjectResponse.getData().getObjectId())
            .collect(Collectors.toList());
    CompletableFuture<TransactionBlockResponse> res =
        sui.mergeCoin(
            sender.get(),
            dest,
            source,
            null,
            5000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);
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

  /**
   * Transfer object.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test transferObject.")
  void transferObjects() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();

    if (sender.isPresent()) {
      final Optional<String> receipt =
          sui.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
      if (receipt.isPresent()) {
        List<SuiObjectResponse> objects =
            sui.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null, null)
                .get()
                .getData();
        TransactionBlockResponseOptions transactionBlockResponseOptions =
            new TransactionBlockResponseOptions();
        transactionBlockResponseOptions.setShowEffects(true);
        transactionBlockResponseOptions.setShowEvents(true);
        transactionBlockResponseOptions.setShowInput(true);
        transactionBlockResponseOptions.setShowObjectChanges(true);
        CompletableFuture<TransactionBlockResponse> res =
            sui.transferObjects(
                sender.get(),
                Lists.newArrayList(objects.get(0).getData().getObjectId()),
                receipt.get(),
                null,
                3000L,
                null,
                null,
                transactionBlockResponseOptions,
                ExecuteTransactionRequestType.WaitForLocalExecution);
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

      } else {
        Assertions.fail();
      }
    } else {
      Assertions.fail();
    }
  }

  /**
   * Publish.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test publish.")
  void publish() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    TransactionBlockResponseOptions transactionBlockResponseOptions =
        new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);
    CompletableFuture<TransactionBlockResponse> res1 =
        sui.publish(
            sender.get(),
            Lists.newArrayList(
                "oRzrCwYAAAAKAQAUAhQsA0BJBIkBEgWbAWcHggLNAgjPBGAGrwXCAwrxCC0MngnUAQAMAR4B"
                    + "JAIRAh0CHwIlAiYCJwIoAAACAAABDAAAAwQAAQQHAQAAAgYHAAMCDAEIAQQIBAAFBQwABwcCAAkJ"
                    + "BwAAFgABAAEcARUBAAEjFBUBAAIpCwwAAwoNAQEIAxoJCgEIBBoSEwAFDgYHAQIGIREBAQwGJREB"
                    + "AQgHIg4PAAgXBAUBAgkbCxYACwMHAwUIBAgIEAgHAgwBDAkIAggABwgIAAILBQEIAQgHAQgAAQYJ"
                    + "AAEBAgkABwgIAQgHAQgBAgYIBwcICAELBQEJAAEKAgEIBAMHCwUBCQAKCAQKCAQBBggIAQUBCwUB"
                    + "CAECCQAFAQcICAEIBgEJAAELAwEJAAEICQVCT0FSUwRCb2FyB0Rpc3BsYXkITWV0YWRhdGEGT3B0"
                    + "aW9uCVB1Ymxpc2hlcgZTdHJpbmcJVHhDb250ZXh0A1VJRANVcmwMYWRkX211bHRpcGxlA2FnZQVi"
                    + "b2FycwVidXllcgVjbGFpbQdjcmVhdG9yC2Rlc2NyaXB0aW9uB2Rpc3BsYXkLZHVtbXlfZmllbGQI"
                    + "ZnVsbF91cmwCaWQHaW1nX3VybARpbml0E2lzX29uZV90aW1lX3dpdG5lc3MIbWV0YWRhdGEEbmFt"
                    + "ZQNuZXcVbmV3X3Vuc2FmZV9mcm9tX2J5dGVzBG5vbmUGb2JqZWN0Bm9wdGlvbgdwYWNrYWdlBXBy"
                    + "aWNlD3B1YmxpY190cmFuc2ZlcgZzZW5kZXIEc29tZQZzdHJpbmcIdHJhbnNmZXIKdHhfY29udGV4"
                    + "dAV0eXBlcwN1cmwEdXRmOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgMI"
                    + "AAAAAAAAAAAKAgUEbmFtZQoCDAtkZXNjcmlwdGlvbgoCCAdpbWdfdXJsCgIIB2NyZWF0b3IKAgYF"
                    + "cHJpY2UKAgwLcHJvamVjdF91cmwKAgQDYWdlCgIGBWJ1eWVyCgIJCGZ1bGxfdXJsCgIODWVzY2Fw"
                    + "ZV9zeW50YXgKAgcGe25hbWV9CgI7OlVuaXF1ZSBCb2FyIGZyb20gdGhlIEJvYXJzIGNvbGxlY3Rp"
                    + "b24gd2l0aCB7bmFtZX0gYW5kIHtpZH0KAiEgaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS97aW1nX3Vy"
                    + "bH0KAgoJe2NyZWF0b3J9CgIIB3twcmljZX0KAhgXaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS8KAg8O"
                    + "e21ldGFkYXRhLmFnZX0KAggHe2J1eWVyfQoCCwp7ZnVsbF91cmx9CgIJCFx7bmFtZVx9CgIKCWZp"
                    + "cnN0LnBuZwoCCwpGaXJzdCBCb2FyCgImJUZpcnN0IEJvYXIgZnJvbSB0aGUgQm9hcnMgY29sbGVj"
                    + "dGlvbiEKAgYFQ2hyaXMKAiAfaHR0cHM6Ly9nZXQtYS1ib2FyLmZ1bGx1cmwuY29tLwACARIBAQIJ"
                    + "FAgGFQgEGQgEEAgEDwsDAQgEIAsDAQgEGAgCDQUTCAkCAgELAwAAAAACXw4AOAAEBAUICwEBBwAn"
                    + "CwAKATgBDAMOAwoBOAIMAg0CBwERAwcCEQMHAxEDBwQRAwcFEQMHBhEDBwcRAwcIEQMHCREDBwoR"
                    + "A0AMCgAAAAAAAAAHCxEDBwwRAwcNEQMHDhEDBw8RAwcQEQMHEREDBxIRAwcTEQMHFBEDQAwKAAAA"
                    + "AAAAADgDCwIKAS4RCjgECwMKAS4RCjgFCgERBgcVEQMHFhEDBxcRAwcYEQM4BjgHBgoAAAAAAAAA"
                    + "EgIKAS4RCgcZEQwSAQsBLhEKOAgCAA=="),
            Lists.newArrayList(
                "0x0000000000000000000000000000000000000000000000000000000000000001",
                "0x0000000000000000000000000000000000000000000000000000000000000002"),
            null,
            10000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);
    CompletableFuture<Object> future = new CompletableFuture<>();
    res1.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future.complete(throwable);
          } else {
            future.complete(transactionResponse);
          }
        });

    System.out.println(future.get());
  }

  /**
   * Gets total transaction number.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTotalTransactionBlocks.")
  void getTotalTransactionBlocks() throws ExecutionException, InterruptedException {
    CompletableFuture<Long> res = sui.getTotalTransactionBlocks();
    System.out.printf("total transaction blocks:%d%n", res.get());
  }

  /**
   * Query transaction blocks.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test queryTransactionBlocks.")
  void queryTransactionBlocks() throws ExecutionException, InterruptedException {
    TransactionBlockResponseQuery query = new TransactionBlockResponseQuery();
    CompletableFuture<PaginatedTransactionResponse> res =
        sui.queryTransactionBlocks(query, null, 10, false);

    System.out.printf("paginated transaction blocks:%s%n", res.get());
  }

  /**
   * Gets transaction block.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTransactionBlock.")
  void getTransactionBlock() throws ExecutionException, InterruptedException {
    TransactionBlockResponseQuery query = new TransactionBlockResponseQuery();
    CompletableFuture<PaginatedTransactionResponse> res =
        sui.queryTransactionBlocks(query, null, 10, false);

    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowInput(true);
    options.setShowEffects(true);
    CompletableFuture<TransactionBlockResponse> res1 =
        sui.getTransactionBlock(res.get().getData().get(2).getDigest(), options);
    System.out.printf("transaction block:%s%n", res1.get());
  }

  /**
   * Multi get transaction blocks.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test multiGetTransactionBlocks.")
  void multiGetTransactionBlocks() throws ExecutionException, InterruptedException {
    TransactionBlockResponseQuery query = new TransactionBlockResponseQuery();
    CompletableFuture<PaginatedTransactionResponse> res =
        sui.queryTransactionBlocks(query, null, 10, false);

    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowInput(true);
    options.setShowEffects(true);
    CompletableFuture<List<TransactionBlockResponse>> res1 =
        sui.multiGetTransactionBlocks(
            Lists.newArrayList(
                res.get().getData().get(0).getDigest(), res.get().getData().get(1).getDigest()),
            options);
    System.out.printf("transaction blocks:%s%n", res1.get());
  }

  /**
   * Multi get objects.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test multiGetObjects.")
  void multiGetObjects() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s, objectResponseQuery, null, null, null)
                            .get()
                            .getData()
                            .size()
                        > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    List<String> objects =
        sui.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null, null).get()
            .getData().stream()
            .map(suiObjectResponse -> suiObjectResponse.getData().getObjectId())
            .collect(Collectors.toList());
    CompletableFuture<List<SuiObjectResponse>> res1 =
        sui.multiGetObjects(objects, new ObjectDataOptions());
    System.out.printf("object responses:%s%n", res1.get());
  }

  //  @Test
  //  @DisplayName("Test subscribeEvent.")
  //  void subscribeEvent() throws ExecutionException, InterruptedException {
  //    EventTypeEventFilter eventFilter = new EventTypeEventFilter();
  //    eventFilter.setEventType(EventType.CoinBalanceChange);
  //    Disposable disposable =
  //        sui.subscribeEvent(
  //            eventFilter,
  //            System.out::println,
  //            System.out::println);
  //    moveCall();
  //
  //    disposable.dispose();
  //  }
}
