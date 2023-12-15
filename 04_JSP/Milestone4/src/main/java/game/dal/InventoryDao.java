package game.dal;

import game.model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {
	protected ConnectionManager connectionManager;

	private static InventoryDao instance = null;

	public static InventoryDao getInstance() {
		if (instance == null) {
			instance = new InventoryDao();
		}
		return instance;
	}

	protected InventoryDao() {
		connectionManager = new ConnectionManager();
	}

	public Inventory create(Inventory inventory) throws SQLException {
		String insertInventory = "INSERT INTO inventory(character_id, inventory_slot_id, quantity, item_id) VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertInventory);
			insertStmt.setInt(1, inventory.getCharacter().getCharacterID());
			insertStmt.setInt(2, inventory.getInventorySlotId());
			insertStmt.setInt(3, inventory.getQuantity());
			insertStmt.setInt(4, inventory.getItem().getItemID());
			insertStmt.executeUpdate();

			return inventory;
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

	public Inventory getInventoryByID(int characterId, int inventorySlotId) throws SQLException {
		String selectInventory = "SELECT character_id, inventory_slot_id, quantity, item_id FROM inventory WHERE character_id = ? AND inventory_slot_id = ?; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		ItemDao itemDao = ItemDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInventory);
			selectStmt.setInt(1, characterId);
			selectStmt.setInt(2, inventorySlotId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCharacterID = results.getInt("character_id");
				int resultInventorySlotID = results.getInt("inventory_slot_id");
				int quantity = results.getInt("quantity");

				int itemID = results.getInt("item_id");

				Inventory inventory = new Inventory();
				inventory.setCharacter(characterDao.getCharacterByID(resultCharacterID));
				inventory.setInventorySlotId(resultInventorySlotID);
				inventory.setQuantity(quantity);
				inventory.setItem(itemDao.getItemByID(itemID));

				return inventory;
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

	public List<Inventory> getInventoryByCharacterID(int characterId) throws SQLException {
		List<Inventory> inventories = new ArrayList<>();
		String selectInventory = "SELECT character_id, inventory_slot_id, quantity, item_id FROM inventory WHERE character_id = ?; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		ItemDao itemDao = ItemDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInventory);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int resultCharacterID = results.getInt("character_id");
				int resultInventorySlotID = results.getInt("inventory_slot_id");
				int quantity = results.getInt("quantity");
				int itemID = results.getInt("item_id");

				Inventory inventory = new Inventory();
				inventory.setCharacter(characterDao.getCharacterByID(resultCharacterID));
				inventory.setInventorySlotId(resultInventorySlotID);
				inventory.setQuantity(quantity);
				inventory.setItem(itemDao.getItemByID(itemID));

				inventories.add(inventory);
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
		return inventories;
	}
}
