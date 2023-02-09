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
 * the move staking pool type.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class StakingPool {

  private String validator_address;

  private Long starting_epoch;

  private Long sui_balance;

  private SuiBalance rewards_pool;

  private SuiSupply delegation_token_supply;

  private SuiLinkedTable<String> pending_delegations;

  private SuiTableVec pending_withdraws;

  public String getValidator_address() {
    return validator_address;
  }

  public void setValidator_address(String validator_address) {
    this.validator_address = validator_address;
  }

  public Long getStarting_epoch() {
    return starting_epoch;
  }

  public void setStarting_epoch(Long starting_epoch) {
    this.starting_epoch = starting_epoch;
  }

  public Long getSui_balance() {
    return sui_balance;
  }

  public void setSui_balance(Long sui_balance) {
    this.sui_balance = sui_balance;
  }

  public SuiBalance getRewards_pool() {
    return rewards_pool;
  }

  public void setRewards_pool(SuiBalance rewards_pool) {
    this.rewards_pool = rewards_pool;
  }

  public SuiSupply getDelegation_token_supply() {
    return delegation_token_supply;
  }

  public void setDelegation_token_supply(SuiSupply delegation_token_supply) {
    this.delegation_token_supply = delegation_token_supply;
  }

  public SuiLinkedTable<String> getPending_delegations() {
    return pending_delegations;
  }

  public void setPending_delegations(SuiLinkedTable<String> pending_delegations) {
    this.pending_delegations = pending_delegations;
  }

  public SuiTableVec getPending_withdraws() {
    return pending_withdraws;
  }

  public void setPending_withdraws(SuiTableVec pending_withdraws) {
    this.pending_withdraws = pending_withdraws;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StakingPool that = (StakingPool) o;
    return validator_address.equals(that.validator_address)
        && starting_epoch.equals(that.starting_epoch)
        && sui_balance.equals(that.sui_balance)
        && rewards_pool.equals(that.rewards_pool)
        && delegation_token_supply.equals(that.delegation_token_supply)
        && pending_delegations.equals(that.pending_delegations)
        && pending_withdraws.equals(that.pending_withdraws);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        validator_address,
        starting_epoch,
        sui_balance,
        rewards_pool,
        delegation_token_supply,
        pending_delegations,
        pending_withdraws);
  }

  @Override
  public String toString() {
    return "StakingPool{"
        + "validator_address='"
        + validator_address
        + '\''
        + ", starting_epoch="
        + starting_epoch
        + ", sui_balance="
        + sui_balance
        + ", rewards_pool="
        + rewards_pool
        + ", delegation_token_supply="
        + delegation_token_supply
        + ", pending_delegations="
        + pending_delegations
        + ", pending_withdraws="
        + pending_withdraws
        + '}';
  }
}
