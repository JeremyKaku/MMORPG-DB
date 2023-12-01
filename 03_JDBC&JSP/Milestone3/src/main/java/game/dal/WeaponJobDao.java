package game.dal;

import game.model.WeaponJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeaponJobDao {
    protected ConnectionManager connectionManager;

    private static WeaponJobDao instance = null;

    public static WeaponJobDao getInstance() {
        if (instance == null) {
            instance = new WeaponJobDao();
        }
        return instance;
    }

    protected WeaponJobDao() {
        connectionManager = new ConnectionManager();
    }

    public WeaponJob create(WeaponJob weaponJob) throws SQLException {
        String insertWeaponJob = "INSERT INTO weapon_job (item_id, job_id) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponJob);
            insertStmt.setInt(1, weaponJob.getWeapon().getItemID());
            insertStmt.setInt(2, weaponJob.getJob().getJobId());
            insertStmt.executeUpdate();

            return weaponJob;
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

    public WeaponJob getWeaponJobByIds(int itemId, int jobId) throws SQLException {
        String selectWeaponJob = "SELECT item_id, job_id FROM weapon_job WHERE item_id = ? AND job_id = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        JobDao jobDao = JobDao.getInstance();
        WeaponDao weaponDao = WeaponDao.getInstance();

        try {
        	connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectWeaponJob);
            selectStmt.setInt(1, itemId);
            selectStmt.setInt(2, jobId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultItemID = results.getInt("item_id");

                int resultJobID = results.getInt("job_id");
                
                WeaponJob weaponJob = new WeaponJob();
                weaponJob.setWeapon(weaponDao.getWeaponByID(resultItemID));
                weaponJob.setJob(jobDao.getJobById(resultJobID));

                return weaponJob;
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
