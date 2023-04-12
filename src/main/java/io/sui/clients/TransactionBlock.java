/*
 * Copyright 2023 281165273grape@gmail.com
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

import static io.sui.bcsgen.CallArg.Pure.Builder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.novi.serde.Bytes;
import com.novi.serde.DeserializationError;
import com.novi.serde.SerializationError;
import com.novi.serde.Tuple3;
import io.sui.bcsgen.AccountAddress;
import io.sui.bcsgen.Argument;
import io.sui.bcsgen.CallArg;
import io.sui.bcsgen.Digest;
import io.sui.bcsgen.GasCoin;
import io.sui.bcsgen.GasData;
import io.sui.bcsgen.Identifier;
import io.sui.bcsgen.MoveValue;
import io.sui.bcsgen.MoveValue.Bool;
import io.sui.bcsgen.MoveValue.U8;
import io.sui.bcsgen.MoveValue.Vector;
import io.sui.bcsgen.ObjectArg;
import io.sui.bcsgen.ObjectArg.ImmOrOwnedObject;
import io.sui.bcsgen.ObjectArg.SharedObject;
import io.sui.bcsgen.ObjectDigest;
import io.sui.bcsgen.ObjectID;
import io.sui.bcsgen.SequenceNumber;
import io.sui.bcsgen.StructTag;
import io.sui.bcsgen.SuiAddress;
import io.sui.bcsgen.TransactionData;
import io.sui.bcsgen.TransactionData.V1;
import io.sui.bcsgen.TransactionDataV1;
import io.sui.bcsgen.TransactionExpiration;
import io.sui.bcsgen.TransactionExpiration.Epoch;
import io.sui.bcsgen.TransactionKind;
import io.sui.bcsgen.TypeTag.bool;
import io.sui.bcsgen.TypeTag.u128;
import io.sui.bcsgen.TypeTag.u16;
import io.sui.bcsgen.TypeTag.u256;
import io.sui.bcsgen.TypeTag.u32;
import io.sui.bcsgen.TypeTag.u64;
import io.sui.bcsgen.TypeTag.u8;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType.Struct;
import io.sui.models.objects.MoveNormalizedType.MutableReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.TypeMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.VectorReferenceMoveNormalizedType;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiObjectData;
import io.sui.models.objects.SuiObjectOwner;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiRawData;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;

/**
 * The type Transaction block.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class TransactionBlock {

  private static final Struct RESOLVED_ASCII_STR = new Struct();
  private static final Struct RESOLVED_UTF8_STR = new Struct();
  private static final Struct RESOLVED_SUI_ID = new Struct();
  private static final Struct RESOLVED_STD_OPTION = new Struct();
  private static final String GAS_COIN_TYPE = "0x2::coin::Coin<0x2::sui::SUI>";
  private static final String SUI_FRAMEWORK_ADDRESS = "0x0000000000000000000000000000000000000002";

  static {
    RESOLVED_ASCII_STR.setAddress("0x1");
    RESOLVED_ASCII_STR.setModule("ascii");
    RESOLVED_ASCII_STR.setName("String");

    RESOLVED_UTF8_STR.setAddress("0x1");
    RESOLVED_UTF8_STR.setModule("string");
    RESOLVED_UTF8_STR.setName("String");

    RESOLVED_SUI_ID.setAddress("0x2");
    RESOLVED_SUI_ID.setModule("object");
    RESOLVED_SUI_ID.setName("ID");

    RESOLVED_STD_OPTION.setAddress("0x2");
    RESOLVED_STD_OPTION.setModule("option");
    RESOLVED_STD_OPTION.setName("Option");
  }

  private final ProgrammableTransactionBuilder programmableTransactionBuilder;

  private final QueryClient queryClient;

  private final GasData.Builder gasBuilder;

  private SuiAddress sender;

  private TransactionExpiration transactionExpiration =
      new TransactionExpiration.None.Builder().build();

  /**
   * Instantiates a new Transaction block.
   *
   * @param queryClient the query client
   */
  public TransactionBlock(QueryClient queryClient) {
    this.queryClient = queryClient;
    this.programmableTransactionBuilder = new ProgrammableTransactionBuilder();
    this.gasBuilder = new GasData.Builder();
  }

  /**
   * Sets expiration.
   *
   * @param epoch the epoch
   */
  public void setExpiration(Long epoch) {
    if (epoch != null) {
      TransactionExpiration.Epoch.Builder builder = new Epoch.Builder();
      builder.value = epoch;
      this.transactionExpiration = builder.build();
    }
  }

  /**
   * Sets sender.
   *
   * @param sender the sender
   */
  public void setSender(String sender) {
    List<Byte> senderBytes = geAddressBytes(sender);
    final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
    senderAddressBuilder.value = senderBytes;
    this.sender = senderAddressBuilder.build();
  }

  /**
   * Sets gas data.
   *
   * @param gasPayments the gas payments
   * @param owner the owner
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the gas data
   */
  @SuppressWarnings("unchecked")
  public CompletableFuture<Void> setGasData(
      List<String> gasPayments, String owner, Long gasBudget, Long gasPrice) {
    ObjectDataOptions objectDataOptions = new ObjectDataOptions();
    CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[] gasPaymentsFuture =
        (CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[])
            gasPayments.stream()
                .map(
                    s ->
                        queryClient
                            .getObjectRef(s, objectDataOptions)
                            .thenApply(TransactionBlock.this::getObjectRef))
                .toArray(CompletableFuture[]::new);
    return CompletableFuture.allOf(gasPaymentsFuture)
        .thenAccept(
            unused -> {
              TransactionBlock.this.gasBuilder.payment =
                  Arrays.stream(gasPaymentsFuture)
                      .map(CompletableFuture::join)
                      .collect(Collectors.toList());
              if (StringUtils.isNotEmpty(owner)) {
                List<Byte> ownerBytes = geAddressBytes(owner);
                final SuiAddress.Builder ownerAddressBuilder = new SuiAddress.Builder();
                ownerAddressBuilder.value = ownerBytes;
                TransactionBlock.this.gasBuilder.owner = ownerAddressBuilder.build();
              }
              TransactionBlock.this.gasBuilder.budget = gasBudget;
              TransactionBlock.this.gasBuilder.price = gasPrice;
            });
  }

  /**
   * Build completable future.
   *
   * @return the completable future
   */
  public CompletableFuture<TransactionData> build() {
    TransactionKind.ProgrammableTransaction.Builder transactionKindBuilder =
        new TransactionKind.ProgrammableTransaction.Builder();
    transactionKindBuilder.value = this.programmableTransactionBuilder.build();

    TransactionDataV1.Builder v1builder = new TransactionDataV1.Builder();
    v1builder.kind = transactionKindBuilder.build();
    v1builder.sender = sender;
    v1builder.expiration = transactionExpiration;

    if (this.gasBuilder.owner == null) {
      this.gasBuilder.owner = sender;
    }

    CompletableFuture<Long> gasPriceFuture;
    if (this.gasBuilder.price == null) {
      gasPriceFuture = queryClient.getReferenceGasPrice();
    } else {
      gasPriceFuture = CompletableFuture.completedFuture(this.gasBuilder.price);
    }

    CompletableFuture<Long> gasBudgetFuture;
    if (this.gasBuilder.budget == null) {
      // TODO: USE DRYRUN
      gasBudgetFuture = CompletableFuture.completedFuture(Long.MAX_VALUE);
    } else {
      gasBudgetFuture = CompletableFuture.completedFuture(this.gasBuilder.budget);
    }

    return CompletableFuture.allOf(gasPriceFuture, gasPriceFuture)
        .thenCompose(
            (Function<Void, CompletableFuture<TransactionData>>)
                unused -> {
                  List<String> excludeObjects =
                      programmableTransactionBuilder.getInputs().values().stream()
                          .flatMap(
                              (Function<CallArg, Stream<ObjectArg>>)
                                  callArg -> {
                                    if (callArg instanceof CallArgObjVec) {
                                      return ((CallArgObjVec) callArg).getObjectArgs().stream();
                                    } else if (callArg instanceof CallArg.Object) {
                                      return Stream.of(((CallArg.Object) callArg).value);
                                    }

                                    return Stream.empty();
                                  })
                          .map(
                              (Function<ObjectArg, Optional<String>>)
                                  objectArg -> {
                                    if (objectArg instanceof SharedObject) {
                                      return Optional.of(
                                          toAddress(((SharedObject) objectArg).id.value.value));
                                    }

                                    if (objectArg instanceof ImmOrOwnedObject) {
                                      return Optional.of(
                                          toAddress(
                                              ((ImmOrOwnedObject) objectArg)
                                                  .value
                                                  .field0
                                                  .value
                                                  .value));
                                    }

                                    return Optional.empty();
                                  })
                          .filter(Optional::isPresent)
                          .map(Optional::get)
                          .collect(Collectors.toList());

                  Long gasBudget = gasBudgetFuture.join();
                  Long gasPrice = gasPriceFuture.join();
                  GasData.Builder gasDataBuilder = new GasData.Builder();
                  gasDataBuilder.budget = gasBudget;
                  gasDataBuilder.price = gasPrice;
                  gasDataBuilder.owner = gasBuilder.owner;

                  V1.Builder builder = new V1.Builder();
                  if (this.gasBuilder.payment == null || this.gasBuilder.payment.isEmpty()) {
                    return selectGas(
                            toAddress(gasBuilder.owner.value), gasBudget, gasPrice, excludeObjects)
                        .thenApply(
                            suiObjectRef -> {
                              Tuple3<ObjectID, SequenceNumber, ObjectDigest> gas =
                                  getObjectRef(suiObjectRef);
                              gasDataBuilder.payment = Lists.newArrayList(gas);
                              v1builder.gas_data = gasDataBuilder.build();
                              builder.value = v1builder.build();
                              return builder.build();
                            });
                  } else {
                    gasDataBuilder.payment = this.gasBuilder.payment;
                    v1builder.gas_data = gasDataBuilder.build();
                    builder.value = v1builder.build();
                    return CompletableFuture.completedFuture(builder.build());
                  }
                });
  }

  /**
   * Make move vec completable future.
   *
   * @param mutables the mutables
   * @param args the args
   * @return the completable future
   */
  @SuppressWarnings("unchecked")
  public CompletableFuture<Argument> makeMoveVec(List<Boolean> mutables, List<String> args) {
    CompletableFuture<ObjectArg>[] callArgFutures =
        (CompletableFuture<ObjectArg>[])
            Streams.zip(args.stream(), mutables.stream(), this::newObjectArg)
                .toArray(CompletableFuture[]::new);
    return CompletableFuture.allOf(callArgFutures)
        .thenApply(
            unused -> {
              List<ObjectArg> objectArgs =
                  Arrays.stream(callArgFutures)
                      .map(CompletableFuture::join)
                      .collect(Collectors.toList());
              return programmableTransactionBuilder.makeObjVec(objectArgs);
            });
  }

  /**
   * Make move vec argument.
   *
   * @param objects the objects
   * @return the argument
   */
  public Argument makeMoveVec(List<ObjectArg> objects) {
    return programmableTransactionBuilder.makeObjVec(objects);
  }

  /**
   * Split coins completable future.
   *
   * @param coin the coin
   * @param amounts the amounts
   * @return the completable future
   */
  public CompletableFuture<Argument> splitCoins(String coin, List<Long> amounts) {
    if (coin == null) {
      return CompletableFuture.completedFuture(splitCoins(amounts));
    }
    CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coinFuture =
        queryClient.getObjectRef(coin, new ObjectDataOptions()).thenApply(this::getObjectRef);
    return coinFuture.thenApply(
        objectIDSequenceNumberObjectDigestTuple3 ->
            programmableTransactionBuilder.splitCoins(coinFuture.join(), amounts));
  }

  /**
   * Split coins argument.
   *
   * @param amounts the amounts
   * @return the argument
   */
  public Argument splitCoins(List<Long> amounts) {
    return programmableTransactionBuilder.splitCoins(amounts);
  }

  /**
   * Split coins argument.
   *
   * @param coin the coin
   * @param amounts the amounts
   * @return the argument
   */
  public Argument splitCoins(Argument coin, List<Argument> amounts) {
    return programmableTransactionBuilder.splitCoins(coin, amounts);
  }

  /**
   * Merge coins argument.
   *
   * @param destinationCoin the destination coin
   * @param sourceCoins the source coins
   * @return the argument
   */
  public Argument mergeCoins(Argument destinationCoin, List<Argument> sourceCoins) {
    return programmableTransactionBuilder.mergeCoins(destinationCoin, sourceCoins);
  }

  /**
   * Merge coins completable future.
   *
   * @param destinationCoin the destination coin
   * @param sourceCoins the source coins
   * @return the completable future
   */
  @SuppressWarnings("unchecked")
  public CompletableFuture<Argument> mergeCoins(String destinationCoin, List<String> sourceCoins) {
    ObjectDataOptions objectDataOptions = new ObjectDataOptions();
    CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> destinationCoinFuture =
        queryClient.getObjectRef(destinationCoin, objectDataOptions).thenApply(this::getObjectRef);

    CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[] sourceCoinsFuture =
        (CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[])
            sourceCoins.stream()
                .map(
                    s ->
                        queryClient
                            .getObjectRef(s, new ObjectDataOptions())
                            .thenApply(TransactionBlock.this::getObjectRef))
                .toArray(CompletableFuture[]::new);
    return CompletableFuture.allOf(ArrayUtils.addAll(sourceCoinsFuture, destinationCoinFuture))
        .thenApply(
            unused -> {
              List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> scoins =
                  Arrays.stream(sourceCoinsFuture)
                      .map(CompletableFuture::join)
                      .collect(Collectors.toList());
              Tuple3<ObjectID, SequenceNumber, ObjectDigest> dcoin = destinationCoinFuture.join();
              return programmableTransactionBuilder.mergeCoins(dcoin, scoins);
            });
  }

  /**
   * Transfer objects argument.
   *
   * @param objects the objects
   * @param recipient the recipient
   * @return the argument
   */
  public Argument transferObjects(List<Argument> objects, Argument recipient) {
    return programmableTransactionBuilder.transferObjects(objects, recipient);
  }

  /**
   * Transfer objects completable future.
   *
   * @param suiObjects the sui objects
   * @param recipient the recipient
   * @return the completable future
   */
  @SuppressWarnings("unchecked")
  public CompletableFuture<Argument> transferObjects(List<String> suiObjects, String recipient) {
    List<Byte> recipientBytes = geAddressBytes(recipient);
    final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
    recipientAddressBuilder.value = recipientBytes;

    CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[] suiObjectsFuture =
        (CompletableFuture<Tuple3<ObjectID, SequenceNumber, ObjectDigest>>[])
            suiObjects.stream()
                .map(
                    s ->
                        queryClient
                            .getObjectRef(s, new ObjectDataOptions())
                            .thenApply(TransactionBlock.this::getObjectRef))
                .toArray(CompletableFuture[]::new);
    return CompletableFuture.allOf(suiObjectsFuture)
        .thenApply(
            unused -> {
              List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> objects =
                  Arrays.stream(suiObjectsFuture)
                      .map(CompletableFuture::join)
                      .collect(Collectors.toList());
              return programmableTransactionBuilder.transferObjects(
                  recipientAddressBuilder.build(), objects);
            });
  }

  /**
   * MoveCall1 argument.
   *
   * @param packageObjectId the package object id
   * @param module the module
   * @param function the function
   * @param typeArguments the type arguments
   * @param arguments the arguments
   * @return the argument
   */
  public Argument moveCall1(
      String packageObjectId,
      String module,
      String function,
      List<io.sui.models.transactions.TypeTag> typeArguments,
      List<Argument> arguments) {
    final List<io.sui.bcsgen.TypeTag> bcsTypeArguments =
        typeArguments.stream().map(this::toBcsTypeTag).collect(Collectors.toList());
    Identifier.Builder moduleBuilder = new Identifier.Builder();
    moduleBuilder.value = module;
    Identifier.Builder functionBuilder = new Identifier.Builder();
    functionBuilder.value = function;

    AccountAddress.Builder objectAddressBuilder = new AccountAddress.Builder();
    objectAddressBuilder.value = geAddressBytes(packageObjectId);
    ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
    objectIdBuilder.value = objectAddressBuilder.build();

    return programmableTransactionBuilder.programmableMoveCall(
        objectIdBuilder.build(),
        moduleBuilder.build(),
        functionBuilder.build(),
        bcsTypeArguments,
        arguments);
  }

  /**
   * Move call completable future.
   *
   * @param packageObjectId the package object id
   * @param module the module
   * @param function the function
   * @param typeArguments the type arguments
   * @param arguments the arguments
   * @return the completable future
   */
  @SuppressWarnings("unchecked")
  public CompletableFuture<Argument> moveCall(
      String packageObjectId,
      String module,
      String function,
      List<io.sui.models.transactions.TypeTag> typeArguments,
      List<?> arguments) {
    List<io.sui.bcsgen.TypeTag> bcsTypeArguments =
        typeArguments.stream().map(this::toBcsTypeTag).collect(Collectors.toList());
    return extractNormalizedFunctionParams(packageObjectId, module, function)
        .thenCompose(
            moveNormalizedTypes -> {
              if (moveNormalizedTypes.size() != arguments.size()) {
                throw new MoveCallArgSizeNotMatchException(
                    moveNormalizedTypes.size(), arguments.size());
              }
              CompletableFuture<CallArg>[] callArgFutures =
                  (CompletableFuture<CallArg>[])
                      Streams.zip(
                              moveNormalizedTypes.stream(),
                              arguments.stream(),
                              (BiFunction<MoveNormalizedType, Object, CompletableFuture<CallArg>>)
                                  this::toBcsCallArg)
                          .toArray(CompletableFuture[]::new);
              return CompletableFuture.allOf(callArgFutures)
                  .thenApply(
                      unused -> {
                        final List<CallArg> callArgs =
                            Arrays.stream(callArgFutures)
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList());

                        Identifier.Builder moduleBuilder = new Identifier.Builder();
                        moduleBuilder.value = module;
                        Identifier.Builder functionBuilder = new Identifier.Builder();
                        functionBuilder.value = function;

                        AccountAddress.Builder objectAddressBuilder = new AccountAddress.Builder();
                        objectAddressBuilder.value = geAddressBytes(packageObjectId);
                        ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
                        objectIdBuilder.value = objectAddressBuilder.build();

                        return programmableTransactionBuilder.moveCall(
                            objectIdBuilder.build(),
                            moduleBuilder.build(),
                            functionBuilder.build(),
                            bcsTypeArguments,
                            callArgs);
                      });
            });
  }

  /**
   * Publish argument.
   *
   * @param compiledModules the compiled modules
   * @param depIds the dep ids
   * @return the argument
   */
  public Argument publish(List<String> compiledModules, List<String> depIds) {
    List<List<Byte>> modules =
        compiledModules.stream()
            .map(s -> Arrays.asList(ArrayUtils.toObject(Base64.decode(s))))
            .collect(Collectors.toList());
    List<ObjectID> depIdObjects =
        depIds != null
            ? depIds.stream()
                .map(
                    objectId -> {
                      AccountAddress.Builder accountAddressBuilder = new AccountAddress.Builder();
                      accountAddressBuilder.value = geAddressBytes(objectId);
                      ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
                      objectIdBuilder.value = accountAddressBuilder.build();
                      return objectIdBuilder.build();
                    })
                .collect(Collectors.toList())
            : Lists.newArrayList();
    return programmableTransactionBuilder.publishUpgradeable(modules, depIdObjects);
  }

  /**
   * Pure argument.
   *
   * @param value the value
   * @return the argument
   */
  public Argument pure(Object value) {
    return this.programmableTransactionBuilder.pure(value);
  }

  @SuppressWarnings("unchecked")
  private CompletableFuture<SuiObjectRef> selectGas(
      String signer, Long budget, Long gasPrice, List<String> excludeObjects) {
    final ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
    ObjectDataOptions objectDataOptions = new ObjectDataOptions();
    objectDataOptions.setShowPreviousTransaction(true);
    objectDataOptions.setShowOwner(true);
    objectDataOptions.setShowContent(true);
    objectDataOptions.setShowStorageRebate(true);
    objectDataOptions.setShowBcs(true);
    objectResponseQuery.setOptions(objectDataOptions);
    return queryClient
        .getObjectsOwnedByAddress(signer, objectResponseQuery, null, null, null)
        .thenCompose(
            (Function<PaginatedObjectsResponse, CompletableFuture<SuiObjectRef>>)
                paginatedObjectsResponse -> {
                  CompletableFuture<Optional<SuiObjectRef>>[] gases =
                      (CompletableFuture<Optional<SuiObjectRef>>[])
                          paginatedObjectsResponse.getData().stream()
                              .map(
                                  objectResponse -> {
                                    if (objectResponse.getError() == null
                                        && objectResponse
                                            .getData()
                                            .getType()
                                            .equals(GAS_COIN_TYPE)) {
                                      GasCoin gasCoin;
                                      try {
                                        gasCoin =
                                            GasCoin.bcsDeserialize(
                                                Base64.decode(
                                                    ((SuiRawData.MoveObject)
                                                            objectResponse.getData().getBcs())
                                                        .getBcsBytes()));
                                      } catch (DeserializationError e) {
                                        throw new BcsSerializationException(e);
                                      }
                                      final long requiredGasAmount =
                                          BigInteger.valueOf(budget)
                                              .multiply(BigInteger.valueOf(gasPrice))
                                              .longValue();

                                      if (!excludeObjects.contains(
                                              objectResponse.getData().getObjectId())
                                          && gasCoin.value.balance.value >= requiredGasAmount) {
                                        return CompletableFuture.completedFuture(
                                            Optional.of(objectResponse.getData().getRef()));
                                      }

                                      return CompletableFuture.completedFuture(Optional.empty());
                                    }
                                    return CompletableFuture
                                        .<Optional<SuiObjectRef>>completedFuture(Optional.empty());
                                  })
                              .toArray(CompletableFuture[]::new);

                  return CompletableFuture.allOf(gases)
                      .thenApply(
                          unused -> {
                            Optional<Optional<SuiObjectRef>> selected =
                                Arrays.stream(gases)
                                    .map(CompletableFuture::join)
                                    .filter(Optional::isPresent)
                                    .findFirst();

                            if (!selected.isPresent()) {
                              throw new GasNotFoundException();
                            }

                            return selected.get().get();
                          });
                });
  }

  private CompletableFuture<List<MoveNormalizedType>> extractNormalizedFunctionParams(
      String packageObjectId, String module, String function) {
    CompletableFuture<MoveNormalizedFunction> normalizedFunction =
        this.queryClient.getNormalizedMoveFunction(packageObjectId, module, function);
    return normalizedFunction.thenCompose(
        (Function<MoveNormalizedFunction, CompletableFuture<List<MoveNormalizedType>>>)
            moveNormalizedFunction -> {
              final boolean hasTxContext =
                  moveNormalizedFunction.getParameters().size() > 0
                      && isTxContext(Iterables.getLast(moveNormalizedFunction.getParameters()));
              return CompletableFuture.completedFuture(
                  hasTxContext
                      ? moveNormalizedFunction
                          .getParameters()
                          .subList(0, moveNormalizedFunction.getParameters().size() - 1)
                      : moveNormalizedFunction.getParameters());
            });
  }

  private Optional<MoveNormalizedStructType> extractStruct(MoveNormalizedType moveNormalizedType) {
    if (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
      return Optional.of((MoveNormalizedStructType) moveNormalizedType);
    } else if (moveNormalizedType instanceof MoveNormalizedType.ReferenceMoveNormalizedType) {
      final MoveNormalizedType refMoveNormalizedType =
          ((MoveNormalizedType.ReferenceMoveNormalizedType) moveNormalizedType).getReference();
      if (refMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
        return Optional.of((MoveNormalizedStructType) refMoveNormalizedType);
      }
    } else if (moveNormalizedType
        instanceof MoveNormalizedType.MutableReferenceMoveNormalizedType) {
      final MoveNormalizedType mutableRefMoveNormalizedType =
          ((MoveNormalizedType.MutableReferenceMoveNormalizedType) moveNormalizedType)
              .getMutableReference();
      if (mutableRefMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
        return Optional.of((MoveNormalizedStructType) mutableRefMoveNormalizedType);
      }
    }

    return Optional.empty();
  }

  private boolean isTxContext(MoveNormalizedType moveNormalizedType) {
    Optional<MoveNormalizedStructType> structTypeOpt = extractStruct(moveNormalizedType);
    return structTypeOpt
        .filter(
            moveNormalizedStructType ->
                moveNormalizedStructType.getStruct().getAddress().equals("0x2")
                    && moveNormalizedStructType.getStruct().getModule().equals("tx_context")
                    && moveNormalizedStructType.getStruct().getName().equals("TxContext"))
        .isPresent();
  }

  @NotNull private Builder getPureBuilder(MoveValue moveValue) {
    final Builder pureBuilder = new Builder();
    try {
      pureBuilder.value = Arrays.asList(ArrayUtils.toObject(moveValue.bcsSerialize()));
    } catch (SerializationError e) {
      throw new BcsSerializationException(e);
    }
    return pureBuilder;
  }

  private Optional<MoveValue> toPureMoveValue(
      MoveNormalizedType moveNormalizedType, java.lang.Object argVal) {
    final MoveValue moveValue;
    if (moveNormalizedType instanceof MoveNormalizedType.TypeMoveNormalizedType) {
      MoveNormalizedType.TypeMoveNormalizedType argType =
          (MoveNormalizedType.TypeMoveNormalizedType) moveNormalizedType;
      switch (argType) {
        case U8:
          checkArgType(moveNormalizedType, argVal, Byte.class);
          MoveValue.U8.Builder u8Builder = new MoveValue.U8.Builder();
          u8Builder.value = (Byte) argVal;
          moveValue = u8Builder.build();
          break;
        case U16:
          checkArgType(moveNormalizedType, argVal, Short.class);

          MoveValue.U16.Builder u16Builder = new MoveValue.U16.Builder();
          u16Builder.value = (Short) argVal;
          moveValue = u16Builder.build();
          break;
        case U32:
          checkArgType(moveNormalizedType, argVal, Integer.class);

          MoveValue.U32.Builder u32Builder = new MoveValue.U32.Builder();
          u32Builder.value = (Integer) argVal;
          moveValue = u32Builder.build();
          break;
        case U64:
          checkArgType(moveNormalizedType, argVal, Long.class);

          MoveValue.U64.Builder u64Builder = new MoveValue.U64.Builder();
          u64Builder.value = (Long) argVal;
          moveValue = u64Builder.build();
          break;
        case U128:
          checkArgType(moveNormalizedType, argVal, BigInteger.class);

          MoveValue.U128.Builder u128Builder = new MoveValue.U128.Builder();
          u128Builder.value = (BigInteger) argVal;
          moveValue = u128Builder.build();
          break;
        case U256:
          checkArgType(moveNormalizedType, argVal, byte[].class);

          MoveValue.U256.Builder u256Builder = new MoveValue.U256.Builder();
          u256Builder.value = Arrays.asList(ArrayUtils.toObject((byte[]) argVal));
          moveValue = u256Builder.build();
          break;
        case Bool:
          checkArgType(moveNormalizedType, argVal, Boolean.class);

          MoveValue.Bool.Builder boolBuilder = new Bool.Builder();
          boolBuilder.value = (Boolean) argVal;
          moveValue = boolBuilder.build();
          break;
        case Address:
          checkArgType(moveNormalizedType, argVal, String.class);

          AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
          addressBuilder.value = geAddressBytes((String) argVal);
          MoveValue.Address.Builder addressValueBuilder = new MoveValue.Address.Builder();
          addressValueBuilder.value = addressBuilder.build();
          moveValue = addressValueBuilder.build();
          break;
        default:
          throw new NotSupportedMoveNormalizedTypeException();
      }

      return Optional.of(moveValue);
    }

    if (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
      final Struct argStruct =
          ((MoveNormalizedType.MoveNormalizedStructType) moveNormalizedType).getStruct();
      if (argStruct.equals(RESOLVED_ASCII_STR)) {
        checkArgType(moveNormalizedType, argVal, String.class);

        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
        vectorBuilder.value =
            Arrays.stream(
                    ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.US_ASCII)))
                .map(
                    (Function<Byte, MoveValue>)
                        b -> {
                          U8.Builder u8Builder = new U8.Builder();
                          u8Builder.value = b;
                          return u8Builder.build();
                        })
                .collect(Collectors.toList());
        moveValue = vectorBuilder.build();

        return Optional.of(moveValue);
      } else if (argStruct.equals(RESOLVED_UTF8_STR)) {
        checkArgType(moveNormalizedType, argVal, String.class);

        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
        vectorBuilder.value =
            Arrays.stream(ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.UTF_8)))
                .map(
                    (Function<Byte, MoveValue>)
                        b -> {
                          U8.Builder u8Builder = new U8.Builder();
                          u8Builder.value = b;
                          return u8Builder.build();
                        })
                .collect(Collectors.toList());
        moveValue = vectorBuilder.build();

        return Optional.of(moveValue);
      } else if (argStruct.equals(RESOLVED_SUI_ID)) {
        checkArgType(moveNormalizedType, argVal, String.class);

        AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
        addressBuilder.value = geAddressBytes((String) argVal);
        MoveValue.Address.Builder addressValueBuilder = new MoveValue.Address.Builder();
        addressValueBuilder.value = addressBuilder.build();
        moveValue = addressValueBuilder.build();

        return Optional.of(moveValue);
      } else if (argStruct.equals(RESOLVED_STD_OPTION)) {
        MoveNormalizedType.VectorReferenceMoveNormalizedType optionToVec =
            new VectorReferenceMoveNormalizedType();
        optionToVec.setVector(argStruct.getTypeArguments().get(0));
        toPureMoveValue(optionToVec, argVal);
      }
    }

    if (moveNormalizedType instanceof MoveNormalizedType.VectorReferenceMoveNormalizedType) {
      if (((MoveNormalizedType.VectorReferenceMoveNormalizedType) moveNormalizedType).getVector()
          == TypeMoveNormalizedType.U8) {
        checkArgType(moveNormalizedType, argVal, String.class);

        MoveValue.Vector.Builder vectorBuilder = new MoveValue.Vector.Builder();
        vectorBuilder.value =
            Arrays.stream(ArrayUtils.toObject(((String) argVal).getBytes(StandardCharsets.UTF_8)))
                .map(
                    (Function<Byte, MoveValue>)
                        b -> {
                          U8.Builder u8Builder = new U8.Builder();
                          u8Builder.value = b;
                          return u8Builder.build();
                        })
                .collect(Collectors.toList());
        moveValue = vectorBuilder.build();

        return Optional.of(moveValue);
      }

      if (!(argVal instanceof List<?>)) {
        throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
      }

      List<?> objects = (List<?>) argVal;
      List<MoveValue> pureMoveValues =
          objects.stream()
              .map(
                  object ->
                      toPureMoveValue(
                          ((MoveNormalizedType.VectorReferenceMoveNormalizedType)
                                  moveNormalizedType)
                              .getVector(),
                          object))
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList());

      if (pureMoveValues.isEmpty()) {
        // ObjVec TYPE will be handled later
        return Optional.empty();
      }

      MoveValue.Vector.Builder moveValueVectorBuilder = new Vector.Builder();
      moveValueVectorBuilder.value = pureMoveValues;

      return Optional.of(moveValueVectorBuilder.build());
    }

    return Optional.empty();
  }

  @SuppressWarnings("unchecked")
  private CompletableFuture<CallArg> toBcsCallArg(
      MoveNormalizedType moveNormalizedType, java.lang.Object argVal) {
    final Optional<MoveValue> pureMoveValue = toPureMoveValue(moveNormalizedType, argVal);
    if (pureMoveValue.isPresent()) {
      final CallArg.Pure.Builder pureBuilder = getPureBuilder(pureMoveValue.get());
      return CompletableFuture.completedFuture(pureBuilder.build());
    }

    if (moveNormalizedType instanceof MoveNormalizedType.VectorReferenceMoveNormalizedType) {

      if (((MoveNormalizedType.VectorReferenceMoveNormalizedType) moveNormalizedType).getVector()
          instanceof MoveNormalizedType.MoveNormalizedStructType) {
        checkArgType(moveNormalizedType, argVal, List.class);

        CompletableFuture<ObjectArg>[] objectArgFutures =
            (CompletableFuture<ObjectArg>[])
                Arrays.stream((String[]) argVal)
                    .map((String objectId) -> newObjectArg(objectId, moveNormalizedType))
                    .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(objectArgFutures)
            .thenApply(
                unused -> {
                  final CallArgObjVec callArgObjVec = new CallArgObjVec();
                  callArgObjVec.setObjectArgs(
                      Arrays.stream(objectArgFutures)
                          .map(CompletableFuture::join)
                          .collect(Collectors.toList()));
                  return callArgObjVec;
                });
      }
    }

    final Optional<Struct> structOptional =
        extractStruct(moveNormalizedType).map(MoveNormalizedStructType::getStruct);

    if (structOptional.isPresent()
        || (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedTypeParameterType)) {
      checkArgType(moveNormalizedType, argVal, String.class);

      return newObjectArg((String) argVal, moveNormalizedType)
          .thenApply(
              objectArg -> {
                final io.sui.bcsgen.CallArg.Object.Builder objectBuilder =
                    new CallArg.Object.Builder();
                objectBuilder.value = objectArg;
                return objectBuilder.build();
              });
    }

    throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
  }

  private CompletableFuture<ObjectArg> newObjectArg(String objectId, boolean mutable) {
    ObjectDataOptions queryOptions = new ObjectDataOptions();
    queryOptions.setShowOwner(true);
    return queryClient
        .getObject(objectId, queryOptions)
        .thenApply(
            objectResponse -> {
              if (objectResponse.getError() != null) {
                throw new SuiObjectNotFoundException();
              }
              final SuiObjectData objectData = objectResponse.getData();
              final SuiObjectOwner owner = objectData.getOwner();
              if (owner instanceof SuiObjectOwner.SharedOwner) {
                AccountAddress.Builder accountAddressBuilder = new AccountAddress.Builder();
                accountAddressBuilder.value = geAddressBytes(objectId);
                ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
                objectIdBuilder.value = accountAddressBuilder.build();
                SequenceNumber.Builder seqBuilder = new SequenceNumber.Builder();
                seqBuilder.value =
                    ((SuiObjectOwner.SharedOwner) owner).getShared().getInitial_shared_version();
                SharedObject.Builder sharedObjectBuilder = new SharedObject.Builder();
                sharedObjectBuilder.id = objectIdBuilder.build();
                sharedObjectBuilder.initial_shared_version = seqBuilder.build();
                if (mutable) {
                  sharedObjectBuilder.mutable = true;
                }

                return sharedObjectBuilder.build();
              }

              ImmOrOwnedObject.Builder immOrOwnedObjectBuilder = new ImmOrOwnedObject.Builder();
              immOrOwnedObjectBuilder.value = getObjectRef(objectResponse.getObjectRef());
              return immOrOwnedObjectBuilder.build();
            });
  }

  private CompletableFuture<ObjectArg> newObjectArg(
      String objectId, MoveNormalizedType moveNormalizedType) {
    return newObjectArg(objectId, moveNormalizedType instanceof MutableReferenceMoveNormalizedType);
  }

  private void checkArgType(
      MoveNormalizedType moveNormalizedType, java.lang.Object argVal, Class<?> expectedArgClass) {
    if (!expectedArgClass.isInstance(argVal)) {
      throw new CallArgTypeMismatchException(moveNormalizedType, argVal.getClass());
    }
  }

  /**
   * Ge address bytes list.
   *
   * @param address the address
   * @return the list
   */
  @NotNull public List<Byte> geAddressBytes(String address) {
    return Arrays.asList(
        ArrayUtils.toObject(
            Hex.decode(StringUtils.leftPad(StringUtils.removeStart(address, "0x"), 64, "0"))));
  }

  /**
   * To address string.
   *
   * @param addressBytes the address bytes
   * @return the string
   */
  public String toAddress(List<Byte> addressBytes) {
    return StringUtils.prependIfMissing(
        Hex.toHexString(ArrayUtils.toPrimitive(addressBytes.toArray(new Byte[0]))), "0x");
  }

  private Tuple3<ObjectID, SequenceNumber, ObjectDigest> getObjectRef(SuiObjectRef objRef) {
    AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
    addressBuilder.value = geAddressBytes(objRef.getObjectId());

    ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
    objectIdBuilder.value = addressBuilder.build();

    SequenceNumber.Builder sequenceNumberBuilder = new SequenceNumber.Builder();
    sequenceNumberBuilder.value = objRef.getVersion();
    Digest.Builder digestBuilder = new Digest.Builder();
    digestBuilder.value = Bytes.valueOf(Base58.decode(objRef.getDigest()));
    ObjectDigest.Builder objectDigestBuilder = new ObjectDigest.Builder();
    objectDigestBuilder.value = digestBuilder.build();

    return new Tuple3<>(
        objectIdBuilder.build(), sequenceNumberBuilder.build(), objectDigestBuilder.build());
  }

  private io.sui.bcsgen.TypeTag toBcsTypeTag(io.sui.models.transactions.TypeTag typeTag) {
    if (typeTag instanceof io.sui.models.transactions.TypeTag.SimpleType) {
      switch ((io.sui.models.transactions.TypeTag.SimpleType) typeTag) {
        case u8:
          io.sui.bcsgen.TypeTag.u8.Builder u8builder = new u8.Builder();
          return u8builder.build();
        case u16:
          io.sui.bcsgen.TypeTag.u16.Builder u16builder = new u16.Builder();
          return u16builder.build();
        case u32:
          io.sui.bcsgen.TypeTag.u32.Builder u32builder = new u32.Builder();
          return u32builder.build();
        case u64:
          io.sui.bcsgen.TypeTag.u64.Builder u64builder = new u64.Builder();
          return u64builder.build();
        case bool:
          io.sui.bcsgen.TypeTag.bool.Builder boolBuilder = new bool.Builder();
          return boolBuilder.build();
        case u128:
          io.sui.bcsgen.TypeTag.u128.Builder u128builder = new u128.Builder();
          return u128builder.build();
        case u256:
          io.sui.bcsgen.TypeTag.u256.Builder u256builder = new u256.Builder();
          return u256builder.build();
        case signer:
          io.sui.bcsgen.TypeTag.signer.Builder signerBuilder =
              new io.sui.bcsgen.TypeTag.signer.Builder();
          return signerBuilder.build();
        case address:
          io.sui.bcsgen.TypeTag.address.Builder addressBuilder =
              new io.sui.bcsgen.TypeTag.address.Builder();
          return addressBuilder.build();
        default:
          throw new NotSupportedTypeTagException();
      }
    } else if (typeTag instanceof io.sui.models.transactions.TypeTag.VectorType) {
      io.sui.bcsgen.TypeTag.vector.Builder vectorBuilder =
          new io.sui.bcsgen.TypeTag.vector.Builder();
      vectorBuilder.value =
          toBcsTypeTag(((io.sui.models.transactions.TypeTag.VectorType) typeTag).getTypeTag());
      return vectorBuilder.build();
    } else {
      AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
      addressBuilder.value =
          geAddressBytes(
              ((io.sui.models.transactions.TypeTag.StructType) typeTag)
                  .getStructTag()
                  .getAddress());
      Identifier.Builder moduleBuilder = new Identifier.Builder();
      moduleBuilder.value =
          ((io.sui.models.transactions.TypeTag.StructType) typeTag).getStructTag().getModule();
      Identifier.Builder nameBuilder = new Identifier.Builder();
      nameBuilder.value =
          ((io.sui.models.transactions.TypeTag.StructType) typeTag).getStructTag().getName();

      StructTag.Builder structTagBuilder = new StructTag.Builder();
      structTagBuilder.address = addressBuilder.build();
      structTagBuilder.module = moduleBuilder.build();
      structTagBuilder.name = nameBuilder.build();
      structTagBuilder.type_args =
          ((io.sui.models.transactions.TypeTag.StructType) typeTag)
              .getStructTag().getTypeParams().stream()
                  .map(this::toBcsTypeTag)
                  .collect(Collectors.toList());
      io.sui.bcsgen.TypeTag.struct.Builder structBuilder =
          new io.sui.bcsgen.TypeTag.struct.Builder();
      structBuilder.value = structTagBuilder.build();
      return structBuilder.build();
    }
  }
}
