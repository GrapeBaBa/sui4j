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

package io.sui.models.transactions;


import java.util.Map;
import java.util.Objects;

/**
 * The type Move package.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MovePackage {

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
    if (!(o instanceof MovePackage)) {
      return false;
    }
    MovePackage that = (MovePackage) o;
    return disassembled.equals(that.disassembled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(disassembled);
  }

  @Override
  public String toString() {
    return "MovePackage{" + "disassembled=" + disassembled + '}';
  }
}
