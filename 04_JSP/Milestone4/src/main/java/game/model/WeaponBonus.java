package game.model;

public class WeaponBonus {
	protected Weapon item;
	protected Attribute attribute;
	protected int bonusValue;

	public WeaponBonus() {
	}

	public WeaponBonus(Weapon item, Attribute attribute, int bonusValue) {
		this.item = item;
		this.attribute = attribute;
		this.bonusValue = bonusValue;
	}

	public WeaponBonus(Weapon item, Attribute attribute) {
		this.item = item;
		this.attribute = attribute;
	}

	/** Getters and setters. */

	public Weapon getItem() {
		return item;
	}

	public void setItem(Weapon item) {
		this.item = item;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public int getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}
}