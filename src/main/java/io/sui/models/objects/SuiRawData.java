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


import java.util.Map;
import java.util.Objects;

/**
 * The interface Sui data.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class SuiData {

  /**
   * The type Package object.
   */
  public static class PackageObject extends SuiData {

    private String id;

    private String dataType;

    private Map<String, String> moduleMap;

    private Long version;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getDataType() {
      return dataType;
    }

    public void setDataType(String dataType) {
      this.dataType = dataType;
    }

    public Map<String, String> getModuleMap() {
      return moduleMap;
    }

    public void setModuleMap(Map<String, String> moduleMap) {
      this.moduleMap = moduleMap;
    }

    public Long getVersion() {
      return version;
    }

    public void setVersion(Long version) {
      this.version = version;
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
      return id.equals(that.id) && dataType.equals(that.dataType) && moduleMap.equals(
          that.moduleMap)
          && version.equals(that.version);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, dataType, moduleMap, version);
    }

    @Override
    public String toString() {
      return "PackageObject{" +
          "id='" + id + '\'' +
          ", dataType='" + dataType + '\'' +
          ", moduleMap=" + moduleMap +
          ", version=" + version +
          '}';
    }
  }

  /**
   * The type Move object.
   */
  public static class MoveObject extends SuiData {

    private String dataType;

    private boolean hasPublicTransfer;

    private String type;

    private Long version;

    private String bcsBytes;

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
    @SuppressWarnings("checkstyle:ParameterName")
    public void setHasPublicTransfer(boolean hasPublicTransfer) {
      this.hasPublicTransfer = hasPublicTransfer;
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

    public String getBcsBytes() {
      return bcsBytes;
    }

    @SuppressWarnings("checkstyle:ParameterName")
    public void setBcsBytes(String bcsBytes) {
      this.bcsBytes = bcsBytes;
    }

    public Long getVersion() {
      return version;
    }

    public void setVersion(Long version) {
      this.version = version;
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
      return hasPublicTransfer == that.hasPublicTransfer && dataType.equals(that.dataType)
          && type.equals(that.type) && version.equals(that.version) && bcsBytes.equals(
          that.bcsBytes);
    }

    @Override
    public int hashCode() {
      return Objects.hash(dataType, hasPublicTransfer, type, version, bcsBytes);
    }

    @Override
    public String toString() {
      return "MoveObject{" +
          "dataType='" + dataType + '\'' +
          ", hasPublicTransfer=" + hasPublicTransfer +
          ", type='" + type + '\'' +
          ", version=" + version +
          ", bcsBytes='" + bcsBytes + '\'' +
          '}';
    }
  }
}
