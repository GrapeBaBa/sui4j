package io.sui.models.objects;

import com.google.common.base.Objects;

public class Table {

  private String id;

  private Long size;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Table table = (Table) o;
    return Objects.equal(id, table.id) && Objects.equal(size, table.size);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, size);
  }

  @Override
  public String toString() {
    return "Table{" +
        "id='" + id + '\'' +
        ", size=" + size +
        '}';
  }
}
