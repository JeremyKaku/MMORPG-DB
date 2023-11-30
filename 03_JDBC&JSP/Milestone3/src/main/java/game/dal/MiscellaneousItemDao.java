package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.MiscellaneousItem;

public class MiscellaneousItemDao {
	protected ConnectionManager connectionManager;
	private static MiscellaneousItemDao instance = null;

	protected MiscellaneousItemDao() {
		connectionManager = new ConnectionManager();
	}

	public static MiscellaneousItemDao getInstance() {
		if (instance == null) {
			instance = new MiscellaneousItemDao();
		}
		return instance;
	}

	public MiscellaneousItem create(MiscellaneousItem misItem) throws SQLException {
		String insertMisItem = "INSERT INTO miscellaneous_item(item_id,item_description) " + "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMisItem);
			insertStmt.setInt(1, misItem.getItemID());
			insertStmt.setString(2, misItem.getItemDescription());
			insertStmt.executeUpdate();
			return misItem;
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

	public MiscellaneousItem getConsumableItemByID(int itemID) throws SQLException {
		String selectMisItem = "SELECT m.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,m.item_description as item_description "
				+ "FROM miscellaneous_item m INNER JOIN Item i " + "  ON m.item_id = i.item_id " + "WHERE i.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMisItem);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				Double resultVendorPrize = results.getDouble("vendor_price");
				String resultItemDes = results.getString("item_description");
				MiscellaneousItem misItem = new MiscellaneousItem(itemID, resultItemName, resultMaxStackSize,
						resultVendorPrize, resultItemDes);
				return misItem;
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

	public MiscellaneousItem updateItemDescription(MiscellaneousItem misItem, String newItemDescription)
			throws SQLException {
		String updateItemDescription = "UPDATE miscellaneous_item SET item_description = ? WHERE item_id = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItemDescription);
			updateStmt.setString(1, newItemDescription);
			updateStmt.setInt(2, misItem.getItemID());
			updateStmt.executeUpdate();

			misItem.setItemDescription(newItemDescription);
			return misItem;
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

}
