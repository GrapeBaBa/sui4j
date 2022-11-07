package io.sui.models;

import java.math.BigInteger;
import java.util.Objects;


/**
 * The type Gas cost summary.
 */
public class GasCostSummary {
	private BigInteger computationCost;

	private BigInteger storageCost;

	private BigInteger storageRebate;

	/**
	 * Gets computation cost.
	 *
	 * @return the computation cost
	 */
	BigInteger getComputationCost() {
		return computationCost;
	}

	/**
	 * Sets computation cost.
	 *
	 * @param computationCost the computation cost
	 */
	void setComputationCost(BigInteger computationCost) {
		this.computationCost = computationCost;
	}

	/**
	 * Gets storage cost.
	 *
	 * @return the storage cost
	 */
	BigInteger getStorageCost() {
		return storageCost;
	}

	/**
	 * Sets storage cost.
	 *
	 * @param storageCost the storage cost
	 */
	void setStorageCost(BigInteger storageCost) {
		this.storageCost = storageCost;
	}

	/**
	 * Gets storage rebate.
	 *
	 * @return the storage rebate
	 */
	BigInteger getStorageRebate() {
		return storageRebate;
	}

	/**
	 * Sets storage rebate.
	 *
	 * @param storageRebate the storage rebate
	 */
	void setStorageRebate(BigInteger storageRebate) {
		this.storageRebate = storageRebate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GasCostSummary that = (GasCostSummary) o;
		return computationCost.equals(that.computationCost) && storageCost.equals(that.storageCost) && storageRebate.equals(that.storageRebate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(computationCost, storageCost, storageRebate);
	}

	@Override
	public String toString() {
		return "GasCostSummary{" +
				"computationCost=" + computationCost +
				", storageCost=" + storageCost +
				", storageRebate=" + storageRebate +
				'}';
	}

}
