package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.CustomizedWeapon;
import game.model.Quality;

public class CustomizedWeaponDao {
	protected ConnectionManager connectionManager;
	private static CustomizedWeaponDao instance = null;
	protected CustomizedWeaponDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CustomizedWeaponDao getInstance() {
		if(instance == null) {
			instance = new CustomizedWeaponDao();
		}
		return instance;
	}
	
	   public CustomizedWeapon create(CustomizedWeapon customWeapon) throws SQLException {
	        String insertCustomWeapon = "INSERT INTO customized_weapon(item_id,item_quality,customized_condition,dye_color,maker) " +
	                "VALUES(?,?,?,?,?);";
	        Connection connection = null;
	        PreparedStatement insertStmt = null;
	        ResultSet resultKey = null;

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
	        String selectCustomWeapon =
					"SELECT i.item_id as item_id, i.item_name as item_name, i.max_stack_size as max_stack_size, i.vendor_price as vendor_price," + 
						    " g.item_level as item_level,g.gear_slot_id as gear_slot_id, g.required_level as required_level,g.defense_rating as defense_rating," +
						    " g.magic_defense_rating as magic_defense_rating, cg.item_quality as item_quality, cg.customized_condition as customized_condition," +
						    " cg.dye_color as dye_color, cg.maker as maker " +
						    " FROM customized_weapon cg JOIN weapon g ON cg.item_id = g.item_id " +
						    " JOIN Quality q ON q.item_quality = cg.item_quality" +
							" JOIN item i ON i.item_id = cg.item_id" + 
							" WHERE cg.item_id=?;";
	        Connection connection = null;
	        PreparedStatement selectStmt = null;
	        ResultSet results = null;

	        try {
	            connection = connectionManager.getConnection();
	            selectStmt = connection.prepareStatement(selectCustomWeapon);
	            selectStmt.setInt(1, itemID);
	            results = selectStmt.executeQuery();
	            QualityDao qualityDao = QualityDao.getInstance();

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

	                Quality quality = qualityDao.getQualityByName(resultItemQuality);
	                CustomizedWeapon custWeapon = new CustomizedWeapon(itemID,resultItemName,resultMaxStackSize,resultVendorPrice,resultItemLevel,resultGearSlotID,resultReqLevel,resultDefenseRating,resultMagicRating,quality,resultItemCustomCond,resultDyeColor,resultItemMaker);
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
