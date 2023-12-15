package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import game.model.Job;

import java.util.*;

public class JobDao {
	protected ConnectionManager connectionManager;

	private static JobDao instance = null;

	public static JobDao getInstance() {
		if (instance == null) {
			instance = new JobDao();
		}
		return instance;
	}

	protected JobDao() {
		connectionManager = new ConnectionManager();
	}

	public Job create(Job job) throws SQLException {
		String insertJob = "INSERT INTO job (job_name, availiability) VALUES (?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, job.getJobName());
			insertStmt.setBoolean(2, job.isAvailability());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int jobId = -1;
			if (resultKey.next()) {
				jobId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			job.setJobId(jobId);
			return job;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}

	}

	public Job getJobById(int jobId) throws SQLException {

		String selectBlogComment = "SELECT job_id, job_name, availiability FROM job WHERE job_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogComment);
			selectStmt.setInt(1, jobId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultJobId = results.getInt("job_id");
				String jobName = results.getString("job_name");
				boolean isAvailability = results.getBoolean("availiability");

				Job job = new Job(resultJobId, jobName, isAvailability);
				return job;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Job> getAllJobs() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String selectAllJobsQuery = "SELECT * FROM job";
 
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectAllJobsQuery);
             ResultSet results = selectStmt.executeQuery()) {
 
            while (results.next()) {
                int jobId = results.getInt("job_id");
                String jobName = results.getString("job_name");
                boolean availability = results.getBoolean("availiability");
 
                Job job = new Job(jobId, jobName, availability);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return jobs;
    }
}
