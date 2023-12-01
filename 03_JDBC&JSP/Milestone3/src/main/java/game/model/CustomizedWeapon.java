package game.model;

import java.math.BigDecimal;

public class CustomizedWeapon extends Weapon {
	protected String itemQuality;
	protected int customizedCondition;
	protected String dyeColor;
	protected String maker;

	public CustomizedWeapon() {
	}

	public CustomizedWeapon(int itemID, String itemName, int maxStackSize, int vendorPrice, int itemLevel,
			int requiredLevel, int damageDone, BigDecimal autoAttack, BigDecimal attackDelay, String itemQuality,
			int customizedCondition, String dyeColor, String maker) {
		super(itemID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel, damageDone, autoAttack,
				attackDelay);
		this.itemQuality = itemQuality;
		this.customizedCondition = customizedCondition;
		this.dyeColor = dyeColor;
		this.maker = maker;
	}

	public CustomizedWeapon(int itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public String getItemQuality() {
		return itemQuality;
	}

	public void setItemQuality(String itemQuality) {
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