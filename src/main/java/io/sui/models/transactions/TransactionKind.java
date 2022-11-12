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
 * The type Transaction kind.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class TransactionKind {

  /** The type Transfer object transaction kind. */
  public static class TransferObjectTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private TransferObject TransferObject;

    /**
     * Gets transfer object.
     *
     * @return the transfer object
     */
    public TransferObject getTransferObject() {
      return TransferObject;
    }

    /**
     * Sets transfer object.
     *
     * @param transferObject the transfer object
     */
    public void setTransferObject(TransferObject transferObject) {
      TransferObject = transferObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjectTransactionKind)) {
        return false;
      }
      TransferObjectTransactionKind that = (TransferObjectTransactionKind) o;
      return TransferObject.equals(that.TransferObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TransferObject);
    }

    @Override
    public String toString() {
      return "TransferObjectTransactionKind{" + "TransferObject=" + TransferObject + '}';
    }
  }

  /** The type Publish transaction kind. */
  public static class PublishTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private MovePackage Publish;

    /**
     * Gets publish.
     *
     * @return the publish
     */
    public MovePackage getPublish() {
      return Publish;
    }

    /**
     * Sets publish.
     *
     * @param publish the publish
     */
    public void setPublish(MovePackage publish) {
      Publish = publish;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PublishTransactionKind)) {
        return false;
      }
      PublishTransactionKind that = (PublishTransactionKind) o;
      return Publish.equals(that.Publish);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Publish);
    }

    @Override
    public String toString() {
      return "PublishTransactionKind{" + "Publish=" + Publish + '}';
    }
  }

  /** The type Call transaction kind. */
  public static class CallTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveCall Call;

    /**
     * Gets call.
     *
     * @return the call
     */
    public MoveCall getCall() {
      return Call;
    }

    /**
     * Sets call.
     *
     * @param call the call
     */
    public void setCall(MoveCall call) {
      Call = call;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CallTransactionKind)) {
        return false;
      }
      CallTransactionKind that = (CallTransactionKind) o;
      return Call.equals(that.Call);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Call);
    }

    @Override
    public String toString() {
      return "CallTransactionKind{" + "Call=" + Call + '}';
    }
  }

  /** The type Transfer sui transaction kind. */
  public static class TransferSuiTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private TransferSui TransferSui;

    /**
     * Gets transfer sui.
     *
     * @return the transfer sui
     */
    public TransferSui getTransferSui() {
      return TransferSui;
    }

    /**
     * Sets transfer sui.
     *
     * @param transferSui the transfer sui
     */
    public void setTransferSui(TransferSui transferSui) {
      TransferSui = transferSui;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferSuiTransactionKind)) {
        return false;
      }
      TransferSuiTransactionKind that = (TransferSuiTransactionKind) o;
      return TransferSui.equals(that.TransferSui);
    }

    @Override
    public int hashCode() {
      return Objects.hash(TransferSui);
    }

    @Override
    public String toString() {
      return "TransferSuiTransactionKind{" + "TransferSui=" + TransferSui + '}';
    }
  }

  /** The type Pay transaction kind. */
  public static class PayTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private Pay Pay;

    /**
     * Gets pay.
     *
     * @return the pay
     */
    public Pay getPay() {
      return Pay;
    }

    /**
     * Sets pay.
     *
     * @param pay the pay
     */
    public void setPay(Pay pay) {
      Pay = pay;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PayTransactionKind)) {
        return false;
      }
      PayTransactionKind that = (PayTransactionKind) o;
      return Pay.equals(that.Pay);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Pay);
    }

    @Override
    public String toString() {
      return "PayTransactionKind{" + "Pay=" + Pay + '}';
    }
  }

  /** The type Pay sui transaction kind. */
  public static class PaySuiTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private Pay PaySui;

    /**
     * Gets pay sui.
     *
     * @return the pay sui
     */
    public Pay getPaySui() {
      return PaySui;
    }

    /**
     * Sets pay sui.
     *
     * @param paySui the pay sui
     */
    public void setPaySui(Pay paySui) {
      PaySui = paySui;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PaySuiTransactionKind)) {
        return false;
      }
      PaySuiTransactionKind that = (PaySuiTransactionKind) o;
      return PaySui.equals(that.PaySui);
    }

    @Override
    public int hashCode() {
      return Objects.hash(PaySui);
    }

    @Override
    public String toString() {
      return "PaySuiTransactionKind{" + "PaySui=" + PaySui + '}';
    }
  }

  /** The type Pay all sui transaction kind. */
  public static class PayAllSuiTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private PayAllSui PayAllSui;

    /**
     * Gets pay all sui.
     *
     * @return the pay all sui
     */
    public PayAllSui getPayAllSui() {
      return PayAllSui;
    }

    /**
     * Sets pay all sui.
     *
     * @param payAllSui the pay all sui
     */
    public void setPayAllSui(PayAllSui payAllSui) {
      PayAllSui = payAllSui;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PayAllSuiTransactionKind)) {
        return false;
      }
      PayAllSuiTransactionKind that = (PayAllSuiTransactionKind) o;
      return PayAllSui.equals(that.PayAllSui);
    }

    @Override
    public int hashCode() {
      return Objects.hash(PayAllSui);
    }

    @Override
    public String toString() {
      return "PayAllSuiTransactionKind{" + "PayAllSui=" + PayAllSui + '}';
    }
  }

  /** The type Change epoch transaction kind. */
  public static class ChangeEpochTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ChangeEpoch ChangeEpoch;

    /**
     * Gets change epoch.
     *
     * @return the change epoch
     */
    public ChangeEpoch getChangeEpoch() {
      return ChangeEpoch;
    }

    /**
     * Sets change epoch.
     *
     * @param changeEpoch the change epoch
     */
    public void setChangeEpoch(ChangeEpoch changeEpoch) {
      ChangeEpoch = changeEpoch;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ChangeEpochTransactionKind)) {
        return false;
      }
      ChangeEpochTransactionKind that = (ChangeEpochTransactionKind) o;
      return ChangeEpoch.equals(that.ChangeEpoch);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ChangeEpoch);
    }

    @Override
    public String toString() {
      return "ChangeEpochTransactionKind{" + "ChangeEpoch=" + ChangeEpoch + '}';
    }
  }
}
