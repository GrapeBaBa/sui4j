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
 * The type Pay.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class Pay {

  private List<SuiObjectRef> coins;

  private List<String> recipients;

  private List<Long> amounts;

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
   * Gets recipients.
   *
   * @return the recipients
   */
  public List<String> getRecipients() {
    return recipients;
  }

  /**
   * Sets recipients.
   *
   * @param recipients the recipients
   */
  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  /**
   * Gets amounts.
   *
   * @return the amounts
   */
  public List<Long> getAmounts() {
    return amounts;
  }

  /**
   * Sets amounts.
   *
   * @param amounts the amounts
   */
  public void setAmounts(List<Long> amounts) {
    this.amounts = amounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pay)) {
      return false;
    }
    Pay pay = (Pay) o;
    return coins.equals(pay.coins)
        && recipients.equals(pay.recipients)
        && amounts.equals(pay.amounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coins, recipients, amounts);
  }

  @Override
  public String toString() {
    return "Pay{" + "coins=" + coins + ", recipients=" + recipients + ", amounts=" + amounts + '}';
  }
}
