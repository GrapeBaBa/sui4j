package io.sui.bcsgen;


public final class UpgradeInfo {
    public final ObjectID upgraded_id;
    public final SequenceNumber upgraded_version;

    public UpgradeInfo(ObjectID upgraded_id, SequenceNumber upgraded_version) {
        java.util.Objects.requireNonNull(upgraded_id, "upgraded_id must not be null");
        java.util.Objects.requireNonNull(upgraded_version, "upgraded_version must not be null");
        this.upgraded_id = upgraded_id;
        this.upgraded_version = upgraded_version;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        upgraded_id.serialize(serializer);
        upgraded_version.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static UpgradeInfo deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.upgraded_id = ObjectID.deserialize(deserializer);
        builder.upgraded_version = SequenceNumber.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static UpgradeInfo bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        UpgradeInfo value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UpgradeInfo other = (UpgradeInfo) obj;
        if (!java.util.Objects.equals(this.upgraded_id, other.upgraded_id)) { return false; }
        if (!java.util.Objects.equals(this.upgraded_version, other.upgraded_version)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.upgraded_id != null ? this.upgraded_id.hashCode() : 0);
        value = 31 * value + (this.upgraded_version != null ? this.upgraded_version.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ObjectID upgraded_id;
        public SequenceNumber upgraded_version;

        public UpgradeInfo build() {
            return new UpgradeInfo(
                upgraded_id,
                upgraded_version
            );
        }
    }
}
