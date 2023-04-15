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

/**
 * the coin info.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class Coin {

  private String coinType;

  private String coinObjectId;

  private BigInteger version;

  private String digest;

  private BigInteger balance;

  private BigInteger lockedUtilEpoch;

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

  public BigInteger getVersion() {
    return version;
  }

  public void setVersion(BigInteger version) {
    this.version = version;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public BigInteger getBalance() {
    return balance;
  }

  public void setBalance(BigInteger balance) {
    this.balance = balance;
  }

  public BigInteger getLockedUtilEpoch() {
    return lockedUtilEpoch;
  }

  public void setLockedUtilEpoch(BigInteger lockedUtilEpoch) {
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coin coin = (Coin) o;
    return Objects.equal(coinType, coin.coinType)
        && Objects.equal(coinObjectId, coin.coinObjectId)
        && Objects.equal(version, coin.version)
        && Objects.equal(digest, coin.digest)
        && Objects.equal(balance, coin.balance)
        && Objects.equal(lockedUtilEpoch, coin.lockedUtilEpoch)
        && Objects.equal(previousTransaction, coin.previousTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        coinType, coinObjectId, version, digest, balance, lockedUtilEpoch, previousTransaction);
  }

  @Override
  public String toString() {
    return "Coin{"
        + "coinType='"
        + coinType
        + '\''
        + ", coinObjectId='"
        + coinObjectId
        + '\''
        + ", version="
        + version
        + ", digest='"
        + digest
        + '\''
        + ", balance="
        + balance
        + ", lockedUtilEpoch='"
        + lockedUtilEpoch
        + '\''
        + ", previousTransaction='"
        + previousTransaction
        + '\''
        + '}';
  }
}
