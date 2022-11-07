package io.sui.models;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

import com.google.common.primitives.UnsignedLong;


/**
 * The type Authority quorum sign info.
 */
public class AuthorityQuorumSignInfo {
	private BigInteger epoch;

	private String signature;

	private byte[] signersMap;

	/**
	 * Gets epoch.
	 *
	 * @return the epoch
	 */
	BigInteger getEpoch() {
		return epoch;
	}

	/**
	 * Sets epoch.
	 *
	 * @param epoch the epoch
	 */
	void setEpoch(BigInteger epoch) {
		this.epoch = epoch;
	}

	/**
	 * Gets signature.
	 *
	 * @return the signature
	 */
	String getSignature() {
		return signature;
	}

	/**
	 * Sets signature.
	 *
	 * @param signature the signature
	 */
	void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * Get signers map byte [ ].
	 *
	 * @return the byte [ ]
	 */
	byte[] getSignersMap() {
		return signersMap;
	}

	/**
	 * Sets signers map.
	 *
	 * @param signersMap the signers map
	 */
	void setSignersMap(byte[] signersMap) {
		this.signersMap = signersMap;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorityQuorumSignInfo that = (AuthorityQuorumSignInfo) o;
		return epoch.equals(that.epoch) && signature.equals(that.signature) && Arrays.equals(signersMap, that.signersMap);
	}

	@Override
	public int hashCode() {
		return Objects.hash(epoch, signature, Arrays.hashCode(signersMap));
	}

	@Override
	public String toString() {
		return "AuthorityQuorumSignInfo{" +
				"epoch=" + epoch +
				", signature='" + signature + '\'' +
				", signersMap='" + Arrays.toString(signersMap) + '\'' +
				'}';
	}

}
