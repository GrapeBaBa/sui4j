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

  private List<SuiEvent> events;

  private List<String> dependencies;

  public ExecutionStatus getStatus() {
    return status;
  }

  public void setStatus(ExecutionStatus status) {
    this.status = status;
  }

  public GasCostSummary getGasUsed() {
    return gasUsed;
  }

  public void setGasUsed(GasCostSummary gasUsed) {
    this.gasUsed = gasUsed;
  }

  public List<SuiObjectRef> getSharedObjects() {
    return sharedObjects;
  }

  public void setSharedObjects(List<SuiObjectRef> sharedObjects) {
    this.sharedObjects = sharedObjects;
  }

  public String getTransactionDigest() {
    return transactionDigest;
  }

  public void setTransactionDigest(String transactionDigest) {
    this.transactionDigest = transactionDigest;
  }

  public List<SuiOwnerObjectRef> getCreated() {
    return created;
  }

  public void setCreated(List<SuiOwnerObjectRef> created) {
    this.created = created;
  }

  public List<SuiOwnerObjectRef> getMutated() {
    return mutated;
  }

  public void setMutated(List<SuiOwnerObjectRef> mutated) {
    this.mutated = mutated;
  }

  public List<SuiOwnerObjectRef> getUnwrapped() {
    return unwrapped;
  }

  public void setUnwrapped(List<SuiOwnerObjectRef> unwrapped) {
    this.unwrapped = unwrapped;
  }

  public List<SuiObjectRef> getDeleted() {
    return deleted;
  }

  public void setDeleted(List<SuiObjectRef> deleted) {
    this.deleted = deleted;
  }

  public List<SuiObjectRef> getWrapped() {
    return wrapped;
  }

  public void setWrapped(List<SuiObjectRef> wrapped) {
    this.wrapped = wrapped;
  }

  public SuiOwnerObjectRef getGasObject() {
    return gasObject;
  }

  public void setGasObject(SuiOwnerObjectRef gasObject) {
    this.gasObject = gasObject;
  }

  public List<SuiEvent> getEvents() {
    return events;
  }

  public void setEvents(List<SuiEvent> events) {
    this.events = events;
  }

  public List<String> getDependencies() {
    return dependencies;
  }

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
    return status.equals(that.status) && gasUsed.equals(that.gasUsed) && sharedObjects.equals(
        that.sharedObjects) && transactionDigest.equals(that.transactionDigest) && created.equals(
        that.created) && mutated.equals(that.mutated) && unwrapped.equals(that.unwrapped)
        && deleted.equals(that.deleted) && wrapped.equals(that.wrapped) && gasObject.equals(
        that.gasObject) && events.equals(that.events) && dependencies.equals(that.dependencies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, gasUsed, sharedObjects, transactionDigest, created, mutated,
        unwrapped, deleted, wrapped, gasObject, events, dependencies);
  }

  @Override
  public String toString() {
    return "TransactionEffects{"
        + "status=" + status
        + ", gasUsed=" + gasUsed
        + ", sharedObjects=" + sharedObjects
        + ", transactionDigest='" + transactionDigest + '\''
        + ", created=" + created
        + ", mutated=" + mutated
        + ", unwrapped=" + unwrapped
        + ", deleted=" + deleted
        + ", wrapped=" + wrapped
        + ", gasObject=" + gasObject
        + ", events=" + events
        + ", dependencies=" + dependencies
        + '}';
  }
}
