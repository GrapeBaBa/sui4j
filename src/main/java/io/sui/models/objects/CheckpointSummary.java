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


import io.sui.models.transactions.GasCostSummary;
import java.util.List;
import java.util.Objects;

/**
 * the checkpoint summary.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class CheckpointSummary {

  private Long epoch;

  private Long sequence_number;

  private Long network_total_transactions;

  private String content_digest;

  private String previous_digest;

  private GasCostSummary epoch_rolling_gas_cost_summary;

  private List<String[]> next_epoch_committee;

  private Long timestamp_ms;

  public Long getEpoch() {
    return epoch;
  }

  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  public Long getSequence_number() {
    return sequence_number;
  }

  public void setSequence_number(Long sequence_number) {
    this.sequence_number = sequence_number;
  }

  public Long getNetwork_total_transactions() {
    return network_total_transactions;
  }

  public void setNetwork_total_transactions(Long network_total_transactions) {
    this.network_total_transactions = network_total_transactions;
  }

  public String getContent_digest() {
    return content_digest;
  }

  public void setContent_digest(String content_digest) {
    this.content_digest = content_digest;
  }

  public String getPrevious_digest() {
    return previous_digest;
  }

  public void setPrevious_digest(String previous_digest) {
    this.previous_digest = previous_digest;
  }

  public GasCostSummary getEpoch_rolling_gas_cost_summary() {
    return epoch_rolling_gas_cost_summary;
  }

  public void setEpoch_rolling_gas_cost_summary(GasCostSummary epoch_rolling_gas_cost_summary) {
    this.epoch_rolling_gas_cost_summary = epoch_rolling_gas_cost_summary;
  }

  public List<String[]> getNext_epoch_committee() {
    return next_epoch_committee;
  }

  public void setNext_epoch_committee(List<String[]> next_epoch_committee) {
    this.next_epoch_committee = next_epoch_committee;
  }

  public Long getTimestampMs() {
    return timestamp_ms;
  }

  public void setTimestampMs(Long timestamp_ms) {
    this.timestamp_ms = timestamp_ms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheckpointSummary that = (CheckpointSummary) o;
    return epoch.equals(that.epoch)
        && sequence_number.equals(that.sequence_number)
        && network_total_transactions.equals(that.network_total_transactions)
        && content_digest.equals(that.content_digest)
        && previous_digest.equals(that.previous_digest)
        && epoch_rolling_gas_cost_summary.equals(that.epoch_rolling_gas_cost_summary)
        && next_epoch_committee.equals(that.next_epoch_committee)
        && timestamp_ms.equals(that.timestamp_ms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        epoch,
        sequence_number,
        network_total_transactions,
        content_digest,
        previous_digest,
        epoch_rolling_gas_cost_summary,
        next_epoch_committee,
        timestamp_ms);
  }

  @Override
  public String toString() {
    return "CheckpointSummary{"
        + "epoch="
        + epoch
        + ", sequence_number="
        + sequence_number
        + ", network_total_transactions="
        + network_total_transactions
        + ", content_digest='"
        + content_digest
        + '\''
        + ", previous_digest='"
        + previous_digest
        + '\''
        + ", epoch_rolling_gas_cost_summary="
        + epoch_rolling_gas_cost_summary
        + ", next_epoch_committee="
        + next_epoch_committee
        + ", timestamp_ms="
        + timestamp_ms
        + '}';
  }
}
