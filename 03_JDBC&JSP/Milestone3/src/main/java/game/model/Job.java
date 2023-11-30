package game.model;

public class Job {

	private int jobId;
	private String jobName;
	private boolean availability;

	public int getJobId() {
		return jobId;
	}

	public Job(int jobId, String jobName, boolean availability) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.availability = availability;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}