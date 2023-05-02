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

package io.sui.models.governance;


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Validator summary.
 *
 * @author grapebaba
 * @since 2023.05
 */
public class ValidatorSummary {

  private String suiAddress;
  private String protocolPubkeyBytes;
  private String networkPubkeyBytes;
  private String workerPubkeyBytes;
  private String proofOfPossessionBytes;
  private String operationCapId;
  private String name;
  private String description;
  private String imageUrl;
  private String projectUrl;
  private String p2pAddress;
  private String netAddress;
  private String primaryAddress;
  private String workerAddress;
  private String nextEpochProtocolPubkeyBytes;
  private String nextEpochProofOfPossession;
  private String nextEpochNetworkPubkeyBytes;
  private String nextEpochWorkerPubkeyBytes;
  private String nextEpochNetAddress;
  private String nextEpochP2pAddress;
  private String nextEpochPrimaryAddress;
  private String nextEpochWorkerAddress;
  private BigInteger votingPower;
  private BigInteger gasPrice;
  private BigInteger commissionRate;
  private BigInteger nextEpochStake;
  private BigInteger nextEpochGasPrice;
  private BigInteger nextEpochCommissionRate;
  private String stakingPoolId;
  private BigInteger stakingPoolActivationEpoch;
  private BigInteger stakingPoolDeactivationEpoch;
  private BigInteger stakingPoolSuiBalance;
  private BigInteger rewardsPool;
  private BigInteger poolTokenBalance;
  private BigInteger pendingStake;
  private BigInteger pendingPoolTokenWithdraw;
  private BigInteger pendingTotalSuiWithdraw;
  private String exchangeRatesId;
  private BigInteger exchangeRatesSize;

  /**
   * Gets sui address.
   *
   * @return the sui address
   */
  public String getSuiAddress() {
    return suiAddress;
  }

  /**
   * Sets sui address.
   *
   * @param suiAddress the sui address
   */
  public void setSuiAddress(String suiAddress) {
    this.suiAddress = suiAddress;
  }

  /**
   * Gets protocol pubkey bytes.
   *
   * @return the protocol pubkey bytes
   */
  public String getProtocolPubkeyBytes() {
    return protocolPubkeyBytes;
  }

  /**
   * Sets protocol pubkey bytes.
   *
   * @param protocolPubkeyBytes the protocol pubkey bytes
   */
  public void setProtocolPubkeyBytes(String protocolPubkeyBytes) {
    this.protocolPubkeyBytes = protocolPubkeyBytes;
  }

  /**
   * Gets network pubkey bytes.
   *
   * @return the network pubkey bytes
   */
  public String getNetworkPubkeyBytes() {
    return networkPubkeyBytes;
  }

  /**
   * Sets network pubkey bytes.
   *
   * @param networkPubkeyBytes the network pubkey bytes
   */
  public void setNetworkPubkeyBytes(String networkPubkeyBytes) {
    this.networkPubkeyBytes = networkPubkeyBytes;
  }

  /**
   * Gets worker pubkey bytes.
   *
   * @return the worker pubkey bytes
   */
  public String getWorkerPubkeyBytes() {
    return workerPubkeyBytes;
  }

  /**
   * Sets worker pubkey bytes.
   *
   * @param workerPubkeyBytes the worker pubkey bytes
   */
  public void setWorkerPubkeyBytes(String workerPubkeyBytes) {
    this.workerPubkeyBytes = workerPubkeyBytes;
  }

  /**
   * Gets proof of possession bytes.
   *
   * @return the proof of possession bytes
   */
  public String getProofOfPossessionBytes() {
    return proofOfPossessionBytes;
  }

  /**
   * Sets proof of possession bytes.
   *
   * @param proofOfPossessionBytes the proof of possession bytes
   */
  public void setProofOfPossessionBytes(String proofOfPossessionBytes) {
    this.proofOfPossessionBytes = proofOfPossessionBytes;
  }

  /**
   * Gets operation cap id.
   *
   * @return the operation cap id
   */
  public String getOperationCapId() {
    return operationCapId;
  }

