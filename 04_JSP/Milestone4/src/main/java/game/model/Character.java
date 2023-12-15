package game.model;

public class Character {
	protected int characterID;
	protected String firstName;
	protected String lastName;
	protected Player player;
	protected Weapon weapon;

	public Character() {
	}

	public Character(int characterID, String firstName, String lastName, Player player, Weapon weapon) {
		this.characterID = characterID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.player = player;
		this.weapon = weapon;
	}

	public Character(String firstName, String lastName, Player player, Weapon weapon) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.player = player;
		this.weapon = weapon;
	}

	public Character(int characterID) {
		this.characterID = characterID;
	}

	public Character(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Character(Player player, Weapon weapon) {
		this.player = player;
		this.weapon = weapon;
	}

	/** Getters and setters. */

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayerID(Player player) {
		this.player = player;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}