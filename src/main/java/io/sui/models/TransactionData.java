package io.sui.models;


import java.math.BigInteger;
import java.util.List;

import com.google.common.primitives.UnsignedLong;

/**
 * The type Transaction data.
 */
public class TransactionData {
	private BigInteger gasBudget;

	private SuiObjectRef gasPayment;

	private String sender;

	private List<TransactionKind> transactions;

	/**
	 * Gets gas budget.
	 *
	 * @return the gas budget
	 */
	BigInteger getGasBudget() {
		return gasBudget;
	}

	/**
	 * Sets gas budget.
	 *
	 * @param gasBudget the gas budget
	 */
	void setGasBudget(BigInteger gasBudget) {
		this.gasBudget = gasBudget;
	}

	/**
	 * Gets gas payment.
	 *
	 * @return the gas payment
	 */
	SuiObjectRef getGasPayment() {
		return gasPayment;
	}

	/**
	 * Sets gas payment.
	 *
	 * @param gasPayment the gas payment
	 */
	void setGasPayment(SuiObjectRef gasPayment) {
		this.gasPayment = gasPayment;
	}

	/**
	 * Gets sender.
	 *
	 * @return the sender
	 */
	String getSender() {
		return sender;
	}

	/**
	 * Sets sender.
	 *
	 * @param sender the sender
	 */
	void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets transactions.
	 *
	 * @return the transactions
	 */
	List<TransactionKind> getTransactions() {
		return transactions;
	}

	/**
	 * Sets transactions.
	 *
	 * @param transactions the transactions
	 */
	void setTransactions(List<TransactionKind> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "TransactionData{" +
				"gasBudget=" + gasBudget +
				", gasPayment=" + gasPayment +
				", sender='" + sender + '\'' +
				", transactions=" + transactions +
				'}';
	}


}
