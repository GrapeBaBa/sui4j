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


import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * The type EndOfEpochData.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class EndOfEpochData {

  private List<List<String>> nextEpochCommittee;

  private BigInteger nextEpochProtocolVersion;

  private List<?> epochCommitments;

  /**
   * Gets next epoch committee.
   *
   * @return the next epoch committee
   */
  public List<List<String>> getNextEpochCommittee() {
    return nextEpochCommittee;
  }

  /**
   * Sets next epoch committee.
   *
   * @param nextEpochCommittee the next epoch committee
   */
  public void setNextEpochCommittee(List<List<String>> nextEpochCommittee) {
    this.nextEpochCommittee = nextEpochCommittee;
  }

  /**
   * Gets next epoch protocol version.
   *
   * @return the next epoch protocol version
   */
  public BigInteger getNextEpochProtocolVersion() {
    return nextEpochProtocolVersion;
  }

  /**
   * Sets next epoch protocol version.
   *
   * @param nextEpochProtocolVersion the next epoch protocol version
   */
  public void setNextEpochProtocolVersion(BigInteger nextEpochProtocolVersion) {
    this.nextEpochProtocolVersion = nextEpochProtocolVersion;
  }

  /**
   * Gets epoch commitments.
   *
   * @return the epoch commitments
   */
  public List<?> getEpochCommitments() {
    return epochCommitments;
  }

  /**
   * Sets epoch commitments.
   *
   * @param epochCommitments the epoch commitments
   */
  public void setEpochCommitments(List<?> epochCommitments) {
    this.epochCommitments = epochCommitments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EndOfEpochData)) {
      return false;
    }
    EndOfEpochData that = (EndOfEpochData) o;
    return Objects.equals(nextEpochCommittee, that.nextEpochCommittee)
        && Objects.equals(nextEpochProtocolVersion, that.nextEpochProtocolVersion)
        && Objects.equals(epochCommitments, that.epochCommitments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nextEpochCommittee, nextEpochProtocolVersion, epochCommitments);
  }

  @Override
  public String toString() {
    return "EndOfEpochData{"
        + "nextEpochCommittee="
        + nextEpochCommittee
        + ", nextEpochProtocolVersion="
        + nextEpochProtocolVersion
        + ", epochCommitments="
        + epochCommitments
        + '}';
  }
}
