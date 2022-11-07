/*
 * Copyright 2022 281165273grape@gmail.com
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

/**
 * The interface Json handler.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface JsonHandler {

  /**
   * From json json rpc 20 response.
   *
   * @param <T> the type parameter
   * @param response the response
   * @param clazz the t class
   * @return the json rpc 20 response
   */
  <T> JsonRpc20Response<T> fromJson(String response, Class<T> clazz);

  /**
   * To json string.
   *
   * @param request the request
   * @return the string
   */
  String toJson(JsonRpc20Request request);
}
