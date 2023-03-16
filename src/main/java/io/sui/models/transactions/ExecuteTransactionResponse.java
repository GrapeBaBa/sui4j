/*
 * Copyright 2022-2023 281165273grape@gmail.com
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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The interface Execute transaction response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface ExecuteTransactionResponse {

  /** The type Immediate return response. */
  class ImmediateReturnResponse implements ExecuteTransactionResponse {

    @SuppressWarnings("checkstyle:MemberName")
    private ImmediateReturn ImmediateReturn;

    /**
     * Gets immediate return.
     *
     * @return the immediate return
     */
    public ExecuteTransactionResponse.ImmediateReturn getImmediateReturn() {
      return ImmediateReturn;
    }

    /**
     * Sets immediate return.
     *
     * @param immediateReturn the immediate return
     */
    public void setImmediateReturn(ExecuteTransactionResponse.ImmediateReturn immediateReturn) {
      ImmediateReturn = immediateReturn;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ImmediateReturnResponse)) {
        return false;
      }
      ImmediateReturnResponse that = (ImmediateReturnResponse) o;
      return ImmediateReturn.equals(that.ImmediateReturn);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ImmediateReturn);
    }

    @Override
    public String toString() {
      return "ImmediateReturnResponse{" + "ImmediateReturn=" + ImmediateReturn + '}';
    }
  }

  /** The type Immediate return. */
  class ImmediateReturn {

    @SuppressWarnings("checkstyle:MemberName")
    private String tx_digest;

    /**
     * Gets tx digest.
     *
     * @return the tx digest
     */
    public String getTx_digest() {
      return tx_digest;
    }

    /**
     * Sets tx digest.
     *
     * @param tx_digest the tx digest
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setTx_digest(String tx_digest) {
      this.tx_digest = tx_digest;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ImmediateReturn)) {
        return false;
      }
      ImmediateReturn that = (ImmediateReturn) o;
      return tx_digest.equals(that.tx_digest);
    }

    @Override
    public int hashCode() {
      return Objects.hash(tx_digest);
    }

    @Override
    public String toString() {
      return "ImmediateReturn{" + "tx_digest='" + tx_digest + '\'' + '}';
    }
  }

  /** The type Tx cert response. */
  class TxCertResponse implements ExecuteTransactionResponse {

    @SuppressWarnings("checkstyle:MemberName")
    private TxCert TxCert;

    /**
     * Gets tx cert.
     *
     * @return the tx cert
     */
    public ExecuteTransactionResponse.TxCert getTxCert() {
      return TxCert;
    }

    /**
     * Sets tx cert.
     *
     * @param txCert the tx cert
     */
    public void setTxCert(ExecuteTransactionResponse.TxCert txCert) {
      TxCert = txCert;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TxCertResponse)) {
        return false;
      }
      TxCertResponse that = (TxCertResponse) o;
      return TxCert.equals(that.TxCert);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TxCert);
    }

    @Override
    public String toString() {
      return "TxCertResponse{" + "TxCert=" + TxCert + '}';
    }
  }

  /** The type Tx cert. */
  class TxCert {

    private Transaction certificate;

    /**
     * Gets certificate.
     *
     * @return the certificate
     */
    public Transaction getCertificate() {
      return certificate;
    }

    /**
     * Sets certificate.
     *
     * @param certificate the certificate
     */
    public void setCertificate(Transaction certificate) {
      this.certificate = certificate;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TxCert)) {
        return false;
      }
      TxCert txCert = (TxCert) o;
      return certificate.equals(txCert.certificate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(certificate);
    }

    @Override
    public String toString() {
      return "TxCert{" + "certificate=" + certificate + '}';
    }
  }

  /** The type Effects cert response. */
  class EffectsCertResponse implements ExecuteTransactionResponse {

    @SuppressWarnings("checkstyle:MemberName")
    private EffectsCert EffectsCert;

    /**
     * Gets effects cert.
     *
     * @return the effects cert
     */
    public ExecuteTransactionResponse.EffectsCert getEffectsCert() {
      return EffectsCert;
    }

    /**
     * Sets effects cert.
     *
     * @param effectsCert the effects cert
     */
    public void setEffectsCert(ExecuteTransactionResponse.EffectsCert effectsCert) {
      EffectsCert = effectsCert;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EffectsCertResponse)) {
        return false;
      }
      EffectsCertResponse that = (EffectsCertResponse) o;
      return EffectsCert.equals(that.EffectsCert);
    }

    @Override
    public int hashCode() {
      return Objects.hash(EffectsCert);
    }

    @Override
    public String toString() {
      return "EffectsCertResponse{" + "EffectsCert=" + EffectsCert + '}';
    }
  }

  /** The type Effects cert. */
  class EffectsCert {

    private Transaction certificate;

    private CertifiedTransactionEffects effects;

    @SuppressWarnings("checkstyle:MemberName")
    private boolean confirmed_local_execution;

    /**
     * Is confirmed local execution boolean.
     *
     * @return the boolean
     */
    public boolean isConfirmed_local_execution() {
      return confirmed_local_execution;
    }

    /**
     * Sets confirmed local execution.
     *
     * @param confirmed_local_execution the confirmed local execution
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setConfirmed_local_execution(boolean confirmed_local_execution) {
      this.confirmed_local_execution = confirmed_local_execution;
    }

    /**
     * Gets certificate.
     *
     * @return the certificate
     */
    public Transaction getCertificate() {
      return certificate;
    }

    /**
     * Sets certificate.
     *
     * @param certificate the certificate
     */
    public void setCertificate(Transaction certificate) {
      this.certificate = certificate;
    }

    /**
     * Gets effects.
     *
     * @return the effects
     */
    public CertifiedTransactionEffects getEffects() {
      return effects;
    }

    /**
     * Sets effects.
     *
     * @param effects the effects
     */
    public void setEffects(CertifiedTransactionEffects effects) {
      this.effects = effects;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EffectsCert)) {
        return false;
      }
      EffectsCert that = (EffectsCert) o;
      return confirmed_local_execution == that.confirmed_local_execution
          && certificate.equals(that.certificate)
          && effects.equals(that.effects);
    }

    @Override
    public int hashCode() {
      return Objects.hash(certificate, effects, confirmed_local_execution);
    }

    @Override
    public String toString() {
      return "EffectsCert{"
          + "certificate="
          + certificate
          + ", effects="
          + effects
          + ", confirmed_local_execution="
          + confirmed_local_execution
          + '}';
    }
  }

  /** The type Certified transaction effects. */
  class CertifiedTransactionEffects {

    private String transactionEffectsDigest;

    private TransactionEffects effects;

    private AuthorityQuorumSignInfo authSignInfo;

    /**
     * Gets transaction effects digest.
     *
     * @return the transaction effects digest
     */
    public String getTransactionEffectsDigest() {
      return transactionEffectsDigest;
    }

    /**
     * Sets transaction effects digest.
     *
     * @param transactionEffectsDigest the transaction effects digest
     */
    public void setTransactionEffectsDigest(String transactionEffectsDigest) {
      this.transactionEffectsDigest = transactionEffectsDigest;
    }

    /**
     * Gets auth sign info.
     *
     * @return the auth sign info
     */
    public AuthorityQuorumSignInfo getAuthSignInfo() {
      return authSignInfo;
    }

    /**
     * Sets auth sign info.
     *
     * @param authSignInfo the auth sign info
     */
    public void setAuthSignInfo(AuthorityQuorumSignInfo authSignInfo) {
      this.authSignInfo = authSignInfo;
    }

    /**
     * Gets effects.
     *
     * @return the effects
     */
    public TransactionEffects getEffects() {
      return effects;
    }

    /**
     * Sets effects.
     *
     * @param effects the effects
     */
    public void setEffects(TransactionEffects effects) {
      this.effects = effects;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CertifiedTransactionEffects)) {
        return false;
      }
      CertifiedTransactionEffects that = (CertifiedTransactionEffects) o;
      return transactionEffectsDigest.equals(that.transactionEffectsDigest)
          && effects.equals(that.effects)
          && authSignInfo.equals(that.authSignInfo);
    }

    @Override
    public int hashCode() {
      return Objects.hash(transactionEffectsDigest, effects, authSignInfo);
    }

    @Override
    public String toString() {
      return "CertifiedTransactionEffects{"
          + "transactionEffectsDigest='"
          + transactionEffectsDigest
          + '\''
          + ", effects="
          + effects
          + ", authSignInfo="
          + authSignInfo
          + '}';
    }
  }
}
