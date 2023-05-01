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

package io.sui.models.transactions;


import io.sui.models.transactions.TransactionFilter.FromAndToAddress;
import java.util.Objects;

/**
 * The interface Transaction query.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class TransactionFilter {

  /** The type Move function query. */
  public static class MoveFunctionFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveFunction MoveFunction;

    /**
     * Gets move function.
     *
     * @return the move function
     */
    public MoveFunction getMoveFunction() {
      return MoveFunction;
    }

    /**
     * Sets move function.
     *
     * @param moveFunction the move function
     */
    public void setMoveFunction(MoveFunction moveFunction) {
      MoveFunction = moveFunction;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveFunctionFilter)) {
        return false;
      }
      MoveFunctionFilter that = (MoveFunctionFilter) o;
      return MoveFunction.equals(that.MoveFunction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveFunction);
    }

    @Override
    public String toString() {
      return "MoveFunctionFilter{" + "MoveFunction=" + MoveFunction + '}';
    }
  }

  /** The type Input object query. */
  public static class InputObjectFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String InputObject;

    /**
     * Gets input object.
     *
     * @return the input object
     */
    public String getInputObject() {
      return InputObject;
    }

    /**
     * Sets input object.
     *
     * @param inputObject the input object
     */
    public void setInputObject(String inputObject) {
      InputObject = inputObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof InputObjectFilter)) {
        return false;
      }
      InputObjectFilter that = (InputObjectFilter) o;
      return InputObject.equals(that.InputObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(InputObject);
    }

    @Override
    public String toString() {
      return "InputObjectFilter{" + "InputObject='" + InputObject + '\'' + '}';
    }
  }

  /** The type Mutated object query. */
  public static class ChangedObjectFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String ChangedObject;

    /**
     * Gets mutated object.
     *
     * @return the mutated object
     */
    public String getChangedObject() {
      return ChangedObject;
    }

    /**
     * Sets mutated object.
     *
     * @param changedObject the mutated object
     */
    public void setChangedObject(String changedObject) {
      ChangedObject = changedObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ChangedObjectFilter)) {
        return false;
      }
      ChangedObjectFilter that = (ChangedObjectFilter) o;
      return ChangedObject.equals(that.ChangedObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ChangedObject);
    }

    @Override
    public String toString() {
      return "MutatedObjectQuery{" + "MutatedObject='" + ChangedObject + '\'' + '}';
    }
  }

  /** The type From address query. */
  public static class FromAddressFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String FromAddress;

    /**
     * Gets from address.
     *
     * @return the from address
     */
    public String getFromAddress() {
      return FromAddress;
    }

    /**
     * Sets from address.
     *
     * @param fromAddress the from address
     */
    public void setFromAddress(String fromAddress) {
      FromAddress = fromAddress;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FromAddressFilter)) {
        return false;
      }
      FromAddressFilter that = (FromAddressFilter) o;
      return FromAddress.equals(that.FromAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(FromAddress);
    }

    @Override
    public String toString() {
      return "FromAddressFilter{" + "FromAddress='" + FromAddress + '\'' + '}';
    }
  }

  /** The type To address query. */
  public static class ToAddressFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String ToAddress;

    /**
     * Gets to address.
     *
     * @return the to address
     */
    public String getToAddress() {
      return ToAddress;
    }

    /**
     * Sets to address.
     *
     * @param toAddress the to address
     */
    public void setToAddress(String toAddress) {
      ToAddress = toAddress;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ToAddressFilter)) {
        return false;
      }
      ToAddressFilter that = (ToAddressFilter) o;
      return ToAddress.equals(that.ToAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ToAddress);
    }

    @Override
    public String toString() {
      return "ToAddressFilter{" + "ToAddress='" + ToAddress + '\'' + '}';
    }
  }

  /** The type From and to address filter. */
  public static class FromAndToAddressFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private FromAndToAddress FromAndToAddress;

    /**
     * Gets from and to address.
     *
     * @return the from and to address
     */
    public TransactionFilter.FromAndToAddress getFromAndToAddress() {
      return FromAndToAddress;
    }

    /**
     * Sets from and to address.
     *
     * @param fromAndToAddress the from and to address
     */
    public void setFromAndToAddress(TransactionFilter.FromAndToAddress fromAndToAddress) {
      FromAndToAddress = fromAndToAddress;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FromAndToAddressFilter)) {
        return false;
      }
      FromAndToAddressFilter that = (FromAndToAddressFilter) o;
      return Objects.equals(FromAndToAddress, that.FromAndToAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(FromAndToAddress);
    }

    @Override
    public String toString() {
      return "FromAndToAddressFilter{" + "FromAndToAddress=" + FromAndToAddress + '}';
    }
  }

  /** The type Transaction kind filter. */
  public static class TransactionKindFilter extends TransactionFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String TransactionKind;

    /**
     * Gets transaction kind.
     *
     * @return the transaction kind
     */
    public String getTransactionKind() {
      return TransactionKind;
    }

    /**
     * Sets transaction kind.
     *
     * @param transactionKind the transaction kind
     */
    public void setTransactionKind(String transactionKind) {
      TransactionKind = transactionKind;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransactionKindFilter)) {
        return false;
      }
      TransactionKindFilter that = (TransactionKindFilter) o;
      return TransactionKind.equals(that.TransactionKind);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TransactionKind);
    }

    @Override
    public String toString() {
      return "TransactionKindFilter{" + "TransactionKind='" + TransactionKind + '\'' + '}';
    }
  }

  /** The type From and to address. */
  public static class FromAndToAddress {

    @SuppressWarnings("checkstyle:MemberName")
    private String FromAddress;

    @SuppressWarnings("checkstyle:MemberName")
    private String ToAddress;

    /**
     * Gets from address.
     *
     * @return the from address
     */
    public String getFromAddress() {
      return FromAddress;
    }

    /**
     * Sets from address.
     *
     * @param fromAddress the from address
     */
    public void setFromAddress(String fromAddress) {
      FromAddress = fromAddress;
    }

    /**
     * Gets to address.
     *
     * @return the to address
     */
    public String getToAddress() {
      return ToAddress;
    }

    /**
     * Sets to address.
     *
     * @param toAddress the to address
     */
    public void setToAddress(String toAddress) {
      ToAddress = toAddress;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FromAndToAddress)) {
        return false;
      }
      FromAndToAddress that = (FromAndToAddress) o;
      return Objects.equals(FromAddress, that.FromAddress)
          && Objects.equals(ToAddress, that.ToAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(FromAddress, ToAddress);
    }

    @Override
    public String toString() {
      return "FromAndToAddress{"
          + "FromAddress='"
          + FromAddress
          + '\''
          + ", ToAddress='"
          + ToAddress
          + '\''
          + '}';
    }
  }
}
