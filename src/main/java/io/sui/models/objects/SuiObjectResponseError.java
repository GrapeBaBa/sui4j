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

package io.sui.models.objects;


import java.util.Objects;

/**
 * The type Sui object response error.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class SuiObjectResponseError {

  private String tag;

  @SuppressWarnings("checkstyle:MemberName")
  private String object_id;

  private Long version;

  private String digest;

  /**
   * Gets tag.
   *
   * @return the tag
   */
  public String getTag() {
    return tag;
  }

  /**
   * Sets tag.
   *
   * @param tag the tag
   */
  public void setTag(String tag) {
    this.tag = tag;
  }

  /**
   * Gets object id.
   *
   * @return the object id
   */
  public String getObject_id() {
    return object_id;
  }

  /**
   * Sets object id.
   *
   * @param object_id the object id
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setObject_id(String object_id) {
    this.object_id = object_id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuiObjectResponseError)) {
      return false;
    }
    SuiObjectResponseError that = (SuiObjectResponseError) o;
    return tag.equals(that.tag)
        && object_id.equals(that.object_id)
        && version.equals(that.version)
        && digest.equals(that.digest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tag, object_id, version, digest);
  }

  @Override
  public String toString() {
    return "SuiObjectResponseError{"
        + "tag='"
        + tag
        + '\''
        + ", object_id='"
        + object_id
        + '\''
        + ", version="
        + version
        + ", digest='"
        + digest
        + '\''
        + '}';
  }
}
