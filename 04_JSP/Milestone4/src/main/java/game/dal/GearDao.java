package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import game.model.*;

public class GearDao extends ItemDao {
	protected ConnectionManager connectionManager;
	private static GearDao instance = null;

	protected GearDao() {
		connectionManager = new ConnectionManager();
	}

	public static GearDao getInstance() {
		if (instance == null) {
			instance = new GearDao();
		}
		return instance;
	}

	public Gear create(Gear gear) throws SQLException {
		Item newItem = create(
				new Item(gear.getItemID(), gear.getItemName(), gear.getMaxStackSize(), gear.getVendorPrice()));

		String insertGear = "INSERT INTO gear(item_id,item_level,gear_slot_id,required_level,defense_rating,magic_defense_rating) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		gear.setItemID(newItem.getItemID());
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGear);
			insertStmt.setInt(1, gear.getItemID());
			insertStmt.setInt(2, gear.getItemLevel());
			insertStmt.setInt(3, gear.getGearSlot().getGearSlotId());
			insertStmt.setInt(4, gear.getRequiredLevel());
			insertStmt.setFloat(5, gear.getDefenseRating());
			insertStmt.setFloat(6, gear.getMagicDefenseRating());
			insertStmt.executeUpdate();
			return gear;
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

	public Gear getGearByID(int itemID) throws SQLException {
		String selectGear = "SELECT g.item_id as item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,g.item_level as item_level,g.gear_slot_id as gear_slot_id,g.required_level as required_level,g.defense_rating as defense_rating,g.magic_defense_rating as magic_defense_rating "
				+ "FROM Gear g INNER JOIN Item i " + " ON g.item_id = i.item_id " + "WHERE i.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGear);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			GearSlotDao gearSlotDao = GearSlotDao.getInstance();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrize = results.getInt("vendor_price");
				int resultItemLevel = results.getInt("item_level");
				int resultGearSlotID = results.getInt("gear_slot_id");
				int resultRequiredLevel = results.getInt("required_level");
				int resultDefenseRating = results.getInt("defense_rating");
				int resultMagicDefenseRating = results.getInt("magic_defense_rating");
				GearSlot gearSlot = gearSlotDao.getGearSlotById(resultGearSlotID);
				Gear gear = new Gear(itemID, resultItemName, resultMaxStackSize, resultVendorPrize, resultItemLevel,
						gearSlot, resultRequiredLevel, resultDefenseRating, resultMagicDefenseRating);
				return gear;
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

	public List<Gear> getGearByPartialName(String name) throws SQLException {
		List<Gear> gears = new ArrayList<Gear>();
		String selectGearByPartialName = "SELECT g.item_id, i.item_name, i.max_stack_size, i.vendor_price, g.item_level, "
				+ "g.gear_slot_id, g.required_level, g.defense_rating, g.magic_defense_rating "
				+ "FROM gear g INNER JOIN item i ON g.item_id = i.item_id " + "WHERE i.item_name LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGearByPartialName);
			selectStmt.setString(1, "%" + name + "%");
			results = selectStmt.executeQuery();
			GearSlotDao gearSlotDao = GearSlotDao.getInstance();

			while (results.next()) {
				int itemID = results.getInt("item_id");
				String itemName = results.getString("item_name");
				int maxStackSize = results.getInt("max_stack_size");
				int vendorPrice = results.getInt("vendor_price");
				int itemLevel = results.getInt("item_level");
				int gearSlotID = results.getInt("gear_slot_id");
				int requiredLevel = results.getInt("required_level");
				int defenseRating = results.getInt("defense_rating");
				int magicDefenseRating = results.getInt("magic_defense_rating");
				GearSlot gearSlot = gearSlotDao.getGearSlotById(gearSlotID);
				Gear gear = new Gear(itemID, itemName, maxStackSize, vendorPrice, itemLevel, gearSlot, requiredLevel,
						defenseRating, magicDefenseRating);
				gears.add(gear);
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
		return gears;
	}

	public Gear delete(Gear gearItem) throws SQLException {
		String deleteGear = "DELETE FROM gear WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGear);
			deleteStmt.setInt(1, gearItem.getItemID());
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
	
	public List<Item> getAllGears() throws SQLException {
	    List<Item> gears = new ArrayList<>();
	    String selectAllGearsQuery = "SELECT g.item_id, i.item_name, i.max_stack_size, i.vendor_price, g.item_level, g.gear_slot_id,"
	            + "s.gear_slot_name, g.required_level, g.defense_rating, g.magic_defense_rating "
	            + "FROM gear g INNER JOIN item i ON g.item_id = i.item_id INNER JOIN gear_slot s ON g.gear_slot_id = s.gear_slot_id;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
 
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectAllGearsQuery);
	        results = selectStmt.executeQuery();
 
	        while (results.next()) {
	            int itemID = results.getInt("item_id");
	            String itemName = results.getString("item_name");
	            int maxStackSize = results.getInt("max_stack_size");
	            int vendorPrice = results.getInt("vendor_price");
	            int itemLevel = results.getInt("item_level");
	            int requiredLevel = results.getInt("required_level");
	            int defenseRating = results.getInt("defense_rating");
	            int magicDefenseRating = results.getInt("magic_defense_rating");
	            String gearSlotName = results.getString("gear_slot_name");
	            
	            GearSlot gearSlot = new GearSlot();
	            gearSlot.setGearSlotName(gearSlotName);
 
	            Gear gear = new Gear(itemID, itemName, maxStackSize, vendorPrice, itemLevel, gearSlot, requiredLevel,
	                    defenseRating, magicDefenseRating);
	            gears.add(gear);
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
	    return gears;
	}
}
