package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import game.model.GearSlot;

public class GearSlotDao {
	protected ConnectionManager connectionManager;

	private static GearSlotDao instance = null;

	public static GearSlotDao getInstance() {
		if (instance == null) {
			instance = new GearSlotDao();
		}
		return instance;
	}

	protected GearSlotDao() {
		connectionManager = new ConnectionManager();
	}

	public GearSlot create(GearSlot gearSlot) throws SQLException {
		String insertGearSlot = "INSERT INTO gear_slot (gear_slot_name) VALUES (?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGearSlot, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, gearSlot.getGearSlotName());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int gearSlotId = -1;
			if (resultKey.next()) {
				gearSlotId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			gearSlot.setGearSlotId(gearSlotId);
			return gearSlot;
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

	public GearSlot getGearSlotById(int gearSlotId) throws SQLException {
		String selectGearSlot = "SELECT gear_slot_id, gear_slot_name FROM gear_slot WHERE gear_slot_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGearSlot);
			selectStmt.setInt(1, gearSlotId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultGearSlotId = results.getInt("gear_slot_id");
				String gearSlotName = results.getString("gear_slot_name");

				GearSlot gearSlot = new GearSlot();
				gearSlot.setGearSlotId(resultGearSlotId);
				gearSlot.setGearSlotName(gearSlotName);
				return gearSlot;
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