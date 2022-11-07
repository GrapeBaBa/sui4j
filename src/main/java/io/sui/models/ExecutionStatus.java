/*
 * Copyright 281165273grape@gmail.com
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

package io.sui.models;

import java.util.Objects;


/**
 * The type Execution status.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ExecutionStatus {

  /**
   * The enum Execution status type.
   */
  public enum ExecutionStatusType {
    /**
     * Success execution status type.
     */
    success,
    /**
     * Failure execution status type.
     */
    failure
  }

  private ExecutionStatusType status;

  private String error;

  /**
   * Gets status.
   *
   * @return the status
   */
  ExecutionStatusType getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  void setStatus(ExecutionStatusType status) {
    this.status = status;
  }

  /**
   * Gets error.
   *
   * @return the error
   */
  String getError() {
    return error;
  }

  /**
   * Sets error.
   *
   * @param error the error
   */
  void setError(String error) {
    this.error = error;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExecutionStatus that = (ExecutionStatus) o;
    return status == that.status && error.equals(that.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, error);
  }

  @Override
  public String toString() {
    return "ExecutionStatus{"
        + "status=" + status
        + ", error='" + error + '\''
        + '}';
  }


}
