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

package io.sui.models.objects;


import java.util.List;
import java.util.Objects;

/**
 * The interface Move normalized type.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface MoveNormalizedType {

  /** The enum Type move normalized type. */
  enum TypeMoveNormalizedType implements MoveNormalizedType {
    /** Bool type move normalized type. */
    Bool,
    /** U 8 type move normalized type. */
    U8,
    /** U 16 type move normalized type. */
    U16,
    /** U 32 type move normalized type. */
    U32,
    /** U 64 type move normalized type. */
    U64,
    /** U 128 type move normalized type. */
    U128,
    /** U 256 type move normalized type. */
    U256,
    /** Address type move normalized type. */
    Address,
    /** Signer type move normalized type. */
    Signer
  }

  /** The type Reference move normalized type. */
  class ReferenceMoveNormalizedType implements MoveNormalizedType {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveNormalizedType Reference;

    /**
     * Gets reference.
     *
     * @return the reference
     */
    public MoveNormalizedType getReference() {
      return Reference;
    }

    /**
     * Sets reference.
     *
     * @param reference the reference
     */
    public void setReference(MoveNormalizedType reference) {
      Reference = reference;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ReferenceMoveNormalizedType)) {
        return false;
      }
      ReferenceMoveNormalizedType that = (ReferenceMoveNormalizedType) o;
      return Reference.equals(that.Reference);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Reference);
    }

    @Override
    public String toString() {
      return "ReferenceMoveNormalizedType{" + "Reference=" + Reference + '}';
    }
  }

  /** The type Mutable reference move normalized type. */
  class MutableReferenceMoveNormalizedType implements MoveNormalizedType {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveNormalizedType MutableReference;

    /**
     * Gets mutable reference.
     *
     * @return the mutable reference
     */
    public MoveNormalizedType getMutableReference() {
      return MutableReference;
    }

    /**
     * Sets mutable reference.
     *
     * @param mutableReference the mutable reference
     */
    public void setMutableReference(MoveNormalizedType mutableReference) {
      MutableReference = mutableReference;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MutableReferenceMoveNormalizedType)) {
        return false;
      }
      MutableReferenceMoveNormalizedType that = (MutableReferenceMoveNormalizedType) o;
      return MutableReference.equals(that.MutableReference);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MutableReference);
    }

    @Override
    public String toString() {
      return "MutableReferenceMoveNormalizedType{" + "MutableReference=" + MutableReference + '}';
    }
  }

  /** The type Vector reference move normalized type. */
  class VectorReferenceMoveNormalizedType implements MoveNormalizedType {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveNormalizedType Vector;

    /**
     * Gets vector.
     *
     * @return the vector
     */
    public MoveNormalizedType getVector() {
      return Vector;
    }

    /**
     * Sets vector.
     *
     * @param vector the vector
     */
    public void setVector(MoveNormalizedType vector) {
      Vector = vector;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof VectorReferenceMoveNormalizedType)) {
        return false;
      }
      VectorReferenceMoveNormalizedType that = (VectorReferenceMoveNormalizedType) o;
      return Vector.equals(that.Vector);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Vector);
    }

    @Override
    public String toString() {
      return "VectorReferenceMoveNormalizedType{" + "Vector=" + Vector + '}';
    }
  }

  /** The type Move normalized type parameter type. */
  class MoveNormalizedTypeParameterType implements MoveNormalizedType {

    @SuppressWarnings("checkstyle:MemberName")
    private Integer TypeParameter;

    /**
     * Gets type parameter.
     *
     * @return the type parameter
     */
    public Integer getTypeParameter() {
      return TypeParameter;
    }

    /**
     * Sets type parameter.
     *
     * @param typeParameter the type parameter
     */
    public void setTypeParameter(Integer typeParameter) {
      TypeParameter = typeParameter;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveNormalizedTypeParameterType)) {
        return false;
      }
      MoveNormalizedTypeParameterType that = (MoveNormalizedTypeParameterType) o;
      return TypeParameter.equals(that.TypeParameter);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TypeParameter);
    }

    @Override
    public String toString() {
      return "MoveNormalizedTypeParameterType{" + "TypeParameter=" + TypeParameter + '}';
    }
  }

  /** The type Move normalized struct type. */
  class MoveNormalizedStructType implements MoveNormalizedType {

    /** The type Struct. */
    public static class Struct {

      private String address;

      private String module;

      private String name;

      private List<MoveNormalizedType> typeArguments;

      /**
       * Gets address.
       *
       * @return the address
       */
      public String getAddress() {
        return address;
      }

      /**
       * Sets address.
       *
       * @param address the address
       */
      public void setAddress(String address) {
        this.address = address;
      }

      /**
       * Gets module.
       *
       * @return the module
       */
      public String getModule() {
        return module;
      }

      /**
       * Sets module.
       *
       * @param module the module
       */
      public void setModule(String module) {
        this.module = module;
      }

      /**
       * Gets name.
       *
       * @return the name
       */
      public String getName() {
        return name;
      }

      /**
       * Sets name.
       *
       * @param name the name
       */
      public void setName(String name) {
        this.name = name;
      }

      /**
       * Gets type arguments.
       *
       * @return the type arguments
       */
      public List<MoveNormalizedType> getTypeArguments() {
        return typeArguments;
      }

      /**
       * Sets type arguments.
       *
       * @param typeArguments the type arguments
       */
      @SuppressWarnings("checkstyle:ParameterName")
      public void setTypeArguments(List<MoveNormalizedType> typeArguments) {
        this.typeArguments = typeArguments;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (!(o instanceof Struct)) {
          return false;
        }
        Struct struct = (Struct) o;
        return address.equals(struct.address)
            && module.equals(struct.module)
            && name.equals(struct.name);
      }

      @Override
      public int hashCode() {
        return Objects.hash(address, module, name);
      }

      @Override
      public String toString() {
        return "Struct{"
            + "address='"
            + address
            + '\''
            + ", module='"
            + module
            + '\''
            + ", name='"
            + name
            + '\''
            + ", type_arguments="
            + typeArguments
            + '}';
      }
    }

    @SuppressWarnings("checkstyle:MemberName")
    private Struct Struct;

    /**
     * Gets struct.
     *
     * @return the struct
     */
    public MoveNormalizedStructType.Struct getStruct() {
      return Struct;
    }

    /**
     * Sets struct.
     *
     * @param struct the struct
     */
    public void setStruct(MoveNormalizedStructType.Struct struct) {
      Struct = struct;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveNormalizedStructType)) {
        return false;
      }
      MoveNormalizedStructType that = (MoveNormalizedStructType) o;
      return Struct.equals(that.Struct);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Struct);
    }

    @Override
    public String toString() {
      return "MoveNormalizedStructType{" + "Struct=" + Struct + '}';
    }
  }
}
