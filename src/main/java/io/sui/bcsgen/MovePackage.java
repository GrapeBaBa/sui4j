package io.sui.bcsgen;


public final class MovePackage {
    public final ObjectID id;
    public final SequenceNumber version;
    public final java.util.Map<String, com.novi.serde.Bytes> module_map;
    public final java.util.List<TypeOrigin> type_origin_table;
    public final java.util.Map<ObjectID, UpgradeInfo> linkage_table;

    public MovePackage(ObjectID id, SequenceNumber version, java.util.Map<String, com.novi.serde.Bytes> module_map, java.util.List<TypeOrigin> type_origin_table, java.util.Map<ObjectID, UpgradeInfo> linkage_table) {
        java.util.Objects.requireNonNull(id, "id must not be null");
        java.util.Objects.requireNonNull(version, "version must not be null");
        java.util.Objects.requireNonNull(module_map, "module_map must not be null");
        java.util.Objects.requireNonNull(type_origin_table, "type_origin_table must not be null");
        java.util.Objects.requireNonNull(linkage_table, "linkage_table must not be null");
        this.id = id;
        this.version = version;
        this.module_map = module_map;
        this.type_origin_table = type_origin_table;
        this.linkage_table = linkage_table;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        id.serialize(serializer);
        version.serialize(serializer);
        TraitHelpers.serialize_map_str_to_bytes(module_map, serializer);
        TraitHelpers.serialize_vector_TypeOrigin(type_origin_table, serializer);
        TraitHelpers.serialize_map_ObjectID_to_UpgradeInfo(linkage_table, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MovePackage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.id = ObjectID.deserialize(deserializer);
        builder.version = SequenceNumber.deserialize(deserializer);
        builder.module_map = TraitHelpers.deserialize_map_str_to_bytes(deserializer);
        builder.type_origin_table = TraitHelpers.deserialize_vector_TypeOrigin(deserializer);
        builder.linkage_table = TraitHelpers.deserialize_map_ObjectID_to_UpgradeInfo(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MovePackage bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MovePackage value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MovePackage other = (MovePackage) obj;
        if (!java.util.Objects.equals(this.id, other.id)) { return false; }
        if (!java.util.Objects.equals(this.version, other.version)) { return false; }
        if (!java.util.Objects.equals(this.module_map, other.module_map)) { return false; }
        if (!java.util.Objects.equals(this.type_origin_table, other.type_origin_table)) { return false; }
        if (!java.util.Objects.equals(this.linkage_table, other.linkage_table)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.id != null ? this.id.hashCode() : 0);
        value = 31 * value + (this.version != null ? this.version.hashCode() : 0);
        value = 31 * value + (this.module_map != null ? this.module_map.hashCode() : 0);
        value = 31 * value + (this.type_origin_table != null ? this.type_origin_table.hashCode() : 0);
        value = 31 * value + (this.linkage_table != null ? this.linkage_table.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public ObjectID id;
        public SequenceNumber version;
        public java.util.Map<String, com.novi.serde.Bytes> module_map;
        public java.util.List<TypeOrigin> type_origin_table;
        public java.util.Map<ObjectID, UpgradeInfo> linkage_table;

        public MovePackage build() {
            return new MovePackage(
                id,
                version,
                module_map,
                type_origin_table,
                linkage_table
            );
        }
    }
}
