package game.model;

public class CharacterJob {
	private Character character;
	private Job job;
	private int jobLevel;
	private int experiencePoint;
	private boolean isCurrentJob;

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