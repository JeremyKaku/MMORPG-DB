package game.model;

public class Job {

	protected int jobId;
	protected String jobName;
	protected boolean availability;

	public Job() {
	}

	public Job(int jobId, String jobName, boolean availability) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.availability = availability;
	}

	public int getJobId() {
		return jobId;
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