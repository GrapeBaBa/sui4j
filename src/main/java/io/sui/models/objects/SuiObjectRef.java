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

package io.sui.models.objects;


import io.sui.models.objects.ObjectResponse.ObjectResponseDetails;
import java.util.Objects;

/**
 * The type Sui object ref.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiObjectRef implements ObjectResponseDetails {

  private String digest;

  private String objectId;

  private Long version;

  /**
   * Gets digest.
   *
   * @return the digest
   */
  public String getDigest() {
    return digest;
  }

  /**
   * Sets digest.
   *
   * @param digest the digest
   */
  public void setDigest(String digest) {
    this.digest = digest;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuiObjectRef that = (SuiObjectRef) o;
    return digest.equals(that.digest)
        && objectId.equals(that.objectId)
        && version.equals(that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(digest, objectId, version);
  }

  @Override
  public String toString() {
    return "SuiObjectRef{"
        + "digest='"
        + digest
        + '\''
        + ", objectId='"
        + objectId
        + '\''
        + ", version="
        + version
        + '}';
  }
}
