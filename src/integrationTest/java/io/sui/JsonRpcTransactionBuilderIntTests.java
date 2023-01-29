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
import io.sui.clients.JsonRpcTransactionBuilder;
import io.sui.clients.TransactionBuilder;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallParams;
import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallRequestParams;
import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectParams;
import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectRequestParams;
import io.sui.models.transactions.StructTag;
import io.sui.models.transactions.TransactionBytes;
import io.sui.models.transactions.TypeTag;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Json rpc transaction builder int tests.
 *
 * @author grapebaba
 * @since 2022.11
 */
class JsonRpcTransactionBuilderIntTests {

  private static final String BASE_URL = "http://localhost:9000";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static TransactionBuilder transactionBuilder;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    transactionBuilder = new JsonRpcTransactionBuilder(jsonRpcClientProvider);
  }

  /**
   * Gets split coin.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test splitCoin.")
  void splitCoin() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.splitCoin(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            Lists.newArrayList(90000000000000L, 10000000000000L),
            null,
            1000L);
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
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            "0x7fbcb802d11d836a4034e7491bb544ddef460094",
            "0x24e6a45a16746213cc3aa152e2a6227857a580fa",
            1000L);
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
            Lists.newArrayList("0x26cab55541e4b0f362211f9394200b7e41fd45eb"),
            Lists.newArrayList("0x49ef9b602b76a37e0f9177783755c1a190866e72"),
            Lists.newArrayList(100L),
            null,
            1L);
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
            Lists.newArrayList("0x26cab55541e4b0f362211f9394200b7e41fd45eb"),
            Lists.newArrayList("0x49ef9b602b76a37e0f9177783755c1a190866e72"),
            Lists.newArrayList(100L),
            1L);
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
            Lists.newArrayList("0x26cab55541e4b0f362211f9394200b7e41fd45eb"),
            "0x49ef9b602b76a37e0f9177783755c1a190866e72",
            1L);
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
            "0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
            "0x45a795430b8adee6da9450c8c83e4b5d757fa2a5",
            200L,
            "0xfa423b6448e5e83d03e0d98ce00b5be32da5ee86",
            2000L);
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
  void transferObject() throws ExecutionException, InterruptedException {
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.transferObject(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            "0x51de405091c9f971fc6085d384f9ba764f268fca",
            "0x26cab55541e4b0f362211f9394200b7e41fd45eb",
            1L);
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
   * Batch transaction.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test batchTransaction.")
  void batchTransaction() throws ExecutionException, InterruptedException {
    MoveCallParams moveCallParams = new MoveCallParams();
    moveCallParams.setPackageObjectId("0x0000000000000000000000000000000000000002");
    moveCallParams.setModule("devnet_nft");
    moveCallParams.setFunction("mint");
    moveCallParams.setArguments(
        Lists.newArrayList(
            "Example NFT",
            "An NFT created by the Sui Command Line Tool",
            "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"));
    MoveCallRequestParams moveCallRequestParams = new MoveCallRequestParams();
    moveCallRequestParams.setMoveCallRequestParams(moveCallParams);

    TransferObjectParams transferObjectParams = new TransferObjectParams();
    transferObjectParams.setObjectId("0xb97f379088266a788b6b7ac350c99c3cf7683bcb");
    transferObjectParams.setRecipient("0x207f2c9f08472b1ff68644fdfc7a70df10cb3d4e");
    TransferObjectRequestParams transferObjectRequestParams = new TransferObjectRequestParams();
    transferObjectRequestParams.setTransferObjectRequestParams(transferObjectParams);
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.batchTransaction(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            Lists.newArrayList(moveCallRequestParams, transferObjectRequestParams),
            "0x163e344adfb74793481c77661f463811b990fe2a",
            20L);
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
   * Move call.
   *
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  @Test
  @DisplayName("Test moveCall.")
  void moveCall() throws ExecutionException, InterruptedException {
    final TypeTag.StructType structType = new TypeTag.StructType();
    StructTag structTag = new StructTag();
    structTag.setAddress("0x2");
    structTag.setModule("sui");
    structTag.setName("SUI");
    structType.setStructTag(structTag);
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.moveCall(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x0000000000000000000000000000000000000002",
            "pay",
            "split",
            Lists.newArrayList(structType),
            Lists.newArrayList("0x05f71eb5dc69224ef8e3a4c13917c799190237d9", 10000L),
            null,
            1000L);
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

    CompletableFuture<TransactionBytes> res2 =
        transactionBuilder.moveCall(
            "0xea79464d86786b7a7a63e3f13f798f29f5e65947",
            "0x0000000000000000000000000000000000000002",
            "devnet_nft",
            "mint",
            Lists.newArrayList(),
            Lists.newArrayList(
                "Example NFT",
                "An example NFT.",
                "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"),
            null,
            5000L);
    CompletableFuture<Object> future1 = new CompletableFuture<>();
    res2.whenComplete(
        (transactionResponse, throwable) -> {
          if (throwable != null) {
            future1.complete(throwable);
          } else {
            future1.complete(transactionResponse);
          }
        });
    System.out.println(future1.get());
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
    CompletableFuture<TransactionBytes> res =
        transactionBuilder.publish(
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
            "0x05f71eb5dc69224ef8e3a4c13917c799190237d9",
            100L);
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
