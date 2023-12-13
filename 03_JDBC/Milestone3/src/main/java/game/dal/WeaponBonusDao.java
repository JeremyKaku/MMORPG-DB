package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import game.model.Attribute;
import game.model.Weapon;
import game.model.WeaponBonus;

public class WeaponBonusDao {
	protected ConnectionManager connectionManager;
	private static WeaponBonusDao instance = null;

	protected WeaponBonusDao() {
		connectionManager = new ConnectionManager();
	}

	public static WeaponBonusDao getInstance() {
		if (instance == null) {
			instance = new WeaponBonusDao();
		}
		return instance;
	}

	public WeaponBonus create(WeaponBonus weaponBonus) throws SQLException {
		String insertWeaponBonus = "INSERT INTO weapon_bonus(item_id, attribute_id, bonus_value) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWeaponBonus);
			insertStmt.setInt(1, weaponBonus.getItem().getItemID());
			insertStmt.setInt(2, weaponBonus.getAttribute().getAttributeID());
			insertStmt.setInt(3, weaponBonus.getBonusValue());
			insertStmt.executeUpdate();
			return weaponBonus;
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

	public WeaponBonus getWeaponBonusByID(int itemId, int attributeId) throws SQLException {
		String selectWeaponBonus = "SELECT item_id, attribute_id, bonus_value FROM weapon_bonus WHERE item_id=? AND attribute_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeaponBonus);
			selectStmt.setInt(1, itemId);
			selectStmt.setInt(2, attributeId);
			results = selectStmt.executeQuery();
			WeaponDao weaponDao = WeaponDao.getInstance();
			AttributeDao attributeDao = AttributeDao.getInstance();
			if (results.next()) {
				int resultBonusValue = results.getInt("bonus_value");
				Weapon weapon = weaponDao.getWeaponByID(itemId);
				Attribute attribute = attributeDao.getAttributeByID(attributeId);
				WeaponBonus weaponBonus = new WeaponBonus(weapon, attribute, resultBonusValue);
				return weaponBonus;
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

	public WeaponBonus delete(WeaponBonus weaponBonus) throws SQLException {
		String deleteWeaponBonus = "DELETE FROM weapon_bonus WHERE item_id=? AND attribute_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWeaponBonus);
			deleteStmt.setInt(1, weaponBonus.getItem().getItemID());
			deleteStmt.setInt(2, weaponBonus.getAttribute().getAttributeID());
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
