package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Set;

public class SuiSystemState {

  private SuiUID info;

  private Long epoch;

  private ValidatorSet validators;

  @SerializedName(value = "treasuryCap", alternate = "treasury_cap")
  private SuiSupply treasuryCap;

  @SerializedName(value = "storageFund", alternate = "storage_fund")
  private SuiBalance storageFund;

  private SuiSystemParameters parameters;

  @SerializedName(value = "referenceGasPrice", alternate = "reference_gas_price")
  private Long referenceGasPrice;

  @SerializedName(value = "validatorReportRecords", alternate = "validator_report_records")
  private LinkedHashMap<String, Set<String>> validatorReportRecords;

  @SerializedName(value = "stakeSubsidy", alternate = "stake_subsidy")
  private SuiStakeSubsidy stake_subsidy;

  @SerializedName(value = "safeMode", alternate = "safe_mode")
  private Boolean safeMode;

  @SerializedName(value = "epochStartTimestampMs", alternate = "epoch_start_timestamp_ms")
  private Long epochStartTimestampMs;

  public SuiUID getInfo() {
    return info;
  }

  public void setInfo(SuiUID info) {
    this.info = info;
  }

  public Long getEpoch() {
    return epoch;
  }

  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  public ValidatorSet getValidators() {
    return validators;
  }

  public void setValidators(ValidatorSet validators) {
    this.validators = validators;
  }

  public SuiSupply getTreasuryCap() {
    return treasuryCap;
  }

  public void setTreasuryCap(SuiSupply treasuryCap) {
    this.treasuryCap = treasuryCap;
  }

  public SuiBalance getStorageFund() {
    return storageFund;
  }

  public void setStorageFund(SuiBalance storageFund) {
    this.storageFund = storageFund;
  }

  public SuiSystemParameters getParameters() {
    return parameters;
  }

  public void setParameters(SuiSystemParameters parameters) {
    this.parameters = parameters;
  }

  public Long getReferenceGasPrice() {
    return referenceGasPrice;
  }

  public void setReferenceGasPrice(Long referenceGasPrice) {
    this.referenceGasPrice = referenceGasPrice;
  }

  public LinkedHashMap<String, Set<String>> getValidatorReportRecords() {
    return validatorReportRecords;
  }

  public void setValidatorReportRecords(LinkedHashMap<String, Set<String>> validatorReportRecords) {
    this.validatorReportRecords = validatorReportRecords;
  }

  public SuiStakeSubsidy getStake_subsidy() {
    return stake_subsidy;
  }

  public void setStake_subsidy(SuiStakeSubsidy stake_subsidy) {
    this.stake_subsidy = stake_subsidy;
  }

  public Boolean getSafeMode() {
    return safeMode;
  }

  public void setSafeMode(Boolean safeMode) {
    this.safeMode = safeMode;
  }

  public Long getEpochStartTimestampMs() {
    return epochStartTimestampMs;
  }

  public void setEpochStartTimestampMs(Long epochStartTimestampMs) {
    this.epochStartTimestampMs = epochStartTimestampMs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiSystemState that = (SuiSystemState) o;
    return Objects.equal(info, that.info) && Objects.equal(epoch, that.epoch) && Objects.equal(validators, that.validators) && Objects.equal(treasuryCap, that.treasuryCap) && Objects.equal(storageFund, that.storageFund) && Objects.equal(parameters, that.parameters) && Objects.equal(referenceGasPrice, that.referenceGasPrice) && Objects.equal(validatorReportRecords, that.validatorReportRecords) && Objects.equal(stake_subsidy, that.stake_subsidy) && Objects.equal(safeMode, that.safeMode) && Objects.equal(epochStartTimestampMs, that.epochStartTimestampMs);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(info, epoch, validators, treasuryCap, storageFund, parameters, referenceGasPrice, validatorReportRecords, stake_subsidy, safeMode, epochStartTimestampMs);
  }

  @Override
  public String toString() {
    return "SuiSystemState{" +
        "info='" + info + '\'' +
        ", epoch=" + epoch +
        ", validators=" + validators +
        ", treasuryCap=" + treasuryCap +
        ", storageFund=" + storageFund +
        ", parameters=" + parameters +
        ", referenceGasPrice=" + referenceGasPrice +
        ", validatorReportRecords=" + validatorReportRecords +
        ", stake_subsidy=" + stake_subsidy +
        ", safeMode=" + safeMode +
        ", epochStartTimestampMs=" + epochStartTimestampMs +
        '}';
  }
}
