package game.model;

import java.util.HashSet;
import java.util.Set;

public class ConsumableItem extends Item implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private Item item;
	private int itemLevel;
	private String itemDescription;
	private Set<?> consumableItemEffects = new HashSet<Object>(0);

	public ConsumableItem() {
	}

	public ConsumableItem(Item item, int itemLevel, String itemDescription) {
		this.item = item;
		this.itemLevel = itemLevel;
		this.itemDescription = itemDescription;
	}

	public ConsumableItem(Item item, int itemLevel, String itemDescription, Set<?> consumableItemEffects) {
		this.item = item;
		this.itemLevel = itemLevel;
		this.itemDescription = itemDescription;
		this.consumableItemEffects = consumableItemEffects;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItemLevel() {
		return this.itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Set<?> getConsumableItemEffects() {
		return this.consumableItemEffects;
	}

	public void setConsumableItemEffects(Set<?> consumableItemEffects) {
		this.consumableItemEffects = consumableItemEffects;
	}

}
