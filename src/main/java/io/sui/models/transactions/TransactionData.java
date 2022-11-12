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


import io.sui.models.objects.SuiObjectRef;
import java.util.List;

/**
 * The type Transaction data.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionData {

  private Long gasBudget;

  private SuiObjectRef gasPayment;

  private String sender;

  private List<TransactionKind> transactions;

  /**
   * Gets gas budget.
   *
   * @return the gas budget
   */
  public Long getGasBudget() {
    return gasBudget;
  }

  /**
   * Sets gas budget.
   *
   * @param gasBudget the gas budget
   */
  public void setGasBudget(Long gasBudget) {
    this.gasBudget = gasBudget;
  }

  /**
   * Gets gas payment.
   *
   * @return the gas payment
   */
  public SuiObjectRef getGasPayment() {
    return gasPayment;
  }

  /**
   * Sets gas payment.
   *
   * @param gasPayment the gas payment
   */
  public void setGasPayment(SuiObjectRef gasPayment) {
    this.gasPayment = gasPayment;
  }

  /**
   * Gets sender.
   *
   * @return the sender
   */
  public String getSender() {
    return sender;
  }

  /**
   * Sets sender.
   *
   * @param sender the sender
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Gets transactions.
   *
   * @return the transactions
   */
  public List<TransactionKind> getTransactions() {
    return transactions;
  }

  /**
   * Sets transactions.
   *
   * @param transactions the transactions
   */
  public void setTransactions(List<TransactionKind> transactions) {
    this.transactions = transactions;
  }

  @Override
  public String toString() {
    return "TransactionData{"
        + "gasBudget="
        + gasBudget
        + ", gasPayment="
        + gasPayment
        + ", sender='"
        + sender
        + '\''
        + ", transactions="
        + transactions
        + '}';
  }
}
