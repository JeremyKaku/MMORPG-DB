package game.model;

public class WeaponBonusId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private int attributeId;

	public WeaponBonusId() {
	}

	public WeaponBonusId(int itemId, int attributeId) {
		this.itemId = itemId;
		this.attributeId = attributeId;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WeaponBonusId))
			return false;
		WeaponBonusId castOther = (WeaponBonusId) other;

		return (this.getItemId() == castOther.getItemId()) && (this.getAttributeId() == castOther.getAttributeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getItemId();
		result = 37 * result + this.getAttributeId();
		return result;
	}

}
