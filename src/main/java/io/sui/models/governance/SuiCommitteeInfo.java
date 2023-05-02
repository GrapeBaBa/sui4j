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

package io.sui.models.governance;


import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * The type Committee info response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiCommitteeInfo {

  @SuppressWarnings("checkstyle:MemberName")
  private List<Validator> validators;

  private BigInteger epoch;

  public List<Validator> getValidators() {
    return validators;
  }

  @SuppressWarnings("checkstyle:ParameterName")
  public void setValidators(List<Validator> validators) {
    this.validators = validators;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuiCommitteeInfo)) {
      return false;
    }
    SuiCommitteeInfo that = (SuiCommitteeInfo) o;
    return validators.equals(that.validators) && epoch.equals(that.epoch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validators, epoch);
  }

  @Override
  public String toString() {
    return "SuiCommitteeInfo{" + "committee_info=" + validators + ", epoch=" + epoch + '}';
  }
}
