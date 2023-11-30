package game.model;

public class CustomizedGear extends Gear {
    protected Quality itemQuality;
    protected int customizedCondition;
    protected String dyeColor;
    protected String maker;
	
    public CustomizedGear(int itemID, String itemName, int maxStackSize, double vendorPrice, int itemLevel,
			int gearSlotID, int requiredLevel, int defenseRating, int magicDefenseRating,
			Quality itemQuality, int customizedCondition, String dyeColor, String maker) {
		super(itemID, itemName, maxStackSize, vendorPrice, itemLevel, gearSlotID, requiredLevel, defenseRating,
				magicDefenseRating);
		this.itemQuality = itemQuality;
		this.customizedCondition = customizedCondition;
		this.dyeColor = dyeColor;
		this.maker = maker;
	}
    
	public CustomizedGear(int itemID) {
		super(itemID);
	}
    
	/** Getters and setters. */

	public Quality getItemQuality() {
		return itemQuality;
	}

	public void setItemQuality(Quality itemQuality) {
		this.itemQuality = itemQuality;
	}

	public int getCustomizedCondition() {
		return customizedCondition;
	}

	public void setCustomizedCondition(int customizedCondition) {
		this.customizedCondition = customizedCondition;
	}

	public String getDyeColor() {
		return dyeColor;
	}

	public void setDyeColor(String dyeColor) {
		this.dyeColor = dyeColor;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}   
}