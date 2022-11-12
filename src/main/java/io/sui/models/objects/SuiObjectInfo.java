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
 * The type Sui object info.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiObjectInfo extends SuiObjectRef {

  private String type;

  private SuiObjectOwner owner;

  private String previousTransaction;

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
   * Gets owner.
   *
   * @return the owner
   */
  public SuiObjectOwner getOwner() {
    return owner;
  }

  /**
   * Sets owner.
   *
   * @param owner the owner
   */
  public void setOwner(SuiObjectOwner owner) {
    this.owner = owner;
  }

  /**
   * Gets previous transaction.
   *
   * @return the previous transaction
   */
  public String getPreviousTransaction() {
    return previousTransaction;
  }

  /**
   * Sets previous transaction.
   *
   * @param previousTransaction the previous transaction
   */
  public void setPreviousTransaction(String previousTransaction) {
    this.previousTransaction = previousTransaction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuiObjectInfo)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SuiObjectInfo that = (SuiObjectInfo) o;
    return type.equals(that.type)
        && owner.equals(that.owner)
        && previousTransaction.equals(that.previousTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), type, owner, previousTransaction);
  }

  @Override
  public String toString() {
    return "SuiObjectInfo{"
        + "type='"
        + type
        + '\''
        + ", owner="
        + owner
        + ", previousTransaction='"
        + previousTransaction
        + '\''
        + "} "
        + super.toString();
  }
}
