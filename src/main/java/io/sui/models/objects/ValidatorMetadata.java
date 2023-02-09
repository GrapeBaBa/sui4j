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


import java.util.Arrays;
import java.util.Objects;

/**
 * The validator metadata.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
@SuppressWarnings({"checkstyle:MemberName", "checkstyle:ParameterName"})
public class ValidatorMetadata {

  private String sui_address;

  private byte[] pubkey_bytes;

  private byte[] network_pubkey_bytes;

  private byte[] worker_pubkey_bytes;

  private byte[] proof_of_possession_bytes;

  private byte[] name;

  private byte[] description;

  private byte[] image_url;

  private byte[] project_url;

  private byte[] net_address;

  private byte[] consensus_address;

  private byte[] worker_address;

  private Long next_epoch_stake;

  private Long next_epoch_delegation;

  private Long next_epoch_gas_price;

  private Long next_epoch_commission_rate;

  public String getSui_address() {
    return sui_address;
  }

  public void setSui_address(String sui_address) {
    this.sui_address = sui_address;
  }

  public byte[] getPubkey_bytes() {
    return pubkey_bytes;
  }

  public void setPubkey_bytes(byte[] pubkey_bytes) {
    this.pubkey_bytes = pubkey_bytes;
  }

  public byte[] getNetwork_pubkey_bytes() {
    return network_pubkey_bytes;
  }

  public void setNetwork_pubkey_bytes(byte[] network_pubkey_bytes) {
    this.network_pubkey_bytes = network_pubkey_bytes;
  }

  public byte[] getWorker_pubkey_bytes() {
    return worker_pubkey_bytes;
  }

  public void setWorker_pubkey_bytes(byte[] worker_pubkey_bytes) {
    this.worker_pubkey_bytes = worker_pubkey_bytes;
  }

  public byte[] getProof_of_possession_bytes() {
    return proof_of_possession_bytes;
  }

  public void setProof_of_possession_bytes(byte[] proof_of_possession_bytes) {
    this.proof_of_possession_bytes = proof_of_possession_bytes;
  }

  public byte[] getName() {
    return name;
  }

  public void setName(byte[] name) {
    this.name = name;
  }

  public byte[] getDescription() {
    return description;
  }

  public void setDescription(byte[] description) {
    this.description = description;
  }

  public byte[] getImage_url() {
    return image_url;
  }

  public void setImage_url(byte[] image_url) {
    this.image_url = image_url;
  }

  public byte[] getProject_url() {
    return project_url;
  }

  public void setProject_url(byte[] project_url) {
    this.project_url = project_url;
  }

  public byte[] getNet_address() {
    return net_address;
  }

  public void setNet_address(byte[] net_address) {
    this.net_address = net_address;
  }

  public byte[] getConsensus_address() {
    return consensus_address;
  }

  public void setConsensus_address(byte[] consensus_address) {
    this.consensus_address = consensus_address;
  }

  public byte[] getWorker_address() {
    return worker_address;
  }

  public void setWorker_address(byte[] worker_address) {
    this.worker_address = worker_address;
  }

  public Long getNext_epoch_stake() {
    return next_epoch_stake;
  }

  public void setNext_epoch_stake(Long next_epoch_stake) {
    this.next_epoch_stake = next_epoch_stake;
  }

  public Long getNext_epoch_delegation() {
    return next_epoch_delegation;
  }

  public void setNext_epoch_delegation(Long next_epoch_delegation) {
    this.next_epoch_delegation = next_epoch_delegation;
  }

  public Long getNext_epoch_gas_price() {
    return next_epoch_gas_price;
  }

  public void setNext_epoch_gas_price(Long next_epoch_gas_price) {
    this.next_epoch_gas_price = next_epoch_gas_price;
  }

  public Long getNext_epoch_commission_rate() {
    return next_epoch_commission_rate;
  }

  public void setNext_epoch_commission_rate(Long next_epoch_commission_rate) {
    this.next_epoch_commission_rate = next_epoch_commission_rate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidatorMetadata that = (ValidatorMetadata) o;
    return sui_address.equals(that.sui_address)
        && Arrays.equals(pubkey_bytes, that.pubkey_bytes)
        && Arrays.equals(network_pubkey_bytes, that.network_pubkey_bytes)
        && Arrays.equals(worker_pubkey_bytes, that.worker_pubkey_bytes)
        && Arrays.equals(proof_of_possession_bytes, that.proof_of_possession_bytes)
        && Arrays.equals(name, that.name)
        && Arrays.equals(description, that.description)
        && Arrays.equals(image_url, that.image_url)
        && Arrays.equals(project_url, that.project_url)
        && Arrays.equals(net_address, that.net_address)
        && Arrays.equals(consensus_address, that.consensus_address)
        && Arrays.equals(worker_address, that.worker_address)
        && next_epoch_stake.equals(that.next_epoch_stake)
        && next_epoch_delegation.equals(that.next_epoch_delegation)
        && next_epoch_gas_price.equals(that.next_epoch_gas_price)
        && next_epoch_commission_rate.equals(that.next_epoch_commission_rate);
  }

  @Override
  public int hashCode() {
    int result =
        Objects.hash(
            sui_address,
            next_epoch_stake,
            next_epoch_delegation,
            next_epoch_gas_price,
            next_epoch_commission_rate);
    result = 31 * result + Arrays.hashCode(pubkey_bytes);
    result = 31 * result + Arrays.hashCode(network_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(worker_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(proof_of_possession_bytes);
    result = 31 * result + Arrays.hashCode(name);
    result = 31 * result + Arrays.hashCode(description);
    result = 31 * result + Arrays.hashCode(image_url);
    result = 31 * result + Arrays.hashCode(project_url);
    result = 31 * result + Arrays.hashCode(net_address);
    result = 31 * result + Arrays.hashCode(consensus_address);
    result = 31 * result + Arrays.hashCode(worker_address);
    return result;
  }

  @Override
  public String toString() {
    return "ValidatorMetadata{"
        + "sui_address='"
        + sui_address
        + '\''
        + ", pubkey_bytes="
        + Arrays.toString(pubkey_bytes)
        + ", network_pubkey_bytes="
        + Arrays.toString(network_pubkey_bytes)
        + ", worker_pubkey_bytes="
        + Arrays.toString(worker_pubkey_bytes)
        + ", proof_of_possession_bytes="
        + Arrays.toString(proof_of_possession_bytes)
        + ", name="
        + Arrays.toString(name)
        + ", description="
        + Arrays.toString(description)
        + ", image_url="
        + Arrays.toString(image_url)
        + ", project_url="
        + Arrays.toString(project_url)
        + ", net_address="
        + Arrays.toString(net_address)
        + ", consensus_address="
        + Arrays.toString(consensus_address)
        + ", worker_address="
        + Arrays.toString(worker_address)
        + ", next_epoch_stake="
        + next_epoch_stake
        + ", next_epoch_delegation="
        + next_epoch_delegation
        + ", next_epoch_gas_price="
        + next_epoch_gas_price
        + ", next_epoch_commission_rate="
        + next_epoch_commission_rate
        + '}';
  }
}
