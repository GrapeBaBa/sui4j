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

package io.sui.models.objects;


import java.util.Objects;

/**
 * The interface Move function arg type.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface MoveFunctionArgType {

  /** The enum Pure function move function arg type. */
  enum PureFunctionMoveFunctionArgType implements MoveFunctionArgType {

    /** Pure pure function move function arg type. */
    Pure
  }

  /** The type Object value kind move function arg type. */
  class ObjectValueKindMoveFunctionArgType implements MoveFunctionArgType {

    /** The enum Object value kind. */
    public enum ObjectValueKind {
      /** By immutable reference object value kind. */
      ByImmutableReference,
      /** By mutable reference object value kind. */
      ByMutableReference,
      /** By value object value kind. */
      ByValue
    }

    @SuppressWarnings("checkstyle:MemberName")
    private ObjectValueKind Object;

    /**
     * Gets object.
     *
     * @return the object
     */
    public ObjectValueKind getObject() {
      return Object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(ObjectValueKind object) {
      Object = object;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectValueKindMoveFunctionArgType)) {
        return false;
      }
      ObjectValueKindMoveFunctionArgType that = (ObjectValueKindMoveFunctionArgType) o;
      return Object == that.Object;
    }

    @Override
    public int hashCode() {
      return Objects.hash(Object);
    }

    @Override
    public String toString() {
      return "ObjectValueKindMoveFunctionArgType{" + "Object=" + Object + '}';
    }
  }
}
