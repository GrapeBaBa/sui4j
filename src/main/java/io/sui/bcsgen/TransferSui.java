package io.sui.bcsgen;


public final class TransferSui {
    public final SuiAddress recipient;
    public final java.util.Optional<@com.novi.serde.Unsigned Long> amount;

    public TransferSui(SuiAddress recipient, java.util.Optional<@com.novi.serde.Unsigned Long> amount) {
        java.util.Objects.requireNonNull(recipient, "recipient must not be null");
        java.util.Objects.requireNonNull(amount, "amount must not be null");
        this.recipient = recipient;
        this.amount = amount;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        recipient.serialize(serializer);
        TraitHelpers.serialize_option_u64(amount, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TransferSui deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.recipient = SuiAddress.deserialize(deserializer);
        builder.amount = TraitHelpers.deserialize_option_u64(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TransferSui bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransferSui value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TransferSui other = (TransferSui) obj;
        if (!java.util.Objects.equals(this.recipient, other.recipient)) { return false; }
        if (!java.util.Objects.equals(this.amount, other.amount)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.recipient != null ? this.recipient.hashCode() : 0);
        value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public SuiAddress recipient;
        public java.util.Optional<@com.novi.serde.Unsigned Long> amount;

        public TransferSui build() {
            return new TransferSui(
                recipient,
                amount
            );
        }
    }
}
