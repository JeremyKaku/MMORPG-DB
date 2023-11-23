package game.model;

public class CharacterEquippedId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int characterId;
	private int gearSlotId;

	public CharacterEquippedId() {
	}

	public CharacterEquippedId(int characterId, int gearSlotId) {
		this.characterId = characterId;
		this.gearSlotId = gearSlotId;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getGearSlotId() {
		return this.gearSlotId;
	}

	public void setGearSlotId(int gearSlotId) {
		this.gearSlotId = gearSlotId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CharacterEquippedId))
			return false;
		CharacterEquippedId castOther = (CharacterEquippedId) other;

		return (this.getCharacterId() == castOther.getCharacterId())
				&& (this.getGearSlotId() == castOther.getGearSlotId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCharacterId();
		result = 37 * result + this.getGearSlotId();
		return result;
	}

}
