package game.model;

public class CharacterCurrencyId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int characterId;
	private int currencyId;

	public CharacterCurrencyId() {
	}

	public CharacterCurrencyId(int characterId, int currencyId) {
		this.characterId = characterId;
		this.currencyId = currencyId;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CharacterCurrencyId))
			return false;
		CharacterCurrencyId castOther = (CharacterCurrencyId) other;

		return (this.getCharacterId() == castOther.getCharacterId())
				&& (this.getCurrencyId() == castOther.getCurrencyId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCharacterId();
		result = 37 * result + this.getCurrencyId();
		return result;
	}

}
