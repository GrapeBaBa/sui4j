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


import java.util.Objects;

/**
 * The type Move function.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveFunction {

  private String suiPackage;

  private String module;

  private String function;

  /**
   * Gets sui package.
   *
   * @return the sui package
   */
  public String getSuiPackage() {
    return suiPackage;
  }

  /**
   * Sets sui package.
   *
   * @param suiPackage the sui package
   */
  public void setSuiPackage(String suiPackage) {
    this.suiPackage = suiPackage;
  }

  /**
   * Gets module.
   *
   * @return the module
   */
  public String getModule() {
    return module;
  }

  /**
   * Sets module.
   *
   * @param module the module
   */
  public void setModule(String module) {
    this.module = module;
  }

  /**
   * Gets function.
   *
   * @return the function
   */
  public String getFunction() {
    return function;
  }

  /**
   * Sets function.
   *
   * @param function the function
   */
  public void setFunction(String function) {
    this.function = function;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveFunction)) {
      return false;
    }
    MoveFunction that = (MoveFunction) o;
    return suiPackage.equals(that.suiPackage)
        && module.equals(that.module)
        && function.equals(that.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(suiPackage, module, function);
  }

  @Override
  public String toString() {
    return "MoveFunction{"
        + "suiPackage='"
        + suiPackage
        + '\''
        + ", module='"
        + module
        + '\''
        + ", function='"
        + function
        + '\''
        + '}';
  }
}
