package io.sui.bcsgen;


public abstract class ExecutionFailureStatus {

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public static ExecutionFailureStatus deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0: return InsufficientGas.load(deserializer);
            case 1: return InvalidGasObject.load(deserializer);
            case 2: return InvalidTransactionUpdate.load(deserializer);
            case 3: return ModuleNotFound.load(deserializer);
            case 4: return FunctionNotFound.load(deserializer);
            case 5: return InvariantViolation.load(deserializer);
            case 6: return MoveObjectTooBig.load(deserializer);
            case 7: return MovePackageTooBig.load(deserializer);
            case 8: return InvalidTransferObject.load(deserializer);
            case 9: return InvalidTransferSui.load(deserializer);
            case 10: return InvalidTransferSuiInsufficientBalance.load(deserializer);
            case 11: return InvalidCoinObject.load(deserializer);
            case 12: return EmptyInputCoins.load(deserializer);
            case 13: return EmptyRecipients.load(deserializer);
            case 14: return RecipientsAmountsArityMismatch.load(deserializer);
            case 15: return InsufficientBalance.load(deserializer);
            case 16: return CoinTypeMismatch.load(deserializer);
            case 17: return NonEntryFunctionInvoked.load(deserializer);
            case 18: return EntryTypeArityMismatch.load(deserializer);
            case 19: return EntryArgumentError.load(deserializer);
            case 20: return EntryTypeArgumentError.load(deserializer);
            case 21: return CircularObjectOwnership.load(deserializer);
            case 22: return InvalidChildObjectArgument.load(deserializer);
            case 23: return InvalidSharedByValue.load(deserializer);
            case 24: return TooManyChildObjects.load(deserializer);
            case 25: return InvalidParentDeletion.load(deserializer);
            case 26: return InvalidParentFreezing.load(deserializer);
            case 27: return PublishErrorEmptyPackage.load(deserializer);
            case 28: return PublishErrorNonZeroAddress.load(deserializer);
            case 29: return PublishErrorDuplicateModule.load(deserializer);
            case 30: return SuiMoveVerificationError.load(deserializer);
            case 31: return MovePrimitiveRuntimeError.load(deserializer);
            case 32: return MoveAbort.load(deserializer);
            case 33: return VMVerificationOrDeserializationError.load(deserializer);
            case 34: return VMInvariantViolation.load(deserializer);
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

    public static final class InvalidTransactionUpdate extends ExecutionFailureStatus {
        public InvalidTransactionUpdate() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            serializer.decrease_container_depth();
        }

