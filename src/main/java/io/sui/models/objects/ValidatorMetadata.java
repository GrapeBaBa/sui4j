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
 * @author grapebaba
 * @since 2023.2
 */
@SuppressWarnings({"checkstyle:MemberName", "checkstyle:ParameterName"})
public class ValidatorMetadata {

  private String sui_address;

  private byte[] pubkey_bytes;

  private byte[] network_pubkey_bytes;

  private byte[] worker_pubkey_bytes;

  private byte[] proof_of_possession_bytes;

  private String name;

  private String description;

  private String image_url;

  private String project_url;

  private byte[] net_address;

  private byte[] p2p_address;

  private byte[] primary_address;

  private byte[] worker_address;

  private byte[] next_epoch_protocol_pubkey_bytes;

  private byte[] next_epoch_proof_of_possession;

  private byte[] next_epoch_network_pubkey_bytes;

  private byte[] next_epoch_worker_pubkey_bytes;

  private byte[] next_epoch_net_address;

  private byte[] next_epoch_p2p_address;

  private byte[] next_epoch_primary_address;

  private byte[] next_epoch_worker_address;

  /**
   * Gets sui address.
   *
   * @return the sui address
   */
  public String getSui_address() {
    return sui_address;
  }

  /**
   * Sets sui address.
   *
   * @param sui_address the sui address
   */
  public void setSui_address(String sui_address) {
    this.sui_address = sui_address;
  }

  /**
   * Get pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getPubkey_bytes() {
    return pubkey_bytes;
  }

  /**
   * Sets pubkey bytes.
   *
   * @param pubkey_bytes the pubkey bytes
   */
  public void setPubkey_bytes(byte[] pubkey_bytes) {
    this.pubkey_bytes = pubkey_bytes;
  }

  /**
   * Get network pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNetwork_pubkey_bytes() {
    return network_pubkey_bytes;
  }

  /**
   * Sets network pubkey bytes.
   *
   * @param network_pubkey_bytes the network pubkey bytes
   */
  public void setNetwork_pubkey_bytes(byte[] network_pubkey_bytes) {
    this.network_pubkey_bytes = network_pubkey_bytes;
  }

  /**
   * Get worker pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getWorker_pubkey_bytes() {
    return worker_pubkey_bytes;
  }

  /**
   * Sets worker pubkey bytes.
   *
   * @param worker_pubkey_bytes the worker pubkey bytes
   */
  public void setWorker_pubkey_bytes(byte[] worker_pubkey_bytes) {
    this.worker_pubkey_bytes = worker_pubkey_bytes;
  }

  /**
   * Get proof of possession bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getProof_of_possession_bytes() {
    return proof_of_possession_bytes;
  }

  /**
   * Sets proof of possession bytes.
   *
   * @param proof_of_possession_bytes the proof of possession bytes
   */
  public void setProof_of_possession_bytes(byte[] proof_of_possession_bytes) {
    this.proof_of_possession_bytes = proof_of_possession_bytes;
  }

  /**
   * Get net address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNet_address() {
    return net_address;
  }

  /**
   * Sets net address.
   *
   * @param net_address the net address
   */
  public void setNet_address(byte[] net_address) {
    this.net_address = net_address;
  }

  /**
   * Get worker address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getWorker_address() {
    return worker_address;
  }

  /**
   * Sets worker address.
   *
   * @param worker_address the worker address
   */
  public void setWorker_address(byte[] worker_address) {
    this.worker_address = worker_address;
  }

