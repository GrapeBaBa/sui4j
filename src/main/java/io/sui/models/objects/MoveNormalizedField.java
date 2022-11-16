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
 * The type Move normalized field.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class MoveNormalizedField {

  private String name;

  @SuppressWarnings("checkstyle:MemberName")
  private MoveNormalizedType type_;

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
   * Gets type.
   *
   * @return the type
   */
  public MoveNormalizedType getType_() {
    return type_;
  }

  /**
   * Sets type.
   *
   * @param type_ the type
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setType_(MoveNormalizedType type_) {
    this.type_ = type_;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MoveNormalizedField)) {
      return false;
    }
    MoveNormalizedField that = (MoveNormalizedField) o;
    return name.equals(that.name) && type_.equals(that.type_);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type_);
  }

  @Override
  public String toString() {
    return "MoveNormalizedField{" + "name='" + name + '\'' + ", type_=" + type_ + '}';
  }
}
