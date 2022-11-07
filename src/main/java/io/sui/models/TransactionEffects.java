package io.sui.models;

import java.util.List;

public class TransactionEffects {
	private ExecutionStatus status;

	private GasCostSummary gasUsed;

	private List<SuiObjectRef> sharedObjects;

	private String transactionDigest;

	private List<SuiOwnerObjectRef> created;

	private List<SuiOwnerObjectRef> mutated;

	private List<SuiOwnerObjectRef> unwrapped;

	private List<SuiObjectRef> deleted;

	private List<SuiObjectRef> wrapped;

	private SuiOwnerObjectRef gasObject;

	private List<SuiEvent> events;

	private List<String> dependencies;
}
