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

package io.sui.models.objects;


import io.sui.models.transactions.GasCostSummary;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * The checkpoint summary.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
@SuppressWarnings({"checkstyle:MemberName", "checkstyle:ParameterName"})
public class Checkpoint {

  private BigInteger epoch;

  private BigInteger sequenceNumber;

  private BigInteger networkTotalTransactions;

  private String digest;

  private String previousDigest;

  private GasCostSummary epochRollingGasCostSummary;

  private EndOfEpochData endOfEpochData;

  private BigInteger timestampMs;

  private String validatorSignature;

  private List<String> transactions;

  private List<?> checkpointCommitments;

  public BigInteger getEpoch() {
    return epoch;
  }

  public void setEpoch(BigInteger epoch) {
    this.epoch = epoch;
  }

  public BigInteger getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(BigInteger sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public BigInteger getNetworkTotalTransactions() {
    return networkTotalTransactions;
  }

  public void setNetworkTotalTransactions(BigInteger networkTotalTransactions) {
    this.networkTotalTransactions = networkTotalTransactions;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public String getPreviousDigest() {
    return previousDigest;
  }

  public void setPreviousDigest(String previousDigest) {
    this.previousDigest = previousDigest;
  }

  public GasCostSummary getEpochRollingGasCostSummary() {
    return epochRollingGasCostSummary;
  }

  public void setEpochRollingGasCostSummary(GasCostSummary epochRollingGasCostSummary) {
    this.epochRollingGasCostSummary = epochRollingGasCostSummary;
  }

  public EndOfEpochData getEndOfEpochData() {
    return endOfEpochData;
  }

  public void setEndOfEpochData(EndOfEpochData endOfEpochData) {
    this.endOfEpochData = endOfEpochData;
  }

  public BigInteger getTimestampMs() {
    return timestampMs;
  }

  public void setTimestampMs(BigInteger timestampMs) {
    this.timestampMs = timestampMs;
  }

  public String getValidatorSignature() {
    return validatorSignature;
  }

  public void setValidatorSignature(String validatorSignature) {
    this.validatorSignature = validatorSignature;
  }

  public List<String> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<String> transactions) {
    this.transactions = transactions;
  }

  public List<?> getCheckpointCommitments() {
    return checkpointCommitments;
  }

  public void setCheckpointCommitments(List<?> checkpointCommitments) {
    this.checkpointCommitments = checkpointCommitments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Checkpoint)) {
      return false;
    }
    Checkpoint that = (Checkpoint) o;
    return Objects.equals(epoch, that.epoch)
        && Objects.equals(sequenceNumber, that.sequenceNumber)
        && Objects.equals(networkTotalTransactions, that.networkTotalTransactions)
        && Objects.equals(digest, that.digest)
        && Objects.equals(previousDigest, that.previousDigest)
        && Objects.equals(epochRollingGasCostSummary, that.epochRollingGasCostSummary)
        && Objects.equals(endOfEpochData, that.endOfEpochData)
        && Objects.equals(timestampMs, that.timestampMs)
        && Objects.equals(validatorSignature, that.validatorSignature)
        && Objects.equals(transactions, that.transactions)
        && Objects.equals(checkpointCommitments, that.checkpointCommitments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        epoch,
        sequenceNumber,
        networkTotalTransactions,
        digest,
        previousDigest,
        epochRollingGasCostSummary,
        endOfEpochData,
        timestampMs,
        validatorSignature,
        transactions,
        checkpointCommitments);
  }

  @Override
  public String toString() {
    return "Checkpoint{"
        + "epoch="
        + epoch
        + ", sequenceNumber="
        + sequenceNumber
        + ", networkTotalTransactions="
        + networkTotalTransactions
        + ", digest='"
        + digest
        + '\''
        + ", previousDigest='"
        + previousDigest
        + '\''
        + ", epochRollingGasCostSummary="
        + epochRollingGasCostSummary
        + ", endOfEpochData="
        + endOfEpochData
        + ", timestampMs="
        + timestampMs
        + ", validatorSignature='"
        + validatorSignature
        + '\''
        + ", transactions="
        + transactions
        + ", checkpointCommitments="
        + checkpointCommitments
        + '}';
  }
}
