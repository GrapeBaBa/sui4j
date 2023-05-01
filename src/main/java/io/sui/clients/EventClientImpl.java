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


import com.google.common.collect.Lists;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventFilter;
import io.sui.models.events.SuiEvent;
import io.sui.models.transactions.TransactionEffects;
import io.sui.models.transactions.TransactionFilter;

/**
 * The type Event client.
 *
 * @author grapebaba
 * @since 2022.12
 */
public class EventClientImpl implements EventClient {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  public EventClientImpl(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public Disposable subscribeEvent(
      EventFilter eventFilter, Consumer<SuiEvent> onNext, Consumer<SuiApiException> onError) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_subscribeEvent", Lists.newArrayList(eventFilter));
    return this.jsonRpcClientProvider.subscribe(request, onNext, onError);
  }

  @Override
  public Disposable subscribeTransaction(
      TransactionFilter transactionFilter,
      Consumer<TransactionEffects> onNext,
      Consumer<SuiApiException> onError) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "suix_subscribeTransaction", Lists.newArrayList(transactionFilter));
    return this.jsonRpcClientProvider.subscribe(request, onNext, onError);
  }
}
