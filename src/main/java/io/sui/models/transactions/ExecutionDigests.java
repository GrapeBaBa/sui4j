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


import com.google.common.base.Objects;

/**
 * the execution digests.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class ExecutionDigests {

  /** the TransactionDigest value. */
  private String transaction;

  /** the TransactionEffectsDigest value. */
  private String effects;

  public String getTransaction() {
    return transaction;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public String getEffects() {
    return effects;
  }

  public void setEffects(String effects) {
    this.effects = effects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExecutionDigests that = (ExecutionDigests) o;
    return Objects.equal(transaction, that.transaction) && Objects.equal(effects, that.effects);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(transaction, effects);
  }

  @Override
  public String toString() {
    return "ExecutionDigests{"
        + "transaction='"
        + transaction
        + '\''
        + ", effects='"
        + effects
        + '\''
        + '}';
  }
}
