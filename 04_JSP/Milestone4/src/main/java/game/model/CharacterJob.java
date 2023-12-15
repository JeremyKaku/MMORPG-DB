package game.model;

public class CharacterJob {
	protected Character character;
	protected Job job;
	protected int jobLevel;
	protected int experiencePoint;
	protected boolean isCurrentJob;

	public CharacterJob() {
	}

	public CharacterJob(Character character, Job job, int jobLevel, int experiencePoint, boolean isCurrentJob) {
		this.character = character;
		this.job = job;
		this.jobLevel = jobLevel;
		this.experiencePoint = experiencePoint;
		this.isCurrentJob = isCurrentJob;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}

	public int getExperiencePoint() {
		return experiencePoint;
	}

	public void setExperiencePoint(int experiencePoint) {
		this.experiencePoint = experiencePoint;
	}

	public boolean isCurrentJob() {
		return isCurrentJob;
	}

	public void setCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}

}