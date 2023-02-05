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


import java.lang.reflect.Type;
import java.util.Map;

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
   * @param typeT the type t
   * @return the json rpc 20 response
   */
  <T> JsonRpc20Response<T> fromJson(String response, Type typeT);

  /**
   * From json json rpc 20 ws response.
   *
   * @param response the response
   * @return the json rpc 20 ws response
   */
  JsonRpc20WSResponse fromJson(String response);

  /**
   * From json general map.
   *
   * @param json the json
   * @return the map
   */
  Map<String, Object> fromJsonMap(String json);

  /**
   * From json json rpc 20 request.
   *
   * @param request the request
   * @return the json rpc 20 request
   */
  JsonRpc20Request fromJsonReq(String request);

  /**
   * To json string.
   *
   * @param request the request
   * @return the string
   */
  String toJson(JsonRpc20Request request);
}
