package game.model;

public class GearSlot {
	protected int gearSlotId;
	protected String gearSlotName;

	public GearSlot() {
	}

	public GearSlot(int gearSlotId, String gearSlotName) {
		this.gearSlotId = gearSlotId;
		this.gearSlotName = gearSlotName;
	}

	public GearSlot(String gearSlotName) {
		this.gearSlotName = gearSlotName;
	}
	
	public GearSlot(int gearSlotId) {
		this.gearSlotId = gearSlotId;
	}

	public int getGearSlotId() {
		return gearSlotId;
	}

	public void setGearSlotId(int gearSlotId) {
		this.gearSlotId = gearSlotId;
	}

	public String getGearSlotName() {
		return gearSlotName;
	}

	public void setGearSlotName(String gearSlotName) {
		this.gearSlotName = gearSlotName;
	}

}