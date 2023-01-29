package io.sui.bcsgen;


public final class MoveCall {
    public final com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> Package;
    public final Identifier module;
    public final Identifier function;
    public final java.util.List<TypeTag> type_arguments;
    public final java.util.List<CallArg> arguments;

    public MoveCall(com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> Package, Identifier module, Identifier function, java.util.List<TypeTag> type_arguments, java.util.List<CallArg> arguments) {
        java.util.Objects.requireNonNull(Package, "Package must not be null");
        java.util.Objects.requireNonNull(module, "module must not be null");
        java.util.Objects.requireNonNull(function, "function must not be null");
        java.util.Objects.requireNonNull(type_arguments, "type_arguments must not be null");
        java.util.Objects.requireNonNull(arguments, "arguments must not be null");
        this.Package = Package;
        this.module = module;
        this.function = function;
        this.type_arguments = type_arguments;
        this.arguments = arguments;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(Package, serializer);
        module.serialize(serializer);
        function.serialize(serializer);
        TraitHelpers.serialize_vector_TypeTag(type_arguments, serializer);
        TraitHelpers.serialize_vector_CallArg(arguments, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveCall deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.Package = TraitHelpers.deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        builder.module = Identifier.deserialize(deserializer);
        builder.function = Identifier.deserialize(deserializer);
        builder.type_arguments = TraitHelpers.deserialize_vector_TypeTag(deserializer);
        builder.arguments = TraitHelpers.deserialize_vector_CallArg(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MoveCall bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveCall value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MoveCall other = (MoveCall) obj;
        if (!java.util.Objects.equals(this.Package, other.Package)) { return false; }
        if (!java.util.Objects.equals(this.module, other.module)) { return false; }
        if (!java.util.Objects.equals(this.function, other.function)) { return false; }
        if (!java.util.Objects.equals(this.type_arguments, other.type_arguments)) { return false; }
        if (!java.util.Objects.equals(this.arguments, other.arguments)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.Package != null ? this.Package.hashCode() : 0);
        value = 31 * value + (this.module != null ? this.module.hashCode() : 0);
        value = 31 * value + (this.function != null ? this.function.hashCode() : 0);
        value = 31 * value + (this.type_arguments != null ? this.type_arguments.hashCode() : 0);
        value = 31 * value + (this.arguments != null ? this.arguments.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> Package;
        public Identifier module;
        public Identifier function;
        public java.util.List<TypeTag> type_arguments;
        public java.util.List<CallArg> arguments;

        public MoveCall build() {
            return new MoveCall(
                Package,
                module,
                function,
                type_arguments,
                arguments
            );
        }
    }
}
