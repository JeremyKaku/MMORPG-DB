package game.model;

public class GearBonus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private GearBonusId id;
	private Attribute attribute;
	private Gear gear;
	private int bonusValue;

	public GearBonus() {
	}

	public GearBonus(GearBonusId id, Attribute attribute, Gear gear, int bonusValue) {
		this.id = id;
		this.attribute = attribute;
		this.gear = gear;
		this.bonusValue = bonusValue;
	}

	public GearBonusId getId() {
		return this.id;
	}

	public void setId(GearBonusId id) {
		this.id = id;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Gear getGear() {
		return this.gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public int getBonusValue() {
		return this.bonusValue;
	}

	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}

}
