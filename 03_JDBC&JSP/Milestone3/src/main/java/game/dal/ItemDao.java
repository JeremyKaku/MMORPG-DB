package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import game.model.Item;

public class ItemDao {
	protected ConnectionManager connectionManager;
	private static ItemDao instance = null;

	protected ItemDao() {
		connectionManager = new ConnectionManager();
	}

	public static ItemDao getInstance() {
		if (instance == null) {
			instance = new ItemDao();
		}
		return instance;
	}

	public Item create(Item item) throws SQLException {
		String insertItem = "INSERT INTO Item(item_name,max_stack_size,vendor_price) " + "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, item.getItemName());
			insertStmt.setInt(2, item.getMaxStackSize());
			insertStmt.setDouble(3, item.getVendorPrice());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int itemID = -1;
			if (resultKey.next()) {
				itemID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			item.setItemID(itemID);
			return item;
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

	public Item getItemByID(int itemID) throws SQLException {
		String selectItem = "SELECT item_id,item_name,max_stack_size,vendor_price FROM Item WHERE item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrize = results.getInt("vendor_price");
				Item item = new Item(itemID, resultItemName, resultMaxStackSize, resultVendorPrize);
				return item;
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

	public List<Item> getAllItems() throws SQLException {
		List<Item> items = new ArrayList<>();
		String selectAllItems = "SELECT item_id, item_name, max_stack_size, vendor_price FROM Item;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllItems);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int itemId = results.getInt("item_id");
				String itemName = results.getString("item_name");
				int maxStackSize = results.getInt("max_stack_size");
				int vendorPrice = results.getInt("vendor_price");

				Item item = new Item(itemId, itemName, maxStackSize, vendorPrice);
				items.add(item);
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
		return items;
	}

}
