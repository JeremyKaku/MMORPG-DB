package game.model;

public class Gear extends Item {
	protected int itemLevel;
	protected int gearSlotID;
	protected int requiredLevel;
	protected int defenseRating;
	protected int magicDefenseRating;
	
	public Gear(int itemID, String itemName, int maxStackSize, double vendorPrice, int gear_id, int itemLevel,
			int gearSlotID, int requiredLevel, int defenseRating, int magicDefenseRating) {
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

	public int getGearSlotID() {
		return gearSlotID;
	}

	public void setGearSlotID(int gearSlotID) {
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