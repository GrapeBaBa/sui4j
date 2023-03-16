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

// import static io.sui.models.objects.ObjectStatus.Exists;
//
// import com.google.common.collect.Iterables;
// import com.google.common.collect.Lists;
// import com.google.common.collect.Streams;
// import com.novi.serde.Bytes;
// import com.novi.serde.SerializationError;
// import com.novi.serde.Tuple3;
// import io.sui.bcsgen.AccountAddress;
// import io.sui.bcsgen.CallArg;
// import io.sui.bcsgen.CallArg.Object;
// import io.sui.bcsgen.CallArg.Pure;
// import io.sui.bcsgen.GasData;
// import io.sui.bcsgen.Identifier;
// import io.sui.bcsgen.MoveValue;
// import io.sui.bcsgen.MoveValue.Bool;
// import io.sui.bcsgen.MoveValue.U8;
// import io.sui.bcsgen.MoveValue.Vector;
// import io.sui.bcsgen.ObjectArg;
// import io.sui.bcsgen.ObjectArg.ImmOrOwnedObject;
// import io.sui.bcsgen.ObjectArg.SharedObject;
// import io.sui.bcsgen.ObjectDigest;
// import io.sui.bcsgen.ObjectID;
// import io.sui.bcsgen.SequenceNumber;
// import io.sui.bcsgen.StructTag;
// import io.sui.bcsgen.SuiAddress;
// import io.sui.bcsgen.TransactionData;
// import io.sui.bcsgen.TransactionData.V1;
// import io.sui.bcsgen.TransactionDataV1;
// import io.sui.bcsgen.TransactionExpiration;
// import io.sui.bcsgen.TransactionExpiration.None;
// import io.sui.bcsgen.TypeTag.bool;
// import io.sui.bcsgen.TypeTag.u128;
// import io.sui.bcsgen.TypeTag.u16;
// import io.sui.bcsgen.TypeTag.u256;
// import io.sui.bcsgen.TypeTag.u32;
// import io.sui.bcsgen.TypeTag.u64;
// import io.sui.bcsgen.TypeTag.u8;
// import io.sui.models.objects.MoveNormalizedFunction;
// import io.sui.models.objects.MoveNormalizedType;
// import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType;
// import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType.Struct;
// import io.sui.models.objects.MoveNormalizedType.TypeMoveNormalizedType;
// import io.sui.models.objects.ObjectResponse;
// import io.sui.models.objects.SuiData.MoveObject;
// import io.sui.models.objects.SuiObject;
// import io.sui.models.objects.SuiObjectInfo;
// import io.sui.models.objects.SuiObjectOwner;
// import io.sui.models.objects.SuiObjectRef;
// import io.sui.models.transactions.RPCTransactionRequestParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.MoveCallRequestParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectParams;
// import io.sui.models.transactions.RPCTransactionRequestParams.TransferObjectRequestParams;
// import io.sui.models.transactions.TransactionBytes;
// import io.sui.models.transactions.TypeTag;
// import io.sui.models.transactions.TypeTag.StructType;
// import java.math.BigInteger;
// import java.nio.charset.StandardCharsets;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.CompletableFuture;
// import java.util.function.BiFunction;
// import java.util.function.Function;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
// import org.apache.commons.lang3.ArrayUtils;
// import org.apache.commons.lang3.StringUtils;
// import org.bitcoinj.core.Base58;
// import org.bouncycastle.util.encoders.Base64;
// import org.bouncycastle.util.encoders.Hex;
// import org.jetbrains.annotations.NotNull;
//
/// **
// * The type Local transaction builder.
// *
// * @author grapebaba
// * @since 2023.1
// */
// public class LocalTransactionBuilder implements TransactionBuilder {
//
//  private static final Struct RESOLVED_ASCII_STR = new Struct();
//  private static final Struct RESOLVED_UTF8_STR = new Struct();
//  private static final Struct RESOLVED_SUI_ID = new Struct();
//  private static final String GAS_COIN_TYPE = "0x2::coin::Coin<0x2::sui::SUI>";
//  private static final String SUI_FRAMEWORK_ADDRESS =
//  "0x0000000000000000000000000000000000000002";
//
//  static {
//    RESOLVED_ASCII_STR.setAddress("0x1");
//    RESOLVED_ASCII_STR.setModule("ascii");
//    RESOLVED_ASCII_STR.setName("String");
//
//    RESOLVED_UTF8_STR.setAddress("0x1");
//    RESOLVED_UTF8_STR.setModule("string");
//    RESOLVED_UTF8_STR.setName("String");
//
//    RESOLVED_SUI_ID.setAddress("0x2");
//    RESOLVED_SUI_ID.setModule("object");
//    RESOLVED_SUI_ID.setName("ID");
//  }
//
//  private final QueryClient queryClient;
//
//  /**
//   * Instantiates a new Local transaction builder.
//   *
//   * @param queryClient the query client
//   */
//  public LocalTransactionBuilder(QueryClient queryClient) {
//    this.queryClient = queryClient;
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> splitCoin(
//      String signer, String coin, List<Long> splitAmounts, String gas, long gasBudget) {
//    return getCoinStructTag(coin)
//        .thenCompose(
//            (Function<TypeTag, CompletableFuture<TransactionBytes>>)
//                typeTag ->
//                    moveCall(
//                        signer,
//                        SUI_FRAMEWORK_ADDRESS,
//                        "pay",
//                        "split_vec",
//                        Lists.newArrayList(typeTag),
//                        Lists.newArrayList(coin, splitAmounts),
//                        gas,
//                        gasBudget));
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> splitCoinEqual(
//      String signer, String coin, long splitCount, String gas, long gasBudget) {
//    return getCoinStructTag(coin)
//        .thenCompose(
//            (Function<TypeTag, CompletableFuture<TransactionBytes>>)
//                typeTag ->
//                    moveCall(
//                        signer,
//                        SUI_FRAMEWORK_ADDRESS,
//                        "pay",
//                        "divide_and_keep",
//                        Lists.newArrayList(typeTag),
//                        Lists.newArrayList(coin, splitCount),
//                        gas,
//                        gasBudget));
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> mergeCoins(
//      String signer, String primaryCoin, String toMergeCoin, String gas, long gasBudget) {
//    return getCoinStructTag(toMergeCoin)
//        .thenCompose(
//            (Function<TypeTag, CompletableFuture<TransactionBytes>>)
//                typeTag ->
//                    moveCall(
//                        signer,
//                        SUI_FRAMEWORK_ADDRESS,
//                        "pay",
//                        "join",
//                        Lists.newArrayList(typeTag),
//                        Lists.newArrayList(primaryCoin, toMergeCoin),
//                        gas,
//                        gasBudget));
//  }
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public CompletableFuture<TransactionBytes> pay(
//      String signer,
//      List<String> inputCoins,
//      List<String> recipients,
//      List<Long> amounts,
//      String gas,
//      long gasBudget) {
//    if (StringUtils.isNotEmpty(gas) && inputCoins.contains(gas)) {
//      throw new GasInPayCoinsException();
//    }
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//    CompletableFuture<SuiObjectRef> gasRefFuture =
//        refGasPriceFuture.thenCompose(
//            (Function<Long, CompletableFuture<SuiObjectRef>>)
//                gasPrice -> selectGas(signer, gas, gasBudget, gasPrice, inputCoins));
//
//    if (inputCoins.isEmpty()) {
//      throw new EmptyInputCoinsException();
//    }
//    CompletableFuture<SuiObjectRef>[] coinRefFutures =
//        (CompletableFuture<SuiObjectRef>[])
//            inputCoins.stream().map(queryClient::getObjectRef).toArray(CompletableFuture[]::new);
//
//    return CompletableFuture.allOf(
//            ArrayUtils.addAll(coinRefFutures, gasRefFuture, refGasPriceFuture))
//        .thenApply(
//            unused -> {
//              final SuiObjectRef objRef = gasRefFuture.join();
//              final long refGasPrice = refGasPriceFuture.join();
//              List<SuiObjectRef> coinRefs =
//                  Arrays.stream(coinRefFutures)
//                      .map(CompletableFuture::join)
//                      .collect(Collectors.toList());
//              if (coinRefs.isEmpty()) {
//                throw new SuiObjectNotFoundException();
//              }
//
//              Pay.Builder payBuilder = new Pay.Builder();
//              payBuilder.amounts = amounts;
//              payBuilder.coins =
//                  coinRefs.stream()
//                      .map(LocalTransactionBuilder.this::getObjectRef)
//                      .collect(Collectors.toList());
//              payBuilder.recipients =
//                  recipients.stream()
//                      .map(
//                          s -> {
//                            List<Byte> recipientBytes = geAddressBytes(s);
//                            final SuiAddress.Builder recipientAddressBuilder =
//                                new SuiAddress.Builder();
//                            recipientAddressBuilder.value = recipientBytes;
//                            return recipientAddressBuilder.build();
//                          })
//                      .collect(Collectors.toList());
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//              final SingleTransactionKind.Pay.Builder payKindBuilder =
//                  new SingleTransactionKind.Pay.Builder();
//              payKindBuilder.value = payBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = payKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(objRef));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(objRef);
//              return transactionBytes;
//            });
//  }
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public CompletableFuture<TransactionBytes> paySui(
//      String signer,
//      List<String> inputCoins,
//      List<String> recipients,
//      List<Long> amounts,
//      long gasBudget) {
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//
//    if (inputCoins.isEmpty()) {
//      throw new EmptyInputCoinsException();
//    }
//    CompletableFuture<SuiObjectRef>[] coinRefFutures =
//        (CompletableFuture<SuiObjectRef>[])
//            inputCoins.stream().map(queryClient::getObjectRef).toArray(CompletableFuture[]::new);
//
//    return CompletableFuture.allOf(ArrayUtils.addAll(coinRefFutures, refGasPriceFuture))
//        .thenApply(
//            unused -> {
//              final long refGasPrice = refGasPriceFuture.join();
//              List<SuiObjectRef> coinRefs =
//                  Arrays.stream(coinRefFutures)
//                      .map(CompletableFuture::join)
//                      .collect(Collectors.toList());
//
//              PaySui.Builder paySuiBuilder = new PaySui.Builder();
//              paySuiBuilder.amounts = amounts;
//              paySuiBuilder.coins =
//                  coinRefs.stream()
//                      .map(LocalTransactionBuilder.this::getObjectRef)
//                      .collect(Collectors.toList());
//              paySuiBuilder.recipients =
//                  recipients.stream()
//                      .map(
//                          s -> {
//                            List<Byte> recipientBytes = geAddressBytes(s);
//                            final SuiAddress.Builder recipientAddressBuilder =
//                                new SuiAddress.Builder();
//                            recipientAddressBuilder.value = recipientBytes;
//                            return recipientAddressBuilder.build();
//                          })
//                      .collect(Collectors.toList());
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//              final SingleTransactionKind.PaySui.Builder paySuiKindBuilder =
//                  new SingleTransactionKind.PaySui.Builder();
//              paySuiKindBuilder.value = paySuiBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = paySuiKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(coinRefs.get(0)));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(coinRefs.get(0));
//              return transactionBytes;
//            });
//  }
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public CompletableFuture<TransactionBytes> payAllSui(
//      String signer, List<String> inputCoins, String recipient, long gasBudget) {
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//
//    if (inputCoins.isEmpty()) {
//      throw new EmptyInputCoinsException();
//    }
//    CompletableFuture<SuiObjectRef>[] coinRefFutures =
//        (CompletableFuture<SuiObjectRef>[])
//            inputCoins.stream().map(queryClient::getObjectRef).toArray(CompletableFuture[]::new);
//
//    return CompletableFuture.allOf(ArrayUtils.addAll(coinRefFutures, refGasPriceFuture))
//        .thenApply(
//            unused -> {
//              final long refGasPrice = refGasPriceFuture.join();
//              List<SuiObjectRef> coinRefs =
//                  Arrays.stream(coinRefFutures)
//                      .map(CompletableFuture::join)
//                      .collect(Collectors.toList());
//              if (coinRefs.isEmpty()) {
//                throw new SuiObjectNotFoundException();
//              }
//              List<Byte> recipientBytes = geAddressBytes(recipient);
//              final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
//              recipientAddressBuilder.value = recipientBytes;
//
//              PayAllSui.Builder payAllSuiBuilder = new PayAllSui.Builder();
//              payAllSuiBuilder.recipient = recipientAddressBuilder.build();
//              payAllSuiBuilder.coins =
//                  coinRefs.stream()
//                      .map(LocalTransactionBuilder.this::getObjectRef)
//                      .collect(Collectors.toList());
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//              final SingleTransactionKind.PayAllSui.Builder payAllSuiKindBuilder =
//                  new SingleTransactionKind.PayAllSui.Builder();
//              payAllSuiKindBuilder.value = payAllSuiBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = payAllSuiKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(coinRefs.get(0)));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(coinRefs.get(0));
//              return transactionBytes;
//            });
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> transferSui(
//      String signer, String coin, long gasBudget, String recipient, long amount) {
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//    CompletableFuture<SuiObjectRef> objRefFuture = queryClient.getObjectRef(coin);
//    return CompletableFuture.allOf(refGasPriceFuture, objRefFuture)
//        .thenApply(
//            unused -> {
//              final Long refGasPrice = refGasPriceFuture.join();
//              final SuiObjectRef objRef = objRefFuture.join();
//
//              List<Byte> recipientBytes = geAddressBytes(recipient);
//              final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
//              recipientAddressBuilder.value = recipientBytes;
//              final Builder transferSuiBuilder = new Builder();
//              transferSuiBuilder.recipient = recipientAddressBuilder.build();
//              transferSuiBuilder.amount = Optional.of(amount);
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//              final SingleTransactionKind.TransferSui.Builder transferSuiKindBuilder =
//                  new SingleTransactionKind.TransferSui.Builder();
//              transferSuiKindBuilder.value = transferSuiBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = transferSuiKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(objRef));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(objRef);
//              return transactionBytes;
//            });
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> transferObject(
//      String signer, String suiObject, String recipient, String gas, long gasBudget) {
//    CompletableFuture<SuiObjectRef> objRefFuture = queryClient.getObjectRef(suiObject);
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//    CompletableFuture<SuiObjectRef> gasRefFuture =
//        refGasPriceFuture.thenCompose(
//            (Function<Long, CompletableFuture<SuiObjectRef>>)
//                gasPrice ->
//                    selectGas(signer, gas, gasBudget, gasPrice, Lists.newArrayList(suiObject)));
//    return CompletableFuture.allOf(refGasPriceFuture, objRefFuture, gasRefFuture)
//        .thenApply(
//            unused -> {
//              final Long refGasPrice = refGasPriceFuture.join();
//              final SuiObjectRef objRef = objRefFuture.join();
//              final SuiObjectRef gasRef = gasRefFuture.join();
//
//              List<Byte> recipientBytes = geAddressBytes(recipient);
//              final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
//              recipientAddressBuilder.value = recipientBytes;
//
//              final TransferObject.Builder transferObjectBuilder = new TransferObject.Builder();
//              transferObjectBuilder.recipient = recipientAddressBuilder.build();
//              transferObjectBuilder.object_ref = getObjectRef(objRef);
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//              final SingleTransactionKind.TransferObject.Builder transferObjectKindBuilder =
//                  new SingleTransactionKind.TransferObject.Builder();
//              transferObjectKindBuilder.value = transferObjectBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = transferObjectKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(gasRef));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(gasRef);
//              return transactionBytes;
//            });
//  }
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public CompletableFuture<TransactionBytes> batchTransaction(
//      String signer,
//      List<RPCTransactionRequestParams> batchTransactionParams,
//      String gas,
//      long gasBudget) {
//    CompletableFuture<SingleTransactionKind>[] kindFutures =
//        (CompletableFuture<SingleTransactionKind>[])
//            batchTransactionParams.stream()
//                .map(
//                    rpcTransactionRequestParams -> {
//                      if (rpcTransactionRequestParams instanceof TransferObjectRequestParams) {
//                        TransferObjectParams transferObjectParams =
//                            ((TransferObjectRequestParams) rpcTransactionRequestParams)
//                                .getTransferObjectRequestParams();
//                        return queryClient
//                            .getObjectRef(transferObjectParams.getObjectId())
//                            .thenApply(
//                                (Function<SuiObjectRef, SingleTransactionKind>)
//                                    suiObjectRef -> {
//                                      List<Byte> recipientBytes =
//                                          geAddressBytes(transferObjectParams.getRecipient());
//                                      final SuiAddress.Builder recipientAddressBuilder =
//                                          new SuiAddress.Builder();
//                                      recipientAddressBuilder.value = recipientBytes;
//
//                                      final TransferObject.Builder transferObjectBuilder =
//                                          new TransferObject.Builder();
//                                      transferObjectBuilder.recipient =
//                                          recipientAddressBuilder.build();
//                                      transferObjectBuilder.object_ref =
//                                      getObjectRef(suiObjectRef);
//
//                                      final SingleTransactionKind.TransferObject.Builder
//                                          transferObjectKindBuilder =
//                                          new SingleTransactionKind.TransferObject.Builder();
//                                      transferObjectKindBuilder.value =
//                                          transferObjectBuilder.build();
//
//                                      return transferObjectKindBuilder.build();
//                                    });
//                      }
//
//                      if (rpcTransactionRequestParams instanceof MoveCallRequestParams) {
//                        MoveCallParams moveCallParams =
//                            ((MoveCallRequestParams) rpcTransactionRequestParams)
//                                .getMoveCallRequestParams();
//
//                        List<io.sui.bcsgen.TypeTag> bcsTypeArguments =
//                            moveCallParams.getTypeArguments().stream()
//                                .map(LocalTransactionBuilder.this::toBcsTypeTag)
//                                .collect(Collectors.toList());
//
//                        return extractNormalizedFunctionParams(
//                            moveCallParams.getPackageObjectId(),
//                            moveCallParams.getModule(),
//                            moveCallParams.getFunction())
//                            .thenCompose(
//                                (Function<
//                                    List<MoveNormalizedType>,
//                                    CompletableFuture<SingleTransactionKind>>)
//                                    moveNormalizedTypes -> {
//                                      if (moveNormalizedTypes.size()
//                                          != moveCallParams.getArguments().size()) {
//                                        throw new MoveCallArgSizeNotMatchException(
//                                            moveNormalizedTypes.size(),
//                                            moveCallParams.getArguments().size());
//                                      }
//                                      CompletableFuture<CallArg>[] callArgFutures =
//                                          (CompletableFuture<CallArg>[])
//                                              Streams.zip(
//                                                      moveNormalizedTypes.stream(),
//                                                      moveCallParams.getArguments().stream(),
//                                                      (BiFunction<
//                                                          MoveNormalizedType,
//                                                          java.lang.Object,
//                                                          CompletableFuture<CallArg>>)
//                                                          LocalTransactionBuilder.this
//                                                              ::toBcsCallArg)
//                                                  .toArray(CompletableFuture[]::new);
//                                      return CompletableFuture.allOf(callArgFutures)
//                                          .thenCompose(
//                                              (Function<
//                                                  Void,
//                                                  CompletableFuture<SingleTransactionKind>>)
//                                                  unused -> {
//                                                    List<CallArg> callArgs =
//                                                        Arrays.stream(callArgFutures)
//                                                            .map(CompletableFuture::join)
//                                                            .collect(Collectors.toList());
//                                                    List<String> excludeObjects =
//                                                        callArgs.stream()
//                                                            .flatMap(
//                                                                (Function<
//                                                                    CallArg, Stream<ObjectArg>>)
//                                                                    callArg -> {
//                                                                      if (callArg
//                                                                          instanceof ObjVec) {
//                                                                        return ((ObjVec) callArg)
//                                                                            .value.stream();
//                                                                      } else if (callArg
//                                                                          instanceof Object) {
//                                                                        return Stream.of(
//                                                                            ((Object) callArg)
//                                                                                .value);
//                                                                      }
//
//                                                                      return Stream.empty();
//                                                                    })
//                                                            .map(
//                                                                (Function<
//                                                                    ObjectArg,
//                                                                    Optional<String>>)
//                                                                    objectArg -> {
//                                                                      if (objectArg
//                                                                          instanceof
//                                                                          SharedObject) {
//                                                                        return Optional.of(
//                                                                            toAddress(
//                                                                                ((SharedObject)
//                                                                                    objectArg)
//                                                                                    .id
//                                                                                    .value
//                                                                                    .value));
//                                                                      }
//
//                                                                      if (objectArg
//                                                                          instanceof
//                                                                          ImmOrOwnedObject) {
//                                                                        return Optional.of(
//                                                                            toAddress(
//                                                                               ((ImmOrOwnedObject)
//                                                                                    objectArg)
//                                                                                    .value
//                                                                                    .field0
//                                                                                    .value
//                                                                                    .value));
//                                                                      }
//
//                                                                      return Optional.empty();
//                                                                    })
//                                                            .filter(Optional::isPresent)
//                                                            .map(Optional::get)
//                                                            .collect(Collectors.toList());
//
//                                                    CompletableFuture<Long> refGasPriceFuture =
//                                                        queryClient.getReferenceGasPrice();
//                                                    CompletableFuture<SuiObjectRef> gasRefFuture =
//                                                        refGasPriceFuture.thenCompose(
//                                                            (Function<
//                                                                Long,
//                                                                CompletableFuture<
//                                                                    SuiObjectRef>>)
//                                                                gasPrice ->
//                                                                    selectGas(
//                                                                        signer,
//                                                                        gas,
//                                                                        gasBudget,
//                                                                        gasPrice,
//                                                                        excludeObjects));
//                                                    return CompletableFuture.allOf(
//                                                            gasRefFuture, refGasPriceFuture)
//                                                        .thenApply(
//                                                            unused1 -> {
//                                                              Identifier.Builder moduleBuilder =
//                                                                  new Identifier.Builder();
//                                                              moduleBuilder.value =
//                                                                  moveCallParams.getModule();
//                                                              Identifier.Builder functionBuilder =
//                                                                  new Identifier.Builder();
//                                                              functionBuilder.value =
//                                                                  moveCallParams.getFunction();
//
//                                                              AccountAddress.Builder
//                                                                  objectAddressBuilder =
//                                                                  new AccountAddress.Builder();
//                                                              objectAddressBuilder.value =
//                                                                  geAddressBytes(
//                                                                      moveCallParams
//                                                                          .getPackageObjectId());
//                                                              ObjectID.Builder objectIdBuilder =
//                                                                  new ObjectID.Builder();
//                                                              objectIdBuilder.value =
//                                                                  objectAddressBuilder.build();
//                                                              final MoveCall.Builder
//                                                                  moveCallBuilder =
//                                                                  new MoveCall.Builder();
//                                                              moveCallBuilder.type_arguments =
//                                                                  bcsTypeArguments;
//                                                             moveCallBuilder.arguments = callArgs;
//                                                              moveCallBuilder.Package =
//                                                                  objectIdBuilder.build();
//                                                              moveCallBuilder.module =
//                                                                  moduleBuilder.build();
//                                                              moveCallBuilder.function =
//                                                                  functionBuilder.build();
//
//                                                              final Call.Builder
//                                                                  moveCallKindBuilder =
//                                                                  new Call.Builder();
//                                                              moveCallKindBuilder.value =
//                                                                  moveCallBuilder.build();
//
//                                                              return moveCallKindBuilder.build();
//                                                            });
//                                                  });
//                                    });
//                      }
//                      throw new NotSupportedTransactionKindException();
//                    })
//                .toArray(CompletableFuture[]::new);
//    return CompletableFuture.allOf(kindFutures)
//        .thenCompose(
//            (Function<Void, CompletableFuture<TransactionBytes>>)
//                unused -> {
//                  final List<SingleTransactionKind> kinds =
//                      Arrays.stream(kindFutures)
//                          .map(CompletableFuture::join)
//                          .collect(Collectors.toList());
//
//                  List<String> excludeObjects =
//                      kinds.stream()
//                          .map(
//                              singleTransactionKind -> {
//                                if (singleTransactionKind
//                                    instanceof SingleTransactionKind.TransferObject) {
//
//                                  return Lists.newArrayList(
//                                      toAddress(
//                                          ((SingleTransactionKind.TransferObject)
//                                              singleTransactionKind)
//                                              .value
//                                              .object_ref
//                                              .field0
//                                              .value
//                                              .value));
//                                }
//
//                                if (singleTransactionKind instanceof Call) {
//                                  List<CallArg> callArgs =
//                                      ((Call) singleTransactionKind).value.arguments;
//
//                                  return callArgs.stream()
//                                      .flatMap(
//                                          (Function<CallArg, Stream<ObjectArg>>)
//                                              callArg -> {
//                                                if (callArg instanceof ObjVec) {
//                                                  return ((ObjVec) callArg).value.stream();
//                                                } else if (callArg instanceof Object) {
//                                                  return Stream.of(((Object) callArg).value);
//                                                }
//
//                                                return Stream.empty();
//                                              })
//                                      .map(
//                                          (Function<ObjectArg, Optional<String>>)
//                                              objectArg -> {
//                                                if (objectArg instanceof SharedObject) {
//                                                  return Optional.of(
//                                                      toAddress(
//                                                          ((SharedObject) objectArg)
//                                                              .id
//                                                              .value
//                                                              .value));
//                                                }
//
//                                                if (objectArg instanceof ImmOrOwnedObject) {
//                                                  return Optional.of(
//                                                      toAddress(
//                                                          ((ImmOrOwnedObject) objectArg)
//                                                              .value
//                                                              .field0
//                                                              .value
//                                                              .value));
//                                                }
//
//                                                return Optional.empty();
//                                              })
//                                      .filter(Optional::isPresent)
//                                      .map(Optional::get)
//                                      .collect(Collectors.toList());
//                                }
//
//                                throw new NotSupportedTransactionKindException();
//                              })
//                          .flatMap(Collection::stream)
//                          .collect(Collectors.toList());
//
//                  CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//                  CompletableFuture<SuiObjectRef> gasRefFuture =
//                      refGasPriceFuture.thenCompose(
//                          (Function<Long, CompletableFuture<SuiObjectRef>>)
//                              gasPrice ->
//                                  selectGas(signer, gas, gasBudget, gasPrice, excludeObjects));
//                  return CompletableFuture.allOf(gasRefFuture, refGasPriceFuture)
//                      .thenApply(
//                          unused12 -> {
//                            final Batch.Builder batchBuilder = new Batch.Builder();
//                            batchBuilder.value = kinds;
//
//                            List<Byte> senderBytes = geAddressBytes(signer);
//                            final SuiAddress.Builder senderAddressBuilder =
//                                new SuiAddress.Builder();
//                            senderAddressBuilder.value = senderBytes;
//
//                            final Long refGasPrice = refGasPriceFuture.join();
//                            final SuiObjectRef gasRef = gasRefFuture.join();
//                            final GasData.Builder gasDataBuilder = new GasData.Builder();
//                            gasDataBuilder.budget = gasBudget;
//                            gasDataBuilder.price = refGasPrice;
//                            gasDataBuilder.payment = Lists.newArrayList(getObjectRef(gasRef));
//                            gasDataBuilder.owner = senderAddressBuilder.build();
//                            final TransactionDataV1.Builder transactionDataV1Builder =
//                                new TransactionDataV1.Builder();
//                            transactionDataV1Builder.kind = batchBuilder.build();
//                            transactionDataV1Builder.sender = senderAddressBuilder.build();
//                            transactionDataV1Builder.gas_data = gasDataBuilder.build();
//                            TransactionExpiration.None.Builder expirationBuilder =
//                                new None.Builder();
//                            transactionDataV1Builder.expiration = expirationBuilder.build();
//
//                            final TransactionData.V1.Builder v1Builder = new V1.Builder();
//                            v1Builder.value = transactionDataV1Builder.build();
//
//                            final TransactionBytes transactionBytes = new TransactionBytes();
//                            transactionBytes.setLocalTxBytes(v1Builder.build());
//                            transactionBytes.setGas(gasRef);
//                            return transactionBytes;
//                          });
//                });
//  }
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public CompletableFuture<TransactionBytes> moveCall(
//      String signer,
//      String packageObjectId,
//      String module,
//      String function,
//      List<TypeTag> typeArguments,
//      List<?> arguments,
//      String gas,
//      long gasBudget) {
//    List<io.sui.bcsgen.TypeTag> bcsTypeArguments =
//        typeArguments.stream().map(this::toBcsTypeTag).collect(Collectors.toList());
//
//    return extractNormalizedFunctionParams(packageObjectId, module, function)
//        .thenCompose(
//            (Function<List<MoveNormalizedType>, CompletableFuture<TransactionBytes>>)
//                moveNormalizedTypes -> {
//                  if (moveNormalizedTypes.size() != arguments.size()) {
//                    throw new MoveCallArgSizeNotMatchException(
//                        moveNormalizedTypes.size(), arguments.size());
//                  }
//                  CompletableFuture<CallArg>[] callArgFutures =
//                      (CompletableFuture<CallArg>[])
//                          Streams.zip(
//                                  moveNormalizedTypes.stream(),
//                                  arguments.stream(),
//                                  (BiFunction<
//                                      MoveNormalizedType,
//                                      java.lang.Object,
//                                      CompletableFuture<CallArg>>)
//                                      this::toBcsCallArg)
//                              .toArray(CompletableFuture[]::new);
//                  return CompletableFuture.allOf(callArgFutures)
//                      .thenCompose(
//                          (Function<Void, CompletableFuture<TransactionBytes>>)
//                              unused -> {
//                                List<CallArg> callArgs =
//                                    Arrays.stream(callArgFutures)
//                                        .map(CompletableFuture::join)
//                                        .collect(Collectors.toList());
//                                List<String> excludeObjects =
//                                    callArgs.stream()
//                                        .flatMap(
//                                            (Function<CallArg, Stream<ObjectArg>>)
//                                                callArg -> {
//                                                  if (callArg instanceof ObjVec) {
//                                                    return ((ObjVec) callArg).value.stream();
//                                                  } else if (callArg instanceof Object) {
//                                                    return Stream.of(((Object) callArg).value);
//                                                  }
//
//                                                  return Stream.empty();
//                                                })
//                                        .map(
//                                            (Function<ObjectArg, Optional<String>>)
//                                                objectArg -> {
//                                                  if (objectArg instanceof SharedObject) {
//                                                    return Optional.of(
//                                                        toAddress(
//                                                            ((SharedObject) objectArg)
//                                                                .id
//                                                                .value
//                                                                .value));
//                                                  }
//
//                                                  if (objectArg instanceof ImmOrOwnedObject) {
//                                                    return Optional.of(
//                                                        toAddress(
//                                                            ((ImmOrOwnedObject) objectArg)
//                                                                .value
//                                                                .field0
//                                                                .value
//                                                                .value));
//                                                  }
//
//                                                  return Optional.empty();
//                                                })
//                                        .filter(Optional::isPresent)
//                                        .map(Optional::get)
//                                        .collect(Collectors.toList());
//
//                                CompletableFuture<Long> refGasPriceFuture =
//                                    queryClient.getReferenceGasPrice();
//                                CompletableFuture<SuiObjectRef> gasRefFuture =
//                                    refGasPriceFuture.thenCompose(
//                                        (Function<Long, CompletableFuture<SuiObjectRef>>)
//                                            gasPrice ->
//                                                selectGas(
//                                                    signer,
//                                                    gas,
//                                                    gasBudget,
//                                                    gasPrice,
//                                                    excludeObjects));
//
//                                return CompletableFuture.allOf(gasRefFuture, refGasPriceFuture)
//                                    .thenApply(
//                                        unused1 -> {
//                                          List<Byte> senderBytes = geAddressBytes(signer);
//                                          final SuiAddress.Builder senderAddressBuilder =
//                                              new SuiAddress.Builder();
//                                          senderAddressBuilder.value = senderBytes;
//
//                                          Identifier.Builder moduleBuilder =
//                                              new Identifier.Builder();
//                                          moduleBuilder.value = module;
//                                          Identifier.Builder functionBuilder =
//                                              new Identifier.Builder();
//                                          functionBuilder.value = function;
//
//                                          AccountAddress.Builder objectAddressBuilder =
//                                              new AccountAddress.Builder();
//                                          objectAddressBuilder.value =
//                                              geAddressBytes(packageObjectId);
//                                       ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
//                                          objectIdBuilder.value = objectAddressBuilder.build();
//                                          final MoveCall.Builder moveCallBuilder =
//                                              new MoveCall.Builder();
//                                          moveCallBuilder.type_arguments = bcsTypeArguments;
//                                          moveCallBuilder.arguments = callArgs;
//                                          moveCallBuilder.Package = objectIdBuilder.build();
//                                          moveCallBuilder.module = moduleBuilder.build();
//                                          moveCallBuilder.function = functionBuilder.build();
//
//                                          final Call.Builder moveCallKindBuilder =
//                                              new Call.Builder();
//                                          moveCallKindBuilder.value = moveCallBuilder.build();
//
//                                          final Single.Builder singleKindBuilder =
//                                              new Single.Builder();
//                                          singleKindBuilder.value = moveCallKindBuilder.build();
//
//                                          long refGasPrice = refGasPriceFuture.join();
//                                          SuiObjectRef gasRef = gasRefFuture.join();
//                                          final GasData.Builder gasDataBuilder =
//                                              new GasData.Builder();
//                                          gasDataBuilder.budget = gasBudget;
//                                          gasDataBuilder.price = refGasPrice;
//                                          gasDataBuilder.payment =
//                                              Lists.newArrayList(getObjectRef(gasRef));
//                                          gasDataBuilder.owner = senderAddressBuilder.build();
//                                        final TransactionDataV1.Builder transactionDataV1Builder =
//                                              new TransactionDataV1.Builder();
//                                       transactionDataV1Builder.kind = singleKindBuilder.build();
//                                          transactionDataV1Builder.sender =
//                                              senderAddressBuilder.build();
//                                          transactionDataV1Builder.gas_data =
//                                              gasDataBuilder.build();
//                                          TransactionExpiration.None.Builder expirationBuilder =
//                                              new None.Builder();
//                                          transactionDataV1Builder.expiration =
//                                              expirationBuilder.build();
//
//                                          final TransactionData.V1.Builder v1Builder =
//                                              new V1.Builder();
//                                          v1Builder.value = transactionDataV1Builder.build();
//
//                                          final TransactionBytes transactionBytes =
//                                              new TransactionBytes();
//                                          transactionBytes.setLocalTxBytes(v1Builder.build());
//                                          transactionBytes.setGas(gasRef);
//                                          return transactionBytes;
//                                        });
//                              });
//                });
//  }
//
//  @Override
//  public CompletableFuture<TransactionBytes> publish(
//      String signer, List<String> compiledModules, String gas, long gasBudget) {
//    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
//    CompletableFuture<SuiObjectRef> gasRefFuture =
//        refGasPriceFuture.thenCompose(
//            (Function<Long, CompletableFuture<SuiObjectRef>>)
//                gasPrice -> selectGas(signer, gas, gasBudget, gasPrice, Lists.newArrayList()));
//    return CompletableFuture.allOf(refGasPriceFuture, gasRefFuture)
//        .thenApply(
//            unused -> {
//              final Long refGasPrice = refGasPriceFuture.join();
//              final SuiObjectRef objRef = gasRefFuture.join();
//
//              List<Byte> senderBytes = geAddressBytes(signer);
//              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
//              senderAddressBuilder.value = senderBytes;
//
//            MoveModulePublish.Builder moveModulePublishBuilder = new MoveModulePublish.Builder();
//              moveModulePublishBuilder.modules =
//                  compiledModules.stream()
//                      .map(s -> Bytes.valueOf(Base64.decode(s)))
//                      .collect(Collectors.toList());
//              final SingleTransactionKind.Publish.Builder publishKindBuilder =
//                  new SingleTransactionKind.Publish.Builder();
//              publishKindBuilder.value = moveModulePublishBuilder.build();
//
//              final Single.Builder singleKindBuilder = new Single.Builder();
//              singleKindBuilder.value = publishKindBuilder.build();
//
//              final GasData.Builder gasDataBuilder = new GasData.Builder();
//              gasDataBuilder.budget = gasBudget;
//              gasDataBuilder.price = refGasPrice;
//              gasDataBuilder.payment = Lists.newArrayList(getObjectRef(objRef));
//              gasDataBuilder.owner = senderAddressBuilder.build();
//              final TransactionDataV1.Builder transactionDataV1Builder =
//                  new TransactionDataV1.Builder();
//              transactionDataV1Builder.kind = singleKindBuilder.build();
//              transactionDataV1Builder.sender = senderAddressBuilder.build();
//              transactionDataV1Builder.gas_data = gasDataBuilder.build();
//              TransactionExpiration.None.Builder expirationBuilder = new None.Builder();
//              transactionDataV1Builder.expiration = expirationBuilder.build();
//
//              final TransactionData.V1.Builder v1Builder = new V1.Builder();
//              v1Builder.value = transactionDataV1Builder.build();
//
//              final TransactionBytes transactionBytes = new TransactionBytes();
//              transactionBytes.setLocalTxBytes(v1Builder.build());
//              transactionBytes.setGas(objRef);
//              return transactionBytes;
//            });
//  }
//
//  private CompletableFuture<TypeTag> getCoinStructTag(String objectId) {
//    return queryClient
//        .getObject(objectId)
//        .thenApply(
//            objectResponse -> {
//              String type = null;
//              if (objectResponse.getDetails() instanceof SuiObjectInfo) {
//                type = ((SuiObjectInfo) objectResponse.getDetails()).getType();
//              }
//
//              if (objectResponse.getDetails() instanceof SuiObject) {
//                if (((SuiObject) objectResponse.getDetails()).getData() instanceof MoveObject) {
//                  type =
//                    ((MoveObject) ((SuiObject) objectResponse.getDetails()).getData()).getType();
//                }
//              }
//
//              if (null == type) {
//                throw new SuiObjectNotFoundException();
//              }
//
//              if (!GAS_COIN_TYPE.equals(type)) {
//                throw new ObjectIsNotCoinException(objectId);
//              }
//
//              io.sui.models.transactions.StructTag structTag =
//                  new io.sui.models.transactions.StructTag();
//              structTag.setAddress(SUI_FRAMEWORK_ADDRESS);
//              structTag.setModule("sui");
//              structTag.setName("SUI");
//              structTag.setTypeParams(Lists.newArrayList());
//
//              StructType structType = new StructType();
//              structType.setStructTag(structTag);
//              return structType;
//            });
//  }
//
//  @SuppressWarnings("unchecked")
//  private CompletableFuture<SuiObjectRef> selectGas(
//      String signer, String inputGas, long budget, long gasPrice, List<String> excludeObjects) {
//    if (StringUtils.isNotEmpty(inputGas)) {
//      return queryClient.getObjectRef(inputGas);
//    } else {
//      return queryClient
//          .getObjectsOwnedByAddress(signer)
//          .thenCompose(
//              (Function<List<SuiObjectInfo>, CompletableFuture<SuiObjectRef>>)
//                  suiObjectInfos -> {
//                    CompletableFuture<Optional<SuiObjectRef>>[] gases =
//                        (CompletableFuture<Optional<SuiObjectRef>>[])
//                            suiObjectInfos.stream()
//                                .map(
//                                    suiObjectInfo -> {
//                                      if (suiObjectInfo.getType().equals(GAS_COIN_TYPE)) {
//                                        return queryClient
//                                            .getObject(suiObjectInfo.getObjectId())
//                                            .thenCompose(
//                                                (Function<
//                                                    ObjectResponse,
//                                                    CompletableFuture<Optional<SuiObjectRef>>>)
//                                                    objectResponse -> {
//                                                      final SuiObject suiObject =
//                                                          (SuiObject) objectResponse.getDetails();
//                                                      final long balance =
//                                                          Long.parseLong(
//                                                              (String)
//                                                                ((MoveObject) suiObject.getData())
//                                                                      .getFields()
//                                                                      .get("balance"));
//                                                      final long requiredGasAmount =
//                                                          BigInteger.valueOf(budget)
//                                                              .multiply(
//                                                                  BigInteger.valueOf(gasPrice))
//                                                              .longValue();
//                                                      if (!excludeObjects.contains(
//                                                          suiObjectInfo.getObjectId())
//                                                          && balance >= requiredGasAmount) {
//                                                        return CompletableFuture.completedFuture(
//                                                          Optional.of(suiObject.getReference()));
//                                                      }
//
//                                                      return CompletableFuture.completedFuture(
//                                                          Optional.empty());
//                                                    });
//                                      }
//                                      return CompletableFuture
//                                          .<Optional<SuiObjectRef>>completedFuture(
//                                              Optional.empty());
//                                    })
//                                .toArray(CompletableFuture[]::new);
//
//                    return CompletableFuture.allOf(gases)
//                        .thenApply(
//                            unused -> {
//                              Optional<Optional<SuiObjectRef>> selected =
//                                  Arrays.stream(gases)
//                                      .map(CompletableFuture::join)
//                                      .filter(Optional::isPresent)
//                                      .findFirst();
//
//                              if (!selected.isPresent()) {
//                                throw new GasNotFoundException();
//                              }
//
//                              return selected.get().get();
//                            });
//                  });
//    }
//  }
//
//  private CompletableFuture<List<MoveNormalizedType>> extractNormalizedFunctionParams(
//      String packageObjectId, String module, String function) {
//    CompletableFuture<MoveNormalizedFunction> normalizedFunction =
//        this.queryClient.getNormalizedMoveFunction(packageObjectId, module, function);
//    return normalizedFunction.thenCompose(
//        (Function<MoveNormalizedFunction, CompletableFuture<List<MoveNormalizedType>>>)
//            moveNormalizedFunction -> {
//              final boolean hasTxContext =
//                  moveNormalizedFunction.getParameters().size() > 0
//                      && isTxContext(Iterables.getLast(moveNormalizedFunction.getParameters()));
//              return CompletableFuture.completedFuture(
//                  hasTxContext
//                      ? moveNormalizedFunction
//                      .getParameters()
//                      .subList(0, moveNormalizedFunction.getParameters().size() - 1)
//                      : moveNormalizedFunction.getParameters());
//            });
//  }
//
//  private Optional<MoveValue> toPureMoveValue(
//      MoveNormalizedType moveNormalizedType, java.lang.Object argVal) {
//    final MoveValue moveValue;
//    if (moveNormalizedType instanceof MoveNormalizedType.TypeMoveNormalizedType) {
//      MoveNormalizedType.TypeMoveNormalizedType argType =
//          (MoveNormalizedType.TypeMoveNormalizedType) moveNormalizedType;
//      switch (argType) {
//        case U8:
//          checkArgType(moveNormalizedType, argVal, Byte.class);
//          MoveValue.U8.Builder u8Builder = new MoveValue.U8.Builder();
//          u8Builder.value = (Byte) argVal;
//          moveValue = u8Builder.build();
//          break;
//        case U16:
//          checkArgType(moveNormalizedType, argVal, Short.class);
//
//          MoveValue.U16.Builder u16Builder = new MoveValue.U16.Builder();
//          u16Builder.value = (Short) argVal;
//          moveValue = u16Builder.build();
//          break;
//        case U32:
//          checkArgType(moveNormalizedType, argVal, Integer.class);
//
//          MoveValue.U32.Builder u32Builder = new MoveValue.U32.Builder();
//          u32Builder.value = (Integer) argVal;
//          moveValue = u32Builder.build();
//          break;
//        case U64:
//          checkArgType(moveNormalizedType, argVal, Long.class);
//
//          MoveValue.U64.Builder u64Builder = new MoveValue.U64.Builder();
//          u64Builder.value = (Long) argVal;
//          moveValue = u64Builder.build();
//          break;
//        case U128:
//          checkArgType(moveNormalizedType, argVal, BigInteger.class);
//
//          MoveValue.U128.Builder u128Builder = new MoveValue.U128.Builder();
//          u128Builder.value = (BigInteger) argVal;
//          moveValue = u128Builder.build();
//          break;
//        case U256:
//          checkArgType(moveNormalizedType, argVal, byte[].class);
//
//          MoveValue.U256.Builder u256Builder = new MoveValue.U256.Builder();
//          u256Builder.value = Arrays.asList(ArrayUtils.toObject((byte[]) argVal));
//          moveValue = u256Builder.build();
//          break;
//        case Bool:
//          checkArgType(moveNormalizedType, argVal, Boolean.class);
//
//          MoveValue.Bool.Builder boolBuilder = new Bool.Builder();
//          boolBuilder.value = (Boolean) argVal;
//          moveValue = boolBuilder.build();
//          break;
//        case Address:
//          checkArgType(moveNormalizedType, argVal, String.class);
//
//          AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
//          addressBuilder.value = geAddressBytes((String) argVal);
//          MoveValue.Address.Builder addressValueBuilder = new MoveValue.Address.Builder();
//          addressValueBuilder.value = addressBuilder.build();
//          moveValue = addressValueBuilder.build();
//          break;
//        default:
//          throw new NotSupportedMoveNormalizedTypeException();
//      }
//
//      return Optional.of(moveValue);
//    }
//
//    if (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
//      final Struct argStruct =
//          ((MoveNormalizedType.MoveNormalizedStructType) moveNormalizedType).getStruct();
//      if (argStruct.equals(RESOLVED_ASCII_STR)) {
//        checkArgType(moveNormalizedType, argVal, String.class);
//
//        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
//        vectorBuilder.value =
//            Arrays.stream(
//                    ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.US_ASCII)))
//                .map(
//                    (Function<Byte, MoveValue>)
//                        b -> {
//                          U8.Builder u8Builder = new U8.Builder();
//                          u8Builder.value = b;
//                          return u8Builder.build();
//                        })
//                .collect(Collectors.toList());
//        moveValue = vectorBuilder.build();
//
//        return Optional.of(moveValue);
//      } else if (argStruct.equals(RESOLVED_UTF8_STR)) {
//        checkArgType(moveNormalizedType, argVal, String.class);
//
//        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
//        vectorBuilder.value =
//            Arrays.stream(ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.UTF_8)))
//                .map(
//                    (Function<Byte, MoveValue>)
//                        b -> {
//                          U8.Builder u8Builder = new U8.Builder();
//                          u8Builder.value = b;
//                          return u8Builder.build();
//                        })
//                .collect(Collectors.toList());
//        moveValue = vectorBuilder.build();
//
//        return Optional.of(moveValue);
//      } else if (argStruct.equals(RESOLVED_SUI_ID)) {
//        checkArgType(moveNormalizedType, argVal, String.class);
//
//        AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
//        addressBuilder.value = geAddressBytes((String) argVal);
//        MoveValue.Address.Builder addressValueBuilder = new MoveValue.Address.Builder();
//        addressValueBuilder.value = addressBuilder.build();
//        moveValue = addressValueBuilder.build();
//
//        return Optional.of(moveValue);
//      }
//    }
//
//    if (moveNormalizedType instanceof MoveNormalizedType.VectorReferenceMoveNormalizedType) {
//      if (((MoveNormalizedType.VectorReferenceMoveNormalizedType) moveNormalizedType).getVector()
//          == TypeMoveNormalizedType.U8) {
//        checkArgType(moveNormalizedType, argVal, String.class);
//
//        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
//        vectorBuilder.value =
//            Arrays.stream(ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.UTF_8)))
//                .map(
//                    (Function<Byte, MoveValue>)
//                        b -> {
//                          U8.Builder u8Builder = new U8.Builder();
//                          u8Builder.value = b;
//                          return u8Builder.build();
//                        })
//                .collect(Collectors.toList());
//        moveValue = vectorBuilder.build();
//
//        return Optional.of(moveValue);
//      }
//
//      if (!(argVal instanceof List<?>)) {
//        throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
//      }
//
//      List<?> objects = (List<?>) argVal;
//      List<MoveValue> pureMoveValues =
//          objects.stream()
//              .map(
//                  object ->
//                      toPureMoveValue(
//                          ((MoveNormalizedType.VectorReferenceMoveNormalizedType)
//                              moveNormalizedType)
//                              .getVector(),
//                          object))
//              .filter(Optional::isPresent)
//              .map(Optional::get)
//              .collect(Collectors.toList());
//
//      if (pureMoveValues.isEmpty()) {
//        // ObjVec TYPE will be handled later
//        return Optional.empty();
//      }
//
//      MoveValue.Vector.Builder moveValueVectorBuilder = new Vector.Builder();
//      moveValueVectorBuilder.value = pureMoveValues;
//
//      return Optional.of(moveValueVectorBuilder.build());
//    }
//
//    return Optional.empty();
//  }
//
//  @SuppressWarnings("unchecked")
//  private CompletableFuture<CallArg> toBcsCallArg(
//      MoveNormalizedType moveNormalizedType, java.lang.Object argVal) {
//    final Optional<MoveValue> pureMoveValue = toPureMoveValue(moveNormalizedType, argVal);
//    if (pureMoveValue.isPresent()) {
//      final CallArg.Pure.Builder pureBuilder = getPureBuilder(pureMoveValue.get());
//      return CompletableFuture.completedFuture(pureBuilder.build());
//    }
//
//    if (moveNormalizedType instanceof MoveNormalizedType.VectorReferenceMoveNormalizedType) {
//
//      if (((MoveNormalizedType.VectorReferenceMoveNormalizedType) moveNormalizedType).getVector()
//          instanceof MoveNormalizedType.MoveNormalizedStructType) {
//        checkArgType(moveNormalizedType, argVal, List.class);
//
//        CompletableFuture<ObjectArg>[] objectArgFutures =
//            (CompletableFuture<ObjectArg>[])
//                Arrays.stream((String[]) argVal)
//                    .map(this::newObjectArg)
//                    .toArray(CompletableFuture[]::new);
//
//        return CompletableFuture.allOf(objectArgFutures)
//            .thenApply(
//                unused -> {
//                  final CallArg.ObjVec.Builder objVecBuilder = new ObjVec.Builder();
//                  objVecBuilder.value =
//                      Arrays.stream(objectArgFutures)
//                          .map(CompletableFuture::join)
//                          .collect(Collectors.toList());
//                  return objVecBuilder.build();
//                });
//      }
//    }
//
//    final Optional<Struct> structOptional =
//        extractStruct(moveNormalizedType).map(MoveNormalizedStructType::getStruct);
//
//    if (structOptional.isPresent()
//        || (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedTypeParameterType)) {
//      checkArgType(moveNormalizedType, argVal, String.class);
//
//      return newObjectArg((String) argVal)
//          .thenApply(
//              objectArg -> {
//                final Object.Builder objectBuilder = new Object.Builder();
//                objectBuilder.value = objectArg;
//                return objectBuilder.build();
//              });
//    }
//
//    throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
//  }
//
//  private CompletableFuture<ObjectArg> newObjectArg(String objectId) {
//    return queryClient
//        .getObject(objectId)
//        .thenApply(
//            objectResponse -> {
//              if (Exists != objectResponse.getStatus()) {
//                throw new SuiObjectNotFoundException();
//              }
//              final SuiObjectOwner owner = ((SuiObject) objectResponse.getDetails()).getOwner();
//              if (owner instanceof SuiObjectOwner.SharedOwner) {
//                AccountAddress.Builder accountAddressBuilder = new AccountAddress.Builder();
//                accountAddressBuilder.value = geAddressBytes(objectId);
//                ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
//                objectIdBuilder.value = accountAddressBuilder.build();
//                SequenceNumber.Builder seqBuilder = new SequenceNumber.Builder();
//                seqBuilder.value =
//                    ((SuiObjectOwner.SharedOwner) owner).getShared().getInitial_shared_version();
//                SharedObject.Builder sharedObjectBuilder = new SharedObject.Builder();
//                sharedObjectBuilder.id = objectIdBuilder.build();
//                sharedObjectBuilder.initial_shared_version = seqBuilder.build();
//                sharedObjectBuilder.mutable = true;
//                return sharedObjectBuilder.build();
//              }
//
//              ImmOrOwnedObject.Builder immOrOwnedObjectBuilder = new ImmOrOwnedObject.Builder();
//              immOrOwnedObjectBuilder.value = getObjectRef(objectResponse.getObjectRef());
//              return immOrOwnedObjectBuilder.build();
//            });
//  }
//
//  private void checkArgType(
//      MoveNormalizedType moveNormalizedType, java.lang.Object argVal, Class<?> expectedArgClass) {
//    if (!expectedArgClass.isInstance(argVal)) {
//      throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
//    }
//  }
//
//  @NotNull
//  private Pure.Builder getPureBuilder(MoveValue moveValue) {
//    final Pure.Builder pureBuilder = new Pure.Builder();
//    try {
//      pureBuilder.value = Arrays.asList(ArrayUtils.toObject(moveValue.bcsSerialize()));
//    } catch (SerializationError e) {
//      throw new BcsSerializationException(e);
//    }
//    return pureBuilder;
//  }
//
//  private boolean isTxContext(MoveNormalizedType moveNormalizedType) {
//    if (moveNormalizedType instanceof MoveNormalizedType.MutableReferenceMoveNormalizedType) {
//      final MoveNormalizedType mutableRefMoveNormalizedType =
//          ((MoveNormalizedType.MutableReferenceMoveNormalizedType) moveNormalizedType)
//              .getMutableReference();
//      if (mutableRefMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
//      final Struct struct = ((MoveNormalizedStructType) mutableRefMoveNormalizedType).getStruct();
//        return struct.getAddress().equals("0x2")
//            && struct.getModule().equals("tx_context")
//            && struct.getName().equals("TxContext");
//      } else {
//        return false;
//      }
//    } else {
//      return false;
//    }
//  }
//
// private Optional<MoveNormalizedStructType> extractStruct(MoveNormalizedType moveNormalizedType) {
//    if (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
//      return Optional.of((MoveNormalizedStructType) moveNormalizedType);
//    } else if (moveNormalizedType instanceof MoveNormalizedType.ReferenceMoveNormalizedType) {
//      final MoveNormalizedType refMoveNormalizedType =
//          ((MoveNormalizedType.ReferenceMoveNormalizedType) moveNormalizedType).getReference();
//      if (refMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
//        return Optional.of((MoveNormalizedStructType) refMoveNormalizedType);
//      }
//    } else if (moveNormalizedType
//        instanceof MoveNormalizedType.MutableReferenceMoveNormalizedType) {
//      final MoveNormalizedType mutableRefMoveNormalizedType =
//          ((MoveNormalizedType.MutableReferenceMoveNormalizedType) moveNormalizedType)
//              .getMutableReference();
//      if (mutableRefMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
//        return Optional.of((MoveNormalizedStructType) mutableRefMoveNormalizedType);
//      }
//    }
//
//    return Optional.empty();
//  }
//
//  @NotNull
//  private List<Byte> geAddressBytes(String address) {
//    return Arrays.asList(ArrayUtils.toObject(Hex.decode(StringUtils.removeStart(address, "0x"))));
//  }
//
//  private String toAddress(List<Byte> addressBytes) {
//    return StringUtils.prependIfMissing(
//        Hex.toHexString(ArrayUtils.toPrimitive(addressBytes.toArray(new Byte[0]))), "0x");
//  }
//
//  private Tuple3<ObjectID, SequenceNumber, ObjectDigest> getObjectRef(SuiObjectRef objRef) {
//    AccountAddress.Builder coinAddressBuilder = new AccountAddress.Builder();
//    coinAddressBuilder.value = geAddressBytes(objRef.getObjectId());
//
//    ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
//    objectIdBuilder.value = coinAddressBuilder.build();
//
//    SequenceNumber.Builder sequenceNumberBuilder = new SequenceNumber.Builder();
//    sequenceNumberBuilder.value = objRef.getVersion();
//    Sha3Digest.Builder sha3DigestBuilder = new Sha3Digest.Builder();
//    sha3DigestBuilder.value = Bytes.valueOf(Base58.decode(objRef.getDigest()));
//    ObjectDigest.Builder objectDigestBuilder = new ObjectDigest.Builder();
//    objectDigestBuilder.value = sha3DigestBuilder.build();
//
//    return new Tuple3<>(
//        objectIdBuilder.build(), sequenceNumberBuilder.build(), objectDigestBuilder.build());
//  }
//
//  private io.sui.bcsgen.TypeTag toBcsTypeTag(TypeTag typeTag) {
//    if (typeTag instanceof TypeTag.SimpleType) {
//      switch ((TypeTag.SimpleType) typeTag) {
//        case u8:
//          io.sui.bcsgen.TypeTag.u8.Builder u8builder = new u8.Builder();
//          return u8builder.build();
//        case u16:
//          io.sui.bcsgen.TypeTag.u16.Builder u16builder = new u16.Builder();
//          return u16builder.build();
//        case u32:
//          io.sui.bcsgen.TypeTag.u32.Builder u32builder = new u32.Builder();
//          return u32builder.build();
//        case u64:
//          io.sui.bcsgen.TypeTag.u64.Builder u64builder = new u64.Builder();
//          return u64builder.build();
//        case bool:
//          io.sui.bcsgen.TypeTag.bool.Builder boolBuilder = new bool.Builder();
//          return boolBuilder.build();
//        case u128:
//          io.sui.bcsgen.TypeTag.u128.Builder u128builder = new u128.Builder();
//          return u128builder.build();
//        case u256:
//          io.sui.bcsgen.TypeTag.u256.Builder u256builder = new u256.Builder();
//          return u256builder.build();
//        case signer:
//          io.sui.bcsgen.TypeTag.signer.Builder signerBuilder =
//              new io.sui.bcsgen.TypeTag.signer.Builder();
//          return signerBuilder.build();
//        case address:
//          io.sui.bcsgen.TypeTag.address.Builder addressBuilder =
//              new io.sui.bcsgen.TypeTag.address.Builder();
//          return addressBuilder.build();
//        default:
//          throw new NotSupportedTypeTagException();
//      }
//    } else if (typeTag instanceof TypeTag.VectorType) {
//      io.sui.bcsgen.TypeTag.vector.Builder vectorBuilder =
//          new io.sui.bcsgen.TypeTag.vector.Builder();
//      vectorBuilder.value = toBcsTypeTag(((TypeTag.VectorType) typeTag).getTypeTag());
//      return vectorBuilder.build();
//    } else {
//      AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
//      addressBuilder.value =
//          geAddressBytes(((TypeTag.StructType) typeTag).getStructTag().getAddress());
//      Identifier.Builder moduleBuilder = new Identifier.Builder();
//      moduleBuilder.value = ((TypeTag.StructType) typeTag).getStructTag().getModule();
//      Identifier.Builder nameBuilder = new Identifier.Builder();
//      nameBuilder.value = ((TypeTag.StructType) typeTag).getStructTag().getName();
//
//      StructTag.Builder structTagBuilder = new StructTag.Builder();
//      structTagBuilder.address = addressBuilder.build();
//      structTagBuilder.module = moduleBuilder.build();
//      structTagBuilder.name = nameBuilder.build();
//      structTagBuilder.type_args =
//          ((TypeTag.StructType) typeTag)
//              .getStructTag().getTypeParams().stream()
//              .map(this::toBcsTypeTag)
//              .collect(Collectors.toList());
//      io.sui.bcsgen.TypeTag.struct.Builder structbuilder =
//          new io.sui.bcsgen.TypeTag.struct.Builder();
//      structbuilder.value = structTagBuilder.build();
//      return structbuilder.build();
//    }
//  }
// }
