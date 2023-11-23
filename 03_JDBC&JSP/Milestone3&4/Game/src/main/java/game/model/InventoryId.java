package game.model;

public class InventoryId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int inventorySlotId;
	private int characterId;

	public InventoryId() {
	}

	public InventoryId(int inventorySlotId, int characterId) {
		this.inventorySlotId = inventorySlotId;
		this.characterId = characterId;
	}

	public int getInventorySlotId() {
		return this.inventorySlotId;
	}

	public void setInventorySlotId(int inventorySlotId) {
		this.inventorySlotId = inventorySlotId;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InventoryId))
			return false;
		InventoryId castOther = (InventoryId) other;

		return (this.getInventorySlotId() == castOther.getInventorySlotId())
				&& (this.getCharacterId() == castOther.getCharacterId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getInventorySlotId();
		result = 37 * result + this.getCharacterId();
		return result;
	}

}
