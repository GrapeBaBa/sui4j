/*
 * Copyright 2022-2023 281165273grape@gmail.com
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

package io.sui.models.events;


import java.util.List;
import java.util.Objects;

/**
 * The type Paginated events.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class PaginatedEvents {

  private List<SuiEvent> data;

  private EventId nextCursor;

  private boolean hasNextPage;

  /**
   * Gets data.
   *
   * @return the data
   */
  public List<SuiEvent> getData() {
    return data;
  }

  /**
   * Sets data.
   *
   * @param data the data
   */
  public void setData(List<SuiEvent> data) {
    this.data = data;
  }

  /**
   * Gets next cursor.
   *
   * @return the next cursor
   */
  public EventId getNextCursor() {
    return nextCursor;
  }

  /**
   * Sets next cursor.
   *
   * @param nextCursor the next cursor
   */
  public void setNextCursor(EventId nextCursor) {
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
    if (!(o instanceof PaginatedEvents)) {
      return false;
    }
    PaginatedEvents that = (PaginatedEvents) o;
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
    return "PaginatedEvents{"
        + "data="
        + data
        + ", nextCursor="
        + nextCursor
        + ", hasNextPage="
        + hasNextPage
        + '}';
  }
}
