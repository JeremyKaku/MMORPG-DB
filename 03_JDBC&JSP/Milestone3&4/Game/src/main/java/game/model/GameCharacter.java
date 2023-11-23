package game.model;

import java.util.HashSet;
import java.util.Set;

public class GameCharacter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int characterId;
	private Player player;
	private Weapon weapon;
	private String firstName;
	private String lastName;
	private Set<?> characterCurrencies = new HashSet<Object>(0);
	private Set<?> characterEquippeds = new HashSet<Object>(0);
	private Set<?> characterJobs = new HashSet<Object>(0);
	private Set<?> characterAttributes = new HashSet<Object>(0);
	private Set<?> inventories = new HashSet<Object>(0);

	public GameCharacter() {
	}

	public GameCharacter(int characterId, Player player, Weapon weapon, String firstName, String lastName) {
		this.characterId = characterId;
		this.player = player;
		this.weapon = weapon;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public GameCharacter(int characterId, Player player, Weapon weapon, String firstName, String lastName,
			Set<?> characterCurrencies, Set<?> characterEquippeds, Set<?> characterJobs, Set<?> characterAttributes,
			Set<?> inventories) {
		this.characterId = characterId;
		this.player = player;
		this.weapon = weapon;
		this.firstName = firstName;
		this.lastName = lastName;
		this.characterCurrencies = characterCurrencies;
		this.characterEquippeds = characterEquippeds;
		this.characterJobs = characterJobs;
		this.characterAttributes = characterAttributes;
		this.inventories = inventories;
	}

	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<?> getCharacterCurrencies() {
		return this.characterCurrencies;
	}

	public void setCharacterCurrencies(Set<?> characterCurrencies) {
		this.characterCurrencies = characterCurrencies;
	}

	public Set<?> getCharacterEquippeds() {
		return this.characterEquippeds;
	}

	public void setCharacterEquippeds(Set<?> characterEquippeds) {
		this.characterEquippeds = characterEquippeds;
	}

	public Set<?> getCharacterJobs() {
		return this.characterJobs;
	}

	public void setCharacterJobs(Set<?> characterJobs) {
		this.characterJobs = characterJobs;
	}

	public Set<?> getCharacterAttributes() {
		return this.characterAttributes;
	}

	public void setCharacterAttributes(Set<?> characterAttributes) {
		this.characterAttributes = characterAttributes;
	}

	public Set<?> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<?> inventories) {
		this.inventories = inventories;
	}

}
