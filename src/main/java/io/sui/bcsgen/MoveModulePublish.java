package io.sui.bcsgen;


public final class MoveModulePublish {
    public final java.util.List<com.novi.serde.Bytes> modules;

    public MoveModulePublish(java.util.List<com.novi.serde.Bytes> modules) {
        java.util.Objects.requireNonNull(modules, "modules must not be null");
        this.modules = modules;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_bytes(modules, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveModulePublish deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.modules = TraitHelpers.deserialize_vector_bytes(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MoveModulePublish bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveModulePublish value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MoveModulePublish other = (MoveModulePublish) obj;
        if (!java.util.Objects.equals(this.modules, other.modules)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.modules != null ? this.modules.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<com.novi.serde.Bytes> modules;

        public MoveModulePublish build() {
            return new MoveModulePublish(
                modules
            );
        }
    }
}
