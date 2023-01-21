package io.sui.bcsgen;


public final class Pay {
    public final java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins;
    public final java.util.List<SuiAddress> recipients;
    public final java.util.List<@com.novi.serde.Unsigned Long> amounts;

    public Pay(java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins, java.util.List<SuiAddress> recipients, java.util.List<@com.novi.serde.Unsigned Long> amounts) {
        java.util.Objects.requireNonNull(coins, "coins must not be null");
        java.util.Objects.requireNonNull(recipients, "recipients must not be null");
        java.util.Objects.requireNonNull(amounts, "amounts must not be null");
        this.coins = coins;
        this.recipients = recipients;
        this.amounts = amounts;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(coins, serializer);
        TraitHelpers.serialize_vector_SuiAddress(recipients, serializer);
        TraitHelpers.serialize_vector_u64(amounts, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static Pay deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.coins = TraitHelpers.deserialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        builder.recipients = TraitHelpers.deserialize_vector_SuiAddress(deserializer);
        builder.amounts = TraitHelpers.deserialize_vector_u64(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static Pay bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        Pay value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Pay other = (Pay) obj;
        if (!java.util.Objects.equals(this.coins, other.coins)) { return false; }
        if (!java.util.Objects.equals(this.recipients, other.recipients)) { return false; }
        if (!java.util.Objects.equals(this.amounts, other.amounts)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.coins != null ? this.coins.hashCode() : 0);
        value = 31 * value + (this.recipients != null ? this.recipients.hashCode() : 0);
        value = 31 * value + (this.amounts != null ? this.amounts.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> coins;
        public java.util.List<SuiAddress> recipients;
        public java.util.List<@com.novi.serde.Unsigned Long> amounts;

        public Pay build() {
            return new Pay(
                coins,
                recipients,
                amounts
            );
        }
    }
}
