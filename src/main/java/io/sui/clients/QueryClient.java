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

package io.sui.clients;


import io.sui.models.coin.Balance;
import io.sui.models.coin.CoinMetadata;
import io.sui.models.coin.CoinSupply;
import io.sui.models.coin.PaginatedCoins;
import io.sui.models.events.EventFilter;
import io.sui.models.events.EventId;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.events.SuiEvent;
import io.sui.models.governance.DelegatedStake;
import io.sui.models.governance.SuiCommitteeInfo;
import io.sui.models.governance.SystemStateSummary;
import io.sui.models.governance.ValidatorsApy;
import io.sui.models.objects.Checkpoint;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedCheckpoint;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiObjectResponse;
import io.sui.models.transactions.PaginatedTransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionBlockResponseQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * The interface Sui client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface QueryClient {

  /** The constant DEFAULT_COIN_TYPE. */
  String DEFAULT_COIN_TYPE = "0x2::sui::SUI";

  /**
   * Gets object.
   *
   * @param id the id
   * @param objectDataOptions the object data options
   * @return the object
   */
  CompletableFuture<SuiObjectResponse> getObject(String id, ObjectDataOptions objectDataOptions);

  /**
   * Gets objects owned by address.
   *
   * @param address the address
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @return the objects owned by address
   */
  CompletableFuture<PaginatedObjectsResponse> getOwnedObjects(
      String address, ObjectResponseQuery query, String cursor, Integer limit);

  /**
   * Gets total transaction number.
   *
   * @return the total transaction number
   */
  CompletableFuture<Long> getTotalTransactionBlocks();

  /**
   * Gets transaction.
   *
   * @param digest the digest
   * @param options the options
   * @return the transaction
   */
  CompletableFuture<TransactionBlockResponse> getTransactionBlock(
      String digest, TransactionBlockResponseOptions options);

  /**
   * Multi get transaction blocks completable future.
   *
   * @param digests the digests
   * @param options the options
   * @return the completable future
   */
  CompletableFuture<List<TransactionBlockResponse>> multiGetTransactionBlocks(
      List<String> digests, TransactionBlockResponseOptions options);

  /**
   * Multi get objects completable future.
   *
   * @param objectIds the object ids
   * @param options the options
   * @return the completable future
   */
  CompletableFuture<List<SuiObjectResponse>> multiGetObjects(
      List<String> objectIds, ObjectDataOptions options);

  /**
   * Gets events.
   *
   * @param eventFilter the event filter
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the events
   */
  CompletableFuture<PaginatedEvents> queryEvents(
      EventFilter eventFilter, EventId cursor, Integer limit, boolean isDescOrder);

  /**
   * Gets normalized move modules by package.
   *
   * @param packageId the package id
   * @return the normalized move modules by package
   */
  CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId);

  /**
   * Gets committee info.
   *
   * @param epoch the epoch
   * @return the committee info
   */
  CompletableFuture<SuiCommitteeInfo> getCommitteeInfo(BigInteger epoch);

  /**
   * Gets move function arg types.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the move function arg types
   */
  CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function);

  /**
   * Gets normalized move function.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the normalized move function
   */
  CompletableFuture<MoveNormalizedFunction> getNormalizedMoveFunction(
      String suiPackage, String module, String function);

  /**
   * Gets normalized move module.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @return the normalized move module
   */
  CompletableFuture<MoveNormalizedModule> getNormalizedMoveModule(String suiPackage, String module);

  /**
   * Gets normalized move struct.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param struct the struct
   * @return the normalized move struct
   */
  CompletableFuture<MoveNormalizedStruct> getNormalizedMoveStruct(
      String suiPackage, String module, String struct);

  /**
   * Gets transactions.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the transactions
   */
  CompletableFuture<PaginatedTransactionBlockResponse> queryTransactionBlocks(
      TransactionBlockResponseQuery query, String cursor, Integer limit, boolean isDescOrder);

  /**
   * Gets coin metadata.
   *
   * @param coinType the coin type
   * @return the coin metadata
   */
  CompletableFuture<CoinMetadata> getCoinMetadata(String coinType);

  /**
   * Gets reference gas price.
   *
   * @return the reference gas price
   */
  CompletableFuture<Long> getReferenceGasPrice();

  /**
   * Gets object ref.
   *
   * @param id the id
   * @param objectDataOptions the object data options
   * @return the object ref
   */
  CompletableFuture<SuiObjectRef> getObjectRef(String id, ObjectDataOptions objectDataOptions);

  /**
   * Return the total coin balance for all coin type, owned by the address owner.
   *
   * @param address the owner address
   * @return the total coin balance for all coin type, owned by the address owner.
   */
  CompletableFuture<List<Balance>> getAllBalances(String address);

  /**
   * Return all Coin objects owned by an address.
   *
   * @param address the owner address
   * @param cursor the cursor
   * @param limit the limit
   * @return all Coin objects owned by the address.
   */
  CompletableFuture<PaginatedCoins> getAllCoins(String address, String cursor, Integer limit);

  /**
   * Return all Coin with coin_type objects owned by an address.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @param cursor the cursor
   * @param limit the limit
   * @return all Coin objects owned by the address.
   */
  CompletableFuture<PaginatedCoins> getCoins(
      String address, String coinType, String cursor, long limit);

  /**
   * Return the total coin balance for one coin type, owned by the address owner.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @return the balance for the coin type, owned by the address owner
   */
  CompletableFuture<Balance> getBalance(String address, String coinType);

  /**
   * Return a checkpoint based on a checkpoint sequence number or digest.
   *
   * @param checkpointId the checkpoint sequence number
   * @return the checkpoint summary based on the checkpoint sequence number
   */
  CompletableFuture<Checkpoint> getCheckpoint(String checkpointId);

  /**
   * Gets validators apy.
   *
   * @return the validators apy
   */
  CompletableFuture<ValidatorsApy> getValidatorsApy();

  /**
   * Gets total supply.
   *
   * @param coin the coin
   * @return the total supply
   */
  CompletableFuture<CoinSupply> getTotalSupply(String coin);

  /**
   * Gets stakes by ids.
   *
   * @param stakedSuiIds the staked sui ids
   * @return the stakes by ids
   */
  CompletableFuture<List<DelegatedStake>> getStakesByIds(List<String> stakedSuiIds);

  /**
   * Gets stakes.
   *
   * @param owner the owner
   * @return the stakes
   */
  CompletableFuture<List<DelegatedStake>> getStakes(String owner);

  /**
   * Gets latest sui system state.
   *
   * @return the latest sui system state
   */
  CompletableFuture<SystemStateSummary> getLatestSuiSystemState();

  /**
   * Gets checkpoints.
   *
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the checkpoints
   */
  CompletableFuture<PaginatedCheckpoint> getCheckpoints(
      String cursor, Integer limit, boolean isDescOrder);

  /**
   * Gets events.
   *
   * @param transactionDigest the transaction digest
   * @return the events
   */
  CompletableFuture<List<SuiEvent>> getEvents(String transactionDigest);

  /**
   * Gets latest checkpoint sequence number.
   *
   * @return the latest checkpoint sequence number
   */
  CompletableFuture<BigInteger> getLatestCheckpointSequenceNumber();
}
