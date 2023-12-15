package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import game.model.CharacterJob;
import game.model.*;
import game.model.Character;

public class CharacterJobDao {
	protected ConnectionManager connectionManager;

	private static CharacterJobDao instance = null;

	public static CharacterJobDao getInstance() {
		if (instance == null) {
			instance = new CharacterJobDao();
		}
		return instance;
	}

	protected CharacterJobDao() {
		connectionManager = new ConnectionManager();
	}

	public CharacterJob insertCharacterJob(CharacterJob characterJob) throws SQLException {
		String insertCharacterJob = "INSERT INTO character_job (character_id, job_id, job_level, experience_point, is_current_job) VALUES (?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacterJob);
			insertStmt.setInt(1, characterJob.getCharacter().getCharacterID());
			insertStmt.setInt(2, characterJob.getJob().getJobId());
			insertStmt.setInt(3, characterJob.getJobLevel());
			insertStmt.setInt(4, characterJob.getExperiencePoint());
			insertStmt.setBoolean(5, characterJob.isCurrentJob());
			insertStmt.executeUpdate();

			return characterJob;
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

	public CharacterJob getCharacterJobByIds(int characterId, int jobId) throws SQLException {
		String selectCharacterJob = "SELECT character_id, job_id, job_level, experience_point, is_current_job FROM character_job WHERE character_id = ? AND job_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		JobDao jobDao = JobDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacterJob);
			selectStmt.setInt(1, characterId);
			selectStmt.setInt(2, jobId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCharacterID = results.getInt("character_id");
				int resultJobID = results.getInt("job_id");
				int jobLevel = results.getInt("job_level");
				int experiencePoint = results.getInt("experience_point");
				boolean isCurrentJob = results.getBoolean("is_current_job");

				CharacterJob characterJob = new CharacterJob();
				characterJob.setCharacter(characterDao.getCharacterByID(resultCharacterID));
				characterJob.setJob(jobDao.getJobById(resultJobID));
				characterJob.setJobLevel(jobLevel);
				characterJob.setExperiencePoint(experiencePoint);
				characterJob.setCurrentJob(isCurrentJob);

				return characterJob;
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

    public List<CharacterJob> getCharacterJobByCharacterID(int characterID) throws SQLException {
        List<CharacterJob> characterJobs = new ArrayList<>();
        String selectCharacterJobs =
                "SELECT * FROM character_job " +
                "JOIN job ON character_job.job_id = job.job_id " +
                "WHERE character_id = ?;";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectCharacterJobs)) {

            preparedStatement.setInt(1, characterID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
        		CharacterDao characterDao = CharacterDao.getInstance();
        		 Character character = characterDao.getCharacterByID(characterID);
                while (resultSet.next()) {
                    Job job = new Job(
                            resultSet.getInt("job_id"),
                            resultSet.getString("job_name"),
                            resultSet.getBoolean("availiability")
                    );

                    CharacterJob characterJob = new CharacterJob(
                            character,
                            job,
                            resultSet.getInt("job_level"),
                            resultSet.getInt("experience_point"),
                            resultSet.getBoolean("is_current_job")
                    );
                    characterJobs.add(characterJob);
                }
            }
        }
        return characterJobs;
    }
	
}



