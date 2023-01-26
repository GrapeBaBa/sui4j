package io.sui.bcsgen;


public final class MoveLocation {
    public final ModuleId module;
    public final @com.novi.serde.Unsigned Short function;
    public final @com.novi.serde.Unsigned Short instruction;

    public MoveLocation(ModuleId module, @com.novi.serde.Unsigned Short function, @com.novi.serde.Unsigned Short instruction) {
        java.util.Objects.requireNonNull(module, "module must not be null");
        java.util.Objects.requireNonNull(function, "function must not be null");
        java.util.Objects.requireNonNull(instruction, "instruction must not be null");
        this.module = module;
        this.function = function;
        this.instruction = instruction;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        module.serialize(serializer);
        serializer.serialize_u16(function);
        serializer.serialize_u16(instruction);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveLocation deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.module = ModuleId.deserialize(deserializer);
        builder.function = deserializer.deserialize_u16();
        builder.instruction = deserializer.deserialize_u16();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MoveLocation bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveLocation value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MoveLocation other = (MoveLocation) obj;
        if (!java.util.Objects.equals(this.module, other.module)) { return false; }
        if (!java.util.Objects.equals(this.function, other.function)) { return false; }
        if (!java.util.Objects.equals(this.instruction, other.instruction)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.module != null ? this.module.hashCode() : 0);
        value = 31 * value + (this.function != null ? this.function.hashCode() : 0);
        value = 31 * value + (this.instruction != null ? this.instruction.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ModuleId module;
        public @com.novi.serde.Unsigned Short function;
        public @com.novi.serde.Unsigned Short instruction;

        public MoveLocation build() {
            return new MoveLocation(
                module,
                function,
                instruction
            );
        }
    }
}
