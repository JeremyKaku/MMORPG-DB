package game.model;

public class ConsumableItemEffect {
	protected ConsumableItem item;
	protected Attribute attribute;
	protected int effectPercentage;
	protected int maxEffectValue;

	public ConsumableItemEffect() {
	}

	public ConsumableItemEffect(ConsumableItem item, Attribute attribute, int effectPercentage, int maxEffectValue) {
		this.item = item;
		this.attribute = attribute;
		this.effectPercentage = effectPercentage;
		this.maxEffectValue = maxEffectValue;
	}

	public ConsumableItemEffect(ConsumableItem item, Attribute attribute) {
		this.item = item;
		this.attribute = attribute;
	}

	/** Getters and setters. */

	public ConsumableItem getItem() {
		return item;
	}

	public void setItem(ConsumableItem item) {
		this.item = item;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public int getEffectPercentage() {
		return effectPercentage;
	}

	public void setEffectPercentage(int effectPercentage) {
		this.effectPercentage = effectPercentage;
	}

	public int getMaxEffectValue() {
		return maxEffectValue;
	}

	public void setMaxEffectValue(int maxEffectValue) {
		this.maxEffectValue = maxEffectValue;
	}
}