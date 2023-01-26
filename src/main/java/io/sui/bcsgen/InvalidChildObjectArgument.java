package io.sui.bcsgen;


public final class InvalidChildObjectArgument {
    public final ObjectID child;
    public final SuiAddress parent;

    public InvalidChildObjectArgument(ObjectID child, SuiAddress parent) {
        java.util.Objects.requireNonNull(child, "child must not be null");
        java.util.Objects.requireNonNull(parent, "parent must not be null");
        this.child = child;
        this.parent = parent;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        child.serialize(serializer);
        parent.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static InvalidChildObjectArgument deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.child = ObjectID.deserialize(deserializer);
        builder.parent = SuiAddress.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static InvalidChildObjectArgument bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        InvalidChildObjectArgument value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        InvalidChildObjectArgument other = (InvalidChildObjectArgument) obj;
        if (!java.util.Objects.equals(this.child, other.child)) { return false; }
        if (!java.util.Objects.equals(this.parent, other.parent)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.child != null ? this.child.hashCode() : 0);
        value = 31 * value + (this.parent != null ? this.parent.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ObjectID child;
        public SuiAddress parent;

        public InvalidChildObjectArgument build() {
            return new InvalidChildObjectArgument(
                child,
                parent
            );
        }
    }
}
