package io.sui.jsonrpc;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Json rpc 20 request.
 */
public class JsonRpc20Request {

	private String jsonrpc = "2.0";

	private String method;

	private List<?> params;

	private long id;

	/**
	 * Gets jsonrpc.
	 *
	 * @return the jsonrpc
	 */
	String getJsonrpc() {
		return jsonrpc;
	}

	/**
	 * Sets jsonrpc.
	 *
	 * @param jsonrpc the jsonrpc
	 */
	void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	/**
	 * Gets method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets method.
	 *
	 * @param method the method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets params.
	 *
	 * @return the params
	 */
	public List<?> getParams() {
		return params;
	}

	/**
	 * Sets params.
	 *
	 * @param params the params
	 */
	public void setParams(List<?> params) {
		this.params = params;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		JsonRpc20Request that = (JsonRpc20Request) o;
		return id == that.id && jsonrpc.equals(that.jsonrpc) && method.equals(that.method) && params.equals(that.params);
	}

	@Override
	public int hashCode() {
		return Objects.hash(jsonrpc, method, params, id);
	}

	@Override
	public String toString() {
		return "JsonRpc20Request{" +
				"jsonrpc='" + jsonrpc + '\'' +
				", method='" + method + '\'' +
				", params=" + params +
				", id=" + id +
				'}';
	}

}
