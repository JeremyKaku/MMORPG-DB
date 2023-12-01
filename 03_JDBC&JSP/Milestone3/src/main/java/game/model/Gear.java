package game.model;

public class Gear extends Item {
	protected int itemLevel;
	protected GearSlot gearSlotID;
	protected int requiredLevel;
	protected int defenseRating;
	protected int magicDefenseRating;

	public Gear() {
	}

	public Gear(int itemID, String itemName, int maxStackSize, int vendorPrice, int itemLevel, GearSlot gearSlotID,
			int requiredLevel, int defenseRating, int magicDefenseRating) {
		super(itemID, itemName, maxStackSize, vendorPrice);
		this.itemLevel = itemLevel;
		this.gearSlotID = gearSlotID;
		this.requiredLevel = requiredLevel;
		this.defenseRating = defenseRating;
		this.magicDefenseRating = magicDefenseRating;
	}

	public Gear(int itemID) {
		super(itemID);
	}

	/** Getters and setters. */

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public GearSlot getGearSlot() {
		return gearSlotID;
	}

	public void setGearSlot(GearSlot gearSlotID) {
		this.gearSlotID = gearSlotID;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public int getDefenseRating() {
		return defenseRating;
	}

	public void setDefenseRating(int defenseRating) {
		this.defenseRating = defenseRating;
	}

	public int getMagicDefenseRating() {
		return magicDefenseRating;
	}

	public void setMagicDefenseRating(int magicDefenseRating) {
		this.magicDefenseRating = magicDefenseRating;
	}

}