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

package io.sui;


import io.sui.models.CommitteeInfoResponse;
import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.GetObjectResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.SuiObjectInfo;
import io.sui.models.transactions.TransactionResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * The interface Sui client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface SuiClient {

  /**
   * Gets object.
   *
   * @param id the id
   * @return the object
   */
  CompletableFuture<GetObjectResponse> getObject(String id);

  /**
   * Gets objects owned by address.
   *
   * @param address the address
   * @return the objects owned by address
   */
  CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByAddress(String address);

  /**
   * Gets objects owned by object.
   *
   * @param objectId the object id
   * @return the objects owned by object
   */
  CompletableFuture<List<SuiObjectInfo>> getObjectsOwnedByObject(String objectId);

  /**
   * Gets raw object.
   *
   * @param id the id
   * @return the raw object
   */
  CompletableFuture<GetObjectResponse> getRawObject(String id);

  /**
   * Gets total transaction number.
   *
   * @return the total transaction number
   */
  CompletableFuture<Long> getTotalTransactionNumber();

  /**
   * Gets transaction.
   *
   * @param digest the digest
   * @return the transaction
   */
  CompletableFuture<TransactionResponse> getTransaction(String digest);

  /**
   * Gets transactions in range.
   *
   * @param start the start
   * @param end the end
   * @return the transactions in range
   */
  CompletableFuture<List<String>> getTransactionsInRange(Long start, Long end);

  /**
   * Gets events.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the events
   */
  CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder);

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
  CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch);

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
}
