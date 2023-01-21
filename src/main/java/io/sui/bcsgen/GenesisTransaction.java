package io.sui.bcsgen;


public final class GenesisTransaction {
    public final java.util.List<GenesisObject> objects;

    public GenesisTransaction(java.util.List<GenesisObject> objects) {
        java.util.Objects.requireNonNull(objects, "objects must not be null");
        this.objects = objects;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_GenesisObject(objects, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static GenesisTransaction deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.objects = TraitHelpers.deserialize_vector_GenesisObject(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static GenesisTransaction bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        GenesisTransaction value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        GenesisTransaction other = (GenesisTransaction) obj;
        if (!java.util.Objects.equals(this.objects, other.objects)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.objects != null ? this.objects.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<GenesisObject> objects;

        public GenesisTransaction build() {
            return new GenesisTransaction(
                objects
            );
        }
    }
}
