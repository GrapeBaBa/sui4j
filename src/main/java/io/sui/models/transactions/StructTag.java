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


import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;

/**
 * The type Struct tag.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class StructTag {

  private String address;

  private String module;

  private String name;

  private List<TypeTag> typeParams = Lists.newArrayList();

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
   * Gets type params.
   *
   * @return the type params
   */
  public List<TypeTag> getTypeParams() {
    return typeParams;
  }

  /**
   * Sets type params.
   *
   * @param typeParams the type params
   */
  public void setTypeParams(List<TypeTag> typeParams) {
    this.typeParams = typeParams;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StructTag)) {
      return false;
    }
    StructTag structTag = (StructTag) o;
    return address.equals(structTag.address)
        && module.equals(structTag.module)
        && name.equals(structTag.name)
        && typeParams.equals(structTag.typeParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, module, name, typeParams);
  }

  @Override
  public String toString() {
    StringBuilder typeParamsBuilder = new StringBuilder();
    if (typeParams != null) {
      for (int i = 0; i < typeParams.size(); i++) {
        if (i == 0) {
          typeParamsBuilder.append("<");
          typeParamsBuilder.append(String.format("%s", typeParams.get(i)));
        } else {
          typeParamsBuilder.append(String.format(", %s", typeParams.get(i)));
          if (i == typeParams.size() - 1) {
            typeParamsBuilder.append(">");
          }
        }
      }
    }

    return String.format("%s::%s::%s%s", address, module, name, typeParamsBuilder);
  }
}
