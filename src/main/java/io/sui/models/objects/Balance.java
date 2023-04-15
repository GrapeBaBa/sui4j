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


import com.google.common.base.Objects;
import java.math.BigInteger;
import java.util.Map;

/**
 * the coin balance.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class Balance {

  private String coinType;

  private Long coinObjectCount;

  private BigInteger totalBalance;

  private Map<BigInteger, BigInteger> lockedBalance;

  public String getCoinType() {
    return coinType;
  }

  public void setCoinType(String coinType) {
    this.coinType = coinType;
  }

  public Long getCoinObjectCount() {
    return coinObjectCount;
  }

  public void setCoinObjectCount(Long coinObjectCount) {
    this.coinObjectCount = coinObjectCount;
  }

  public BigInteger getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(BigInteger totalBalance) {
    this.totalBalance = totalBalance;
  }

  public Map<BigInteger, BigInteger> getLockedBalance() {
    return lockedBalance;
  }

  public void setLockedBalance(Map<BigInteger, BigInteger> lockedBalance) {
    this.lockedBalance = lockedBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Balance balance = (Balance) o;
    return Objects.equal(coinType, balance.coinType)
        && Objects.equal(coinObjectCount, balance.coinObjectCount)
        && Objects.equal(totalBalance, balance.totalBalance)
        && Objects.equal(lockedBalance, balance.lockedBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(coinType, coinObjectCount, totalBalance, lockedBalance);
  }

  @Override
  public String toString() {
    return "Balance{"
        + "coinType='"
        + coinType
        + '\''
        + ", coinObjectCount="
        + coinObjectCount
        + ", totalBalance="
        + totalBalance
        + ", lockedBalance="
        + lockedBalance
        + '}';
  }
}
