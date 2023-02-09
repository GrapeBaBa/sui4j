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


import com.google.common.base.Objects;

/**
 * The move table type.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class SuiTableVec {

  private Table contents;

  public Table getContents() {
    return contents;
  }

  public void setContents(Table contents) {
    this.contents = contents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuiTableVec that = (SuiTableVec) o;
    return Objects.equal(contents, that.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(contents);
  }

  @Override
  public String toString() {
    return "SuiTableVec{" + "contents=" + contents + '}';
  }
}
