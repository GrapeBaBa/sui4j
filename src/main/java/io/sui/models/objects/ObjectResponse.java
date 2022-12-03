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


import java.util.Objects;

/**
 * The type Get object response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ObjectResponse {

  /** The interface Get object response details. */
  public interface ObjectResponseDetails {}

  /** The type Object id response details. */
  public static class ObjectIdResponseDetails implements ObjectResponseDetails {

    private String objectId;

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

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      ObjectIdResponseDetails that = (ObjectIdResponseDetails) o;
      return objectId.equals(that.objectId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(objectId);
    }

    @Override
    public String toString() {
      return "ObjectIdResponseDetails{" + "objectId='" + objectId + '\'' + '}';
    }
  }

  /** The type Object id and version response details. */
  public static class ObjectIdAndVersionResponseDetails implements ObjectResponseDetails {

    private String objectId;

    private Long version;

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
      if (!(o instanceof ObjectIdAndVersionResponseDetails)) {
        return false;
      }
      ObjectIdAndVersionResponseDetails that = (ObjectIdAndVersionResponseDetails) o;
      return objectId.equals(that.objectId) && version.equals(that.version);
    }

    @Override
    public int hashCode() {
      return Objects.hash(objectId, version);
    }

    @Override
    public String toString() {
      return "ObjectIdAndVersionResponseDetails{"
          + "objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + '}';
    }
  }

  /** The type Object id higher version response details. */
  public static class ObjectIdHigherVersionResponseDetails implements ObjectResponseDetails {

    @SuppressWarnings("checkstyle:MemberName")
    private String object_id;

    @SuppressWarnings("checkstyle:MemberName")
    private Long asked_version;

    @SuppressWarnings("checkstyle:MemberName")
    private Long latest_version;

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
     * Gets asked version.
     *
     * @return the asked version
     */
    public Long getAsked_version() {
      return asked_version;
    }

    /**
     * Sets asked version.
     *
     * @param asked_version the asked version
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setAsked_version(Long asked_version) {
      this.asked_version = asked_version;
    }

    /**
     * Gets latest version.
     *
     * @return the latest version
     */
    public Long getLatest_version() {
      return latest_version;
    }

    /**
     * Sets latest version.
     *
     * @param latest_version the latest version
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setLatest_version(Long latest_version) {
      this.latest_version = latest_version;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectIdHigherVersionResponseDetails)) {
        return false;
      }
      ObjectIdHigherVersionResponseDetails that = (ObjectIdHigherVersionResponseDetails) o;
      return object_id.equals(that.object_id)
          && asked_version.equals(that.asked_version)
          && latest_version.equals(that.latest_version);
    }

    @Override
    public int hashCode() {
      return Objects.hash(object_id, asked_version, latest_version);
    }

    @Override
    public String toString() {
      return "ObjectIdHigherVersionResponseDetails{"
          + "object_id='"
          + object_id
          + '\''
          + ", asked_version="
          + asked_version
          + ", latest_version="
          + latest_version
          + '}';
    }
  }

  private ObjectStatus status;

  private ObjectResponseDetails details;

  /**
   * Gets status.
   *
   * @return the status
   */
  public ObjectStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(ObjectStatus status) {
    this.status = status;
  }

  /**
   * Gets details.
   *
   * @return the details
   */
  public ObjectResponseDetails getDetails() {
    return details;
  }

  /**
   * Sets details.
   *
   * @param details the details
   */
  public void setDetails(ObjectResponseDetails details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectResponse that = (ObjectResponse) o;
    return status == that.status && details.equals(that.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, details);
  }

  @Override
  public String toString() {
    return "GetObjectResponse{" + "status=" + status + ", details=" + details + '}';
  }
}
