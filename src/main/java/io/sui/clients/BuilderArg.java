/*
 * Copyright 2023 281165273grape@gmail.com
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

package io.sui.clients;


import io.sui.bcsgen.ObjectID;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Builder arg.
 *
 * @author grapebaba
 * @since 2023.04
 */
public abstract class BuilderArg {

  /** The type Object builder arg. */
  public static class ObjectBuilderArg extends BuilderArg {

    private ObjectID id;

    public ObjectBuilderArg(ObjectID id) {
      this.id = id;
    }

    /**
     * Gets object id.
     *
     * @return the object id
     */
    public ObjectID getId() {
      return id;
    }

    /**
     * Sets object id.
     *
     * @param id the object id
     */
    public void setId(ObjectID id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectBuilderArg)) {
        return false;
      }
      ObjectBuilderArg that = (ObjectBuilderArg) o;
      return id.equals(that.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }

    @Override
    public String toString() {
      return "ObjectBuilderArg{" + "objectID=" + id + '}';
    }
  }

  /** The type Pure builder arg. */
  public static class PureBuilderArg extends BuilderArg {

    private byte[] value;

    public PureBuilderArg(byte[] value) {
      this.value = value;
    }

    /**
     * Get value byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getValue() {
      return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(byte[] value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PureBuilderArg)) {
        return false;
      }
      PureBuilderArg that = (PureBuilderArg) o;
      return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
      return "PureBuilderArg{" + "value=" + Arrays.toString(value) + '}';
    }
  }

  /** The type Forced non unique pure builder arg. */
  public static class ForcedNonUniquePureBuilderArg extends BuilderArg {

    private int value;

    public ForcedNonUniquePureBuilderArg(int value) {
      this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
      return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ForcedNonUniquePureBuilderArg)) {
        return false;
      }
      ForcedNonUniquePureBuilderArg that = (ForcedNonUniquePureBuilderArg) o;
      return value == that.value;
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    @Override
    public String toString() {
      return "ForcedNonUniquePureBuilderArg{" + "value=" + value + '}';
    }
  }
}
