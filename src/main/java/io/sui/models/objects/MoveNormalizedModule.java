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
import java.util.Map;
import java.util.Objects;

/**
 * The type Move normalized module.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveNormalizedModule {

  private Integer fileFormatVersion;

  private String address;

  private String name;

  private List<MoveModuleId> friends;

  private Map<String, MoveNormalizedStruct> structs;

  private Map<String, MoveNormalizedFunction> exposedFunctions;

  /**
   * Gets file format version.
   *
   * @return the file format version
   */
  public Integer getFileFormatVersion() {
    return fileFormatVersion;
  }

  /**
   * Sets file format version.
   *
   * @param fileFormatVersion the file format version
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setFileFormatVersion(Integer fileFormatVersion) {
    this.fileFormatVersion = fileFormatVersion;
  }

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
   * Gets friends.
   *
   * @return the friends
   */
  public List<MoveModuleId> getFriends() {
    return friends;
  }

  /**
   * Sets friends.
   *
   * @param friends the friends
   */
  public void setFriends(List<MoveModuleId> friends) {
    this.friends = friends;
  }

  /**
   * Gets structs.
   *
   * @return the structs
   */
  public Map<String, MoveNormalizedStruct> getStructs() {
    return structs;
  }

  /**
   * Sets structs.
   *
   * @param structs the structs
   */
  public void setStructs(Map<String, MoveNormalizedStruct> structs) {
    this.structs = structs;
  }

  /**
   * Gets exposed functions.
   *
   * @return the exposed functions
   */
  public Map<String, MoveNormalizedFunction> getExposedFunctions() {
    return exposedFunctions;
  }

  /**
   * Sets exposed functions.
   *
   * @param exposedFunctions the exposed functions
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setExposedFunctions(Map<String, MoveNormalizedFunction> exposedFunctions) {
    this.exposedFunctions = exposedFunctions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveNormalizedModule)) {
      return false;
    }
    MoveNormalizedModule that = (MoveNormalizedModule) o;
    return fileFormatVersion.equals(that.fileFormatVersion)
        && address.equals(that.address)
        && name.equals(that.name)
        && friends.equals(that.friends)
        && structs.equals(that.structs)
        && exposedFunctions.equals(that.exposedFunctions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileFormatVersion, address, name, friends, structs, exposedFunctions);
  }

  @Override
  public String toString() {
    return "MoveNormalizedModule{"
        + "file_format_version="
        + fileFormatVersion
        + ", address='"
        + address
        + '\''
        + ", name='"
        + name
        + '\''
        + ", friends="
        + friends
        + ", structs="
        + structs
        + ", exposed_functions="
        + exposedFunctions
        + '}';
  }
}
