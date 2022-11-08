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

package io.sui.jsonrpc;


import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The interface Json rpc client provider.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface JsonRpcClientProvider {

  /** The constant nextId. */
  AtomicLong nextId = new AtomicLong();

  /**
   * Next id long.
   *
   * @return the long
   */
  default long nextId() {
    return nextId.incrementAndGet();
  }

  /**
   * Call completable future.
   *
   * @param <T> the type parameter
   * @param request the request
   * @param url the url
   * @param typeOfT the type of t
   * @return the completable future
   */
  <T> CompletableFuture<JsonRpc20Response<T>> call(
      JsonRpc20Request request, String url, Type typeOfT);
}
