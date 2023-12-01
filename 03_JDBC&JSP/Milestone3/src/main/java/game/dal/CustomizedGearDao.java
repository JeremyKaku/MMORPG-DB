package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.*;

public class CustomizedGearDao extends GearDao {
	protected ConnectionManager connectionManager;
	private static CustomizedGearDao instance = null;

	protected CustomizedGearDao() {
		connectionManager = new ConnectionManager();
	}

	public static CustomizedGearDao getInstance() {
		if (instance == null) {
			instance = new CustomizedGearDao();
		}
		return instance;
	}

	public CustomizedGear create(CustomizedGear customGear) throws SQLException {
		Gear newGear = create(new Gear(customGear.getItemID(), customGear.getItemName(), customGear.getMaxStackSize(),
				customGear.getVendorPrice(), customGear.getItemLevel(), customGear.getGearSlot(),
				customGear.getRequiredLevel(), customGear.getDefenseRating(), customGear.getMagicDefenseRating()));

		String insertCustomGear = "INSERT INTO customized_gear(item_id,item_quality,customized_condition,dye_color,maker) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		customGear.setItemID(newGear.getItemID());
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCustomGear);
			insertStmt.setInt(1, customGear.getItemID());
			insertStmt.setString(2, customGear.getItemQuality());
			insertStmt.setInt(3, customGear.getCustomizedCondition());
			insertStmt.setString(4, customGear.getDyeColor());
			insertStmt.setString(5, customGear.getMaker());
			insertStmt.executeUpdate();
			return customGear;
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

	public CustomizedGear getCustomizedGearByID(int itemID) throws SQLException {
		String selectCustomItem = "SELECT i.item_id as item_id, i.item_name as item_name, i.max_stack_size as max_stack_size, i.vendor_price as vendor_price,"
				+ " g.item_level as item_level,g.gear_slot_id as gear_slot_id, g.required_level as required_level,g.defense_rating as defense_rating,"
				+ " g.magic_defense_rating as magic_defense_rating, cg.item_quality as item_quality, cg.customized_condition as customized_condition,"
				+ " cg.dye_color as dye_color, cg.maker as maker "
				+ " FROM customized_gear cg INNER JOIN gear g ON cg.item_id = g.item_id "
				+ " JOIN Quality q ON q.item_quality = cg.item_quality" + " JOIN item i ON i.item_id = cg.item_id"
				+ " WHERE cg.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomItem);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			GearSlotDao gearSlotDao = GearSlotDao.getInstance();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrice = results.getInt("vendor_price");

				int resultItemLevel = results.getInt("item_level");
				int resultGearSlotID = results.getInt("gear_slot_id");
				int resultReqLevel = results.getInt("required_level");
				int resultDefenseRating = results.getInt("defense_rating");
				int resultMagicRating = results.getInt("magic_defense_rating");

				String resultItemQuality = results.getString("item_quality");
				int resultItemCustomCond = results.getInt("customized_condition");
				String resultDyeColor = results.getString("dye_color");
				String resultItemMaker = results.getString("maker");
				GearSlot gearSlot = gearSlotDao.getGearSlotById(resultGearSlotID);
				CustomizedGear custGear = new CustomizedGear(itemID, resultItemName, resultMaxStackSize,
						resultVendorPrice, resultItemLevel, gearSlot, resultReqLevel, resultDefenseRating,
						resultMagicRating, resultItemQuality, resultItemCustomCond, resultDyeColor, resultItemMaker);
				return custGear;
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
