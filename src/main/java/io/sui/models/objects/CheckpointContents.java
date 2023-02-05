package io.sui.models.objects;

import com.google.common.base.Objects;
import io.sui.models.transactions.ExecutionDigests;
import io.sui.models.transactions.PaginatedTransactionDigests;

import java.util.List;

/**
 * the contents of a checkpoint
 */
public class CheckpointContents {

  private List<ExecutionDigests> transactions;

  private List<String> userSignatures;

  public List<ExecutionDigests> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<ExecutionDigests> transactions) {
    this.transactions = transactions;
  }

  public List<String> getUserSignatures() {
    return userSignatures;
  }

  public void setUserSignatures(List<String> userSignatures) {
    this.userSignatures = userSignatures;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CheckpointContents that = (CheckpointContents) o;
    return Objects.equal(transactions, that.transactions) && Objects.equal(userSignatures, that.userSignatures);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(transactions, userSignatures);
  }

  @Override
  public String toString() {
    return "CheckpointContents{" +
        "transactions=" + transactions +
        ", userSignatures=" + userSignatures +
        '}';
  }
}
