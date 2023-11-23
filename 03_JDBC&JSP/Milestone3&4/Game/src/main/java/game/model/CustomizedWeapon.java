package game.model;

public class CustomizedWeapon implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private Quality quality;
	private Weapon weapon;
	private int customizedCondition;
	private String dyeColor;
	private String maker;

	public CustomizedWeapon() {
	}

	public CustomizedWeapon(Quality quality, Weapon weapon, int customizedCondition) {
		this.quality = quality;
		this.weapon = weapon;
		this.customizedCondition = customizedCondition;
	}

	public CustomizedWeapon(Quality quality, Weapon weapon, int customizedCondition, String dyeColor, String maker) {
		this.quality = quality;
		this.weapon = weapon;
		this.customizedCondition = customizedCondition;
		this.dyeColor = dyeColor;
		this.maker = maker;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Quality getQuality() {
		return this.quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public int getCustomizedCondition() {
		return this.customizedCondition;
	}

	public void setCustomizedCondition(int customizedCondition) {
		this.customizedCondition = customizedCondition;
	}

	public String getDyeColor() {
		return this.dyeColor;
	}

	public void setDyeColor(String dyeColor) {
		this.dyeColor = dyeColor;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

}
