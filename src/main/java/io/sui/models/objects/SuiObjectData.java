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


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Sui object.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class SuiObjectData {

  private String objectId;

  private BigInteger version;

  private String digest;

  private String type;

  private SuiParsedData content;

  private SuiRawData bcs;

  private SuiObjectOwner owner;

  private String previousTransaction;

  private BigInteger storageRebate;

  private DisplayFieldsResponse display;

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
  public BigInteger getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(BigInteger version) {
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
   * Gets content.
   *
   * @return the content
   */
  public SuiParsedData getContent() {
    return content;
  }

  /**
   * Sets content.
   *
   * @param content the content
   */
  public void setContent(SuiParsedData content) {
    this.content = content;
  }

  /**
   * Gets bcs.
   *
   * @return the bcs
   */
  public SuiRawData getBcs() {
    return bcs;
  }

  /**
   * Sets bcs.
   *
   * @param bcs the bcs
   */
  public void setBcs(SuiRawData bcs) {
    this.bcs = bcs;
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

  /**
   * Gets storage rebate.
   *
   * @return the storage rebate
   */
  public BigInteger getStorageRebate() {
    return storageRebate;
  }

  /**
   * Sets storage rebate.
   *
   * @param storageRebate the storage rebate
   */
  public void setStorageRebate(BigInteger storageRebate) {
    this.storageRebate = storageRebate;
  }

  /**
   * Gets display.
   *
   * @return the display
   */
  public DisplayFieldsResponse getDisplay() {
    return display;
  }

  /**
   * Sets display.
   *
   * @param display the display
   */
  public void setDisplay(DisplayFieldsResponse display) {
    this.display = display;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SuiObjectData)) {
      return false;
    }
    SuiObjectData that = (SuiObjectData) o;
    return objectId.equals(that.objectId)
        && version.equals(that.version)
        && digest.equals(that.digest)
        && type.equals(that.type)
        && content.equals(that.content)
        && bcs.equals(that.bcs)
        && owner.equals(that.owner)
        && previousTransaction.equals(that.previousTransaction)
        && storageRebate.equals(that.storageRebate)
        && display.equals(that.display);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        objectId,
        version,
        digest,
        type,
        content,
        bcs,
        owner,
        previousTransaction,
        storageRebate,
        display);
  }

  @Override
  public String toString() {
    return "SuiObjectData{"
        + "objectId='"
        + objectId
        + '\''
        + ", version="
        + version
        + ", digest='"
        + digest
        + '\''
        + ", type='"
        + type
        + '\''
        + ", content="
        + content
        + ", bcs="
        + bcs
        + ", owner="
        + owner
        + ", previousTransaction='"
        + previousTransaction
        + '\''
        + ", storageRebate="
        + storageRebate
        + ", display="
        + display
        + '}';
  }

  /**
   * Gets ref.
   *
   * @return the ref
   */
  public SuiObjectRef getRef() {
    SuiObjectRef suiObjectRef = new SuiObjectRef();
    suiObjectRef.setObjectId(this.objectId);
    suiObjectRef.setDigest(this.digest);
    suiObjectRef.setVersion(this.version);
    return suiObjectRef;
  }
}
