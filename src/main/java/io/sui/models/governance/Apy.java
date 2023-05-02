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


import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Apy.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class Apy {

  private BigDecimal apy;

  private String address;

  /**
   * Gets apy.
   *
   * @return the apy
   */
  public BigDecimal getApy() {
    return apy;
  }

  /**
   * Sets apy.
   *
   * @param apy the apy
   */
  public void setApy(BigDecimal apy) {
    this.apy = apy;
  }

  /**
   * Gets address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address the address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Apy)) {
      return false;
    }
    Apy apy1 = (Apy) o;
    return Objects.equals(apy, apy1.apy) && Objects.equals(address, apy1.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apy, address);
  }

  @Override
  public String toString() {
    return "Apy{" + "apy=" + apy + ", address='" + address + '\'' + '}';
  }
}
