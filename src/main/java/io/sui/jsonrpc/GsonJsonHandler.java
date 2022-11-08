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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.ToNumberStrategy;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.sui.models.GetObjectResponse;
import io.sui.models.SuiData;
import io.sui.models.SuiObject;
import io.sui.models.SuiObjectOwner;
import io.sui.models.SuiObjectRef;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

/**
 * The type Gson json handler.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class GsonJsonHandler implements JsonHandler {

  /** The type Error code deserializer. */
  public static class ErrorCodeDeserializer
      implements JsonDeserializer<JsonRpc20Response.Error.ErrorCode> {

    @Override
    public JsonRpc20Response.Error.ErrorCode deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return JsonRpc20Response.Error.ErrorCode.valueOfCode(json.getAsInt());
    }
  }

  /** The type Sui data deserializer. */
  public class SuiDataDeserializer implements JsonDeserializer<SuiData> {

    @Override
    public SuiData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if ("package".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiData.PackageObject.class);
      }
      if ("moveObject".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiData.MoveObject.class);
      }
      return null;
    }
  }

  /** The type Sui object owner deserializer. */
  public class SuiObjectOwnerDeserializer implements JsonDeserializer<SuiObjectOwner> {

    @Override
    public SuiObjectOwner deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonObject()) {
        if (!json.getAsJsonObject().get("AddressOwner").isJsonNull()) {
          return gson.fromJson(json, SuiObjectOwner.AddressOwner.class);
        }
        if (!json.getAsJsonObject().get("ObjectOwner").isJsonNull()) {
          return gson.fromJson(json, SuiObjectOwner.ObjectOwner.class);
        }
        if (!json.getAsJsonObject().get("Shared").isJsonNull()) {
          return gson.fromJson(json, SuiObjectOwner.SharedOwner.class);
        }
      } else {
        return SuiObjectOwner.StringSuiObjectOwner.Immutable;
      }

      return null;
    }
  }

  /** The type Get object response details deserializer. */
  public class GetObjectResponseDetailsDeserializer
      implements JsonDeserializer<GetObjectResponse.GetObjectResponseDetails> {

    @Override
    public GetObjectResponse.GetObjectResponseDetails deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonObject()) {
        if (!json.getAsJsonObject().get("data").isJsonNull()) {
          return gson.fromJson(json, SuiObject.class);
        } else {
          return gson.fromJson(json, SuiObjectRef.class);
        }
      } else {
        final GetObjectResponse.ObjectIdResponseDetails objectIdResponseDetails =
            new GetObjectResponse.ObjectIdResponseDetails();
        objectIdResponseDetails.setObjectId(json.getAsString());
        return objectIdResponseDetails;
      }
    }
  }

  /** The type Big integer to number policy. */
  public static class BigIntegerToNumberPolicy implements ToNumberStrategy {

    @Override
    public Number readNumber(JsonReader in) throws IOException {
      String value = in.nextString();

      try {
        return new BigInteger(value);
      } catch (NumberFormatException e) {
        throw new JsonParseException(
            "Cannot parse " + value + "; at path " + in.getPreviousPath(), e);
      }
    }
  }

  private final Gson gson;

  /** Instantiates a new Gson json handler. */
  public GsonJsonHandler() {
    this.gson =
        new GsonBuilder()
            .setObjectToNumberStrategy(new BigIntegerToNumberPolicy())
            .setNumberToNumberStrategy(new BigIntegerToNumberPolicy())
            .registerTypeAdapter(
                JsonRpc20Response.Error.ErrorCode.class, new ErrorCodeDeserializer())
            .registerTypeAdapter(SuiObjectOwner.class, new SuiObjectOwnerDeserializer())
            .registerTypeAdapter(SuiData.class, new SuiDataDeserializer())
            .registerTypeAdapter(
                GetObjectResponse.GetObjectResponseDetails.class,
                new GetObjectResponseDetailsDeserializer())
            .create();
  }

  @Override
  public <T> JsonRpc20Response<T> fromJson(String response, Type typeOfT) {
    Type type = TypeToken.getParameterized(JsonRpc20Response.class, typeOfT).getType();
    return this.gson.fromJson(response, type);
  }

  @Override
  public String toJson(JsonRpc20Request request) {
    return this.gson.toJson(request);
  }
}
