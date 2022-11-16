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


import java.util.Objects;

/**
 * The type Move struct type parameter.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveStructTypeParameter {

  private MoveAbilitySet constraints;

  @SuppressWarnings("checkstyle:MemberName")
  private boolean is_phantom;

  /**
   * Gets constraints.
   *
   * @return the constraints
   */
  public MoveAbilitySet getConstraints() {
    return constraints;
  }

  /**
   * Sets constraints.
   *
   * @param constraints the constraints
   */
  public void setConstraints(MoveAbilitySet constraints) {
    this.constraints = constraints;
  }

  /**
   * Is is phantom boolean.
   *
   * @return the boolean
   */
  public boolean isIs_phantom() {
    return is_phantom;
  }

  /**
   * Sets is phantom.
   *
   * @param is_phantom the is phantom
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setIs_phantom(boolean is_phantom) {
    this.is_phantom = is_phantom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveStructTypeParameter)) {
      return false;
    }
    MoveStructTypeParameter that = (MoveStructTypeParameter) o;
    return is_phantom == that.is_phantom && constraints.equals(that.constraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(constraints, is_phantom);
  }

  @Override
  public String toString() {
    return "MoveStructTypeParameter{"
        + "constraints="
        + constraints
        + ", is_phantom="
        + is_phantom
        + '}';
  }
}
