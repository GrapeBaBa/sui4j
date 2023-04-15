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

package io.sui.clients;

/**
 * The type Gas budget less than gas price exception.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class GasBudgetLessThanGasPriceException extends RuntimeException {

  /**
   * Instantiates a new Gas budget less than gas price exception.
   *
   * @param gasBudget the gas budget
   * @param gasPrice the gas price
   */
  public GasBudgetLessThanGasPriceException(Long gasBudget, Long gasPrice) {
    super(
        String.format(
            "Gas budget %d is less than the reference gas price %d. The gas budget must be at least"
                + " the current reference gas price of %d.",
            gasBudget, gasPrice, gasPrice));
  }
}
