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

package io.sui.models.transactions;


import io.sui.models.objects.SuiObjectData;
import io.sui.models.objects.SuiObjectRef;
import java.util.List;
import java.util.Objects;

/**
 * The type Parsed publish response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ParsedPublishResponse {

  private List<SuiObjectData> createdObjects;

  private SuiObjectRef suiPackage;

  private SuiObjectData updatedGas;

  /**
   * Gets created objects.
   *
   * @return the created objects
   */
  public List<SuiObjectData> getCreatedObjects() {
    return createdObjects;
  }

  /**
   * Sets created objects.
   *
   * @param createdObjects the created objects
   */
  public void setCreatedObjects(List<SuiObjectData> createdObjects) {
    this.createdObjects = createdObjects;
  }

  /**
   * Gets sui package.
   *
   * @return the sui package
   */
  public SuiObjectRef getSuiPackage() {
    return suiPackage;
  }

  /**
   * Sets sui package.
   *
   * @param suiPackage the sui package
   */
  public void setSuiPackage(SuiObjectRef suiPackage) {
    this.suiPackage = suiPackage;
  }

  /**
   * Gets updated gas.
   *
   * @return the updated gas
   */
  public SuiObjectData getUpdatedGas() {
    return updatedGas;
  }

  /**
   * Sets updated gas.
   *
   * @param updatedGas the updated gas
   */
  public void setUpdatedGas(SuiObjectData updatedGas) {
    this.updatedGas = updatedGas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParsedPublishResponse)) {
      return false;
    }
    ParsedPublishResponse that = (ParsedPublishResponse) o;
    return createdObjects.equals(that.createdObjects)
        && suiPackage.equals(that.suiPackage)
        && updatedGas.equals(that.updatedGas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdObjects, suiPackage, updatedGas);
  }

  @Override
  public String toString() {
    return "ParsedPublishResponse{"
        + "createdObjects="
        + createdObjects
        + ", suiPackage="
        + suiPackage
        + ", updatedGas="
        + updatedGas
        + "} "
        + super.toString();
  }
}
