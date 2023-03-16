package io.sui.bcsgen;


public final class MoveObject {
    public final MoveObjectType type_;
    public final Boolean has_public_transfer;
    public final SequenceNumber version;
    public final com.novi.serde.Bytes contents;

    public MoveObject(MoveObjectType type_, Boolean has_public_transfer, SequenceNumber version, com.novi.serde.Bytes contents) {
        java.util.Objects.requireNonNull(type_, "type_ must not be null");
        java.util.Objects.requireNonNull(has_public_transfer, "has_public_transfer must not be null");
        java.util.Objects.requireNonNull(version, "version must not be null");
        java.util.Objects.requireNonNull(contents, "contents must not be null");
        this.type_ = type_;
        this.has_public_transfer = has_public_transfer;
        this.version = version;
        this.contents = contents;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        type_.serialize(serializer);
        serializer.serialize_bool(has_public_transfer);
        version.serialize(serializer);
        serializer.serialize_bytes(contents);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveObject deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.type_ = MoveObjectType.deserialize(deserializer);
        builder.has_public_transfer = deserializer.deserialize_bool();
        builder.version = SequenceNumber.deserialize(deserializer);
        builder.contents = deserializer.deserialize_bytes();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MoveObject bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveObject value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MoveObject other = (MoveObject) obj;
        if (!java.util.Objects.equals(this.type_, other.type_)) { return false; }
        if (!java.util.Objects.equals(this.has_public_transfer, other.has_public_transfer)) { return false; }
        if (!java.util.Objects.equals(this.version, other.version)) { return false; }
        if (!java.util.Objects.equals(this.contents, other.contents)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.type_ != null ? this.type_.hashCode() : 0);
        value = 31 * value + (this.has_public_transfer != null ? this.has_public_transfer.hashCode() : 0);
        value = 31 * value + (this.version != null ? this.version.hashCode() : 0);
        value = 31 * value + (this.contents != null ? this.contents.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public MoveObjectType type_;
        public Boolean has_public_transfer;
        public SequenceNumber version;
        public com.novi.serde.Bytes contents;

        public MoveObject build() {
            return new MoveObject(
                type_,
                has_public_transfer,
                version,
                contents
            );
        }
    }
}
