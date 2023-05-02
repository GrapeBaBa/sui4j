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

package io.sui.models.coin;


import java.util.Objects;

/**
 * The type Coin metadata.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class CoinMetadata {

  private byte decimals;

  private String description;

  private String iconUrl;

  private String id;

  private String name;

  private String symbol;

  /**
   * Gets decimals.
   *
   * @return the decimals
   */
  public byte getDecimals() {
    return decimals;
  }

  /**
   * Sets decimals.
   *
   * @param decimals the decimals
   */
  public void setDecimals(byte decimals) {
    this.decimals = decimals;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets icon url.
   *
   * @return the icon url
   */
  public String getIconUrl() {
    return iconUrl;
  }

  /**
   * Sets icon url.
   *
   * @param iconUrl the icon url
   */
  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets symbol.
   *
   * @return the symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets symbol.
   *
   * @param symbol the symbol
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CoinMetadata)) {
      return false;
    }
    CoinMetadata that = (CoinMetadata) o;
    return decimals == that.decimals
        && description.equals(that.description)
        && iconUrl.equals(that.iconUrl)
        && id.equals(that.id)
        && name.equals(that.name)
        && symbol.equals(that.symbol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(decimals, description, iconUrl, id, name, symbol);
  }

  @Override
  public String toString() {
    return "CoinMetadata{"
        + "decimals="
        + decimals
        + ", description='"
        + description
        + '\''
        + ", iconUrl='"
        + iconUrl
        + '\''
        + ", id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", symbol='"
        + symbol
        + '\''
        + '}';
  }
}
