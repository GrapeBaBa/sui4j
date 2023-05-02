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


import com.google.common.collect.Lists;
import io.reactivex.rxjava3.disposables.Disposable;
import io.sui.bcsgen.Argument;
import io.sui.bcsgen.Intent;
import io.sui.bcsgen.TransactionData;
import io.sui.clients.QueryClient;
import io.sui.clients.TransactionBlock;
import io.sui.crypto.KeyResponse;
import io.sui.crypto.SignatureScheme;
import io.sui.models.FaucetResponse;
import io.sui.models.coin.Balance;
import io.sui.models.coin.CoinMetadata;
import io.sui.models.coin.CoinSupply;
import io.sui.models.coin.PaginatedCoins;
import io.sui.models.events.EventFilter.AllEventFilter;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.governance.DelegatedStake;
import io.sui.models.governance.SuiCommitteeInfo;
import io.sui.models.governance.SystemStateSummary;
import io.sui.models.governance.ValidatorsApy;
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
import io.sui.models.transactions.TransactionFilter.FromAddressFilter;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Sui int tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiIntTests {

  private static final String BASE_NODE_URL = "http://localhost:9000";
  //  private static final String BASE_NODE_URL = "https://fullnode.devnet.sui.io:443";

  private static final String BASE_FAUCET_URL = "http://localhost:9123";
  //  private static final String BASE_FAUCET_URL = "https://faucet.devnet.sui.io";

  //  private static final String TEST_KEY_STORE_PATH =
  //      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";

  private static final String KEY_STORE_PATH =
      Paths.get("src/integrationTest/sui.keystore").toAbsolutePath().toString();

  private static final Sui SUI = new Sui(BASE_NODE_URL, BASE_FAUCET_URL, KEY_STORE_PATH);

  /** New address. */
  @Test
  @DisplayName("Test newAddress.")
  void newAddress() {
    KeyResponse res1 = SUI.newAddress(SignatureScheme.ED25519);
    System.out.printf("mnemonic+address:%s%n", res1);
    System.out.println();

    KeyResponse res2 = SUI.newAddress(SignatureScheme.ED25519);
    System.out.printf("mnemonic+address:%s%n", res2);
    System.out.println();

    KeyResponse res3 = SUI.newAddress(SignatureScheme.Secp256k1);
    System.out.printf("mnemonic+address:%s%n", res3);
    System.out.println();
  }

  /** Request sui from faucet. */
  @Test
  @DisplayName("Test requestSuiFromFaucet.")
  void requestSuiFromFaucet() {
    SUI.addresses()
        .forEach(
            s -> {
              System.out.printf("address:%s%n", s);
              CompletableFuture<FaucetResponse> res = SUI.requestSuiFromFaucet(s);

              try {
                TimeUnit.SECONDS.sleep(2);
                System.out.printf("faucet response: %s%n%n", res.get());
              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Assertions.fail();
              }
            });
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null)
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
        SUI.moveCall(
            sender.get(),
            "0x02",
            "pay",
            "split",
            Lists.newArrayList(structType),
            Lists.newArrayList(suiObjectResponse.getData().getObjectId(), 10000L),
            null,
            3000000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);

    System.out.println(res.get());
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
      final Optional<String> recipient =
          SUI.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
      if (recipient.isPresent()) {
        List<SuiObjectResponse> objects =
            SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null)
                .get()
                .getData();
        TransactionBlockResponseOptions transactionBlockResponseOptions =
            new TransactionBlockResponseOptions();
        transactionBlockResponseOptions.setShowEffects(true);
        transactionBlockResponseOptions.setShowEvents(true);
        transactionBlockResponseOptions.setShowInput(true);
        transactionBlockResponseOptions.setShowObjectChanges(true);
        CompletableFuture<TransactionBlockResponse> res =
            SUI.transferObjects(
                sender.get(),
                Lists.newArrayList(objects.get(0).getData().getObjectId()),
                recipient.get(),
                null,
                3000000L,
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
    CompletableFuture<TransactionBlockResponse> res =
        SUI.publish(
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
            30000000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);

    System.out.println(res.get());
  }

  /**
   * Subscribe event.
   *
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test subscribeEvent.")
  void subscribeEvent() throws InterruptedException {
    AllEventFilter eventFilter = new AllEventFilter();

    Disposable disposable =
        SUI.subscribeEvent(eventFilter, System.out::println, System.out::println);

    TimeUnit.SECONDS.sleep(10);

    disposable.dispose();
  }

  /**
   * Subscribe transaction.
   *
   * @throws InterruptedException the interrupted exception
   * @throws ExecutionException the execution exception
   */
  @Test
  @DisplayName("Test subscribeTransaction.")
  void subscribeTransaction() throws InterruptedException, ExecutionException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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

    FromAddressFilter transactionFilter = new FromAddressFilter();
    transactionFilter.setFromAddress(sender.get());

    final SuiObjectResponse suiObjectResponse =
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null)
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

    Disposable disposable =
        SUI.subscribeTransaction(transactionFilter, System.out::println, System.out::println);

    SUI.moveCall(
        sender.get(),
        "0x02",
        "pay",
        "split",
        Lists.newArrayList(structType),
        Lists.newArrayList(suiObjectResponse.getData().getObjectId(), 10000L),
        null,
        3000000L,
        null,
        null,
        transactionBlockResponseOptions,
        ExecuteTransactionRequestType.WaitForLocalExecution);

    TimeUnit.SECONDS.sleep(5);

    disposable.dispose();
  }

  /**
   * Sponsored transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test sponsoredTransaction.")
  void sponsoredTransaction() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
    if (!recipient.isPresent()) {
      Assertions.fail();
    }

    final Optional<String> sponsor =
        SUI.addresses().stream()
            .filter(s -> !s.equals(sender.get()) && !s.equals(recipient.get()))
            .findFirst();
    if (!sponsor.isPresent()) {
      Assertions.fail();
    }

    List<SuiObjectResponse> objects =
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null).get().getData();

    CompletableFuture<TransactionBlockResponse> res =
        SUI.newTransactionBlock()
            .thenCompose(
                (Function<TransactionBlock, CompletableFuture<TransactionBlockResponse>>)
                    transactionBlock -> {
                      transactionBlock.setExpiration(null);
                      transactionBlock.setSender(sender.get());
                      return transactionBlock
                          .transferObjects(
                              Lists.newArrayList(objects.get(0).getData().getObjectId()),
                              recipient.get())
                          .thenCompose(
                              (Function<Argument, CompletableFuture<TransactionBlockResponse>>)
                                  argument -> {
                                    final CompletableFuture<TransactionData>
                                        transactionDataCompletableFuture =
                                            transactionBlock
                                                .setGasData(
                                                    Lists.newArrayList(),
                                                    sponsor.get(),
                                                    3000000L,
                                                    null)
                                                .thenCompose(
                                                    (Function<
                                                            Void,
                                                            CompletableFuture<TransactionData>>)
                                                        unused -> transactionBlock.build());

                                    TransactionBlockResponseOptions
                                        transactionBlockResponseOptions =
                                            new TransactionBlockResponseOptions();
                                    transactionBlockResponseOptions.setShowEffects(true);
                                    transactionBlockResponseOptions.setShowEvents(true);
                                    transactionBlockResponseOptions.setShowInput(true);
                                    transactionBlockResponseOptions.setShowObjectChanges(true);
                                    return transactionDataCompletableFuture.thenCompose(
                                        (Function<
                                                TransactionData,
                                                CompletableFuture<TransactionBlockResponse>>)
                                            transactionData -> {
                                              Intent intent = SUI.transactionDataIntent();
                                              String sponsorSig =
                                                  SUI.signTransactionBlock(
                                                      sponsor.get(), transactionData, intent);
                                              String senderSig =
                                                  SUI.signTransactionBlock(
                                                      sender.get(), transactionData, intent);
                                              return SUI.executeTransaction(
                                                  transactionData,
                                                  Lists.newArrayList(senderSig, sponsorSig),
                                                  transactionBlockResponseOptions,
                                                  ExecuteTransactionRequestType
                                                      .WaitForLocalExecution);
                                            });
                                  });
                    });

    System.out.println(res.get());
  }

  /**
   * Gets all coins.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getAllCoins.")
  void getAllCoins() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
    CompletableFuture<PaginatedCoins> res = SUI.getAllCoins(sender.get(), null, null);
    System.out.printf("paginated coins:%s%n", res.get());
  }

  /**
   * Gets balance.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getBalance.")
  void getBalance() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
    CompletableFuture<Balance> res = SUI.getBalance(sender.get(), null);
    System.out.printf("balance:%s%n", res.get());
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
    if (!recipient.isPresent()) {
      Assertions.fail();
    }

    TransactionBlockResponseOptions transactionBlockResponseOptions =
        new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);
    CompletableFuture<TransactionBlockResponse> res =
        SUI.transferSui(
            sender.get(),
            null,
            recipient.get(),
            20000L,
            null,
            3000000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);

    System.out.println(res.get());
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null)
            .get()
            .getData()
            .get(0)
            .getData()
            .getObjectId();
    List<String> source =
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null).get().getData()
            .subList(1, 2).stream()
            .map(suiObjectResponse -> suiObjectResponse.getData().getObjectId())
            .collect(Collectors.toList());
    CompletableFuture<TransactionBlockResponse> res =
        SUI.mergeCoin(
            sender.get(),
            dest,
            source,
            null,
            3000000L,
            null,
            null,
            transactionBlockResponseOptions,
            ExecuteTransactionRequestType.WaitForLocalExecution);

    System.out.println(res.get());
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
    CompletableFuture<Long> res = SUI.getTotalTransactionBlocks();
    System.out.printf("total transaction blocks:%d%n", res.get());
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, new ObjectResponseQuery(), null, null)
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
    System.out.println(sender.get());
    CompletableFuture<PaginatedObjectsResponse> res =
        SUI.getObjectsOwnedByAddress(sender.get(), null, null, null);
    System.out.printf("paginated objects:%s%n", res.get());
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
        SUI.queryTransactionBlocks(query, null, 10, false);

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
        SUI.queryTransactionBlocks(query, null, 10, false);

    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowInput(true);
    options.setShowEffects(true);
    CompletableFuture<TransactionBlockResponse> res1 =
        SUI.getTransactionBlock(res.get().getData().get(2).getDigest(), options);
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
        SUI.queryTransactionBlocks(query, null, 10, false);

    TransactionBlockResponseOptions options = new TransactionBlockResponseOptions();
    options.setShowInput(true);
    options.setShowEffects(true);
    CompletableFuture<List<TransactionBlockResponse>> res1 =
        SUI.multiGetTransactionBlocks(
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
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null).get().getData()
            .stream()
            .map(suiObjectResponse -> suiObjectResponse.getData().getObjectId())
            .collect(Collectors.toList());
    CompletableFuture<List<SuiObjectResponse>> res1 =
        SUI.multiGetObjects(objects, new ObjectDataOptions());
    System.out.printf("object responses:%s%n", res1.get());
  }

  /**
   * Gets events.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test queryEvents.")
  void queryEvents() throws ExecutionException, InterruptedException {
    AllEventFilter eventFilter = new AllEventFilter();
    CompletableFuture<PaginatedEvents> res = SUI.queryEvents(eventFilter, null, 10, false);
    System.out.println(res.get());
  }

  /**
   * Gets validators apy.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getValidatorsApy.")
  void getValidatorsApy() throws ExecutionException, InterruptedException {
    CompletableFuture<ValidatorsApy> res = SUI.getValidatorsApy();
    System.out.println(res.get());
  }

  /**
   * Gets total supply.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getTotalSupply.")
  void getTotalSupply() throws ExecutionException, InterruptedException {
    CompletableFuture<CoinSupply> res = SUI.getTotalSupply("0x2::sui::SUI");
    System.out.println(res.get());
  }

  /**
   * Gets stakes by ids.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getStakesByIds.")
  void getStakesByIds() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
        SUI.getObjectsOwnedByAddress(sender.get(), objectResponseQuery, null, null).get().getData()
            .stream()
            .map(suiObjectResponse -> suiObjectResponse.getData().getObjectId())
            .collect(Collectors.toList());
    CompletableFuture<List<DelegatedStake>> res = SUI.getStakesByIds(Lists.newArrayList());
    res.whenComplete(
        (delegatedStake, throwable) -> {
          if (throwable != null) {
            System.out.println(throwable.getMessage());
          } else {
            System.out.println(delegatedStake);
          }
        });
  }

  /**
   * Gets stakes.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getStakes.")
  void getStakes() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
    CompletableFuture<List<DelegatedStake>> res = SUI.getStakes(sender.get());
    System.out.println(res.get());
  }

  /**
   * Gets latest sui system state.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getLatestSuiSystemState.")
  void getLatestSuiSystemState() throws ExecutionException, InterruptedException {
    CompletableFuture<SystemStateSummary> res = SUI.getLatestSuiSystemState();
    System.out.println(res.get());
  }

  /**
   * Gets committee info.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getLatestSuiSystemState.")
  void getCommitteeInfo() throws ExecutionException, InterruptedException {
    CompletableFuture<SuiCommitteeInfo> res = SUI.getCommitteeInfo(BigInteger.ONE);
    System.out.println(res.get());
  }

  /**
   * Gets coins.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCoins.")
  void getCoins() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, 10)
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
    CompletableFuture<PaginatedCoins> res = SUI.getCoins(sender.get(), null, null, 10);
    System.out.println(res.get());
  }

  /**
   * Gets coin metadata.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getCoinMetadata.")
  void getCoinMetadata() throws ExecutionException, InterruptedException {
    CompletableFuture<CoinMetadata> res = SUI.getCoinMetadata(QueryClient.DEFAULT_COIN_TYPE);
    System.out.println(res.get());
  }

  /**
   * Gets all balances.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test getAllBalances.")
  void getAllBalances() throws ExecutionException, InterruptedException {
    ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    objectResponseQuery.setOptions(new ObjectDataOptions());
    final Optional<String> sender =
        SUI.addresses().stream()
            .filter(
                s -> {
                  try {
                    return SUI.getObjectsOwnedByAddress(s, objectResponseQuery, null, null)
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
    CompletableFuture<List<Balance>> res = SUI.getAllBalances(sender.get());
    System.out.println(res.get());
  }
}
