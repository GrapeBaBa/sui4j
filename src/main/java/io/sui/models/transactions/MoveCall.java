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


import java.util.List;
import java.util.Objects;

/**
 * The type Move call.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveCall {

  private String suiPackage;

  private String module;

  private String function;

  private List<String> typeArguments;

  private List<?> arguments;

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

  /**
   * Gets type arguments.
   *
   * @return the type arguments
   */
  public List<String> getTypeArguments() {
    return typeArguments;
  }

  /**
   * Sets type arguments.
   *
   * @param typeArguments the type arguments
   */
  public void setTypeArguments(List<String> typeArguments) {
    this.typeArguments = typeArguments;
  }

  /**
   * Gets arguments.
   *
   * @return the arguments
   */
  public List<?> getArguments() {
    return arguments;
  }

  /**
   * Sets arguments.
   *
   * @param arguments the arguments
   */
  public void setArguments(List<?> arguments) {
    this.arguments = arguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveCall)) {
      return false;
    }
    MoveCall moveCall = (MoveCall) o;
    return suiPackage.equals(moveCall.suiPackage)
        && module.equals(moveCall.module)
        && function.equals(moveCall.function)
        && typeArguments.equals(moveCall.typeArguments)
        && arguments.equals(moveCall.arguments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(suiPackage, module, function, typeArguments, arguments);
  }

  @Override
  public String toString() {
    return "MoveCall{"
        + "suiPackage="
        + suiPackage
        + ", module='"
        + module
        + '\''
        + ", function='"
        + function
        + '\''
        + ", typeArguments="
        + typeArguments
        + ", arguments="
        + arguments
        + '}';
  }
}
