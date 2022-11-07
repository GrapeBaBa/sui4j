package io.sui.jsonrpc;


public interface JsonHandler {
	<T> JsonRpc20Response<T> fromJson(String response, Class<T> tClass);

	String toJson(JsonRpc20Request request);
}
