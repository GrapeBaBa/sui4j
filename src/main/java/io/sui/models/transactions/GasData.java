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

package io.sui.models.transactions;


import io.sui.models.objects.SuiObjectRef;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/** The type Gas data. */
public class GasData {

  private List<SuiObjectRef> payment;

  private String owner;

  private BigInteger price;

  private BigInteger budget;

  /**
   * Gets payment.
   *
   * @return the payment
   */
  public List<SuiObjectRef> getPayment() {
    return payment;
  }

  /**
   * Sets payment.
   *
   * @param payment the payment
   */
  public void setPayment(List<SuiObjectRef> payment) {
    this.payment = payment;
  }

  /**
   * Gets owner.
   *
   * @return the owner
   */
  public String getOwner() {
    return owner;
  }

  /**
   * Sets owner.
   *
   * @param owner the owner
   */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * Gets price.
   *
   * @return the price
   */
  public BigInteger getPrice() {
    return price;
  }

  /**
   * Sets price.
   *
   * @param price the price
   */
  public void setPrice(BigInteger price) {
    this.price = price;
  }

  /**
   * Gets budget.
   *
   * @return the budget
   */
  public BigInteger getBudget() {
    return budget;
  }

  /**
   * Sets budget.
   *
   * @param budget the budget
   */
  public void setBudget(BigInteger budget) {
    this.budget = budget;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GasData)) {
      return false;
    }
    GasData gasData = (GasData) o;
    return price == gasData.price
        && budget == gasData.budget
        && payment.equals(gasData.payment)
        && owner.equals(gasData.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payment, owner, price, budget);
  }

  @Override
  public String toString() {
    return "GasData{"
        + "payment="
        + payment
        + ", owner='"
        + owner
        + '\''
        + ", price="
        + price
        + ", budget="
        + budget
        + '}';
  }
}
