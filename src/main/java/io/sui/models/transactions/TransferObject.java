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

package io.sui.models.transactions;


import io.sui.models.objects.SuiObjectRef;
import java.util.Objects;

/**
 * The type Transfer object.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransferObject {

  private String recipient;

  private SuiObjectRef objectRef;

  /**
   * Gets recipient.
   *
   * @return the recipient
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * Sets recipient.
   *
   * @param recipient the recipient
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  /**
   * Gets object ref.
   *
   * @return the object ref
   */
  public SuiObjectRef getObjectRef() {
    return objectRef;
  }

  /**
   * Sets object ref.
   *
   * @param objectRef the object ref
   */
  public void setObjectRef(SuiObjectRef objectRef) {
    this.objectRef = objectRef;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransferObject)) {
      return false;
    }
    TransferObject that = (TransferObject) o;
    return recipient.equals(that.recipient) && objectRef.equals(that.objectRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipient, objectRef);
  }

  @Override
  public String toString() {
    return "TransferObject{" + "recipient='" + recipient + '\'' + ", objectRef=" + objectRef + '}';
  }
}
