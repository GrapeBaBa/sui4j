/*
 * Copyright 281165273grape@gmail.com
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

package io.sui.jsonrpc;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Json rpc 20 response.
 *
 * @param <T> the type parameter
 * @author grapebaba
 * @since 2022.11
 */
public class JsonRpc20Response<T> {

  /**
   * The type Error.
   */
  public static class Error {

    /**
     * The enum Error code.
     */
    public enum ErrorCode {

      /**
       * Parse error error code.
       */
      PARSE_ERROR(-32700),

      /**
       * Invalid json request error code.
       */
      INVALID_JSON_REQUEST(-32600),

      /**
       * Method not found error code.
       */
      METHOD_NOT_FOUND(-32601),

      /**
       * Invalid params error code.
       */
      INVALID_PARAMS(-32602),

      /**
       * Internal error error code.
       */
      INTERNAL_ERROR(-32603),

      /**
       * Procedure is method error code.
       */
      PROCEDURE_IS_METHOD(-32604),

      /**
       * Failure response error code.
       */
      FAILURE_RESPONSE(-40000),

      /**
       * Io error error code.
       */
      IO_ERROR(-40001);

      private static final Map<Integer, ErrorCode> BY_CODE = new HashMap<>();

      static {
        for (ErrorCode e : values()) {
          BY_CODE.put(e.code, e);
        }
      }

      private final int code;


      ErrorCode(int code) {
        this.code = code;
      }

      /**
       * Gets code.
       *
       * @return the code
       */
      public int getCode() {
        return code;
      }

      /**
       * Value of code error code.
       *
       * @param code the code
       * @return the error code
       */
      public static ErrorCode valueOfCode(int code) {
        return BY_CODE.get(code);
      }

      @Override
      public String toString() {
        return "ErrorCode{"
            + "code=" + code + '}';
      }

    }

    private ErrorCode code;

    private String message;

    /**
     * Gets code.
     *
     * @return the code
     */
    public ErrorCode getCode() {
      return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(ErrorCode code) {
      this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
      return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
      this.message = message;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Error error = (Error) o;
      return code == error.code && message.equals(error.message);
    }

    @Override
    public int hashCode() {
      return Objects.hash(code, message);
    }

    @Override
    public String toString() {
      return "Error{"
          + "code=" + code
          + ", message='"
          + message + '\''
          + '}';
    }
  }


  private long id;

  private String jsonrpc = "2.0";

  private T result;

  private Error error;

  private Throwable throwable;

  /**
   * Gets throwable.
   *
   * @return the throwable
   */
  public Throwable getThrowable() {
    return throwable;
  }

  /**
   * Sets throwable.
   *
   * @param throwable the throwable
   */
  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
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

  /**
   * Gets jsonrpc.
   *
   * @return the jsonrpc
   */
  public String getJsonrpc() {
    return jsonrpc;
  }

  /**
   * Sets jsonrpc.
   *
   * @param jsonrpc the jsonrpc
   */
  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  /**
   * Gets result.
   *
   * @return the result
   */
  public T getResult() {
    return result;
  }

  /**
   * Sets result.
   *
   * @param result the result
   */
  public void setResult(T result) {
    this.result = result;
  }

  /**
   * Gets error.
   *
   * @return the error
   */
  public Error getError() {
    return error;
  }

  /**
   * Sets error.
   *
   * @param error the error
   */
  public void setError(Error error) {
    this.error = error;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonRpc20Response<?> that = (JsonRpc20Response<?>) o;
    return id == that.id && jsonrpc.equals(that.jsonrpc) && result.equals(that.result)
        && error.equals(that.error) && throwable.equals(that.throwable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, jsonrpc, result, error, throwable);
  }

  @Override
  public String toString() {
    return "JsonRpc20Response{"
        + "id=" + id
        + ", jsonrpc='" + jsonrpc + '\''
        + ", result=" + result
        + ", error=" + error
        + ", throwable=" + throwable
        + '}';
  }

}
