package io.sui;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.sui.jsonrpc.GsonJsonHandler;
import io.sui.jsonrpc.JsonHandler;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.jsonrpc.OkHttpJsonRpcClientProvider;
import io.sui.models.GetObjectResponse;
import io.sui.models.ObjectStatus;
import io.sui.models.SuiData;
import io.sui.models.SuiObject;
import io.sui.models.SuiObjectOwner;
import io.sui.models.SuiObjectRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Author: kaichen
 * Date: 2022/11/6
 * Time: 17:30
 */
class SuiClientImplTests {

	private static final String BASE_URL = "http://localhost:9000";

	@Test
	void getObject() throws ExecutionException, InterruptedException {
		JsonHandler jsonHandler = new GsonJsonHandler();
		JsonRpcClientProvider jsonRpcClientProvider = new OkHttpJsonRpcClientProvider(BASE_URL, jsonHandler);
		SuiClient client = new SuiClientImpl(jsonRpcClientProvider);
		CompletableFuture<GetObjectResponse> res = client.getObject("0xa204b49f2a65eb3d418ccae864b331c524c2fa75");
		GetObjectResponse response = res.get();
		System.out.println(response);
		assertEquals(ObjectStatus.Exists, response.getStatus());
		SuiObject suiObject = (SuiObject) response.getDetails();
		SuiData.MoveObject moveObject = (SuiData.MoveObject) suiObject.getData();
		assertEquals("0x2::coin::Coin<0x2::sui::SUI>", moveObject.getType());
		SuiObjectOwner.AddressOwner addressOwner = (SuiObjectOwner.AddressOwner) suiObject.getOwner();
		assertEquals("0xea79464d86786b7a7a63e3f13f798f29f5e65947", addressOwner.getAddressOwner());
		assertEquals(BigInteger.valueOf(100000000000000L), moveObject.getFields().get("balance"));
		SuiObjectRef suiObjectRef = suiObject.getReference();
		assertEquals("CxYsqphRB24TVGDgV/973d5+cmgZZoXpGloXw1+rPIU=", suiObjectRef.getDigest());
	}
}