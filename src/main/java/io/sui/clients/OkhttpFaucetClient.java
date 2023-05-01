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


import io.sui.json.JsonHandler;
import io.sui.models.FaucetResponse;
import io.sui.models.SuiApiException;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

/**
 * The type Okhttp faucet client.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class OkhttpFaucetClient implements FaucetClient {

  private final OkHttpClient httpClient;

  private final String baseUrl;

  private final JsonHandler jsonHandler;

  /**
   * Instantiates a new Okhttp faucet client.
   *
   * @param baseUrl the base url
   * @param jsonHandler the json handler
   */
  public OkhttpFaucetClient(String baseUrl, JsonHandler jsonHandler) {
    this.jsonHandler = jsonHandler;
    this.httpClient =
        new OkHttpClient()
            .newBuilder()
            .pingInterval(Duration.ofSeconds(15))
            .writeTimeout(Duration.ofSeconds(15))
            .readTimeout(Duration.ofSeconds(15))
            .build();
    this.baseUrl = baseUrl;
  }

  /**
   * request faucet.
   *
   * @param address the address.
   * @return FaucetResponse.
   */
  public CompletableFuture<FaucetResponse> requestSuiFromFaucet(String address) {
    final CompletableFuture<FaucetResponse> future = new CompletableFuture<>();
    final Request okhttpRequest;
    try {
      final String requestBodyJsonStr =
          String.format("{\"FixedAmountRequest\": {\"recipient\": \"%s\"}}", address);
      final RequestBody requestBody =
          RequestBody.create(requestBodyJsonStr, MediaType.get("application/json; charset=utf-8"));
      okhttpRequest =
          new Request.Builder()
              .url(String.format("%s/gas", this.baseUrl))
              .post(requestBody)
              .build();
    } catch (Throwable throwable) {
      future.completeExceptionally(throwable);
      return future;
    }

    this.httpClient
        .newCall(okhttpRequest)
        .enqueue(
            new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
              }

              @Override
              public void onResponse(Call call, @NotNull Response response) {
                try {
                  final ResponseBody responseBody = response.body();
                  final FaucetResponse faucetResponse;
                  if (response.isSuccessful()) {
                    faucetResponse =
                        jsonHandler.fromJsonFaucet(Objects.requireNonNull(responseBody).string());
                    future.complete(faucetResponse);
                  } else if (response.code() == 403) {
                    future.completeExceptionally(new SuiApiException(new HttpForbiddenException()));
                  }
                } catch (Throwable throwable) {
                  future.completeExceptionally(throwable);
                }
              }
            });

    return future;
  }
}