  /**
   * Get p 2 p address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getP2p_address() {
    return p2p_address;
  }

  /**
   * Sets p 2 p address.
   *
   * @param p2p_address the p 2 p address
   */
  public void setP2p_address(byte[] p2p_address) {
    this.p2p_address = p2p_address;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets image url.
   *
   * @return the image url
   */
  public String getImage_url() {
    return image_url;
  }

  /**
   * Sets image url.
   *
   * @param image_url the image url
   */
  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  /**
   * Gets project url.
   *
   * @return the project url
   */
  public String getProject_url() {
    return project_url;
  }

  /**
   * Sets project url.
   *
   * @param project_url the project url
   */
  public void setProject_url(String project_url) {
    this.project_url = project_url;
  }

  /**
   * Get primary address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getPrimary_address() {
    return primary_address;
  }

  /**
   * Sets primary address.
   *
   * @param primary_address the primary address
   */
  public void setPrimary_address(byte[] primary_address) {
    this.primary_address = primary_address;
  }

  /**
   * Get next epoch protocol pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_protocol_pubkey_bytes() {
    return next_epoch_protocol_pubkey_bytes;
  }

  /**
   * Sets next epoch protocol pubkey bytes.
   *
   * @param next_epoch_protocol_pubkey_bytes the next epoch protocol pubkey bytes
   */
  public void setNext_epoch_protocol_pubkey_bytes(byte[] next_epoch_protocol_pubkey_bytes) {
    this.next_epoch_protocol_pubkey_bytes = next_epoch_protocol_pubkey_bytes;
  }

  /**
   * Get next epoch proof of possession byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_proof_of_possession() {
    return next_epoch_proof_of_possession;
  }

  /**
   * Sets next epoch proof of possession.
   *
   * @param next_epoch_proof_of_possession the next epoch proof of possession
   */
  public void setNext_epoch_proof_of_possession(byte[] next_epoch_proof_of_possession) {
    this.next_epoch_proof_of_possession = next_epoch_proof_of_possession;
  }

  /**
   * Get next epoch network pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_network_pubkey_bytes() {
    return next_epoch_network_pubkey_bytes;
  }

  /**
   * Sets next epoch network pubkey bytes.
   *
   * @param next_epoch_network_pubkey_bytes the next epoch network pubkey bytes
   */
  public void setNext_epoch_network_pubkey_bytes(byte[] next_epoch_network_pubkey_bytes) {
    this.next_epoch_network_pubkey_bytes = next_epoch_network_pubkey_bytes;
  }

  /**
   * Get next epoch worker pubkey bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_worker_pubkey_bytes() {
    return next_epoch_worker_pubkey_bytes;
  }

  /**
   * Sets next epoch worker pubkey bytes.
   *
   * @param next_epoch_worker_pubkey_bytes the next epoch worker pubkey bytes
   */
  public void setNext_epoch_worker_pubkey_bytes(byte[] next_epoch_worker_pubkey_bytes) {
    this.next_epoch_worker_pubkey_bytes = next_epoch_worker_pubkey_bytes;
  }

  /**
   * Get next epoch net address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_net_address() {
    return next_epoch_net_address;
  }

  /**
   * Sets next epoch net address.
   *
   * @param next_epoch_net_address the next epoch net address
   */
  public void setNext_epoch_net_address(byte[] next_epoch_net_address) {
    this.next_epoch_net_address = next_epoch_net_address;
  }

  /**
   * Get next epoch p 2 p address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_p2p_address() {
    return next_epoch_p2p_address;
  }

  /**
   * Sets next epoch p 2 p address.
   *
   * @param next_epoch_p2p_address the next epoch p 2 p address
   */
  public void setNext_epoch_p2p_address(byte[] next_epoch_p2p_address) {
    this.next_epoch_p2p_address = next_epoch_p2p_address;
  }

  /**
   * Get next epoch primary address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_primary_address() {
    return next_epoch_primary_address;
  }

  /**
   * Sets next epoch primary address.
   *
   * @param next_epoch_primary_address the next epoch primary address
   */
  public void setNext_epoch_primary_address(byte[] next_epoch_primary_address) {
    this.next_epoch_primary_address = next_epoch_primary_address;
  }

  /**
   * Get next epoch worker address byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getNext_epoch_worker_address() {
    return next_epoch_worker_address;
  }

  /**
   * Sets next epoch worker address.
   *
   * @param next_epoch_worker_address the next epoch worker address
   */
  public void setNext_epoch_worker_address(byte[] next_epoch_worker_address) {
    this.next_epoch_worker_address = next_epoch_worker_address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ValidatorMetadata)) {
      return false;
    }
    ValidatorMetadata that = (ValidatorMetadata) o;
    return sui_address.equals(that.sui_address)
        && Arrays.equals(pubkey_bytes, that.pubkey_bytes)
        && Arrays.equals(network_pubkey_bytes, that.network_pubkey_bytes)
        && Arrays.equals(worker_pubkey_bytes, that.worker_pubkey_bytes)
        && Arrays.equals(proof_of_possession_bytes, that.proof_of_possession_bytes)
        && name.equals(that.name)
        && description.equals(that.description)
        && image_url.equals(that.image_url)
        && project_url.equals(that.project_url)
        && Arrays.equals(net_address, that.net_address)
        && Arrays.equals(p2p_address, that.p2p_address)
        && Arrays.equals(primary_address, that.primary_address)
        && Arrays.equals(worker_address, that.worker_address)
        && Arrays.equals(next_epoch_protocol_pubkey_bytes, that.next_epoch_protocol_pubkey_bytes)
        && Arrays.equals(next_epoch_proof_of_possession, that.next_epoch_proof_of_possession)
        && Arrays.equals(next_epoch_network_pubkey_bytes, that.next_epoch_network_pubkey_bytes)
        && Arrays.equals(next_epoch_worker_pubkey_bytes, that.next_epoch_worker_pubkey_bytes)
        && Arrays.equals(next_epoch_net_address, that.next_epoch_net_address)
        && Arrays.equals(next_epoch_p2p_address, that.next_epoch_p2p_address)
        && Arrays.equals(next_epoch_primary_address, that.next_epoch_primary_address)
        && Arrays.equals(next_epoch_worker_address, that.next_epoch_worker_address);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(sui_address, name, description, image_url, project_url);
    result = 31 * result + Arrays.hashCode(pubkey_bytes);
    result = 31 * result + Arrays.hashCode(network_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(worker_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(proof_of_possession_bytes);
    result = 31 * result + Arrays.hashCode(net_address);
    result = 31 * result + Arrays.hashCode(p2p_address);
    result = 31 * result + Arrays.hashCode(primary_address);
    result = 31 * result + Arrays.hashCode(worker_address);
    result = 31 * result + Arrays.hashCode(next_epoch_protocol_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(next_epoch_proof_of_possession);
    result = 31 * result + Arrays.hashCode(next_epoch_network_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(next_epoch_worker_pubkey_bytes);
    result = 31 * result + Arrays.hashCode(next_epoch_net_address);
    result = 31 * result + Arrays.hashCode(next_epoch_p2p_address);
    result = 31 * result + Arrays.hashCode(next_epoch_primary_address);
    result = 31 * result + Arrays.hashCode(next_epoch_worker_address);
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
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", image_url='"
        + image_url
        + '\''
        + ", project_url='"
        + project_url
        + '\''
        + ", net_address="
        + Arrays.toString(net_address)
        + ", p2p_address="
        + Arrays.toString(p2p_address)
        + ", primary_address="
        + Arrays.toString(primary_address)
        + ", worker_address="
        + Arrays.toString(worker_address)
        + ", next_epoch_protocol_pubkey_bytes="
        + Arrays.toString(next_epoch_protocol_pubkey_bytes)
        + ", next_epoch_proof_of_possession="
        + Arrays.toString(next_epoch_proof_of_possession)
        + ", next_epoch_network_pubkey_bytes="
        + Arrays.toString(next_epoch_network_pubkey_bytes)
        + ", next_epoch_worker_pubkey_bytes="
        + Arrays.toString(next_epoch_worker_pubkey_bytes)
        + ", next_epoch_net_address="
        + Arrays.toString(next_epoch_net_address)
        + ", next_epoch_p2p_address="
        + Arrays.toString(next_epoch_p2p_address)
        + ", next_epoch_primary_address="
        + Arrays.toString(next_epoch_primary_address)
        + ", next_epoch_worker_address="
        + Arrays.toString(next_epoch_worker_address)
        + '}';
  }
}
