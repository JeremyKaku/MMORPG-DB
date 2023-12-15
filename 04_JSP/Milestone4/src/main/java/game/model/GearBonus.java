package game.model;

public class GearBonus {
	protected Gear item;
	protected Attribute attribute;
	protected int bonusValue;

	public GearBonus() {
	}

	public GearBonus(Gear item, Attribute attribute, int bonusValue) {
		this.item = item;
		this.attribute = attribute;
		this.bonusValue = bonusValue;
	}

	public GearBonus(Gear item, Attribute attribute) {
		this.item = item;
		this.attribute = attribute;
	}

	/** Getters and setters. */

	public Gear getItem() {
		return item;
	}

	public void setItem(Gear item) {
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