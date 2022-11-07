/*
 * Copyright 281165273grape@gmail.com
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

package io.sui.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

/**
 * The type Sui event.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("ALL")
public abstract class SuiEvent {

  /**
   * The enum Balance change type.
   */
  public enum BalanceChangeType {
    /**
     * Gas balance change type.
     */
    Gas,
    /**
     * Pay balance change type.
     */
    Pay,
    /**
     * Receive balance change type.
     */
    Receive
  }

  /**
   * The type Transfer object event.
   */
  public static class TransferObjectEvent extends SuiEvent {

    private String packageId;

    private String transactionModule;

    private String sender;

    private SuiObjectOwner recipient;

    private String objectType;

    private String objectId;

    private BigInteger version;

    public String getPackageId() {
      return packageId;
    }

    public void setPackageId(String packageId) {
      this.packageId = packageId;
    }

    public String getTransactionModule() {
      return transactionModule;
    }

    public void setTransactionModule(String transactionModule) {
      this.transactionModule = transactionModule;
    }

    public String getSender() {
      return sender;
    }

    public void setSender(String sender) {
      this.sender = sender;
    }

    public SuiObjectOwner getRecipient() {
      return recipient;
    }

    public void setRecipient(SuiObjectOwner recipient) {
      this.recipient = recipient;
    }

    public String getObjectType() {
      return objectType;
    }

    public void setObjectType(String objectType) {
      this.objectType = objectType;
    }

    public String getObjectId() {
      return objectId;
    }

    public void setObjectId(String objectId) {
      this.objectId = objectId;
    }

    public BigInteger getVersion() {
      return version;
    }

    public void setVersion(BigInteger version) {
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
      TransferObjectEvent that = (TransferObjectEvent) o;
      return packageId.equals(that.packageId) && transactionModule.equals(that.transactionModule)
          && sender.equals(that.sender) && recipient.equals(that.recipient) && objectType.equals(
          that.objectType) && objectId.equals(that.objectId) && version.equals(that.version);
    }

    @Override
    public int hashCode() {
      return Objects.hash(packageId, transactionModule, sender, recipient, objectType, objectId,
          version);
    }
  }

  /**
   * The type Move event.
   */
  public static class MoveEvent extends SuiEvent {

    private String bcs;

    private String packageId;

    private String sender;

    private String transactionModule;

    private String type;

    private Map<String, Object> fields;
  }

  /**
   * The type Publish event.
   */
  public static class PublishEvent extends SuiEvent {

    private String packageId;

    private String sender;
  }

  /**
   * The type Coin balance change event.
   */
  public static class CoinBalanceChangeEvent extends SuiEvent {

    private String packageId;

    private String transactionModule;

    private String sender;

    private SuiObjectOwner owner;

    private BalanceChangeType changeType;

    private String coinType;

    private String coinObjectId;

    private BigInteger version;

    private BigDecimal amount;
  }

  /**
   * The type Mutate object event.
   */
  public static class MutateObjectEvent extends SuiEvent {

    private String packageId;

    private String transactionModule;

    private String sender;

    private String objectType;

    private String objectId;

    private BigInteger version;
  }

  /**
   * The type Delete object event.
   */
  public static class DeleteObjectEvent extends SuiEvent {

    private String packageId;

    private String transactionModule;

    private String sender;

    private String objectId;

    private BigInteger version;
  }

  /**
   * The type New object event.
   */
  public static class NewObjectEvent extends SuiEvent {

    private String packageId;

    private String transactionModule;

    private SuiObjectOwner recipient;

    private String sender;

    private String objectId;

    private String objectType;

    private BigInteger version;
  }

  /**
   * The type Epoch change event.
   */
  public static class EpochChangeEvent extends SuiEvent {

    private BigInteger epochChange;
  }

  /**
   * The type Checkpoint event.
   */
  public static class CheckpointEvent extends SuiEvent {

    private BigInteger checkpoint;
  }

  private TransferObjectEvent transferObject;

  private MoveEvent moveEvent;

  private PublishEvent publish;

  private CoinBalanceChangeEvent coinBalanceChange;

  private MutateObjectEvent mutateObject;

  private DeleteObjectEvent deleteObject;

  private NewObjectEvent newObject;

  private EpochChangeEvent epochChange;

  private CheckpointEvent checkpoint;

}
