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


import java.util.List;
import java.util.Objects;

/**
 * The type Certified transaction.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class Transaction {

  private List<String> txSignatures;

  private TransactionData data;

  /**
   * Gets tx signatures.
   *
   * @return the tx signatures
   */
  public List<String> getTxSignatures() {
    return txSignatures;
  }

  /**
   * Sets tx signatures.
   *
   * @param txSignatures the tx signatures
   */
  public void setTxSignatures(List<String> txSignatures) {
    this.txSignatures = txSignatures;
  }

  /**
   * Gets data.
   *
   * @return the data
   */
  public TransactionData getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  public void setData(TransactionData data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Transaction)) {
      return false;
    }
    Transaction that = (Transaction) o;
    return txSignatures.equals(that.txSignatures) && data.equals(that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txSignatures, data);
  }

  @Override
  public String toString() {
    return "Transaction{" + "txSignatures=" + txSignatures + ", data=" + data + '}';
  }
}
