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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The type Consensus commit prologue.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class ConsensusCommitPrologue {

  private Long epoch;

  private Long round;

  @SuppressWarnings("checkstyle:MemberName")
  private Long commit_timestamp_ms;

  /**
   * Gets epoch.
   *
   * @return the epoch
   */
  public long getEpoch() {
    return epoch;
  }

  /**
   * Sets epoch.
   *
   * @param epoch the epoch
   */
  public void setEpoch(long epoch) {
    this.epoch = epoch;
  }

  /**
   * Gets round.
   *
   * @return the round
   */
  public long getRound() {
    return round;
  }

  /**
   * Sets round.
   *
   * @param round the round
   */
  public void setRound(long round) {
    this.round = round;
  }

  /**
   * Gets commit timestamp ms.
   *
   * @return the commit timestamp ms
   */
  public long getCommit_timestamp_ms() {
    return commit_timestamp_ms;
  }

  /**
   * Sets commit timestamp ms.
   *
   * @param commit_timestamp_ms the commit timestamp ms
   */
  @SuppressWarnings("checkstyle:ParameterName")
  public void setCommit_timestamp_ms(long commit_timestamp_ms) {
    this.commit_timestamp_ms = commit_timestamp_ms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConsensusCommitPrologue)) {
      return false;
    }
    ConsensusCommitPrologue that = (ConsensusCommitPrologue) o;
    return epoch.equals(that.epoch)
        && round.equals(that.round)
        && commit_timestamp_ms.equals(that.commit_timestamp_ms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch, round, commit_timestamp_ms);
  }

  @Override
  public String toString() {
    return "ConsensusCommitPrologue{"
        + "epoch="
        + epoch
        + ", round="
        + round
        + ", commit_timestamp_ms="
        + commit_timestamp_ms
        + '}';
  }
}
