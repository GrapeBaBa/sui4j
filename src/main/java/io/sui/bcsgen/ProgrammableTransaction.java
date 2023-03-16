package io.sui.bcsgen;


public final class ProgrammableTransaction {
    public final java.util.List<CallArg> inputs;
    public final java.util.List<Command> commands;

    public ProgrammableTransaction(java.util.List<CallArg> inputs, java.util.List<Command> commands) {
        java.util.Objects.requireNonNull(inputs, "inputs must not be null");
        java.util.Objects.requireNonNull(commands, "commands must not be null");
        this.inputs = inputs;
        this.commands = commands;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_CallArg(inputs, serializer);
        TraitHelpers.serialize_vector_Command(commands, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ProgrammableTransaction deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.inputs = TraitHelpers.deserialize_vector_CallArg(deserializer);
        builder.commands = TraitHelpers.deserialize_vector_Command(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ProgrammableTransaction bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ProgrammableTransaction value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ProgrammableTransaction other = (ProgrammableTransaction) obj;
        if (!java.util.Objects.equals(this.inputs, other.inputs)) { return false; }
        if (!java.util.Objects.equals(this.commands, other.commands)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.inputs != null ? this.inputs.hashCode() : 0);
        value = 31 * value + (this.commands != null ? this.commands.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<CallArg> inputs;
        public java.util.List<Command> commands;

        public ProgrammableTransaction build() {
            return new ProgrammableTransaction(
                inputs,
                commands
            );
        }
    }
}
