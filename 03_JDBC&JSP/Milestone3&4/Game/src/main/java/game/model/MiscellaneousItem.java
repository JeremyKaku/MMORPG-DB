package game.model;

public class MiscellaneousItem extends Item implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private Item item;
	private String itemDescription;

	public MiscellaneousItem() {
	}

	public MiscellaneousItem(Item item, String itemDescription) {
		this.item = item;
		this.itemDescription = itemDescription;
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

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

}
