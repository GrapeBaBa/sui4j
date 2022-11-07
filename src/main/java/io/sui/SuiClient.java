package io.sui;

import java.util.concurrent.CompletableFuture;

import io.sui.models.GetObjectResponse;
import io.sui.models.SuiData;
import io.sui.models.SuiObject;


public interface SuiClient {

	CompletableFuture<GetObjectResponse> getObject(String id);
}