  /**
   * Sets operation cap id.
   *
   * @param operationCapId the operation cap id
   */
  public void setOperationCapId(String operationCapId) {
    this.operationCapId = operationCapId;
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
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets image url.
   *
   * @param imageUrl the image url
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Gets project url.
   *
   * @return the project url
   */
  public String getProjectUrl() {
    return projectUrl;
  }

  /**
   * Sets project url.
   *
   * @param projectUrl the project url
   */
  public void setProjectUrl(String projectUrl) {
    this.projectUrl = projectUrl;
  }

  /**
   * Gets p 2 p address.
   *
   * @return the p 2 p address
   */
  public String getP2pAddress() {
    return p2pAddress;
  }

  /**
   * Sets p 2 p address.
   *
   * @param p2pAddress the p 2 p address
   */
  public void setP2pAddress(String p2pAddress) {
    this.p2pAddress = p2pAddress;
  }

  /**
   * Gets net address.
   *
   * @return the net address
   */
  public String getNetAddress() {
    return netAddress;
  }

  /**
   * Sets net address.
   *
   * @param netAddress the net address
   */
  public void setNetAddress(String netAddress) {
    this.netAddress = netAddress;
  }

  /**
   * Gets primary address.
   *
   * @return the primary address
   */
  public String getPrimaryAddress() {
    return primaryAddress;
  }

  /**
   * Sets primary address.
   *
   * @param primaryAddress the primary address
   */
  public void setPrimaryAddress(String primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  /**
   * Gets worker address.
   *
   * @return the worker address
   */
  public String getWorkerAddress() {
    return workerAddress;
  }

  /**
   * Sets worker address.
   *
   * @param workerAddress the worker address
   */
  public void setWorkerAddress(String workerAddress) {
    this.workerAddress = workerAddress;
  }

  /**
   * Gets next epoch protocol pubkey bytes.
   *
   * @return the next epoch protocol pubkey bytes
   */
  public String getNextEpochProtocolPubkeyBytes() {
    return nextEpochProtocolPubkeyBytes;
  }

  /**
   * Sets next epoch protocol pubkey bytes.
   *
   * @param nextEpochProtocolPubkeyBytes the next epoch protocol pubkey bytes
   */
  public void setNextEpochProtocolPubkeyBytes(String nextEpochProtocolPubkeyBytes) {
    this.nextEpochProtocolPubkeyBytes = nextEpochProtocolPubkeyBytes;
  }

  /**
   * Gets next epoch proof of possession.
   *
   * @return the next epoch proof of possession
   */
  public String getNextEpochProofOfPossession() {
    return nextEpochProofOfPossession;
  }

  /**
   * Sets next epoch proof of possession.
   *
   * @param nextEpochProofOfPossession the next epoch proof of possession
   */
  public void setNextEpochProofOfPossession(String nextEpochProofOfPossession) {
    this.nextEpochProofOfPossession = nextEpochProofOfPossession;
  }

  /**
   * Gets next epoch network pubkey bytes.
   *
   * @return the next epoch network pubkey bytes
   */
  public String getNextEpochNetworkPubkeyBytes() {
    return nextEpochNetworkPubkeyBytes;
  }

  /**
   * Sets next epoch network pubkey bytes.
   *
   * @param nextEpochNetworkPubkeyBytes the next epoch network pubkey bytes
   */
  public void setNextEpochNetworkPubkeyBytes(String nextEpochNetworkPubkeyBytes) {
    this.nextEpochNetworkPubkeyBytes = nextEpochNetworkPubkeyBytes;
  }

  /**
   * Gets next epoch worker pubkey bytes.
   *
   * @return the next epoch worker pubkey bytes
   */
  public String getNextEpochWorkerPubkeyBytes() {
    return nextEpochWorkerPubkeyBytes;
  }

  /**
   * Sets next epoch worker pubkey bytes.
   *
   * @param nextEpochWorkerPubkeyBytes the next epoch worker pubkey bytes
   */
  public void setNextEpochWorkerPubkeyBytes(String nextEpochWorkerPubkeyBytes) {
    this.nextEpochWorkerPubkeyBytes = nextEpochWorkerPubkeyBytes;
  }

  /**
   * Gets next epoch net address.
   *
   * @return the next epoch net address
   */
  public String getNextEpochNetAddress() {
    return nextEpochNetAddress;
  }

  /**
   * Sets next epoch net address.
   *
   * @param nextEpochNetAddress the next epoch net address
   */
  public void setNextEpochNetAddress(String nextEpochNetAddress) {
    this.nextEpochNetAddress = nextEpochNetAddress;
  }

  /**
   * Gets next epoch p 2 p address.
   *
   * @return the next epoch p 2 p address
   */
  public String getNextEpochP2pAddress() {
    return nextEpochP2pAddress;
  }

  /**
   * Sets next epoch p 2 p address.
   *
   * @param nextEpochP2pAddress the next epoch p 2 p address
   */
  public void setNextEpochP2pAddress(String nextEpochP2pAddress) {
    this.nextEpochP2pAddress = nextEpochP2pAddress;
  }

  /**
   * Gets next epoch primary address.
   *
   * @return the next epoch primary address
   */
  public String getNextEpochPrimaryAddress() {
    return nextEpochPrimaryAddress;
  }

  /**
   * Sets next epoch primary address.
   *
   * @param nextEpochPrimaryAddress the next epoch primary address
   */
  public void setNextEpochPrimaryAddress(String nextEpochPrimaryAddress) {
    this.nextEpochPrimaryAddress = nextEpochPrimaryAddress;
  }

  /**
   * Gets next epoch worker address.
   *
   * @return the next epoch worker address
   */
  public String getNextEpochWorkerAddress() {
    return nextEpochWorkerAddress;
  }

  /**
   * Sets next epoch worker address.
   *
   * @param nextEpochWorkerAddress the next epoch worker address
   */
  public void setNextEpochWorkerAddress(String nextEpochWorkerAddress) {
    this.nextEpochWorkerAddress = nextEpochWorkerAddress;
  }

  /**
   * Gets voting power.
   *
   * @return the voting power
   */
  public BigInteger getVotingPower() {
    return votingPower;
  }

  /**
   * Sets voting power.
   *
   * @param votingPower the voting power
   */
  public void setVotingPower(BigInteger votingPower) {
    this.votingPower = votingPower;
  }

  /**
   * Gets gas price.
   *
   * @return the gas price
   */
  public BigInteger getGasPrice() {
    return gasPrice;
  }

  /**
   * Sets gas price.
   *
   * @param gasPrice the gas price
   */
  public void setGasPrice(BigInteger gasPrice) {
    this.gasPrice = gasPrice;
  }

  /**
   * Gets commission rate.
   *
   * @return the commission rate
   */
  public BigInteger getCommissionRate() {
    return commissionRate;
  }

  /**
   * Sets commission rate.
   *
   * @param commissionRate the commission rate
   */
  public void setCommissionRate(BigInteger commissionRate) {
    this.commissionRate = commissionRate;
  }

  /**
   * Gets next epoch stake.
   *
   * @return the next epoch stake
   */
  public BigInteger getNextEpochStake() {
    return nextEpochStake;
  }

  /**
   * Sets next epoch stake.
   *
   * @param nextEpochStake the next epoch stake
   */
  public void setNextEpochStake(BigInteger nextEpochStake) {
    this.nextEpochStake = nextEpochStake;
  }

  /**
   * Gets next epoch gas price.
   *
   * @return the next epoch gas price
   */
  public BigInteger getNextEpochGasPrice() {
    return nextEpochGasPrice;
  }

  /**
   * Sets next epoch gas price.
   *
   * @param nextEpochGasPrice the next epoch gas price
   */
  public void setNextEpochGasPrice(BigInteger nextEpochGasPrice) {
    this.nextEpochGasPrice = nextEpochGasPrice;
  }

  /**
   * Gets next epoch commission rate.
   *
   * @return the next epoch commission rate
   */
  public BigInteger getNextEpochCommissionRate() {
    return nextEpochCommissionRate;
  }

  /**
   * Sets next epoch commission rate.
   *
   * @param nextEpochCommissionRate the next epoch commission rate
   */
  public void setNextEpochCommissionRate(BigInteger nextEpochCommissionRate) {
    this.nextEpochCommissionRate = nextEpochCommissionRate;
  }

  /**
   * Gets staking pool id.
   *
   * @return the staking pool id
   */
  public String getStakingPoolId() {
    return stakingPoolId;
  }

  /**
   * Sets staking pool id.
   *
   * @param stakingPoolId the staking pool id
   */
  public void setStakingPoolId(String stakingPoolId) {
    this.stakingPoolId = stakingPoolId;
  }

  /**
   * Gets staking pool activation epoch.
   *
   * @return the staking pool activation epoch
   */
  public BigInteger getStakingPoolActivationEpoch() {
    return stakingPoolActivationEpoch;
  }

  /**
   * Sets staking pool activation epoch.
   *
   * @param stakingPoolActivationEpoch the staking pool activation epoch
   */
  public void setStakingPoolActivationEpoch(BigInteger stakingPoolActivationEpoch) {
    this.stakingPoolActivationEpoch = stakingPoolActivationEpoch;
  }

  /**
   * Gets staking pool deactivation epoch.
   *
   * @return the staking pool deactivation epoch
   */
  public BigInteger getStakingPoolDeactivationEpoch() {
    return stakingPoolDeactivationEpoch;
  }

  /**
   * Sets staking pool deactivation epoch.
   *
   * @param stakingPoolDeactivationEpoch the staking pool deactivation epoch
   */
  public void setStakingPoolDeactivationEpoch(BigInteger stakingPoolDeactivationEpoch) {
    this.stakingPoolDeactivationEpoch = stakingPoolDeactivationEpoch;
  }

  /**
   * Gets staking pool sui balance.
   *
   * @return the staking pool sui balance
   */
  public BigInteger getStakingPoolSuiBalance() {
    return stakingPoolSuiBalance;
  }

  /**
   * Sets staking pool sui balance.
   *
   * @param stakingPoolSuiBalance the staking pool sui balance
   */
  public void setStakingPoolSuiBalance(BigInteger stakingPoolSuiBalance) {
    this.stakingPoolSuiBalance = stakingPoolSuiBalance;
  }

  /**
   * Gets rewards pool.
   *
   * @return the rewards pool
   */
  public BigInteger getRewardsPool() {
    return rewardsPool;
  }

  /**
   * Sets rewards pool.
   *
   * @param rewardsPool the rewards pool
   */
  public void setRewardsPool(BigInteger rewardsPool) {
    this.rewardsPool = rewardsPool;
  }

  /**
   * Gets pool token balance.
   *
   * @return the pool token balance
   */
  public BigInteger getPoolTokenBalance() {
    return poolTokenBalance;
  }

  /**
   * Sets pool token balance.
   *
   * @param poolTokenBalance the pool token balance
   */
  public void setPoolTokenBalance(BigInteger poolTokenBalance) {
    this.poolTokenBalance = poolTokenBalance;
  }

  /**
   * Gets pending stake.
   *
   * @return the pending stake
   */
  public BigInteger getPendingStake() {
    return pendingStake;
  }

  /**
   * Sets pending stake.
   *
   * @param pendingStake the pending stake
   */
  public void setPendingStake(BigInteger pendingStake) {
    this.pendingStake = pendingStake;
  }

  /**
   * Gets pending pool token withdraw.
   *
   * @return the pending pool token withdraw
   */
  public BigInteger getPendingPoolTokenWithdraw() {
    return pendingPoolTokenWithdraw;
  }

  /**
   * Sets pending pool token withdraw.
   *
   * @param pendingPoolTokenWithdraw the pending pool token withdraw
   */
  public void setPendingPoolTokenWithdraw(BigInteger pendingPoolTokenWithdraw) {
    this.pendingPoolTokenWithdraw = pendingPoolTokenWithdraw;
  }

  /**
   * Gets pending total sui withdraw.
   *
   * @return the pending total sui withdraw
   */
  public BigInteger getPendingTotalSuiWithdraw() {
    return pendingTotalSuiWithdraw;
  }

  /**
   * Sets pending total sui withdraw.
   *
   * @param pendingTotalSuiWithdraw the pending total sui withdraw
   */
  public void setPendingTotalSuiWithdraw(BigInteger pendingTotalSuiWithdraw) {
    this.pendingTotalSuiWithdraw = pendingTotalSuiWithdraw;
  }

  /**
   * Gets exchange rates id.
   *
   * @return the exchange rates id
   */
  public String getExchangeRatesId() {
    return exchangeRatesId;
  }

  /**
   * Sets exchange rates id.
   *
   * @param exchangeRatesId the exchange rates id
   */
  public void setExchangeRatesId(String exchangeRatesId) {
    this.exchangeRatesId = exchangeRatesId;
  }

  /**
   * Gets exchange rates size.
   *
   * @return the exchange rates size
   */
  public BigInteger getExchangeRatesSize() {
    return exchangeRatesSize;
  }

  /**
   * Sets exchange rates size.
   *
   * @param exchangeRatesSize the exchange rates size
   */
  public void setExchangeRatesSize(BigInteger exchangeRatesSize) {
    this.exchangeRatesSize = exchangeRatesSize;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ValidatorSummary)) {
      return false;
    }
    ValidatorSummary that = (ValidatorSummary) o;
    return Objects.equals(suiAddress, that.suiAddress)
        && Objects.equals(protocolPubkeyBytes, that.protocolPubkeyBytes)
        && Objects.equals(networkPubkeyBytes, that.networkPubkeyBytes)
        && Objects.equals(workerPubkeyBytes, that.workerPubkeyBytes)
        && Objects.equals(proofOfPossessionBytes, that.proofOfPossessionBytes)
        && Objects.equals(operationCapId, that.operationCapId)
        && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && Objects.equals(imageUrl, that.imageUrl)
        && Objects.equals(projectUrl, that.projectUrl)
        && Objects.equals(p2pAddress, that.p2pAddress)
        && Objects.equals(netAddress, that.netAddress)
        && Objects.equals(primaryAddress, that.primaryAddress)
        && Objects.equals(workerAddress, that.workerAddress)
        && Objects.equals(nextEpochProtocolPubkeyBytes, that.nextEpochProtocolPubkeyBytes)
        && Objects.equals(nextEpochProofOfPossession, that.nextEpochProofOfPossession)
        && Objects.equals(nextEpochNetworkPubkeyBytes, that.nextEpochNetworkPubkeyBytes)
        && Objects.equals(nextEpochWorkerPubkeyBytes, that.nextEpochWorkerPubkeyBytes)
        && Objects.equals(nextEpochNetAddress, that.nextEpochNetAddress)
        && Objects.equals(nextEpochP2pAddress, that.nextEpochP2pAddress)
        && Objects.equals(nextEpochPrimaryAddress, that.nextEpochPrimaryAddress)
        && Objects.equals(nextEpochWorkerAddress, that.nextEpochWorkerAddress)
        && Objects.equals(votingPower, that.votingPower)
        && Objects.equals(gasPrice, that.gasPrice)
        && Objects.equals(commissionRate, that.commissionRate)
        && Objects.equals(nextEpochStake, that.nextEpochStake)
        && Objects.equals(nextEpochGasPrice, that.nextEpochGasPrice)
        && Objects.equals(nextEpochCommissionRate, that.nextEpochCommissionRate)
        && Objects.equals(stakingPoolId, that.stakingPoolId)
        && Objects.equals(stakingPoolActivationEpoch, that.stakingPoolActivationEpoch)
        && Objects.equals(stakingPoolDeactivationEpoch, that.stakingPoolDeactivationEpoch)
        && Objects.equals(stakingPoolSuiBalance, that.stakingPoolSuiBalance)
        && Objects.equals(rewardsPool, that.rewardsPool)
        && Objects.equals(poolTokenBalance, that.poolTokenBalance)
        && Objects.equals(pendingStake, that.pendingStake)
        && Objects.equals(pendingPoolTokenWithdraw, that.pendingPoolTokenWithdraw)
        && Objects.equals(pendingTotalSuiWithdraw, that.pendingTotalSuiWithdraw)
        && Objects.equals(exchangeRatesId, that.exchangeRatesId)
        && Objects.equals(exchangeRatesSize, that.exchangeRatesSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        suiAddress,
        protocolPubkeyBytes,
        networkPubkeyBytes,
        workerPubkeyBytes,
        proofOfPossessionBytes,
        operationCapId,
        name,
        description,
        imageUrl,
        projectUrl,
        p2pAddress,
        netAddress,
        primaryAddress,
        workerAddress,
        nextEpochProtocolPubkeyBytes,
        nextEpochProofOfPossession,
        nextEpochNetworkPubkeyBytes,
        nextEpochWorkerPubkeyBytes,
        nextEpochNetAddress,
        nextEpochP2pAddress,
        nextEpochPrimaryAddress,
        nextEpochWorkerAddress,
        votingPower,
        gasPrice,
        commissionRate,
        nextEpochStake,
        nextEpochGasPrice,
        nextEpochCommissionRate,
        stakingPoolId,
        stakingPoolActivationEpoch,
        stakingPoolDeactivationEpoch,
        stakingPoolSuiBalance,
        rewardsPool,
        poolTokenBalance,
        pendingStake,
        pendingPoolTokenWithdraw,
        pendingTotalSuiWithdraw,
        exchangeRatesId,
        exchangeRatesSize);
  }

