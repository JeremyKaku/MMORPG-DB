package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import game.model.ConsumableItem;
import game.model.Item;

public class ConsumableItemDao extends ItemDao {
	protected ConnectionManager connectionManager;
	private static ConsumableItemDao instance = null;

	protected ConsumableItemDao() {
		connectionManager = new ConnectionManager();
	}

	public static ConsumableItemDao getInstance() {
		if (instance == null) {
			instance = new ConsumableItemDao();
		}
		return instance;
	}

	public ConsumableItem create(ConsumableItem consumable) throws SQLException {
		Item newItem = create(new Item(consumable.getItemID(), consumable.getItemName(), consumable.getMaxStackSize(),
				consumable.getVendorPrice()));

		String insertConsumable = "INSERT INTO consumable_item(item_id,item_level,item_description) "
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		consumable.setItemID(newItem.getItemID());
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertConsumable);
			insertStmt.setInt(1, consumable.getItemID());
			insertStmt.setInt(2, consumable.getItemLevel());
			insertStmt.setString(3, consumable.getItemDescription());
			insertStmt.executeUpdate();
			return consumable;
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

	public ConsumableItem getConsumableItemByID(int itemID) throws SQLException {
		String selectWeapon = "SELECT c.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,c.item_level as item_level,c.item_description as item_description "
				+ "FROM ConsumableItem c INNER JOIN Item i " + "  ON c.item_id = i.item_id " + "WHERE i.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeapon);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrize = results.getInt("vendor_price");
				int resultItemLevel = results.getInt("item_level");
				String resultItemDes = results.getString("item_description");
				ConsumableItem reviewer = new ConsumableItem(itemID, resultItemName, resultMaxStackSize,
						resultVendorPrize, resultItemLevel, resultItemDes);
				return reviewer;
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

	public ConsumableItem updateConsumableItemDescription(ConsumableItem consumableItem, String newItemDescription)
			throws SQLException {
		String updateItemDescription = "UPDATE consumable_item SET item_description = ? WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItemDescription);
			updateStmt.setString(1, newItemDescription);
			updateStmt.setInt(2, consumableItem.getItemID());
			updateStmt.executeUpdate();
			consumableItem.setItemDescription(newItemDescription);
			return consumableItem;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public ConsumableItem delete(ConsumableItem consumableItem) throws SQLException {
		String deleteConsumableItem = "DELETE FROM consumable_item WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteConsumableItem);
			deleteStmt.setInt(1, consumableItem.getItemID());
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
	
	public List<Item> getAllConsumableItems() throws SQLException {
        List<Item> consumableItems = new ArrayList<>();
        String selectAllConsumableItemsQuery = "SELECT c.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,c.item_level as item_level,c.item_description as item_description "
				+ "FROM consumable_item c INNER JOIN item i " + "  ON c.item_id = i.item_id ";
 
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectAllConsumableItemsQuery);
             ResultSet results = selectStmt.executeQuery()) {
 
            while (results.next()) {
                int itemID = results.getInt("item_id");
                String itemName = results.getString("item_name");
                int maxStackSize = results.getInt("max_stack_size");
                int vendorPrice = results.getInt("vendor_price");
                int itemLevel = results.getInt("item_level");
                String itemDescription = results.getString("item_description");
 
                ConsumableItem consumableItem = new ConsumableItem(itemID, itemName, maxStackSize, vendorPrice, itemLevel, itemDescription);
                consumableItems.add(consumableItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return consumableItems;
    }

}
