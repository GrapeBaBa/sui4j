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


import java.util.Objects;

/**
 * The type Mutate object event.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MutateObjectEvent {

  private String packageId;

  private String transactionModule;

  private String sender;

  private String objectType;

  private String objectId;

  private Long version;

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
   * Gets object type.
   *
   * @return the object type
   */
  public String getObjectType() {
    return objectType;
  }

  /**
   * Sets object type.
   *
   * @param objectType the object type
   */
  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  /**
   * Gets object id.
   *
   * @return the object id
   */
  public String getObjectId() {
    return objectId;
  }

  /**
   * Sets object id.
   *
   * @param objectId the object id
   */
  public void setObjectId(String objectId) {
    this.objectId = objectId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MutateObjectEvent)) {
      return false;
    }
    MutateObjectEvent that = (MutateObjectEvent) o;
    return packageId.equals(that.packageId)
        && transactionModule.equals(that.transactionModule)
        && sender.equals(that.sender)
        && objectType.equals(that.objectType)
        && objectId.equals(that.objectId)
        && version.equals(that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(packageId, transactionModule, sender, objectType, objectId, version);
  }

  @Override
  public String toString() {
    return "MutateObjectEvent{"
        + "packageId='"
        + packageId
        + '\''
        + ", transactionModule='"
        + transactionModule
        + '\''
        + ", sender='"
        + sender
        + '\''
        + ", objectType='"
        + objectType
        + '\''
        + ", objectId='"
        + objectId
        + '\''
        + ", version="
        + version
        + '}';
  }
}
