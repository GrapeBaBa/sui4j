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

package io.sui.models;


import java.util.List;
import java.util.Objects;

/**
 * The type Faucet response.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class FaucetResponse {

  private List<FaucetCoinInfo> transferredGasObjects;

  private String error;

  /**
   * Gets transferred gas objects.
   *
   * @return the transferred gas objects
   */
  public List<FaucetCoinInfo> getTransferredGasObjects() {
    return transferredGasObjects;
  }

  /**
   * Sets transferred gas objects.
   *
   * @param transferredGasObjects the transferred gas objects
   */
  public void setTransferredGasObjects(List<FaucetCoinInfo> transferredGasObjects) {
    this.transferredGasObjects = transferredGasObjects;
  }

  /**
   * Gets error.
   *
   * @return the error
   */
  public String getError() {
    return error;
  }

  /**
   * Sets error.
   *
   * @param error the error
   */
  public void setError(String error) {
    this.error = error;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FaucetResponse)) {
      return false;
    }
    FaucetResponse that = (FaucetResponse) o;
    return transferredGasObjects.equals(that.transferredGasObjects) && error.equals(that.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transferredGasObjects, error);
  }

  @Override
  public String toString() {
    return "FaucetResponse{"
        + "transferredGasObjects="
        + transferredGasObjects
        + ", error='"
        + error
        + '\''
        + '}';
  }
}
