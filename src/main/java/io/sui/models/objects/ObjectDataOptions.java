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

package io.sui.models.objects;


import java.util.Objects;

/**
 * The type Object data options.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class ObjectDataOptions {

  private boolean showType = true;

  private boolean showContent;

  private boolean showBcs;

  private boolean showOwner;

  private boolean showPreviousTransaction;

  private boolean showStorageRebate;

  private boolean showDisplay;

  /**
   * Is show type boolean.
   *
   * @return the boolean
   */
  public boolean isShowType() {
    return showType;
  }

  /**
   * Sets show type.
   *
   * @param showType the show type
   */
  public void setShowType(boolean showType) {
    this.showType = showType;
  }

  /**
   * Is show content boolean.
   *
   * @return the boolean
   */
  public boolean isShowContent() {
    return showContent;
  }

  /**
   * Sets show content.
   *
   * @param showContent the show content
   */
  public void setShowContent(boolean showContent) {
    this.showContent = showContent;
  }

  /**
   * Is show bcs boolean.
   *
   * @return the boolean
   */
  public boolean isShowBcs() {
    return showBcs;
  }

  /**
   * Sets show bcs.
   *
   * @param showBcs the show bcs
   */
  public void setShowBcs(boolean showBcs) {
    this.showBcs = showBcs;
  }

  /**
   * Is show owner boolean.
   *
   * @return the boolean
   */
  public boolean isShowOwner() {
    return showOwner;
  }

  /**
   * Sets show owner.
   *
   * @param showOwner the show owner
   */
  public void setShowOwner(boolean showOwner) {
    this.showOwner = showOwner;
  }

  /**
   * Is show previous transaction boolean.
   *
   * @return the boolean
   */
  public boolean isShowPreviousTransaction() {
    return showPreviousTransaction;
  }

  /**
   * Sets show previous transaction.
   *
   * @param showPreviousTransaction the show previous transaction
   */
  public void setShowPreviousTransaction(boolean showPreviousTransaction) {
    this.showPreviousTransaction = showPreviousTransaction;
  }

  /**
   * Is show storage rebate boolean.
   *
   * @return the boolean
   */
  public boolean isShowStorageRebate() {
    return showStorageRebate;
  }

  /**
   * Sets show storage rebate.
   *
   * @param showStorageRebate the show storage rebate
   */
  public void setShowStorageRebate(boolean showStorageRebate) {
    this.showStorageRebate = showStorageRebate;
  }

  /**
   * Is show display boolean.
   *
   * @return the boolean
   */
  public boolean isShowDisplay() {
    return showDisplay;
  }

  /**
   * Sets show display.
   *
   * @param showDisplay the show display
   */
  public void setShowDisplay(boolean showDisplay) {
    this.showDisplay = showDisplay;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ObjectDataOptions)) {
      return false;
    }
    ObjectDataOptions that = (ObjectDataOptions) o;
    return showType == that.showType
        && showContent == that.showContent
        && showBcs == that.showBcs
        && showOwner == that.showOwner
        && showPreviousTransaction == that.showPreviousTransaction
        && showStorageRebate == that.showStorageRebate
        && showDisplay == that.showDisplay;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        showType,
        showContent,
        showBcs,
        showOwner,
        showPreviousTransaction,
        showStorageRebate,
        showDisplay);
  }

  @Override
  public String toString() {
    return "ObjectDataOptions{"
        + "showType="
        + showType
        + ", showContent="
        + showContent
        + ", showBcs="
        + showBcs
        + ", showOwner="
        + showOwner
        + ", showPreviousTransaction="
        + showPreviousTransaction
        + ", showStorageRebate="
        + showStorageRebate
        + ", showDisplay="
        + showDisplay
        + '}';
  }
}
