package io.sui.models.objects;

import com.google.common.base.Objects;

public class ValidatorPair {

  private String from;

  private String to;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ValidatorPair that = (ValidatorPair) o;
    return Objects.equal(from, that.from) && Objects.equal(to, that.to);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(from, to);
  }

  @Override
  public String toString() {
    return "ValidatorPair{" +
        "from='" + from + '\'' +
        ", to='" + to + '\'' +
        '}';
  }
}
