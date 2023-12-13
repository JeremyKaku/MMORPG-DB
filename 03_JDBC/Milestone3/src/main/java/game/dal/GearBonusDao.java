package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import game.model.Attribute;
import game.model.Gear;
import game.model.GearBonus;

public class GearBonusDao {
	protected ConnectionManager connectionManager;
	private static GearBonusDao instance = null;

	protected GearBonusDao() {
		connectionManager = new ConnectionManager();
	}

	public static GearBonusDao getInstance() {
		if (instance == null) {
			instance = new GearBonusDao();
		}
		return instance;
	}

	public GearBonus create(GearBonus gearBonus) throws SQLException {
		String insertGearBonus = "INSERT INTO gear_bonus(item_id, attribute_id, bonus_value) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGearBonus);
			insertStmt.setInt(1, gearBonus.getItem().getItemID());
			insertStmt.setInt(2, gearBonus.getAttribute().getAttributeID());
			insertStmt.setInt(3, gearBonus.getBonusValue());
			insertStmt.executeUpdate();
			return gearBonus;
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

	public GearBonus getGearBonusByID(int itemId, int attributeId) throws SQLException {
		String selectGearBonus = "SELECT item_id, attribute_id, bonus_value FROM gear_bonus WHERE item_id=? AND attribute_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGearBonus);
			selectStmt.setInt(1, itemId);
			selectStmt.setInt(2, attributeId);
			results = selectStmt.executeQuery();
			GearDao gearDao = GearDao.getInstance();
			AttributeDao attributeDao = AttributeDao.getInstance();
			if (results.next()) {
				int resultBonusValue = results.getInt("bonus_value");
				Gear gear = gearDao.getGearByID(itemId);
				Attribute attribute = attributeDao.getAttributeByID(attributeId);
				GearBonus gearBonus = new GearBonus(gear, attribute, resultBonusValue);
				return gearBonus;
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

	public GearBonus delete(GearBonus gearBonus) throws SQLException {
		String deleteGearBonus = "DELETE FROM gear_bonus WHERE item_id=? AND attribute_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGearBonus);
			deleteStmt.setInt(1, gearBonus.getItem().getItemID());
			deleteStmt.setInt(2, gearBonus.getAttribute().getAttributeID());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
