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

package io.sui.models.events;


import java.util.Objects;

/**
 * The type Event id.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class EventId {

  private String txDigest;

  private Long eventSeq;

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

  public String getTxDigest() {
    return txDigest;
  }

  public void setTxDigest(String txDigest) {
    this.txDigest = txDigest;
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
    return txDigest.equals(eventId.txDigest) && eventSeq.equals(eventId.eventSeq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txDigest, eventSeq);
  }

  @Override
  public String toString() {
    return "EventId{" + "txDigest=" + txDigest + ", eventSeq=" + eventSeq + '}';
  }
}
