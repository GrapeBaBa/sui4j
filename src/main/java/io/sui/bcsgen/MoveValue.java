package io.sui.bcsgen;


public abstract class MoveValue {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static MoveValue deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return U8.load(deserializer);
            case 1: return U64.load(deserializer);
            case 2: return U128.load(deserializer);
            case 3: return Bool.load(deserializer);
            case 4: return Address.load(deserializer);
            case 5: return Vector.load(deserializer);
            case 6: return Struct.load(deserializer);
            case 7: return Signer.load(deserializer);
            case 8: return U16.load(deserializer);
            case 9: return U32.load(deserializer);
            case 10: return U256.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for MoveValue: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MoveValue bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MoveValue value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class U8 extends MoveValue {
        public final @com.novi.serde.Unsigned Byte value;

        public U8(@com.novi.serde.Unsigned Byte value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(0);
            serializer.serialize_u8(value);
            serializer.decrease_container_depth();
        }

        static U8 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u8();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            U8 other = (U8) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Byte value;

            public U8 build() {
                return new U8(
                    value
                );
            }
        }
    }

    public static final class U64 extends MoveValue {
        public final @com.novi.serde.Unsigned Long value;

        public U64(@com.novi.serde.Unsigned Long value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(1);
            serializer.serialize_u64(value);
            serializer.decrease_container_depth();
        }

        static U64 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u64();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            U64 other = (U64) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long value;

            public U64 build() {
                return new U64(
                    value
                );
            }
        }
    }

    public static final class U128 extends MoveValue {
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger value;

        public U128(java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(2);
            serializer.serialize_u128(value);
            serializer.decrease_container_depth();
        }

        static U128 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u128();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            U128 other = (U128) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger value;

            public U128 build() {
                return new U128(
                    value
                );
            }
        }
    }

    public static final class Bool extends MoveValue {
        public final Boolean value;

        public Bool(Boolean value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(3);
            serializer.serialize_bool(value);
            serializer.decrease_container_depth();
        }

        static Bool load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_bool();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Bool other = (Bool) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public Boolean value;

            public Bool build() {
                return new Bool(
                    value
                );
            }
        }
    }

    public static final class Address extends MoveValue {
        public final AccountAddress value;

        public Address(AccountAddress value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(4);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static Address load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = AccountAddress.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Address other = (Address) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public AccountAddress value;

            public Address build() {
                return new Address(
                    value
                );
            }
        }
    }

    public static final class Vector extends MoveValue {
        public final java.util.List<MoveValue> value;

        public Vector(java.util.List<MoveValue> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(5);
            TraitHelpers.serialize_vector_MoveValue(value, serializer);
            serializer.decrease_container_depth();
        }

        static Vector load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_vector_MoveValue(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Vector other = (Vector) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.List<MoveValue> value;

            public Vector build() {
                return new Vector(
                    value
                );
            }
        }
    }

    public static final class Struct extends MoveValue {
        public final MoveStruct value;

        public Struct(MoveStruct value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(6);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static Struct load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = MoveStruct.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Struct other = (Struct) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public MoveStruct value;

            public Struct build() {
                return new Struct(
                    value
                );
            }
        }
    }

    public static final class Signer extends MoveValue {
        public final AccountAddress value;

        public Signer(AccountAddress value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(7);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static Signer load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = AccountAddress.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Signer other = (Signer) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public AccountAddress value;

            public Signer build() {
                return new Signer(
                    value
                );
            }
        }
    }

    public static final class U16 extends MoveValue {
        public final @com.novi.serde.Unsigned Short value;

        public U16(@com.novi.serde.Unsigned Short value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(8);
            serializer.serialize_u16(value);
            serializer.decrease_container_depth();
        }

        static U16 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
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
            U16 other = (U16) obj;
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

            public U16 build() {
                return new U16(
                    value
                );
            }
        }
    }

    public static final class U32 extends MoveValue {
        public final @com.novi.serde.Unsigned Integer value;

        public U32(@com.novi.serde.Unsigned Integer value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(9);
            serializer.serialize_u32(value);
            serializer.decrease_container_depth();
        }

        static U32 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = deserializer.deserialize_u32();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            U32 other = (U32) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Integer value;

            public U32 build() {
                return new U32(
                    value
                );
            }
        }
    }

    public static final class U256 extends MoveValue {
        public final java.util.@com.novi.serde.ArrayLen(length=32) List<@com.novi.serde.Unsigned Byte> value;

        public U256(java.util.@com.novi.serde.ArrayLen(length=32) List<@com.novi.serde.Unsigned Byte> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
//            serializer.serialize_variant_index(10);
            TraitHelpers.serialize_array32_u8_array(value, serializer);
            serializer.decrease_container_depth();
        }

        static U256 load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_array32_u8_array(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            U256 other = (U256) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.@com.novi.serde.ArrayLen(length=32) List<@com.novi.serde.Unsigned Byte> value;

            public U256 build() {
                return new U256(
                    value
                );
            }
        }
    }
}

