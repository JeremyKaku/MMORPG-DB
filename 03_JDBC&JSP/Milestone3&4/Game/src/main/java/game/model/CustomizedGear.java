package game.model;

public class CustomizedGear extends Gear implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private Gear gear;
	private Quality quality;
	private int customizedCondition;
	private String dyeColor;
	private String maker;

	public CustomizedGear() {
	}

	public CustomizedGear(Gear gear, Quality quality, int customizedCondition) {
		this.gear = gear;
		this.quality = quality;
		this.customizedCondition = customizedCondition;
	}

	public CustomizedGear(Gear gear, Quality quality, int customizedCondition, String dyeColor, String maker) {
		this.gear = gear;
		this.quality = quality;
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

	public Gear getGear() {
		return this.gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public Quality getQuality() {
		return this.quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
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