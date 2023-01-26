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


import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.novi.serde.Bytes;
import com.novi.serde.Tuple3;
import io.sui.bcsgen.AccountAddress;
import io.sui.bcsgen.CallArg;
import io.sui.bcsgen.Identifier;
import io.sui.bcsgen.ObjectDigest;
import io.sui.bcsgen.ObjectID;
import io.sui.bcsgen.SequenceNumber;
import io.sui.bcsgen.SingleTransactionKind;
import io.sui.bcsgen.StructTag;
import io.sui.bcsgen.SuiAddress;
import io.sui.bcsgen.TransactionData;
import io.sui.bcsgen.TransactionKind.Single;
import io.sui.bcsgen.TransferObject;
import io.sui.bcsgen.TransferSui.Builder;
import io.sui.bcsgen.TypeTag.bool;
import io.sui.bcsgen.TypeTag.signer;
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
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.SuiData.MoveObject;
import io.sui.models.objects.SuiObject;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.transactions.RPCTransactionRequestParams;
import io.sui.models.transactions.TransactionBytes;
import io.sui.models.transactions.TypeTag;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA. Author: kaichen Date: 2023/1/21 Time: 17:39
 */
public class LocalTransactionBuilder implements TransactionBuilder {

  private final QueryClient queryClient;

