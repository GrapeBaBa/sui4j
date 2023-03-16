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

package io.sui.models.transactions;


import java.util.List;
import java.util.Objects;

/**
 * The type Command.
 *
 * @author grapebaba
 * @since 2023.03
 */
public abstract class Command {

  /** The type Move call command. */
  public static class MoveCallCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveCall MoveCall;

    /**
     * Gets move call.
     *
     * @return the move call
     */
    public Command.MoveCall getMoveCall() {
      return MoveCall;
    }

    /**
     * Sets move call.
     *
     * @param moveCall the move call
     */
    public void setMoveCall(Command.MoveCall moveCall) {
      MoveCall = moveCall;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveCallCommand)) {
        return false;
      }
      MoveCallCommand that = (MoveCallCommand) o;
      return MoveCall.equals(that.MoveCall);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveCall);
    }

    @Override
    public String toString() {
      return "MoveCallCommand{" + "MoveCall=" + MoveCall + '}';
    }
  }

  /** The type Transfer objects command. */
  public static class TransferObjectsCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private TransferObjects TransferObjects;

    /**
     * Gets transfer objects.
     *
     * @return the transfer objects
     */
    public Command.TransferObjects getTransferObjects() {
      return TransferObjects;
    }

    /**
     * Sets transfer objects.
     *
     * @param transferObjects the transfer objects
     */
    public void setTransferObjects(Command.TransferObjects transferObjects) {
      TransferObjects = transferObjects;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjectsCommand)) {
        return false;
      }
      TransferObjectsCommand that = (TransferObjectsCommand) o;
      return TransferObjects.equals(that.TransferObjects);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TransferObjects);
    }

    @Override
    public String toString() {
      return "TransferObjectsCommand{" + "TransferObjects=" + TransferObjects + '}';
    }
  }

  /** The type Split coin command. */
  public static class SplitCoinCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private SplitCoin SplitCoin;

    /**
     * Gets split coin.
     *
     * @return the split coin
     */
    public Command.SplitCoin getSplitCoin() {
      return SplitCoin;
    }

    /**
     * Sets split coin.
     *
     * @param splitCoin the split coin
     */
    public void setSplitCoin(Command.SplitCoin splitCoin) {
      SplitCoin = splitCoin;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SplitCoinCommand)) {
        return false;
      }
      SplitCoinCommand that = (SplitCoinCommand) o;
      return SplitCoin.equals(that.SplitCoin);
    }

    @Override
    public int hashCode() {
      return Objects.hash(SplitCoin);
    }

    @Override
    public String toString() {
      return "SplitCoinCommand{" + "SplitCoin=" + SplitCoin + '}';
    }
  }

  /** The type Merge coins command. */
  public static class MergeCoinsCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private MergeCoins MergeCoins;

    /**
     * Gets merge coins.
     *
     * @return the merge coins
     */
    public Command.MergeCoins getMergeCoins() {
      return MergeCoins;
    }

    /**
     * Sets merge coins.
     *
     * @param mergeCoins the merge coins
     */
    public void setMergeCoins(Command.MergeCoins mergeCoins) {
      MergeCoins = mergeCoins;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MergeCoinsCommand)) {
        return false;
      }
      MergeCoinsCommand that = (MergeCoinsCommand) o;
      return MergeCoins.equals(that.MergeCoins);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MergeCoins);
    }

    @Override
    public String toString() {
      return "MergeCoinsCommand{" + "MergeCoins=" + MergeCoins + '}';
    }
  }

  /** The type Publish command. */
  public static class PublishCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private MovePackage Publish;

    /**
     * Gets publish.
     *
     * @return the publish
     */
    public MovePackage getPublish() {
      return Publish;
    }

    /**
     * Sets publish.
     *
     * @param publish the publish
     */
    public void setPublish(MovePackage publish) {
      Publish = publish;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PublishCommand)) {
        return false;
      }
      PublishCommand that = (PublishCommand) o;
      return Publish.equals(that.Publish);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Publish);
    }

    @Override
    public String toString() {
      return "PublishCommand{" + "Publish=" + Publish + '}';
    }
  }

  /** The type Make move vec command. */
  public static class MakeMoveVecCommand extends Command {

    @SuppressWarnings("checkstyle:MemberName")
    private MakeMoveVec MakeMoveVec;

    /**
     * Gets make move vec.
     *
     * @return the make move vec
     */
    public Command.MakeMoveVec getMakeMoveVec() {
      return MakeMoveVec;
    }

    /**
     * Sets make move vec.
     *
     * @param makeMoveVec the make move vec
     */
    public void setMakeMoveVec(Command.MakeMoveVec makeMoveVec) {
      MakeMoveVec = makeMoveVec;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MakeMoveVecCommand)) {
        return false;
      }
      MakeMoveVecCommand that = (MakeMoveVecCommand) o;
      return MakeMoveVec.equals(that.MakeMoveVec);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MakeMoveVec);
    }

    @Override
    public String toString() {
      return "MakeMoveVecCommand{" + "MakeMoveVec=" + MakeMoveVec + '}';
    }
  }

  /**
   * The type Move call.
   *
   * @author grapebaba
   * @since 2022.11
   */
  public static class MoveCall {

    private String suiPackage;

    private String module;

    private String function;

    private List<String> typeArguments;

    private List<Argument> arguments;

    /**
     * Gets sui package.
     *
     * @return the sui package
     */
    public String getSuiPackage() {
      return suiPackage;
    }

    /**
     * Sets sui package.
     *
     * @param suiPackage the sui package
     */
    public void setSuiPackage(String suiPackage) {
      this.suiPackage = suiPackage;
    }

    /**
     * Gets module.
     *
     * @return the module
     */
    public String getModule() {
      return module;
    }

    /**
     * Sets module.
     *
     * @param module the module
     */
    public void setModule(String module) {
      this.module = module;
    }

    /**
     * Gets function.
     *
     * @return the function
     */
    public String getFunction() {
      return function;
    }

    /**
     * Sets function.
     *
     * @param function the function
     */
    public void setFunction(String function) {
      this.function = function;
    }

    /**
     * Gets type arguments.
     *
     * @return the type arguments
     */
    public List<String> getTypeArguments() {
      return typeArguments;
    }

    /**
     * Sets type arguments.
     *
     * @param typeArguments the type arguments
     */
    public void setTypeArguments(List<String> typeArguments) {
      this.typeArguments = typeArguments;
    }

    /**
     * Gets arguments.
     *
     * @return the arguments
     */
    public List<?> getArguments() {
      return arguments;
    }

    /**
     * Sets arguments.
     *
     * @param arguments the arguments
     */
    public void setArguments(List<Argument> arguments) {
      this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveCall)) {
        return false;
      }
      MoveCall moveCall = (MoveCall) o;
      return suiPackage.equals(moveCall.suiPackage)
          && module.equals(moveCall.module)
          && function.equals(moveCall.function)
          && typeArguments.equals(moveCall.typeArguments)
          && arguments.equals(moveCall.arguments);
    }

    @Override
    public int hashCode() {
      return Objects.hash(suiPackage, module, function, typeArguments, arguments);
    }

    @Override
    public String toString() {
      return "MoveCall{"
          + "suiPackage='"
          + suiPackage
          + '\''
          + ", module='"
          + module
          + '\''
          + ", function='"
          + function
          + '\''
          + ", typeArguments="
          + typeArguments
          + ", arguments="
          + arguments
          + '}';
    }
  }

  /** The type Transfer objects. */
  public static class TransferObjects {

    private List<Argument> field0;

    private Argument field1;

    /**
     * Gets field 0.
     *
     * @return the field 0
     */
    public List<Argument> getField0() {
      return field0;
    }

    /**
     * Sets field 0.
     *
     * @param field0 the field 0
     */
    public void setField0(List<Argument> field0) {
      this.field0 = field0;
    }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public Argument getField1() {
      return field1;
    }

    /**
     * Sets field 1.
     *
     * @param field1 the field 1
     */
    public void setField1(Argument field1) {
      this.field1 = field1;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjects)) {
        return false;
      }
      TransferObjects that = (TransferObjects) o;
      return field0.equals(that.field0) && field1.equals(that.field1);
    }

    @Override
    public int hashCode() {
      return Objects.hash(field0, field1);
    }

    @Override
    public String toString() {
      return "TransferObjects{" + "field0=" + field0 + ", field1=" + field1 + '}';
    }
  }

  /** The type Split coin. */
  public static class SplitCoin {

    private Argument field0;

    private String address;

    /**
     * Gets field 0.
     *
     * @return the field 0
     */
    public Argument getField0() {
      return field0;
    }

    /**
     * Sets field 0.
     *
     * @param field0 the field 0
     */
    public void setField0(Argument field0) {
      this.field0 = field0;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
      return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
      this.address = address;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SplitCoin)) {
        return false;
      }
      SplitCoin splitCoin = (SplitCoin) o;
      return field0.equals(splitCoin.field0) && address.equals(splitCoin.address);
    }

    @Override
    public int hashCode() {
      return Objects.hash(field0, address);
    }

    @Override
    public String toString() {
      return "SplitCoin{" + "field0=" + field0 + ", address='" + address + '\'' + '}';
    }
  }

  /** The type Merge coins. */
  public static class MergeCoins {

    private Argument field0;

    private List<Argument> field1;

    /**
     * Gets field 0.
     *
     * @return the field 0
     */
    public Argument getField0() {
      return field0;
    }

    /**
     * Sets field 0.
     *
     * @param field0 the field 0
     */
    public void setField0(Argument field0) {
      this.field0 = field0;
    }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public List<Argument> getField1() {
      return field1;
    }

    /**
     * Sets field 1.
     *
     * @param field1 the field 1
     */
    public void setField1(List<Argument> field1) {
      this.field1 = field1;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MergeCoins)) {
        return false;
      }
      MergeCoins that = (MergeCoins) o;
      return field0.equals(that.field0) && field1.equals(that.field1);
    }

    @Override
    public int hashCode() {
      return Objects.hash(field0, field1);
    }

    @Override
    public String toString() {
      return "MergeCoins{" + "field0=" + field0 + ", field1=" + field1 + '}';
    }
  }

  /** The type Make move vec. */
  public static class MakeMoveVec {

    private String field0;

    private List<Argument> field1;

    /**
     * Gets field 0.
     *
     * @return the field 0
     */
    public String getField0() {
      return field0;
    }

    /**
     * Sets field 0.
     *
     * @param field0 the field 0
     */
    public void setField0(String field0) {
      this.field0 = field0;
    }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public List<Argument> getField1() {
      return field1;
    }

    /**
     * Sets field 1.
     *
     * @param field1 the field 1
     */
    public void setField1(List<Argument> field1) {
      this.field1 = field1;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MakeMoveVec)) {
        return false;
      }
      MakeMoveVec that = (MakeMoveVec) o;
      return field0.equals(that.field0) && field1.equals(that.field1);
    }

    @Override
    public int hashCode() {
      return Objects.hash(field0, field1);
    }

    @Override
    public String toString() {
      return "MakeMoveVec{" + "field0='" + field0 + '\'' + ", field1=" + field1 + '}';
    }
  }
}
