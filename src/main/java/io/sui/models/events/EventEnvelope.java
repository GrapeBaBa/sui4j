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
 * The type Event envelope.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class EventEnvelope {

  private Long timestamp;

  private String txDigest;

  private EventId id;

  private EventKind event;

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets timestamp.
   *
   * @param timestamp the timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets tx digest.
   *
   * @return the tx digest
   */
  public String getTxDigest() {
    return txDigest;
  }

  /**
   * Sets tx digest.
   *
   * @param txDigest the tx digest
   */
  public void setTxDigest(String txDigest) {
    this.txDigest = txDigest;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public EventId getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(EventId id) {
    this.id = id;
  }

  /**
   * Gets event.
   *
   * @return the event
   */
  public EventKind getEvent() {
    return event;
  }

  /**
   * Sets event.
   *
   * @param event the event
   */
  public void setEvent(EventKind event) {
    this.event = event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventEnvelope)) {
      return false;
    }
    EventEnvelope that = (EventEnvelope) o;
    return timestamp.equals(that.timestamp)
        && txDigest.equals(that.txDigest)
        && id.equals(that.id)
        && event.equals(that.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, txDigest, id, event);
  }

  @Override
  public String toString() {
    return "EventEnvelop{"
        + "timestamp="
        + timestamp
        + ", txDigest='"
        + txDigest
        + '\''
        + ", id="
        + id
        + ", event="
        + event
        + '}';
  }
}
