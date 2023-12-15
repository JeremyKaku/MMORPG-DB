package game.model;

public class MiscellaneousItem extends Item {
	protected String itemDescription;

	public MiscellaneousItem() {
	}

	public MiscellaneousItem(int itemID, String itemName, int maxStackSize, int vendorPrice, String itemDescription) {
		super(itemID, itemName, maxStackSize, vendorPrice);
		this.itemDescription = itemDescription;
	}

	public MiscellaneousItem(int itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}