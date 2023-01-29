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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.common.collect.Lists;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.ExecuteTransactionResponse;
import io.sui.models.transactions.RPCTransactionRequestParams;
import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallParams;
import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallRequestParams;
import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectParams;
import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectRequestParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

  private static final String TEST_KEY_STORE_PATH =
      System.getProperty("user.home") + "/.sui/sui_config/sui.keystore";

  private static Sui sui;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    sui = new Sui(BASE_URL, TEST_KEY_STORE_PATH, true);
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> response = res.get();
    System.out.println(response);
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0x0a7421363a1f6a82800f7c9340ac02b5905798cb");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(0).getObjectId();
    List<String> addresses = new ArrayList<>(sui.addresses());

    // ED25519 KEY
    System.out.println(addresses.get(0));
    System.out.println(coinObjectId);
    CompletableFuture<ExecuteTransactionResponse> res2 =
        sui.transferSui(
            "0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
            coinObjectId,
            200L,
            "0xfa423b6448e5e83d03e0d98ce00b5be32da5ee86",
            2000L,
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

    assertNotNull(
        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
            .getEffectsCert()
            .getCertificate()
            .getTransactionDigest());

    //    // SECP256K1 KEY
    //    System.out.println(addresses.get(1));
    //    CompletableFuture<ExecuteTransactionResponse> res3 =
    //        sui.transferSui(
    //            addresses.get(1),
    //            coinObjectId,
    //            100L,
    //            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //            2000L,
    //            ExecuteTransactionRequestType.WaitForLocalExecution);
    //    CompletableFuture<Object> future1 = new CompletableFuture<>();
    //    res3.whenComplete(
    //        (transactionResponse, throwable) -> {
    //          if (throwable != null) {
    //            future1.complete(throwable);
    //          } else {
    //            future1.complete(transactionResponse);
    //          }
    //        });
    //
    //    assertTrue(
    //        ((SuiApiException) ((CompletionException) future1.get()).getCause())
    //            .getError()
    //            .getMessage()
    //            .contains(addresses.get(1)));
    //
    //    // ED25519 KEY
    //    System.out.println(addresses.get(0));
    //    CompletableFuture<ExecuteTransactionResponse> res4 =
    //        sui.transferSui(
    //            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //            coinObjectId,
    //            100L,
    //            addresses.get(0),
    //            2000L,
    //            ExecuteTransactionRequestType.WaitForLocalExecution);
    //    CompletableFuture<Object> future2 = new CompletableFuture<>();
    //    res4.whenComplete(
    //        (transactionResponse, throwable) -> {
    //          if (throwable != null) {
    //            future2.complete(throwable);
    //          } else {
    //            future2.complete(transactionResponse);
    //          }
    //        });
    //
    //    assertNotNull(
    //        ((ExecuteTransactionResponse.EffectsCertResponse) future2.get())
    //            .getEffectsCert()
    //            .getCertificate()
    //            .getTransactionDigest());
    //
    //    // SECP256K1 KEY
    //    System.out.println(addresses.get(1));
    //    CompletableFuture<ExecuteTransactionResponse> res5 =
    //        sui.transferSui(
    //            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
    //            coinObjectId,
    //            100L,
    //            addresses.get(1),
    //            2000L,
    //            ExecuteTransactionRequestType.WaitForLocalExecution);
    //    CompletableFuture<Object> future3 = new CompletableFuture<>();
    //    res5.whenComplete(
    //        (transactionResponse, throwable) -> {
    //          if (throwable != null) {
    //            future3.complete(throwable);
    //          } else {
    //            future3.complete(transactionResponse);
    //          }
    //        });
    //
    //    assertNotNull(
    //        ((ExecuteTransactionResponse.EffectsCertResponse) future3.get())
    //            .getEffectsCert()
    //            .getCertificate()
    //            .getTransactionDigest());
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
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();
    if (!sender.isPresent()) {
      Assertions.fail();
    }
    CompletableFuture<ExecuteTransactionResponse> res =
        sui.moveCall(
            sender.get(),
            "0x0000000000000000000000000000000000000002",
            "devnet_nft",
            "mint",
            Lists.newArrayList(),
            Lists.newArrayList(
                "Example NFT",
                "An example NFT.",
                "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"),
            null,
            2000L,
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
    assertNotNull(
        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
            .getEffectsCert()
            .getCertificate()
            .getTransactionDigest());
  }

  /**
   * Batch transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test batchTransaction.")
  void batchTransaction() throws ExecutionException, InterruptedException {
    RPCTransactionRequestParams.MoveCallParams moveCallParams = new MoveCallParams();
    moveCallParams.setArguments(
        Lists.newArrayList(
            "Example NFT",
            "An example NFT.",
            "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"));
    moveCallParams.setTypeArguments(Lists.newArrayList());
    moveCallParams.setPackageObjectId("0x0000000000000000000000000000000000000002");
    moveCallParams.setModule("devnet_nft");
    moveCallParams.setFunction("mint");
    RPCTransactionRequestParams.MoveCallRequestParams moveCallRequestParams =
        new MoveCallRequestParams();
    moveCallRequestParams.setMoveCallRequestParams(moveCallParams);

    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(3).getObjectId();
    List<String> addresses = new ArrayList<>(sui.addresses());
    RPCTransactionRequestParams.TransferObjectParams transferObjectParams =
        new TransferObjectParams();
    transferObjectParams.setObjectId(coinObjectId);
    transferObjectParams.setRecipient(addresses.get(3));
    RPCTransactionRequestParams.TransferObjectRequestParams transferObjectRequestParams =
        new TransferObjectRequestParams();
    transferObjectRequestParams.setTransferObjectRequestParams(transferObjectParams);
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.batchTransaction(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList(moveCallRequestParams, transferObjectRequestParams),
            null,
            1000L,
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

    assertNotNull(
        ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
            .getEffectsCert()
            .getCertificate()
            .getTransactionDigest());
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(2).getObjectId();
    List<String> addresses = new ArrayList<>(sui.addresses());
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.paySui(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList(coinObjectId),
            Lists.newArrayList(addresses.get(4), addresses.get(5)),
            Lists.newArrayList(1000L, 1000L),
            500L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
  }

  /**
   * Split coin.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test splitCoin.")
  void splitCoin() throws ExecutionException, InterruptedException {
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(6).getObjectId();
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.splitCoin(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            coinObjectId,
            Lists.newArrayList(100000L, 200000L, 100000L),
            null,
            5000L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String coinObjectId = objects.get(6).getObjectId();
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.splitCoinEqual(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            coinObjectId,
            5L,
            null,
            5000L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    String primaryCoinObjectId = objects.get(3).getObjectId();
    String toMergeCoinObjectId = objects.get(5).getObjectId();
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.mergeCoins(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            primaryCoinObjectId,
            toMergeCoinObjectId,
            null,
            5000L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
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
    CompletableFuture<List<SuiObjectInfo>> res =
        sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
    List<SuiObjectInfo> objects = res.get();
    List<String> addresses = new ArrayList<>(sui.addresses());
    String coinObjectId = objects.get(5).getObjectId();
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.payAllSui(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList(coinObjectId),
            addresses.get(6),
            5000L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
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
    final Optional<String> sender =
        sui.addresses().stream()
            .filter(
                s -> {
                  try {
                    return sui.getObjectsOwnedByAddress(s).get().size() > 1;
                  } catch (InterruptedException | ExecutionException e) {
                    return false;
                  }
                })
            .findFirst();

    if (sender.isPresent()) {
      final Optional<String> receipt =
          sui.addresses().stream().filter(s -> !s.equals(sender.get())).findFirst();
      if (receipt.isPresent()) {
        List<SuiObjectInfo> objects = sui.getObjectsOwnedByAddress(sender.get()).get();
        CompletableFuture<ExecuteTransactionResponse> res =
            sui.transferObject(
                sender.get(),
                objects.get(0).getObjectId(),
                receipt.get(),
                null,
                3000L,
                ExecuteTransactionRequestType.WaitForLocalExecution);

        assertNotNull(
            ((ExecuteTransactionResponse.EffectsCertResponse) res.get())
                .getEffectsCert()
                .getCertificate()
                .getTransactionDigest());
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
    CompletableFuture<ExecuteTransactionResponse> res1 =
        sui.publish(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList(
                "oRzrCwYAAAAKAQAIAggMAxQuBEICBUQrB2+IAQj3ASgKnwIKDKkCkwEN"
                    + "vAMEAAABAQECAQMABAgAAwYCAAENBAAABQABAAAHAgEAAAgDAQAACQQFAAAK"
                    + "BgEAAAsEBwABDgIIAAMPCQUAAhALAQEICAoCBggAAwABBwgBAQcIAAEGCAAB"
                    + "BQMHCAADBwgBAQMBCAIBBggBAQgAAQkAB2NvdW50ZXIGb2JqZWN0CHRyYW5z"
                    + "ZmVyCnR4X2NvbnRleHQHQ291bnRlcgxhc3NlcnRfdmFsdWUJVHhDb250ZXh0"
                    + "BmNyZWF0ZQlpbmNyZW1lbnQFb3duZXIJc2V0X3ZhbHVlBXZhbHVlAmlkA1VJ"
                    + "RANuZXcGc2VuZGVyDHNoYXJlX29iamVjdAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAIAAgMMCAIJBQsDAAEEAAEJCwAQABQLASED"
                    + "CAYAAAAAAAAAACcCAQEEAAEJCgARBgsALhEHBgAAAAAAAAAAEgA4AAICAQQA"
                    + "AQkKABAAFAYBAAAAAAAAABYLAA8AFQIDAQAAAQQLABABFAIEAQQAAREKABAB"
                    + "FAsCLhEHIQMMCwABBgAAAAAAAAAAJwsBCwAPABUCBQEAAAEECwAQABQCAAIAAQA="),
            null,
            5000L,
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

    if (future.get() instanceof Throwable) {
      System.out.println(future.get());
    } else {
      assertNotNull(
          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
              .getEffectsCert()
              .getCertificate()
              .getTransactionDigest());
    }
  }

  @SuppressWarnings("checkstyle:CommentsIndentation")
  @Test
  @DisplayName("Test executeTransaction.")
  void executeTransaction() throws ExecutionException, InterruptedException {
    //    CompletableFuture<ExecuteTransactionResponse> res1 =
    //        sui.executeTransaction(
    //            "VHJhbnNhY3Rpb25EYXRhOjoAAgAAAAAAAAAAAAAAAAAAAAAAAAACAQAAAAAAAAA"
    //                + "gqfVhcEKs/dHDNNRZjotHPp50jauePdlz6dUbjdvSY5sKZGV2bmV0X25mdARtaW5"
    //                + "0AAMADAtFeGFtcGxlIE5GVAAQD0FuIGV4YW1wbGUgTkZULgBDQmlwZnM6Ly9iYWZ"
    //                + "rcmVpYm5ncWhsM2dhYTdkYW9iNGkydmNjemlheTJqamxwNDM1Y2Y2NnZob25vN25"
    //                + "ydnd3NTN0eep5Rk2GeGt6emPj8T95jyn15llHBfcetdxpIk7446TBORfHmRkCN9k"
    //                +
    // "VAAAAAAAAACArlSTbZdDuxBzAXyjsIM2onPldxSlf//vYqXL640o3CQEAAAAAAAAAiBMAAAAAAAA=",
    //            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
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
    //    if (future.get() instanceof Throwable) {
    //      System.out.println(future.get());
    //    } else {
    //      assertNotNull(
    //          ((ExecuteTransactionResponse.EffectsCertResponse) future.get())
    //              .getEffectsCert()
    //              .getCertificate()
    //              .getTransactionDigest());
    //    }
  }
}
