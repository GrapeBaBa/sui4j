package io.sui.bcsgen;


public abstract class CommandArgumentError {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static CommandArgumentError deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return TypeMismatch.load(deserializer);
            case 1: return InvalidBCSBytes.load(deserializer);
            case 2: return InvalidUsageOfPureArg.load(deserializer);
            case 3: return InvalidArgumentToPrivateEntryFunction.load(deserializer);
            case 4: return IndexOutOfBounds.load(deserializer);
            case 5: return SecondaryIndexOutOfBounds.load(deserializer);
            case 6: return InvalidResultArity.load(deserializer);
            case 7: return InvalidGasCoinUsage.load(deserializer);
            case 8: return InvalidValueUsage.load(deserializer);
            case 9: return InvalidObjectByValue.load(deserializer);
            case 10: return InvalidObjectByMutRef.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for CommandArgumentError: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static CommandArgumentError bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        CommandArgumentError value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class TypeMismatch extends CommandArgumentError {
        public TypeMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            serializer.decrease_container_depth();
        }

        static TypeMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TypeMismatch other = (TypeMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public TypeMismatch build() {
                return new TypeMismatch(
                );
            }
        }
    }

    public static final class InvalidBCSBytes extends CommandArgumentError {
        public InvalidBCSBytes() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            serializer.decrease_container_depth();
        }

        static InvalidBCSBytes load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidBCSBytes other = (InvalidBCSBytes) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidBCSBytes build() {
                return new InvalidBCSBytes(
                );
            }
        }
    }

    public static final class InvalidUsageOfPureArg extends CommandArgumentError {
        public InvalidUsageOfPureArg() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            serializer.decrease_container_depth();
        }

        static InvalidUsageOfPureArg load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidUsageOfPureArg other = (InvalidUsageOfPureArg) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidUsageOfPureArg build() {
                return new InvalidUsageOfPureArg(
                );
            }
        }
    }

    public static final class InvalidArgumentToPrivateEntryFunction extends CommandArgumentError {
        public InvalidArgumentToPrivateEntryFunction() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            serializer.decrease_container_depth();
        }

        static InvalidArgumentToPrivateEntryFunction load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidArgumentToPrivateEntryFunction other = (InvalidArgumentToPrivateEntryFunction) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidArgumentToPrivateEntryFunction build() {
                return new InvalidArgumentToPrivateEntryFunction(
                );
            }
        }
    }

    public static final class IndexOutOfBounds extends CommandArgumentError {
        public final @com.novi.serde.Unsigned Short idx;

        public IndexOutOfBounds(@com.novi.serde.Unsigned Short idx) {
            java.util.Objects.requireNonNull(idx, "idx must not be null");
            this.idx = idx;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(4);
            serializer.serialize_u16(idx);
            serializer.decrease_container_depth();
        }

        static IndexOutOfBounds load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.idx = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            IndexOutOfBounds other = (IndexOutOfBounds) obj;
            if (!java.util.Objects.equals(this.idx, other.idx)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.idx != null ? this.idx.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short idx;

            public IndexOutOfBounds build() {
                return new IndexOutOfBounds(
                    idx
                );
            }
        }
    }

    public static final class SecondaryIndexOutOfBounds extends CommandArgumentError {
        public final @com.novi.serde.Unsigned Short result_idx;
        public final @com.novi.serde.Unsigned Short secondary_idx;

        public SecondaryIndexOutOfBounds(@com.novi.serde.Unsigned Short result_idx, @com.novi.serde.Unsigned Short secondary_idx) {
            java.util.Objects.requireNonNull(result_idx, "result_idx must not be null");
            java.util.Objects.requireNonNull(secondary_idx, "secondary_idx must not be null");
            this.result_idx = result_idx;
            this.secondary_idx = secondary_idx;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(5);
            serializer.serialize_u16(result_idx);
            serializer.serialize_u16(secondary_idx);
            serializer.decrease_container_depth();
        }

        static SecondaryIndexOutOfBounds load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.result_idx = deserializer.deserialize_u16();
            builder.secondary_idx = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SecondaryIndexOutOfBounds other = (SecondaryIndexOutOfBounds) obj;
            if (!java.util.Objects.equals(this.result_idx, other.result_idx)) { return false; }
            if (!java.util.Objects.equals(this.secondary_idx, other.secondary_idx)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.result_idx != null ? this.result_idx.hashCode() : 0);
            value = 31 * value + (this.secondary_idx != null ? this.secondary_idx.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short result_idx;
            public @com.novi.serde.Unsigned Short secondary_idx;

            public SecondaryIndexOutOfBounds build() {
                return new SecondaryIndexOutOfBounds(
                    result_idx,
                    secondary_idx
                );
            }
        }
    }

    public static final class InvalidResultArity extends CommandArgumentError {
        public final @com.novi.serde.Unsigned Short result_idx;

        public InvalidResultArity(@com.novi.serde.Unsigned Short result_idx) {
            java.util.Objects.requireNonNull(result_idx, "result_idx must not be null");
            this.result_idx = result_idx;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(6);
            serializer.serialize_u16(result_idx);
            serializer.decrease_container_depth();
        }

        static InvalidResultArity load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.result_idx = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidResultArity other = (InvalidResultArity) obj;
            if (!java.util.Objects.equals(this.result_idx, other.result_idx)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.result_idx != null ? this.result_idx.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short result_idx;

            public InvalidResultArity build() {
                return new InvalidResultArity(
                    result_idx
                );
            }
        }
    }

    public static final class InvalidGasCoinUsage extends CommandArgumentError {
        public InvalidGasCoinUsage() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(7);
            serializer.decrease_container_depth();
        }

        static InvalidGasCoinUsage load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidGasCoinUsage other = (InvalidGasCoinUsage) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidGasCoinUsage build() {
                return new InvalidGasCoinUsage(
                );
            }
        }
    }

    public static final class InvalidValueUsage extends CommandArgumentError {
        public InvalidValueUsage() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(8);
            serializer.decrease_container_depth();
        }

        static InvalidValueUsage load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidValueUsage other = (InvalidValueUsage) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidValueUsage build() {
                return new InvalidValueUsage(
                );
            }
        }
    }

    public static final class InvalidObjectByValue extends CommandArgumentError {
        public InvalidObjectByValue() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(9);
            serializer.decrease_container_depth();
        }

        static InvalidObjectByValue load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidObjectByValue other = (InvalidObjectByValue) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidObjectByValue build() {
                return new InvalidObjectByValue(
                );
            }
        }
    }

    public static final class InvalidObjectByMutRef extends CommandArgumentError {
        public InvalidObjectByMutRef() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(10);
            serializer.decrease_container_depth();
        }

        static InvalidObjectByMutRef load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidObjectByMutRef other = (InvalidObjectByMutRef) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidObjectByMutRef build() {
                return new InvalidObjectByMutRef(
                );
            }
        }
    }
}

