package io.sui.bcsgen;


public abstract class Argument {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static Argument deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return GasCoin.load(deserializer);
            case 1: return Input.load(deserializer);
            case 2: return Result.load(deserializer);
            case 3: return NestedResult.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for Argument: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static Argument bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        Argument value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class GasCoin extends Argument {
        public GasCoin() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            serializer.decrease_container_depth();
        }

        static GasCoin load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            GasCoin other = (GasCoin) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public GasCoin build() {
                return new GasCoin(
                );
            }
        }
    }

    public static final class Input extends Argument {
        public final @com.novi.serde.Unsigned Short value;

        public Input(@com.novi.serde.Unsigned Short value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            serializer.serialize_u16(value);
            serializer.decrease_container_depth();
        }

        static Input load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Input other = (Input) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short value;

            public Input build() {
                return new Input(
                    value
                );
            }
        }
    }

    public static final class Result extends Argument {
        public final @com.novi.serde.Unsigned Short value;

        public Result(@com.novi.serde.Unsigned Short value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            serializer.serialize_u16(value);
            serializer.decrease_container_depth();
        }

        static Result load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Result other = (Result) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short value;

            public Result build() {
                return new Result(
                    value
                );
            }
        }
    }

    public static final class NestedResult extends Argument {
        public final @com.novi.serde.Unsigned Short field0;
        public final @com.novi.serde.Unsigned Short field1;

        public NestedResult(@com.novi.serde.Unsigned Short field0, @com.novi.serde.Unsigned Short field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            serializer.serialize_u16(field0);
            serializer.serialize_u16(field1);
            serializer.decrease_container_depth();
        }

        static NestedResult load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = deserializer.deserialize_u16();
            builder.field1 = deserializer.deserialize_u16();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            NestedResult other = (NestedResult) obj;
            if (!java.util.Objects.equals(this.field0, other.field0)) { return false; }
            if (!java.util.Objects.equals(this.field1, other.field1)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.field0 != null ? this.field0.hashCode() : 0);
            value = 31 * value + (this.field1 != null ? this.field1.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short field0;
            public @com.novi.serde.Unsigned Short field1;

            public NestedResult build() {
                return new NestedResult(
                    field0,
                    field1
                );
            }
        }
    }
}

