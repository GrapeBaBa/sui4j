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


import com.google.common.collect.Lists;
import com.novi.bcs.BcsSerializer;
import com.novi.serde.SerializationError;
import com.novi.serde.Serializer;
import com.novi.serde.Tuple3;
import io.sui.bcsgen.Argument;
import io.sui.bcsgen.Argument.GasCoin;
import io.sui.bcsgen.Argument.Input;
import io.sui.bcsgen.Argument.NestedResult;
import io.sui.bcsgen.Argument.Result;
import io.sui.bcsgen.CallArg;
import io.sui.bcsgen.CallArg.Object;
import io.sui.bcsgen.CallArg.Pure;
import io.sui.bcsgen.Command;
import io.sui.bcsgen.Command.MakeMoveVec;
import io.sui.bcsgen.Command.MergeCoins;
import io.sui.bcsgen.Command.MoveCall;
import io.sui.bcsgen.Command.Publish;
import io.sui.bcsgen.Command.SplitCoins;
import io.sui.bcsgen.Command.TransferObjects;
import io.sui.bcsgen.GasData;
import io.sui.bcsgen.Identifier;
import io.sui.bcsgen.MoveValue;
import io.sui.bcsgen.ObjectArg;
import io.sui.bcsgen.ObjectArg.ImmOrOwnedObject;
import io.sui.bcsgen.ObjectArg.SharedObject;
import io.sui.bcsgen.ObjectDigest;
import io.sui.bcsgen.ObjectID;
import io.sui.bcsgen.ProgrammableMoveCall;
import io.sui.bcsgen.ProgrammableTransaction;
import io.sui.bcsgen.ProgrammableTransaction.Builder;
import io.sui.bcsgen.SequenceNumber;
import io.sui.bcsgen.SuiAddress;
import io.sui.bcsgen.TransactionData;
import io.sui.bcsgen.TransactionData.V1;
import io.sui.bcsgen.TransactionDataV1;
import io.sui.bcsgen.TransactionExpiration;
import io.sui.bcsgen.TransactionKind;
import io.sui.bcsgen.TypeTag;
import io.sui.clients.BuilderArg.ForcedNonUniquePureBuilderArg;
import io.sui.clients.BuilderArg.ObjectBuilderArg;
import io.sui.clients.BuilderArg.PureBuilderArg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;

