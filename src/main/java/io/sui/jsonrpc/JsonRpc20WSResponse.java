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

package io.sui.jsonrpc;


import io.sui.models.events.SuiEvent;
import java.util.Objects;

/**
 * The type JsonRpc20 websocket response.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class JsonRpc20WSResponse {

  /** The type Params. */
  public static class Params {

    private Long subscription;

    private SuiEvent result;

    /**
     * Gets subscription.
     *
     * @return the subscription
     */
    public Long getSubscription() {
      return subscription;
    }

    /**
     * Sets subscription.
     *
     * @param subscription the subscription
     */
    public void setSubscription(Long subscription) {
      this.subscription = subscription;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public SuiEvent getResult() {
      return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(SuiEvent result) {
      this.result = result;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Params)) {
        return false;
      }
      Params params = (Params) o;
      return subscription.equals(params.subscription) && result.equals(params.result);
    }

    @Override
    public int hashCode() {
      return Objects.hash(subscription, result);
    }

    @Override
    public String toString() {
      return "Params{" + "subscription=" + subscription + ", result=" + result + '}';
    }
  }

  private String jsonrpc;

  private String method;

  private Params params;

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
  public Params getParams() {
    return params;
  }

  /**
   * Sets params.
   *
   * @param params the params
   */
  public void setParams(Params params) {
    this.params = params;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JsonRpc20WSResponse)) {
      return false;
    }
    JsonRpc20WSResponse that = (JsonRpc20WSResponse) o;
    return jsonrpc.equals(that.jsonrpc) && method.equals(that.method) && params.equals(that.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jsonrpc, method, params);
  }

  @Override
  public String toString() {
    return "JsonRpc20WSResponse{"
        + "jsonrpc='"
        + jsonrpc
        + '\''
        + ", method='"
        + method
        + '\''
        + ", params="
        + params
        + '}';
  }
}
