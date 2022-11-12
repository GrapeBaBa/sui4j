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


import java.util.Map;
import java.util.Objects;

/**
 * The type Move event.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveEvent {

  private String bcs;

  private String packageId;

  private String sender;

  private String transactionModule;

  private String type;

  private Map<String, ?> fields;

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
   * Gets fields.
   *
   * @return the fields
   */
  public Map<String, ?> getFields() {
    return fields;
  }

  /**
   * Sets fields.
   *
   * @param fields the fields
   */
  public void setFields(Map<String, ?> fields) {
    this.fields = fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveEvent)) {
      return false;
    }
    MoveEvent moveEvent = (MoveEvent) o;
    return bcs.equals(moveEvent.bcs)
        && packageId.equals(moveEvent.packageId)
        && sender.equals(moveEvent.sender)
        && transactionModule.equals(moveEvent.transactionModule)
        && type.equals(moveEvent.type)
        && fields.equals(moveEvent.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bcs, packageId, sender, transactionModule, type, fields);
  }

  @Override
  public String toString() {
    return "MoveEvent{"
        + "bcs='"
        + bcs
        + '\''
        + ", packageId='"
        + packageId
        + '\''
        + ", sender='"
        + sender
        + '\''
        + ", transactionModule='"
        + transactionModule
        + '\''
        + ", type='"
        + type
        + '\''
        + ", fields="
        + fields
        + '}';
  }
}
