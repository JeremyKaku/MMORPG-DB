package game.model;

public class CharacterAttribute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private CharacterAttributeId id;
	private Attribute attribute;
	private GameCharacter gameCharacter;
	private int attributeValue;

	public CharacterAttribute() {
	}

	public CharacterAttribute(CharacterAttributeId id, Attribute attribute, GameCharacter gameCharacter,
			int attributeValue) {
		this.id = id;
		this.attribute = attribute;
		this.gameCharacter = gameCharacter;
		this.attributeValue = attributeValue;
	}

	public CharacterAttributeId getId() {
		return this.id;
	}

	public void setId(CharacterAttributeId id) {
		this.id = id;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public GameCharacter getGameCharacter() {
		return this.gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public int getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(int attributeValue) {
		this.attributeValue = attributeValue;
	}

}
