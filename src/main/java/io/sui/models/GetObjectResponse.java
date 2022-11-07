package io.sui.models;


import java.util.Objects;

/**
 * The type Get object response.
 */
public class GetObjectResponse {
	/**
	 * The interface Get object response details.
	 */
	public interface GetObjectResponseDetails {

	}

	/**
	 * The type Object id response details.
	 */
	public static class ObjectIdResponseDetails implements GetObjectResponseDetails {
		private String objectId;

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

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			ObjectIdResponseDetails that = (ObjectIdResponseDetails) o;
			return objectId.equals(that.objectId);
		}

		@Override
		public int hashCode() {
			return Objects.hash(objectId);
		}

		@Override
		public String toString() {
			return "ObjectIdResponseDetails{" +
					"objectId='" + objectId + '\'' +
					'}';
		}
	}

	private ObjectStatus status;

	private GetObjectResponseDetails details;

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public ObjectStatus getStatus() {
		return status;
	}

	/**
	 * Sets status.
	 *
	 * @param status the status
	 */
	public void setStatus(ObjectStatus status) {
		this.status = status;
	}

	/**
	 * Gets details.
	 *
	 * @return the details
	 */
	public GetObjectResponseDetails getDetails() {
		return details;
	}

	/**
	 * Sets details.
	 *
	 * @param details the details
	 */
	public void setDetails(GetObjectResponseDetails details) {
		this.details = details;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GetObjectResponse that = (GetObjectResponse) o;
		return status == that.status && details.equals(that.details);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, details);
	}

	@Override
	public String toString() {
		return "GetObjectResponse{" +
				"status=" + status +
				", details=" + details +
				'}';
	}
}
