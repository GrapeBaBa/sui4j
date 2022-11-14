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


import io.sui.models.objects.SuiObjectOwner;
import java.util.Objects;

/**
 * The interface Event query.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface EventQuery {

  /** The enum All query. */
  enum AllQuery implements EventQuery {
    /** All all query. */
    All
  }

  /** The type Transaction event query. */
  class TransactionEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private String Transaction;

    /**
     * Gets transaction.
     *
     * @return the transaction
     */
    public String getTransaction() {
      return Transaction;
    }

    /**
     * Sets transaction.
     *
     * @param transaction the transaction
     */
    public void setTransaction(String transaction) {
      Transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransactionEventQuery)) {
        return false;
      }
      TransactionEventQuery that = (TransactionEventQuery) o;
      return Transaction.equals(that.Transaction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Transaction);
    }

    @Override
    public String toString() {
      return "Transaction{" + "Transaction='" + Transaction + '\'' + '}';
    }
  }

  /** The type Move module event query. */
  class MoveModuleEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveModule MoveModule;

    /**
     * Gets move module.
     *
     * @return the move module
     */
    public io.sui.models.events.MoveModule getMoveModule() {
      return MoveModule;
    }

    /**
     * Sets move module.
     *
     * @param moveModule the move module
     */
    public void setMoveModule(io.sui.models.events.MoveModule moveModule) {
      MoveModule = moveModule;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveModuleEventQuery)) {
        return false;
      }
      MoveModuleEventQuery that = (MoveModuleEventQuery) o;
      return MoveModule.equals(that.MoveModule);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveModule);
    }

    @Override
    public String toString() {
      return "MoveModuleEventQuery{" + "MoveModule=" + MoveModule + '}';
    }
  }

  /** The type Move event event query. */
  class MoveEventEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private String MoveEvent;

    /**
     * Gets move event.
     *
     * @return the move event
     */
    public String getMoveEvent() {
      return MoveEvent;
    }

    /**
     * Sets move event.
     *
     * @param moveEvent the move event
     */
    public void setMoveEvent(String moveEvent) {
      MoveEvent = moveEvent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveEventEventQuery)) {
        return false;
      }
      MoveEventEventQuery that = (MoveEventEventQuery) o;
      return MoveEvent.equals(that.MoveEvent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveEvent);
    }

    @Override
    public String toString() {
      return "MoveEventEventQuery{" + "MoveEvent='" + MoveEvent + '\'' + '}';
    }
  }

  /** The type Event type event query. */
  class EventTypeEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private EventType EventType;

    /**
     * Gets event type.
     *
     * @return the event type
     */
    public EventType getEventType() {
      return EventType;
    }

    /**
     * Sets event type.
     *
     * @param eventType the event type
     */
    public void setEventType(EventType eventType) {
      EventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EventTypeEventQuery)) {
        return false;
      }
      EventTypeEventQuery that = (EventTypeEventQuery) o;
      return EventType == that.EventType;
    }

    @Override
    public int hashCode() {
      return Objects.hash(EventType);
    }

    @Override
    public String toString() {
      return "EventTypeEventQuery{" + "EventType=" + EventType + '}';
    }
  }

  /** The type Sender event query. */
  class SenderEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private String Sender;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return Sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      Sender = sender;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SenderEventQuery)) {
        return false;
      }
      SenderEventQuery that = (SenderEventQuery) o;
      return Sender.equals(that.Sender);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Sender);
    }

    @Override
    public String toString() {
      return "SenderEventQuery{" + "Sender='" + Sender + '\'' + '}';
    }
  }

  /** The type Recipient event query. */
  class RecipientEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private SuiObjectOwner Recipient;

    /**
     * Gets recipient.
     *
     * @return the recipient
     */
    public SuiObjectOwner getRecipient() {
      return Recipient;
    }

    /**
     * Sets recipient.
     *
     * @param recipient the recipient
     */
    public void setRecipient(SuiObjectOwner recipient) {
      Recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof RecipientEventQuery)) {
        return false;
      }
      RecipientEventQuery that = (RecipientEventQuery) o;
      return Recipient.equals(that.Recipient);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Recipient);
    }

    @Override
    public String toString() {
      return "RecipientEventQuery{" + "Recipient=" + Recipient + '}';
    }
  }

  /** The type Object event query. */
  class ObjectEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private String Object;

    /**
     * Gets object.
     *
     * @return the object
     */
    public String getObject() {
      return Object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(String object) {
      Object = object;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectEventQuery)) {
        return false;
      }
      ObjectEventQuery that = (ObjectEventQuery) o;
      return Object.equals(that.Object);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Object);
    }

    @Override
    public String toString() {
      return "ObjectEventQuery{" + "Object='" + Object + '\'' + '}';
    }
  }

  /** The type Time range event query. */
  class TimeRangeEventQuery implements EventQuery {

    @SuppressWarnings("checkstyle:MemberName")
    private TimeRange TimeRange;

    /**
     * Gets time range.
     *
     * @return the time range
     */
    public TimeRange getTimeRange() {
      return TimeRange;
    }

    /**
     * Sets time range.
     *
     * @param timeRange the time range
     */
    public void setTimeRange(TimeRange timeRange) {
      TimeRange = timeRange;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TimeRangeEventQuery)) {
        return false;
      }
      TimeRangeEventQuery that = (TimeRangeEventQuery) o;
      return TimeRange.equals(that.TimeRange);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TimeRange);
    }

    @Override
    public String toString() {
      return "TimeRangeEventQuery{" + "TimeRange=" + TimeRange + '}';
    }
  }
}
