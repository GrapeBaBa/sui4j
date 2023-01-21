package io.sui.bcsgen;


public final class PayAllSui {
    public final java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins;
    public final SuiAddress recipient;

    public PayAllSui(java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins, SuiAddress recipient) {
        java.util.Objects.requireNonNull(coins, "coins must not be null");
        java.util.Objects.requireNonNull(recipient, "recipient must not be null");
        this.coins = coins;
        this.recipient = recipient;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(coins, serializer);
        recipient.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static PayAllSui deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.coins = TraitHelpers.deserialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        builder.recipient = SuiAddress.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static PayAllSui bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        PayAllSui value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PayAllSui other = (PayAllSui) obj;
        if (!java.util.Objects.equals(this.coins, other.coins)) { return false; }
        if (!java.util.Objects.equals(this.recipient, other.recipient)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.coins != null ? this.coins.hashCode() : 0);
        value = 31 * value + (this.recipient != null ? this.recipient.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins;
        public SuiAddress recipient;

        public PayAllSui build() {
            return new PayAllSui(
                coins,
                recipient
            );
        }
    }
}
