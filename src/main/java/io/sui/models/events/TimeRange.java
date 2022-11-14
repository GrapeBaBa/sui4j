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

package io.sui.models.events;


import java.util.Objects;

/**
 * The type Time range.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TimeRange {

  @SuppressWarnings("checkstyle:MemberName")
  private Long start_time;

  @SuppressWarnings("checkstyle:MemberName")
  private Long end_time;

  /**
   * Gets start time.
   *
   * @return the start time
   */
  public Long getStart_time() {
    return start_time;
  }

  /**
   * Sets start time.
   *
   * @param start_time the start time
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setStart_time(Long start_time) {
    this.start_time = start_time;
  }

  /**
   * Gets end time.
   *
   * @return the end time
   */
  public Long getEnd_time() {
    return end_time;
  }

  /**
   * Sets end time.
   *
   * @param end_time the end time
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setEnd_time(Long end_time) {
    this.end_time = end_time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TimeRange)) {
      return false;
    }
    TimeRange timeRange = (TimeRange) o;
    return start_time.equals(timeRange.start_time) && end_time.equals(timeRange.end_time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start_time, end_time);
  }

  @Override
  public String toString() {
    return "TimeRange{" + "start_time=" + start_time + ", end_time=" + end_time + '}';
  }
}