/**
 * The type Programmable transaction builder.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class ProgrammableTransactionBuilder {

  private LinkedHashMap<BuilderArg, CallArg> inputs = new LinkedHashMap<>();

  private List<Command> commands = new ArrayList<>();

  /**
   * Gets inputs.
   *
   * @return the inputs
   */
  public LinkedHashMap<BuilderArg, CallArg> getInputs() {
    return inputs;
  }

  /**
   * Build programmable transaction.
   *
   * @return the programmable transaction
   */
  public ProgrammableTransaction build() {
    ProgrammableTransaction.Builder builder = new Builder();
    builder.inputs = Lists.newArrayList(inputs.values());
    builder.commands = commands;
    return builder.build();
  }

  /**
   * Pure bytes argument.
   *
   * @param bytes the bytes
   * @param forceSeparate the force separate
   * @return the argument
   */
  public Argument pureBytes(byte[] bytes, boolean forceSeparate) {
    final BuilderArg arg =
        forceSeparate
            ? new ForcedNonUniquePureBuilderArg(this.inputs.size())
            : new PureBuilderArg(bytes);
    CallArg.Pure.Builder builder = new Pure.Builder();
    builder.value = Arrays.asList(ArrayUtils.toObject(bytes));
    this.inputs.put(arg, builder.build());
    Argument.Input.Builder argBuilder = new Input.Builder();
    argBuilder.value = (short) (this.inputs.size() - 1);
    return argBuilder.build();
  }

  /**
   * Obj argument.
   *
   * @param objectArg the object arg
   * @return the argument
   */
  public Argument obj(ObjectArg objectArg) {
    ObjectID objectId;
    if (objectArg instanceof ImmOrOwnedObject) {
      objectId = ((ImmOrOwnedObject) objectArg).value.field0;
    } else {
      objectId = ((SharedObject) objectArg).id;
    }

    ObjectBuilderArg search = new ObjectBuilderArg(objectId);
    ObjectArg input = getOrCreate(objectArg, search);

    CallArg.Object.Builder objectBuilder = new Object.Builder();
    objectBuilder.value = input;
    this.inputs.put(search, objectBuilder.build());
    Argument.Input.Builder argBuilder = new Input.Builder();
    argBuilder.value = (short) (this.inputs.size() - 1);
    return argBuilder.build();
  }

  /**
   * Input argument.
   *
   * @param callArg the call arg
   * @return the argument
   */
  public Argument input(CallArg callArg) {
    if (callArg instanceof CallArg.Pure) {
      return pureBytes(ArrayUtils.toPrimitive(((Pure) callArg).value.toArray(new Byte[0])), false);
    } else {
      return obj(((Object) callArg).value);
    }
  }

  private ObjectArg getOrCreate(ObjectArg objectArg, ObjectBuilderArg search) {
    if (!this.inputs.containsKey(search)) {
      return objectArg;
    }

    ObjectArg old = ((Object) this.inputs.get(search)).value;
    if ((objectArg instanceof SharedObject) && (old instanceof SharedObject)) {
      if (((SharedObject) objectArg)
          .initial_shared_version.equals(((SharedObject) old).initial_shared_version)) {
        ObjectArg.SharedObject.Builder shardObjectArgBuilder = new SharedObject.Builder();
        shardObjectArgBuilder.id = search.getId();
        shardObjectArgBuilder.initial_shared_version =
            ((SharedObject) objectArg).initial_shared_version;
        shardObjectArgBuilder.mutable =
            ((SharedObject) objectArg).mutable || ((SharedObject) old).mutable;
        return shardObjectArgBuilder.build();
      }
    }

    if (!objectArg.equals(old)) {
      throw new MismatchedObjectArgumentException(search.getId(), old, objectArg);
    }

    return objectArg;
  }

  /**
   * Make obj vec argument.
   *
   * @param objs the objs
   * @return the argument
   */
  public Argument makeObjVec(List<ObjectArg> objs) {
    List<Argument> arguments = objs.stream().map(this::obj).collect(Collectors.toList());
    Command.MakeMoveVec.Builder makeMoveVecBuilder = new MakeMoveVec.Builder();
    makeMoveVecBuilder.field0 = Optional.empty();
    makeMoveVecBuilder.field1 = arguments;
    return command(makeMoveVecBuilder.build());
  }

  /**
   * Pure argument.
   *
   * @param value the value
   * @return the argument
   */
  public Argument pure(java.lang.Object value) {
    byte[] bytes;
    try {
      if (value instanceof MoveValue) {
        bytes = ((MoveValue) value).bcsSerialize();
      } else if (value instanceof SuiAddress) {
        bytes = ((SuiAddress) value).bcsSerialize();
      } else if (value instanceof Long) {
        Serializer serializer = new BcsSerializer();
        serializer.increase_container_depth();
        serializer.serialize_u64((Long) value);
        serializer.decrease_container_depth();
        bytes = serializer.get_bytes();
      } else {
        throw new NotSupportedArgumentException(value.getClass());
      }
      return this.pureBytes(bytes, false);
    } catch (SerializationError e) {
      throw new BcsSerializationException(e);
    }
  }

  /**
   * Command argument.
   *
   * @param command the command
   * @return the argument
   */
  public Argument command(Command command) {
    Argument argument = new Result((short) this.commands.size());
    this.commands.add(command);
    return argument;
  }

  /**
   * Move call argument.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @param typeArguments the type arguments
   * @param arguments the arguments
   * @return the argument
   */
  public Argument moveCall(
      ObjectID suiPackage,
      Identifier module,
      Identifier function,
      List<TypeTag> typeArguments,
      List<CallArg> arguments) {
    List<Argument> bcsArguments =
        arguments.stream()
            .map(
                callArg -> {
                  if (callArg instanceof CallArgObjVec) {
                    return this.makeObjVec(((CallArgObjVec) callArg).getObjectArgs());
                  }
                  return this.input(callArg);
                })
            .collect(Collectors.toList());

    return this.programmableMoveCall(suiPackage, module, function, typeArguments, bcsArguments);
  }

  /**
   * Programmable move call argument.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @param typeArguments the type arguments
   * @param arguments the arguments
   * @return the argument
   */
  public Argument programmableMoveCall(
      ObjectID suiPackage,
      Identifier module,
      Identifier function,
      List<TypeTag> typeArguments,
      List<Argument> arguments) {
    ProgrammableMoveCall.Builder progCallBuilder = new ProgrammableMoveCall.Builder();
    progCallBuilder.Package = suiPackage;
    progCallBuilder.module = module;
    progCallBuilder.function = function;
    progCallBuilder.type_arguments = typeArguments;
    progCallBuilder.arguments = arguments;
    Command.MoveCall.Builder callBuilder = new MoveCall.Builder();
    callBuilder.value = progCallBuilder.build();
    return this.command(callBuilder.build());
  }

  /**
   * Transfer arg argument.
   *
   * @param recipient the recipient
   * @param arg the arg
   * @return the argument
   */
  public Argument transferArg(SuiAddress recipient, Argument arg) {
    return this.transferArgs(recipient, Lists.newArrayList(arg));
  }

  /**
   * Transfer args argument.
   *
   * @param recipient the recipient
   * @param args the args
   * @return the argument
   */
  public Argument transferArgs(SuiAddress recipient, List<Argument> args) {
    Argument recArg = this.pure(recipient);
    Command.TransferObjects.Builder builder = new TransferObjects.Builder();
    builder.field0 = args;
    builder.field1 = recArg;
    return this.command(builder.build());
  }

  /**
   * Publish upgradeable argument.
   *
   * @param modules the modules
   * @param depIds the dep ids
   * @return the argument
   */
  public Argument publishUpgradeable(List<List<Byte>> modules, List<ObjectID> depIds) {
    Command.Publish.Builder builder = new Publish.Builder();
    builder.field0 = modules;
    builder.field1 = depIds;
    return this.command(builder.build());
  }

  /**
   * Transfer sui argument.
   *
   * @param recipient the recipient
   * @param amount the amount
   * @return the argument
   */
  public Argument transferSui(SuiAddress recipient, Long amount) {
    Argument recipientArg = this.pure(recipient);
    Argument coinArg;
    if (amount != null) {
      Argument amountArg = this.pure(amount);
      Command.SplitCoins.Builder splitCoinCommandBuilder = new SplitCoins.Builder();
      splitCoinCommandBuilder.field0 = new Argument.GasCoin.Builder().build();
      splitCoinCommandBuilder.field1 = Lists.newArrayList(amountArg);
      coinArg = this.command(splitCoinCommandBuilder.build());
    } else {
      coinArg = new Argument.GasCoin.Builder().build();
    }

    Command.TransferObjects.Builder transferObjectsCommandBuilder = new TransferObjects.Builder();
    transferObjectsCommandBuilder.field0 = Lists.newArrayList(coinArg);
    transferObjectsCommandBuilder.field1 = recipientArg;
    return this.command(transferObjectsCommandBuilder.build());
  }

  /**
   * Transfer object argument.
   *
   * @param recipient the recipient
   * @param objectRef the object ref
   * @return the argument
   */
  public Argument transferObject(
      SuiAddress recipient, Tuple3<ObjectID, SequenceNumber, ObjectDigest> objectRef) {
    return transferObjects(recipient, Lists.newArrayList(objectRef));
  }

  /**
   * Split coins argument.
   *
   * @param amounts the amounts
   * @return the argument
   */
  public Argument splitCoins(List<Long> amounts) {
    Argument coin = new GasCoin.Builder().build();
    List<Argument> amountArgs = amounts.stream().map(this::pure).collect(Collectors.toList());

    return splitCoins(coin, amountArgs);
  }

  /**
   * Split coins argument.
   *
   * @param coin the coin
   * @param amounts the amounts
   * @return the argument
   */
  public Argument splitCoins(
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> coin, List<Long> amounts) {
    ImmOrOwnedObject.Builder objectBuilder = new ImmOrOwnedObject.Builder();
    objectBuilder.value = coin;
    Argument coinArg = obj(objectBuilder.build());

    List<Argument> amountArgs = amounts.stream().map(this::pure).collect(Collectors.toList());

    return splitCoins(coinArg, amountArgs);
  }

  /**
   * Split coins argument.
   *
   * @param coin the coin
   * @param amounts the amounts
   * @return the argument
   */
  public Argument splitCoins(Argument coin, List<Argument> amounts) {
    Command.SplitCoins.Builder builder = new SplitCoins.Builder();
    builder.field0 = coin;
    builder.field1 = amounts;
    return this.command(builder.build());
  }

  /**
   * Merge coins argument.
   *
   * @param destCoin the dest coin
   * @param sourceCoins the source coins
   * @return the argument
   */
  public Argument mergeCoins(
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> destCoin,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> sourceCoins) {
    ImmOrOwnedObject.Builder objectBuilder = new ImmOrOwnedObject.Builder();
    objectBuilder.value = destCoin;
    Argument dcoin = obj(objectBuilder.build());

    List<Argument> scoins =
        sourceCoins.stream()
            .map(
                objectRef -> {
                  ImmOrOwnedObject.Builder builder = new ImmOrOwnedObject.Builder();
                  builder.value = objectRef;
                  return obj(builder.build());
                })
            .collect(Collectors.toList());

    return mergeCoins(dcoin, scoins);
  }

  /**
   * Merge coins argument.
   *
   * @param destinationCoin the destination coin
   * @param sourceCoins the source coins
   * @return the argument
   */
  public Argument mergeCoins(Argument destinationCoin, List<Argument> sourceCoins) {
    Command.MergeCoins.Builder builder = new MergeCoins.Builder();
    builder.field0 = destinationCoin;
    builder.field1 = sourceCoins;
    return this.command(builder.build());
  }

  /**
   * Transfer objects argument.
   *
   * @param objects the objects
   * @param recipient the recipient
   * @return the argument
   */
  public Argument transferObjects(List<Argument> objects, Argument recipient) {
    Command.TransferObjects.Builder transferObjectsCommandBuilder = new TransferObjects.Builder();
    transferObjectsCommandBuilder.field0 = objects;
    transferObjectsCommandBuilder.field1 = recipient;
    return this.command(transferObjectsCommandBuilder.build());
  }

  /**
   * Transfer objects argument.
   *
   * @param recipient the recipient
   * @param objectRefs the object refs
   * @return the argument
   */
  public Argument transferObjects(
      SuiAddress recipient, List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> objectRefs) {
    Argument recipientArg = this.pure(recipient);
    List<Argument> arguments =
        objectRefs.stream()
            .map(
                objectRef -> {
                  ImmOrOwnedObject.Builder objectBuilder = new ImmOrOwnedObject.Builder();
                  objectBuilder.value = objectRef;
                  return obj(objectBuilder.build());
                })
            .collect(Collectors.toList());

    return transferObjects(arguments, recipientArg);
  }

  /**
   * Pay all sui argument.
   *
   * @param recipient the recipient
   * @return the argument
   */
  public Argument payAllSui(SuiAddress recipient) {
    Argument recArg = this.pure(recipient);
    Command.TransferObjects.Builder builder = new TransferObjects.Builder();
    builder.field0 = Lists.newArrayList(new Argument.GasCoin.Builder().build());
    builder.field1 = recArg;
    return this.command(builder.build());
  }

  /**
   * Pay sui.
   *
   * @param recipients the recipients
   * @param amounts the amounts
   */
  public void paySui(List<SuiAddress> recipients, List<Long> amounts) {
    Argument coinArg = new Argument.GasCoin.Builder().build();
    this.payImpl(recipients, amounts, coinArg);
  }

  /**
   * Pay.
   *
   * @param coins the coins
   * @param recipients the recipients
   * @param amounts the amounts
   */
  public void pay(
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins,
      List<SuiAddress> recipients,
      List<Long> amounts) {
    if (coins.isEmpty()) {
      throw new EmptyInputCoinsException();
    }
    ObjectArg.ImmOrOwnedObject.Builder coinBuilder = new ImmOrOwnedObject.Builder();
    coinBuilder.value = coins.get(0);
    Argument coinArg = this.obj(coinBuilder.build());
    if (coins.size() > 1) {
      List<Argument> mergeArgs =
          coins.subList(1, coins.size()).stream()
              .map(
                  coin -> {
                    ImmOrOwnedObject.Builder builder = new ImmOrOwnedObject.Builder();
                    builder.value = coin;
                    return obj(builder.build());
                  })
              .collect(Collectors.toList());
      Command.MergeCoins.Builder mergeBuilder = new MergeCoins.Builder();
      mergeBuilder.field0 = coinArg;
      mergeBuilder.field1 = mergeArgs;
      this.command(mergeBuilder.build());
    }
    this.payImpl(recipients, amounts, coinArg);
  }

  private void payImpl(List<SuiAddress> recipients, List<Long> amounts, Argument coin) {
    if (recipients.size() != amounts.size()) {
      throw new PayRecipientsAndAmountsMismatchException(recipients.size(), amounts.size());
    }

    if (amounts.isEmpty()) {
      return;
    }

    LinkedHashMap<SuiAddress, List<Integer>> recipientMap = new LinkedHashMap<>();
    List<Argument> amtArgs = Lists.newArrayList();
    for (int i = 0; i < recipients.size(); i++) {
      if (recipientMap.containsKey(recipients.get(i))) {
        recipientMap.get(recipients.get(i)).add(i);
      } else {
        recipientMap.put(recipients.get(i), Lists.newArrayList(i));
      }
      amtArgs.add(this.pure(amounts.get(i)));
    }

    Command.SplitCoins.Builder splitBuilder = new SplitCoins.Builder();
    splitBuilder.field0 = coin;
    splitBuilder.field1 = amtArgs;

    Argument.Result splitPrimary = (Result) this.command(splitBuilder.build());
    for (Entry<SuiAddress, List<Integer>> entry : recipientMap.entrySet()) {
      SuiAddress suiAddress = entry.getKey();
      List<Integer> splitSecondaries = entry.getValue();
      Argument recArg = pure(suiAddress);
      List<Argument> coins = Lists.newArrayList();
      for (Integer index : splitSecondaries) {
        Argument.NestedResult.Builder nestedBuilder = new NestedResult.Builder();
        nestedBuilder.field0 = splitPrimary.value;
        nestedBuilder.field1 = index.shortValue();
        coins.add(nestedBuilder.build());
      }
      TransferObjects.Builder transferBuilder = new TransferObjects.Builder();
      transferBuilder.field0 = coins;
      transferBuilder.field1 = recArg;
      command(transferBuilder.build());
    }
  }

  /**
   * New transfer sui transaction data.
   *
   * @param recipient the recipient
   * @param sender the sender
   * @param amount the amount
   * @param gasPayment the gas payment
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newTransferSui(
      SuiAddress recipient,
      SuiAddress sender,
      Long amount,
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> gasPayment,
      Long gasBudget,
      Long gasPrice) {
    ProgrammableTransactionBuilder programmableTransactionBuilder =
        new ProgrammableTransactionBuilder();
    programmableTransactionBuilder.transferSui(recipient, amount);
    ProgrammableTransaction programmableTransaction = programmableTransactionBuilder.build();
    return newProgrammable(
        sender, Lists.newArrayList(gasPayment), programmableTransaction, gasBudget, gasPrice);
  }

  /**
   * New pay sui transaction data.
   *
   * @param sender the sender
   * @param coins the coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gasPayment the gas payment
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newPaySui(
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins,
      List<SuiAddress> recipients,
      List<Long> amounts,
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> gasPayment,
      Long gasBudget,
      Long gasPrice) {
    coins.add(0, gasPayment);
    ProgrammableTransactionBuilder programmableTransactionBuilder =
        new ProgrammableTransactionBuilder();
    programmableTransactionBuilder.paySui(recipients, amounts);
    ProgrammableTransaction programmableTransaction = programmableTransactionBuilder.build();
    return newProgrammable(sender, coins, programmableTransaction, gasBudget, gasPrice);
  }

  /**
   * New pay all sui transaction data.
   *
   * @param sender the sender
   * @param coins the coins
   * @param recipient the recipient
   * @param gasPayment the gas payment
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newPayAllSui(
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins,
      SuiAddress recipient,
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> gasPayment,
      Long gasBudget,
      Long gasPrice) {
    coins.add(0, gasPayment);
    ProgrammableTransactionBuilder programmableTransactionBuilder =
        new ProgrammableTransactionBuilder();
    programmableTransactionBuilder.payAllSui(recipient);
    ProgrammableTransaction programmableTransaction = programmableTransactionBuilder.build();
    return newProgrammable(sender, coins, programmableTransaction, gasBudget, gasPrice);
  }

  /**
   * New pay transaction data.
   *
   * @param sender the sender
   * @param coins the coins
   * @param recipients the recipients
   * @param amounts the amounts
   * @param gasPayment the gas payment
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newPay(
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins,
      List<SuiAddress> recipients,
      List<Long> amounts,
      Tuple3<ObjectID, SequenceNumber, ObjectDigest> gasPayment,
      Long gasBudget,
      Long gasPrice) {
    ProgrammableTransactionBuilder programmableTransactionBuilder =
        new ProgrammableTransactionBuilder();
    programmableTransactionBuilder.pay(coins, recipients, amounts);
    ProgrammableTransaction programmableTransaction = programmableTransactionBuilder.build();
    return newProgrammable(
        sender, Lists.newArrayList(gasPayment), programmableTransaction, gasBudget, gasPrice);
  }

  /**
   * New module transaction data.
   *
   * @param sender the sender
   * @param gasPayment the gas payment
   * @param modules the modules
   * @param depIds the dep ids
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newModule(
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> gasPayment,
      List<List<Byte>> modules,
      List<ObjectID> depIds,
      Long gasBudget,
      Long gasPrice) {
    ProgrammableTransactionBuilder programmableTransactionBuilder =
        new ProgrammableTransactionBuilder();
    Argument upgradeArg = programmableTransactionBuilder.publishUpgradeable(modules, depIds);
    programmableTransactionBuilder.transferArg(sender, upgradeArg);
    ProgrammableTransaction programmableTransaction = programmableTransactionBuilder.build();
    return newProgrammable(
        sender, Lists.newArrayList(gasPayment), programmableTransaction, gasBudget, gasPrice);
  }

  /**
   * New programmable transaction data.
   *
   * @param sender the sender
   * @param gasPayment the gas payment
   * @param programmableTransaction the programmable transaction
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newProgrammable(
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> gasPayment,
      ProgrammableTransaction programmableTransaction,
      Long gasBudget,
      Long gasPrice) {
    TransactionKind.ProgrammableTransaction.Builder builder =
        new TransactionKind.ProgrammableTransaction.Builder();
    builder.value = programmableTransaction;
    return newWithGasCoins(builder.build(), sender, gasPayment, gasBudget, gasPrice);
  }

  /**
   * New with gas coins transaction data.
   *
   * @param kind the kind
   * @param sender the sender
   * @param gasPayment the gas payment
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   * @return the transaction data
   */
  public static TransactionData newWithGasCoins(
      TransactionKind kind,
      SuiAddress sender,
      List<Tuple3<ObjectID, SequenceNumber, ObjectDigest>> gasPayment,
      Long gasBudget,
      Long gasPrice) {

    TransactionDataV1.Builder v1builder = new TransactionDataV1.Builder();
    v1builder.kind = kind;
    v1builder.sender = sender;
    GasData.Builder gasBuilder = new GasData.Builder();
    gasBuilder.payment = gasPayment;
    gasBuilder.price = gasPrice;
    gasBuilder.budget = gasBudget;
    gasBuilder.owner = sender;
    v1builder.gas_data = gasBuilder.build();
    v1builder.expiration = new TransactionExpiration.None.Builder().build();
    TransactionData.V1.Builder builder = new V1.Builder();
    builder.value = v1builder.build();
    return builder.build();
  }
}
