package game.model;

import java.util.HashSet;
import java.util.Set;

public class GearSlot implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int gearSlotId;
	private String gearSlotName;
	private Set<?> characterEquippeds = new HashSet<Object>(0);
	private Set<?> gears = new HashSet<Object>(0);

	public GearSlot() {
	}

	public GearSlot(int gearSlotId, String gearSlotName) {
		this.gearSlotId = gearSlotId;
		this.gearSlotName = gearSlotName;
	}

	public GearSlot(int gearSlotId, String gearSlotName, Set<?> characterEquippeds, Set<?> gears) {
		this.gearSlotId = gearSlotId;
		this.gearSlotName = gearSlotName;
		this.characterEquippeds = characterEquippeds;
		this.gears = gears;
	}

	public int getGearSlotId() {
		return this.gearSlotId;
	}

	public void setGearSlotId(int gearSlotId) {
		this.gearSlotId = gearSlotId;
	}

	public String getGearSlotName() {
		return this.gearSlotName;
	}

	public void setGearSlotName(String gearSlotName) {
		this.gearSlotName = gearSlotName;
	}

	public Set<?> getCharacterEquippeds() {
		return this.characterEquippeds;
	}

	public void setCharacterEquippeds(Set<?> characterEquippeds) {
		this.characterEquippeds = characterEquippeds;
	}

	public Set<?> getGears() {
		return this.gears;
	}

	public void setGears(Set<?> gears) {
		this.gears = gears;
	}

}
