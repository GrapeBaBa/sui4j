package io.sui.bcsgen;


public abstract class PackageUpgradeError {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static PackageUpgradeError deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return UnableToFetchPackage.load(deserializer);
            case 1: return NotAPackage.load(deserializer);
            case 2: return IncompatibleUpgrade.load(deserializer);
            case 3: return DigestDoesNotMatch.load(deserializer);
            case 4: return UnknownUpgradePolicy.load(deserializer);
            case 5: return PackageIDDoesNotMatch.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for PackageUpgradeError: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static PackageUpgradeError bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        PackageUpgradeError value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class UnableToFetchPackage extends PackageUpgradeError {
        public final ObjectID package_id;

        public UnableToFetchPackage(ObjectID package_id) {
            java.util.Objects.requireNonNull(package_id, "package_id must not be null");
            this.package_id = package_id;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            package_id.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static UnableToFetchPackage load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.package_id = ObjectID.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            UnableToFetchPackage other = (UnableToFetchPackage) obj;
            if (!java.util.Objects.equals(this.package_id, other.package_id)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.package_id != null ? this.package_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID package_id;

            public UnableToFetchPackage build() {
                return new UnableToFetchPackage(
                    package_id
                );
            }
        }
    }

    public static final class NotAPackage extends PackageUpgradeError {
        public final ObjectID object_id;

        public NotAPackage(ObjectID object_id) {
            java.util.Objects.requireNonNull(object_id, "object_id must not be null");
            this.object_id = object_id;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            object_id.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static NotAPackage load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.object_id = ObjectID.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            NotAPackage other = (NotAPackage) obj;
            if (!java.util.Objects.equals(this.object_id, other.object_id)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.object_id != null ? this.object_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID object_id;

            public NotAPackage build() {
                return new NotAPackage(
                    object_id
                );
            }
        }
    }

    public static final class IncompatibleUpgrade extends PackageUpgradeError {
        public IncompatibleUpgrade() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            serializer.decrease_container_depth();
        }

        static IncompatibleUpgrade load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            IncompatibleUpgrade other = (IncompatibleUpgrade) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public IncompatibleUpgrade build() {
                return new IncompatibleUpgrade(
                );
            }
        }
    }

    public static final class DigestDoesNotMatch extends PackageUpgradeError {
        public final java.util.List<@com.novi.serde.Unsigned Byte> digest;

        public DigestDoesNotMatch(java.util.List<@com.novi.serde.Unsigned Byte> digest) {
            java.util.Objects.requireNonNull(digest, "digest must not be null");
            this.digest = digest;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            TraitHelpers.serialize_vector_u8(digest, serializer);
            serializer.decrease_container_depth();
        }

        static DigestDoesNotMatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.digest = TraitHelpers.deserialize_vector_u8(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            DigestDoesNotMatch other = (DigestDoesNotMatch) obj;
            if (!java.util.Objects.equals(this.digest, other.digest)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.digest != null ? this.digest.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.util.List<@com.novi.serde.Unsigned Byte> digest;

            public DigestDoesNotMatch build() {
                return new DigestDoesNotMatch(
                    digest
                );
            }
        }
    }

    public static final class UnknownUpgradePolicy extends PackageUpgradeError {
        public final @com.novi.serde.Unsigned Byte policy;

        public UnknownUpgradePolicy(@com.novi.serde.Unsigned Byte policy) {
            java.util.Objects.requireNonNull(policy, "policy must not be null");
            this.policy = policy;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(4);
            serializer.serialize_u8(policy);
            serializer.decrease_container_depth();
        }

        static UnknownUpgradePolicy load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.policy = deserializer.deserialize_u8();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            UnknownUpgradePolicy other = (UnknownUpgradePolicy) obj;
            if (!java.util.Objects.equals(this.policy, other.policy)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.policy != null ? this.policy.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Byte policy;

            public UnknownUpgradePolicy build() {
                return new UnknownUpgradePolicy(
                    policy
                );
            }
        }
    }

    public static final class PackageIDDoesNotMatch extends PackageUpgradeError {
        public final ObjectID package_id;
        public final ObjectID ticket_id;

        public PackageIDDoesNotMatch(ObjectID package_id, ObjectID ticket_id) {
            java.util.Objects.requireNonNull(package_id, "package_id must not be null");
            java.util.Objects.requireNonNull(ticket_id, "ticket_id must not be null");
            this.package_id = package_id;
            this.ticket_id = ticket_id;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(5);
            package_id.serialize(serializer);
            ticket_id.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static PackageIDDoesNotMatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.package_id = ObjectID.deserialize(deserializer);
            builder.ticket_id = ObjectID.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PackageIDDoesNotMatch other = (PackageIDDoesNotMatch) obj;
            if (!java.util.Objects.equals(this.package_id, other.package_id)) { return false; }
            if (!java.util.Objects.equals(this.ticket_id, other.ticket_id)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.package_id != null ? this.package_id.hashCode() : 0);
            value = 31 * value + (this.ticket_id != null ? this.ticket_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID package_id;
            public ObjectID ticket_id;

            public PackageIDDoesNotMatch build() {
                return new PackageIDDoesNotMatch(
                    package_id,
                    ticket_id
                );
            }
        }
    }
}

