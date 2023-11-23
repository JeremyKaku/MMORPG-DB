package game.model;

import java.util.HashSet;
import java.util.Set;

public class Item implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private String itemName;
	private int maxStackSize;
	private Integer vendorPrice;
	private Weapon weapon;
	private Gear gear;
	private Set<?> inventories = new HashSet<Object>(0);
	private MiscellaneousItem miscellaneousItem;
	private ConsumableItem consumableItem;

	public Item() {
	}

	public Item(int itemId, String itemName, int maxStackSize) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.maxStackSize = maxStackSize;
	}

	public Item(int itemId, String itemName, int maxStackSize, Integer vendorPrice, Weapon weapon, Gear gear,
			Set<?> inventories, MiscellaneousItem miscellaneousItem, ConsumableItem consumableItem) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.maxStackSize = maxStackSize;
		this.vendorPrice = vendorPrice;
		this.weapon = weapon;
		this.gear = gear;
		this.inventories = inventories;
		this.miscellaneousItem = miscellaneousItem;
		this.consumableItem = consumableItem;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getMaxStackSize() {
		return this.maxStackSize;
	}

	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}

	public Integer getVendorPrice() {
		return this.vendorPrice;
	}

	public void setVendorPrice(Integer vendorPrice) {
		this.vendorPrice = vendorPrice;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Gear getGear() {
		return this.gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public Set<?> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<?> inventories) {
		this.inventories = inventories;
	}

	public MiscellaneousItem getMiscellaneousItem() {
		return this.miscellaneousItem;
	}

	public void setMiscellaneousItem(MiscellaneousItem miscellaneousItem) {
		this.miscellaneousItem = miscellaneousItem;
	}

	public ConsumableItem getConsumableItem() {
		return this.consumableItem;
	}

	public void setConsumableItem(ConsumableItem consumableItem) {
		this.consumableItem = consumableItem;
	}

}