  @Override
  public String toString() {
    return "ValidatorSummary{"
        + "suiAddress='"
        + suiAddress
        + '\''
        + ", protocolPubkeyBytes='"
        + protocolPubkeyBytes
        + '\''
        + ", networkPubkeyBytes='"
        + networkPubkeyBytes
        + '\''
        + ", workerPubkeyBytes='"
        + workerPubkeyBytes
        + '\''
        + ", proofOfPossessionBytes='"
        + proofOfPossessionBytes
        + '\''
        + ", operationCapId='"
        + operationCapId
        + '\''
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", imageUrl='"
        + imageUrl
        + '\''
        + ", projectUrl='"
        + projectUrl
        + '\''
        + ", p2pAddress='"
        + p2pAddress
        + '\''
        + ", netAddress='"
        + netAddress
        + '\''
        + ", primaryAddress='"
        + primaryAddress
        + '\''
        + ", workerAddress='"
        + workerAddress
        + '\''
        + ", nextEpochProtocolPubkeyBytes='"
        + nextEpochProtocolPubkeyBytes
        + '\''
        + ", nextEpochProofOfPossession='"
        + nextEpochProofOfPossession
        + '\''
        + ", nextEpochNetworkPubkeyBytes='"
        + nextEpochNetworkPubkeyBytes
        + '\''
        + ", nextEpochWorkerPubkeyBytes='"
        + nextEpochWorkerPubkeyBytes
        + '\''
        + ", nextEpochNetAddress='"
        + nextEpochNetAddress
        + '\''
        + ", nextEpochP2pAddress='"
        + nextEpochP2pAddress
        + '\''
        + ", nextEpochPrimaryAddress='"
        + nextEpochPrimaryAddress
        + '\''
        + ", nextEpochWorkerAddress='"
        + nextEpochWorkerAddress
        + '\''
        + ", votingPower="
        + votingPower
        + ", gasPrice="
        + gasPrice
        + ", commissionRate="
        + commissionRate
        + ", nextEpochStake="
        + nextEpochStake
        + ", nextEpochGasPrice="
        + nextEpochGasPrice
        + ", nextEpochCommissionRate="
        + nextEpochCommissionRate
        + ", stakingPoolId='"
        + stakingPoolId
        + '\''
        + ", stakingPoolActivationEpoch="
        + stakingPoolActivationEpoch
        + ", stakingPoolDeactivationEpoch="
        + stakingPoolDeactivationEpoch
        + ", stakingPoolSuiBalance="
        + stakingPoolSuiBalance
        + ", rewardsPool="
        + rewardsPool
        + ", poolTokenBalance="
        + poolTokenBalance
        + ", pendingStake="
        + pendingStake
        + ", pendingPoolTokenWithdraw="
        + pendingPoolTokenWithdraw
        + ", pendingTotalSuiWithdraw="
        + pendingTotalSuiWithdraw
        + ", exchangeRatesId='"
        + exchangeRatesId
        + '\''
        + ", exchangeRatesSize="
        + exchangeRatesSize
        + '}';
  }
}
