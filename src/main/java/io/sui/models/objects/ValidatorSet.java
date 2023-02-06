package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;

public class ValidatorSet {

  @SerializedName(value = "validatorStake", alternate = "validator_stake")
  private Long validatorStake;

  @SerializedName(value = "delegationStake", alternate = "delegation_stake")
  private Long delegationStake;

  @SerializedName(value = "activeValidators", alternate = "active_validators")
  private List<Validator> activeValidators;

  @SerializedName(value = "pendingValidators", alternate = "pending_validators")
  private List<Validator> pendingValidators;

  @SerializedName(value = "pendingRemovals", alternate = "pending_removals")
  private List<Long> pendingRemovals;

  @SerializedName(value = "nextEpochValidators", alternate = "next_epoch_validators")
  private List<ValidatorMetadata> nextEpochValidators;

  @SerializedName(value = "pendingDelegationSwitches", alternate = "pending_delegation_switches")
  private SuiVecMap<ValidatorPair, SuiTableVec> pendingDelegationSwitches;

  public Long getValidatorStake() {
    return validatorStake;
  }

  public void setValidatorStake(Long validatorStake) {
    this.validatorStake = validatorStake;
  }

  public Long getDelegationStake() {
    return delegationStake;
  }

  public void setDelegationStake(Long delegationStake) {
    this.delegationStake = delegationStake;
  }

  public List<Validator> getActiveValidators() {
    return activeValidators;
  }

  public void setActiveValidators(List<Validator> activeValidators) {
    this.activeValidators = activeValidators;
  }

  public List<Validator> getPendingValidators() {
    return pendingValidators;
  }

  public void setPendingValidators(List<Validator> pendingValidators) {
    this.pendingValidators = pendingValidators;
  }

  public List<Long> getPendingRemovals() {
    return pendingRemovals;
  }

  public void setPendingRemovals(List<Long> pendingRemovals) {
    this.pendingRemovals = pendingRemovals;
  }

  public List<ValidatorMetadata> getNextEpochValidators() {
    return nextEpochValidators;
  }

  public void setNextEpochValidators(List<ValidatorMetadata> nextEpochValidators) {
    this.nextEpochValidators = nextEpochValidators;
  }

  public SuiVecMap<ValidatorPair, SuiTableVec> getPendingDelegationSwitches() {
    return pendingDelegationSwitches;
  }

  public void setPendingDelegationSwitches(SuiVecMap<ValidatorPair, SuiTableVec> pendingDelegationSwitches) {
    this.pendingDelegationSwitches = pendingDelegationSwitches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ValidatorSet that = (ValidatorSet) o;
    return Objects.equal(validatorStake, that.validatorStake) && Objects.equal(delegationStake, that.delegationStake) && Objects.equal(activeValidators, that.activeValidators) && Objects.equal(pendingValidators, that.pendingValidators) && Objects.equal(pendingRemovals, that.pendingRemovals) && Objects.equal(nextEpochValidators, that.nextEpochValidators) && Objects.equal(pendingDelegationSwitches, that.pendingDelegationSwitches);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(validatorStake, delegationStake, activeValidators, pendingValidators, pendingRemovals, nextEpochValidators, pendingDelegationSwitches);
  }

  @Override
  public String toString() {
    return "ValidatorSet{" +
        "validatorStake=" + validatorStake +
        ", delegationStake=" + delegationStake +
        ", activeValidators=" + activeValidators +
        ", pendingValidators=" + pendingValidators +
        ", pendingRemovals=" + pendingRemovals +
        ", nextEpochValidators=" + nextEpochValidators +
        ", pendingDelegationSwitches=" + pendingDelegationSwitches +
        '}';
  }
}
