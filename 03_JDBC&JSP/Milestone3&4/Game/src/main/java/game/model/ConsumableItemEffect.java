package game.model;

public class ConsumableItemEffect implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private ConsumableItemEffectId id;
	private Attribute attribute;
	private ConsumableItem consumableItem;
	private int effectPercentage;
	private int maxEffectValue;

	public ConsumableItemEffect() {
	}

	public ConsumableItemEffect(ConsumableItemEffectId id, Attribute attribute, ConsumableItem consumableItem,
			int effectPercentage, int maxEffectValue) {
		this.id = id;
		this.attribute = attribute;
		this.consumableItem = consumableItem;
		this.effectPercentage = effectPercentage;
		this.maxEffectValue = maxEffectValue;
	}

	public ConsumableItemEffectId getId() {
		return this.id;
	}

	public void setId(ConsumableItemEffectId id) {
		this.id = id;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public ConsumableItem getConsumableItem() {
		return this.consumableItem;
	}

	public void setConsumableItem(ConsumableItem consumableItem) {
		this.consumableItem = consumableItem;
	}

	public int getEffectPercentage() {
		return this.effectPercentage;
	}

	public void setEffectPercentage(int effectPercentage) {
		this.effectPercentage = effectPercentage;
	}

	public int getMaxEffectValue() {
		return this.maxEffectValue;
	}

	public void setMaxEffectValue(int maxEffectValue) {
		this.maxEffectValue = maxEffectValue;
	}

}
