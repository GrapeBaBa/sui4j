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

package io.sui.models;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Authority quorum sign info.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class AuthorityQuorumSignInfo {

  private BigInteger epoch;

  private String signature;

  private byte[] signersMap;

  /**
   * Gets epoch.
   *
   * @return the epoch
   */
  public BigInteger getEpoch() {
    return epoch;
  }

  /**
   * Sets epoch.
   *
   * @param epoch the epoch
   */
  public void setEpoch(BigInteger epoch) {
    this.epoch = epoch;
  }

  /**
   * Gets signature.
   *
   * @return the signature
   */
  public String getSignature() {
    return signature;
  }

  /**
   * Sets signature.
   *
   * @param signature the signature
   */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /**
   * Get signers map byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getSignersMap() {
    return signersMap;
  }

  /**
   * Sets signers map.
   *
   * @param signersMap the signers map
   */
  public void setSignersMap(byte[] signersMap) {
    this.signersMap = signersMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorityQuorumSignInfo that = (AuthorityQuorumSignInfo) o;
    return epoch.equals(that.epoch)
        && signature.equals(that.signature)
        && Arrays.equals(signersMap, that.signersMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(epoch, signature, Arrays.hashCode(signersMap));
  }

  @Override
  public String toString() {
    return "AuthorityQuorumSignInfo{"
        + "epoch="
        + epoch
        + ", signature='"
        + signature
        + '\''
        + ", signersMap='"
        + Arrays.toString(signersMap)
        + '\''
        + '}';
  }
}
