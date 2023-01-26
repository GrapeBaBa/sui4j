package io.sui.bcsgen;


public final class MoveFieldLayout {
    public final Identifier name;
    public final MoveTypeLayout layout;

    public MoveFieldLayout(Identifier name, MoveTypeLayout layout) {
        java.util.Objects.requireNonNull(name, "name must not be null");
        java.util.Objects.requireNonNull(layout, "layout must not be null");
        this.name = name;
        this.layout = layout;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        name.serialize(serializer);
        layout.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveFieldLayout deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.name = Identifier.deserialize(deserializer);
        builder.layout = MoveTypeLayout.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MoveFieldLayout bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveFieldLayout value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MoveFieldLayout other = (MoveFieldLayout) obj;
        if (!java.util.Objects.equals(this.name, other.name)) { return false; }
        if (!java.util.Objects.equals(this.layout, other.layout)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        value = 31 * value + (this.layout != null ? this.layout.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public Identifier name;
        public MoveTypeLayout layout;

        public MoveFieldLayout build() {
            return new MoveFieldLayout(
                name,
                layout
            );
        }
    }
}
