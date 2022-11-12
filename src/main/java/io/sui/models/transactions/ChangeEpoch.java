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

/** The type Change epoch. */
public class ChangeEpoch {

  private Long epoch;

  @SuppressWarnings("checkstyle:MemberName")
  private Long storage_charge;

  @SuppressWarnings("checkstyle:MemberName")
  private Long computation_charge;

  /**
   * Gets epoch.
   *
   * @return the epoch
   */
  public Long getEpoch() {
    return epoch;
  }

  /**
   * Sets epoch.
   *
   * @param epoch the epoch
   */
  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  /**
   * Gets storage charge.
   *
   * @return the storage charge
   */
  public Long getStorage_charge() {
    return storage_charge;
  }

  /**
   * Sets storage charge.
   *
   * @param storage_charge the storage charge
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setStorage_charge(Long storage_charge) {
    this.storage_charge = storage_charge;
  }

  /**
   * Gets computation charge.
   *
   * @return the computation charge
   */
  public Long getComputation_charge() {
    return computation_charge;
  }

  /**
   * Sets computation charge.
   *
   * @param computation_charge the computation charge
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setComputation_charge(Long computation_charge) {
    this.computation_charge = computation_charge;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ChangeEpoch)) {
      return false;
    }
    ChangeEpoch that = (ChangeEpoch) o;
    return epoch.equals(that.epoch)
        && storage_charge.equals(that.storage_charge)
        && computation_charge.equals(that.computation_charge);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch, storage_charge, computation_charge);
  }

  @Override
  public String toString() {
    return "ChangeEpoch{"
        + "epoch="
        + epoch
        + ", storage_charge="
        + storage_charge
        + ", computation_charge="
        + computation_charge
        + '}';
  }
}
