package io.sui.bcsgen;


public final class MovePackage {
    public final ObjectID id;
    public final java.util.Map<String, com.novi.serde.Bytes> module_map;

    public MovePackage(ObjectID id, java.util.Map<String, com.novi.serde.Bytes> module_map) {
        java.util.Objects.requireNonNull(id, "id must not be null");
        java.util.Objects.requireNonNull(module_map, "module_map must not be null");
        this.id = id;
        this.module_map = module_map;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        id.serialize(serializer);
        TraitHelpers.serialize_map_str_to_bytes(module_map, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MovePackage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.id = ObjectID.deserialize(deserializer);
        builder.module_map = TraitHelpers.deserialize_map_str_to_bytes(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MovePackage bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MovePackage value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MovePackage other = (MovePackage) obj;
        if (!java.util.Objects.equals(this.id, other.id)) { return false; }
        if (!java.util.Objects.equals(this.module_map, other.module_map)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.id != null ? this.id.hashCode() : 0);
        value = 31 * value + (this.module_map != null ? this.module_map.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ObjectID id;
        public java.util.Map<String, com.novi.serde.Bytes> module_map;

        public MovePackage build() {
            return new MovePackage(
                id,
                module_map
            );
        }
    }
}
