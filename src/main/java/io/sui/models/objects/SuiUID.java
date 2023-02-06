package io.sui.models.objects;

import com.google.common.base.Objects;

public class SuiUID {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiUID suiUID = (SuiUID) o;
    return Objects.equal(id, suiUID.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "SuiUID{" +
        "id='" + id + '\'' +
        '}';
  }
}
