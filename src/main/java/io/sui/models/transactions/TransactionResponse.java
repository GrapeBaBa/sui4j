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

package io.sui.models.transactions;


import io.sui.models.events.SuiEvent;
import io.sui.models.objects.ObjectChange;
import java.util.List;
import java.util.Objects;

/**
 * The type Transaction response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransactionResponse {

  private String digest;

  private Transaction transaction;

  private TransactionEffects effects;

  private List<SuiEvent> events;

  @SuppressWarnings("checkstyle:MemberName")
  private Long timestampMs;

  private Long checkpoint;

  private boolean confirmedLocalExecution;

  private List<ObjectChange> objectChanges;

  private List<String> errors;

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
   * Gets transaction.
   *
   * @return the transaction
   */
  public Transaction getTransaction() {
    return transaction;
  }

  /**
   * Sets transaction.
   *
   * @param transaction the transaction
   */
  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  /**
   * Gets effects.
   *
   * @return the effects
   */
  public TransactionEffects getEffects() {
    return effects;
  }

  /**
   * Sets effects.
   *
   * @param effects the effects
   */
  public void setEffects(TransactionEffects effects) {
    this.effects = effects;
  }

  /**
   * Gets events.
   *
   * @return the events
   */
  public List<SuiEvent> getEvents() {
    return events;
  }

  /**
   * Sets events.
   *
   * @param events the events
   */
  public void setEvents(List<SuiEvent> events) {
    this.events = events;
  }

  /**
   * Gets timestamp ms.
   *
   * @return the timestamp ms
   */
  public Long getTimestampMs() {
    return timestampMs;
  }

  /**
   * Sets timestamp ms.
   *
   * @param timestampMs the timestamp ms
   */
  public void setTimestampMs(Long timestampMs) {
    this.timestampMs = timestampMs;
  }

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

  /**
   * Is confirmed local execution boolean.
   *
   * @return the boolean
   */
  public boolean isConfirmedLocalExecution() {
    return confirmedLocalExecution;
  }

  /**
   * Sets confirmed local execution.
   *
   * @param confirmedLocalExecution the confirmed local execution
   */
  public void setConfirmedLocalExecution(boolean confirmedLocalExecution) {
    this.confirmedLocalExecution = confirmedLocalExecution;
  }

  /**
   * Gets object changes.
   *
   * @return the object changes
   */
  public List<ObjectChange> getObjectChanges() {
    return objectChanges;
  }

  /**
   * Sets object changes.
   *
   * @param objectChanges the object changes
   */
  public void setObjectChanges(List<ObjectChange> objectChanges) {
    this.objectChanges = objectChanges;
  }

  /**
   * Gets errors.
   *
   * @return the errors
   */
  public List<String> getErrors() {
    return errors;
  }

  /**
   * Sets errors.
   *
   * @param errors the errors
   */
  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionResponse)) {
      return false;
    }
    TransactionResponse that = (TransactionResponse) o;
    return confirmedLocalExecution == that.confirmedLocalExecution
        && digest.equals(that.digest)
        && transaction.equals(that.transaction)
        && effects.equals(that.effects)
        && events.equals(that.events)
        && timestampMs.equals(that.timestampMs)
        && checkpoint.equals(that.checkpoint)
        && objectChanges.equals(that.objectChanges)
        && errors.equals(that.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        digest,
        transaction,
        effects,
        events,
        timestampMs,
        checkpoint,
        confirmedLocalExecution,
        objectChanges,
        errors);
  }

  @Override
  public String toString() {
    return "TransactionResponse{"
        + "digest='"
        + digest
        + '\''
        + ", transaction="
        + transaction
        + ", effects="
        + effects
        + ", events="
        + events
        + ", timestampMs="
        + timestampMs
        + ", checkpoint="
        + checkpoint
        + ", confirmedLocalExecution="
        + confirmedLocalExecution
        + ", objectChanges="
        + objectChanges
        + ", errors="
        + errors
        + '}';
  }
}
