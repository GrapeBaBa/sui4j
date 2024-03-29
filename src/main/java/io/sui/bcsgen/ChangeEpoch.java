package io.sui.bcsgen;


public final class ChangeEpoch {
    public final @com.novi.serde.Unsigned Long epoch;
    public final ProtocolVersion protocol_version;
    public final @com.novi.serde.Unsigned Long storage_charge;
    public final @com.novi.serde.Unsigned Long computation_charge;
    public final @com.novi.serde.Unsigned Long storage_rebate;
    public final @com.novi.serde.Unsigned Long non_refundable_storage_fee;
    public final @com.novi.serde.Unsigned Long epoch_start_timestamp_ms;
    public final java.util.List<com.novi.serde.Tuple3<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>, java.util.List<ObjectID>>> system_packages;

    public ChangeEpoch(@com.novi.serde.Unsigned Long epoch, ProtocolVersion protocol_version, @com.novi.serde.Unsigned Long storage_charge, @com.novi.serde.Unsigned Long computation_charge, @com.novi.serde.Unsigned Long storage_rebate, @com.novi.serde.Unsigned Long non_refundable_storage_fee, @com.novi.serde.Unsigned Long epoch_start_timestamp_ms, java.util.List<com.novi.serde.Tuple3<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>, java.util.List<ObjectID>>> system_packages) {
        java.util.Objects.requireNonNull(epoch, "epoch must not be null");
        java.util.Objects.requireNonNull(protocol_version, "protocol_version must not be null");
        java.util.Objects.requireNonNull(storage_charge, "storage_charge must not be null");
        java.util.Objects.requireNonNull(computation_charge, "computation_charge must not be null");
        java.util.Objects.requireNonNull(storage_rebate, "storage_rebate must not be null");
        java.util.Objects.requireNonNull(non_refundable_storage_fee, "non_refundable_storage_fee must not be null");
        java.util.Objects.requireNonNull(epoch_start_timestamp_ms, "epoch_start_timestamp_ms must not be null");
        java.util.Objects.requireNonNull(system_packages, "system_packages must not be null");
        this.epoch = epoch;
        this.protocol_version = protocol_version;
        this.storage_charge = storage_charge;
        this.computation_charge = computation_charge;
        this.storage_rebate = storage_rebate;
        this.non_refundable_storage_fee = non_refundable_storage_fee;
        this.epoch_start_timestamp_ms = epoch_start_timestamp_ms;
        this.system_packages = system_packages;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(epoch);
        protocol_version.serialize(serializer);
        serializer.serialize_u64(storage_charge);
        serializer.serialize_u64(computation_charge);
        serializer.serialize_u64(storage_rebate);
        serializer.serialize_u64(non_refundable_storage_fee);
        serializer.serialize_u64(epoch_start_timestamp_ms);
        TraitHelpers.serialize_vector_tuple3_SequenceNumber_vector_vector_u8_vector_ObjectID(system_packages, serializer);
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
        builder.protocol_version = ProtocolVersion.deserialize(deserializer);
        builder.storage_charge = deserializer.deserialize_u64();
        builder.computation_charge = deserializer.deserialize_u64();
        builder.storage_rebate = deserializer.deserialize_u64();
        builder.non_refundable_storage_fee = deserializer.deserialize_u64();
        builder.epoch_start_timestamp_ms = deserializer.deserialize_u64();
        builder.system_packages = TraitHelpers.deserialize_vector_tuple3_SequenceNumber_vector_vector_u8_vector_ObjectID(deserializer);
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
        if (!java.util.Objects.equals(this.protocol_version, other.protocol_version)) { return false; }
        if (!java.util.Objects.equals(this.storage_charge, other.storage_charge)) { return false; }
        if (!java.util.Objects.equals(this.computation_charge, other.computation_charge)) { return false; }
        if (!java.util.Objects.equals(this.storage_rebate, other.storage_rebate)) { return false; }
        if (!java.util.Objects.equals(this.non_refundable_storage_fee, other.non_refundable_storage_fee)) { return false; }
        if (!java.util.Objects.equals(this.epoch_start_timestamp_ms, other.epoch_start_timestamp_ms)) { return false; }
        if (!java.util.Objects.equals(this.system_packages, other.system_packages)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.epoch != null ? this.epoch.hashCode() : 0);
        value = 31 * value + (this.protocol_version != null ? this.protocol_version.hashCode() : 0);
        value = 31 * value + (this.storage_charge != null ? this.storage_charge.hashCode() : 0);
        value = 31 * value + (this.computation_charge != null ? this.computation_charge.hashCode() : 0);
        value = 31 * value + (this.storage_rebate != null ? this.storage_rebate.hashCode() : 0);
        value = 31 * value + (this.non_refundable_storage_fee != null ? this.non_refundable_storage_fee.hashCode() : 0);
        value = 31 * value + (this.epoch_start_timestamp_ms != null ? this.epoch_start_timestamp_ms.hashCode() : 0);
        value = 31 * value + (this.system_packages != null ? this.system_packages.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long epoch;
        public ProtocolVersion protocol_version;
        public @com.novi.serde.Unsigned Long storage_charge;
        public @com.novi.serde.Unsigned Long computation_charge;
        public @com.novi.serde.Unsigned Long storage_rebate;
        public @com.novi.serde.Unsigned Long non_refundable_storage_fee;
        public @com.novi.serde.Unsigned Long epoch_start_timestamp_ms;
        public java.util.List<com.novi.serde.Tuple3<SequenceNumber, java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>>, java.util.List<ObjectID>>> system_packages;

        public ChangeEpoch build() {
            return new ChangeEpoch(
                epoch,
                protocol_version,
                storage_charge,
                computation_charge,
                storage_rebate,
                non_refundable_storage_fee,
                epoch_start_timestamp_ms,
                system_packages
            );
        }
    }
}
