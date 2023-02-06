package io.sui.models.objects;

import com.google.common.base.Objects;

import java.util.List;

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
    return "MoveOption{" +
        "vec=" + vec +
        '}';
  }
}
