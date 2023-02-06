package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

public class SuiSystemParameters {

  @SerializedName(value = "minValidatorStake", alternate = "min_validator_stake")
  private Long minValidatorStake;

  @SerializedName(value = "maxValidatorCandidateCount", alternate = "max_validator_candidate_count")
  private Long max_validator_candidate_count;

  public Long getMinValidatorStake() {
    return minValidatorStake;
  }

  public void setMinValidatorStake(Long minValidatorStake) {
    this.minValidatorStake = minValidatorStake;
  }

  public Long getMax_validator_candidate_count() {
    return max_validator_candidate_count;
  }

  public void setMax_validator_candidate_count(Long max_validator_candidate_count) {
    this.max_validator_candidate_count = max_validator_candidate_count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiSystemParameters that = (SuiSystemParameters) o;
    return Objects.equal(minValidatorStake, that.minValidatorStake) && Objects.equal(max_validator_candidate_count, that.max_validator_candidate_count);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(minValidatorStake, max_validator_candidate_count);
  }

  @Override
  public String toString() {
    return "SuiSystemParameters{" +
        "minValidatorStake=" + minValidatorStake +
        ", max_validator_candidate_count=" + max_validator_candidate_count +
        '}';
  }
}
