package io.sui.bcsgen;


public final class TransferObject {
    public final SuiAddress recipient;
    public final com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> object_ref;

    public TransferObject(SuiAddress recipient, com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> object_ref) {
        java.util.Objects.requireNonNull(recipient, "recipient must not be null");
        java.util.Objects.requireNonNull(object_ref, "object_ref must not be null");
        this.recipient = recipient;
        this.object_ref = object_ref;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        recipient.serialize(serializer);
        TraitHelpers.serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(object_ref, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TransferObject deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.recipient = SuiAddress.deserialize(deserializer);
        builder.object_ref = TraitHelpers.deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TransferObject bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransferObject value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TransferObject other = (TransferObject) obj;
        if (!java.util.Objects.equals(this.recipient, other.recipient)) { return false; }
        if (!java.util.Objects.equals(this.object_ref, other.object_ref)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.recipient != null ? this.recipient.hashCode() : 0);
        value = 31 * value + (this.object_ref != null ? this.object_ref.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public SuiAddress recipient;
        public com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> object_ref;

        public TransferObject build() {
            return new TransferObject(
                recipient,
                object_ref
            );
        }
    }
}
