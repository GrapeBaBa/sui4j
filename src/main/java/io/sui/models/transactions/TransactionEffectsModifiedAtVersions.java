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

package io.sui.models.transactions;


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Transaction effects modified at versions.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class TransactionEffectsModifiedAtVersions {

  private String objectId;

  private BigInteger sequenceNumber;

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
   * Gets sequence number.
   *
   * @return the sequence number
   */
  public BigInteger getSequenceNumber() {
    return sequenceNumber;
  }

  /**
   * Sets sequence number.
   *
   * @param sequenceNumber the sequence number
   */
  public void setSequenceNumber(BigInteger sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionEffectsModifiedAtVersions)) {
      return false;
    }
    TransactionEffectsModifiedAtVersions that = (TransactionEffectsModifiedAtVersions) o;
    return objectId.equals(that.objectId) && sequenceNumber.equals(that.sequenceNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectId, sequenceNumber);
  }

  @Override
  public String toString() {
    return "TransactionEffectsModifiedAtVersions{"
        + "objectId='"
        + objectId
        + '\''
        + ", sequenceNumber="
        + sequenceNumber
        + '}';
  }
}
