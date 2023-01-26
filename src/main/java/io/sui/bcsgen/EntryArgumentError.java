package io.sui.bcsgen;


public final class EntryArgumentError {
    public final @com.novi.serde.Unsigned Byte argument_idx;
    public final EntryArgumentErrorKind kind;

    public EntryArgumentError(@com.novi.serde.Unsigned Byte argument_idx, EntryArgumentErrorKind kind) {
        java.util.Objects.requireNonNull(argument_idx, "argument_idx must not be null");
        java.util.Objects.requireNonNull(kind, "kind must not be null");
        this.argument_idx = argument_idx;
        this.kind = kind;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u8(argument_idx);
        kind.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static EntryArgumentError deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.argument_idx = deserializer.deserialize_u8();
        builder.kind = EntryArgumentErrorKind.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static EntryArgumentError bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        EntryArgumentError value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EntryArgumentError other = (EntryArgumentError) obj;
        if (!java.util.Objects.equals(this.argument_idx, other.argument_idx)) { return false; }
        if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.argument_idx != null ? this.argument_idx.hashCode() : 0);
        value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Byte argument_idx;
        public EntryArgumentErrorKind kind;

        public EntryArgumentError build() {
            return new EntryArgumentError(
                argument_idx,
                kind
            );
        }
    }
}
