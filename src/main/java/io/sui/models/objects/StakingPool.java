package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StakingPool {

  @SerializedName(value = "validatorAddress", alternate = "validator_address")
  private String validatorAddress;

  @SerializedName(value = "startingEpoch", alternate = "starting_epoch")
  private Long startingEpoch;

  @SerializedName(value = "suiBalance", alternate = "sui_balance")
  private Long suiBalance;

  @SerializedName(value = "rewardsPool", alternate = "rewards_pool")
  private SuiBalance rewardsPool;

  @SerializedName(value = "delegationTokenSupply", alternate = "delegation_token_supply")
  private SuiSupply delegationTokenSupply;

  @SerializedName(value = "pendingDelegations", alternate = "pending_delegations")
  private SuiLinkedTable<String> pendingDelegations;

  @SerializedName(value = "pendingWithdraws", alternate = "pending_withdraws")
  private SuiTableVec pendingWithdraws;

  public String getValidatorAddress() {
    return validatorAddress;
  }

  public void setValidatorAddress(String validatorAddress) {
    this.validatorAddress = validatorAddress;
  }

  public Long getStartingEpoch() {
    return startingEpoch;
  }

  public void setStartingEpoch(Long startingEpoch) {
    this.startingEpoch = startingEpoch;
  }

  public Long getSuiBalance() {
    return suiBalance;
  }

  public void setSuiBalance(Long suiBalance) {
    this.suiBalance = suiBalance;
  }

  public SuiBalance getRewardsPool() {
    return rewardsPool;
  }

  public void setRewardsPool(SuiBalance rewardsPool) {
    this.rewardsPool = rewardsPool;
  }

  public SuiSupply getDelegationTokenSupply() {
    return delegationTokenSupply;
  }

  public void setDelegationTokenSupply(SuiSupply delegationTokenSupply) {
    this.delegationTokenSupply = delegationTokenSupply;
  }

  public SuiLinkedTable<String> getPendingDelegations() {
    return pendingDelegations;
  }

  public void setPending_delegations(SuiLinkedTable<String> pendingDelegations) {
    this.pendingDelegations = pendingDelegations;
  }

  public SuiTableVec getPendingWithdraws() {
    return pendingWithdraws;
  }

  public void setPendingWithdraws(SuiTableVec pendingWithdraws) {
    this.pendingWithdraws = pendingWithdraws;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StakingPool that = (StakingPool) o;
    return Objects.equal(validatorAddress, that.validatorAddress) && Objects.equal(startingEpoch, that.startingEpoch) && Objects.equal(suiBalance, that.suiBalance) && Objects.equal(rewardsPool, that.rewardsPool) && Objects.equal(delegationTokenSupply, that.delegationTokenSupply) && Objects.equal(pendingDelegations, that.pendingDelegations) && Objects.equal(pendingWithdraws, that.pendingWithdraws);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(validatorAddress, startingEpoch, suiBalance, rewardsPool, delegationTokenSupply, pendingDelegations, pendingWithdraws);
  }

  @Override
  public String toString() {
    return "StakingPool{" +
        "validatorAddress='" + validatorAddress + '\'' +
        ", startingEpoch=" + startingEpoch +
        ", suiBalance=" + suiBalance +
        ", rewardsPool=" + rewardsPool +
        ", delegationTokenSupply=" + delegationTokenSupply +
        ", pendingDelegations=" + pendingDelegations +
        ", pendingWithdraws=" + pendingWithdraws +
        '}';
  }
}
