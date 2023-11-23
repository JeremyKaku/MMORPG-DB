package game.model;

public class CharacterJobId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int characterId;
	private int jobId;

	public CharacterJobId() {
	}

	public CharacterJobId(int characterId, int jobId) {
		this.characterId = characterId;
		this.jobId = jobId;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CharacterJobId))
			return false;
		CharacterJobId castOther = (CharacterJobId) other;

		return (this.getCharacterId() == castOther.getCharacterId()) && (this.getJobId() == castOther.getJobId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCharacterId();
		result = 37 * result + this.getJobId();
		return result;
	}

}
