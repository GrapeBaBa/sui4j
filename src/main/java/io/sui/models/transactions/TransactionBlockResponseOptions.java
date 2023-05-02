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


import java.util.Objects;

/**
 * The type Transaction response options.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class TransactionBlockResponseOptions {

  private boolean showInput;

  private boolean showEffects;

  private boolean showEvents;

  private boolean showObjectChanges;

  private boolean showBalanceChanges;

  /**
   * Is show input boolean.
   *
   * @return the boolean
   */
  public boolean isShowInput() {
    return showInput;
  }

  /**
   * Sets show input.
   *
   * @param showInput the show input
   */
  public void setShowInput(boolean showInput) {
    this.showInput = showInput;
  }

  /**
   * Is show effects boolean.
   *
   * @return the boolean
   */
  public boolean isShowEffects() {
    return showEffects;
  }

  /**
   * Sets show effects.
   *
   * @param showEffects the show effects
   */
  public void setShowEffects(boolean showEffects) {
    this.showEffects = showEffects;
  }

  /**
   * Is show events boolean.
   *
   * @return the boolean
   */
  public boolean isShowEvents() {
    return showEvents;
  }

  /**
   * Sets show events.
   *
   * @param showEvents the show events
   */
  public void setShowEvents(boolean showEvents) {
    this.showEvents = showEvents;
  }

  /**
   * Is show object changes boolean.
   *
   * @return the boolean
   */
  public boolean isShowObjectChanges() {
    return showObjectChanges;
  }

  /**
   * Sets show object changes.
   *
   * @param showObjectChanges the show object changes
   */
  public void setShowObjectChanges(boolean showObjectChanges) {
    this.showObjectChanges = showObjectChanges;
  }

  /**
   * Is show balance changes boolean.
   *
   * @return the boolean
   */
  public boolean isShowBalanceChanges() {
    return showBalanceChanges;
  }

  /**
   * Sets show balance changes.
   *
   * @param showBalanceChanges the show balance changes
   */
  public void setShowBalanceChanges(boolean showBalanceChanges) {
    this.showBalanceChanges = showBalanceChanges;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionBlockResponseOptions)) {
      return false;
    }
    TransactionBlockResponseOptions that = (TransactionBlockResponseOptions) o;
    return showInput == that.showInput
        && showEffects == that.showEffects
        && showEvents == that.showEvents
        && showObjectChanges == that.showObjectChanges
        && showBalanceChanges == that.showBalanceChanges;
  }

  @Override
  public int hashCode() {
    return Objects.hash(showInput, showEffects, showEvents, showObjectChanges, showBalanceChanges);
  }

  @Override
  public String toString() {
    return "TransactionBlockResponseOptions{"
        + "showInput="
        + showInput
        + ", showEffects="
        + showEffects
        + ", showEvents="
        + showEvents
        + ", showObjectChanges="
        + showObjectChanges
        + ", showBalanceChanges="
        + showBalanceChanges
        + '}';
  }
}
