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

package io.sui.models;


import java.util.Objects;

/**
 * The type Faucet coin info.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class FaucetCoinInfo {

  private Long amount;

  private String id;

  private String transferTxDigest;

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public Long getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amount the amount
   */
  public void setAmount(Long amount) {
    this.amount = amount;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets transfer tx digest.
   *
   * @return the transfer tx digest
   */
  public String getTransferTxDigest() {
    return transferTxDigest;
  }

  /**
   * Sets transfer tx digest.
   *
   * @param transferTxDigest the transfer tx digest
   */
  public void setTransferTxDigest(String transferTxDigest) {
    this.transferTxDigest = transferTxDigest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FaucetCoinInfo)) {
      return false;
    }
    FaucetCoinInfo that = (FaucetCoinInfo) o;
    return amount.equals(that.amount)
        && id.equals(that.id)
        && transferTxDigest.equals(that.transferTxDigest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, id, transferTxDigest);
  }

  @Override
  public String toString() {
    return "FaucetCoinInfo{"
        + "amount="
        + amount
        + ", id='"
        + id
        + '\''
        + ", transferTxDigest='"
        + transferTxDigest
        + '\''
        + '}';
  }
}
