package io.sui.models.objects;


import com.google.common.base.Objects;

import java.math.BigInteger;
import java.util.Map;

/**
 * the coin balance
 */
public class Balance {

  private String coinType;

  private Long coinObjectCount;

  private BigInteger totalBalance;

  private Map<Long, BigInteger> lockedBalance;

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

  public Map<Long, BigInteger> getLockedBalance() {
    return lockedBalance;
  }

  public void setLockedBalance(Map<Long, BigInteger> lockedBalance) {
    this.lockedBalance = lockedBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Balance balance = (Balance) o;
    return Objects.equal(coinType, balance.coinType) && Objects.equal(coinObjectCount, balance.coinObjectCount) && Objects.equal(totalBalance, balance.totalBalance) && Objects.equal(lockedBalance, balance.lockedBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(coinType, coinObjectCount, totalBalance, lockedBalance);
  }

  @Override
  public String toString() {
    return "Balance{" +
        "coinType='" + coinType + '\'' +
        ", coinObjectCount=" + coinObjectCount +
        ", totalBalance=" + totalBalance +
        ", lockedBalance=" + lockedBalance +
        '}';
  }
}
