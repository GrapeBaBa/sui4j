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


import io.sui.models.objects.InputObjectKind;
import io.sui.models.objects.SuiObjectRef;
import java.util.List;
import java.util.Objects;

/**
 * The type Transaction bytes.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionBytes {

  private SuiObjectRef gas;

  private List<InputObjectKind> inputObjects;

  private String txBytes;

  /**
   * Gets gas.
   *
   * @return the gas
   */
  public SuiObjectRef getGas() {
    return gas;
  }

  /**
   * Sets gas.
   *
   * @param gas the gas
   */
  public void setGas(SuiObjectRef gas) {
    this.gas = gas;
  }

  /**
   * Gets input objects.
   *
   * @return the input objects
   */
  public List<InputObjectKind> getInputObjects() {
    return inputObjects;
  }

  /**
   * Sets input objects.
   *
   * @param inputObjects the input objects
   */
  public void setInputObjects(List<InputObjectKind> inputObjects) {
    this.inputObjects = inputObjects;
  }

  /**
   * Gets tx bytes.
   *
   * @return the tx bytes
   */
  public String getTxBytes() {
    return txBytes;
  }

  /**
   * Sets tx bytes.
   *
   * @param txBytes the tx bytes
   */
  public void setTxBytes(String txBytes) {
    this.txBytes = txBytes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionBytes)) {
      return false;
    }
    TransactionBytes that = (TransactionBytes) o;
    return gas.equals(that.gas)
        && inputObjects.equals(that.inputObjects)
        && txBytes.equals(that.txBytes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gas, inputObjects, txBytes);
  }

  @Override
  public String toString() {
    return "TransactionBytes{"
        + "gas="
        + gas
        + ", inputObjects="
        + inputObjects
        + ", txBytes='"
        + txBytes
        + '\''
        + '}';
  }
}
