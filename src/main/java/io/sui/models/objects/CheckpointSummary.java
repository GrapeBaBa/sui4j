package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;
import io.sui.models.transactions.GasCostSummary;

import java.util.List;

public class CheckpointSummary {

  private Long epoch;

  @SerializedName(value = "sequenceNumber", alternate = {"sequence_number"})
  private Long sequenceNumber;

  @SerializedName(value = "networkTotalTransactions", alternate = {"network_total_transactions"})
  private Long networkTotalTransactions;

  @SerializedName(value = "contentDigest", alternate = {"content_digest"})
  private String contentDigest;

  @SerializedName(value = "previousDigest", alternate = {"previous_digest"})
  private String previousDigest;

  @SerializedName(value = "epochRollingGasCostSummary", alternate = {"epoch_rolling_gas_cost_summary"})
  private GasCostSummary epochRollingGasCostSummary;

  @SerializedName(value = "nextEpochCommittee", alternate = {"next_epoch_committee"})
  private List<String[]> nextEpochCommittee;

  @SerializedName(value = "timestampMs", alternate = {"timestamp_ms"})
  private Long timestampMs;

  private Long id;

  public Long getEpoch() {
    return epoch;
  }

  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  public Long getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(Long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public Long getNetworkTotalTransactions() {
    return networkTotalTransactions;
  }

  public void setNetworkTotalTransactions(Long networkTotalTransactions) {
    this.networkTotalTransactions = networkTotalTransactions;
  }

  public String getContentDigest() {
    return contentDigest;
  }

  public void setContentDigest(String contentDigest) {
    this.contentDigest = contentDigest;
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

  public List<String[]> getNextEpochCommittee() {
    return nextEpochCommittee;
  }

  public void setNextEpochCommittee(List<String[]> nextEpochCommittee) {
    this.nextEpochCommittee = nextEpochCommittee;
  }

  public Long getTimestampMs() {
    return timestampMs;
  }

  public void setTimestampMs(Long timestampMs) {
    this.timestampMs = timestampMs;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CheckpointSummary that = (CheckpointSummary) o;
    return Objects.equal(epoch, that.epoch) && Objects.equal(sequenceNumber, that.sequenceNumber) && Objects.equal(networkTotalTransactions, that.networkTotalTransactions) && Objects.equal(contentDigest, that.contentDigest) && Objects.equal(previousDigest, that.previousDigest) && Objects.equal(epochRollingGasCostSummary, that.epochRollingGasCostSummary) && Objects.equal(nextEpochCommittee, that.nextEpochCommittee) && Objects.equal(timestampMs, that.timestampMs) && Objects.equal(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(epoch, sequenceNumber, networkTotalTransactions, contentDigest, previousDigest, epochRollingGasCostSummary, nextEpochCommittee, timestampMs, id);
  }

  @Override
  public String toString() {
    return "CheckpointSummary{" +
        "epoch=" + epoch +
        ", sequenceNumber=" + sequenceNumber +
        ", networkTotalTransactions=" + networkTotalTransactions +
        ", contentDigest='" + contentDigest + '\'' +
        ", previousDigest='" + previousDigest + '\'' +
        ", epochRollingGasCostSummary=" + epochRollingGasCostSummary +
        ", nextEpochCommittee=" + nextEpochCommittee +
        ", timestampMs=" + timestampMs +
        ", id=" + id +
        '}';
  }
}
