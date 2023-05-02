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

package io.sui.models.coin;


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type CoinSupply.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class CoinSupply {

  private BigInteger value;

  /**
   * Gets value.
   *
   * @return the value
   */
  public BigInteger getValue() {
    return value;
  }

  /**
   * Sets value.
   *
   * @param value the value
   */
  public void setValue(BigInteger value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CoinSupply)) {
      return false;
    }
    CoinSupply that = (CoinSupply) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "CoinSupply{" + "value=" + value + '}';
  }
}
