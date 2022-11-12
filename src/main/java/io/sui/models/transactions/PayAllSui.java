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


import io.sui.models.objects.SuiObjectRef;
import java.util.List;
import java.util.Objects;

/**
 * The type Pay all sui.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class PayAllSui {

  private List<SuiObjectRef> coins;

  private String recipient;

  /**
   * Gets coins.
   *
   * @return the coins
   */
  public List<SuiObjectRef> getCoins() {
    return coins;
  }

  /**
   * Sets coins.
   *
   * @param coins the coins
   */
  public void setCoins(List<SuiObjectRef> coins) {
    this.coins = coins;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PayAllSui)) {
      return false;
    }
    PayAllSui payAllSui = (PayAllSui) o;
    return coins.equals(payAllSui.coins) && recipient.equals(payAllSui.recipient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coins, recipient);
  }

  @Override
  public String toString() {
    return "PayAllSui{" + "coins=" + coins + ", recipient='" + recipient + '\'' + '}';
  }
}
