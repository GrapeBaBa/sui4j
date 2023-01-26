package io.sui.bcsgen;


public final class ObjectFormatOptions {
    public final Boolean include_types;

    public ObjectFormatOptions(Boolean include_types) {
        java.util.Objects.requireNonNull(include_types, "include_types must not be null");
        this.include_types = include_types;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_bool(include_types);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ObjectFormatOptions deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.include_types = deserializer.deserialize_bool();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ObjectFormatOptions bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ObjectFormatOptions value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ObjectFormatOptions other = (ObjectFormatOptions) obj;
        if (!java.util.Objects.equals(this.include_types, other.include_types)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.include_types != null ? this.include_types.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public Boolean include_types;

        public ObjectFormatOptions build() {
            return new ObjectFormatOptions(
                include_types
            );
        }
    }
}
