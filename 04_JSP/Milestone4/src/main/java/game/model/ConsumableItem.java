package game.model;

public class ConsumableItem extends Item {
	protected int itemLevel;
	protected String itemDescription;

	public ConsumableItem() {
	}

	public ConsumableItem(int itemID, String itemName, int maxStackSize, int vendorPrice, int itemLevel,
			String itemDescription) {
		super(itemID, itemName, maxStackSize, vendorPrice);
		this.itemLevel = itemLevel;
		this.itemDescription = itemDescription;
	}

	public ConsumableItem(int itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
