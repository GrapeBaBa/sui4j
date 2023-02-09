/*
 * Copyright 2023 281165273grape@gmail.com
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
import java.util.List;

/**
 * the move option type.
 *
 * @param <T> the parameter type
 * @author thinkAfCod
 * @since 2023.2
 */
public class MoveOption<T> {

  private List<T> vec;

  public List<T> getVec() {
    return vec;
  }

  public void setVec(List<T> vec) {
    this.vec = vec;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MoveOption<?> that = (MoveOption<?>) o;
    return Objects.equal(vec, that.vec);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(vec);
  }

  @Override
  public String toString() {
    return "MoveOption{" + "vec=" + vec + '}';
  }
}
