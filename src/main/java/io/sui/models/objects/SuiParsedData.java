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

package io.sui.models.objects;


import java.util.Map;
import java.util.Objects;

/**
 * The type Sui parsed data.
 *
 * @author grapebaba
 * @since 2023.03
 */
public abstract class SuiParsedData {

  /** The type Move object. */
  public static class MoveObject extends SuiParsedData {

    private String type;

    private Map<String, ?> fields;

    private boolean hasPublicTransfer;

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
      this.type = type;
    }

    /**
     * Gets fields.
     *
     * @return the fields
     */
    public Map<String, ?> getFields() {
      return fields;
    }

    /**
     * Sets fields.
     *
     * @param fields the fields
     */
    public void setFields(Map<String, ?> fields) {
      this.fields = fields;
    }

    /**
     * Is has public transfer boolean.
     *
     * @return the boolean
     */
    public boolean isHasPublicTransfer() {
      return hasPublicTransfer;
    }

    /**
     * Sets has public transfer.
     *
     * @param hasPublicTransfer the has public transfer
     */
    public void setHasPublicTransfer(boolean hasPublicTransfer) {
      this.hasPublicTransfer = hasPublicTransfer;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveObject)) {
        return false;
      }
      MoveObject that = (MoveObject) o;
      return hasPublicTransfer == that.hasPublicTransfer
          && type.equals(that.type)
          && fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
      return Objects.hash(type, fields, hasPublicTransfer);
    }

    @Override
    public String toString() {
      return "MoveObject{"
          + "type='"
          + type
          + '\''
          + ", fields="
          + fields
          + ", hasPublicTransfer="
          + hasPublicTransfer
          + '}';
    }
  }

  /** The type Package object. */
  public static class PackageObject extends SuiParsedData {

    private Map<String, String> disassembled;

    /**
     * Gets disassembled.
     *
     * @return the disassembled
     */
    public Map<String, String> getDisassembled() {
      return disassembled;
    }

    /**
     * Sets disassembled.
     *
     * @param disassembled the disassembled
     */
    public void setDisassembled(Map<String, String> disassembled) {
      this.disassembled = disassembled;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PackageObject)) {
        return false;
      }
      PackageObject that = (PackageObject) o;
      return disassembled.equals(that.disassembled);
    }

    @Override
    public int hashCode() {
      return Objects.hash(disassembled);
    }

    @Override
    public String toString() {
      return "PackageObject{" + "disassembled=" + disassembled + '}';
    }
  }
}
