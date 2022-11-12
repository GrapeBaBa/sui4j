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

package io.sui.models.events;


import io.sui.models.objects.SuiObjectOwner;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Coin balance change event.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class CoinBalanceChangeEvent {

  /** The enum Balance change type. */
  public enum BalanceChangeType {
    /** Gas balance change type. */
    Gas,
    /** Pay balance change type. */
    Pay,
    /** Receive balance change type. */
    Receive
  }

  private String packageId;

  private String transactionModule;

  private String sender;

  private SuiObjectOwner owner;

  private BalanceChangeType changeType;

  private String coinType;

  private String coinObjectId;

  private Long version;

  private BigInteger amount;

  /**
   * Gets package id.
   *
   * @return the package id
   */
  public String getPackageId() {
    return packageId;
  }

  /**
   * Sets package id.
   *
   * @param packageId the package id
   */
  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  /**
   * Gets transaction module.
   *
   * @return the transaction module
   */
  public String getTransactionModule() {
    return transactionModule;
  }

  /**
   * Sets transaction module.
   *
   * @param transactionModule the transaction module
   */
  public void setTransactionModule(String transactionModule) {
    this.transactionModule = transactionModule;
  }

  /**
   * Gets sender.
   *
   * @return the sender
   */
  public String getSender() {
    return sender;
  }

  /**
   * Sets sender.
   *
   * @param sender the sender
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Gets owner.
   *
   * @return the owner
   */
  public SuiObjectOwner getOwner() {
    return owner;
  }

  /**
   * Sets owner.
   *
   * @param owner the owner
   */
  public void setOwner(SuiObjectOwner owner) {
    this.owner = owner;
  }

  /**
   * Gets change type.
   *
   * @return the change type
   */
  public BalanceChangeType getChangeType() {
    return changeType;
  }

  /**
   * Sets change type.
   *
   * @param changeType the change type
   */
  public void setChangeType(BalanceChangeType changeType) {
    this.changeType = changeType;
  }

  /**
   * Gets coin type.
   *
   * @return the coin type
   */
  public String getCoinType() {
    return coinType;
  }

  /**
   * Sets coin type.
   *
   * @param coinType the coin type
   */
  public void setCoinType(String coinType) {
    this.coinType = coinType;
  }

  /**
   * Gets coin object id.
   *
   * @return the coin object id
   */
  public String getCoinObjectId() {
    return coinObjectId;
  }

  /**
   * Sets coin object id.
   *
   * @param coinObjectId the coin object id
   */
  public void setCoinObjectId(String coinObjectId) {
    this.coinObjectId = coinObjectId;
  }

  /**
   * Gets version.
   *
   * @return the version
   */
  public Long getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Long version) {
    this.version = version;
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public BigInteger getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amount the amount
   */
  public void setAmount(BigInteger amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CoinBalanceChangeEvent)) {
      return false;
    }
    CoinBalanceChangeEvent that = (CoinBalanceChangeEvent) o;
    return packageId.equals(that.packageId)
        && transactionModule.equals(that.transactionModule)
        && sender.equals(that.sender)
        && owner.equals(that.owner)
        && changeType == that.changeType
        && coinType.equals(that.coinType)
        && coinObjectId.equals(that.coinObjectId)
        && version.equals(that.version)
        && amount.equals(that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        packageId,
        transactionModule,
        sender,
        owner,
        changeType,
        coinType,
        coinObjectId,
        version,
        amount);
  }

  @Override
  public String toString() {
    return "CoinBalanceChangeEvent{"
        + "packageId='"
        + packageId
        + '\''
        + ", transactionModule='"
        + transactionModule
        + '\''
        + ", sender='"
        + sender
        + '\''
        + ", owner="
        + owner
        + ", changeType="
        + changeType
        + ", coinType='"
        + coinType
        + '\''
        + ", coinObjectId='"
        + coinObjectId
        + '\''
        + ", version="
        + version
        + ", amount="
        + amount
        + '}';
  }
}
