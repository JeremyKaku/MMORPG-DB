package game.model;

import java.util.HashSet;
import java.util.Set;

public class Attribute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int attributeId;
	private String attributeName;
	private Set<?> consumableItemEffects = new HashSet<Object>(0);
	private Set<?> weaponBonuses = new HashSet<Object>(0);
	private Set<?> gearBonuses = new HashSet<Object>(0);
	private Set<?> characterAttributes = new HashSet<Object>(0);

	public Attribute() {
	}

	public Attribute(int attributeId, String attributeName) {
		this.attributeId = attributeId;
		this.attributeName = attributeName;
	}

	public Attribute(int attributeId, String attributeName, Set<?> consumableItemEffects, Set<?> weaponBonuses,
			Set<?> gearBonuses, Set<?> characterAttributes) {
		this.attributeId = attributeId;
		this.attributeName = attributeName;
		this.consumableItemEffects = consumableItemEffects;
		this.weaponBonuses = weaponBonuses;
		this.gearBonuses = gearBonuses;
		this.characterAttributes = characterAttributes;
	}

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Set<?> getConsumableItemEffects() {
		return this.consumableItemEffects;
	}

	public void setConsumableItemEffects(Set<?> consumableItemEffects) {
		this.consumableItemEffects = consumableItemEffects;
	}

	public Set<?> getWeaponBonuses() {
		return this.weaponBonuses;
	}

	public void setWeaponBonuses(Set<?> weaponBonuses) {
		this.weaponBonuses = weaponBonuses;
	}

	public Set<?> getGearBonuses() {
		return this.gearBonuses;
	}

	public void setGearBonuses(Set<?> gearBonuses) {
		this.gearBonuses = gearBonuses;
	}

	public Set<?> getCharacterAttributes() {
		return this.characterAttributes;
	}

	public void setCharacterAttributes(Set<?> characterAttributes) {
		this.characterAttributes = characterAttributes;
	}

}
