package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

public class SuiStakeSubsidy {

  @SerializedName(value = "epochCounter", alternate = "epoch_counter")
  private Long epochCounter;
  private SuiBalance balance;
  @SerializedName(value = "currentEpochAmount", alternate = "current_epoch_amount")
  private Long currentEpochAmount;

  public Long getEpochCounter() {
    return epochCounter;
  }

  public void setEpochCounter(Long epochCounter) {
    this.epochCounter = epochCounter;
  }

  public SuiBalance getBalance() {
    return balance;
  }

  public void setBalance(SuiBalance balance) {
    this.balance = balance;
  }

  public Long getCurrentEpochAmount() {
    return currentEpochAmount;
  }

  public void setCurrentEpochAmount(Long currentEpochAmount) {
    this.currentEpochAmount = currentEpochAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuiStakeSubsidy that = (SuiStakeSubsidy) o;
    return Objects.equal(epochCounter, that.epochCounter) && Objects.equal(balance, that.balance) && Objects.equal(currentEpochAmount, that.currentEpochAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(epochCounter, balance, currentEpochAmount);
  }

  @Override
  public String toString() {
    return "SuiStakeSubsidy{" +
        "epochCounter=" + epochCounter +
        ", balance=" + balance +
        ", currentEpochAmount=" + currentEpochAmount +
        '}';
  }
}
