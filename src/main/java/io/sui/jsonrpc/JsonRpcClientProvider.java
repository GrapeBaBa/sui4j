package io.sui.jsonrpc;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

public interface JsonRpcClientProvider {
	AtomicLong nextId = new AtomicLong();

	default long nextId() {
		return nextId.incrementAndGet();
	}

	<T> CompletableFuture<JsonRpc20Response<T>> call(JsonRpc20Request request, String url, Class<T> tClass);
}
