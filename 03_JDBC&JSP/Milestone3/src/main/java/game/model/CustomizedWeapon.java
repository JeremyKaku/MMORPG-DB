package game.model;

public class CustomizedWeapon extends Weapon {
	protected Item item;
    protected Quality itemQuality;
    protected int customizedCondition;
    protected String dyeColor;
    protected String maker;

	public CustomizedWeapon(int itemID, String itemName, int maxStackSize, double vendorPrice, int itemLevel,
			int requiredLevel, int damageDone, float autoAttack, float attackDelay, Item item, Quality itemQuality,
			int customizedCondition, String dyeColor, String maker) {
		super(itemID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel, damageDone, autoAttack,
				attackDelay);
		this.item = item;
		this.itemQuality = itemQuality;
		this.customizedCondition = customizedCondition;
		this.dyeColor = dyeColor;
		this.maker = maker;
	}

	public CustomizedWeapon(int itemID) {
		super(itemID);
	}
    
	/** Getters and setters. */

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

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