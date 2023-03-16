package io.sui.bcsgen;


public abstract class ObjectArg {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static ObjectArg deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return ImmOrOwnedObject.load(deserializer);
            case 1: return SharedObject.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for ObjectArg: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ObjectArg bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ObjectArg value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class ImmOrOwnedObject extends ObjectArg {
        public final com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> value;

        public ImmOrOwnedObject(com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            TraitHelpers.serialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(value, serializer);
            serializer.decrease_container_depth();
        }

        static ImmOrOwnedObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_tuple3_ObjectID_SequenceNumber_ObjectDigest(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ImmOrOwnedObject other = (ImmOrOwnedObject) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public com.novi.serde.Tuple3<ObjectID, SequenceNumber, ObjectDigest> value;

            public ImmOrOwnedObject build() {
                return new ImmOrOwnedObject(
                    value
                );
            }
        }
    }

    public static final class SharedObject extends ObjectArg {
        public final ObjectID id;
        public final SequenceNumber initial_shared_version;
        public final Boolean mutable;

        public SharedObject(ObjectID id, SequenceNumber initial_shared_version, Boolean mutable) {
            java.util.Objects.requireNonNull(id, "id must not be null");
            java.util.Objects.requireNonNull(initial_shared_version, "initial_shared_version must not be null");
            java.util.Objects.requireNonNull(mutable, "mutable must not be null");
            this.id = id;
            this.initial_shared_version = initial_shared_version;
            this.mutable = mutable;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            id.serialize(serializer);
            initial_shared_version.serialize(serializer);
            serializer.serialize_bool(mutable);
            serializer.decrease_container_depth();
        }

        static SharedObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.id = ObjectID.deserialize(deserializer);
            builder.initial_shared_version = SequenceNumber.deserialize(deserializer);
            builder.mutable = deserializer.deserialize_bool();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SharedObject other = (SharedObject) obj;
            if (!java.util.Objects.equals(this.id, other.id)) { return false; }
            if (!java.util.Objects.equals(this.initial_shared_version, other.initial_shared_version)) { return false; }
            if (!java.util.Objects.equals(this.mutable, other.mutable)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.id != null ? this.id.hashCode() : 0);
            value = 31 * value + (this.initial_shared_version != null ? this.initial_shared_version.hashCode() : 0);
            value = 31 * value + (this.mutable != null ? this.mutable.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID id;
            public SequenceNumber initial_shared_version;
            public Boolean mutable;

            public SharedObject build() {
                return new SharedObject(
                    id,
                    initial_shared_version,
                    mutable
                );
            }
        }
    }
}

