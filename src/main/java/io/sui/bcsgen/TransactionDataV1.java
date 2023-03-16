package io.sui.bcsgen;


public final class TransactionDataV1 {
    public final TransactionKind kind;
    public final SuiAddress sender;
    public final GasData gas_data;
    public final TransactionExpiration expiration;

    public TransactionDataV1(TransactionKind kind, SuiAddress sender, GasData gas_data, TransactionExpiration expiration) {
        java.util.Objects.requireNonNull(kind, "kind must not be null");
        java.util.Objects.requireNonNull(sender, "sender must not be null");
        java.util.Objects.requireNonNull(gas_data, "gas_data must not be null");
        java.util.Objects.requireNonNull(expiration, "expiration must not be null");
        this.kind = kind;
        this.sender = sender;
        this.gas_data = gas_data;
        this.expiration = expiration;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        kind.serialize(serializer);
        sender.serialize(serializer);
        gas_data.serialize(serializer);
        expiration.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TransactionDataV1 deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.kind = TransactionKind.deserialize(deserializer);
        builder.sender = SuiAddress.deserialize(deserializer);
        builder.gas_data = GasData.deserialize(deserializer);
        builder.expiration = TransactionExpiration.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TransactionDataV1 bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransactionDataV1 value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TransactionDataV1 other = (TransactionDataV1) obj;
        if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
        if (!java.util.Objects.equals(this.sender, other.sender)) { return false; }
        if (!java.util.Objects.equals(this.gas_data, other.gas_data)) { return false; }
        if (!java.util.Objects.equals(this.expiration, other.expiration)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
        value = 31 * value + (this.sender != null ? this.sender.hashCode() : 0);
        value = 31 * value + (this.gas_data != null ? this.gas_data.hashCode() : 0);
        value = 31 * value + (this.expiration != null ? this.expiration.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public TransactionKind kind;
        public SuiAddress sender;
        public GasData gas_data;
        public TransactionExpiration expiration;

        public TransactionDataV1 build() {
            return new TransactionDataV1(
                kind,
                sender,
                gas_data,
                expiration
            );
        }
    }
}
