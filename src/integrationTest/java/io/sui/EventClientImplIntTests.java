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

package io.sui;


import io.reactivex.rxjava3.functions.Consumer;
import io.sui.clients.EventClient;
import io.sui.clients.EventClientImpl;
import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventFilter;
import io.sui.models.events.EventFilter.AllEventFilter;
import io.sui.models.events.EventFilter.MoveModuleEventFilter;
import io.sui.models.events.SuiEvent;
import io.sui.models.objects.MoveModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Event client impl int tests.
 *
 * @author grapebaba
 * @since 2023.2
 */
public class EventClientImplIntTests {

  private static final String BASE_URL = "https://fullnode.devnet.sui.io:443";

  private static final JsonHandler jsonHandler = new GsonJsonHandler();

  private static EventClient eventClient;

  /** Before all. */
  @BeforeAll
  static void beforeAll() {
    JsonRpcClientProvider jsonRpcClientProvider =
        new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
    eventClient = new EventClientImpl(jsonRpcClientProvider);
  }

  /** Subscribe event. */
  @Test
  @DisplayName("Test subscribeEvent.")
  void subscribeEvent() {
    EventFilter.PackageEventFilter packageEventFilter = new EventFilter.PackageEventFilter();
    packageEventFilter.setSuiPackage("0x2");
    MoveModuleEventFilter moveModuleEventFilter = new MoveModuleEventFilter();
    MoveModule moveModule = new MoveModule();
    moveModule.setSuiPackage("0x2");
    moveModule.setModule("devnet_nft");
    moveModuleEventFilter.setModule(moveModule);
    EventFilter.AllEventFilter allEventFilter = new AllEventFilter();
    allEventFilter.getAll().add(packageEventFilter);
    allEventFilter.getAll().add(moveModuleEventFilter);
    Consumer<SuiEvent> onNext = System.out::println;

    Consumer<SuiApiException> onError = Throwable::printStackTrace;
    eventClient.subscribeEvent(allEventFilter, onNext, onError);
  }
}
