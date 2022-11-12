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


import io.sui.models.events.EventKind;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiOwnerObjectRef;
import java.util.List;
import java.util.Objects;

/**
 * The type Transaction effects.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionEffects {

  private ExecutionStatus status;

  private GasCostSummary gasUsed;

  private List<SuiObjectRef> sharedObjects;

  private String transactionDigest;

  private List<SuiOwnerObjectRef> created;

  private List<SuiOwnerObjectRef> mutated;

  private List<SuiOwnerObjectRef> unwrapped;

  private List<SuiObjectRef> deleted;

  private List<SuiObjectRef> wrapped;

  private SuiOwnerObjectRef gasObject;

  private List<EventKind> events;

  private List<String> dependencies;

  /**
   * Gets status.
   *
   * @return the status
   */
  public ExecutionStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(ExecutionStatus status) {
    this.status = status;
  }

  /**
   * Gets gas used.
   *
   * @return the gas used
   */
  public GasCostSummary getGasUsed() {
    return gasUsed;
  }

  /**
   * Sets gas used.
   *
   * @param gasUsed the gas used
   */
  public void setGasUsed(GasCostSummary gasUsed) {
    this.gasUsed = gasUsed;
  }

  /**
   * Gets shared objects.
   *
   * @return the shared objects
   */
  public List<SuiObjectRef> getSharedObjects() {
    return sharedObjects;
  }

  /**
   * Sets shared objects.
   *
   * @param sharedObjects the shared objects
   */
  public void setSharedObjects(List<SuiObjectRef> sharedObjects) {
    this.sharedObjects = sharedObjects;
  }

  /**
   * Gets transaction digest.
   *
   * @return the transaction digest
   */
  public String getTransactionDigest() {
    return transactionDigest;
  }

  /**
   * Sets transaction digest.
   *
   * @param transactionDigest the transaction digest
   */
  public void setTransactionDigest(String transactionDigest) {
    this.transactionDigest = transactionDigest;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  public List<SuiOwnerObjectRef> getCreated() {
    return created;
  }

  /**
   * Sets created.
   *
   * @param created the created
   */
  public void setCreated(List<SuiOwnerObjectRef> created) {
    this.created = created;
  }

  /**
   * Gets mutated.
   *
   * @return the mutated
   */
  public List<SuiOwnerObjectRef> getMutated() {
    return mutated;
  }

  /**
   * Sets mutated.
   *
   * @param mutated the mutated
   */
  public void setMutated(List<SuiOwnerObjectRef> mutated) {
    this.mutated = mutated;
  }

  /**
   * Gets unwrapped.
   *
   * @return the unwrapped
   */
  public List<SuiOwnerObjectRef> getUnwrapped() {
    return unwrapped;
  }

  /**
   * Sets unwrapped.
   *
   * @param unwrapped the unwrapped
   */
  public void setUnwrapped(List<SuiOwnerObjectRef> unwrapped) {
    this.unwrapped = unwrapped;
  }

  /**
   * Gets deleted.
   *
   * @return the deleted
   */
  public List<SuiObjectRef> getDeleted() {
    return deleted;
  }

  /**
   * Sets deleted.
   *
   * @param deleted the deleted
   */
  public void setDeleted(List<SuiObjectRef> deleted) {
    this.deleted = deleted;
  }

  /**
   * Gets wrapped.
   *
   * @return the wrapped
   */
  public List<SuiObjectRef> getWrapped() {
    return wrapped;
  }

  /**
   * Sets wrapped.
   *
   * @param wrapped the wrapped
   */
  public void setWrapped(List<SuiObjectRef> wrapped) {
    this.wrapped = wrapped;
  }

  /**
   * Gets gas object.
   *
   * @return the gas object
   */
  public SuiOwnerObjectRef getGasObject() {
    return gasObject;
  }

  /**
   * Sets gas object.
   *
   * @param gasObject the gas object
   */
  public void setGasObject(SuiOwnerObjectRef gasObject) {
    this.gasObject = gasObject;
  }

  /**
   * Gets events.
   *
   * @return the events
   */
  public List<EventKind> getEvents() {
    return events;
  }

  /**
   * Sets events.
   *
   * @param events the events
   */
  public void setEvents(List<EventKind> events) {
    this.events = events;
  }

  /**
   * Gets dependencies.
   *
   * @return the dependencies
   */
  public List<String> getDependencies() {
    return dependencies;
  }

  /**
   * Sets dependencies.
   *
   * @param dependencies the dependencies
   */
  public void setDependencies(List<String> dependencies) {
    this.dependencies = dependencies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionEffects that = (TransactionEffects) o;
    return status.equals(that.status)
        && gasUsed.equals(that.gasUsed)
        && sharedObjects.equals(that.sharedObjects)
        && transactionDigest.equals(that.transactionDigest)
        && created.equals(that.created)
        && mutated.equals(that.mutated)
        && unwrapped.equals(that.unwrapped)
        && deleted.equals(that.deleted)
        && wrapped.equals(that.wrapped)
        && gasObject.equals(that.gasObject)
        && events.equals(that.events)
        && dependencies.equals(that.dependencies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        status,
        gasUsed,
        sharedObjects,
        transactionDigest,
        created,
        mutated,
        unwrapped,
        deleted,
        wrapped,
        gasObject,
        events,
        dependencies);
  }

  @Override
  public String toString() {
    return "TransactionEffects{"
        + "status="
        + status
        + ", gasUsed="
        + gasUsed
        + ", sharedObjects="
        + sharedObjects
        + ", transactionDigest='"
        + transactionDigest
        + '\''
        + ", created="
        + created
        + ", mutated="
        + mutated
        + ", unwrapped="
        + unwrapped
        + ", deleted="
        + deleted
        + ", wrapped="
        + wrapped
        + ", gasObject="
        + gasObject
        + ", events="
        + events
        + ", dependencies="
        + dependencies
        + '}';
  }
}
