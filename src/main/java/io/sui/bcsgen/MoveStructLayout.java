package io.sui.bcsgen;


public abstract class MoveStructLayout {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static MoveStructLayout deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return Runtime.load(deserializer);
            case 1: return WithFields.load(deserializer);
            case 2: return WithTypes.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for MoveStructLayout: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveStructLayout bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveStructLayout value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class Runtime extends MoveStructLayout {
        public final java.util.List<MoveTypeLayout> value;

        public Runtime(java.util.List<MoveTypeLayout> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            TraitHelpers.serialize_vector_MoveTypeLayout(value, serializer);
            serializer.decrease_container_depth();
        }

        static Runtime load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_vector_MoveTypeLayout(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Runtime other = (Runtime) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.List<MoveTypeLayout> value;

            public Runtime build() {
                return new Runtime(
                    value
                );
            }
        }
    }

    public static final class WithFields extends MoveStructLayout {
        public final java.util.List<MoveFieldLayout> value;

        public WithFields(java.util.List<MoveFieldLayout> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            TraitHelpers.serialize_vector_MoveFieldLayout(value, serializer);
            serializer.decrease_container_depth();
        }

        static WithFields load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_vector_MoveFieldLayout(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            WithFields other = (WithFields) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.List<MoveFieldLayout> value;

            public WithFields build() {
                return new WithFields(
                    value
                );
            }
        }
    }

    public static final class WithTypes extends MoveStructLayout {
        public final StructTag type_;
        public final java.util.List<MoveFieldLayout> fields;

        public WithTypes(StructTag type_, java.util.List<MoveFieldLayout> fields) {
            java.util.Objects.requireNonNull(type_, "type_ must not be null");
            java.util.Objects.requireNonNull(fields, "fields must not be null");
            this.type_ = type_;
            this.fields = fields;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            type_.serialize(serializer);
            TraitHelpers.serialize_vector_MoveFieldLayout(fields, serializer);
            serializer.decrease_container_depth();
        }

        static WithTypes load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.type_ = StructTag.deserialize(deserializer);
            builder.fields = TraitHelpers.deserialize_vector_MoveFieldLayout(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            WithTypes other = (WithTypes) obj;
            if (!java.util.Objects.equals(this.type_, other.type_)) { return false; }
            if (!java.util.Objects.equals(this.fields, other.fields)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.type_ != null ? this.type_.hashCode() : 0);
            value = 31 * value + (this.fields != null ? this.fields.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public StructTag type_;
            public java.util.List<MoveFieldLayout> fields;

            public WithTypes build() {
                return new WithTypes(
                    type_,
                    fields
                );
            }
        }
    }
}

