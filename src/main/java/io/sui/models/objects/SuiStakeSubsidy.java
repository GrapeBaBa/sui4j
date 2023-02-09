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


import java.util.Objects;

/**
 * the stakeSubsidy.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class SuiStakeSubsidy {

  private Long epoch_counter;

  private SuiBalance balance;

  private Long current_epoch_amount;

  public Long getEpoch_counter() {
    return epoch_counter;
  }

  public void setEpoch_counter(Long epoch_counter) {
    this.epoch_counter = epoch_counter;
  }

  public SuiBalance getBalance() {
    return balance;
  }

  public void setBalance(SuiBalance balance) {
    this.balance = balance;
  }

  public Long getCurrent_epoch_amount() {
    return current_epoch_amount;
  }

  public void setCurrent_epoch_amount(Long current_epoch_amount) {
    this.current_epoch_amount = current_epoch_amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiStakeSubsidy that = (SuiStakeSubsidy) o;
    return epoch_counter.equals(that.epoch_counter)
        && balance.equals(that.balance)
        && current_epoch_amount.equals(that.current_epoch_amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch_counter, balance, current_epoch_amount);
  }

  @Override
  public String toString() {
    return "SuiStakeSubsidy{"
        + "epoch_counter="
        + epoch_counter
        + ", balance="
        + balance
        + ", current_epoch_amount="
        + current_epoch_amount
        + '}';
  }
}
