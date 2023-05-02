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
 * The type ValidatorsApy.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class ValidatorsApy {

  private BigInteger epoch;

  private List<Apy> apys;

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
   * Gets apys.
   *
   * @return the apys
   */
  public List<Apy> getApys() {
    return apys;
  }

  /**
   * Sets apys.
   *
   * @param apys the apys
   */
  public void setApys(List<Apy> apys) {
    this.apys = apys;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ValidatorsApy)) {
      return false;
    }
    ValidatorsApy that = (ValidatorsApy) o;
    return Objects.equals(epoch, that.epoch) && Objects.equals(apys, that.apys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch, apys);
  }

  @Override
  public String toString() {
    return "ValidatorsApy{" + "epoch=" + epoch + ", apys=" + apys + '}';
  }
}
