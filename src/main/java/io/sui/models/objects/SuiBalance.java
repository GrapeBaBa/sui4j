package io.sui.models.objects;

import com.google.common.base.Objects;

public class SuiBalance {

  private Long value;

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiBalance that = (SuiBalance) o;
    return Objects.equal(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public String toString() {
    return "SuiBalance{" +
        "value=" + value +
        '}';
  }
}
