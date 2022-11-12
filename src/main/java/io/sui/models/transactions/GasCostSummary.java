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


import java.util.Objects;

/**
 * The type Gas cost summary.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class GasCostSummary {

  private Long computationCost;

  private Long storageCost;

  private Long storageRebate;

  /**
   * Gets computation cost.
   *
   * @return the computation cost
   */
  public Long getComputationCost() {
    return computationCost;
  }

  /**
   * Sets computation cost.
   *
   * @param computationCost the computation cost
   */
  public void setComputationCost(Long computationCost) {
    this.computationCost = computationCost;
  }

  /**
   * Gets storage cost.
   *
   * @return the storage cost
   */
  public Long getStorageCost() {
    return storageCost;
  }

  /**
   * Sets storage cost.
   *
   * @param storageCost the storage cost
   */
  public void setStorageCost(Long storageCost) {
    this.storageCost = storageCost;
  }

  /**
   * Gets storage rebate.
   *
   * @return the storage rebate
   */
  public Long getStorageRebate() {
    return storageRebate;
  }

  /**
   * Sets storage rebate.
   *
   * @param storageRebate the storage rebate
   */
  public void setStorageRebate(Long storageRebate) {
    this.storageRebate = storageRebate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GasCostSummary that = (GasCostSummary) o;
    return computationCost.equals(that.computationCost)
        && storageCost.equals(that.storageCost)
        && storageRebate.equals(that.storageRebate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(computationCost, storageCost, storageRebate);
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
        + '}';
  }
}
