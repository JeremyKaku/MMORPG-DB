package game.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class Weapon extends Item implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private Item item;
	private int itemLevel;
	private int requiredLevel;
	private int damageDone;
	private BigDecimal autoAttack;
	private BigDecimal attackDelay;
	private Set<?> weaponBonuses = new HashSet<Object>(0);
	private CustomizedWeapon customizedWeapon;
	private Set<?> gameCharacters = new HashSet<Object>(0);
	private Set<?> jobs = new HashSet<Object>(0);

	public Weapon() {
	}

	public Weapon(Item item, int itemLevel, int requiredLevel, int damageDone, BigDecimal autoAttack,
			BigDecimal attackDelay) {
		this.item = item;
		this.itemLevel = itemLevel;
		this.requiredLevel = requiredLevel;
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
	}

	public Weapon(Item item, int itemLevel, int requiredLevel, int damageDone, BigDecimal autoAttack,
			BigDecimal attackDelay, Set<?> weaponBonuses, CustomizedWeapon customizedWeapon, Set<?> gameCharacters,
			Set<?> jobs) {
		this.item = item;
		this.itemLevel = itemLevel;
		this.requiredLevel = requiredLevel;
		this.damageDone = damageDone;
		this.autoAttack = autoAttack;
		this.attackDelay = attackDelay;
		this.weaponBonuses = weaponBonuses;
		this.customizedWeapon = customizedWeapon;
		this.gameCharacters = gameCharacters;
		this.jobs = jobs;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItemLevel() {
		return this.itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public int getRequiredLevel() {
		return this.requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public int getDamageDone() {
		return this.damageDone;
	}

	public void setDamageDone(int damageDone) {
		this.damageDone = damageDone;
	}

	public BigDecimal getAutoAttack() {
		return this.autoAttack;
	}

	public void setAutoAttack(BigDecimal autoAttack) {
		this.autoAttack = autoAttack;
	}

	public BigDecimal getAttackDelay() {
		return this.attackDelay;
	}

	public void setAttackDelay(BigDecimal attackDelay) {
		this.attackDelay = attackDelay;
	}

	public Set<?> getWeaponBonuses() {
		return this.weaponBonuses;
	}

	public void setWeaponBonuses(Set<?> weaponBonuses) {
		this.weaponBonuses = weaponBonuses;
	}

	public CustomizedWeapon getCustomizedWeapon() {
		return this.customizedWeapon;
	}

	public void setCustomizedWeapon(CustomizedWeapon customizedWeapon) {
		this.customizedWeapon = customizedWeapon;
	}

	public Set<?> getGameCharacters() {
		return this.gameCharacters;
	}

	public void setGameCharacters(Set<?> gameCharacters) {
		this.gameCharacters = gameCharacters;
	}

	public Set<?> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<?> jobs) {
		this.jobs = jobs;
	}

}
