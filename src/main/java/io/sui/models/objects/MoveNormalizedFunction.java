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
 * The type Move normalized function.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveNormalizedFunction {

  private MoveVisibility visibility;

  private boolean isEntry;

  private List<MoveAbilitySet> typeParameters;

  private List<MoveNormalizedType> parameters;

  private List<MoveNormalizedType> returnType;

  /**
   * Gets visibility.
   *
   * @return the visibility
   */
  public MoveVisibility getVisibility() {
    return visibility;
  }

  /**
   * Sets visibility.
   *
   * @param visibility the visibility
   */
  public void setVisibility(MoveVisibility visibility) {
    this.visibility = visibility;
  }

  /**
   * Is is entry boolean.
   *
   * @return the boolean
   */
  public boolean isEntry() {
    return isEntry;
  }

  /**
   * Sets is entry.
   *
   * @param entry the is entry
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setEntry(boolean entry) {
    this.isEntry = entry;
  }

  /**
   * Gets type parameters.
   *
   * @return the type parameters
   */
  public List<MoveAbilitySet> getTypeParameters() {
    return typeParameters;
  }

  /**
   * Sets type parameters.
   *
   * @param typeParameters the type parameters
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setTypeParameters(List<MoveAbilitySet> typeParameters) {
    this.typeParameters = typeParameters;
  }

  /**
   * Gets parameters.
   *
   * @return the parameters
   */
  public List<MoveNormalizedType> getParameters() {
    return parameters;
  }

  /**
   * Sets parameters.
   *
   * @param parameters the parameters
   */
  public void setParameters(List<MoveNormalizedType> parameters) {
    this.parameters = parameters;
  }

  /**
   * Gets return.
   *
   * @return the return
   */
  public List<MoveNormalizedType> getReturnType() {
    return returnType;
  }

  /**
   * Sets return.
   *
   * @param returnType the return
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setReturnType(List<MoveNormalizedType> returnType) {
    this.returnType = returnType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveNormalizedFunction)) {
      return false;
    }
    MoveNormalizedFunction that = (MoveNormalizedFunction) o;
    return isEntry == that.isEntry
        && visibility == that.visibility
        && typeParameters.equals(that.typeParameters)
        && parameters.equals(that.parameters)
        && returnType.equals(that.returnType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visibility, isEntry, typeParameters, parameters, returnType);
  }

  @Override
  public String toString() {
    return "MoveNormalizedFunction{"
        + "visibility="
        + visibility
        + ", is_entry="
        + isEntry
        + ", type_parameters="
        + typeParameters
        + ", parameters="
        + parameters
        + ", returnType="
        + returnType
        + '}';
  }
}
