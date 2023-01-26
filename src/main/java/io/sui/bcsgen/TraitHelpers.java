package io.sui.bcsgen;

final class TraitHelpers {
    static void serialize_array20_u8_array(java.util.@com.novi.serde.ArrayLen(length=20) List<@com.novi.serde.Unsigned Byte> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.size() != 20) {
            throw new IllegalArgumentException("Invalid length for fixed-size array: " + value.size() + " instead of "+ 20);
        }
        for (@com.novi.serde.Unsigned Byte item : value) {
            serializer.serialize_u8(item);
        }
    }

    static java.util.@com.novi.serde.ArrayLen(length=20) List<@com.novi.serde.Unsigned Byte> deserialize_array20_u8_array(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        java.util.List<@com.novi.serde.Unsigned Byte> obj = new java.util.ArrayList<@com.novi.serde.Unsigned Byte>(20);
        for (long i = 0; i < 20; i++) {
            obj.add(deserializer.deserialize_u8());
        }
        return obj;
    }

    static void serialize_array48_u8_array(java.util.@com.novi.serde.ArrayLen(length=48) List<@com.novi.serde.Unsigned Byte> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.size() != 48) {
            throw new IllegalArgumentException("Invalid length for fixed-size array: " + value.size() + " instead of "+ 48);
        }
        for (@com.novi.serde.Unsigned Byte item : value) {
            serializer.serialize_u8(item);
        }
    }

    static java.util.@com.novi.serde.ArrayLen(length=48) List<@com.novi.serde.Unsigned Byte> deserialize_array48_u8_array(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        java.util.List<@com.novi.serde.Unsigned Byte> obj = new java.util.ArrayList<@com.novi.serde.Unsigned Byte>(48);
        for (long i = 0; i < 48; i++) {
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

    static void serialize_option_DeleteKind(java.util.Optional<DeleteKind> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            value.get().serialize(serializer);
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<DeleteKind> deserialize_option_DeleteKind(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(DeleteKind.deserialize(deserializer));
        }
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

    static void serialize_option_ObjectFormatOptions(java.util.Optional<ObjectFormatOptions> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        if (value.isPresent()) {
            serializer.serialize_option_tag(true);
            value.get().serialize(serializer);
        } else {
            serializer.serialize_option_tag(false);
        }
    }

    static java.util.Optional<ObjectFormatOptions> deserialize_option_ObjectFormatOptions(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        boolean tag = deserializer.deserialize_option_tag();
        if (!tag) {
            return java.util.Optional.empty();
        } else {
            return java.util.Optional.of(ObjectFormatOptions.deserialize(deserializer));
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

    static void serialize_vector_ObjectArg(java.util.List<ObjectArg> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (ObjectArg item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<ObjectArg> deserialize_vector_ObjectArg(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<ObjectArg> obj = new java.util.ArrayList<ObjectArg>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(ObjectArg.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_SingleTransactionKind(java.util.List<SingleTransactionKind> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (SingleTransactionKind item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<SingleTransactionKind> deserialize_vector_SingleTransactionKind(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<SingleTransactionKind> obj = new java.util.ArrayList<SingleTransactionKind>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(SingleTransactionKind.deserialize(deserializer));
        }
        return obj;
    }

    static void serialize_vector_SuiAddress(java.util.List<SuiAddress> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (SuiAddress item : value) {
            item.serialize(serializer);
        }
    }

    static java.util.List<SuiAddress> deserialize_vector_SuiAddress(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<SuiAddress> obj = new java.util.ArrayList<SuiAddress>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(SuiAddress.deserialize(deserializer));
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

    static void serialize_vector_bytes(java.util.List<com.novi.serde.Bytes> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (com.novi.serde.Bytes item : value) {
            serializer.serialize_bytes(item);
        }
    }

    static java.util.List<com.novi.serde.Bytes> deserialize_vector_bytes(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<com.novi.serde.Bytes> obj = new java.util.ArrayList<com.novi.serde.Bytes>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(deserializer.deserialize_bytes());
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

    static void serialize_vector_u64(java.util.List<@com.novi.serde.Unsigned Long> value, com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.serialize_len(value.size());
        for (@com.novi.serde.Unsigned Long item : value) {
            serializer.serialize_u64(item);
        }
    }

    static java.util.List<@com.novi.serde.Unsigned Long> deserialize_vector_u64(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        long length = deserializer.deserialize_len();
        java.util.List<@com.novi.serde.Unsigned Long> obj = new java.util.ArrayList<@com.novi.serde.Unsigned Long>((int) length);
        for (long i = 0; i < length; i++) {
            obj.add(deserializer.deserialize_u64());
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

}

