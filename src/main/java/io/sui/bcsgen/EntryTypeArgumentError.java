package io.sui.bcsgen;


public final class EntryTypeArgumentError {
    public final @com.novi.serde.Unsigned Short argument_idx;
    public final EntryTypeArgumentErrorKind kind;

    public EntryTypeArgumentError(@com.novi.serde.Unsigned Short argument_idx, EntryTypeArgumentErrorKind kind) {
        java.util.Objects.requireNonNull(argument_idx, "argument_idx must not be null");
        java.util.Objects.requireNonNull(kind, "kind must not be null");
        this.argument_idx = argument_idx;
        this.kind = kind;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u16(argument_idx);
        kind.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static EntryTypeArgumentError deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.argument_idx = deserializer.deserialize_u16();
        builder.kind = EntryTypeArgumentErrorKind.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static EntryTypeArgumentError bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        EntryTypeArgumentError value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EntryTypeArgumentError other = (EntryTypeArgumentError) obj;
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
        public @com.novi.serde.Unsigned Short argument_idx;
        public EntryTypeArgumentErrorKind kind;

        public EntryTypeArgumentError build() {
            return new EntryTypeArgumentError(
                argument_idx,
                kind
            );
        }
    }
}
