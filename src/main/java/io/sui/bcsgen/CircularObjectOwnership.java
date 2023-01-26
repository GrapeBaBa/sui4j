package io.sui.bcsgen;


public final class CircularObjectOwnership {
    public final ObjectID object;

    public CircularObjectOwnership(ObjectID object) {
        java.util.Objects.requireNonNull(object, "object must not be null");
        this.object = object;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        object.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static CircularObjectOwnership deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.object = ObjectID.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static CircularObjectOwnership bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        CircularObjectOwnership value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CircularObjectOwnership other = (CircularObjectOwnership) obj;
        if (!java.util.Objects.equals(this.object, other.object)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.object != null ? this.object.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ObjectID object;

        public CircularObjectOwnership build() {
            return new CircularObjectOwnership(
                object
            );
        }
    }
}
