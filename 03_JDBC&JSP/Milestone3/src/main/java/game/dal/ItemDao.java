package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String insertItem = "INSERT INTO Item(item_id,item_name,max_stack_size,vendor_price) " + "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, item.getItemID());
			insertStmt.setString(2, item.getItemName());
			insertStmt.setInt(3, item.getMaxStackSize());
			insertStmt.setDouble(4, item.getVendorPrice());
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
				String resultItemName = results.getString("item_level");
				int resultMaxStackSize = results.getInt("required_level");
				Double resultVendorPrize = results.getDouble("damage_done");
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

}
