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

package io.sui.models;


import java.math.BigInteger;
import java.util.List;

/**
 * The type Transaction data.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionData {

  private BigInteger gasBudget;

  private SuiObjectRef gasPayment;

  private String sender;

  private List<TransactionKind> transactions;

  /**
   * Gets gas budget.
   *
   * @return the gas budget
   */
  BigInteger getGasBudget() {
    return gasBudget;
  }

  /**
   * Sets gas budget.
   *
   * @param gasBudget the gas budget
   */
  void setGasBudget(BigInteger gasBudget) {
    this.gasBudget = gasBudget;
  }

  /**
   * Gets gas payment.
   *
   * @return the gas payment
   */
  SuiObjectRef getGasPayment() {
    return gasPayment;
  }

  /**
   * Sets gas payment.
   *
   * @param gasPayment the gas payment
   */
  void setGasPayment(SuiObjectRef gasPayment) {
    this.gasPayment = gasPayment;
  }

  /**
   * Gets sender.
   *
   * @return the sender
   */
  String getSender() {
    return sender;
  }

  /**
   * Sets sender.
   *
   * @param sender the sender
   */
  void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Gets transactions.
   *
   * @return the transactions
   */
  List<TransactionKind> getTransactions() {
    return transactions;
  }

  /**
   * Sets transactions.
   *
   * @param transactions the transactions
   */
  void setTransactions(List<TransactionKind> transactions) {
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
