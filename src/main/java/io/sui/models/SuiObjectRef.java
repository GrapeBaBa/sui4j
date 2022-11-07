package io.sui.models;


import java.math.BigInteger;
import java.util.Objects;

/**
 * The type Sui object ref.
 */
public class SuiObjectRef implements GetObjectResponse.GetObjectResponseDetails {
	private String digest;

	private String objectId;

	private BigInteger version;

	/**
	 * Gets digest.
	 *
	 * @return the digest
	 */
	public String getDigest() {
		return digest;
	}

	/**
	 * Sets digest.
	 *
	 * @param digest the digest
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}

	/**
	 * Gets object id.
	 *
	 * @return the object id
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * Sets object id.
	 *
	 * @param objectId the object id
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Gets version.
	 *
	 * @return the version
	 */
	public BigInteger getVersion() {
		return version;
	}

	/**
	 * Sets version.
	 *
	 * @param version the version
	 */
	public void setVersion(BigInteger version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SuiObjectRef that = (SuiObjectRef) o;
		return digest.equals(that.digest) && objectId.equals(that.objectId) && version.equals(that.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(digest, objectId, version);
	}

	@Override
	public String toString() {
		return "SuiObjectRef{" +
				"digest='" + digest + '\'' +
				", objectId='" + objectId + '\'' +
				", version=" + version +
				'}';
	}

}
