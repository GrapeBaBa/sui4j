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


import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type Event filter.
 *
 * @author grapebaba
 * @since 2022.12
 */
public abstract class EventFilter {

  /**
   * The type Move event field.
   *
   * @param <T> the type parameter
   */
  public static class MoveEventField<T> {

    private String path;

    private T value;

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
      return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
      this.path = path;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue() {
      return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(T value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveEventField)) {
        return false;
      }
      MoveEventField<?> that = (MoveEventField<?>) o;
      return path.equals(that.path) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(path, value);
    }

    @Override
    public String toString() {
      return "MoveEventField{" + "path='" + path + '\'' + ", value=" + value + '}';
    }
  }

  /** The type Package event filter. */
  public static class PackageEventFilter extends EventFilter {

    private String suiPackage;

    /**
     * Gets package.
     *
     * @return the package
     */
    public String getSuiPackage() {
      return suiPackage;
    }

    /**
     * Sets package.
     *
     * @param suiPackage the package
     */
    public void setSuiPackage(String suiPackage) {
      this.suiPackage = suiPackage;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PackageEventFilter)) {
        return false;
      }
      PackageEventFilter that = (PackageEventFilter) o;
      return suiPackage.equals(that.suiPackage);
    }

    @Override
    public int hashCode() {
      return Objects.hash(suiPackage);
    }

    @Override
    public String toString() {
      return "PackageEventFilter{" + "Package='" + suiPackage + '\'' + '}';
    }
  }

  /** The type Module event filter. */
  public static class ModuleEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String Module;

    /**
     * Gets module.
     *
     * @return the module
     */
    public String getModule() {
      return Module;
    }

    /**
     * Sets module.
     *
     * @param module the module
     */
    public void setModule(String module) {
      Module = module;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ModuleEventFilter)) {
        return false;
      }
      ModuleEventFilter moduleEventFilter = (ModuleEventFilter) o;
      return Module.equals(moduleEventFilter.Module);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Module);
    }

    @Override
    public String toString() {
      return "Module{" + "Module='" + Module + '\'' + '}';
    }
  }

  /** The type Move event type event filter. */
  public static class MoveEventTypeEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String MoveEventType;

    /**
     * Gets move event type.
     *
     * @return the move event type
     */
    public String getMoveEventType() {
      return MoveEventType;
    }

    /**
     * Sets move event type.
     *
     * @param moveEventType the move event type
     */
    public void setMoveEventType(String moveEventType) {
      MoveEventType = moveEventType;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveEventTypeEventFilter)) {
        return false;
      }
      MoveEventTypeEventFilter that = (MoveEventTypeEventFilter) o;
      return MoveEventType.equals(that.MoveEventType);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveEventType);
    }

    @Override
    public String toString() {
      return "MoveEventTypeEventFilter{" + "MoveEventType='" + MoveEventType + '\'' + '}';
    }
  }

  /** The type Move event field event filter. */
  public static class MoveEventFieldEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveEventField<?> MoveEventField;

    /**
     * Gets move event field.
     *
     * @return the move event field
     */
    public EventFilter.MoveEventField<?> getMoveEventField() {
      return MoveEventField;
    }

    /**
     * Sets move event field.
     *
     * @param moveEventField the move event field
     */
    public void setMoveEventField(EventFilter.MoveEventField<?> moveEventField) {
      MoveEventField = moveEventField;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveEventFieldEventFilter)) {
        return false;
      }
      MoveEventFieldEventFilter that = (MoveEventFieldEventFilter) o;
      return MoveEventField.equals(that.MoveEventField);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveEventField);
    }

    @Override
    public String toString() {
      return "MoveEventFieldEventFilter{" + "MoveEventField=" + MoveEventField + '}';
    }
  }

  /** The type Sender address event filter. */
  public static class SenderAddressEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String SenderAddress;

    /**
     * Gets sender address.
     *
     * @return the sender address
     */
    public String getSenderAddress() {
      return SenderAddress;
    }

    /**
     * Sets sender address.
     *
     * @param senderAddress the sender address
     */
    public void setSenderAddress(String senderAddress) {
      SenderAddress = senderAddress;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SenderAddressEventFilter)) {
        return false;
      }
      SenderAddressEventFilter that = (SenderAddressEventFilter) o;
      return SenderAddress.equals(that.SenderAddress);
    }

    @Override
    public int hashCode() {
      return Objects.hash(SenderAddress);
    }

    @Override
    public String toString() {
      return "SenderAddressEventFilter{" + "SenderAddress='" + SenderAddress + '\'' + '}';
    }
  }

  /** The type Event type event filter. */
  public static class EventTypeEventFilter extends EventFilter {

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
      if (!(o instanceof EventTypeEventFilter)) {
        return false;
      }
      EventTypeEventFilter that = (EventTypeEventFilter) o;
      return EventType == that.EventType;
    }

    @Override
    public int hashCode() {
      return Objects.hash(EventType);
    }

    @Override
    public String toString() {
      return "EventTypeEventFilter{" + "EventType=" + EventType + '}';
    }
  }

  /** The type All event filter. */
  public static class AllEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private List<EventFilter> All = Lists.newArrayList();

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<EventFilter> getAll() {
      return All;
    }

    /**
     * Sets all.
     *
     * @param all the all
     */
    public void setAll(List<EventFilter> all) {
      All = all;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof AllEventFilter)) {
        return false;
      }
      AllEventFilter that = (AllEventFilter) o;
      return All.equals(that.All);
    }

    @Override
    public int hashCode() {
      return Objects.hash(All);
    }

    @Override
    public String toString() {
      return "AllEventFilter{" + "All=" + All + '}';
    }
  }

  /** The type Any event filter. */
  public static class AnyEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private List<EventFilter> Any = Lists.newArrayList();

    /**
     * Gets any.
     *
     * @return the any
     */
    public List<EventFilter> getAny() {
      return Any;
    }

    /**
     * Sets any.
     *
     * @param any the any
     */
    public void setAny(List<EventFilter> any) {
      Any = any;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof AnyEventFilter)) {
        return false;
      }
      AnyEventFilter that = (AnyEventFilter) o;
      return Any.equals(that.Any);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Any);
    }

    @Override
    public String toString() {
      return "AnyEventFilter{" + "Any=" + Any + '}';
    }
  }

  /** The type And event filter. */
  public static class AndEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private final EventFilter[] And = new EventFilter[2];

    /**
     * Get and event filter [ ].
     *
     * @return the event filter [ ]
     */
    public EventFilter[] getAnd() {
      return And;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof AndEventFilter)) {
        return false;
      }
      AndEventFilter that = (AndEventFilter) o;
      return Arrays.equals(And, that.And);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(And);
    }

    @Override
    public String toString() {
      return "AndEventFilter{" + "And=" + Arrays.toString(And) + '}';
    }
  }

  /** The type Or event filter. */
  public static class OrEventFilter extends EventFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private final EventFilter[] Or = new EventFilter[2];

    /**
     * Get or event filter [ ].
     *
     * @return the event filter [ ]
     */
    public EventFilter[] getOr() {
      return Or;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof OrEventFilter)) {
        return false;
      }
      OrEventFilter that = (OrEventFilter) o;
      return Arrays.equals(Or, that.Or);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(Or);
    }

    @Override
    public String toString() {
      return "OrEventFilter{" + "Or=" + Arrays.toString(Or) + '}';
    }
  }
}
