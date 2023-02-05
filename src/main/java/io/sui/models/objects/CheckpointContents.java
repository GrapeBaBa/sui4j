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

package io.sui.models.objects;


import com.google.common.base.Objects;
import io.sui.models.transactions.ExecutionDigests;
import java.util.List;

/**
 * the contents of a checkpoint.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class CheckpointContents {

  private List<ExecutionDigests> transactions;

  private List<String> userSignatures;

  public List<ExecutionDigests> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<ExecutionDigests> transactions) {
    this.transactions = transactions;
  }

  public List<String> getUserSignatures() {
    return userSignatures;
  }

  public void setUserSignatures(List<String> userSignatures) {
    this.userSignatures = userSignatures;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheckpointContents that = (CheckpointContents) o;
    return Objects.equal(transactions, that.transactions)
        && Objects.equal(userSignatures, that.userSignatures);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(transactions, userSignatures);
  }

  @Override
  public String toString() {
    return "CheckpointContents{"
        + "transactions="
        + transactions
        + ", userSignatures="
        + userSignatures
        + '}';
  }
}
