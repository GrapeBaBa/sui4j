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

import static io.sui.models.objects.ObjectStatus.Deleted;
import static io.sui.models.objects.ObjectStatus.Exists;

import io.sui.clients.SuiObjectNotFoundException;
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

  /**
   * Gets object ref.
   *
   * @return the object ref
   */
  public SuiObjectRef getObjectRef() {
    if (Exists == this.getStatus()) {
      return ((SuiObjectData) this.getDetails()).getRef();
    } else if (Deleted == this.getStatus()) {
      return (SuiObjectRef) this.getDetails();
    }
    throw new SuiObjectNotFoundException();
  }
}
