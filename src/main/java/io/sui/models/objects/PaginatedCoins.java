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


import java.util.List;
import java.util.Objects;

/**
 * the paginated coins.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class PaginatedCoins {

  private List<Coin> data;

  private String nextCursor;

  private boolean hasNextPage;

  public List<Coin> getData() {
    return data;
  }

  public void setData(List<Coin> data) {
    this.data = data;
  }

  public String getNextCursor() {
    return nextCursor;
  }

  public void getNextCursor(String nextCursor) {
    this.nextCursor = nextCursor;
  }

  public void setNextCursor(String nextCursor) {
    this.nextCursor = nextCursor;
  }

  public boolean isHasNextPage() {
    return hasNextPage;
  }

  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaginatedCoins)) {
      return false;
    }
    PaginatedCoins that = (PaginatedCoins) o;
    return hasNextPage == that.hasNextPage
        && data.equals(that.data)
        && nextCursor.equals(that.nextCursor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, nextCursor, hasNextPage);
  }

  @Override
  public String toString() {
    return "PaginatedCoins{"
        + "data="
        + data
        + ", nextCursor='"
        + nextCursor
        + '\''
        + ", hasNextPage="
        + hasNextPage
        + '}';
  }
}
