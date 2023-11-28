package game.model;

public class ConsumableItemEffect {
    protected ConsumableItem item;
    protected Attribute attribute;
    protected double effectPercentage;
    protected int maxEffectValue;

	public ConsumableItemEffect(ConsumableItem item, Attribute attribute, double effectPercentage, int maxEffectValue) {
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

	public double getEffectPercentage() {
		return effectPercentage;
	}

	public void setEffectPercentage(double effectPercentage) {
		this.effectPercentage = effectPercentage;
	}

	public int getMaxEffectValue() {
		return maxEffectValue;
	}

	public void setMaxEffectValue(int maxEffectValue) {
		this.maxEffectValue = maxEffectValue;
	}
}