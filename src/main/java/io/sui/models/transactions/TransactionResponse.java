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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The type Transaction response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionResponse {

  private CertifiedTransaction certificate;

  private TransactionEffects effects;

  @SuppressWarnings("checkstyle:MemberName")
  private Long timestamp_ms;

  @SuppressWarnings("checkstyle:MemberName")
  private ParsedTransactionResponseKind parsed_data;

  /**
   * Gets certificate.
   *
   * @return the certificate
   */
  public CertifiedTransaction getCertificate() {
    return certificate;
  }

  /**
   * Sets certificate.
   *
   * @param certificate the certificate
   */
  public void setCertificate(CertifiedTransaction certificate) {
    this.certificate = certificate;
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

  /**
   * Gets timestamp ms.
   *
   * @return the timestamp ms
   */
  public long getTimestamp_ms() {
    return timestamp_ms;
  }

  /**
   * Sets timestamp ms.
   *
   * @param timestamp_ms the timestamp ms
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setTimestamp_ms(long timestamp_ms) {
    this.timestamp_ms = timestamp_ms;
  }

  /**
   * Gets parsed data.
   *
   * @return the parsed data
   */
  public ParsedTransactionResponseKind getParsed_data() {
    return parsed_data;
  }

  /**
   * Sets parsed data.
   *
   * @param parsed_data the parsed data
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setParsed_data(ParsedTransactionResponseKind parsed_data) {
    this.parsed_data = parsed_data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionResponse)) {
      return false;
    }
    TransactionResponse that = (TransactionResponse) o;
    return timestamp_ms == that.timestamp_ms
        && certificate.equals(that.certificate)
        && effects.equals(that.effects)
        && parsed_data.equals(that.parsed_data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(certificate, effects, timestamp_ms, parsed_data);
  }

  @Override
  public String toString() {
    return "TransactionResponse{"
        + "certificate="
        + certificate
        + ", effects="
        + effects
        + ", timestamp_ms="
        + timestamp_ms
        + ", parsed_data="
        + parsed_data
        + '}';
  }
}
