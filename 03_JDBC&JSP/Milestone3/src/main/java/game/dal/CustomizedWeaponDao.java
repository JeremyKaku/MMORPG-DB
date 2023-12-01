package game.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.*;

public class CustomizedWeaponDao extends WeaponDao {
	protected ConnectionManager connectionManager;
	private static CustomizedWeaponDao instance = null;

	protected CustomizedWeaponDao() {
		connectionManager = new ConnectionManager();
	}

	public static CustomizedWeaponDao getInstance() {
		if (instance == null) {
			instance = new CustomizedWeaponDao();
		}
		return instance;
	}

	public CustomizedWeapon create(CustomizedWeapon customWeapon) throws SQLException {
		Weapon weapon = create(new Weapon(customWeapon.getItemID(), customWeapon.getItemName(), customWeapon.getMaxStackSize(),
				customWeapon.getVendorPrice(), customWeapon.getItemLevel(), customWeapon.getRequiredLevel(),
				customWeapon.getDamageDone(), customWeapon.getAutoAttack(), customWeapon.getAttackDelay()));

		String insertCustomWeapon = "INSERT INTO customized_weapon(item_id,item_quality,customized_condition,dye_color,maker) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		customWeapon.setItemID(weapon.getItemID());
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCustomWeapon);
			insertStmt.setInt(1, customWeapon.getItemID());
			insertStmt.setString(2, customWeapon.getItemQuality());
			insertStmt.setInt(3, customWeapon.getCustomizedCondition());
			insertStmt.setString(4, customWeapon.getDyeColor());
			insertStmt.setString(5, customWeapon.getMaker());
			insertStmt.executeUpdate();
			return customWeapon;
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

	public CustomizedWeapon getCustomizedWeaponByID(int itemID) throws SQLException {
		String selectCustomWeapon = "SELECT i.item_id as item_id, i.item_name as item_name, i.max_stack_size as max_stack_size, i.vendor_price as vendor_price,"
				+ "	w.item_level as item_level, w.required_level as required_level, w.damage_done as damage_done, w.auto_attack as auto_attack,w.attack_delay as attack_delay, "
				+ " cw.item_quality as item_quality, cw.customized_condition as customized_condition, cw.dye_color as dye_color, cw.maker as maker "
				+ " FROM customized_weapon cw JOIN weapon w ON cw.item_id = w.item_id "
				+ " JOIN Quality q ON q.item_quality = cw.item_quality" + " JOIN item i ON i.item_id = cw.item_id"
				+ " WHERE cw.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomWeapon);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrice = results.getInt("vendor_price");

				int resultItemLevel = results.getInt("item_level");
				int resultReqLevel = results.getInt("required_level");
				int resultDamageDone = results.getInt("damage_done");
				BigDecimal resultAutoAttack = results.getBigDecimal("auto_attack");
				BigDecimal resultAttackDelay = results.getBigDecimal("attack_delay");

				String resultItemQuality = results.getString("item_quality");
				int resultItemCustomCond = results.getInt("customized_condition");
				String resultDyeColor = results.getString("dye_color");
				String resultItemMaker = results.getString("maker");

				CustomizedWeapon custWeapon = new CustomizedWeapon(itemID, resultItemName, resultMaxStackSize,
						resultVendorPrice, resultItemLevel, resultReqLevel, resultDamageDone, resultAutoAttack,
						resultAttackDelay, resultItemQuality, resultItemCustomCond, resultDyeColor, resultItemMaker);
				return custWeapon;
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
