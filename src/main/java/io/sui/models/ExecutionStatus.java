package io.sui.models;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Author: kaichen
 * Date: 2022/11/4
 * Time: 16:41
 */
public class ExecutionStatus {
	/**
	 * The enum Execution status type.
	 */
	public enum ExecutionStatusType {
		/**
		 *Success execution status type.
		 */
		success,
		/**
		 *Failure execution status type.
		 */
		failure
	}

	private ExecutionStatusType status;

	private String error;

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	ExecutionStatusType getStatus() {
		return status;
	}

	/**
	 * Sets status.
	 *
	 * @param status the status
	 */
	void setStatus(ExecutionStatusType status) {
		this.status = status;
	}

	/**
	 * Gets error.
	 *
	 * @return the error
	 */
	String getError() {
		return error;
	}

	/**
	 * Sets error.
	 *
	 * @param error the error
	 */
	void setError(String error) {
		this.error = error;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExecutionStatus that = (ExecutionStatus) o;
		return status == that.status && error.equals(that.error);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, error);
	}

	@Override
	public String toString() {
		return "ExecutionStatus{" +
				"status=" + status +
				", error='" + error + '\'' +
				'}';
	}


}
