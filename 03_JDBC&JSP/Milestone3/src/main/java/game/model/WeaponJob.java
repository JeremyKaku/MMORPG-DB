package game.model;

public class WeaponJob {

	protected Weapon weapon;
	protected Job job;

	public WeaponJob() {
	}

	public WeaponJob(Weapon weapon, Job job) {
		this.weapon = weapon;
		this.job = job;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}