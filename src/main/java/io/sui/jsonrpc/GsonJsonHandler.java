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


import com.google.common.primitives.Bytes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import io.sui.models.CommitteeInfo;
import io.sui.models.events.EventKind;
import io.sui.models.events.EventQuery;
import io.sui.models.events.EventQuery.AllQuery;
import io.sui.models.events.MoveModule;
import io.sui.models.objects.InputObjectKind;
import io.sui.models.objects.InputObjectKind.ImmOrOwnedMoveObjectKind;
import io.sui.models.objects.InputObjectKind.MovePackageKind;
import io.sui.models.objects.InputObjectKind.SharedMoveObjectKind;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.ObjectValueKindMoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.PureFunctionMoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedTypeParameterType;
import io.sui.models.objects.MoveNormalizedType.MutableReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.ReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.TypeMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.VectorReferenceMoveNormalizedType;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectResponse.ObjectIdAndVersionResponseDetails;
import io.sui.models.objects.ObjectResponse.ObjectResponseDetails;
import io.sui.models.objects.SuiData;
import io.sui.models.objects.SuiObject;
import io.sui.models.objects.SuiObjectOwner;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.transactions.AuthorityQuorumSignInfo;
import io.sui.models.transactions.ExecuteTransactionResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.EffectsCertResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.ImmediateReturnResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.TxCertResponse;
import io.sui.models.transactions.MoveCall;
import io.sui.models.transactions.MoveFunction;
import io.sui.models.transactions.ParsedPublishResponse;
import io.sui.models.transactions.ParsedTransactionResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedMergeCoinResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedPublishResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedSplitCoinResponseKind;
import io.sui.models.transactions.TransactionKind;
import io.sui.models.transactions.TransactionQuery;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (json.getAsJsonObject().get("AddressOwner") != null
            && !json.getAsJsonObject().get("AddressOwner").isJsonNull()) {
          return gson.fromJson(json, SuiObjectOwner.AddressOwner.class);
        }
        if (json.getAsJsonObject().get("ObjectOwner") != null
            && !json.getAsJsonObject().get("ObjectOwner").isJsonNull()) {
          return gson.fromJson(json, SuiObjectOwner.ObjectOwner.class);
        }
        if (json.getAsJsonObject().get("Shared") != null
            && !json.getAsJsonObject().get("Shared").isJsonNull()) {
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
      implements JsonDeserializer<ObjectResponseDetails> {

    @Override
    public ObjectResponseDetails deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonObject()) {
        if (json.getAsJsonObject().get("data") != null
            && !json.getAsJsonObject().get("data").isJsonNull()) {
          return gson.fromJson(json, SuiObject.class);
        } else if (json.getAsJsonObject().get("asked_version") != null
            && !json.getAsJsonObject().get("asked_version").isJsonNull()) {
          return gson.fromJson(json, ObjectResponse.ObjectIdHigherVersionResponseDetails.class);
        } else {
          return gson.fromJson(json, SuiObjectRef.class);
        }
      } else if (json.isJsonArray()) {
        final ObjectResponse.ObjectIdAndVersionResponseDetails objectIdAndVersionResponseDetails =
            new ObjectIdAndVersionResponseDetails();
        objectIdAndVersionResponseDetails.setObjectId(json.getAsJsonArray().get(0).getAsString());
        objectIdAndVersionResponseDetails.setVersion(
            json.getAsJsonArray().get(1) != null ? json.getAsJsonArray().get(1).getAsLong() : null);
        return objectIdAndVersionResponseDetails;
      } else {
        final ObjectResponse.ObjectIdResponseDetails objectIdResponseDetails =
            new ObjectResponse.ObjectIdResponseDetails();
        objectIdResponseDetails.setObjectId(json.getAsString());
        return objectIdResponseDetails;
      }
    }
  }

  /** The type Event kind deserializer. */
  public class EventKindDeserializer implements JsonDeserializer<EventKind> {

    @Override
    public EventKind deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("moveEvent") != null
          && !json.getAsJsonObject().get("moveEvent").isJsonNull()) {
        return gson.fromJson(json, EventKind.MoveEventKind.class);
      }
      if (json.getAsJsonObject().get("publish") != null
          && !json.getAsJsonObject().get("publish").isJsonNull()) {
        return gson.fromJson(json, EventKind.PublishEventKind.class);
      }
      if (json.getAsJsonObject().get("coinBalanceChange") != null
          && !json.getAsJsonObject().get("coinBalanceChange").isJsonNull()) {
        return gson.fromJson(json, EventKind.CoinBalanceChangeEventKind.class);
      }
      if (json.getAsJsonObject().get("transferObject") != null
          && !json.getAsJsonObject().get("transferObject").isJsonNull()) {
        return gson.fromJson(json, EventKind.TransferObjectEventKind.class);
      }
      if (json.getAsJsonObject().get("mutateObject") != null
          && !json.getAsJsonObject().get("mutateObject").isJsonNull()) {
        return gson.fromJson(json, EventKind.MutateObjectEventKind.class);
      }
      if (json.getAsJsonObject().get("deleteObject") != null
          && !json.getAsJsonObject().get("deleteObject").isJsonNull()) {
        return gson.fromJson(json, EventKind.DeleteObjectEventKind.class);
      }
      if (json.getAsJsonObject().get("newObject") != null
          && !json.getAsJsonObject().get("newObject").isJsonNull()) {
        return gson.fromJson(json, EventKind.NewObjectEventKind.class);
      }
      if (json.getAsJsonObject().get("epochChange") != null
          && !json.getAsJsonObject().get("epochChange").isJsonNull()) {
        return gson.fromJson(json, EventKind.EpochChangeEventKind.class);
      }
      if (json.getAsJsonObject().get("checkpoint") != null
          && !json.getAsJsonObject().get("checkpoint").isJsonNull()) {
        return gson.fromJson(json, EventKind.CheckpointEventKind.class);
      }
      return null;
    }
  }

  /** The type Move call deserializer. */
  public class MoveCallDeserializer implements JsonDeserializer<MoveCall> {

    @Override
    public MoveCall deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      MoveCall moveCall = new MoveCall();
      List<?> arguments =
          gson.fromJson(
              json.getAsJsonObject().get("arguments"),
              new com.google.common.reflect.TypeToken<List<?>>() {}.getType());
      moveCall.setArguments(arguments);
      moveCall.setModule(json.getAsJsonObject().get("module").getAsString());
      moveCall.setFunction(json.getAsJsonObject().get("function").getAsString());
      List<String> typeArguments =
          gson.fromJson(
              json.getAsJsonObject().get("typeArguments"),
              new com.google.common.reflect.TypeToken<List<String>>() {}.getType());
      moveCall.setTypeArguments(typeArguments);
      SuiObjectRef suiPackage =
          gson.fromJson(json.getAsJsonObject().get("package"), SuiObjectRef.class);
      moveCall.setSuiPackage(suiPackage);
      return moveCall;
    }
  }

  /** The type Parsed transaction response kind deserializer. */
  public class ParsedTransactionResponseKindDeserializer
      implements JsonDeserializer<ParsedTransactionResponseKind> {

    @Override
    public ParsedTransactionResponseKind deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("Publish") != null
          && !json.getAsJsonObject().get("Publish").isJsonNull()) {
        return gson.fromJson(json, ParsedPublishResponseKind.class);
      }
      if (json.getAsJsonObject().get("SplitCoin") != null
          && !json.getAsJsonObject().get("SplitCoin").isJsonNull()) {
        return gson.fromJson(json, ParsedSplitCoinResponseKind.class);
      }
      if (json.getAsJsonObject().get("MergeCoin") != null
          && !json.getAsJsonObject().get("MergeCoin").isJsonNull()) {
        return gson.fromJson(json, ParsedMergeCoinResponseKind.class);
      }
      return null;
    }
  }

  /** The type Parsed publish response deserializer. */
  public class ParsedPublishResponseDeserializer
      implements JsonDeserializer<ParsedPublishResponse> {

    @Override
    public ParsedPublishResponse deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      ParsedPublishResponse parsedPublishResponse = new ParsedPublishResponse();
      SuiObject updatedGas =
          gson.fromJson(json.getAsJsonObject().get("updatedGas"), SuiObject.class);
      parsedPublishResponse.setUpdatedGas(updatedGas);
      List<SuiObject> createdObjects =
          gson.fromJson(
              json.getAsJsonObject().get("createdObjects"),
              new com.google.common.reflect.TypeToken<List<SuiObject>>() {}.getType());
      parsedPublishResponse.setCreatedObjects(createdObjects);
      SuiObjectRef suiPackage =
          gson.fromJson(json.getAsJsonObject().get("package"), SuiObjectRef.class);
      parsedPublishResponse.setSuiPackage(suiPackage);
      return parsedPublishResponse;
    }
  }

  /** The type Transaction kind deserializer. */
  public class TransactionKindDeserializer implements JsonDeserializer<TransactionKind> {

    @Override
    public TransactionKind deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("TransferObject") != null
          && !json.getAsJsonObject().get("TransferObject").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.TransferObjectTransactionKind.class);
      }
      if (json.getAsJsonObject().get("Publish") != null
          && !json.getAsJsonObject().get("Publish").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.PublishTransactionKind.class);
      }
      if (json.getAsJsonObject().get("Call") != null
          && !json.getAsJsonObject().get("Call").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.CallTransactionKind.class);
      }
      if (json.getAsJsonObject().get("TransferSui") != null
          && !json.getAsJsonObject().get("TransferSui").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.TransferSuiTransactionKind.class);
      }
      if (json.getAsJsonObject().get("ChangeEpoch") != null
          && !json.getAsJsonObject().get("ChangeEpoch").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.ChangeEpochTransactionKind.class);
      }
      if (json.getAsJsonObject().get("Pay") != null
          && !json.getAsJsonObject().get("Pay").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.PayTransactionKind.class);
      }
      if (json.getAsJsonObject().get("PaySui") != null
          && !json.getAsJsonObject().get("PaySui").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.PaySuiTransactionKind.class);
      }
      if (json.getAsJsonObject().get("PayAllSui") != null
          && !json.getAsJsonObject().get("PayAllSui").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.PayAllSuiTransactionKind.class);
      }
      return null;
    }
  }

  /** The type Authority quorum sign info deserializer. */
  public static class AuthorityQuorumSignInfoDeserializer
      implements JsonDeserializer<AuthorityQuorumSignInfo> {

    @Override
    public AuthorityQuorumSignInfo deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      AuthorityQuorumSignInfo authorityQuorumSignInfo = new AuthorityQuorumSignInfo();
      authorityQuorumSignInfo.setEpoch(json.getAsJsonObject().get("epoch").getAsLong());
      authorityQuorumSignInfo.setSignersMap(
          Bytes.toArray(
              json.getAsJsonObject().get("signers_map").getAsJsonArray().asList().stream()
                  .map(JsonElement::getAsByte)
                  .collect(Collectors.toList())));
      List<String> signatures = new ArrayList<>();
      if (json.getAsJsonObject().get("signature") != null
          && json.getAsJsonObject().get("signature").isJsonPrimitive()) {
        String signature = json.getAsJsonObject().get("signature").getAsString();
        signatures.add(signature);
      } else {
        signatures.addAll(
            json.getAsJsonObject().get("signature").getAsJsonArray().asList().stream()
                .map(JsonElement::getAsString)
                .collect(Collectors.toList()));
      }
      authorityQuorumSignInfo.setSignature(signatures);
      return authorityQuorumSignInfo;
    }
  }

  /** The type Move module serializer. */
  public static class MoveModuleSerializer implements JsonSerializer<MoveModule> {

    @Override
    public JsonElement serialize(MoveModule src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("package", src.getSuiPackage());
      jsonObject.addProperty("module", src.getModule());
      return jsonObject;
    }
  }

  /** The type Move function serializer. */
  public static class MoveFunctionSerializer implements JsonSerializer<MoveFunction> {

    @Override
    public JsonElement serialize(
        MoveFunction src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("package", src.getSuiPackage());
      jsonObject.addProperty("module", src.getModule());
      jsonObject.addProperty("function", src.getFunction());
      return jsonObject;
    }
  }

  /** The type Event query serializer. */
  public class EventQuerySerializer implements JsonSerializer<EventQuery> {

    @Override
    public JsonElement serialize(EventQuery src, Type typeOfSrc, JsonSerializationContext context) {
      if (src instanceof EventQuery.AllQuery) {
        return new JsonPrimitive(AllQuery.All.name());
      }

      return gson.toJsonTree(src, typeOfSrc);
    }
  }

  /** The type Committee info deserializer. */
  public static class CommitteeInfoDeserializer implements JsonDeserializer<CommitteeInfo> {

    @Override
    public CommitteeInfo deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      List<JsonElement> committeeInfoStr = json.getAsJsonArray().asList();
      CommitteeInfo committeeInfo = new CommitteeInfo();
      committeeInfo.setAuthorityName(committeeInfoStr.get(0).getAsString());
      committeeInfo.setStakeUnit(committeeInfoStr.get(1).getAsLong());
      return committeeInfo;
    }
  }

  /** The type Move normalized type deserializer. */
  public class MoveNormalizedTypeDeserializer implements JsonDeserializer<MoveNormalizedType> {

    @Override
    public MoveNormalizedType deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonPrimitive()) {
        return TypeMoveNormalizedType.valueOf(json.getAsString());
      }
      if (json.isJsonObject()) {
        if (json.getAsJsonObject().get("TypeParameter") != null
            && !json.getAsJsonObject().get("TypeParameter").isJsonNull()) {
          return gson.fromJson(json, MoveNormalizedTypeParameterType.class);
        }
        if (json.getAsJsonObject().get("Reference") != null
            && !json.getAsJsonObject().get("Reference").isJsonNull()) {
          return gson.fromJson(json, ReferenceMoveNormalizedType.class);
        }
        if (json.getAsJsonObject().get("MutableReference") != null
            && !json.getAsJsonObject().get("MutableReference").isJsonNull()) {
          return gson.fromJson(json, MutableReferenceMoveNormalizedType.class);
        }
        if (json.getAsJsonObject().get("Vector") != null
            && !json.getAsJsonObject().get("Vector").isJsonNull()) {
          return gson.fromJson(json, VectorReferenceMoveNormalizedType.class);
        }
        if (json.getAsJsonObject().get("Struct") != null
            && !json.getAsJsonObject().get("Struct").isJsonNull()) {
          return gson.fromJson(json, MoveNormalizedStructType.class);
        }
      }
      return null;
    }
  }

  /** The type Move function arg type deserializer. */
  public class MoveFunctionArgTypeDeserializer implements JsonDeserializer<MoveFunctionArgType> {

    @Override
    public MoveFunctionArgType deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonPrimitive()) {
        return PureFunctionMoveFunctionArgType.Pure;
      }

      if (json.isJsonObject()) {
        return gson.fromJson(json, ObjectValueKindMoveFunctionArgType.class);
      }

      return null;
    }
  }

  /** The type Input object kind deserializer. */
  public class InputObjectKindDeserializer implements JsonDeserializer<InputObjectKind> {

    @Override
    public InputObjectKind deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("ImmOrOwnedMoveObject") != null
          && !json.getAsJsonObject().get("ImmOrOwnedMoveObject").isJsonNull()) {
        return gson.fromJson(json, ImmOrOwnedMoveObjectKind.class);
      }
      if (json.getAsJsonObject().get("SharedMoveObject") != null
          && !json.getAsJsonObject().get("SharedMoveObject").isJsonNull()) {
        return gson.fromJson(json, SharedMoveObjectKind.class);
      }
      if (json.getAsJsonObject().get("MovePackage") != null
          && !json.getAsJsonObject().get("MovePackage").isJsonNull()) {
        return gson.fromJson(json, MovePackageKind.class);
      }
      return null;
    }
  }

  /** The type Transaction query serializer. */
  public class TransactionQuerySerializer implements JsonSerializer<TransactionQuery> {

    @Override
    public JsonElement serialize(
        TransactionQuery src, Type typeOfSrc, JsonSerializationContext context) {
      if (src instanceof TransactionQuery.AllQuery) {
        return new JsonPrimitive(AllQuery.All.name());
      }

      return gson.toJsonTree(src, typeOfSrc);
    }
  }

  /** The type Execute transaction response deserializer. */
  public class ExecuteTransactionResponseDeserializer
      implements JsonDeserializer<ExecuteTransactionResponse> {

    @Override
    public ExecuteTransactionResponse deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("ImmediateReturn") != null
          && !json.getAsJsonObject().get("ImmediateReturn").isJsonNull()) {
        return gson.fromJson(json, ImmediateReturnResponse.class);
      }
      if (json.getAsJsonObject().get("TxCert") != null
          && !json.getAsJsonObject().get("TxCert").isJsonNull()) {
        return gson.fromJson(json, TxCertResponse.class);
      }
      if (json.getAsJsonObject().get("EffectsCert") != null
          && !json.getAsJsonObject().get("EffectsCert").isJsonNull()) {
        return gson.fromJson(json, EffectsCertResponse.class);
      }
      return null;
    }
  }

  private final Gson gson;

  /** Instantiates a new Gson json handler. */
  public GsonJsonHandler() {
    this.gson =
        new GsonBuilder()
            .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .registerTypeAdapter(
                JsonRpc20Response.Error.ErrorCode.class, new ErrorCodeDeserializer())
            .registerTypeAdapter(SuiObjectOwner.class, new SuiObjectOwnerDeserializer())
            .registerTypeAdapter(SuiData.class, new SuiDataDeserializer())
            .registerTypeAdapter(
                ObjectResponseDetails.class, new GetObjectResponseDetailsDeserializer())
            .registerTypeAdapter(EventKind.class, new EventKindDeserializer())
            .registerTypeAdapter(MoveCall.class, new MoveCallDeserializer())
            .registerTypeAdapter(
                ParsedTransactionResponseKind.class,
                new ParsedTransactionResponseKindDeserializer())
            .registerTypeAdapter(
                ParsedPublishResponse.class, new ParsedPublishResponseDeserializer())
            .registerTypeAdapter(TransactionKind.class, new TransactionKindDeserializer())
            .registerTypeAdapter(
                AuthorityQuorumSignInfo.class, new AuthorityQuorumSignInfoDeserializer())
            .registerTypeAdapter(MoveModule.class, new MoveModuleSerializer())
            .registerTypeAdapter(EventQuery.class, new EventQuerySerializer())
            .registerTypeAdapter(MoveNormalizedType.class, new MoveNormalizedTypeDeserializer())
            .registerTypeAdapter(CommitteeInfo.class, new CommitteeInfoDeserializer())
            .registerTypeAdapter(MoveFunctionArgType.class, new MoveFunctionArgTypeDeserializer())
            .registerTypeAdapter(InputObjectKind.class, new InputObjectKindDeserializer())
            .registerTypeAdapter(TransactionQuery.class, new TransactionQuerySerializer())
            .registerTypeAdapter(MoveFunction.class, new MoveFunctionSerializer())
            .registerTypeAdapter(
                ExecuteTransactionResponse.class, new ExecuteTransactionResponseDeserializer())
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

  /**
   * Gets gson.
   *
   * @return the gson
   */
  public Gson getGson() {
    return gson;
  }
}
