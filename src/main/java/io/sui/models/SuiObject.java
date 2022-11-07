package io.sui.models;

import java.math.BigInteger;
import java.util.Objects;


/**
 * The type Sui object.
 */
public class SuiObject implements GetObjectResponse.GetObjectResponseDetails {

	private SuiData data;

	private SuiObjectOwner owner;

	private String previousTransaction;

	private BigInteger storageRebate;

	private SuiObjectRef reference;

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public SuiData getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data the data
	 */
	public void setData(SuiData data) {
		this.data = data;
	}

	/**
	 * Gets owner.
	 *
	 * @return the owner
	 */
	public SuiObjectOwner getOwner() {
		return owner;
	}

	/**
	 * Sets owner.
	 *
	 * @param owner the owner
	 */
	public void setOwner(SuiObjectOwner owner) {
		this.owner = owner;
	}

	/**
	 * Gets previous transaction.
	 *
	 * @return the previous transaction
	 */
	public String getPreviousTransaction() {
		return previousTransaction;
	}

	/**
	 * Sets previous transaction.
	 *
	 * @param previousTransaction the previous transaction
	 */
	public void setPreviousTransaction(String previousTransaction) {
		this.previousTransaction = previousTransaction;
	}

	/**
	 * Gets storage rebate.
	 *
	 * @return the storage rebate
	 */
	public BigInteger getStorageRebate() {
		return storageRebate;
	}

	/**
	 * Sets storage rebate.
	 *
	 * @param storageRebate the storage rebate
	 */
	public void setStorageRebate(BigInteger storageRebate) {
		this.storageRebate = storageRebate;
	}

	/**
	 * Gets reference.
	 *
	 * @return the reference
	 */
	public SuiObjectRef getReference() {
		return reference;
	}

	/**
	 * Sets reference.
	 *
	 * @param reference the reference
	 */
	public void setReference(SuiObjectRef reference) {
		this.reference = reference;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SuiObject suiObject = (SuiObject) o;
		return data.equals(suiObject.data) && owner.equals(suiObject.owner) && previousTransaction.equals(suiObject.previousTransaction) && storageRebate.equals(suiObject.storageRebate) && reference.equals(suiObject.reference);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, owner, previousTransaction, storageRebate, reference);
	}

	@Override
	public String toString() {
		return "SuiObject{" +
				"data=" + data +
				", owner=" + owner +
				", previousTransaction='" + previousTransaction + '\'' +
				", storageRebate=" + storageRebate +
				", reference=" + reference +
				'}';
	}
}
