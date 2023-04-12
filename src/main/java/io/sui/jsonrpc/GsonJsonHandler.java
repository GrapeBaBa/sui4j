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
import io.sui.models.FaucetResponse;
import io.sui.models.events.EventFilter;
import io.sui.models.events.EventFilter.PackageEventFilter;
import io.sui.models.events.EventKind;
import io.sui.models.events.EventQuery;
import io.sui.models.events.EventQuery.AllQuery;
import io.sui.models.objects.CommitteeInfo;
import io.sui.models.objects.InputObjectKind;
import io.sui.models.objects.InputObjectKind.ImmOrOwnedMoveObjectKind;
import io.sui.models.objects.InputObjectKind.MovePackageKind;
import io.sui.models.objects.InputObjectKind.SharedMoveObjectKind;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.ObjectValueKindMoveFunctionArgType;
import io.sui.models.objects.MoveFunctionArgType.PureFunctionMoveFunctionArgType;
import io.sui.models.objects.MoveModule;
import io.sui.models.objects.MoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedStructType;
import io.sui.models.objects.MoveNormalizedType.MoveNormalizedTypeParameterType;
import io.sui.models.objects.MoveNormalizedType.MutableReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.ReferenceMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.TypeMoveNormalizedType;
import io.sui.models.objects.MoveNormalizedType.VectorReferenceMoveNormalizedType;
import io.sui.models.objects.ObjectChange;
import io.sui.models.objects.ObjectChange.ObjectChangeType;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectStatus;
import io.sui.models.objects.SuiObjectData;
import io.sui.models.objects.SuiObjectOwner;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiParsedData;
import io.sui.models.objects.SuiRawData;
import io.sui.models.transactions.Argument;
import io.sui.models.transactions.Argument.NestedResult;
import io.sui.models.transactions.Argument.NestedResultArgument;
import io.sui.models.transactions.AuthorityQuorumSignInfo;
import io.sui.models.transactions.Command;
import io.sui.models.transactions.Command.MakeMoveVec;
import io.sui.models.transactions.Command.MakeMoveVecCommand;
import io.sui.models.transactions.Command.MergeCoins;
import io.sui.models.transactions.Command.MergeCoinsCommand;
import io.sui.models.transactions.Command.MoveCall;
import io.sui.models.transactions.Command.MoveCallCommand;
import io.sui.models.transactions.Command.PublishCommand;
import io.sui.models.transactions.Command.SplitCoin;
import io.sui.models.transactions.Command.SplitCoinCommand;
import io.sui.models.transactions.Command.TransferObjects;
import io.sui.models.transactions.Command.TransferObjectsCommand;
import io.sui.models.transactions.ExecuteTransactionResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.EffectsCertResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.ImmediateReturnResponse;
import io.sui.models.transactions.ExecuteTransactionResponse.TxCertResponse;
import io.sui.models.transactions.MoveFunction;
import io.sui.models.transactions.ParsedPublishResponse;
import io.sui.models.transactions.ParsedTransactionResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedMergeCoinResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedPublishResponseKind;
import io.sui.models.transactions.ParsedTransactionResponseKind.ParsedSplitCoinResponseKind;
import io.sui.models.transactions.TransactionKind;
import io.sui.models.transactions.TypeTag;
import io.sui.models.transactions.TypeTag.StructType;
import io.sui.models.transactions.TypeTag.VectorType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
  public class SuiRawDataDeserializer implements JsonDeserializer<SuiRawData> {

    @Override
    public SuiRawData deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if ("package".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiRawData.PackageObject.class);
      }
      if ("moveObject".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiRawData.MoveObject.class);
      }
      return null;
    }
  }

  /** The type Sui parsed data deserializer. */
  public class SuiParsedDataDeserializer implements JsonDeserializer<SuiParsedData> {

    @Override
    public SuiParsedData deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if ("package".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiParsedData.PackageObject.class);
      }
      if ("moveObject".equals(json.getAsJsonObject().get("dataType").getAsString())) {
        return gson.fromJson(json, SuiParsedData.MoveObject.class);
      }
      return null;
    }
  }

  /** The type Sui argument deserializer. */
  public class SuiArgumentDeserializer implements JsonDeserializer<Argument> {

    @Override
    public Argument deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonObject()) {
        if (json.getAsJsonObject().get("Input") != null
            && !json.getAsJsonObject().get("Input").isJsonNull()) {
          return gson.fromJson(json, Argument.InputArgument.class);
        }
        if (json.getAsJsonObject().get("Result") != null
            && !json.getAsJsonObject().get("Result").isJsonNull()) {
          return gson.fromJson(json, Argument.ResultArgument.class);
        }
        if (json.getAsJsonObject().get("NestedResult") != null
            && !json.getAsJsonObject().get("NestedResult").isJsonNull()) {
          Argument.NestedResult nestedResult = new NestedResult();
          nestedResult.setField0(
              json.getAsJsonObject().getAsJsonArray("NestedResult").get(0).getAsShort());
          nestedResult.setField1(
              json.getAsJsonObject().getAsJsonArray("NestedResult").get(1).getAsShort());

          NestedResultArgument nestedResultArgument = new NestedResultArgument();
          nestedResultArgument.setNestedResult(nestedResult);
          return nestedResultArgument;
        }
      } else {
        return Argument.GasCoinArgument.GasCoin;
      }
      return null;
    }
  }

  /** The type Object change deserializer. */
  public class ObjectChangeDeserializer implements JsonDeserializer<ObjectChange> {

    @Override
    public ObjectChange deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("type") != null
          && !json.getAsJsonObject().get("type").isJsonNull()) {
        if (ObjectChangeType.published
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangePublished.class);
        }
        if (ObjectChangeType.transferred
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangeTransferred.class);
        }
        if (ObjectChangeType.mutated
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangeMutated.class);
        }
        if (ObjectChangeType.deleted
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangeDeleted.class);
        }
        if (ObjectChangeType.wrapped
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangeWrapped.class);
        }
        if (ObjectChangeType.created
            .toString()
            .equals(json.getAsJsonObject().get("type").getAsString())) {
          return gson.fromJson(json, ObjectChange.ObjectChangeCreated.class);
        }
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
  public class GetObjectResponseDeserializer implements JsonDeserializer<ObjectResponse> {

    @Override
    public ObjectResponse deserialize(
        JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.isJsonObject()) {
        if (json.getAsJsonObject().get("status").getAsString().equals(ObjectStatus.Exists.name())) {
          ObjectResponse objectResponse = new ObjectResponse();
          objectResponse.setStatus(ObjectStatus.Exists);
          objectResponse.setDetails(
              gson.fromJson(json.getAsJsonObject().get("details"), SuiObjectData.class));
          return objectResponse;
        } else if (json.getAsJsonObject()
            .get("status")
            .getAsString()
            .equals(ObjectStatus.NotExists.name())) {
          ObjectResponse objectResponse = new ObjectResponse();
          objectResponse.setStatus(ObjectStatus.NotExists);
          objectResponse.setDetails(
              gson.fromJson(
                  json.getAsJsonObject().get("details"),
                  ObjectResponse.ObjectIdResponseDetails.class));
          return objectResponse;
        } else if (json.getAsJsonObject()
            .get("status")
            .getAsString()
            .equals(ObjectStatus.Deleted.name())) {
          ObjectResponse objectResponse = new ObjectResponse();
          objectResponse.setStatus(ObjectStatus.Deleted);
          objectResponse.setDetails(
              gson.fromJson(json.getAsJsonObject().get("details"), SuiObjectRef.class));
          return objectResponse;
        }
      }
      return null;
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
      List<Argument> arguments =
          gson.fromJson(
              json.getAsJsonObject().get("arguments"),
              new com.google.common.reflect.TypeToken<List<Argument>>() {}.getType());
      moveCall.setArguments(arguments);
      moveCall.setModule(json.getAsJsonObject().get("module").getAsString());
      moveCall.setFunction(json.getAsJsonObject().get("function").getAsString());
      List<String> typeArguments =
          gson.fromJson(
              json.getAsJsonObject().get("type_arguments"),
              new com.google.common.reflect.TypeToken<List<String>>() {}.getType());
      moveCall.setTypeArguments(typeArguments);
      String suiPackage = json.getAsJsonObject().get("package").getAsString();
      moveCall.setSuiPackage(suiPackage);
      return moveCall;
    }
  }

  /** The type Sui command deserializer. */
  public class SuiCommandDeserializer implements JsonDeserializer<Command> {

    @Override
    public Command deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (json.getAsJsonObject().get("MoveCall") != null
          && !json.getAsJsonObject().get("MoveCall").isJsonNull()) {
        return gson.fromJson(json, MoveCallCommand.class);
      }
      if (json.getAsJsonObject().get("TransferObjects") != null
          && !json.getAsJsonObject().get("TransferObjects").isJsonNull()) {
        Command.TransferObjects transferObjects = new TransferObjects();
        List<Argument> field0 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("TransferObjects").get(0),
                new com.google.common.reflect.TypeToken<List<Argument>>() {}.getType());
        Argument field1 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("TransferObjects").get(1),
                new com.google.common.reflect.TypeToken<Argument>() {}.getType());
        transferObjects.setField0(field0);
        transferObjects.setField1(field1);
        TransferObjectsCommand transferObjectsCommand = new TransferObjectsCommand();
        transferObjectsCommand.setTransferObjects(transferObjects);
        return transferObjectsCommand;
      }
      if (json.getAsJsonObject().get("SplitCoin") != null
          && !json.getAsJsonObject().get("SplitCoin").isJsonNull()) {
        Command.SplitCoin splitCoin = new SplitCoin();
        Argument field0 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("SplitCoin").get(0),
                new com.google.common.reflect.TypeToken<Argument>() {}.getType());
        splitCoin.setField0(field0);
        splitCoin.setAddress(
            json.getAsJsonObject().getAsJsonArray("SplitCoin").get(1).getAsString());
        SplitCoinCommand splitCoinCommand = new SplitCoinCommand();
        splitCoinCommand.setSplitCoin(splitCoin);
        return splitCoinCommand;
      }
      if (json.getAsJsonObject().get("MergeCoins") != null
          && !json.getAsJsonObject().get("MergeCoins").isJsonNull()) {
        Command.MergeCoins mergeCoins = new MergeCoins();
        Argument field0 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("MergeCoins").get(0),
                new com.google.common.reflect.TypeToken<Argument>() {}.getType());
        List<Argument> field1 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("MergeCoins").get(1),
                new com.google.common.reflect.TypeToken<List<Argument>>() {}.getType());
        mergeCoins.setField0(field0);
        mergeCoins.setField1(field1);

        MergeCoinsCommand mergeCoinsCommand = new MergeCoinsCommand();
        mergeCoinsCommand.setMergeCoins(mergeCoins);
        return mergeCoinsCommand;
      }
      if (json.getAsJsonObject().get("Publish") != null
          && !json.getAsJsonObject().get("Publish").isJsonNull()) {
        return gson.fromJson(json, PublishCommand.class);
      }
      if (json.getAsJsonObject().get("MakeMoveVec") != null
          && !json.getAsJsonObject().get("MakeMoveVec").isJsonNull()) {
        Command.MakeMoveVec makeMoveVec = new MakeMoveVec();
        List<Argument> field1 =
            gson.fromJson(
                json.getAsJsonObject().getAsJsonArray("MakeMoveVec").get(1),
                new com.google.common.reflect.TypeToken<List<Argument>>() {}.getType());
        makeMoveVec.setField0(
            json.getAsJsonObject().getAsJsonArray("MakeMoveVec").get(0).getAsString());
        makeMoveVec.setField1(field1);

        MakeMoveVecCommand makeMoveVecCommand = new MakeMoveVecCommand();
        makeMoveVecCommand.setMakeMoveVec(makeMoveVec);
        return makeMoveVecCommand;
      }
      return null;
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
      SuiObjectData updatedGas =
          gson.fromJson(json.getAsJsonObject().get("updatedGas"), SuiObjectData.class);
      parsedPublishResponse.setUpdatedGas(updatedGas);
      List<SuiObjectData> createdObjects =
          gson.fromJson(
              json.getAsJsonObject().get("createdObjects"),
              new com.google.common.reflect.TypeToken<List<SuiObjectData>>() {}.getType());
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
      if (json.getAsJsonObject().get("ChangeEpoch") != null
          && !json.getAsJsonObject().get("ChangeEpoch").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.ChangeEpochTransactionKind.class);
      }
      if (json.getAsJsonObject().get("Genesis") != null
          && !json.getAsJsonObject().get("Genesis").isJsonNull()) {
        return gson.fromJson(json, TransactionKind.GenesisTransactionKind.class);
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

  /** The type Package event filter serializer. */
  public static class PackageEventFilterSerializer
      implements JsonSerializer<EventFilter.PackageEventFilter> {

    @Override
    public JsonElement serialize(
        PackageEventFilter src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("Package", src.getSuiPackage());
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

  /** The type Type tag serializer. */
  public static class TypeTagSerializer implements JsonSerializer<TypeTag> {

    @Override
    public JsonElement serialize(TypeTag src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.toString());
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
            .registerTypeAdapter(SuiRawData.class, new SuiRawDataDeserializer())
            .registerTypeAdapter(ObjectResponse.class, new GetObjectResponseDeserializer())
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
            .registerTypeAdapter(MoveFunction.class, new MoveFunctionSerializer())
            .registerTypeAdapter(
                ExecuteTransactionResponse.class, new ExecuteTransactionResponseDeserializer())
            .registerTypeAdapter(StructType.class, new TypeTagSerializer())
            .registerTypeAdapter(VectorType.class, new TypeTagSerializer())
            .registerTypeAdapter(TypeTag.class, new TypeTagSerializer())
            .registerTypeAdapter(
                EventFilter.PackageEventFilter.class, new PackageEventFilterSerializer())
            .registerTypeAdapter(Argument.class, new SuiArgumentDeserializer())
            .registerTypeAdapter(Command.class, new SuiCommandDeserializer())
            .registerTypeAdapter(ObjectChange.class, new ObjectChangeDeserializer())
            .registerTypeAdapter(SuiParsedData.class, new SuiParsedDataDeserializer())
            .create();
  }

  @Override
  public <T> JsonRpc20Response<T> fromJson(String response, Type typeOfT) {
    Type type = TypeToken.getParameterized(JsonRpc20Response.class, typeOfT).getType();
    return this.gson.fromJson(response, type);
  }

  @Override
  public JsonRpc20WSResponse fromJson(String response) {
    return this.gson.fromJson(response, JsonRpc20WSResponse.class);
  }

  @Override
  public FaucetResponse fromJsonFaucet(String response) {
    return this.gson.fromJson(response, FaucetResponse.class);
  }

  @Override
  public JsonRpc20Request fromJsonReq(String request) {
    return this.gson.fromJson(request, JsonRpc20Request.class);
  }

  @Override
  public Map<String, Object> fromJsonMap(String json) {
    return this.gson.fromJson(
        json, new com.google.common.reflect.TypeToken<Map<String, Object>>() {}.getType());
  }

  @Override
  public String toJson(JsonRpc20Request request) {
    return this.gson.toJson(request);
  }
}
