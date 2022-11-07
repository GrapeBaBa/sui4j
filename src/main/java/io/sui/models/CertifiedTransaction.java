/*
 * Copyright 281165273grape@gmail.com
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

package io.sui.models;

import java.util.Objects;


/**
 * The type Certified transaction.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class CertifiedTransaction {

  private AuthorityQuorumSignInfo authSignInfo;

  private String transactionDigest;

  private String txSignature;

  private TransactionData data;

  /**
   * Gets auth sign info.
   *
   * @return the auth sign info
   */
  AuthorityQuorumSignInfo getAuthSignInfo() {
    return authSignInfo;
  }

  /**
   * Sets auth sign info.
   *
   * @param authSignInfo the auth sign info
   */
  void setAuthSignInfo(AuthorityQuorumSignInfo authSignInfo) {
    this.authSignInfo = authSignInfo;
  }

  /**
   * Gets transaction digest.
   *
   * @return the transaction digest
   */
  String getTransactionDigest() {
    return transactionDigest;
  }

  /**
   * Sets transaction digest.
   *
   * @param transactionDigest the transaction digest
   */
  void setTransactionDigest(String transactionDigest) {
    this.transactionDigest = transactionDigest;
  }

  /**
   * Gets tx signature.
   *
   * @return the tx signature
   */
  String getTxSignature() {
    return txSignature;
  }

  /**
   * Sets tx signature.
   *
   * @param txSignature the tx signature
   */
  void setTxSignature(String txSignature) {
    this.txSignature = txSignature;
  }

  /**
   * Gets data.
   *
   * @return the data
   */
  TransactionData getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  void setData(TransactionData data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertifiedTransaction that = (CertifiedTransaction) o;
    return authSignInfo.equals(that.authSignInfo) && transactionDigest.equals(
        that.transactionDigest) && txSignature.equals(that.txSignature) && data.equals(that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authSignInfo, transactionDigest, txSignature, data);
  }

  @Override
  public String toString() {
    return "CertifiedTransaction{"
        + "authSignInfo=" + authSignInfo
        + ", transactionDigest='" + transactionDigest + '\''
        + ", txSignature='" + txSignature + '\''
        + ", data=" + data
        + '}';
  }

}
