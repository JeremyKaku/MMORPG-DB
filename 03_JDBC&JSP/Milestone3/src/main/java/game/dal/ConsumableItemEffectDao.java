package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.Attribute;
import game.model.ConsumableItem;
import game.model.ConsumableItemEffect;

public class ConsumableItemEffectDao {
	protected ConnectionManager connectionManager;
	private static ConsumableItemEffectDao instance = null;

	protected ConsumableItemEffectDao() {
		connectionManager = new ConnectionManager();
	}

	public static ConsumableItemEffectDao getInstance() {
		if (instance == null) {
			instance = new ConsumableItemEffectDao();
		}
		return instance;
	}

	public ConsumableItemEffect create(ConsumableItemEffect consumableItemEffect) throws SQLException {
		String insertCSEffect = "INSERT INTO consumable_item_effect(item_id,attribute_id,effect_percentage,max_effect_value) "
				+ "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCSEffect);
			insertStmt.setInt(1, consumableItemEffect.getItem().getItemID());
			insertStmt.setInt(2, consumableItemEffect.getAttribute().getAttributeID());
			insertStmt.setInt(3, consumableItemEffect.getEffectPercentage());
			insertStmt.setInt(4, consumableItemEffect.getMaxEffectValue());
			insertStmt.executeUpdate();
			return consumableItemEffect;
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

	public ConsumableItemEffect getConsumableItemEffectByID(int itemID, int attributeID) throws SQLException {
		String selectConsumableItemEffect = "SELECT ci.item_id as item_id,ci.item_level as item_level,ci.item_description as item_description,a.attribute_id as attribute_id,a.attribute_name as attribute_name,ce.effect_percentage as effect_percentage,ce.max_effect_value as max_effect_value FROM consumable_item_effect ce JOIN consumable_item ci ON ce.item_id = ci.item_id JOIN attribute a ON a.attribute_id=ce.attribute_id WHERE item_id=? and attribute_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectConsumableItemEffect);
			selectStmt.setInt(1, itemID);
			selectStmt.setInt(2, attributeID);
			results = selectStmt.executeQuery();
			ConsumableItemDao consumableItemDao = ConsumableItemDao.getInstance();
			AttributeDao attributeDao = AttributeDao.getInstance();
			if (results.next()) {
				int resultEffectPercentage = results.getInt("effect_percentage");
				int resultMaxEffectValue = results.getInt("max_effect_value");

				ConsumableItem consumableItem = consumableItemDao.getConsumableItemByID(itemID);
				Attribute attribute = attributeDao.getAttributeByID(attributeID);
				ConsumableItemEffect consumableItemEffect = new ConsumableItemEffect(consumableItem, attribute,
						resultEffectPercentage, resultMaxEffectValue);
				return consumableItemEffect;
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
