package io.sui.bcsgen;


public final class TypeOrigin {
    public final String module_name;
    public final String struct_name;
    public final ObjectID Package;

    public TypeOrigin(String module_name, String struct_name, ObjectID Package) {
        java.util.Objects.requireNonNull(module_name, "module_name must not be null");
        java.util.Objects.requireNonNull(struct_name, "struct_name must not be null");
        java.util.Objects.requireNonNull(Package, "Package must not be null");
        this.module_name = module_name;
        this.struct_name = struct_name;
        this.Package = Package;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(module_name);
        serializer.serialize_str(struct_name);
        Package.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TypeOrigin deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.module_name = deserializer.deserialize_str();
        builder.struct_name = deserializer.deserialize_str();
        builder.Package = ObjectID.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TypeOrigin bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TypeOrigin value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TypeOrigin other = (TypeOrigin) obj;
        if (!java.util.Objects.equals(this.module_name, other.module_name)) { return false; }
        if (!java.util.Objects.equals(this.struct_name, other.struct_name)) { return false; }
        if (!java.util.Objects.equals(this.Package, other.Package)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.module_name != null ? this.module_name.hashCode() : 0);
        value = 31 * value + (this.struct_name != null ? this.struct_name.hashCode() : 0);
        value = 31 * value + (this.Package != null ? this.Package.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String module_name;
        public String struct_name;
        public ObjectID Package;

        public TypeOrigin build() {
            return new TypeOrigin(
                module_name,
                struct_name,
                Package
            );
        }
    }
}
