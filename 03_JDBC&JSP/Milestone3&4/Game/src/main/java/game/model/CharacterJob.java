package game.model;

public class CharacterJob implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private CharacterJobId id;
	private GameCharacter gameCharacter;
	private Job job;
	private int jobLevel;
	private int experiencePoint;
	private boolean isCurrentJob;

	public CharacterJob() {
	}

	public CharacterJob(CharacterJobId id, GameCharacter gameCharacter, Job job, int jobLevel, int experiencePoint,
			boolean isCurrentJob) {
		this.id = id;
		this.gameCharacter = gameCharacter;
		this.job = job;
		this.jobLevel = jobLevel;
		this.experiencePoint = experiencePoint;
		this.isCurrentJob = isCurrentJob;
	}

	public CharacterJobId getId() {
		return this.id;
	}

	public void setId(CharacterJobId id) {
		this.id = id;
	}

	public GameCharacter getGameCharacter() {
		return this.gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getJobLevel() {
		return this.jobLevel;
	}

	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}

	public int getExperiencePoint() {
		return this.experiencePoint;
	}

	public void setExperiencePoint(int experiencePoint) {
		this.experiencePoint = experiencePoint;
	}

	public boolean isIsCurrentJob() {
		return this.isCurrentJob;
	}

	public void setIsCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}

}
