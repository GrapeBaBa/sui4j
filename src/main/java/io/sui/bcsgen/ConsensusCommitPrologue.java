package io.sui.bcsgen;


public final class ConsensusCommitPrologue {
    public final @com.novi.serde.Unsigned Long epoch;
    public final @com.novi.serde.Unsigned Long round;
    public final @com.novi.serde.Unsigned Long commit_timestamp_ms;

    public ConsensusCommitPrologue(@com.novi.serde.Unsigned Long epoch, @com.novi.serde.Unsigned Long round, @com.novi.serde.Unsigned Long commit_timestamp_ms) {
        java.util.Objects.requireNonNull(epoch, "epoch must not be null");
        java.util.Objects.requireNonNull(round, "round must not be null");
        java.util.Objects.requireNonNull(commit_timestamp_ms, "commit_timestamp_ms must not be null");
        this.epoch = epoch;
        this.round = round;
        this.commit_timestamp_ms = commit_timestamp_ms;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(epoch);
        serializer.serialize_u64(round);
        serializer.serialize_u64(commit_timestamp_ms);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ConsensusCommitPrologue deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.epoch = deserializer.deserialize_u64();
        builder.round = deserializer.deserialize_u64();
        builder.commit_timestamp_ms = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ConsensusCommitPrologue bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ConsensusCommitPrologue value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ConsensusCommitPrologue other = (ConsensusCommitPrologue) obj;
        if (!java.util.Objects.equals(this.epoch, other.epoch)) { return false; }
        if (!java.util.Objects.equals(this.round, other.round)) { return false; }
        if (!java.util.Objects.equals(this.commit_timestamp_ms, other.commit_timestamp_ms)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.epoch != null ? this.epoch.hashCode() : 0);
        value = 31 * value + (this.round != null ? this.round.hashCode() : 0);
        value = 31 * value + (this.commit_timestamp_ms != null ? this.commit_timestamp_ms.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long epoch;
        public @com.novi.serde.Unsigned Long round;
        public @com.novi.serde.Unsigned Long commit_timestamp_ms;

        public ConsensusCommitPrologue build() {
            return new ConsensusCommitPrologue(
                epoch,
                round,
                commit_timestamp_ms
            );
        }
    }
}
