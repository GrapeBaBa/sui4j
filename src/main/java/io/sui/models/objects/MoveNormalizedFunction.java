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
 * The type Move normalized function.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveNormalizedFunction {

  private MoveVisibility visibility;

  @SuppressWarnings("checkstyle:MemberName")
  private boolean is_entry;

  @SuppressWarnings("checkstyle:MemberName")
  private List<MoveAbilitySet> type_parameters;

  private List<MoveNormalizedType> parameters;

  @SuppressWarnings("checkstyle:MemberName")
  private List<MoveNormalizedType> return_;

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
  public boolean isIs_entry() {
    return is_entry;
  }

  /**
   * Sets is entry.
   *
   * @param is_entry the is entry
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setIs_entry(boolean is_entry) {
    this.is_entry = is_entry;
  }

  /**
   * Gets type parameters.
   *
   * @return the type parameters
   */
  public List<MoveAbilitySet> getType_parameters() {
    return type_parameters;
  }

  /**
   * Sets type parameters.
   *
   * @param type_parameters the type parameters
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setType_parameters(List<MoveAbilitySet> type_parameters) {
    this.type_parameters = type_parameters;
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
  public List<MoveNormalizedType> getReturn_() {
    return return_;
  }

  /**
   * Sets return.
   *
   * @param return_ the return
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setReturn_(List<MoveNormalizedType> return_) {
    this.return_ = return_;
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
    return is_entry == that.is_entry
        && visibility == that.visibility
        && type_parameters.equals(that.type_parameters)
        && parameters.equals(that.parameters)
        && return_.equals(that.return_);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visibility, is_entry, type_parameters, parameters, return_);
  }

  @Override
  public String toString() {
    return "MoveNormalizedFunction{"
        + "visibility="
        + visibility
        + ", is_entry="
        + is_entry
        + ", type_parameters="
        + type_parameters
        + ", parameters="
        + parameters
        + ", return_="
        + return_
        + '}';
  }
}
