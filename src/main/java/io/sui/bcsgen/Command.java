package io.sui.bcsgen;


public abstract class Command {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static Command deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return MoveCall.load(deserializer);
            case 1: return TransferObjects.load(deserializer);
            case 2: return SplitCoins.load(deserializer);
            case 3: return MergeCoins.load(deserializer);
            case 4: return Publish.load(deserializer);
            case 5: return MakeMoveVec.load(deserializer);
            case 6: return Upgrade.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for Command: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static Command bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        Command value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class MoveCall extends Command {
        public final ProgrammableMoveCall value;

        public MoveCall(ProgrammableMoveCall value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static MoveCall load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = ProgrammableMoveCall.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MoveCall other = (MoveCall) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ProgrammableMoveCall value;

            public MoveCall build() {
                return new MoveCall(
                    value
                );
            }
        }
    }

    public static final class TransferObjects extends Command {
        public final java.util.List<Argument> field0;
        public final Argument field1;

        public TransferObjects(java.util.List<Argument> field0, Argument field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            TraitHelpers.serialize_vector_Argument(field0, serializer);
            field1.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static TransferObjects load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = TraitHelpers.deserialize_vector_Argument(deserializer);
            builder.field1 = Argument.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TransferObjects other = (TransferObjects) obj;
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
            public java.util.List<Argument> field0;
            public Argument field1;

            public TransferObjects build() {
                return new TransferObjects(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class SplitCoins extends Command {
        public final Argument field0;
        public final java.util.List<Argument> field1;

        public SplitCoins(Argument field0, java.util.List<Argument> field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            field0.serialize(serializer);
            TraitHelpers.serialize_vector_Argument(field1, serializer);
            serializer.decrease_container_depth();
        }

        static SplitCoins load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = Argument.deserialize(deserializer);
            builder.field1 = TraitHelpers.deserialize_vector_Argument(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SplitCoins other = (SplitCoins) obj;
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
            public Argument field0;
            public java.util.List<Argument> field1;

            public SplitCoins build() {
                return new SplitCoins(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class MergeCoins extends Command {
        public final Argument field0;
        public final java.util.List<Argument> field1;

        public MergeCoins(Argument field0, java.util.List<Argument> field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            field0.serialize(serializer);
            TraitHelpers.serialize_vector_Argument(field1, serializer);
            serializer.decrease_container_depth();
        }

        static MergeCoins load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = Argument.deserialize(deserializer);
            builder.field1 = TraitHelpers.deserialize_vector_Argument(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MergeCoins other = (MergeCoins) obj;
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
            public Argument field0;
            public java.util.List<Argument> field1;

            public MergeCoins build() {
                return new MergeCoins(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class Publish extends Command {
        public final java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0;
        public final java.util.List<ObjectID> field1;

        public Publish(java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0, java.util.List<ObjectID> field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(4);
            TraitHelpers.serialize_vector_vector_u8(field0, serializer);
            TraitHelpers.serialize_vector_ObjectID(field1, serializer);
            serializer.decrease_container_depth();
        }

        static Publish load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = TraitHelpers.deserialize_vector_vector_u8(deserializer);
            builder.field1 = TraitHelpers.deserialize_vector_ObjectID(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Publish other = (Publish) obj;
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
            public java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0;
            public java.util.List<ObjectID> field1;

            public Publish build() {
                return new Publish(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class MakeMoveVec extends Command {
        public final java.util.Optional<TypeTag> field0;
        public final java.util.List<Argument> field1;

        public MakeMoveVec(java.util.Optional<TypeTag> field0, java.util.List<Argument> field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(5);
            TraitHelpers.serialize_option_TypeTag(field0, serializer);
            TraitHelpers.serialize_vector_Argument(field1, serializer);
            serializer.decrease_container_depth();
        }

        static MakeMoveVec load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = TraitHelpers.deserialize_option_TypeTag(deserializer);
            builder.field1 = TraitHelpers.deserialize_vector_Argument(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MakeMoveVec other = (MakeMoveVec) obj;
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
            public java.util.Optional<TypeTag> field0;
            public java.util.List<Argument> field1;

            public MakeMoveVec build() {
                return new MakeMoveVec(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class Upgrade extends Command {
        public final java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0;
        public final java.util.List<ObjectID> field1;
        public final ObjectID field2;
        public final Argument field3;

        public Upgrade(java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0, java.util.List<ObjectID> field1, ObjectID field2, Argument field3) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            java.util.Objects.requireNonNull(field2, "field2 must not be null");
            java.util.Objects.requireNonNull(field3, "field3 must not be null");
            this.field0 = field0;
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(6);
            TraitHelpers.serialize_vector_vector_u8(field0, serializer);
            TraitHelpers.serialize_vector_ObjectID(field1, serializer);
            field2.serialize(serializer);
            field3.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static Upgrade load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = TraitHelpers.deserialize_vector_vector_u8(deserializer);
            builder.field1 = TraitHelpers.deserialize_vector_ObjectID(deserializer);
            builder.field2 = ObjectID.deserialize(deserializer);
            builder.field3 = Argument.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Upgrade other = (Upgrade) obj;
            if (!java.util.Objects.equals(this.field0, other.field0)) { return false; }
            if (!java.util.Objects.equals(this.field1, other.field1)) { return false; }
            if (!java.util.Objects.equals(this.field2, other.field2)) { return false; }
            if (!java.util.Objects.equals(this.field3, other.field3)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.field0 != null ? this.field0.hashCode() : 0);
            value = 31 * value + (this.field1 != null ? this.field1.hashCode() : 0);
            value = 31 * value + (this.field2 != null ? this.field2.hashCode() : 0);
            value = 31 * value + (this.field3 != null ? this.field3.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.List<java.util.List<@com.novi.serde.Unsigned Byte>> field0;
            public java.util.List<ObjectID> field1;
            public ObjectID field2;
            public Argument field3;

            public Upgrade build() {
                return new Upgrade(
                    field0,
                    field1,
                    field2,
                    field3
                );
            }
        }
    }
}

