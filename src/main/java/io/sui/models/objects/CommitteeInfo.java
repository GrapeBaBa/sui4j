/*
 * Copyright 2022 281165273grape@gmail.com
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
 * The type Committee info.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class CommitteeInfo {

  private String authorityName;

  private Long stakeUnit;

  /**
   * Gets authority name.
   *
   * @return the authority name
   */
  public String getAuthorityName() {
    return authorityName;
  }

  /**
   * Sets authority name.
   *
   * @param authorityName the authority name
   */
  public void setAuthorityName(String authorityName) {
    this.authorityName = authorityName;
  }

  /**
   * Gets stake unit.
   *
   * @return the stake unit
   */
  public Long getStakeUnit() {
    return stakeUnit;
  }

  /**
   * Sets stake unit.
   *
   * @param stakeUnit the stake unit
   */
  public void setStakeUnit(Long stakeUnit) {
    this.stakeUnit = stakeUnit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommitteeInfo)) {
      return false;
    }
    CommitteeInfo that = (CommitteeInfo) o;
    return authorityName.equals(that.authorityName) && stakeUnit.equals(that.stakeUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorityName, stakeUnit);
  }

  @Override
  public String toString() {
    return "CommitteeInfo{"
        + "authorityName='"
        + authorityName
        + '\''
        + ", stakeUnit="
        + stakeUnit
        + '}';
  }
}