  public LocalTransactionBuilder(QueryClient queryClient) {
    this.queryClient = queryClient;
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoin(
      String signer, String coin, List<Long> splitAmounts, String gas, long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoinEqual(
      String signer, String coin, long splitCount, String gas, long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> mergeCoins(
      String signer, String primaryCoin, String toMergeCoin, String gas, long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> pay(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      String gas,
      long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> paySui(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> payAllSui(
      String signer, List<String> inputCoins, String recipient, long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> transferSui(
      String signer, String coin, long gasBudget, String recipient, long amount) {
    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
    CompletableFuture<SuiObjectRef> objRefFuture = queryClient.getObjectRef(coin);
    return CompletableFuture.allOf(refGasPriceFuture, objRefFuture)
        .thenApply(
            unused -> {
              final Long refGasPrice = refGasPriceFuture.join();
              final SuiObjectRef objRef = objRefFuture.join();

              List<Byte> recipientBytes = geAddressBytes(recipient);
              final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
              recipientAddressBuilder.value = recipientBytes;
              final Builder transferSuiBuilder = new Builder();
              transferSuiBuilder.recipient = recipientAddressBuilder.build();
              transferSuiBuilder.amount = Optional.of(amount);

              List<Byte> senderBytes = geAddressBytes(signer);
              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
              senderAddressBuilder.value = senderBytes;

              final SingleTransactionKind.TransferSui.Builder transferSuiKindBuilder =
                  new SingleTransactionKind.TransferSui.Builder();
              transferSuiKindBuilder.value = transferSuiBuilder.build();

              final Single.Builder singleKindBuilder = new Single.Builder();
              singleKindBuilder.value = transferSuiKindBuilder.build();

              final TransactionData.Builder transactionDataBuilder = new TransactionData.Builder();
              transactionDataBuilder.gas_budget = gasBudget;
              transactionDataBuilder.kind = singleKindBuilder.build();
              transactionDataBuilder.gas_price = refGasPrice;
              transactionDataBuilder.gas_payment = getObjectRef(objRef);
              transactionDataBuilder.sender = senderAddressBuilder.build();

              final TransactionBytes transactionBytes = new TransactionBytes();
              transactionBytes.setLocalTxBytes(transactionDataBuilder.build());
              transactionBytes.setGas(objRef);
              return transactionBytes;
            });
  }

  @Override
  public CompletableFuture<TransactionBytes> transferObject(
      String signer, String suiObject, String recipient, String gas, long gasBudget) {
    CompletableFuture<SuiObjectRef> objRefFuture = queryClient.getObjectRef(suiObject);
    CompletableFuture<SuiObjectRef> gasRefFuture = selectGas(signer, gas, gasBudget,
        Lists.newArrayList(suiObject));
    CompletableFuture<Long> refGasPriceFuture = queryClient.getReferenceGasPrice();
    return CompletableFuture.allOf(refGasPriceFuture, objRefFuture, gasRefFuture)
        .thenApply(
            unused -> {
              final Long refGasPrice = refGasPriceFuture.join();
              final SuiObjectRef objRef = objRefFuture.join();
              final SuiObjectRef gasRef = gasRefFuture.join();

              List<Byte> recipientBytes =
                  geAddressBytes(recipient);
              final SuiAddress.Builder recipientAddressBuilder = new SuiAddress.Builder();
              recipientAddressBuilder.value = recipientBytes;

              final TransferObject.Builder transferObjectBuilder = new TransferObject.Builder();
              transferObjectBuilder.recipient = recipientAddressBuilder.build();
              transferObjectBuilder.object_ref = getObjectRef(objRef);

              List<Byte> senderBytes = geAddressBytes(signer);
              final SuiAddress.Builder senderAddressBuilder = new SuiAddress.Builder();
              senderAddressBuilder.value = senderBytes;

              final SingleTransactionKind.TransferObject.Builder transferObjectKindBuilder =
                  new SingleTransactionKind.TransferObject.Builder();
              transferObjectKindBuilder.value = transferObjectBuilder.build();

              final Single.Builder singleKindBuilder = new Single.Builder();
              singleKindBuilder.value = transferObjectKindBuilder.build();

              final TransactionData.Builder transactionDataBuilder = new TransactionData.Builder();
              transactionDataBuilder.gas_budget = gasBudget;
              transactionDataBuilder.kind = singleKindBuilder.build();
              transactionDataBuilder.gas_price = refGasPrice;
              transactionDataBuilder.gas_payment = getObjectRef(gasRef);
              transactionDataBuilder.sender = senderAddressBuilder.build();

              final TransactionBytes transactionBytes = new TransactionBytes();
              transactionBytes.setLocalTxBytes(transactionDataBuilder.build());
              transactionBytes.setGas(gasRef);
              return transactionBytes;
            });
  }

  @NotNull
  private List<Byte> geAddressBytes(String recipient) {
    return Arrays.asList(
        ArrayUtils.toObject(Hex.decode(StringUtils.removeStart(recipient, "0x"))));
  }

  private Tuple3<ObjectID, SequenceNumber, ObjectDigest> getObjectRef(SuiObjectRef objRef) {
    AccountAddress.Builder coinAddressBuilder = new AccountAddress.Builder();
    coinAddressBuilder.value = geAddressBytes(objRef.getObjectId());

    ObjectID.Builder objectIdBuilder = new ObjectID.Builder();
    objectIdBuilder.value = coinAddressBuilder.build();

    SequenceNumber.Builder sequenceNumberBuilder = new SequenceNumber.Builder();
    sequenceNumberBuilder.value = objRef.getVersion();
    ObjectDigest.Builder objectDigestBuilder = new ObjectDigest.Builder();
    objectDigestBuilder.value = Bytes.valueOf(Base64.decode(objRef.getDigest()));

    return new Tuple3<>(
        objectIdBuilder.build(),
        sequenceNumberBuilder.build(),
        objectDigestBuilder.build());
  }

  @Override
  public CompletableFuture<TransactionBytes> batchTransaction(
      String signer,
      List<RPCTransactionRequestParams> batchTransactionParams,
      String gas,
      long gasBudget) {
    return null;
  }

  @Override
  public CompletableFuture<TransactionBytes> moveCall(
      String signer,
      String packageObjectId,
      String module,
      String function,
      List<TypeTag> typeArguments,
      List<?> arguments,
      String gas,
      long gasBudget) {
    List<io.sui.bcsgen.TypeTag> bcsTypeArguments = typeArguments.stream()
        .map(this::toBcsTypeTag).collect(Collectors.toList());
    return null;
  }

  private io.sui.bcsgen.TypeTag toBcsTypeTag(TypeTag typeTag) {
    if (typeTag instanceof TypeTag.SimpleType) {
      switch ((TypeTag.SimpleType) typeTag) {
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
          io.sui.bcsgen.TypeTag.bool.Builder boolbuilder = new bool.Builder();
          return boolbuilder.build();
        case u128:
          io.sui.bcsgen.TypeTag.u128.Builder u128builder = new u128.Builder();
          return u128builder.build();
        case u256:
          io.sui.bcsgen.TypeTag.u256.Builder u256builder = new u256.Builder();
          return u256builder.build();
        case signer:
          io.sui.bcsgen.TypeTag.signer.Builder signerbuilder = new io.sui.bcsgen.TypeTag.signer.Builder();
          return signerbuilder.build();
        case address:
          io.sui.bcsgen.TypeTag.address.Builder addressbuilder = new io.sui.bcsgen.TypeTag.address.Builder();
          return addressbuilder.build();
        default:
          throw new NoSupportedTypeTagException();
      }
    } else if (typeTag instanceof TypeTag.VectorType) {
      io.sui.bcsgen.TypeTag.vector.Builder vectorbuilder = new io.sui.bcsgen.TypeTag.vector.Builder();
      vectorbuilder.value = toBcsTypeTag(((TypeTag.VectorType) typeTag).getTypeTag());
      return vectorbuilder.build();
    } else {
      AccountAddress.Builder addressBuilder = new AccountAddress.Builder();
      addressBuilder.value = geAddressBytes(
          ((TypeTag.StructType) typeTag).getStructTag().getAddress());
      Identifier.Builder moduleBuilder = new Identifier.Builder();
      moduleBuilder.value = ((TypeTag.StructType) typeTag).getStructTag().getModule();
      Identifier.Builder nameBuilder = new Identifier.Builder();
      nameBuilder.value = ((TypeTag.StructType) typeTag).getStructTag().getName();

      StructTag.Builder structtagbuilder = new StructTag.Builder();
      structtagbuilder.address = addressBuilder.build();
      structtagbuilder.module = moduleBuilder.build();
      structtagbuilder.name = nameBuilder.build();
      structtagbuilder.type_args = ((TypeTag.StructType) typeTag).getStructTag().getTypeParams()
          .stream().map(
              this::toBcsTypeTag).collect(Collectors.toList());
      io.sui.bcsgen.TypeTag.struct.Builder
          structbuilder = new io.sui.bcsgen.TypeTag.struct.Builder();
      structbuilder.value = structtagbuilder.build();
      return structbuilder.build();
    }
  }

  @Override
  public CompletableFuture<TransactionBytes> publish(
      String signer, List<String> compiledModules, String gas, long gasBudget) {
    return null;
  }

  public CompletableFuture<SuiObjectRef> selectGas(
      String signer, String inputGas, long budget, List<String> excludeObjects) {
    if (StringUtils.isNotEmpty(inputGas)) {
      return queryClient.getObjectRef(inputGas);
    } else {
      return queryClient
          .getObjectsOwnedByAddress(signer)
          .thenCompose(
              (Function<List<SuiObjectInfo>, CompletableFuture<SuiObjectRef>>)
                  suiObjectInfos -> {
                    List<CompletableFuture<Optional<SuiObjectRef>>> gases;
                    gases =
                        suiObjectInfos.stream()
                            .map(
                                suiObjectInfo -> {
                                  if (suiObjectInfo
                                      .getType()
                                      .equals("0x2::coin::Coin<0x2::sui::SUI>")) {
                                    return queryClient
                                        .getObject(suiObjectInfo.getObjectId())
                                        .thenCompose(
                                            (Function<
                                                ObjectResponse,
                                                CompletableFuture<Optional<SuiObjectRef>>>)
                                                objectResponse -> {
                                                  final SuiObject suiObject =
                                                      (SuiObject) objectResponse.getDetails();
                                                  final Long balance = Long.valueOf((String)
                                                      ((MoveObject) suiObject.getData())
                                                          .getFields()
                                                          .get("balance"));
                                                  if (!excludeObjects.contains(
                                                      suiObjectInfo.getObjectId())
                                                      && balance
                                                      >= budget) {
                                                    return CompletableFuture.completedFuture(
                                                        Optional.of(suiObject.getReference()));
                                                  }

                                                  return CompletableFuture.completedFuture(
                                                      Optional.empty());
                                                });
                                  }
                                  return CompletableFuture.<Optional<SuiObjectRef>>completedFuture(
                                      Optional.empty());
                                })
                            .collect(Collectors.toList());

                    return CompletableFuture.allOf(gases.toArray(new CompletableFuture[0]))
                        .thenApply(
                            unused -> {
                              Optional<Optional<SuiObjectRef>> selected =
                                  gases.stream()
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
  }

  private CompletableFuture<List<MoveNormalizedType>> extractNormalizedFunctionParams(
      String packageObjectId,
      String module,
      String function) {
    CompletableFuture<MoveNormalizedFunction> normalizedFunction = this.queryClient.getNormalizedMoveFunction(
        packageObjectId, module, function);
    return normalizedFunction.thenCompose(
        (Function<MoveNormalizedFunction, CompletableFuture<List<MoveNormalizedType>>>) moveNormalizedFunction -> {
          final boolean hasTxContext =
              moveNormalizedFunction.getParameters().size() > 0 && isTxContext(Iterables.getLast(
                  moveNormalizedFunction.getParameters()));
          return CompletableFuture.completedFuture(
              hasTxContext ? moveNormalizedFunction.getParameters()
                  .subList(0, moveNormalizedFunction.getParameters().size() - 1)
                  : moveNormalizedFunction.getParameters());
        });
  }

  private CompletableFuture<CallArg> toBcsCallArg(MoveNormalizedType)

  private boolean isTxContext(MoveNormalizedType moveNormalizedType) {
    if (moveNormalizedType instanceof MoveNormalizedType.MutableReferenceMoveNormalizedType) {
      final MoveNormalizedType mutableRefMoveNormalizedType = ((MoveNormalizedType.MutableReferenceMoveNormalizedType) moveNormalizedType).getMutableReference();
      if (mutableRefMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
        final Struct struct = ((MoveNormalizedStructType) mutableRefMoveNormalizedType).getStruct();
        return struct.getAddress()
            .equals("0x2") && struct.getModule().equals("tx_context") && struct.getName()
            .equals("TxContext");
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  private Optional<MoveNormalizedStructType> extractStruct(MoveNormalizedType moveNormalizedType) {
    if (moveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
      return Optional.of((MoveNormalizedStructType) moveNormalizedType);
    } else if (moveNormalizedType instanceof MoveNormalizedType.ReferenceMoveNormalizedType) {
      final MoveNormalizedType refMoveNormalizedType = ((MoveNormalizedType.ReferenceMoveNormalizedType) moveNormalizedType).getReference();
      if (refMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
        return Optional.of((MoveNormalizedStructType) refMoveNormalizedType);
      }
    } else if (moveNormalizedType instanceof MoveNormalizedType.MutableReferenceMoveNormalizedType) {
      final MoveNormalizedType mutableRefMoveNormalizedType = ((MoveNormalizedType.MutableReferenceMoveNormalizedType) moveNormalizedType).getMutableReference();
      if (mutableRefMoveNormalizedType instanceof MoveNormalizedType.MoveNormalizedStructType) {
        return Optional.of((MoveNormalizedStructType) mutableRefMoveNormalizedType);
      }
    }

    return Optional.empty();
  }
}
