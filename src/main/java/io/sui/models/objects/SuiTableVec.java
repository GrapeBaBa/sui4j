package io.sui.models.objects;

import com.google.common.base.Objects;

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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiTableVec that = (SuiTableVec) o;
    return Objects.equal(contents, that.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(contents);
  }

  @Override
  public String toString() {
    return "SuiTableVec{" +
        "contents=" + contents +
        '}';
  }
}
