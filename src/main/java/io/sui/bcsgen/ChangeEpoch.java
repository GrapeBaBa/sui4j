package io.sui.bcsgen;


public final class ChangeEpoch {
    public final @com.novi.serde.Unsigned Long epoch;
    public final @com.novi.serde.Unsigned Long storage_charge;
    public final @com.novi.serde.Unsigned Long computation_charge;
    public final @com.novi.serde.Unsigned Long storage_rebate;

    public ChangeEpoch(@com.novi.serde.Unsigned Long epoch, @com.novi.serde.Unsigned Long storage_charge, @com.novi.serde.Unsigned Long computation_charge, @com.novi.serde.Unsigned Long storage_rebate) {
        java.util.Objects.requireNonNull(epoch, "epoch must not be null");
        java.util.Objects.requireNonNull(storage_charge, "storage_charge must not be null");
        java.util.Objects.requireNonNull(computation_charge, "computation_charge must not be null");
        java.util.Objects.requireNonNull(storage_rebate, "storage_rebate must not be null");
        this.epoch = epoch;
        this.storage_charge = storage_charge;
        this.computation_charge = computation_charge;
        this.storage_rebate = storage_rebate;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(epoch);
        serializer.serialize_u64(storage_charge);
        serializer.serialize_u64(computation_charge);
        serializer.serialize_u64(storage_rebate);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ChangeEpoch deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.epoch = deserializer.deserialize_u64();
        builder.storage_charge = deserializer.deserialize_u64();
        builder.computation_charge = deserializer.deserialize_u64();
        builder.storage_rebate = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ChangeEpoch bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ChangeEpoch value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ChangeEpoch other = (ChangeEpoch) obj;
        if (!java.util.Objects.equals(this.epoch, other.epoch)) { return false; }
        if (!java.util.Objects.equals(this.storage_charge, other.storage_charge)) { return false; }
        if (!java.util.Objects.equals(this.computation_charge, other.computation_charge)) { return false; }
        if (!java.util.Objects.equals(this.storage_rebate, other.storage_rebate)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.epoch != null ? this.epoch.hashCode() : 0);
        value = 31 * value + (this.storage_charge != null ? this.storage_charge.hashCode() : 0);
        value = 31 * value + (this.computation_charge != null ? this.computation_charge.hashCode() : 0);
        value = 31 * value + (this.storage_rebate != null ? this.storage_rebate.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long epoch;
        public @com.novi.serde.Unsigned Long storage_charge;
        public @com.novi.serde.Unsigned Long computation_charge;
        public @com.novi.serde.Unsigned Long storage_rebate;

        public ChangeEpoch build() {
            return new ChangeEpoch(
                epoch,
                storage_charge,
                computation_charge,
                storage_rebate
            );
        }
    }
}
