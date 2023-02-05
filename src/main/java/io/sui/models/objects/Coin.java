package io.sui.models.objects;

import com.google.common.base.Objects;

/**
 * the coin info
 */
public class Coin {

  private String coinType;

  private String coinObjectId;

  private Long version;

  private String digest;

  private Long balance;

  private String lockedUtilEpoch;

  private String previousTransaction;

  public String getCoinType() {
    return coinType;
  }

  public void setCoinType(String coinType) {
    this.coinType = coinType;
  }

  public String getCoinObjectId() {
    return coinObjectId;
  }

  public void setCoinObjectId(String coinObjectId) {
    this.coinObjectId = coinObjectId;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public String getLockedUtilEpoch() {
    return lockedUtilEpoch;
  }

  public void setLockedUtilEpoch(String lockedUtilEpoch) {
    this.lockedUtilEpoch = lockedUtilEpoch;
  }

  public String getPreviousTransaction() {
    return previousTransaction;
  }

  public void setPreviousTransaction(String previousTransaction) {
    this.previousTransaction = previousTransaction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coin coin = (Coin) o;
    return Objects.equal(coinType, coin.coinType) && Objects.equal(coinObjectId, coin.coinObjectId) && Objects.equal(version, coin.version) && Objects.equal(digest, coin.digest) && Objects.equal(balance, coin.balance) && Objects.equal(lockedUtilEpoch, coin.lockedUtilEpoch) && Objects.equal(previousTransaction, coin.previousTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(coinType, coinObjectId, version, digest, balance, lockedUtilEpoch, previousTransaction);
  }

  @Override
  public String toString() {
    return "Coin{" +
        "coinType='" + coinType + '\'' +
        ", coinObjectId='" + coinObjectId + '\'' +
        ", version=" + version +
        ", digest='" + digest + '\'' +
        ", balance=" + balance +
        ", lockedUtilEpoch='" + lockedUtilEpoch + '\'' +
        ", previousTransaction='" + previousTransaction + '\'' +
        '}';
  }
}
