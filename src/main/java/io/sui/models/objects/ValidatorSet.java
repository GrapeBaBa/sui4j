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

package io.sui.models.objects;


import java.util.List;
import java.util.Objects;

/**
 * the validator set type.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
@SuppressWarnings({"checkstyle:MemberName", "checkstyle:ParameterName"})
public class ValidatorSet {

  private Long validator_stake;

  private Long delegation_stake;

  private List<Validator> active_validators;

  private List<Validator> pending_validators;

  private List<Long> pending_removals;

  private List<ValidatorMetadata> next_epoch_validators;

  private SuiVecMap<ValidatorPair, SuiTableVec> pending_delegation_switches;

  public Long getValidator_stake() {
    return validator_stake;
  }

  public void setValidator_stake(Long validator_stake) {
    this.validator_stake = validator_stake;
  }

  public Long getDelegation_stake() {
    return delegation_stake;
  }

  public void setDelegation_stake(Long delegation_stake) {
    this.delegation_stake = delegation_stake;
  }

  public List<Validator> getActive_validators() {
    return active_validators;
  }

  public void setActive_validators(List<Validator> active_validators) {
    this.active_validators = active_validators;
  }

  public List<Validator> getPending_validators() {
    return pending_validators;
  }

  public void setPending_validators(List<Validator> pending_validators) {
    this.pending_validators = pending_validators;
  }

  public List<Long> getPending_removals() {
    return pending_removals;
  }

  public void setPending_removals(List<Long> pending_removals) {
    this.pending_removals = pending_removals;
  }

  public List<ValidatorMetadata> getNext_epoch_validators() {
    return next_epoch_validators;
  }

  public void setNext_epoch_validators(List<ValidatorMetadata> next_epoch_validators) {
    this.next_epoch_validators = next_epoch_validators;
  }

  public SuiVecMap<ValidatorPair, SuiTableVec> getPending_delegation_switches() {
    return pending_delegation_switches;
  }

  public void setPending_delegation_switches(
      SuiVecMap<ValidatorPair, SuiTableVec> pending_delegation_switches) {
    this.pending_delegation_switches = pending_delegation_switches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidatorSet that = (ValidatorSet) o;
    return validator_stake.equals(that.validator_stake)
        && delegation_stake.equals(that.delegation_stake)
        && active_validators.equals(that.active_validators)
        && pending_validators.equals(that.pending_validators)
        && pending_removals.equals(that.pending_removals)
        && next_epoch_validators.equals(that.next_epoch_validators)
        && pending_delegation_switches.equals(that.pending_delegation_switches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        validator_stake,
        delegation_stake,
        active_validators,
        pending_validators,
        pending_removals,
        next_epoch_validators,
        pending_delegation_switches);
  }

  @Override
  public String toString() {
    return "ValidatorSet{"
        + "validator_stake="
        + validator_stake
        + ", delegation_stake="
        + delegation_stake
        + ", active_validators="
        + active_validators
        + ", pending_validators="
        + pending_validators
        + ", pending_removals="
        + pending_removals
        + ", next_epoch_validators="
        + next_epoch_validators
        + ", pending_delegation_switches="
        + pending_delegation_switches
        + '}';
  }
}
