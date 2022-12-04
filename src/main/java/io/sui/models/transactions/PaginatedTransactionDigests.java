/*
 * Copyright 2022 281165273grape@gmail.com
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

package io.sui.models.transactions;


import java.util.List;
import java.util.Objects;

/**
 * The type Paginated transaction digests.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class PaginatedTransactionDigests {

  private List<String> data;

  private String nextCursor;

  /**
   * Gets data.
   *
   * @return the data
   */
  public List<String> getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  public void setData(List<String> data) {
    this.data = data;
  }

  /**
   * Gets next cursor.
   *
   * @return the next cursor
   */
  public String getNextCursor() {
    return nextCursor;
  }

  /**
   * Sets next cursor.
   *
   * @param nextCursor the next cursor
   */
  public void setNextCursor(String nextCursor) {
    this.nextCursor = nextCursor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaginatedTransactionDigests)) {
      return false;
    }
    PaginatedTransactionDigests that = (PaginatedTransactionDigests) o;
    return data.equals(that.data) && nextCursor.equals(that.nextCursor);
  }

  @Override
  public String toString() {
    return "PaginatedTransactionDigests{"
        + "data="
        + data
        + ", nextCursor='"
        + nextCursor
        + '\''
        + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, nextCursor);
  }
}
