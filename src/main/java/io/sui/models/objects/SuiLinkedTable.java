package io.sui.models.objects;

import com.google.common.base.Objects;

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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiLinkedTable<?> that = (SuiLinkedTable<?>) o;
    return Objects.equal(id, that.id) && Objects.equal(size, that.size) && Objects.equal(head, that.head) && Objects.equal(tail, that.tail);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, size, head, tail);
  }

  @Override
  public String toString() {
    return "SuiLinkedTable{" +
        "id='" + id + '\'' +
        ", size=" + size +
        ", head=" + head +
        ", tail=" + tail +
        '}';
  }
}
