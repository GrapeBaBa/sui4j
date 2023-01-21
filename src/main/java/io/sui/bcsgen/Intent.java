package io.sui.bcsgen;


public final class Intent {
    public final @com.novi.serde.Unsigned Byte scope;
    public final @com.novi.serde.Unsigned Byte version;
    public final @com.novi.serde.Unsigned Byte app_id;

    public Intent(@com.novi.serde.Unsigned Byte scope, @com.novi.serde.Unsigned Byte version, @com.novi.serde.Unsigned Byte app_id) {
        java.util.Objects.requireNonNull(scope, "scope must not be null");
        java.util.Objects.requireNonNull(version, "version must not be null");
        java.util.Objects.requireNonNull(app_id, "app_id must not be null");
        this.scope = scope;
        this.version = version;
        this.app_id = app_id;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u8(scope);
        serializer.serialize_u8(version);
        serializer.serialize_u8(app_id);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static Intent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.scope = deserializer.deserialize_u8();
        builder.version = deserializer.deserialize_u8();
        builder.app_id = deserializer.deserialize_u8();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static Intent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        Intent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Intent other = (Intent) obj;
        if (!java.util.Objects.equals(this.scope, other.scope)) { return false; }
        if (!java.util.Objects.equals(this.version, other.version)) { return false; }
        if (!java.util.Objects.equals(this.app_id, other.app_id)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.scope != null ? this.scope.hashCode() : 0);
        value = 31 * value + (this.version != null ? this.version.hashCode() : 0);
        value = 31 * value + (this.app_id != null ? this.app_id.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Byte scope;
        public @com.novi.serde.Unsigned Byte version;
        public @com.novi.serde.Unsigned Byte app_id;

        public Intent build() {
            return new Intent(
                scope,
                version,
                app_id
            );
        }
    }
}
