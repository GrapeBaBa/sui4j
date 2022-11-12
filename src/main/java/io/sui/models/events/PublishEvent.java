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
 * The type Publish event.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class PublishEvent {

  private String packageId;

  private String sender;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PublishEvent)) {
      return false;
    }
    PublishEvent that = (PublishEvent) o;
    return packageId.equals(that.packageId) && sender.equals(that.sender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(packageId, sender);
  }

  @Override
  public String toString() {
    return "PublishEvent{" + "packageId='" + packageId + '\'' + ", sender='" + sender + '\'' + '}';
  }
}
