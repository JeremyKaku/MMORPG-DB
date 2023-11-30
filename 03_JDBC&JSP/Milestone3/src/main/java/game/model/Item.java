package game.model;

public class Item {
	protected int itemID;
	protected String itemName;
	protected int maxStackSize;
	protected double vendorPrice;
	
	public Item(int itemID, String itemName, int maxStackSize, double vendorPrice) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.maxStackSize = maxStackSize;
		this.vendorPrice = vendorPrice;
	}

	public Item(int itemID) {
		this.itemID = itemID;
	}

	/** Getters and setters. */
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}

	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}

	public double getVendorPrice() {
		return vendorPrice;
	}

	public void setVendorPrice(double vendorPrice) {
		this.vendorPrice = vendorPrice;
	}
}