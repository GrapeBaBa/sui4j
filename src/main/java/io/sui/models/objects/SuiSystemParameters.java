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
 * the move system parameters type.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class SuiSystemParameters {

  private Long min_validator_stake;

  private Long max_validator_candidate_count;

  public Long getMin_validator_stake() {
    return min_validator_stake;
  }

  public void setMin_validator_stake(Long min_validator_stake) {
    this.min_validator_stake = min_validator_stake;
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
    return min_validator_stake.equals(that.min_validator_stake)
        && max_validator_candidate_count.equals(that.max_validator_candidate_count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(min_validator_stake, max_validator_candidate_count);
  }

  @Override
  public String toString() {
    return "SuiSystemParameters{"
        + "min_validator_stake="
        + min_validator_stake
        + ", max_validator_candidate_count="
        + max_validator_candidate_count
        + '}';
  }
}
