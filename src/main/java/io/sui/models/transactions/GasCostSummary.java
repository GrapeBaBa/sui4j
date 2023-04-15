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


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Gas cost summary.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class GasCostSummary {

  private BigInteger computationCost;

  private BigInteger storageCost;

  private BigInteger storageRebate;

  private BigInteger nonRefundableStorageFee;

  /**
   * Gets computation cost.
   *
   * @return the computation cost
   */
  public BigInteger getComputationCost() {
    return computationCost;
  }

  /**
   * Sets computation cost.
   *
   * @param computationCost the computation cost
   */
  public void setComputationCost(BigInteger computationCost) {
    this.computationCost = computationCost;
  }

  /**
   * Gets storage cost.
   *
   * @return the storage cost
   */
  public BigInteger getStorageCost() {
    return storageCost;
  }

  /**
   * Sets storage cost.
   *
   * @param storageCost the storage cost
   */
  public void setStorageCost(BigInteger storageCost) {
    this.storageCost = storageCost;
  }

  /**
   * Gets storage rebate.
   *
   * @return the storage rebate
   */
  public BigInteger getStorageRebate() {
    return storageRebate;
  }

  /**
   * Sets storage rebate.
   *
   * @param storageRebate the storage rebate
   */
  public void setStorageRebate(BigInteger storageRebate) {
    this.storageRebate = storageRebate;
  }

  /**
   * Gets non refundable storage fee.
   *
   * @return the non refundable storage fee
   */
  public BigInteger getNonRefundableStorageFee() {
    return nonRefundableStorageFee;
  }

  /**
   * Sets non refundable storage fee.
   *
   * @param nonRefundableStorageFee the non refundable storage fee
   */
  public void setNonRefundableStorageFee(BigInteger nonRefundableStorageFee) {
    this.nonRefundableStorageFee = nonRefundableStorageFee;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GasCostSummary)) {
      return false;
    }
    GasCostSummary that = (GasCostSummary) o;
    return computationCost.equals(that.computationCost)
        && storageCost.equals(that.storageCost)
        && storageRebate.equals(that.storageRebate)
        && nonRefundableStorageFee.equals(that.nonRefundableStorageFee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(computationCost, storageCost, storageRebate, nonRefundableStorageFee);
  }

  @Override
  public String toString() {
    return "GasCostSummary{"
        + "computationCost="
        + computationCost
        + ", storageCost="
        + storageCost
        + ", storageRebate="
        + storageRebate
        + ", nonRefundableStorageFee="
        + nonRefundableStorageFee
        + '}';
  }
}
