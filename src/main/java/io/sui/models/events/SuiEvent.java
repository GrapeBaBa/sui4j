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

package io.sui.models.events;


import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

/**
 * The type Sui event.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class SuiEvent {

  private EventId id;

  private String packageId;

  private String transactionModule;

  private String sender;

  private String type;

  private Map<String, ?> parsedJson;

  private String bcs;

  private BigInteger timestampMs;

  /**
   * Gets id.
   *
   * @return the id
   */
  public EventId getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(EventId id) {
    this.id = id;
  }

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
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets parsed json.
   *
   * @return the parsed json
   */
  public Map<String, ?> getParsedJson() {
    return parsedJson;
  }

  /**
   * Sets parsed json.
   *
   * @param parsedJson the parsed json
   */
  public void setParsedJson(Map<String, ?> parsedJson) {
    this.parsedJson = parsedJson;
  }

  /**
   * Gets bcs.
   *
   * @return the bcs
   */
  public String getBcs() {
    return bcs;
  }

  /**
   * Sets bcs.
   *
   * @param bcs the bcs
   */
  public void setBcs(String bcs) {
    this.bcs = bcs;
  }

  /**
   * Gets timestamp ms.
   *
   * @return the timestamp ms
   */
  public BigInteger getTimestampMs() {
    return timestampMs;
  }

  /**
   * Sets timestamp ms.
   *
   * @param timestampMs the timestamp ms
   */
  public void setTimestampMs(BigInteger timestampMs) {
    this.timestampMs = timestampMs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuiEvent)) {
      return false;
    }
    SuiEvent suiEvent = (SuiEvent) o;
    return id.equals(suiEvent.id)
        && packageId.equals(suiEvent.packageId)
        && transactionModule.equals(suiEvent.transactionModule)
        && sender.equals(suiEvent.sender)
        && type.equals(suiEvent.type)
        && parsedJson.equals(suiEvent.parsedJson)
        && bcs.equals(suiEvent.bcs)
        && timestampMs.equals(suiEvent.timestampMs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, packageId, transactionModule, sender, type, parsedJson, bcs, timestampMs);
  }

  @Override
  public String toString() {
    return "SuiEvent{"
        + "id="
        + id
        + ", packageId='"
        + packageId
        + '\''
        + ", transactionModule='"
        + transactionModule
        + '\''
        + ", sender='"
        + sender
        + '\''
        + ", type='"
        + type
        + '\''
        + ", parsedJson="
        + parsedJson
        + ", bcs='"
        + bcs
        + '\''
        + ", timestampMs="
        + timestampMs
        + '}';
  }
}
