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
 * The type Checkpointed object id.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class CheckpointedObjectId {

  private String objectId;

  private Long atCheckpoint;

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
   * Gets at checkpoint.
   *
   * @return the at checkpoint
   */
  public Long getAtCheckpoint() {
    return atCheckpoint;
  }

  /**
   * Sets at checkpoint.
   *
   * @param atCheckpoint the at checkpoint
   */
  public void setAtCheckpoint(Long atCheckpoint) {
    this.atCheckpoint = atCheckpoint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CheckpointedObjectId)) {
      return false;
    }
    CheckpointedObjectId that = (CheckpointedObjectId) o;
    return objectId.equals(that.objectId) && atCheckpoint.equals(that.atCheckpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectId, atCheckpoint);
  }

  @Override
  public String toString() {
    return "CheckpointedObjectId{"
        + "objectId='"
        + objectId
        + '\''
        + ", atCheckpoint="
        + atCheckpoint
        + '}';
  }
}
