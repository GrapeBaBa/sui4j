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

package io.sui.models.transactions;


import io.sui.models.objects.SuiObjectData;
import java.util.List;
import java.util.Objects;

/**
 * The type Parsed split coin response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class ParsedSplitCoinResponse {

  private SuiObjectData updatedCoin;

  private List<SuiObjectData> newCoins;

  private SuiObjectData updatedGas;

  /**
   * Gets updated coin.
   *
   * @return the updated coin
   */
  public SuiObjectData getUpdatedCoin() {
    return updatedCoin;
  }

  /**
   * Sets updated coin.
   *
   * @param updatedCoin the updated coin
   */
  public void setUpdatedCoin(SuiObjectData updatedCoin) {
    this.updatedCoin = updatedCoin;
  }

  /**
   * Gets new coins.
   *
   * @return the new coins
   */
  public List<SuiObjectData> getNewCoins() {
    return newCoins;
  }

  /**
   * Sets new coins.
   *
   * @param newCoins the new coins
   */
  public void setNewCoins(List<SuiObjectData> newCoins) {
    this.newCoins = newCoins;
  }

  /**
   * Gets updated gas.
   *
   * @return the updated gas
   */
  public SuiObjectData getUpdatedGas() {
    return updatedGas;
  }

  /**
   * Sets updated gas.
   *
   * @param updatedGas the updated gas
   */
  public void setUpdatedGas(SuiObjectData updatedGas) {
    this.updatedGas = updatedGas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParsedSplitCoinResponse)) {
      return false;
    }
    ParsedSplitCoinResponse that = (ParsedSplitCoinResponse) o;
    return updatedCoin.equals(that.updatedCoin)
        && newCoins.equals(that.newCoins)
        && updatedGas.equals(that.updatedGas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updatedCoin, newCoins, updatedGas);
  }

  @Override
  public String toString() {
    return "ParsedSplitCoinResponse{"
        + "updatedCoin="
        + updatedCoin
        + ", newCoins="
        + newCoins
        + ", updatedGas="
        + updatedGas
        + '}';
  }
}
