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

package io.sui.clients;

/**
 * The type Pay recipients and amounts mismatch exception.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class PayRecipientsAndAmountsMismatchException extends RuntimeException {

  /**
   * Instantiates a new Pay recipients and amounts mismatch exception.
   *
   * @param recipientsSize the recipients size
   * @param amountsSize the amounts size
   */
  public PayRecipientsAndAmountsMismatchException(int recipientsSize, int amountsSize) {
    super(
        String.format(
            "recipients and amounts mismatch. Got %d recipients but %d amounts",
            recipientsSize, amountsSize));
  }
}
