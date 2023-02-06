package io.sui.models.objects;

import com.google.common.base.Objects;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SuiVecMap<K, V> {

  private List<LinkedHashMap<K,V>> contents;

  public List<LinkedHashMap<K, V>> getContents() {
    return contents;
  }

  public void setContents(List<LinkedHashMap<K, V>> contents) {
    this.contents = contents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiVecMap<?, ?> suiVecMap = (SuiVecMap<?, ?>) o;
    return Objects.equal(contents, suiVecMap.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(contents);
  }

  @Override
  public String toString() {
    return "SuiVecMap{" +
        "contents=" + contents +
        '}';
  }
}
