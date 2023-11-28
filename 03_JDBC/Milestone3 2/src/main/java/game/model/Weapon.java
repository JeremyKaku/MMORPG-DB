package game.model;

public class Weapon extends Item {
	protected int itemLevel;
    protected int requiredLevel;
    protected int damageDone;
	protected float autoAttack;
	protected float attackDelay;

	public Weapon(int itemID, String itemName, int maxStackSize, double vendorPrice,int itemLevel,
			int requiredLevel, int damageDone, float autoAttack, float attackDelay) {
		super(itemID, itemName, maxStackSize, vendorPrice);
		this.itemLevel = itemLevel;
		this.requiredLevel = requiredLevel;
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
	}

	public Weapon(int itemID) {
		super(itemID);
	}
	
	/** Getters and setters. */

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public int getDamageDone() {
		return damageDone;
	}

	public void setDamageDone(int damageDone) {
		this.damageDone = damageDone;
	}

	public float getAutoAttack() {
		return autoAttack;
	}

	public void setAutoAttack(float autoAttack) {
		this.autoAttack = autoAttack;
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}	 
}