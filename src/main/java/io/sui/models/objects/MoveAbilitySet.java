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
 * The type Move ability set.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveAbilitySet {

  private List<String> abilities;

  /**
   * Gets abilities.
   *
   * @return the abilities
   */
  public List<String> getAbilities() {
    return abilities;
  }

  /**
   * Sets abilities.
   *
   * @param abilities the abilities
   */
  public void setAbilities(List<String> abilities) {
    this.abilities = abilities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveAbilitySet)) {
      return false;
    }
    MoveAbilitySet that = (MoveAbilitySet) o;
    return abilities.equals(that.abilities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abilities);
  }

  @Override
  public String toString() {
    return "MoveAbilitySet{" + "abilities=" + abilities + '}';
  }
}
