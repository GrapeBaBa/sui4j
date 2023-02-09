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


import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

/**
 * The move sui system state type.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
@SuppressWarnings({"checkstyle:MemberName", "checkstyle:ParameterName"})
public class SuiSystemState {

  private SuiUID info;

  private Long epoch;

  private ValidatorSet validators;

  private SuiSupply treasury_cap;

  private SuiBalance storage_fund;

  private SuiSystemParameters parameters;

  private Long reference_gas_price;

  private LinkedHashMap<String, Set<String>> validator_report_records;

  private SuiStakeSubsidy stake_subsidy;

  private Boolean safe_mode;

  private Long epoch_start_timestamp_ms;

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

  public SuiSupply getTreasury_cap() {
    return treasury_cap;
  }

  public void setTreasury_cap(SuiSupply treasury_cap) {
    this.treasury_cap = treasury_cap;
  }

  public SuiBalance getStorage_fund() {
    return storage_fund;
  }

  public void setStorage_fund(SuiBalance storage_fund) {
    this.storage_fund = storage_fund;
  }

  public SuiSystemParameters getParameters() {
    return parameters;
  }

  public void setParameters(SuiSystemParameters parameters) {
    this.parameters = parameters;
  }

  public Long getReference_gas_price() {
    return reference_gas_price;
  }

  public void setReference_gas_price(Long reference_gas_price) {
    this.reference_gas_price = reference_gas_price;
  }

  public LinkedHashMap<String, Set<String>> getValidator_report_records() {
    return validator_report_records;
  }

  public void setValidator_report_records(
      LinkedHashMap<String, Set<String>> validator_report_records) {
    this.validator_report_records = validator_report_records;
  }

  public SuiStakeSubsidy getStake_subsidy() {
    return stake_subsidy;
  }

  public void setStake_subsidy(SuiStakeSubsidy stake_subsidy) {
    this.stake_subsidy = stake_subsidy;
  }

  public Boolean getSafe_mode() {
    return safe_mode;
  }

  public void setSafe_mode(Boolean safe_mode) {
    this.safe_mode = safe_mode;
  }

  public Long getEpoch_start_timestamp_ms() {
    return epoch_start_timestamp_ms;
  }

  public void setEpoch_start_timestamp_ms(Long epoch_start_timestamp_ms) {
    this.epoch_start_timestamp_ms = epoch_start_timestamp_ms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuiSystemState that = (SuiSystemState) o;
    return info.equals(that.info)
        && epoch.equals(that.epoch)
        && validators.equals(that.validators)
        && treasury_cap.equals(that.treasury_cap)
        && storage_fund.equals(that.storage_fund)
        && parameters.equals(that.parameters)
        && reference_gas_price.equals(that.reference_gas_price)
        && validator_report_records.equals(that.validator_report_records)
        && stake_subsidy.equals(that.stake_subsidy)
        && safe_mode.equals(that.safe_mode)
        && epoch_start_timestamp_ms.equals(that.epoch_start_timestamp_ms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        info,
        epoch,
        validators,
        treasury_cap,
        storage_fund,
        parameters,
        reference_gas_price,
        validator_report_records,
        stake_subsidy,
        safe_mode,
        epoch_start_timestamp_ms);
  }

  @Override
  public String toString() {
    return "SuiSystemState{"
        + "info="
        + info
        + ", epoch="
        + epoch
        + ", validators="
        + validators
        + ", treasury_cap="
        + treasury_cap
        + ", storage_fund="
        + storage_fund
        + ", parameters="
        + parameters
        + ", reference_gas_price="
        + reference_gas_price
        + ", validator_report_records="
        + validator_report_records
        + ", stake_subsidy="
        + stake_subsidy
        + ", safe_mode="
        + safe_mode
        + ", epoch_start_timestamp_ms="
        + epoch_start_timestamp_ms
        + '}';
  }
}
