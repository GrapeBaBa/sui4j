package io.sui.bcsgen;


public abstract class ExecutionFailureStatus {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static ExecutionFailureStatus deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return InsufficientGas.load(deserializer);
            case 1: return InvalidGasObject.load(deserializer);
            case 2: return InvariantViolation.load(deserializer);
            case 3: return FeatureNotYetSupported.load(deserializer);
            case 4: return MoveObjectTooBig.load(deserializer);
            case 5: return MovePackageTooBig.load(deserializer);
            case 6: return CircularObjectOwnership.load(deserializer);
            case 7: return InsufficientCoinBalance.load(deserializer);
            case 8: return CoinBalanceOverflow.load(deserializer);
            case 9: return PublishErrorNonZeroAddress.load(deserializer);
            case 10: return SuiMoveVerificationError.load(deserializer);
            case 11: return MovePrimitiveRuntimeError.load(deserializer);
            case 12: return MoveAbort.load(deserializer);
            case 13: return VMVerificationOrDeserializationError.load(deserializer);
            case 14: return VMInvariantViolation.load(deserializer);
            case 15: return FunctionNotFound.load(deserializer);
            case 16: return ArityMismatch.load(deserializer);
            case 17: return TypeArityMismatch.load(deserializer);
            case 18: return NonEntryFunctionInvoked.load(deserializer);
            case 19: return CommandArgumentError.load(deserializer);
            case 20: return TypeArgumentError.load(deserializer);
            case 21: return UnusedValueWithoutDrop.load(deserializer);
            case 22: return InvalidPublicFunctionReturnType.load(deserializer);
            case 23: return InvalidTransferObject.load(deserializer);
            case 24: return EffectsTooLarge.load(deserializer);
            default: throw new com.novi.serde.DeserializationError("Unknown variant index for ExecutionFailureStatus: " + index);
        }
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ExecutionFailureStatus bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ExecutionFailureStatus value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static final class InsufficientGas extends ExecutionFailureStatus {
        public InsufficientGas() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            serializer.decrease_container_depth();
        }

        static InsufficientGas load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InsufficientGas other = (InsufficientGas) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InsufficientGas build() {
                return new InsufficientGas(
                );
            }
        }
    }

    public static final class InvalidGasObject extends ExecutionFailureStatus {
        public InvalidGasObject() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            serializer.decrease_container_depth();
        }

        static InvalidGasObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidGasObject other = (InvalidGasObject) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidGasObject build() {
                return new InvalidGasObject(
                );
            }
        }
    }

    public static final class InvariantViolation extends ExecutionFailureStatus {
        public InvariantViolation() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            serializer.decrease_container_depth();
        }

        static InvariantViolation load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvariantViolation other = (InvariantViolation) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvariantViolation build() {
                return new InvariantViolation(
                );
            }
        }
    }

    public static final class FeatureNotYetSupported extends ExecutionFailureStatus {
        public FeatureNotYetSupported() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            serializer.decrease_container_depth();
        }

        static FeatureNotYetSupported load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            FeatureNotYetSupported other = (FeatureNotYetSupported) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public FeatureNotYetSupported build() {
                return new FeatureNotYetSupported(
                );
            }
        }
    }

    public static final class MoveObjectTooBig extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Long object_size;
        public final @com.novi.serde.Unsigned Long max_object_size;

        public MoveObjectTooBig(@com.novi.serde.Unsigned Long object_size, @com.novi.serde.Unsigned Long max_object_size) {
            java.util.Objects.requireNonNull(object_size, "object_size must not be null");
            java.util.Objects.requireNonNull(max_object_size, "max_object_size must not be null");
            this.object_size = object_size;
            this.max_object_size = max_object_size;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(4);
            serializer.serialize_u64(object_size);
            serializer.serialize_u64(max_object_size);
            serializer.decrease_container_depth();
        }

        static MoveObjectTooBig load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.object_size = deserializer.deserialize_u64();
            builder.max_object_size = deserializer.deserialize_u64();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MoveObjectTooBig other = (MoveObjectTooBig) obj;
            if (!java.util.Objects.equals(this.object_size, other.object_size)) { return false; }
            if (!java.util.Objects.equals(this.max_object_size, other.max_object_size)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.object_size != null ? this.object_size.hashCode() : 0);
            value = 31 * value + (this.max_object_size != null ? this.max_object_size.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long object_size;
            public @com.novi.serde.Unsigned Long max_object_size;

            public MoveObjectTooBig build() {
                return new MoveObjectTooBig(
                    object_size,
                    max_object_size
                );
            }
        }
    }

    public static final class MovePackageTooBig extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Long object_size;
        public final @com.novi.serde.Unsigned Long max_object_size;

        public MovePackageTooBig(@com.novi.serde.Unsigned Long object_size, @com.novi.serde.Unsigned Long max_object_size) {
            java.util.Objects.requireNonNull(object_size, "object_size must not be null");
            java.util.Objects.requireNonNull(max_object_size, "max_object_size must not be null");
            this.object_size = object_size;
            this.max_object_size = max_object_size;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(5);
            serializer.serialize_u64(object_size);
            serializer.serialize_u64(max_object_size);
            serializer.decrease_container_depth();
        }

        static MovePackageTooBig load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.object_size = deserializer.deserialize_u64();
            builder.max_object_size = deserializer.deserialize_u64();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MovePackageTooBig other = (MovePackageTooBig) obj;
            if (!java.util.Objects.equals(this.object_size, other.object_size)) { return false; }
            if (!java.util.Objects.equals(this.max_object_size, other.max_object_size)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.object_size != null ? this.object_size.hashCode() : 0);
            value = 31 * value + (this.max_object_size != null ? this.max_object_size.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long object_size;
            public @com.novi.serde.Unsigned Long max_object_size;

            public MovePackageTooBig build() {
                return new MovePackageTooBig(
                    object_size,
                    max_object_size
                );
            }
        }
    }

    public static final class CircularObjectOwnership extends ExecutionFailureStatus {
        public final ObjectID object;

        public CircularObjectOwnership(ObjectID object) {
            java.util.Objects.requireNonNull(object, "object must not be null");
            this.object = object;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(6);
            object.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static CircularObjectOwnership load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.object = ObjectID.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CircularObjectOwnership other = (CircularObjectOwnership) obj;
            if (!java.util.Objects.equals(this.object, other.object)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.object != null ? this.object.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID object;

            public CircularObjectOwnership build() {
                return new CircularObjectOwnership(
                    object
                );
            }
        }
    }

    public static final class InsufficientCoinBalance extends ExecutionFailureStatus {
        public InsufficientCoinBalance() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(7);
            serializer.decrease_container_depth();
        }

        static InsufficientCoinBalance load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InsufficientCoinBalance other = (InsufficientCoinBalance) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InsufficientCoinBalance build() {
                return new InsufficientCoinBalance(
                );
            }
        }
    }

    public static final class CoinBalanceOverflow extends ExecutionFailureStatus {
        public CoinBalanceOverflow() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(8);
            serializer.decrease_container_depth();
        }

        static CoinBalanceOverflow load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CoinBalanceOverflow other = (CoinBalanceOverflow) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public CoinBalanceOverflow build() {
                return new CoinBalanceOverflow(
                );
            }
        }
    }

    public static final class PublishErrorNonZeroAddress extends ExecutionFailureStatus {
        public PublishErrorNonZeroAddress() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(9);
            serializer.decrease_container_depth();
        }

        static PublishErrorNonZeroAddress load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PublishErrorNonZeroAddress other = (PublishErrorNonZeroAddress) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public PublishErrorNonZeroAddress build() {
                return new PublishErrorNonZeroAddress(
                );
            }
        }
    }

    public static final class SuiMoveVerificationError extends ExecutionFailureStatus {
        public SuiMoveVerificationError() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(10);
            serializer.decrease_container_depth();
        }

        static SuiMoveVerificationError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SuiMoveVerificationError other = (SuiMoveVerificationError) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public SuiMoveVerificationError build() {
                return new SuiMoveVerificationError(
                );
            }
        }
    }

    public static final class MovePrimitiveRuntimeError extends ExecutionFailureStatus {
        public final MoveLocationOpt value;

        public MovePrimitiveRuntimeError(MoveLocationOpt value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(11);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static MovePrimitiveRuntimeError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = MoveLocationOpt.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MovePrimitiveRuntimeError other = (MovePrimitiveRuntimeError) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public MoveLocationOpt value;

            public MovePrimitiveRuntimeError build() {
                return new MovePrimitiveRuntimeError(
                    value
                );
            }
        }
    }

    public static final class MoveAbort extends ExecutionFailureStatus {
        public final MoveLocation field0;
        public final @com.novi.serde.Unsigned Long field1;

        public MoveAbort(MoveLocation field0, @com.novi.serde.Unsigned Long field1) {
            java.util.Objects.requireNonNull(field0, "field0 must not be null");
            java.util.Objects.requireNonNull(field1, "field1 must not be null");
            this.field0 = field0;
            this.field1 = field1;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(12);
            field0.serialize(serializer);
            serializer.serialize_u64(field1);
            serializer.decrease_container_depth();
        }

        static MoveAbort load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.field0 = MoveLocation.deserialize(deserializer);
            builder.field1 = deserializer.deserialize_u64();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MoveAbort other = (MoveAbort) obj;
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
            public MoveLocation field0;
            public @com.novi.serde.Unsigned Long field1;

            public MoveAbort build() {
                return new MoveAbort(
                    field0,
                    field1
                );
            }
        }
    }

    public static final class VMVerificationOrDeserializationError extends ExecutionFailureStatus {
        public VMVerificationOrDeserializationError() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(13);
            serializer.decrease_container_depth();
        }

        static VMVerificationOrDeserializationError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            VMVerificationOrDeserializationError other = (VMVerificationOrDeserializationError) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public VMVerificationOrDeserializationError build() {
                return new VMVerificationOrDeserializationError(
                );
            }
        }
    }

    public static final class VMInvariantViolation extends ExecutionFailureStatus {
        public VMInvariantViolation() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(14);
            serializer.decrease_container_depth();
        }

        static VMInvariantViolation load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            VMInvariantViolation other = (VMInvariantViolation) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public VMInvariantViolation build() {
                return new VMInvariantViolation(
                );
            }
        }
    }

    public static final class FunctionNotFound extends ExecutionFailureStatus {
        public FunctionNotFound() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(15);
            serializer.decrease_container_depth();
        }

        static FunctionNotFound load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            FunctionNotFound other = (FunctionNotFound) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public FunctionNotFound build() {
                return new FunctionNotFound(
                );
            }
        }
    }

    public static final class ArityMismatch extends ExecutionFailureStatus {
        public ArityMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(16);
            serializer.decrease_container_depth();
        }

        static ArityMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ArityMismatch other = (ArityMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public ArityMismatch build() {
                return new ArityMismatch(
                );
            }
        }
    }

    public static final class TypeArityMismatch extends ExecutionFailureStatus {
        public TypeArityMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(17);
            serializer.decrease_container_depth();
        }

        static TypeArityMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TypeArityMismatch other = (TypeArityMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public TypeArityMismatch build() {
                return new TypeArityMismatch(
                );
            }
        }
    }

    public static final class NonEntryFunctionInvoked extends ExecutionFailureStatus {
        public NonEntryFunctionInvoked() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(18);
            serializer.decrease_container_depth();
        }

        static NonEntryFunctionInvoked load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            NonEntryFunctionInvoked other = (NonEntryFunctionInvoked) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public NonEntryFunctionInvoked build() {
                return new NonEntryFunctionInvoked(
                );
            }
        }
    }

    public static final class CommandArgumentError extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Short arg_idx;
        public final io.sui.bcsgen.CommandArgumentError kind;

        public CommandArgumentError(@com.novi.serde.Unsigned Short arg_idx, io.sui.bcsgen.CommandArgumentError kind) {
            java.util.Objects.requireNonNull(arg_idx, "arg_idx must not be null");
            java.util.Objects.requireNonNull(kind, "kind must not be null");
            this.arg_idx = arg_idx;
            this.kind = kind;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(19);
            serializer.serialize_u16(arg_idx);
            kind.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static CommandArgumentError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.arg_idx = deserializer.deserialize_u16();
            builder.kind = io.sui.bcsgen.CommandArgumentError.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CommandArgumentError other = (CommandArgumentError) obj;
            if (!java.util.Objects.equals(this.arg_idx, other.arg_idx)) { return false; }
            if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.arg_idx != null ? this.arg_idx.hashCode() : 0);
            value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short arg_idx;
            public io.sui.bcsgen.CommandArgumentError kind;

            public CommandArgumentError build() {
                return new CommandArgumentError(
                    arg_idx,
                    kind
                );
            }
        }
    }

    public static final class TypeArgumentError extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Short argument_idx;
        public final io.sui.bcsgen.TypeArgumentError kind;

        public TypeArgumentError(@com.novi.serde.Unsigned Short argument_idx, io.sui.bcsgen.TypeArgumentError kind) {
            java.util.Objects.requireNonNull(argument_idx, "argument_idx must not be null");
            java.util.Objects.requireNonNull(kind, "kind must not be null");
            this.argument_idx = argument_idx;
            this.kind = kind;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(20);
            serializer.serialize_u16(argument_idx);
            kind.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static TypeArgumentError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.argument_idx = deserializer.deserialize_u16();
            builder.kind = io.sui.bcsgen.TypeArgumentError.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TypeArgumentError other = (TypeArgumentError) obj;
            if (!java.util.Objects.equals(this.argument_idx, other.argument_idx)) { return false; }
            if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.argument_idx != null ? this.argument_idx.hashCode() : 0);
            value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Short argument_idx;
            public io.sui.bcsgen.TypeArgumentError kind;

            public TypeArgumentError build() {
                return new TypeArgumentError(
                    argument_idx,
                    kind
                );
            }
        }
    }

    public static final class UnusedValueWithoutDrop extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Short result_idx;
        public final @com.novi.serde.Unsigned Short secondary_idx;

        public UnusedValueWithoutDrop(@com.novi.serde.Unsigned Short result_idx, @com.novi.serde.Unsigned Short secondary_idx) {
            java.util.Objects.requireNonNull(result_idx, "result_idx must not be null");
            java.util.Objects.requireNonNull(secondary_idx, "secondary_idx must not be null");
            this.result_idx = result_idx;
            this.secondary_idx = secondary_idx;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(21);
            serializer.serialize_u16(result_idx);
            serializer.serialize_u16(secondary_idx);
            serializer.decrease_container_depth();
        }

        static UnusedValueWithoutDrop load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
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
            UnusedValueWithoutDrop other = (UnusedValueWithoutDrop) obj;
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

            public UnusedValueWithoutDrop build() {
                return new UnusedValueWithoutDrop(
                    result_idx,
                    secondary_idx
                );
            }
        }
    }

    public static final class InvalidPublicFunctionReturnType extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Short idx;

        public InvalidPublicFunctionReturnType(@com.novi.serde.Unsigned Short idx) {
            java.util.Objects.requireNonNull(idx, "idx must not be null");
            this.idx = idx;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(22);
            serializer.serialize_u16(idx);
            serializer.decrease_container_depth();
        }

        static InvalidPublicFunctionReturnType load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
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
            InvalidPublicFunctionReturnType other = (InvalidPublicFunctionReturnType) obj;
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

            public InvalidPublicFunctionReturnType build() {
                return new InvalidPublicFunctionReturnType(
                    idx
                );
            }
        }
    }

    public static final class InvalidTransferObject extends ExecutionFailureStatus {
        public InvalidTransferObject() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(23);
            serializer.decrease_container_depth();
        }

        static InvalidTransferObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidTransferObject other = (InvalidTransferObject) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidTransferObject build() {
                return new InvalidTransferObject(
                );
            }
        }
    }

    public static final class EffectsTooLarge extends ExecutionFailureStatus {
        public final @com.novi.serde.Unsigned Long current_size;
        public final @com.novi.serde.Unsigned Long max_size;

        public EffectsTooLarge(@com.novi.serde.Unsigned Long current_size, @com.novi.serde.Unsigned Long max_size) {
            java.util.Objects.requireNonNull(current_size, "current_size must not be null");
            java.util.Objects.requireNonNull(max_size, "max_size must not be null");
            this.current_size = current_size;
            this.max_size = max_size;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(24);
            serializer.serialize_u64(current_size);
            serializer.serialize_u64(max_size);
            serializer.decrease_container_depth();
        }

        static EffectsTooLarge load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.current_size = deserializer.deserialize_u64();
            builder.max_size = deserializer.deserialize_u64();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EffectsTooLarge other = (EffectsTooLarge) obj;
            if (!java.util.Objects.equals(this.current_size, other.current_size)) { return false; }
            if (!java.util.Objects.equals(this.max_size, other.max_size)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.current_size != null ? this.current_size.hashCode() : 0);
            value = 31 * value + (this.max_size != null ? this.max_size.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long current_size;
            public @com.novi.serde.Unsigned Long max_size;

            public EffectsTooLarge build() {
                return new EffectsTooLarge(
                    current_size,
                    max_size
                );
            }
        }
    }
}

