package game.model;

import java.math.BigDecimal;

public class Weapon extends Item {
	protected int itemLevel;
	protected int requiredLevel;
	protected int damageDone;
	protected BigDecimal autoAttack;
	protected BigDecimal attackDelay;

	public Weapon() {
	}

	public Weapon(int itemID, String itemName, int maxStackSize, int vendorPrice, int itemLevel, int requiredLevel,
			int damageDone, BigDecimal autoAttack, BigDecimal attackDelay) {
		super(itemID, itemName, maxStackSize, vendorPrice);
		this.itemLevel = itemLevel;
		this.requiredLevel = requiredLevel;
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
	}
	
	public Weapon(String itemName, int maxStackSize, int vendorPrice, int itemLevel, int requiredLevel,
			int damageDone, BigDecimal autoAttack, BigDecimal attackDelay) {
		super(itemName, maxStackSize, vendorPrice);
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

	public BigDecimal getAutoAttack() {
		return autoAttack;
	}

	public void setAutoAttack(BigDecimal autoAttack) {
		this.autoAttack = autoAttack;
	}

	public BigDecimal getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(BigDecimal attackDelay) {
		this.attackDelay = attackDelay;
	}
}