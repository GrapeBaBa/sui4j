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

package io.sui.models.objects;


import java.util.List;
import java.util.Objects;

/**
 * The type Committee info response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class CommitteeInfoResponse {

  @SuppressWarnings("checkstyle:MemberName")
  private List<CommitteeInfo> committee_info;

  private Long epoch;

  public List<CommitteeInfo> getCommittee_info() {
    return committee_info;
  }

  @SuppressWarnings("checkstyle:ParameterName")
  public void setCommittee_info(List<CommitteeInfo> committee_info) {
    this.committee_info = committee_info;
  }

  /**
   * Gets epoch.
   *
   * @return the epoch
   */
  public Long getEpoch() {
    return epoch;
  }

  /**
   * Sets epoch.
   *
   * @param epoch the epoch
   */
  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CommitteeInfoResponse)) {
      return false;
    }
    CommitteeInfoResponse that = (CommitteeInfoResponse) o;
    return committee_info.equals(that.committee_info) && epoch.equals(that.epoch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(committee_info, epoch);
  }

  @Override
  public String toString() {
    return "CommitteeInfoResponse{" + "committee_info=" + committee_info + ", epoch=" + epoch + '}';
  }
}
