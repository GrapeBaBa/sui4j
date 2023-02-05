package io.sui.models.transactions;

import com.google.common.base.Objects;

/**
 * the execution digests
 */
public class ExecutionDigests {

  /**
   * the TransactionDigest value
   */
  private String transaction;

  /**
   * the TransactionEffectsDigest value
   */
  private String effects;

  public String getTransaction() {
    return transaction;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public String getEffects() {
    return effects;
  }

  public void setEffects(String effects) {
    this.effects = effects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExecutionDigests that = (ExecutionDigests) o;
    return Objects.equal(transaction, that.transaction) && Objects.equal(effects, that.effects);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(transaction, effects);
  }

  @Override
  public String toString() {
    return "ExecutionDigests{" +
        "transaction='" + transaction + '\'' +
        ", effects='" + effects + '\'' +
        '}';
  }
}
