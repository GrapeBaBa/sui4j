package io.sui.bcsgen;


public final class StructTag {
    public final AccountAddress address;
    public final Identifier module;
    public final Identifier name;
    public final java.util.List<TypeTag> type_args;

    public StructTag(AccountAddress address, Identifier module, Identifier name, java.util.List<TypeTag> type_args) {
        java.util.Objects.requireNonNull(address, "address must not be null");
        java.util.Objects.requireNonNull(module, "module must not be null");
        java.util.Objects.requireNonNull(name, "name must not be null");
        java.util.Objects.requireNonNull(type_args, "type_args must not be null");
        this.address = address;
        this.module = module;
        this.name = name;
        this.type_args = type_args;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        address.serialize(serializer);
        module.serialize(serializer);
        name.serialize(serializer);
        TraitHelpers.serialize_vector_TypeTag(type_args, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static StructTag deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.address = AccountAddress.deserialize(deserializer);
        builder.module = Identifier.deserialize(deserializer);
        builder.name = Identifier.deserialize(deserializer);
        builder.type_args = TraitHelpers.deserialize_vector_TypeTag(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static StructTag bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        StructTag value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        StructTag other = (StructTag) obj;
        if (!java.util.Objects.equals(this.address, other.address)) { return false; }
        if (!java.util.Objects.equals(this.module, other.module)) { return false; }
        if (!java.util.Objects.equals(this.name, other.name)) { return false; }
        if (!java.util.Objects.equals(this.type_args, other.type_args)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.address != null ? this.address.hashCode() : 0);
        value = 31 * value + (this.module != null ? this.module.hashCode() : 0);
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        value = 31 * value + (this.type_args != null ? this.type_args.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress address;
        public Identifier module;
        public Identifier name;
        public java.util.List<TypeTag> type_args;

        public StructTag build() {
            return new StructTag(
                address,
                module,
                name,
                type_args
            );
        }
    }
}
