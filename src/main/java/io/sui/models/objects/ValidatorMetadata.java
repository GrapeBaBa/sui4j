package io.sui.models.objects;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ValidatorMetadata {

  @SerializedName(value = "address", alternate = "sui_address")
  private String address;

  @SerializedName(value = "pubkeyBytes", alternate = "pubkey_bytes")
  private byte[] pubkeyBytes;

  @SerializedName(value = "networkPubkeyBytes", alternate = "network_pubkey_bytes")
  private byte[] networkPubkeyBytes;

  @SerializedName(value = "workerPubkeyBytes", alternate = "worker_pubkey_bytes")
  private byte[] workerPubkeyBytes;

  @SerializedName(value = "proofOfPossessionBytes", alternate = "proof_of_possession_bytes")
  private byte[] proofOfPossessionBytes;

  private byte[] name;

  private byte[] description;

  @SerializedName(value = "imageUrl", alternate = "image_url")
  private byte[] imageUrl;

  @SerializedName(value = "projectUrl", alternate = "project_url")
  private byte[] projectUrl;

  @SerializedName(value = "netAddress", alternate = "net_address")
  private byte[] netAddress;

  @SerializedName(value = "consensusAddress", alternate = "consensus_address")
  private byte[] consensusAddress;

  @SerializedName(value = "workerAddress", alternate = "worker_address")
  private byte[] workerAddress;

  @SerializedName(value = "nextEpochStake", alternate = "next_epoch_stake")
  private Long nextEpochStake;

  @SerializedName(value = "nextEpochDelegation", alternate = "next_epoch_delegation")
  private Long nextEpochDelegation;

  @SerializedName(value = "nextEpochGasPrice", alternate = "next_epoch_gas_price")
  private Long next_epoch_gas_price;

  @SerializedName(value = "nextEpochCommissionRate", alternate = "next_epoch_commission_rate")
  private Long nextEpochCommissionRate;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public byte[] getPubkeyBytes() {
    return pubkeyBytes;
  }

  public void setPubkeyBytes(byte[] pubkeyBytes) {
    this.pubkeyBytes = pubkeyBytes;
  }

  public byte[] getNetworkPubkeyBytes() {
    return networkPubkeyBytes;
  }

  public void setNetworkPubkeyBytes(byte[] networkPubkeyBytes) {
    this.networkPubkeyBytes = networkPubkeyBytes;
  }

  public byte[] getWorkerPubkeyBytes() {
    return workerPubkeyBytes;
  }

  public void setWorkerPubkeyBytes(byte[] workerPubkeyBytes) {
    this.workerPubkeyBytes = workerPubkeyBytes;
  }

  public byte[] getProofOfPossessionBytes() {
    return proofOfPossessionBytes;
  }

  public void setProofOfPossessionBytes(byte[] proofOfPossessionBytes) {
    this.proofOfPossessionBytes = proofOfPossessionBytes;
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

  public byte[] getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(byte[] imageUrl) {
    this.imageUrl = imageUrl;
  }

  public byte[] getProjectUrl() {
    return projectUrl;
  }

  public void setProjectUrl(byte[] projectUrl) {
    this.projectUrl = projectUrl;
  }

  public byte[] getNetAddress() {
    return netAddress;
  }

  public void setNetAddress(byte[] netAddress) {
    this.netAddress = netAddress;
  }

  public byte[] getConsensusAddress() {
    return consensusAddress;
  }

  public void setConsensusAddress(byte[] consensusAddress) {
    this.consensusAddress = consensusAddress;
  }

  public byte[] getWorkerAddress() {
    return workerAddress;
  }

  public void setWorkerAddress(byte[] workerAddress) {
    this.workerAddress = workerAddress;
  }

  public Long getNextEpochStake() {
    return nextEpochStake;
  }

  public void setNextEpochStake(Long nextEpochStake) {
    this.nextEpochStake = nextEpochStake;
  }

  public Long getNextEpochDelegation() {
    return nextEpochDelegation;
  }

  public void setNextEpochDelegation(Long nextEpochDelegation) {
    this.nextEpochDelegation = nextEpochDelegation;
  }

  public Long getNext_epoch_gas_price() {
    return next_epoch_gas_price;
  }

  public void setNext_epoch_gas_price(Long next_epoch_gas_price) {
    this.next_epoch_gas_price = next_epoch_gas_price;
  }

  public Long getNextEpochCommissionRate() {
    return nextEpochCommissionRate;
  }

  public void setNextEpochCommissionRate(Long nextEpochCommissionRate) {
    this.nextEpochCommissionRate = nextEpochCommissionRate;
  }

  @Override
  public String toString() {
    return "ValidatorMetadata{" +
        "address='" + address + '\'' +
        ", pubkeyBytes='" + Arrays.toString(pubkeyBytes) + '\'' +
        ", networkPubkeyBytes='" + Arrays.toString(networkPubkeyBytes) + '\'' +
        ", workerPubkeyBytes='" + Arrays.toString(workerPubkeyBytes) + '\'' +
        ", proofOfPossessionBytes='" + Arrays.toString(proofOfPossessionBytes) + '\'' +
        ", name='" + Arrays.toString(name) + '\'' +
        ", description='" + Arrays.toString(description) + '\'' +
        ", imageUrl='" + Arrays.toString(imageUrl) + '\'' +
        ", projectUrl='" + Arrays.toString(projectUrl) + '\'' +
        ", netAddress='" + Arrays.toString(netAddress) + '\'' +
        ", consensusAddress='" + Arrays.toString(consensusAddress) + '\'' +
        ", workerAddress='" + Arrays.toString(workerAddress) + '\'' +
        ", nextEpochStake=" + nextEpochStake +
        ", nextEpochDelegation=" + nextEpochDelegation +
        ", next_epoch_gas_price=" + next_epoch_gas_price +
        ", nextEpochCommissionRate=" + nextEpochCommissionRate +
        '}';
  }
}
