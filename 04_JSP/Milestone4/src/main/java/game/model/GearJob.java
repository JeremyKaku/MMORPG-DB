package game.model;

public class GearJob {
	protected Gear gear;
	protected Job job;

	public GearJob() {
	}

	public GearJob(Gear gear, Job job) {
		this.gear = gear;
		this.job = job;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}