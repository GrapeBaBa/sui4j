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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The interface Type tag.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface TypeTag {

  String toString();

  /** The enum Simple type. */
  enum SimpleType implements TypeTag {
    /** Bool simple type. */
    bool,
    /** U8 simple type. */
    u8,
    /** U16 simple type. */
    u16,
    /** U32 simple type. */
    u32,
    /** U64 simple type. */
    u64,
    /** U128 simple type. */
    u128,
    /** U256 simple type. */
    u256,
    /** Address simple type. */
    address,
    /** Signer simple type. */
    signer
  }

  /** The type Vector type. */
  class VectorType implements TypeTag {

    private TypeTag typeTag;

    /**
     * Gets type tag.
     *
     * @return the type tag
     */
    public TypeTag getTypeTag() {
      return typeTag;
    }

    /**
     * Sets type tag.
     *
     * @param typeTag the type tag
     */
    public void setTypeTag(TypeTag typeTag) {
      this.typeTag = typeTag;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof VectorType)) {
        return false;
      }
      VectorType that = (VectorType) o;
      return typeTag.equals(that.typeTag);
    }

    @Override
    public int hashCode() {
      return Objects.hash(typeTag);
    }

    @Override
    public String toString() {
      return String.format("vector<%s>", typeTag.toString());
    }
  }

  /** The type Struct type. */
  class StructType implements TypeTag {

    private StructTag structTag;

    /**
     * Gets struct tag.
     *
     * @return the struct tag
     */
    public StructTag getStructTag() {
      return structTag;
    }

    /**
     * Sets struct tag.
     *
     * @param structTag the struct tag
     */
    public void setStructTag(StructTag structTag) {
      this.structTag = structTag;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof StructType)) {
        return false;
      }
      StructType that = (StructType) o;
      return structTag.equals(that.structTag);
    }

    @Override
    public int hashCode() {
      return Objects.hash(structTag);
    }

    @Override
    public String toString() {
      return structTag.toString();
    }
  }
}
