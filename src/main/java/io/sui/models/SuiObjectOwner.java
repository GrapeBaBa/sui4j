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

package io.sui.models;


import java.math.BigInteger;
import java.util.Objects;

/**
 * The interface Sui object owner.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface SuiObjectOwner {

  /** The enum String sui object owner. */
  enum StringSuiObjectOwner implements SuiObjectOwner {
    /** Immutable string sui object owner. */
    Immutable
  }

  /** The type Address owner. */
  class AddressOwner implements SuiObjectOwner {

    @SuppressWarnings("checkstyle:MemberName")
    private String AddressOwner;

    /**
     * Gets address owner.
     *
     * @return the address owner
     */
    public String getAddressOwner() {
      return AddressOwner;
    }

    /**
     * Sets address owner.
     *
     * @param addressOwner the address owner
     */
    public void setAddressOwner(String addressOwner) {
      this.AddressOwner = addressOwner;
    }

    @Override
    public String toString() {
      return "AddressOwner{" + "addressOwner='" + AddressOwner + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      AddressOwner that = (AddressOwner) o;
      return AddressOwner.equals(that.AddressOwner);
    }

    @Override
    public int hashCode() {
      return Objects.hash(AddressOwner);
    }
  }

  /** The type Object owner. */
  class ObjectOwner implements SuiObjectOwner {

    @SuppressWarnings("checkstyle:MemberName")
    private String ObjectOwner;

    /**
     * Gets object owner.
     *
     * @return the object owner
     */
    public String getObjectOwner() {
      return ObjectOwner;
    }

    /**
     * Sets object owner.
     *
     * @param objectOwner the object owner
     */
    public void setObjectOwner(String objectOwner) {
      this.ObjectOwner = objectOwner;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      ObjectOwner that = (ObjectOwner) o;
      return ObjectOwner.equals(that.ObjectOwner);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ObjectOwner);
    }

    @Override
    public String toString() {
      return "ObjectOwner{" + "objectOwner='" + ObjectOwner + '\'' + '}';
    }
  }

  /** The type Shared owner. */
  class SharedOwner implements SuiObjectOwner {

    /** The type Shared. */
    public static class Shared {

      @SuppressWarnings("checkstyle:MemberName")
      private BigInteger initial_shared_version;

      /**
       * Gets initial shared version.
       *
       * @return the initial shared version
       */
      public BigInteger getInitial_shared_version() {
        return initial_shared_version;
      }

      /**
       * Sets initial shared version.
       *
       * @param initial_shared_version the initial shared version
       */
      @SuppressWarnings("checkstyle:ParameterName")
      public void setInitial_shared_version(BigInteger initial_shared_version) {
        this.initial_shared_version = initial_shared_version;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        Shared shared = (Shared) o;
        return initial_shared_version.equals(shared.initial_shared_version);
      }

      @Override
      public int hashCode() {
        return Objects.hash(initial_shared_version);
      }

      @Override
      public String toString() {
        return "Shared{" + "initialSharedVersion=" + initial_shared_version + '}';
      }
    }

    @SuppressWarnings("checkstyle:MemberName")
    private Shared Shared;

    /**
     * Gets shared.
     *
     * @return the shared
     */
    public Shared getShared() {
      return Shared;
    }

    /**
     * Sets shared.
     *
     * @param shared the shared
     */
    public void setShared(Shared shared) {
      this.Shared = shared;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      SharedOwner that = (SharedOwner) o;
      return Shared.equals(that.Shared);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Shared);
    }

    @Override
    public String toString() {
      return "SharedOwner{" + "shared=" + Shared + '}';
    }
  }
}
