package game.model;

public class GearBonusId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private int attributeId;

	public GearBonusId() {
	}

	public GearBonusId(int itemId, int attributeId) {
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
		if (!(other instanceof GearBonusId))
			return false;
		GearBonusId castOther = (GearBonusId) other;

		return (this.getItemId() == castOther.getItemId()) && (this.getAttributeId() == castOther.getAttributeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getItemId();
		result = 37 * result + this.getAttributeId();
		return result;
	}

}
