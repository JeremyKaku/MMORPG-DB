package game.dal;

import game.model.GearJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GearJobDao {
	protected ConnectionManager connectionManager;

	private static GearJobDao instance = null;

	public static GearJobDao getInstance() {
		if (instance == null) {
			instance = new GearJobDao();
		}
		return instance;
	}

	protected GearJobDao() {
		connectionManager = new ConnectionManager();
	}

	public GearJob create(GearJob gearJob) throws SQLException {
		String insertGearJob = "INSERT INTO gear_job (item_id, job_id) VALUES (?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGearJob);
			insertStmt.setInt(1, gearJob.getGear().getItemID());
			insertStmt.setInt(2, gearJob.getJob().getJobId());
			insertStmt.executeUpdate();

			return gearJob;
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

	public GearJob getGearJobByIds(int itemId, int jobId) throws SQLException {
		String selectGearJob = "SELECT item_id, job_id FROM gear_job WHERE item_id = ? AND job_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		JobDao jobDao = JobDao.getInstance();
		GearDao gearDao = GearDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGearJob);
			selectStmt.setInt(1, itemId);
			selectStmt.setInt(2, jobId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultItemID = results.getInt("item_id");

				int resultJobID = results.getInt("job_id");

				GearJob gearJob = new GearJob();
				gearJob.setGear(gearDao.getGearByID(resultItemID));
				gearJob.setJob(jobDao.getJobById(resultJobID));

				return gearJob;
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
}
