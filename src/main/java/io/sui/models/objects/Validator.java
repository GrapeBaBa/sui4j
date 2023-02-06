package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

public class Validator {

  private ValidatorMetadata metadata;

  @SerializedName(value = "votingPower", alternate = "voting_power")
  private Long votingPower;

  @SerializedName(value = "stakeAmount", alternate = "stake_amount")
  private Long stakeAmount;

  @SerializedName(value = "pendingStake", alternate = "pending_stake")
  private Long pendingStake;

  @SerializedName(value = "pendingWithdraw", alternate = "pending_withdraw")
  private Long pendingWithdraw;

  @SerializedName(value = "gasPrice", alternate = "gas_price")
  private Long gasPrice;

  @SerializedName(value = "delegationStakingPool", alternate = "delegation_staking_pool")
  private StakingPool delegationStakingPool;

  @SerializedName(value = "commissionRate", alternate = "commission_rate")
  private Long commissionRate;

  public ValidatorMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(ValidatorMetadata metadata) {
    this.metadata = metadata;
  }

  public Long getVotingPower() {
    return votingPower;
  }

  public void setVotingPower(Long votingPower) {
    this.votingPower = votingPower;
  }

  public Long getStakeAmount() {
    return stakeAmount;
  }

  public void setStakeAmount(Long stakeAmount) {
    this.stakeAmount = stakeAmount;
  }

  public Long getPendingStake() {
    return pendingStake;
  }

  public void setPendingStake(Long pendingStake) {
    this.pendingStake = pendingStake;
  }

  public Long getPendingWithdraw() {
    return pendingWithdraw;
  }

  public void setPendingWithdraw(Long pendingWithdraw) {
    this.pendingWithdraw = pendingWithdraw;
  }

  public Long getGasPrice() {
    return gasPrice;
  }

  public void setGasPrice(Long gasPrice) {
    this.gasPrice = gasPrice;
  }

  public StakingPool getDelegationStakingPool() {
    return delegationStakingPool;
  }

  public void setDelegationStakingPool(StakingPool delegationStakingPool) {
    this.delegationStakingPool = delegationStakingPool;
  }

  public Long getCommissionRate() {
    return commissionRate;
  }

  public void setCommissionRate(Long commissionRate) {
    this.commissionRate = commissionRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Validator validator = (Validator) o;
    return Objects.equal(metadata, validator.metadata) && Objects.equal(votingPower, validator.votingPower) && Objects.equal(stakeAmount, validator.stakeAmount) && Objects.equal(pendingStake, validator.pendingStake) && Objects.equal(pendingWithdraw, validator.pendingWithdraw) && Objects.equal(gasPrice, validator.gasPrice) && Objects.equal(delegationStakingPool, validator.delegationStakingPool) && Objects.equal(commissionRate, validator.commissionRate);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(metadata, votingPower, stakeAmount, pendingStake, pendingWithdraw, gasPrice, delegationStakingPool, commissionRate);
  }

  @Override
  public String toString() {
    return "Validator{" +
        "metadata=" + metadata +
        ", votingPower=" + votingPower +
        ", stakeAmount=" + stakeAmount +
        ", pendingStake=" + pendingStake +
        ", pendingWithdraw=" + pendingWithdraw +
        ", gasPrice=" + gasPrice +
        ", delegationStakingPool=" + delegationStakingPool +
        ", commissionRate=" + commissionRate +
        '}';
  }
}
