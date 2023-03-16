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
// import java.util.concurrent.ExecutionException;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
//
/// **
// * The type Sui int tests.
// *
// * @author grapebaba
// * @since 2022.11
// */
// public class SuiIntTests {
//
//  private static final String BASE_URL = "http://localhost:9000";
//
//  private static final String TEST_KEY_STORE_PATH =
//      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";
//
//  private static Sui sui;
//
//  /** Before all. */
//  @BeforeAll
//  static void beforeAll() {
//    sui = new Sui(BASE_URL, TEST_KEY_STORE_PATH, true);
//  }
//
//  /**
//   * Gets objects owned by address.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test getObjectsOwnedByAddress.")
//  void getObjectsOwnedByAddress() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> response = res.get();
//    System.out.println(response);
//  }
//
//  /**
//   * Transfer sui.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @SuppressWarnings("checkstyle:CommentsIndentation")
//  @Test
//  @DisplayName("Test transferSui.")
////  void transferSui() throws ExecutionException, InterruptedException {
////    final String scep256k1sender =
////        sui.addresses().stream()
////            .filter(s -> sui.getByAddress(s) instanceof SECP256K1KeyPair)
////            .findFirst()
////            .get();
////    final String ed25519sender =
////        sui.addresses().stream()
////            .filter(s -> sui.getByAddress(s) instanceof ED25519KeyPair)
////            .findFirst()
////            .get();
////    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(ed25519sender);
////    List<SuiObjectInfo> objects = res.get();
////    String coinObjectId = objects.get(0).getObjectId();
////
////    // ED25519 KEY
////    CompletableFuture<ExecuteTransactionResponse> res2 =
////        sui.transferSui(
////            ed25519sender,
////            coinObjectId,
////            2000L,
////            scep256k1sender,
////            20000L,
////            ExecuteTransactionRequestType.WaitForLocalExecution);
////    CompletableFuture<Object> future = new CompletableFuture<>();
////    res2.whenComplete(
////        (transactionResponse, throwable) -> {
////          if (throwable != null) {
////            future.complete(throwable);
////          } else {
////            future.complete(transactionResponse);
////          }
////        });
////
////    System.out.println(future.get());
////    assertNotNull(
////        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
////            .getEffectsCert()
////            .getCertificate()
////            .getTransactionDigest());
////
////    CompletableFuture<List<SuiObjectInfo>> res1 = sui.getObjectsOwnedByAddress(scep256k1sender);
////    List<SuiObjectInfo> objects1 = res1.get();
////    String coinObjectId1 = objects1.get(0).getObjectId();
////    // SCEP256K1 KEY
////    CompletableFuture<ExecuteTransactionResponse> res3 =
////        sui.transferSui(
////            scep256k1sender,
////            coinObjectId1,
////            1000L,
////            ed25519sender,
////            10000L,
////            ExecuteTransactionRequestType.WaitForLocalExecution);
////    CompletableFuture<Object> future1 = new CompletableFuture<>();
////    res3.whenComplete(
////        (transactionResponse, throwable) -> {
////          if (throwable != null) {
////            future1.complete(throwable);
////          } else {
////            future1.complete(transactionResponse);
////          }
////        });
////
////    System.out.println(future1.get());
////    assertNotNull(
////        ((ExecuteTransactionResponse.EffectsCertResponse) future1.get())
////            .getEffectsCert()
////            .getCertificate()
////            .getTransactionDigest());
////  }
//
//  /**
//   * Move call.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test moveCall.")
//  void moveCall() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<ExecuteTransactionResponse> res =
//        sui.moveCall(
//            sender.get(),
//            "0x0000000000000000000000000000000000000002",
//            "devnet_nft",
//            "mint",
//            Lists.newArrayList(),
//            Lists.newArrayList(
//                "Example NFT",
//                "An example NFT.",
//                "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"),
//            null,
//            2000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Batch transaction.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test batchTransaction.")
//  void batchTransaction() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//
//    RPCTransactionRequestParams.MoveCallParams moveCallParams = new MoveCallParams();
//    moveCallParams.setArguments(
//        Lists.newArrayList(
//            "Example NFT",
//            "An example NFT.",
//            "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"));
//    moveCallParams.setTypeArguments(Lists.newArrayList());
//    moveCallParams.setPackageObjectId("0x0000000000000000000000000000000000000002");
//    moveCallParams.setModule("devnet_nft");
//    moveCallParams.setFunction("mint");
//    RPCTransactionRequestParams.MoveCallRequestParams moveCallRequestParams =
//        new MoveCallRequestParams();
//    moveCallRequestParams.setMoveCallRequestParams(moveCallParams);
//
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    String coinObjectId = objects.get(0).getObjectId();
//    List<String> addresses = new ArrayList<>(sui.addresses());
//    RPCTransactionRequestParams.TransferObjectParams transferObjectParams =
//        new TransferObjectParams();
//    transferObjectParams.setObjectId(coinObjectId);
//    transferObjectParams.setRecipient(addresses.get(3));
//    RPCTransactionRequestParams.TransferObjectRequestParams transferObjectRequestParams =
//        new TransferObjectRequestParams();
//    transferObjectRequestParams.setTransferObjectRequestParams(transferObjectParams);
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.batchTransaction(
//            sender.get(),
//            Lists.newArrayList(moveCallRequestParams, transferObjectRequestParams),
//            null,
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Pay sui.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test paySui.")
//  void paySui() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    String coinObjectId = objects.get(0).getObjectId();
//    List<String> addresses = new ArrayList<>(sui.addresses());
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.paySui(
//            sender.get(),
//            Lists.newArrayList(coinObjectId),
//            Lists.newArrayList(addresses.get(4), addresses.get(5)),
//            Lists.newArrayList(1000L, 1000L),
//            500L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Split coin.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test splitCoin.")
//  void splitCoin() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    String coinObjectId = objects.get(0).getObjectId();
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.splitCoin(
//            sender.get(),
//            coinObjectId,
//            Lists.newArrayList(100000L, 200000L, 100000L),
//            null,
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Split coin equal.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test splitCoinEqual.")
//  void splitCoinEqual() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    String coinObjectId = objects.get(0).getObjectId();
//    String gasCoinObjectId = objects.get(1).getObjectId();
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.splitCoinEqual(
//            sender.get(),
//            coinObjectId,
//            5L,
//            null,
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Merge coins.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test mergeCoins.")
//  void mergeCoins() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    String primaryCoinObjectId = objects.get(0).getObjectId();
//    String toMergeCoinObjectId = objects.get(1).getObjectId();
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.mergeCoins(
//            sender.get(),
//            primaryCoinObjectId,
//            toMergeCoinObjectId,
//            null,
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Pay all sui.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test payAllSui.")
//  void payAllSui() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//    if (!sender.isPresent()) {
//      Assertions.fail();
//    }
//    CompletableFuture<List<SuiObjectInfo>> res = sui.getObjectsOwnedByAddress(sender.get());
//    List<SuiObjectInfo> objects = res.get();
//    List<String> addresses = new ArrayList<>(sui.addresses());
//    String coinObjectId = objects.get(0).getObjectId();
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.payAllSui(
//            sender.get(),
//            Lists.newArrayList(coinObjectId),
//            addresses.get(6),
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
//  /**
//   * Transfer object.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test transferObject.")
//  void transferObject() throws ExecutionException, InterruptedException {
//    final Optional<String> sender =
//        sui.addresses().stream()
//            .filter(
//                s -> {
//                  try {
//                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
//                  } catch (InterruptedException | ExecutionException e) {
//                    return false;
//                  }
//                })
//            .findFirst();
//
//    if (sender.isPresent()) {
//      final Optional<String> receipt =
//          sui.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
//      if (receipt.isPresent()) {
//        List<SuiObjectInfo> objects = sui.getObjectsOwnedByAddress(sender.get()).get();
//        CompletableFuture<ExecuteTransactionResponse> res =
//            sui.transferObject(
//                sender.get(),
//                objects.get(0).getObjectId(),
//                receipt.get(),
//                null,
//                3000L,
//                ExecuteTransactionRequestType.WaitForLocalExecution);
//
//        System.out.println(res.get());
//        assertNotNull(
//            ((ExecuteTransactionResponse.EffectsCertResponse) res.get())
//                .getEffectsCert()
//                .getCertificate()
//                .getTransactionDigest());
//      } else {
//        Assertions.fail();
//      }
//    } else {
//      Assertions.fail();
//    }
//  }
//
//  /**
//   * Publish.
//   *
//   * @throws ExecutionException the execution exception
//   * @throws InterruptedException the interrupted exception
//   */
//  @Test
//  @DisplayName("Test publish.")
//  void publish() throws ExecutionException, InterruptedException {
//
//    CompletableFuture<ExecuteTransactionResponse> res1 =
//        sui.publish(
//            sui.addresses().first(),
//            Lists.newArrayList(
//                "oRzrCwYAAAAKAQAIAggMAxQuBEICBUQrB2+IAQj3ASgKnwIKDKkCkwEN"
//                    + "vAMEAAABAQECAQMABAgAAwYCAAENBAAABQABAAAHAgEAAAgDAQAACQQFAAAK"
//                    + "BgEAAAsEBwABDgIIAAMPCQUAAhALAQEICAoCBggAAwABBwgBAQcIAAEGCAAB"
//                    + "BQMHCAADBwgBAQMBCAIBBggBAQgAAQkAB2NvdW50ZXIGb2JqZWN0CHRyYW5z"
//                    + "ZmVyCnR4X2NvbnRleHQHQ291bnRlcgxhc3NlcnRfdmFsdWUJVHhDb250ZXh0"
//                    + "BmNyZWF0ZQlpbmNyZW1lbnQFb3duZXIJc2V0X3ZhbHVlBXZhbHVlAmlkA1VJ"
//                    + "RANuZXcGc2VuZGVyDHNoYXJlX29iamVjdAAAAAAAAAAAAAAAAAAAAAAAAAAA"
//                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAIAAgMMCAIJBQsDAAEEAAEJCwAQABQLASED"
//                    + "CAYAAAAAAAAAACcCAQEEAAEJCgARBgsALhEHBgAAAAAAAAAAEgA4AAICAQQA"
//                    + "AQkKABAAFAYBAAAAAAAAABYLAA8AFQIDAQAAAQQLABABFAIEAQQAAREKABAB"
//                    + "FAsCLhEHIQMMCwABBgAAAAAAAAAAJwsBCwAPABUCBQEAAAEECwAQABQCAAIAAQA="),
//            null,
//            5000L,
//            ExecuteTransactionRequestType.WaitForLocalExecution);
//    CompletableFuture<Object> future = new CompletableFuture<>();
//    res1.whenComplete(
//        (transactionResponse, throwable) -> {
//          if (throwable != null) {
//            future.complete(throwable);
//          } else {
//            future.complete(transactionResponse);
//          }
//        });
//
//    System.out.println(future.get());
//
//    assertNotNull(
//        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
//            .getEffectsCert()
//            .getCertificate()
//            .getTransactionDigest());
//  }
//
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
// }
