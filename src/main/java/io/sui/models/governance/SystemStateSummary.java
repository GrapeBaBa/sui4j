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

package io.sui.models.governance;


import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * The type System state summary.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class SystemStateSummary {

  private BigInteger epoch;

  private BigInteger protocolVersion;

  private BigInteger systemStateVersion;

  private BigInteger storageFundTotalObjectStorageRebates;
  private BigInteger storageFundNonRefundableBalance;
  private BigInteger referenceGasPrice;
  private boolean safeMode;
  private BigInteger safeModeStorageRewards;
  private BigInteger safeModeComputationRewards;
  private BigInteger safeModeStorageRebates;
  private BigInteger safeModeNonRefundableStorageFee;
  private BigInteger epochStartTimestampMs;
  private BigInteger epochDurationMs;
  private BigInteger stakeSubsidyStartEpoch;
  private BigInteger maxValidatorCount;
  private BigInteger minValidatorJoiningStake;
  private BigInteger validatorLowStakeThreshold;
  private BigInteger validatorVeryLowStakeThreshold;
  private BigInteger validatorLowStakeGracePeriod;
  private BigInteger stakeSubsidyBalance;
  private BigInteger stakeSubsidyDistributionCounter;
  private BigInteger stakeSubsidyCurrentDistributionAmount;
  private BigInteger stakeSubsidyPeriodLength;
  private Integer stakeSubsidyDecreaseRate;
  private BigInteger totalStake;
  private List<ValidatorSummary> activeValidators;
  private String pendingActiveValidatorsId;
  private BigInteger pendingActiveValidatorsSize;
  private List<BigInteger> pendingRemovals;
  private String stakingPoolMappingsId;
  private BigInteger stakingPoolMappingsSize;
  private String inactivePoolsId;
  private BigInteger inactivePoolsSize;
  private String validatorCandidatesId;
  private BigInteger validatorCandidatesSize;
  private List<List<?>> atRiskValidators;
  private List<List<?>> validatorReportRecords;

  /**
   * Gets epoch.
   *
   * @return the epoch
   */
  public BigInteger getEpoch() {
    return epoch;
  }

  /**
   * Sets epoch.
   *
   * @param epoch the epoch
   */
  public void setEpoch(BigInteger epoch) {
    this.epoch = epoch;
  }

  /**
   * Gets protocol version.
   *
   * @return the protocol version
   */
  public BigInteger getProtocolVersion() {
    return protocolVersion;
  }

  /**
   * Sets protocol version.
   *
   * @param protocolVersion the protocol version
   */
  public void setProtocolVersion(BigInteger protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  /**
   * Gets system state version.
   *
   * @return the system state version
   */
  public BigInteger getSystemStateVersion() {
    return systemStateVersion;
  }

  /**
   * Sets system state version.
   *
   * @param systemStateVersion the system state version
   */
  public void setSystemStateVersion(BigInteger systemStateVersion) {
    this.systemStateVersion = systemStateVersion;
  }

  /**
   * Gets storage fund total object storage rebates.
   *
   * @return the storage fund total object storage rebates
   */
  public BigInteger getStorageFundTotalObjectStorageRebates() {
    return storageFundTotalObjectStorageRebates;
  }

  /**
   * Sets storage fund total object storage rebates.
   *
   * @param storageFundTotalObjectStorageRebates the storage fund total object storage rebates
   */
  public void setStorageFundTotalObjectStorageRebates(
      BigInteger storageFundTotalObjectStorageRebates) {
    this.storageFundTotalObjectStorageRebates = storageFundTotalObjectStorageRebates;
  }

  /**
   * Gets storage fund non refundable balance.
   *
   * @return the storage fund non refundable balance
   */
  public BigInteger getStorageFundNonRefundableBalance() {
    return storageFundNonRefundableBalance;
  }

  /**
   * Sets storage fund non refundable balance.
   *
   * @param storageFundNonRefundableBalance the storage fund non refundable balance
   */
  public void setStorageFundNonRefundableBalance(BigInteger storageFundNonRefundableBalance) {
    this.storageFundNonRefundableBalance = storageFundNonRefundableBalance;
  }

  /**
   * Gets reference gas price.
   *
   * @return the reference gas price
   */
  public BigInteger getReferenceGasPrice() {
    return referenceGasPrice;
  }

  /**
   * Sets reference gas price.
   *
   * @param referenceGasPrice the reference gas price
   */
  public void setReferenceGasPrice(BigInteger referenceGasPrice) {
    this.referenceGasPrice = referenceGasPrice;
  }

  /**
   * Is safe mode boolean.
   *
   * @return the boolean
   */
  public boolean isSafeMode() {
    return safeMode;
  }

  /**
   * Sets safe mode.
   *
   * @param safeMode the safe mode
   */
  public void setSafeMode(boolean safeMode) {
    this.safeMode = safeMode;
  }

  /**
   * Gets safe mode storage rewards.
   *
   * @return the safe mode storage rewards
   */
  public BigInteger getSafeModeStorageRewards() {
    return safeModeStorageRewards;
  }

  /**
   * Sets safe mode storage rewards.
   *
   * @param safeModeStorageRewards the safe mode storage rewards
   */
  public void setSafeModeStorageRewards(BigInteger safeModeStorageRewards) {
    this.safeModeStorageRewards = safeModeStorageRewards;
  }

  /**
   * Gets safe mode computation rewards.
   *
   * @return the safe mode computation rewards
   */
  public BigInteger getSafeModeComputationRewards() {
    return safeModeComputationRewards;
  }

  /**
   * Sets safe mode computation rewards.
   *
   * @param safeModeComputationRewards the safe mode computation rewards
   */
  public void setSafeModeComputationRewards(BigInteger safeModeComputationRewards) {
    this.safeModeComputationRewards = safeModeComputationRewards;
  }

  /**
   * Gets safe mode storage rebates.
   *
   * @return the safe mode storage rebates
   */
  public BigInteger getSafeModeStorageRebates() {
    return safeModeStorageRebates;
  }

  /**
   * Sets safe mode storage rebates.
   *
   * @param safeModeStorageRebates the safe mode storage rebates
   */
  public void setSafeModeStorageRebates(BigInteger safeModeStorageRebates) {
    this.safeModeStorageRebates = safeModeStorageRebates;
  }

  /**
   * Gets safe mode non refundable storage fee.
   *
   * @return the safe mode non refundable storage fee
   */
  public BigInteger getSafeModeNonRefundableStorageFee() {
    return safeModeNonRefundableStorageFee;
  }

  /**
   * Sets safe mode non refundable storage fee.
   *
   * @param safeModeNonRefundableStorageFee the safe mode non refundable storage fee
   */
  public void setSafeModeNonRefundableStorageFee(BigInteger safeModeNonRefundableStorageFee) {
    this.safeModeNonRefundableStorageFee = safeModeNonRefundableStorageFee;
  }

  /**
   * Gets epoch start timestamp ms.
   *
   * @return the epoch start timestamp ms
   */
  public BigInteger getEpochStartTimestampMs() {
    return epochStartTimestampMs;
  }

  /**
   * Sets epoch start timestamp ms.
   *
   * @param epochStartTimestampMs the epoch start timestamp ms
   */
  public void setEpochStartTimestampMs(BigInteger epochStartTimestampMs) {
    this.epochStartTimestampMs = epochStartTimestampMs;
  }

  /**
   * Gets epoch duration ms.
   *
   * @return the epoch duration ms
   */
  public BigInteger getEpochDurationMs() {
    return epochDurationMs;
  }

  /**
   * Sets epoch duration ms.
   *
   * @param epochDurationMs the epoch duration ms
   */
  public void setEpochDurationMs(BigInteger epochDurationMs) {
    this.epochDurationMs = epochDurationMs;
  }

  /**
   * Gets stake subsidy start epoch.
   *
   * @return the stake subsidy start epoch
   */
  public BigInteger getStakeSubsidyStartEpoch() {
    return stakeSubsidyStartEpoch;
  }

  /**
   * Sets stake subsidy start epoch.
   *
   * @param stakeSubsidyStartEpoch the stake subsidy start epoch
   */
  public void setStakeSubsidyStartEpoch(BigInteger stakeSubsidyStartEpoch) {
    this.stakeSubsidyStartEpoch = stakeSubsidyStartEpoch;
  }

  /**
   * Gets max validator count.
   *
   * @return the max validator count
   */
  public BigInteger getMaxValidatorCount() {
    return maxValidatorCount;
  }

  /**
   * Sets max validator count.
   *
   * @param maxValidatorCount the max validator count
   */
  public void setMaxValidatorCount(BigInteger maxValidatorCount) {
    this.maxValidatorCount = maxValidatorCount;
  }

  /**
   * Gets min validator joining stake.
   *
   * @return the min validator joining stake
   */
  public BigInteger getMinValidatorJoiningStake() {
    return minValidatorJoiningStake;
  }

  /**
   * Sets min validator joining stake.
   *
   * @param minValidatorJoiningStake the min validator joining stake
   */
  public void setMinValidatorJoiningStake(BigInteger minValidatorJoiningStake) {
    this.minValidatorJoiningStake = minValidatorJoiningStake;
  }

  /**
   * Gets validator low stake threshold.
   *
   * @return the validator low stake threshold
   */
  public BigInteger getValidatorLowStakeThreshold() {
    return validatorLowStakeThreshold;
  }

  /**
   * Sets validator low stake threshold.
   *
   * @param validatorLowStakeThreshold the validator low stake threshold
   */
  public void setValidatorLowStakeThreshold(BigInteger validatorLowStakeThreshold) {
    this.validatorLowStakeThreshold = validatorLowStakeThreshold;
  }

  /**
   * Gets validator very low stake threshold.
   *
   * @return the validator very low stake threshold
   */
  public BigInteger getValidatorVeryLowStakeThreshold() {
    return validatorVeryLowStakeThreshold;
  }

  /**
   * Sets validator very low stake threshold.
   *
   * @param validatorVeryLowStakeThreshold the validator very low stake threshold
   */
  public void setValidatorVeryLowStakeThreshold(BigInteger validatorVeryLowStakeThreshold) {
    this.validatorVeryLowStakeThreshold = validatorVeryLowStakeThreshold;
  }

  /**
   * Gets validator low stake grace period.
   *
   * @return the validator low stake grace period
   */
  public BigInteger getValidatorLowStakeGracePeriod() {
    return validatorLowStakeGracePeriod;
  }

  /**
   * Sets validator low stake grace period.
   *
   * @param validatorLowStakeGracePeriod the validator low stake grace period
   */
  public void setValidatorLowStakeGracePeriod(BigInteger validatorLowStakeGracePeriod) {
    this.validatorLowStakeGracePeriod = validatorLowStakeGracePeriod;
  }

  /**
   * Gets stake subsidy balance.
   *
   * @return the stake subsidy balance
   */
  public BigInteger getStakeSubsidyBalance() {
    return stakeSubsidyBalance;
  }

  /**
   * Sets stake subsidy balance.
   *
   * @param stakeSubsidyBalance the stake subsidy balance
   */
  public void setStakeSubsidyBalance(BigInteger stakeSubsidyBalance) {
    this.stakeSubsidyBalance = stakeSubsidyBalance;
  }

  /**
   * Gets stake subsidy distribution counter.
   *
   * @return the stake subsidy distribution counter
   */
  public BigInteger getStakeSubsidyDistributionCounter() {
    return stakeSubsidyDistributionCounter;
  }

  /**
   * Sets stake subsidy distribution counter.
   *
   * @param stakeSubsidyDistributionCounter the stake subsidy distribution counter
   */
  public void setStakeSubsidyDistributionCounter(BigInteger stakeSubsidyDistributionCounter) {
    this.stakeSubsidyDistributionCounter = stakeSubsidyDistributionCounter;
  }

  /**
   * Gets stake subsidy current distribution amount.
   *
   * @return the stake subsidy current distribution amount
   */
  public BigInteger getStakeSubsidyCurrentDistributionAmount() {
    return stakeSubsidyCurrentDistributionAmount;
  }

  /**
   * Sets stake subsidy current distribution amount.
   *
   * @param stakeSubsidyCurrentDistributionAmount the stake subsidy current distribution amount
   */
  public void setStakeSubsidyCurrentDistributionAmount(
      BigInteger stakeSubsidyCurrentDistributionAmount) {
    this.stakeSubsidyCurrentDistributionAmount = stakeSubsidyCurrentDistributionAmount;
  }

  /**
   * Gets stake subsidy period length.
   *
   * @return the stake subsidy period length
   */
  public BigInteger getStakeSubsidyPeriodLength() {
    return stakeSubsidyPeriodLength;
  }

  /**
   * Sets stake subsidy period length.
   *
   * @param stakeSubsidyPeriodLength the stake subsidy period length
   */
  public void setStakeSubsidyPeriodLength(BigInteger stakeSubsidyPeriodLength) {
    this.stakeSubsidyPeriodLength = stakeSubsidyPeriodLength;
  }

  /**
   * Gets stake subsidy decrease rate.
   *
   * @return the stake subsidy decrease rate
   */
  public Integer getStakeSubsidyDecreaseRate() {
    return stakeSubsidyDecreaseRate;
  }

  /**
   * Sets stake subsidy decrease rate.
   *
   * @param stakeSubsidyDecreaseRate the stake subsidy decrease rate
   */
  public void setStakeSubsidyDecreaseRate(Integer stakeSubsidyDecreaseRate) {
    this.stakeSubsidyDecreaseRate = stakeSubsidyDecreaseRate;
  }

  /**
   * Gets total stake.
   *
   * @return the total stake
   */
  public BigInteger getTotalStake() {
    return totalStake;
  }

  /**
   * Sets total stake.
   *
   * @param totalStake the total stake
   */
  public void setTotalStake(BigInteger totalStake) {
    this.totalStake = totalStake;
  }

  /**
   * Gets active validators.
   *
   * @return the active validators
   */
  public List<ValidatorSummary> getActiveValidators() {
    return activeValidators;
  }

  /**
   * Sets active validators.
   *
   * @param activeValidators the active validators
   */
  public void setActiveValidators(List<ValidatorSummary> activeValidators) {
    this.activeValidators = activeValidators;
  }

  /**
   * Gets pending active validators id.
   *
   * @return the pending active validators id
   */
  public String getPendingActiveValidatorsId() {
    return pendingActiveValidatorsId;
  }

  /**
   * Sets pending active validators id.
   *
   * @param pendingActiveValidatorsId the pending active validators id
   */
  public void setPendingActiveValidatorsId(String pendingActiveValidatorsId) {
    this.pendingActiveValidatorsId = pendingActiveValidatorsId;
  }

  /**
   * Gets pending active validators size.
   *
   * @return the pending active validators size
   */
  public BigInteger getPendingActiveValidatorsSize() {
    return pendingActiveValidatorsSize;
  }

  /**
   * Sets pending active validators size.
   *
   * @param pendingActiveValidatorsSize the pending active validators size
   */
  public void setPendingActiveValidatorsSize(BigInteger pendingActiveValidatorsSize) {
    this.pendingActiveValidatorsSize = pendingActiveValidatorsSize;
  }

  /**
   * Gets pending removals.
   *
   * @return the pending removals
   */
  public List<BigInteger> getPendingRemovals() {
    return pendingRemovals;
  }

  /**
   * Sets pending removals.
   *
   * @param pendingRemovals the pending removals
   */
  public void setPendingRemovals(List<BigInteger> pendingRemovals) {
    this.pendingRemovals = pendingRemovals;
  }

  /**
   * Gets staking pool mappings id.
   *
   * @return the staking pool mappings id
   */
  public String getStakingPoolMappingsId() {
    return stakingPoolMappingsId;
  }

  /**
   * Sets staking pool mappings id.
   *
   * @param stakingPoolMappingsId the staking pool mappings id
   */
  public void setStakingPoolMappingsId(String stakingPoolMappingsId) {
    this.stakingPoolMappingsId = stakingPoolMappingsId;
  }

  /**
   * Gets staking pool mappings size.
   *
   * @return the staking pool mappings size
   */
  public BigInteger getStakingPoolMappingsSize() {
    return stakingPoolMappingsSize;
  }

  /**
   * Sets staking pool mappings size.
   *
   * @param stakingPoolMappingsSize the staking pool mappings size
   */
  public void setStakingPoolMappingsSize(BigInteger stakingPoolMappingsSize) {
    this.stakingPoolMappingsSize = stakingPoolMappingsSize;
  }

  /**
   * Gets inactive pools id.
   *
   * @return the inactive pools id
   */
  public String getInactivePoolsId() {
    return inactivePoolsId;
  }

  /**
   * Sets inactive pools id.
   *
   * @param inactivePoolsId the inactive pools id
   */
  public void setInactivePoolsId(String inactivePoolsId) {
    this.inactivePoolsId = inactivePoolsId;
  }

  /**
   * Gets inactive pools size.
   *
   * @return the inactive pools size
   */
  public BigInteger getInactivePoolsSize() {
    return inactivePoolsSize;
  }

  /**
   * Sets inactive pools size.
   *
   * @param inactivePoolsSize the inactive pools size
   */
  public void setInactivePoolsSize(BigInteger inactivePoolsSize) {
    this.inactivePoolsSize = inactivePoolsSize;
  }

  /**
   * Gets validator candidates id.
   *
   * @return the validator candidates id
   */
  public String getValidatorCandidatesId() {
    return validatorCandidatesId;
  }

  /**
   * Sets validator candidates id.
   *
   * @param validatorCandidatesId the validator candidates id
   */
  public void setValidatorCandidatesId(String validatorCandidatesId) {
    this.validatorCandidatesId = validatorCandidatesId;
  }

  /**
   * Gets validator candidates size.
   *
   * @return the validator candidates size
   */
  public BigInteger getValidatorCandidatesSize() {
    return validatorCandidatesSize;
  }

  /**
   * Sets validator candidates size.
   *
   * @param validatorCandidatesSize the validator candidates size
   */
  public void setValidatorCandidatesSize(BigInteger validatorCandidatesSize) {
    this.validatorCandidatesSize = validatorCandidatesSize;
  }

  /**
   * Gets at risk validators.
   *
   * @return the at risk validators
   */
  public List<List<?>> getAtRiskValidators() {
    return atRiskValidators;
  }

  /**
   * Sets at risk validators.
   *
   * @param atRiskValidators the at risk validators
   */
  public void setAtRiskValidators(List<List<?>> atRiskValidators) {
    this.atRiskValidators = atRiskValidators;
  }

  /**
   * Gets validator report records.
   *
   * @return the validator report records
   */
  public List<List<?>> getValidatorReportRecords() {
    return validatorReportRecords;
  }

  /**
   * Sets validator report records.
   *
   * @param validatorReportRecords the validator report records
   */
  public void setValidatorReportRecords(List<List<?>> validatorReportRecords) {
    this.validatorReportRecords = validatorReportRecords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SystemStateSummary)) {
      return false;
    }
    SystemStateSummary that = (SystemStateSummary) o;
    return safeMode == that.safeMode
        && Objects.equals(epoch, that.epoch)
        && Objects.equals(protocolVersion, that.protocolVersion)
        && Objects.equals(systemStateVersion, that.systemStateVersion)
        && Objects.equals(
            storageFundTotalObjectStorageRebates, that.storageFundTotalObjectStorageRebates)
        && Objects.equals(storageFundNonRefundableBalance, that.storageFundNonRefundableBalance)
        && Objects.equals(referenceGasPrice, that.referenceGasPrice)
        && Objects.equals(safeModeStorageRewards, that.safeModeStorageRewards)
        && Objects.equals(safeModeComputationRewards, that.safeModeComputationRewards)
        && Objects.equals(safeModeStorageRebates, that.safeModeStorageRebates)
        && Objects.equals(safeModeNonRefundableStorageFee, that.safeModeNonRefundableStorageFee)
        && Objects.equals(epochStartTimestampMs, that.epochStartTimestampMs)
        && Objects.equals(epochDurationMs, that.epochDurationMs)
        && Objects.equals(stakeSubsidyStartEpoch, that.stakeSubsidyStartEpoch)
        && Objects.equals(maxValidatorCount, that.maxValidatorCount)
        && Objects.equals(minValidatorJoiningStake, that.minValidatorJoiningStake)
        && Objects.equals(validatorLowStakeThreshold, that.validatorLowStakeThreshold)
        && Objects.equals(validatorVeryLowStakeThreshold, that.validatorVeryLowStakeThreshold)
        && Objects.equals(validatorLowStakeGracePeriod, that.validatorLowStakeGracePeriod)
        && Objects.equals(stakeSubsidyBalance, that.stakeSubsidyBalance)
        && Objects.equals(stakeSubsidyDistributionCounter, that.stakeSubsidyDistributionCounter)
        && Objects.equals(
            stakeSubsidyCurrentDistributionAmount, that.stakeSubsidyCurrentDistributionAmount)
        && Objects.equals(stakeSubsidyPeriodLength, that.stakeSubsidyPeriodLength)
        && Objects.equals(stakeSubsidyDecreaseRate, that.stakeSubsidyDecreaseRate)
        && Objects.equals(totalStake, that.totalStake)
        && Objects.equals(activeValidators, that.activeValidators)
        && Objects.equals(pendingActiveValidatorsId, that.pendingActiveValidatorsId)
        && Objects.equals(pendingActiveValidatorsSize, that.pendingActiveValidatorsSize)
        && Objects.equals(pendingRemovals, that.pendingRemovals)
        && Objects.equals(stakingPoolMappingsId, that.stakingPoolMappingsId)
        && Objects.equals(stakingPoolMappingsSize, that.stakingPoolMappingsSize)
        && Objects.equals(inactivePoolsId, that.inactivePoolsId)
        && Objects.equals(inactivePoolsSize, that.inactivePoolsSize)
        && Objects.equals(validatorCandidatesId, that.validatorCandidatesId)
        && Objects.equals(validatorCandidatesSize, that.validatorCandidatesSize)
        && Objects.equals(atRiskValidators, that.atRiskValidators)
        && Objects.equals(validatorReportRecords, that.validatorReportRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        epoch,
        protocolVersion,
        systemStateVersion,
        storageFundTotalObjectStorageRebates,
        storageFundNonRefundableBalance,
        referenceGasPrice,
        safeMode,
        safeModeStorageRewards,
        safeModeComputationRewards,
        safeModeStorageRebates,
        safeModeNonRefundableStorageFee,
        epochStartTimestampMs,
        epochDurationMs,
        stakeSubsidyStartEpoch,
        maxValidatorCount,
        minValidatorJoiningStake,
        validatorLowStakeThreshold,
        validatorVeryLowStakeThreshold,
        validatorLowStakeGracePeriod,
        stakeSubsidyBalance,
        stakeSubsidyDistributionCounter,
        stakeSubsidyCurrentDistributionAmount,
        stakeSubsidyPeriodLength,
        stakeSubsidyDecreaseRate,
        totalStake,
        activeValidators,
        pendingActiveValidatorsId,
        pendingActiveValidatorsSize,
        pendingRemovals,
        stakingPoolMappingsId,
        stakingPoolMappingsSize,
        inactivePoolsId,
        inactivePoolsSize,
        validatorCandidatesId,
        validatorCandidatesSize,
        atRiskValidators,
        validatorReportRecords);
  }

  @Override
  public String toString() {
    return "SystemStateSummary{"
        + "epoch="
        + epoch
        + ", protocolVersion="
        + protocolVersion
        + ", systemStateVersion="
        + systemStateVersion
        + ", storageFundTotalObjectStorageRebates="
        + storageFundTotalObjectStorageRebates
        + ", storageFundNonRefundableBalance="
        + storageFundNonRefundableBalance
        + ", referenceGasPrice="
        + referenceGasPrice
        + ", safeMode="
        + safeMode
        + ", safeModeStorageRewards="
        + safeModeStorageRewards
        + ", safeModeComputationRewards="
        + safeModeComputationRewards
        + ", safeModeStorageRebates="
        + safeModeStorageRebates
        + ", safeModeNonRefundableStorageFee="
        + safeModeNonRefundableStorageFee
        + ", epochStartTimestampMs="
        + epochStartTimestampMs
        + ", epochDurationMs="
        + epochDurationMs
        + ", stakeSubsidyStartEpoch="
        + stakeSubsidyStartEpoch
        + ", maxValidatorCount="
        + maxValidatorCount
        + ", minValidatorJoiningStake="
        + minValidatorJoiningStake
        + ", validatorLowStakeThreshold="
        + validatorLowStakeThreshold
        + ", validatorVeryLowStakeThreshold="
        + validatorVeryLowStakeThreshold
        + ", validatorLowStakeGracePeriod="
        + validatorLowStakeGracePeriod
        + ", stakeSubsidyBalance="
        + stakeSubsidyBalance
        + ", stakeSubsidyDistributionCounter="
        + stakeSubsidyDistributionCounter
        + ", stakeSubsidyCurrentDistributionAmount="
        + stakeSubsidyCurrentDistributionAmount
        + ", stakeSubsidyPeriodLength="
        + stakeSubsidyPeriodLength
        + ", stakeSubsidyDecreaseRate="
        + stakeSubsidyDecreaseRate
        + ", totalStake="
        + totalStake
        + ", activeValidators="
        + activeValidators
        + ", pendingActiveValidatorsId='"
        + pendingActiveValidatorsId
        + '\''
        + ", pendingActiveValidatorsSize="
        + pendingActiveValidatorsSize
        + ", pendingRemovals="
        + pendingRemovals
        + ", stakingPoolMappingsId='"
        + stakingPoolMappingsId
        + '\''
        + ", stakingPoolMappingsSize="
        + stakingPoolMappingsSize
        + ", inactivePoolsId='"
        + inactivePoolsId
        + '\''
        + ", inactivePoolsSize="
        + inactivePoolsSize
        + ", validatorCandidatesId='"
        + validatorCandidatesId
        + '\''
        + ", validatorCandidatesSize="
        + validatorCandidatesSize
        + ", atRiskValidators="
        + atRiskValidators
        + ", validatorReportRecords="
        + validatorReportRecords
        + '}';
  }
}
