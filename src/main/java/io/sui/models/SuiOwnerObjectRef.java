package io.sui.models;


import java.util.Objects;

public class SuiOwnerObjectRef {
	private SuiObjectOwner owner;

	private SuiObjectRef reference;

	SuiObjectOwner getOwner() {
		return owner;
	}

	void setOwner(SuiObjectOwner owner) {
		this.owner = owner;
	}

	SuiObjectRef getReference() {
		return reference;
	}

	void setReference(SuiObjectRef reference) {
		this.reference = reference;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SuiOwnerObjectRef that = (SuiOwnerObjectRef) o;
		return owner.equals(that.owner) && reference.equals(that.reference);
	}

	@Override
	public int hashCode() {
		return Objects.hash(owner, reference);
	}

	@Override
	public String toString() {
		return "SuiOwnerObjectRef{" +
				"owner=" + owner +
				", reference=" + reference +
				'}';
	}
}
