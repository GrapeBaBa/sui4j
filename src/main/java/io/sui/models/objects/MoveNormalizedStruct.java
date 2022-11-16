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


import java.util.List;
import java.util.Objects;

/**
 * The type Move normalized struct.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveNormalizedStruct {

  private MoveAbilitySet abilities;

  @SuppressWarnings("checkstyle:MemberName")
  private List<MoveStructTypeParameter> type_parameters;

  private List<MoveNormalizedField> fields;

  /**
   * Gets abilities.
   *
   * @return the abilities
   */
  public MoveAbilitySet getAbilities() {
    return abilities;
  }

  /**
   * Sets abilities.
   *
   * @param abilities the abilities
   */
  public void setAbilities(MoveAbilitySet abilities) {
    this.abilities = abilities;
  }

  /**
   * Gets type parameters.
   *
   * @return the type parameters
   */
  public List<MoveStructTypeParameter> getType_parameters() {
    return type_parameters;
  }

  /**
   * Sets type parameters.
   *
   * @param type_parameters the type parameters
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setType_parameters(List<MoveStructTypeParameter> type_parameters) {
    this.type_parameters = type_parameters;
  }

  /**
   * Gets fields.
   *
   * @return the fields
   */
  public List<MoveNormalizedField> getFields() {
    return fields;
  }

  /**
   * Sets fields.
   *
   * @param fields the fields
   */
  public void setFields(List<MoveNormalizedField> fields) {
    this.fields = fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveNormalizedStruct)) {
      return false;
    }
    MoveNormalizedStruct that = (MoveNormalizedStruct) o;
    return abilities.equals(that.abilities)
        && type_parameters.equals(that.type_parameters)
        && fields.equals(that.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abilities, type_parameters, fields);
  }

  @Override
  public String toString() {
    return "MoveNormalizedStruct{"
        + "abilities="
        + abilities
        + ", type_parameters="
        + type_parameters
        + ", fields="
        + fields
        + '}';
  }
}
