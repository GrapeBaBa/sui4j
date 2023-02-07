package io.sui.models.objects;

import com.google.common.base.Objects;
import io.sui.bcsgen.ObjectID;

import java.util.List;

public class PaginatedCoins {

  private List<Coin> data;

  private String objectID;

  public List<Coin> getData() {
    return data;
  }

  public void setData(List<Coin> data) {
    this.data = data;
  }

  public String getObjectID() {
    return objectID;
  }

  public void setObjectID(String objectID) {
    this.objectID = objectID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaginatedCoins that = (PaginatedCoins) o;
    return Objects.equal(data, that.data) && Objects.equal(objectID, that.objectID);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data, objectID);
  }

  @Override
  public String toString() {
    return "PaginatedCoins{" +
        "data=" + data +
        ", objectID=" + objectID +
        '}';
  }
}
