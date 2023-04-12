package io.sui.clients;


import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpc20Response;
import io.sui.models.FaucetResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FaucetClient {

  private final OkHttpClient httpClient;

  private final String baseUrl;

  private final JsonHandler jsonHandler;

  public FaucetClient(OkHttpClient httpClient, String baseUrl,
      JsonHandler jsonHandler) {
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

  public CompletableFuture<FaucetResponse> requestSuiFromFaucet(String address) {
    final CompletableFuture<FaucetResponse> future = new CompletableFuture<>();
    final Request okhttpRequest;
    try {
      final String requestBodyJsonStr = String.format("{\n"
          + "      FixedAmountRequest: {\n"
          + "        %s,\n"
          + "      },\n"
          + "    }", address);
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
              public void onResponse(Call call, Response response) {
                try {
                  final FaucetResponse faucetResponse;
                  if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                      jsonRpc20Response = jsonHandler.fromJson(responseBody.string(), typeOfT);
                    } else {
                      jsonRpc20Response = new JsonRpc20Response<>();
                    }
                  } else {
                    jsonRpc20Response = new JsonRpc20Response<>();
                    JsonRpc20Response.Error error = new JsonRpc20Response.Error();
                    error.setCode(JsonRpc20Response.Error.ErrorCode.FAILURE_RESPONSE);
                    jsonRpc20Response.setError(error);
                  }
                  future.complete(jsonRpc20Response);
                } catch (Throwable throwable) {
                  future.completeExceptionally(throwable);
                }
              }
            });

    return future;
  }
}
