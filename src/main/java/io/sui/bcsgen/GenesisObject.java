package io.sui.bcsgen;


public abstract class GenesisObject {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static GenesisObject deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return RawObject.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for GenesisObject: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static GenesisObject bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        GenesisObject value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class RawObject extends GenesisObject {
        public final Data data;
        public final Owner owner;

        public RawObject(Data data, Owner owner) {
            java.util.Objects.requireNonNull(data, "data must not be null");
            java.util.Objects.requireNonNull(owner, "owner must not be null");
            this.data = data;
            this.owner = owner;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            data.serialize(serializer);
            owner.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static RawObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.data = Data.deserialize(deserializer);
            builder.owner = Owner.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            RawObject other = (RawObject) obj;
            if (!java.util.Objects.equals(this.data, other.data)) { return false; }
            if (!java.util.Objects.equals(this.owner, other.owner)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.data != null ? this.data.hashCode() : 0);
            value = 31 * value + (this.owner != null ? this.owner.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public Data data;
            public Owner owner;

            public RawObject build() {
                return new RawObject(
                    data,
                    owner
                );
            }
        }
    }
}

