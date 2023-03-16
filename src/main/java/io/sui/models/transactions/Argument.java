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


import java.util.Objects;

/**
 * The interface Argument.
 *
 * @author grapebaba
 * @since 2023.03
 */
public interface Argument {

  /** The enum Gas coin argument. */
  enum GasCoinArgument implements Argument {
    /** Gas coin gas coin argument. */
    GasCoin
  }

  /** The type Input argument. */
  class InputArgument implements Argument {

    @SuppressWarnings("checkstyle:MemberName")
    private short Input;

    /**
     * Gets input.
     *
     * @return the input
     */
    public short getInput() {
      return Input;
    }

    /**
     * Sets input.
     *
     * @param input the input
     */
    public void setInput(short input) {
      Input = input;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof InputArgument)) {
        return false;
      }
      InputArgument that = (InputArgument) o;
      return Input == that.Input;
    }

    @Override
    public int hashCode() {
      return Objects.hash(Input);
    }

    @Override
    public String toString() {
      return "InputArgument{" + "Input=" + Input + '}';
    }
  }

  /** The type Result argument. */
  class ResultArgument implements Argument {

    @SuppressWarnings("checkstyle:MemberName")
    private short Result;

    /**
     * Gets result.
     *
     * @return the result
     */
    public short getResult() {
      return Result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(short result) {
      Result = result;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ResultArgument)) {
        return false;
      }
      ResultArgument resultArgument = (ResultArgument) o;
      return Result == resultArgument.Result;
    }

    @Override
    public int hashCode() {
      return Objects.hash(Result);
    }

    @Override
    public String toString() {
      return "Result{" + "Result=" + Result + '}';
    }
  }

  /** The type Nested result argument. */
  class NestedResultArgument implements Argument {

    @SuppressWarnings("checkstyle:MemberName")
    private NestedResult NestedResult;

    /**
     * Gets nested result.
     *
     * @return the nested result
     */
    public Argument.NestedResult getNestedResult() {
      return NestedResult;
    }

    /**
     * Sets nested result.
     *
     * @param nestedResult the nested result
     */
    public void setNestedResult(Argument.NestedResult nestedResult) {
      NestedResult = nestedResult;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NestedResultArgument)) {
        return false;
      }
      NestedResultArgument that = (NestedResultArgument) o;
      return NestedResult.equals(that.NestedResult);
    }

    @Override
    public int hashCode() {
      return Objects.hash(NestedResult);
    }

    @Override
    public String toString() {
      return "NestedResultArgument{" + "NestedResult=" + NestedResult + '}';
    }
  }

  /** The type Nested result. */
  class NestedResult {

    private short field0;

    private short field1;

    /**
     * Gets field 0.
     *
     * @return the field 0
     */
    public short getField0() {
      return field0;
    }

    /**
     * Sets field 0.
     *
     * @param field0 the field 0
     */
    public void setField0(short field0) {
      this.field0 = field0;
    }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public short getField1() {
      return field1;
    }

    /**
     * Sets field 1.
     *
     * @param field1 the field 1
     */
    public void setField1(short field1) {
      this.field1 = field1;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NestedResult)) {
        return false;
      }
      NestedResult that = (NestedResult) o;
      return field0 == that.field0 && field1 == that.field1;
    }

    @Override
    public int hashCode() {
      return Objects.hash(field0, field1);
    }

    @Override
    public String toString() {
      return "NestedResult{" + "field0=" + field0 + ", field1=" + field1 + '}';
    }
  }
}
