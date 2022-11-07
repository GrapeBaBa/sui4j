/*
 * Copyright 281165273grape@gmail.com
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


import java.util.Map;
import java.util.Objects;


/**
 * The interface Sui data.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface SuiData {

  /**
   * The type Package object.
   */
  class PackageObject implements SuiData {

    private String dataType;

    private Map<String, ?> disassembled;

    /**
     * Gets disassembled.
     *
     * @return the disassembled
     */
    public Map<String, ?> getDisassembled() {
      return disassembled;
    }

    /**
     * Sets disassembled.
     *
     * @param disassembled the disassembled
     */
    public void setDisassembled(Map<String, ?> disassembled) {
      this.disassembled = disassembled;
    }

    /**
     * Gets data type.
     *
     * @return the data type
     */
    public String getDataType() {
      return dataType;
    }

    /**
     * Sets data type.
     *
     * @param dataType the data type
     */
    public void setDataType(String dataType) {
      this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      PackageObject that = (PackageObject) o;
      return dataType.equals(that.dataType) && disassembled.equals(that.disassembled);
    }

    @Override
    public int hashCode() {
      return Objects.hash(dataType, disassembled);
    }

    @Override
    public String toString() {
      return "PackageObject{"
          + "dataType='" + dataType + '\''
          + ", disassembled=" + disassembled
          + '}';
    }
  }

  /**
   * The type Move object.
   */
  class MoveObject implements SuiData {

    private String dataType;

    @SuppressWarnings("checkstyle:MemberName")
    private boolean has_public_transfer;

    private String type;

    private Map<String, ?> fields;

    /**
     * Is has public transfer boolean.
     *
     * @return the boolean
     */
    public boolean isHas_public_transfer() {
      return has_public_transfer;
    }

    /**
     * Sets has public transfer.
     *
     * @param has_public_transfer the has public transfer
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setHas_public_transfer(boolean has_public_transfer) {
      this.has_public_transfer = has_public_transfer;
    }

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
     * Gets data type.
     *
     * @return the data type
     */
    public String getDataType() {
      return dataType;
    }

    /**
     * Sets data type.
     *
     * @param dataType the data type
     */
    public void setDataType(String dataType) {
      this.dataType = dataType;
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

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      MoveObject that = (MoveObject) o;
      return has_public_transfer == that.has_public_transfer && dataType.equals(that.dataType)
          && type.equals(that.type) && fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
      return Objects.hash(dataType, has_public_transfer, type, fields);
    }

    @Override
    public String toString() {
      return "MoveObject{"
          + "dataType='" + dataType + '\''
          + ", has_public_transfer=" + has_public_transfer
          + ", type='" + type + '\''
          + ", fields=" + fields
          + '}';
    }
  }

}
