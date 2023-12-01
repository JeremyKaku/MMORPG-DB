package game.model;

public class CharacterEquipped {
	private Character character;
	private GearSlot gearSlot;
	private Gear gear;

	public CharacterEquipped() {
	}

	public CharacterEquipped(Character character, GearSlot gearSlot, Gear gear) {
		this.character = character;
		this.gearSlot = gearSlot;
		this.gear = gear;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public GearSlot getGearSlot() {
		return gearSlot;
	}

	public void setGearSlot(GearSlot gearSlot) {
		this.gearSlot = gearSlot;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear item) {
		this.gear = item;
	}

}