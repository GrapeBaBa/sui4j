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
 * the validator info.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class Validator {

  private ValidatorMetadata metadata;

  private Long voting_power;

  private Long stake_amount;

  private Long pending_stake;

  private Long pending_withdraw;

  private Long gas_price;

  private StakingPool delegation_staking_pool;

  private Long commission_rate;

  public ValidatorMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(ValidatorMetadata metadata) {
    this.metadata = metadata;
  }

  public Long getVoting_power() {
    return voting_power;
  }

  public void setVoting_power(Long voting_power) {
    this.voting_power = voting_power;
  }

  public Long getStake_amount() {
    return stake_amount;
  }

  public void setStake_amount(Long stake_amount) {
    this.stake_amount = stake_amount;
  }

  public Long getPending_stake() {
    return pending_stake;
  }

  public void setPending_stake(Long pending_stake) {
    this.pending_stake = pending_stake;
  }

  public Long getPending_withdraw() {
    return pending_withdraw;
  }

  public void setPending_withdraw(Long pending_withdraw) {
    this.pending_withdraw = pending_withdraw;
  }

  public Long getGas_price() {
    return gas_price;
  }

  public void setGas_price(Long gas_price) {
    this.gas_price = gas_price;
  }

  public StakingPool getDelegation_staking_pool() {
    return delegation_staking_pool;
  }

  public void setDelegation_staking_pool(StakingPool delegation_staking_pool) {
    this.delegation_staking_pool = delegation_staking_pool;
  }

  public Long getCommission_rate() {
    return commission_rate;
  }

  public void setCommission_rate(Long commission_rate) {
    this.commission_rate = commission_rate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Validator validator = (Validator) o;
    return metadata.equals(validator.metadata)
        && voting_power.equals(validator.voting_power)
        && stake_amount.equals(validator.stake_amount)
        && pending_stake.equals(validator.pending_stake)
        && pending_withdraw.equals(validator.pending_withdraw)
        && gas_price.equals(validator.gas_price)
        && delegation_staking_pool.equals(validator.delegation_staking_pool)
        && commission_rate.equals(validator.commission_rate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        metadata,
        voting_power,
        stake_amount,
        pending_stake,
        pending_withdraw,
        gas_price,
        delegation_staking_pool,
        commission_rate);
  }

  @Override
  public String toString() {
    return "Validator{"
        + "metadata="
        + metadata
        + ", voting_power="
        + voting_power
        + ", stake_amount="
        + stake_amount
        + ", pending_stake="
        + pending_stake
        + ", pending_withdraw="
        + pending_withdraw
        + ", gas_price="
        + gas_price
        + ", delegation_staking_pool="
        + delegation_staking_pool
        + ", commission_rate="
        + commission_rate
        + '}';
  }
}
