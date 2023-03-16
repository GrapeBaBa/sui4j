package io.sui.bcsgen;

final class TraitHelpers {
    static void serialize_array32_u8_array(java.util.@com.novi.serde.ArrayLen(length=32) List<@com.novi.serde.Unsigned Byte> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.size() != 32) {
            throw new IllegalArgumentException("Invalid length for fixed-size array: " + value.size() + " instead of "+ 32);
        }
        for (@com.novi.serde.Unsigned Byte item : value) {
            serializer.serialize_u8(item);
        }
    }

    static java.util.@com.novi.serde.ArrayLen(length=32) List<@com.novi.serde.Unsigned Byte> deserialize_array32_u8_array(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        java.util.List<@com.novi.serde.Unsigned Byte> obj = new java.util.ArrayList<@com.novi.serde.Unsigned Byte>(32);
        for (long i = 0; i < 32; i++) {
            obj.add(deserializer.deserialize_u8());
        }
        return obj;
    }

    static void serialize_map_str_to_bytes(java.util.Map<String, com.novi.serde.Bytes> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        int[] offsets = new int[value.size()];
        int count = 0;
        for (java.util.Map.Entry<String, com.novi.serde.Bytes> entry : value.entrySet()) {
            offsets[count++] = serializer.get_buffer_offset();
            serializer.serialize_str(entry.getKey());
            serializer.serialize_bytes(entry.getValue());
        }
        serializer.sort_map_entries(offsets);
    }

    static java.util.Map<String, com.novi.serde.Bytes> deserialize_map_str_to_bytes(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.Map<String, com.novi.serde.Bytes> obj = new java.util.HashMap<String, com.novi.serde.Bytes>();
        int previous_key_start = 0;
        int previous_key_end = 0;
        for (long i = 0; i < length; i++) {
            int key_start = deserializer.get_buffer_offset();
            String key = deserializer.deserialize_str();
            int key_end = deserializer.get_buffer_offset();
            if (i > 0) {
                deserializer.check_that_key_slices_are_increasing(
                    new com.novi.serde.Slice(previous_key_start, previous_key_end),
                    new com.novi.serde.Slice(key_start, key_end));
            }
            previous_key_start = key_start;
            previous_key_end = key_end;
            com.novi.serde.Bytes value = deserializer.deserialize_bytes();
            obj.put(key, value);
        }
        return obj;
    }

    static void serialize_option_MoveLocation(java.util.Optional<MoveLocation> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            value.get().serialize(serializer);
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<MoveLocation> deserialize_option_MoveLocation(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(MoveLocation.deserialize(deserializer));
        }
    }

    static void serialize_option_TypeTag(java.util.Optional<TypeTag> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            value.get().serialize(serializer);
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<TypeTag> deserialize_option_TypeTag(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(TypeTag.deserialize(deserializer));
        }
    }

    static void serialize_option_str(java.util.Optional<String> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            serializer.serialize_str(value.get());
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<String> deserialize_option_str(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(deserializer.deserialize_str());
        }
    }

    static void serialize_option_u64(java.util.Optional<@com.novi.serde.Unsigned Long> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            serializer.serialize_u64(value.get());
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<@com.novi.serde.Unsigned Long> deserialize_option_u64(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(deserializer.deserialize_u64());
        }
    }

    static void serialize_tuple2_Identifier_MoveValue(com.novi.serde.Tuple2<Identifier, MoveValue> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        value.field0.serialize(serializer);
        value.field1.serialize(serializer);
    }

    static com.novi.serde.Tuple2<Identifier, MoveValue> deserialize_tuple2_Identifier_MoveValue(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        return new com.novi.serde.Tuple2<Identifier, MoveValue>(
            Identifier.deserialize(deserializer),
            MoveValue.deserialize(deserializer)
        );
    }

    static void serialize_tuple2_SequenceNumber_vector_vector_u8(com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        value.field0.serialize(serializer);
        TraitHelpers.serialize_vector_vector_u8(value.field1, serializer);
    }

    static com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>> deserialize_tuple2_SequenceNumber_vector_vector_u8(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        return new com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>>(
            SequenceNumber.deserialize(deserializer),
            TraitHelpers.deserialize_vector_vector_u8(deserializer)
        );
    }

    static void serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        value.field0.serialize(serializer);
        value.field1.serialize(serializer);
        value.field2.serialize(serializer);
    }

    static com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        return new com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>(
            ObjectID.deserialize(deserializer),
            SequenceNumber.deserialize(deserializer),
            ObjectDigest.deserialize(deserializer)
        );
    }

    static void serialize_vector_Argument(java.util.List<Argument> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (Argument item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<Argument> deserialize_vector_Argument(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<Argument> obj = new java.util.ArrayList<Argument>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(Argument.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_CallArg(java.util.List<CallArg> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (CallArg item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<CallArg> deserialize_vector_CallArg(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<CallArg> obj = new java.util.ArrayList<CallArg>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(CallArg.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_Command(java.util.List<Command> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (Command item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<Command> deserialize_vector_Command(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<Command> obj = new java.util.ArrayList<Command>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(Command.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_GenesisObject(java.util.List<GenesisObject> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (GenesisObject item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<GenesisObject> deserialize_vector_GenesisObject(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<GenesisObject> obj = new java.util.ArrayList<GenesisObject>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(GenesisObject.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_MoveFieldLayout(java.util.List<MoveFieldLayout> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (MoveFieldLayout item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<MoveFieldLayout> deserialize_vector_MoveFieldLayout(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<MoveFieldLayout> obj = new java.util.ArrayList<MoveFieldLayout>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(MoveFieldLayout.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_MoveTypeLayout(java.util.List<MoveTypeLayout> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (MoveTypeLayout item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<MoveTypeLayout> deserialize_vector_MoveTypeLayout(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<MoveTypeLayout> obj = new java.util.ArrayList<MoveTypeLayout>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(MoveTypeLayout.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_MoveValue(java.util.List<MoveValue> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (MoveValue item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<MoveValue> deserialize_vector_MoveValue(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<MoveValue> obj = new java.util.ArrayList<MoveValue>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(MoveValue.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_ObjectID(java.util.List<ObjectID> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (ObjectID item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<ObjectID> deserialize_vector_ObjectID(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<ObjectID> obj = new java.util.ArrayList<ObjectID>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(ObjectID.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_TypeTag(java.util.List<TypeTag> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (TypeTag item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<TypeTag> deserialize_vector_TypeTag(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<TypeTag> obj = new java.util.ArrayList<TypeTag>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(TypeTag.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_tuple2_Identifier_MoveValue(java.util.List<com.novi.serde.Tuple2<Identifier, MoveValue>> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (com.novi.serde.Tuple2<Identifier, MoveValue> item : value) {
            TraitHelpers.serialize_tuple2_Identifier_MoveValue(item, serializer);
        }
    }

    static java.util.List<com.novi.serde.Tuple2<Identifier, MoveValue>> deserialize_vector_tuple2_Identifier_MoveValue(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<com.novi.serde.Tuple2<Identifier, MoveValue>> obj = new java.util.ArrayList<com.novi.serde.Tuple2<Identifier, MoveValue>>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(TraitHelpers.deserialize_tuple2_Identifier_MoveValue(deserializer));
        }
        return obj;
    }

    static void serialize_vector_tuple2_SequenceNumber_vector_vector_u8(java.util.List<com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>>> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>> item : value) {
            TraitHelpers.serialize_tuple2_SequenceNumber_vector_vector_u8(item, serializer);
        }
    }

    static java.util.List<com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>>> deserialize_vector_tuple2_SequenceNumber_vector_vector_u8(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>>> obj = new java.util.ArrayList<com.novi.serde.Tuple2<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>>>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(TraitHelpers.deserialize_tuple2_SequenceNumber_vector_vector_u8(deserializer));
        }
        return obj;
    }

    static void serialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> item : value) {
            TraitHelpers.serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(item, serializer);
        }
    }

    static java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> deserialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> obj = new java.util.ArrayList<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(TraitHelpers.deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer));
        }
        return obj;
    }

    static void serialize_vector_u8(java.util.List<@com.novi.serde.Unsigned Byte> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (@com.novi.serde.Unsigned Byte item : value) {
            serializer.serialize_u8(item);
        }
    }

    static java.util.List<@com.novi.serde.Unsigned Byte> deserialize_vector_u8(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<@com.novi.serde.Unsigned Byte> obj = new java.util.ArrayList<@com.novi.serde.Unsigned Byte>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(deserializer.deserialize_u8());
        }
        return obj;
    }

    static void serialize_vector_vector_u8(java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (java.util.List<@com.novi.serde.Unsigned Byte> item : value) {
            TraitHelpers.serialize_vector_u8(item, serializer);
        }
    }

    static java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> deserialize_vector_vector_u8(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> obj = new java.util.ArrayList<java.util.List<@com.novi.serde.Unsigned Byte>>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(TraitHelpers.deserialize_vector_u8(deserializer));
        }
        return obj;
    }

}

