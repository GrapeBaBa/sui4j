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
 * The type PaginatedCheckpoint.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class PaginatedCheckpoint {

  private List<Checkpoint> data;

  private String nextCursor;

  private boolean hasNextPage;

  /**
   * Gets data.
   *
   * @return the data
   */
  public List<Checkpoint> getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  public void setData(List<Checkpoint> data) {
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

  /**
   * Is has next page boolean.
   *
   * @return the boolean
   */
  public boolean isHasNextPage() {
    return hasNextPage;
  }

  /**
   * Sets has next page.
   *
   * @param hasNextPage the has next page
   */
  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaginatedCheckpoint)) {
      return false;
    }
    PaginatedCheckpoint that = (PaginatedCheckpoint) o;
    return hasNextPage == that.hasNextPage
        && Objects.equals(data, that.data)
        && Objects.equals(nextCursor, that.nextCursor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, nextCursor, hasNextPage);
  }

  @Override
  public String toString() {
    return "PaginatedCheckpoint{"
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
