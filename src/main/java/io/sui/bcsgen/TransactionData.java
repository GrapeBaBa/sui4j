package io.sui.bcsgen;


public final class TransactionData {
    public final TransactionKind kind;
    public final SuiAddress sender;
    public final com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> gas_payment;
    public final @com.novi.serde.Unsigned Long gas_price;
    public final @com.novi.serde.Unsigned Long gas_budget;

    public TransactionData(TransactionKind kind, SuiAddress sender, com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> gas_payment, @com.novi.serde.Unsigned Long gas_price, @com.novi.serde.Unsigned Long gas_budget) {
        java.util.Objects.requireNonNull(kind, "kind must not be null");
        java.util.Objects.requireNonNull(sender, "sender must not be null");
        java.util.Objects.requireNonNull(gas_payment, "gas_payment must not be null");
        java.util.Objects.requireNonNull(gas_price, "gas_price must not be null");
        java.util.Objects.requireNonNull(gas_budget, "gas_budget must not be null");
        this.kind = kind;
        this.sender = sender;
        this.gas_payment = gas_payment;
        this.gas_price = gas_price;
        this.gas_budget = gas_budget;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        kind.serialize(serializer);
        sender.serialize(serializer);
        TraitHelpers.serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(gas_payment, serializer);
        serializer.serialize_u64(gas_price);
        serializer.serialize_u64(gas_budget);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TransactionData deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.kind = TransactionKind.deserialize(deserializer);
        builder.sender = SuiAddress.deserialize(deserializer);
        builder.gas_payment = TraitHelpers.deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        builder.gas_price = deserializer.deserialize_u64();
        builder.gas_budget = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TransactionData bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransactionData value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TransactionData other = (TransactionData) obj;
        if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
        if (!java.util.Objects.equals(this.sender, other.sender)) { return false; }
        if (!java.util.Objects.equals(this.gas_payment, other.gas_payment)) { return false; }
        if (!java.util.Objects.equals(this.gas_price, other.gas_price)) { return false; }
        if (!java.util.Objects.equals(this.gas_budget, other.gas_budget)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
        value = 31 * value + (this.sender != null ? this.sender.hashCode() : 0);
        value = 31 * value + (this.gas_payment != null ? this.gas_payment.hashCode() : 0);
        value = 31 * value + (this.gas_price != null ? this.gas_price.hashCode() : 0);
        value = 31 * value + (this.gas_budget != null ? this.gas_budget.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public TransactionKind kind;
        public SuiAddress sender;
        public com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> gas_payment;
        public @com.novi.serde.Unsigned Long gas_price;
        public @com.novi.serde.Unsigned Long gas_budget;

        public TransactionData build() {
            return new TransactionData(
                kind,
                sender,
                gas_payment,
                gas_price,
                gas_budget
            );
        }
    }
}
