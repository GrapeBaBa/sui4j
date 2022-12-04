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

package io.sui.models.events;


import java.util.Objects;

/**
 * The type Event kind.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class EventKind {

  /** The type Move event kind. */
  public static class MoveEventKind extends EventKind {

    private MoveEvent moveEvent;

    /**
     * Gets move event.
     *
     * @return the move event
     */
    public MoveEvent getMoveEvent() {
      return moveEvent;
    }

    /**
     * Sets move event.
     *
     * @param moveEvent the move event
     */
    public void setMoveEvent(MoveEvent moveEvent) {
      this.moveEvent = moveEvent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveEventKind)) {
        return false;
      }
      MoveEventKind that = (MoveEventKind) o;
      return moveEvent.equals(that.moveEvent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(moveEvent);
    }

    @Override
    public String toString() {
      return "MoveEventKind{" + "moveEvent=" + moveEvent + '}';
    }
  }

  /** The type Publish event kind. */
  public static class PublishEventKind extends EventKind {

    private PublishEvent publish;

    /**
     * Gets publish.
     *
     * @return the publish
     */
    public PublishEvent getPublish() {
      return publish;
    }

    /**
     * Sets publish.
     *
     * @param publish the publish
     */
    public void setPublish(PublishEvent publish) {
      this.publish = publish;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PublishEventKind)) {
        return false;
      }
      PublishEventKind that = (PublishEventKind) o;
      return publish.equals(that.publish);
    }

    @Override
    public int hashCode() {
      return Objects.hash(publish);
    }

    @Override
    public String toString() {
      return "PublishEventKind{" + "publish=" + publish + '}';
    }
  }

  /** The type Coin balance change event kind. */
  public static class CoinBalanceChangeEventKind extends EventKind {

    private CoinBalanceChangeEvent coinBalanceChange;

    /**
     * Gets coin balance change.
     *
     * @return the coin balance change
     */
    public CoinBalanceChangeEvent getCoinBalanceChange() {
      return coinBalanceChange;
    }

    /**
     * Sets coin balance change.
     *
     * @param coinBalanceChange the coin balance change
     */
    public void setCoinBalanceChange(CoinBalanceChangeEvent coinBalanceChange) {
      this.coinBalanceChange = coinBalanceChange;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CoinBalanceChangeEventKind)) {
        return false;
      }
      CoinBalanceChangeEventKind that = (CoinBalanceChangeEventKind) o;
      return coinBalanceChange.equals(that.coinBalanceChange);
    }

    @Override
    public int hashCode() {
      return Objects.hash(coinBalanceChange);
    }

    @Override
    public String toString() {
      return "CoinBalanceChangeEventKind{" + "coinBalanceChange=" + coinBalanceChange + '}';
    }
  }

  /** The type Transfer object event kind. */
  public static class TransferObjectEventKind extends EventKind {

    private TransferObjectEvent transferObject;

    /**
     * Gets transfer object.
     *
     * @return the transfer object
     */
    public TransferObjectEvent getTransferObject() {
      return transferObject;
    }

    /**
     * Sets transfer object.
     *
     * @param transferObject the transfer object
     */
    public void setTransferObject(TransferObjectEvent transferObject) {
      this.transferObject = transferObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjectEventKind)) {
        return false;
      }
      TransferObjectEventKind that = (TransferObjectEventKind) o;
      return transferObject.equals(that.transferObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(transferObject);
    }

    @Override
    public String toString() {
      return "TransferObjectEventKind{" + "transferObject=" + transferObject + '}';
    }
  }

  /** The type Mutate object event kind. */
  public static class MutateObjectEventKind extends EventKind {

    private MutateObjectEvent mutateObject;

    /**
     * Gets mutate object.
     *
     * @return the mutate object
     */
    public MutateObjectEvent getMutateObject() {
      return mutateObject;
    }

    /**
     * Sets mutate object.
     *
     * @param mutateObject the mutate object
     */
    public void setMutateObject(MutateObjectEvent mutateObject) {
      this.mutateObject = mutateObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MutateObjectEventKind)) {
        return false;
      }
      MutateObjectEventKind that = (MutateObjectEventKind) o;
      return mutateObject.equals(that.mutateObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(mutateObject);
    }

    @Override
    public String toString() {
      return "MutateObjectEventKind{" + "mutateObject=" + mutateObject + '}';
    }
  }

  /** The type Delete object event kind. */
  public static class DeleteObjectEventKind extends EventKind {

    private DeleteObjectEvent deleteObject;

    /**
     * Gets delete object.
     *
     * @return the delete object
     */
    public DeleteObjectEvent getDeleteObject() {
      return deleteObject;
    }

    /**
     * Sets delete object.
     *
     * @param deleteObject the delete object
     */
    public void setDeleteObject(DeleteObjectEvent deleteObject) {
      this.deleteObject = deleteObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DeleteObjectEventKind)) {
        return false;
      }
      DeleteObjectEventKind that = (DeleteObjectEventKind) o;
      return deleteObject.equals(that.deleteObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(deleteObject);
    }

    @Override
    public String toString() {
      return "DeleteObjectEventKind{" + "deleteObject=" + deleteObject + '}';
    }
  }

  /** The type New object event kind. */
  public static class NewObjectEventKind extends EventKind {

    private NewObjectEvent newObject;

    /**
     * Gets new object.
     *
     * @return the new object
     */
    public NewObjectEvent getNewObject() {
      return newObject;
    }

    /**
     * Sets new object.
     *
     * @param newObject the new object
     */
    public void setNewObject(NewObjectEvent newObject) {
      this.newObject = newObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NewObjectEventKind)) {
        return false;
      }
      NewObjectEventKind that = (NewObjectEventKind) o;
      return newObject.equals(that.newObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(newObject);
    }

    @Override
    public String toString() {
      return "NewObjectEventKind{" + "newObject=" + newObject + '}';
    }
  }

  /** The type Epoch change event kind. */
  public static class EpochChangeEventKind extends EventKind {

    private Long epochChange;

    /**
     * Gets epoch change.
     *
     * @return the epoch change
     */
    public Long getEpochChange() {
      return epochChange;
    }

    /**
     * Sets epoch change.
     *
     * @param epochChange the epoch change
     */
    public void setEpochChange(Long epochChange) {
      this.epochChange = epochChange;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EpochChangeEventKind)) {
        return false;
      }
      EpochChangeEventKind that = (EpochChangeEventKind) o;
      return epochChange.equals(that.epochChange);
    }

    @Override
    public int hashCode() {
      return Objects.hash(epochChange);
    }

    @Override
    public String toString() {
      return "EpochChangeEventKind{" + "epochChange=" + epochChange + '}';
    }
  }

  /** The type Checkpoint event kind. */
  public static class CheckpointEventKind extends EventKind {

    private Long checkpoint;

    /**
     * Gets checkpoint.
     *
     * @return the checkpoint
     */
    public Long getCheckpoint() {
      return checkpoint;
    }

    /**
     * Sets checkpoint.
     *
     * @param checkpoint the checkpoint
     */
    public void setCheckpoint(Long checkpoint) {
      this.checkpoint = checkpoint;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CheckpointEventKind)) {
        return false;
      }
      CheckpointEventKind that = (CheckpointEventKind) o;
      return checkpoint.equals(that.checkpoint);
    }

    @Override
    public int hashCode() {
      return Objects.hash(checkpoint);
    }

    @Override
    public String toString() {
      return "CheckpointEventKind{" + "checkpoint=" + checkpoint + '}';
    }
  }
}
