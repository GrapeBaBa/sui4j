package io.sui.models;

import io.sui.jsonrpc.JsonRpc20Response;


/**
 * The type Sui api exception.
 */
public class SuiApiException extends RuntimeException {

	private JsonRpc20Response.Error error;

	private Throwable cause;

	/**
	 * Instantiates a new Sui api exception.
	 *
	 * @param error the error
	 */
	public SuiApiException(JsonRpc20Response.Error error) {
		super();
		this.error = error;
	}

	public SuiApiException(Throwable cause) {
		super(cause);
	}

	/**
	 * Gets error.
	 *
	 * @return the error
	 */
	public JsonRpc20Response.Error getError() {
		return error;
	}

	public void setError(JsonRpc20Response.Error error) {
		this.error = error;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}
}