        static InvalidTransactionUpdate load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidTransactionUpdate other = (InvalidTransactionUpdate) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidTransactionUpdate build() {
                return new InvalidTransactionUpdate(
                );
            }
        }
    }

    public static final class ModuleNotFound extends ExecutionFailureStatus {
        public ModuleNotFound() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(3);
            serializer.decrease_container_depth();
        }

        static ModuleNotFound load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ModuleNotFound other = (ModuleNotFound) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public ModuleNotFound build() {
                return new ModuleNotFound(
                );
            }
        }
    }

    public static final class FunctionNotFound extends ExecutionFailureStatus {
        public FunctionNotFound() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(4);
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

    public static final class InvariantViolation extends ExecutionFailureStatus {
        public InvariantViolation() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(5);
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
            serializer.serialize_variant_index(6);
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
            serializer.serialize_variant_index(7);
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

    public static final class InvalidTransferObject extends ExecutionFailureStatus {
        public InvalidTransferObject() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(8);
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

    public static final class InvalidTransferSui extends ExecutionFailureStatus {
        public InvalidTransferSui() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(9);
            serializer.decrease_container_depth();
        }

        static InvalidTransferSui load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidTransferSui other = (InvalidTransferSui) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidTransferSui build() {
                return new InvalidTransferSui(
                );
            }
        }
    }

    public static final class InvalidTransferSuiInsufficientBalance extends ExecutionFailureStatus {
        public InvalidTransferSuiInsufficientBalance() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(10);
            serializer.decrease_container_depth();
        }

        static InvalidTransferSuiInsufficientBalance load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidTransferSuiInsufficientBalance other = (InvalidTransferSuiInsufficientBalance) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidTransferSuiInsufficientBalance build() {
                return new InvalidTransferSuiInsufficientBalance(
                );
            }
        }
    }

    public static final class InvalidCoinObject extends ExecutionFailureStatus {
        public InvalidCoinObject() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(11);
            serializer.decrease_container_depth();
        }

        static InvalidCoinObject load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidCoinObject other = (InvalidCoinObject) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InvalidCoinObject build() {
                return new InvalidCoinObject(
                );
            }
        }
    }

    public static final class EmptyInputCoins extends ExecutionFailureStatus {
        public EmptyInputCoins() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(12);
            serializer.decrease_container_depth();
        }

        static EmptyInputCoins load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EmptyInputCoins other = (EmptyInputCoins) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public EmptyInputCoins build() {
                return new EmptyInputCoins(
                );
            }
        }
    }

    public static final class EmptyRecipients extends ExecutionFailureStatus {
        public EmptyRecipients() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(13);
            serializer.decrease_container_depth();
        }

        static EmptyRecipients load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EmptyRecipients other = (EmptyRecipients) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public EmptyRecipients build() {
                return new EmptyRecipients(
                );
            }
        }
    }

    public static final class RecipientsAmountsArityMismatch extends ExecutionFailureStatus {
        public RecipientsAmountsArityMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(14);
            serializer.decrease_container_depth();
        }

        static RecipientsAmountsArityMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            RecipientsAmountsArityMismatch other = (RecipientsAmountsArityMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public RecipientsAmountsArityMismatch build() {
                return new RecipientsAmountsArityMismatch(
                );
            }
        }
    }

    public static final class InsufficientBalance extends ExecutionFailureStatus {
        public InsufficientBalance() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(15);
            serializer.decrease_container_depth();
        }

        static InsufficientBalance load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InsufficientBalance other = (InsufficientBalance) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public InsufficientBalance build() {
                return new InsufficientBalance(
                );
            }
        }
    }

    public static final class CoinTypeMismatch extends ExecutionFailureStatus {
        public CoinTypeMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(16);
            serializer.decrease_container_depth();
        }

        static CoinTypeMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CoinTypeMismatch other = (CoinTypeMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public CoinTypeMismatch build() {
                return new CoinTypeMismatch(
                );
            }
        }
    }

    public static final class NonEntryFunctionInvoked extends ExecutionFailureStatus {
        public NonEntryFunctionInvoked() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(17);
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

    public static final class EntryTypeArityMismatch extends ExecutionFailureStatus {
        public EntryTypeArityMismatch() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(18);
            serializer.decrease_container_depth();
        }

        static EntryTypeArityMismatch load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EntryTypeArityMismatch other = (EntryTypeArityMismatch) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public EntryTypeArityMismatch build() {
                return new EntryTypeArityMismatch(
                );
            }
        }
    }

    public static final class EntryArgumentError extends ExecutionFailureStatus {
        public final io.sui.bcsgen.EntryArgumentError value;

        public EntryArgumentError(io.sui.bcsgen.EntryArgumentError value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(19);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static EntryArgumentError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = io.sui.bcsgen.EntryArgumentError.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EntryArgumentError other = (EntryArgumentError) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public io.sui.bcsgen.EntryArgumentError value;

            public EntryArgumentError build() {
                return new EntryArgumentError(
                    value
                );
            }
        }
    }

    public static final class EntryTypeArgumentError extends ExecutionFailureStatus {
        public final io.sui.bcsgen.EntryTypeArgumentError value;

        public EntryTypeArgumentError(io.sui.bcsgen.EntryTypeArgumentError value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(20);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static EntryTypeArgumentError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = io.sui.bcsgen.EntryTypeArgumentError.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EntryTypeArgumentError other = (EntryTypeArgumentError) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public io.sui.bcsgen.EntryTypeArgumentError value;

            public EntryTypeArgumentError build() {
                return new EntryTypeArgumentError(
                    value
                );
            }
        }
    }

    public static final class CircularObjectOwnership extends ExecutionFailureStatus {
        public final io.sui.bcsgen.CircularObjectOwnership value;

        public CircularObjectOwnership(io.sui.bcsgen.CircularObjectOwnership value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(21);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static CircularObjectOwnership load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = io.sui.bcsgen.CircularObjectOwnership.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CircularObjectOwnership other = (CircularObjectOwnership) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public io.sui.bcsgen.CircularObjectOwnership value;

            public CircularObjectOwnership build() {
                return new CircularObjectOwnership(
                    value
                );
            }
        }
    }

    public static final class InvalidChildObjectArgument extends ExecutionFailureStatus {
        public final io.sui.bcsgen.InvalidChildObjectArgument value;

        public InvalidChildObjectArgument(io.sui.bcsgen.InvalidChildObjectArgument value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(22);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static InvalidChildObjectArgument load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = io.sui.bcsgen.InvalidChildObjectArgument.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidChildObjectArgument other = (InvalidChildObjectArgument) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public io.sui.bcsgen.InvalidChildObjectArgument value;

            public InvalidChildObjectArgument build() {
                return new InvalidChildObjectArgument(
                    value
                );
            }
        }
    }

    public static final class InvalidSharedByValue extends ExecutionFailureStatus {
        public final io.sui.bcsgen.InvalidSharedByValue value;

        public InvalidSharedByValue(io.sui.bcsgen.InvalidSharedByValue value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(23);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static InvalidSharedByValue load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = io.sui.bcsgen.InvalidSharedByValue.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidSharedByValue other = (InvalidSharedByValue) obj;
            if (!java.util.Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public io.sui.bcsgen.InvalidSharedByValue value;

            public InvalidSharedByValue build() {
                return new InvalidSharedByValue(
                    value
                );
            }
        }
    }

    public static final class TooManyChildObjects extends ExecutionFailureStatus {
        public final ObjectID object;

        public TooManyChildObjects(ObjectID object) {
            java.util.Objects.requireNonNull(object, "object must not be null");
            this.object = object;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(24);
            object.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static TooManyChildObjects load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
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
            TooManyChildObjects other = (TooManyChildObjects) obj;
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

            public TooManyChildObjects build() {
                return new TooManyChildObjects(
                    object
                );
            }
        }
    }

    public static final class InvalidParentDeletion extends ExecutionFailureStatus {
        public final ObjectID parent;
        public final java.util.Optional<DeleteKind> kind;

        public InvalidParentDeletion(ObjectID parent, java.util.Optional<DeleteKind> kind) {
            java.util.Objects.requireNonNull(parent, "parent must not be null");
            java.util.Objects.requireNonNull(kind, "kind must not be null");
            this.parent = parent;
            this.kind = kind;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(25);
            parent.serialize(serializer);
            TraitHelpers.serialize_option_DeleteKind(kind, serializer);
            serializer.decrease_container_depth();
        }

        static InvalidParentDeletion load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.parent = ObjectID.deserialize(deserializer);
            builder.kind = TraitHelpers.deserialize_option_DeleteKind(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidParentDeletion other = (InvalidParentDeletion) obj;
            if (!java.util.Objects.equals(this.parent, other.parent)) { return false; }
            if (!java.util.Objects.equals(this.kind, other.kind)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.parent != null ? this.parent.hashCode() : 0);
            value = 31 * value + (this.kind != null ? this.kind.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID parent;
            public java.util.Optional<DeleteKind> kind;

            public InvalidParentDeletion build() {
                return new InvalidParentDeletion(
                    parent,
                    kind
                );
            }
        }
    }

    public static final class InvalidParentFreezing extends ExecutionFailureStatus {
        public final ObjectID parent;

        public InvalidParentFreezing(ObjectID parent) {
            java.util.Objects.requireNonNull(parent, "parent must not be null");
            this.parent = parent;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(26);
            parent.serialize(serializer);
            serializer.decrease_container_depth();
        }

        static InvalidParentFreezing load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.parent = ObjectID.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InvalidParentFreezing other = (InvalidParentFreezing) obj;
            if (!java.util.Objects.equals(this.parent, other.parent)) { return false; }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.parent != null ? this.parent.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public ObjectID parent;

            public InvalidParentFreezing build() {
                return new InvalidParentFreezing(
                    parent
                );
            }
        }
    }

    public static final class PublishErrorEmptyPackage extends ExecutionFailureStatus {
        public PublishErrorEmptyPackage() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(27);
            serializer.decrease_container_depth();
        }

        static PublishErrorEmptyPackage load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PublishErrorEmptyPackage other = (PublishErrorEmptyPackage) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public PublishErrorEmptyPackage build() {
                return new PublishErrorEmptyPackage(
                );
            }
        }
    }

    public static final class PublishErrorNonZeroAddress extends ExecutionFailureStatus {
        public PublishErrorNonZeroAddress() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(28);
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

    public static final class PublishErrorDuplicateModule extends ExecutionFailureStatus {
        public PublishErrorDuplicateModule() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(29);
            serializer.decrease_container_depth();
        }

        static PublishErrorDuplicateModule load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PublishErrorDuplicateModule other = (PublishErrorDuplicateModule) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public PublishErrorDuplicateModule build() {
                return new PublishErrorDuplicateModule(
                );
            }
        }
    }

    public static final class SuiMoveVerificationError extends ExecutionFailureStatus {
        public SuiMoveVerificationError() {
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(30);
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
        public final java.util.Optional<MoveLocation> value;

        public MovePrimitiveRuntimeError(java.util.Optional<MoveLocation> value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(31);
            TraitHelpers.serialize_option_MoveLocation(value, serializer);
            serializer.decrease_container_depth();
        }

        static MovePrimitiveRuntimeError load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = TraitHelpers.deserialize_option_MoveLocation(deserializer);
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
            public java.util.Optional<MoveLocation> value;

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
            serializer.serialize_variant_index(32);
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
            serializer.serialize_variant_index(33);
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
            serializer.serialize_variant_index(34);
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
}

