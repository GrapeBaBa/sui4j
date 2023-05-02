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


import java.util.List;
import java.util.Objects;

/**
 * The type Delegated stake.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class DelegatedStake {

  private String validatorAddress;

  private String stakingPool;

  private List<StakeObject> stakes;

  /**
   * Gets validator address.
   *
   * @return the validator address
   */
  public String getValidatorAddress() {
    return validatorAddress;
  }

  /**
   * Sets validator address.
   *
   * @param validatorAddress the validator address
   */
  public void setValidatorAddress(String validatorAddress) {
    this.validatorAddress = validatorAddress;
  }

  /**
   * Gets staking pool.
   *
   * @return the staking pool
   */
  public String getStakingPool() {
    return stakingPool;
  }

  /**
   * Sets staking pool.
   *
   * @param stakingPool the staking pool
   */
  public void setStakingPool(String stakingPool) {
    this.stakingPool = stakingPool;
  }

  /**
   * Gets stakes.
   *
   * @return the stakes
   */
  public List<StakeObject> getStakes() {
    return stakes;
  }

  /**
   * Sets stakes.
   *
   * @param stakes the stakes
   */
  public void setStakes(List<StakeObject> stakes) {
    this.stakes = stakes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DelegatedStake)) {
      return false;
    }
    DelegatedStake that = (DelegatedStake) o;
    return Objects.equals(validatorAddress, that.validatorAddress)
        && Objects.equals(stakingPool, that.stakingPool)
        && Objects.equals(stakes, that.stakes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validatorAddress, stakingPool, stakes);
  }

  @Override
  public String toString() {
    return "DelegatedStake{"
        + "validatorAddress='"
        + validatorAddress
        + '\''
        + ", stakingPool='"
        + stakingPool
        + '\''
        + ", stakes="
        + stakes
        + '}';
  }
}
