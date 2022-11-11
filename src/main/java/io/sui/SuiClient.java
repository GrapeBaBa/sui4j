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


import io.sui.models.GetObjectResponse;
import io.sui.models.SuiObjectInfo;
import java.math.BigInteger;
import java.util.List;
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
  CompletableFuture<BigInteger> getTotalTransactionNumber();
}
