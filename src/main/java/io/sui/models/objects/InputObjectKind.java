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
 * The type Input object.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class InputObjectKind {

  /** The type Imm or owned move object. */
  public static class ImmOrOwnedMoveObjectKind extends InputObjectKind {

    @SuppressWarnings("checkstyle:MemberName")
    private SuiObjectRef ImmOrOwnedMoveObject;

    /**
     * Gets imm or owned move object.
     *
     * @return the imm or owned move object
     */
    public SuiObjectRef getImmOrOwnedMoveObject() {
      return ImmOrOwnedMoveObject;
    }

    /**
     * Sets imm or owned move object.
     *
     * @param immOrOwnedMoveObject the imm or owned move object
     */
    public void setImmOrOwnedMoveObject(SuiObjectRef immOrOwnedMoveObject) {
      ImmOrOwnedMoveObject = immOrOwnedMoveObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ImmOrOwnedMoveObjectKind)) {
        return false;
      }
      ImmOrOwnedMoveObjectKind that = (ImmOrOwnedMoveObjectKind) o;
      return ImmOrOwnedMoveObject.equals(that.ImmOrOwnedMoveObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ImmOrOwnedMoveObject);
    }

    @Override
    public String toString() {
      return "ImmOrOwnedMoveObject{"
          + "ImmOrOwnedMoveObject="
          + ImmOrOwnedMoveObject
          + "} "
          + super.toString();
    }
  }

  /** The type Shared move object. */
  public static class SharedMoveObjectKind extends InputObjectKind {

    /** The type Shared object. */
    public static class SharedObject {

      private String id;

      @SuppressWarnings("checkstyle:MemberName")
      private Long initial_shared_version;

      /**
       * Gets id.
       *
       * @return the id
       */
      public String getId() {
        return id;
      }

      /**
       * Sets id.
       *
       * @param id the id
       */
      public void setId(String id) {
        this.id = id;
      }

      /**
       * Gets initial shared version.
       *
       * @return the initial shared version
       */
      public Long getInitial_shared_version() {
        return initial_shared_version;
      }

      /**
       * Sets initial shared version.
       *
       * @param initial_shared_version the initial shared version
       */
      @SuppressWarnings("checkstyle:ParameterName")
      public void setInitial_shared_version(Long initial_shared_version) {
        this.initial_shared_version = initial_shared_version;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (!(o instanceof SharedObject)) {
          return false;
        }
        SharedObject that = (SharedObject) o;
        return id.equals(that.id) && initial_shared_version.equals(that.initial_shared_version);
      }

      @Override
      public int hashCode() {
        return Objects.hash(id, initial_shared_version);
      }

      @Override
      public String toString() {
        return "SharedObject{"
            + "id='"
            + id
            + '\''
            + ", initial_shared_version="
            + initial_shared_version
            + '}';
      }
    }

    @SuppressWarnings("checkstyle:MemberName")
    private SharedObject SharedMoveObject;

    /**
     * Gets shared move object.
     *
     * @return the shared move object
     */
    public SharedObject getSharedMoveObject() {
      return SharedMoveObject;
    }

    /**
     * Sets shared move object.
     *
     * @param sharedMoveObject the shared move object
     */
    public void setSharedMoveObject(SharedObject sharedMoveObject) {
      SharedMoveObject = sharedMoveObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SharedMoveObjectKind)) {
        return false;
      }
      SharedMoveObjectKind that = (SharedMoveObjectKind) o;
      return SharedMoveObject.equals(that.SharedMoveObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(SharedMoveObject);
    }

    @Override
    public String toString() {
      return "SharedMoveObject{" + "SharedMoveObject=" + SharedMoveObject + "} " + super.toString();
    }
  }

  /** The type Move package. */
  public static class MovePackageKind extends InputObjectKind {

    @SuppressWarnings("checkstyle:MemberName")
    private String MovePackage;

    /**
     * Gets move package.
     *
     * @return the move package
     */
    public String getMovePackage() {
      return MovePackage;
    }

    /**
     * Sets move package.
     *
     * @param movePackage the move package
     */
    public void setMovePackage(String movePackage) {
      MovePackage = movePackage;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MovePackageKind)) {
        return false;
      }
      MovePackageKind that = (MovePackageKind) o;
      return MovePackage.equals(that.MovePackage);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MovePackage);
    }

    @Override
    public String toString() {
      return "MovePackage{" + "MovePackage='" + MovePackage + '\'' + "} " + super.toString();
    }
  }
}
