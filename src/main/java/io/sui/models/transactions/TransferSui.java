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


import java.util.Objects;

/**
 * The type Transfer sui.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class TransferSui {

  private String recipient;

  private Long amount;

  /**
   * Gets recipient.
   *
   * @return the recipient
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * Sets recipient.
   *
   * @param recipient the recipient
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public Long getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amount the amount
   */
  public void setAmount(Long amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransferSui)) {
      return false;
    }
    TransferSui that = (TransferSui) o;
    return recipient.equals(that.recipient) && amount.equals(that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipient, amount);
  }

  @Override
  public String toString() {
    return "TransferSui{" + "recipient='" + recipient + '\'' + ", amount=" + amount + '}';
  }
}
