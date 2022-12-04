/*
 * Copyright 2022 281165273grape@gmail.com
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


import java.util.Objects;

/**
 * The interface Transaction query.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface TransactionQuery {

  /** The enum All query. */
  enum AllQuery implements TransactionQuery {
    /** All all query. */
    All
  }

  /** The type Move function query. */
  class MoveFunctionQuery implements TransactionQuery {

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
      if (!(o instanceof MoveFunctionQuery)) {
        return false;
      }
      MoveFunctionQuery that = (MoveFunctionQuery) o;
      return MoveFunction.equals(that.MoveFunction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveFunction);
    }

    @Override
    public String toString() {
      return "MoveFunctionQuery{" + "MoveFunction=" + MoveFunction + '}';
    }
  }

  /** The type Input object query. */
  class InputObjectQuery implements TransactionQuery {

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
      if (!(o instanceof InputObjectQuery)) {
        return false;
      }
      InputObjectQuery that = (InputObjectQuery) o;
      return InputObject.equals(that.InputObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(InputObject);
    }

    @Override
    public String toString() {
      return "InputObjectQuery{" + "InputObject='" + InputObject + '\'' + '}';
    }
  }

  /** The type Mutated object query. */
  class MutatedObjectQuery implements TransactionQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private String MutatedObject;

    /**
     * Gets mutated object.
     *
     * @return the mutated object
     */
    public String getMutatedObject() {
      return MutatedObject;
    }

    /**
     * Sets mutated object.
     *
     * @param mutatedObject the mutated object
     */
    public void setMutatedObject(String mutatedObject) {
      MutatedObject = mutatedObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MutatedObjectQuery)) {
        return false;
      }
      MutatedObjectQuery that = (MutatedObjectQuery) o;
      return MutatedObject.equals(that.MutatedObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MutatedObject);
    }

    @Override
    public String toString() {
      return "MutatedObjectQuery{" + "MutatedObject='" + MutatedObject + '\'' + '}';
    }
  }

  /** The type From address query. */
  class FromAddressQuery implements TransactionQuery {

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
      if (!(o instanceof FromAddressQuery)) {
        return false;
      }
      FromAddressQuery that = (FromAddressQuery) o;
      return FromAddress.equals(that.FromAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(FromAddress);
    }

    @Override
    public String toString() {
      return "FromAddressQuery{" + "FromAddress='" + FromAddress + '\'' + '}';
    }
  }

  /** The type To address query. */
  class ToAddressQuery implements TransactionQuery {

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
      if (!(o instanceof ToAddressQuery)) {
        return false;
      }
      ToAddressQuery that = (ToAddressQuery) o;
      return ToAddress.equals(that.ToAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ToAddress);
    }

    @Override
    public String toString() {
      return "ToAddressQuery{" + "ToAddress='" + ToAddress + '\'' + '}';
    }
  }
}
