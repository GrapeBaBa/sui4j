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
 * The move linked table type, only use it in sui system state.
 *
 * @param <T> the parameter type
 * @author thinkAfCod
 * @since 2023.2
 */
public class SuiLinkedTable<T> {

  private String id;

  private Long size;

  private MoveOption<T> head;

  private MoveOption<T> tail;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public MoveOption<T> getHead() {
    return head;
  }

  public void setHead(MoveOption<T> head) {
    this.head = head;
  }

  public MoveOption<T> getTail() {
    return tail;
  }

  public void setTail(MoveOption<T> tail) {
    this.tail = tail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuiLinkedTable<?> that = (SuiLinkedTable<?>) o;
    return Objects.equal(id, that.id)
        && Objects.equal(size, that.size)
        && Objects.equal(head, that.head)
        && Objects.equal(tail, that.tail);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, size, head, tail);
  }

  @Override
  public String toString() {
    return "SuiLinkedTable{"
        + "id='"
        + id
        + '\''
        + ", size="
        + size
        + ", head="
        + head
        + ", tail="
        + tail
        + '}';
  }
}
