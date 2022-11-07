package io.sui.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public abstract class SuiEvent {
	public enum BalanceChangeType {
		Gas,
		Pay,
		Receive
	}

	public static class TransferObjectEvent extends SuiEvent {
		private String packageId;

		private String transactionModule;

		private String sender;

		private SuiObjectOwner recipient;

		private String objectType;

		private String objectId;

		private BigInteger version;
	}

	public static class MoveEvent extends SuiEvent {
		private String bcs;

		private String packageId;

		private String sender;

		private String transactionModule;

		private String type;

		private Map<String, Object> fields;
	}

	public static class PublishEvent extends SuiEvent {
		private String packageId;

		private String sender;
	}

	public static class CoinBalanceChangeEvent extends SuiEvent {
		private String packageId;

		private String transactionModule;

		private String sender;

		private SuiObjectOwner owner;

		private BalanceChangeType changeType;

		private String coinType;

		private String coinObjectId;

		private BigInteger version;

		private BigDecimal amount;
	}

	public static class MutateObjectEvent extends SuiEvent {
		private String packageId;

		private String transactionModule;

		private String sender;

		private String objectType;

		private String objectId;

		private BigInteger version;
	}

	public static class DeleteObjectEvent extends SuiEvent {
		private String packageId;

		private String transactionModule;

		private String sender;

		private String objectId;

		private BigInteger version;
	}

	public static class NewObjectEvent extends SuiEvent {
		private String packageId;

		private String transactionModule;

		private SuiObjectOwner recipient;

		private String sender;

		private String objectId;

		private String objectType;

		private BigInteger version;
	}

	public static class EpochChangeEvent extends SuiEvent {
		private BigInteger epochChange;
	}

	public static class CheckpointEvent extends SuiEvent {
		private BigInteger checkpoint;
	}

	private TransferObjectEvent transferObject;

	private MoveEvent moveEvent;

	private PublishEvent publish;

	private CoinBalanceChangeEvent coinBalanceChange;

	private MutateObjectEvent mutateObject;

	private DeleteObjectEvent deleteObject;

	private NewObjectEvent newObject;

	private EpochChangeEvent epochChange;

	private CheckpointEvent checkpoint;

}
