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
import java.util.Objects;

/**
 * The type Stake object.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class StakeObject {

  private String stakedSuiId;

  private BigInteger stakeRequestEpoch;

  private BigInteger stakeActiveEpoch;

  private String principal;

  private StakeObjectStatus status;

  private String estimatedReward;

  /**
   * Gets staked sui id.
   *
   * @return the staked sui id
   */
  public String getStakedSuiId() {
    return stakedSuiId;
  }

  /**
   * Sets staked sui id.
   *
   * @param stakedSuiId the staked sui id
   */
  public void setStakedSuiId(String stakedSuiId) {
    this.stakedSuiId = stakedSuiId;
  }

  /**
   * Gets stake request epoch.
   *
   * @return the stake request epoch
   */
  public BigInteger getStakeRequestEpoch() {
    return stakeRequestEpoch;
  }

  /**
   * Sets stake request epoch.
   *
   * @param stakeRequestEpoch the stake request epoch
   */
  public void setStakeRequestEpoch(BigInteger stakeRequestEpoch) {
    this.stakeRequestEpoch = stakeRequestEpoch;
  }

  /**
   * Gets stake active epoch.
   *
   * @return the stake active epoch
   */
  public BigInteger getStakeActiveEpoch() {
    return stakeActiveEpoch;
  }

  /**
   * Sets stake active epoch.
   *
   * @param stakeActiveEpoch the stake active epoch
   */
  public void setStakeActiveEpoch(BigInteger stakeActiveEpoch) {
    this.stakeActiveEpoch = stakeActiveEpoch;
  }

  /**
   * Gets principal.
   *
   * @return the principal
   */
  public String getPrincipal() {
    return principal;
  }

  /**
   * Sets principal.
   *
   * @param principal the principal
   */
  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public StakeObjectStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(StakeObjectStatus status) {
    this.status = status;
  }

  /**
   * Gets estimated reward.
   *
   * @return the estimated reward
   */
  public String getEstimatedReward() {
    return estimatedReward;
  }

  /**
   * Sets estimated reward.
   *
   * @param estimatedReward the estimated reward
   */
  public void setEstimatedReward(String estimatedReward) {
    this.estimatedReward = estimatedReward;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StakeObject)) {
      return false;
    }
    StakeObject that = (StakeObject) o;
    return Objects.equals(stakedSuiId, that.stakedSuiId)
        && Objects.equals(stakeRequestEpoch, that.stakeRequestEpoch)
        && Objects.equals(stakeActiveEpoch, that.stakeActiveEpoch)
        && Objects.equals(principal, that.principal)
        && status == that.status
        && Objects.equals(estimatedReward, that.estimatedReward);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        stakedSuiId, stakeRequestEpoch, stakeActiveEpoch, principal, status, estimatedReward);
  }

  @Override
  public String toString() {
    return "StakeObject{"
        + "stakedSuiId='"
        + stakedSuiId
        + '\''
        + ", stakeRequestEpoch="
        + stakeRequestEpoch
        + ", stakeActiveEpoch="
        + stakeActiveEpoch
        + ", principal='"
        + principal
        + '\''
        + ", status="
        + status
        + ", estimatedReward='"
        + estimatedReward
        + '\''
        + '}';
  }
}
