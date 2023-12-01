package game.model;

public class Inventory {
	private Character character;
	private int inventorySlotId;
	private int quantity;
	private Item item;

	public Inventory() {
	}

	public Inventory(Character character, int inventorySlotId, int quantity, Item item) {
		this.character = character;
		this.inventorySlotId = inventorySlotId;
		this.quantity = quantity;
		this.item = item;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public int getInventorySlotId() {
		return inventorySlotId;
	}

	public void setInventorySlotId(int inventorySlotId) {
		this.inventorySlotId = inventorySlotId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}