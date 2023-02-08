/*
 * Copyright 2023 281165273grape@gmail.com
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
import java.util.List;

/**
 * the paginated coins.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginatedCoins that = (PaginatedCoins) o;
    return Objects.equal(data, that.data) && Objects.equal(objectID, that.objectID);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data, objectID);
  }

  @Override
  public String toString() {
    return "PaginatedCoins{" + "data=" + data + ", objectID=" + objectID + '}';
  }
}
