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
 * The type Transaction data.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionBlockData {

  private String messageVersion = "v1";

  private GasData gasData;

  private String sender;

  private List<TransactionKind> transactions;

  public String getMessageVersion() {
    return messageVersion;
  }

  public void setMessageVersion(String messageVersion) {
    this.messageVersion = messageVersion;
  }

  public GasData getGasData() {
    return gasData;
  }

  public void setGasData(GasData gasData) {
    this.gasData = gasData;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public List<TransactionKind> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionKind> transactions) {
    this.transactions = transactions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionBlockData)) {
      return false;
    }
    TransactionBlockData that = (TransactionBlockData) o;
    return messageVersion.equals(that.messageVersion)
        && gasData.equals(that.gasData)
        && sender.equals(that.sender)
        && transactions.equals(that.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageVersion, gasData, sender, transactions);
  }

  @Override
  public String toString() {
    return "TransactionBlockData{"
        + "messageVersion='"
        + messageVersion
        + '\''
        + ", gasData="
        + gasData
        + ", sender='"
        + sender
        + '\''
        + ", transactions="
        + transactions
        + '}';
  }
}
