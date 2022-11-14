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
 * The type Event id.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class EventId {

  private Long txSeq;

  private Long eventSeq;

  /**
   * Gets tx seq.
   *
   * @return the tx seq
   */
  public Long getTxSeq() {
    return txSeq;
  }

  /**
   * Sets tx seq.
   *
   * @param txSeq the tx seq
   */
  public void setTxSeq(Long txSeq) {
    this.txSeq = txSeq;
  }

  /**
   * Gets event seq.
   *
   * @return the event seq
   */
  public Long getEventSeq() {
    return eventSeq;
  }

  /**
   * Sets event seq.
   *
   * @param eventSeq the event seq
   */
  public void setEventSeq(Long eventSeq) {
    this.eventSeq = eventSeq;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventId)) {
      return false;
    }
    EventId eventId = (EventId) o;
    return txSeq.equals(eventId.txSeq) && eventSeq.equals(eventId.eventSeq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txSeq, eventSeq);
  }

  @Override
  public String toString() {
    return "EventId{" + "txSeq=" + txSeq + ", eventSeq=" + eventSeq + '}';
  }
}
