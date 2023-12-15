package game.model;

public class CharacterAttribute {
	protected Attribute attribute;
	protected Character character;
	protected String atrributeValue;

	public CharacterAttribute() {
	}

	public CharacterAttribute(Attribute attribute, Character character, String atrributeValue) {
		this.attribute = attribute;
		this.character = character;
		this.atrributeValue = atrributeValue;
	}

	public CharacterAttribute(Attribute attribute, Character character) {
		this.attribute = attribute;
		this.character = character;
	}

	/** Getters and setters. */

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getAtrributeValue() {
		return atrributeValue;
	}

	public void setAtrributeValue(String atrributeValue) {
		this.atrributeValue = atrributeValue;
	}

}