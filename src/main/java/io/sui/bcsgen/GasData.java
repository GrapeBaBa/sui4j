package io.sui.bcsgen;


public final class GasData {
    public final java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> payment;
    public final SuiAddress owner;
    public final @com.novi.serde.Unsigned Long price;
    public final @com.novi.serde.Unsigned Long budget;

    public GasData(java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> payment, SuiAddress owner, @com.novi.serde.Unsigned Long price, @com.novi.serde.Unsigned Long budget) {
        java.util.Objects.requireNonNull(payment, "payment must not be null");
        java.util.Objects.requireNonNull(owner, "owner must not be null");
        java.util.Objects.requireNonNull(price, "price must not be null");
        java.util.Objects.requireNonNull(budget, "budget must not be null");
        this.payment = payment;
        this.owner = owner;
        this.price = price;
        this.budget = budget;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(payment, serializer);
        owner.serialize(serializer);
        serializer.serialize_u64(price);
        serializer.serialize_u64(budget);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static GasData deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.payment = TraitHelpers.deserialize_vector_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
        builder.owner = SuiAddress.deserialize(deserializer);
        builder.price = deserializer.deserialize_u64();
        builder.budget = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static GasData bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        GasData value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        GasData other = (GasData) obj;
        if (!java.util.Objects.equals(this.payment, other.payment)) { return false; }
        if (!java.util.Objects.equals(this.owner, other.owner)) { return false; }
        if (!java.util.Objects.equals(this.price, other.price)) { return false; }
        if (!java.util.Objects.equals(this.budget, other.budget)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.payment != null ? this.payment.hashCode() : 0);
        value = 31 * value + (this.owner != null ? this.owner.hashCode() : 0);
        value = 31 * value + (this.price != null ? this.price.hashCode() : 0);
        value = 31 * value + (this.budget != null ? this.budget.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest>> payment;
        public SuiAddress owner;
        public @com.novi.serde.Unsigned Long price;
        public @com.novi.serde.Unsigned Long budget;

        public GasData build() {
            return new GasData(
                payment,
                owner,
                price,
                budget
            );
        }
    }
}
