package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import game.model.CharacterAttribute;
import game.model.Character;
import game.model.Attribute;

public class CharacterAttributeDao {
	protected ConnectionManager connectionManager;
	private static CharacterAttributeDao instance = null;

	protected CharacterAttributeDao() {
		connectionManager = new ConnectionManager();
	}

	public static CharacterAttributeDao getInstance() {
		if (instance == null) {
			instance = new CharacterAttributeDao();
		}
		return instance;
	}

	public CharacterAttribute create(CharacterAttribute characterAttribute) throws SQLException {
		String insertCharAttribute = "INSERT INTO character_attribute(character_id,attribute_id,attribute_value) "
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharAttribute);
			insertStmt.setInt(1, characterAttribute.getCharacter().getCharacterID());
			insertStmt.setInt(2, characterAttribute.getAttribute().getAttributeID());
			insertStmt.setString(3, characterAttribute.getAtrributeValue());
			insertStmt.executeUpdate();
			return characterAttribute;
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

	public List<CharacterAttribute> getAttributesByCharacter(Character character) throws SQLException {
		List<CharacterAttribute> attributes = new ArrayList<CharacterAttribute>();
		String selectAttributes = "SELECT character_id,attribute_id,attribute_value "
				+ "FROM Rating WHERE character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttributes);
			selectStmt.setInt(1, character.getCharacterID());
			results = selectStmt.executeQuery();
			AttributeDao attributeDao = AttributeDao.getInstance();
			while (results.next()) {
				int resultAttributeID = results.getInt("attribute_id");
				String resultAttributeValue = results.getString("attribute_value");

				Attribute attribute = attributeDao.getAttributeByID(resultAttributeID);
				CharacterAttribute characterAttribute = new CharacterAttribute(attribute, character,
						resultAttributeValue);
				attributes.add(characterAttribute);
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
		return attributes;
	}

	public CharacterAttribute updateRating(CharacterAttribute characterAttribute, String newAtrributeValue)
			throws SQLException {
		String updateAttributeValue = "UPDATE character_attribute SET attribute_value = ? WHERE character_id = ? AND attribute_id = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAttributeValue);
			updateStmt.setString(1, newAtrributeValue);
			updateStmt.setInt(2, characterAttribute.getCharacter().getCharacterID());
			updateStmt.setInt(3, characterAttribute.getAttribute().getAttributeID());
			updateStmt.executeUpdate();

			characterAttribute.setAtrributeValue(newAtrributeValue);
			return characterAttribute;
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

	public List<CharacterAttribute> getAllCharacterAttributes(int charID) throws SQLException {
		List<CharacterAttribute> characterAttributes = new ArrayList<>();
		String selectAllCharacterAttributes = "SELECT character_id, attribute_id, attribute_value FROM character_attribute where character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllCharacterAttributes);
			selectStmt.setInt(1, charID);
			results = selectStmt.executeQuery();
			CharacterDao charDao = CharacterDao.getInstance();
			AttributeDao attributeDao = AttributeDao.getInstance();

			while (results.next()) {
				int characterID = results.getInt("character_id");
				int attributeID = results.getInt("attribute_id");
				String attributeValue = results.getString("attribute_value");

				Character character = charDao.getCharacterByID(characterID);
				Attribute attribute = attributeDao.getAttributeByID(attributeID);

				CharacterAttribute characterAttribute = new CharacterAttribute(attribute, character, attributeValue);
				characterAttributes.add(characterAttribute);
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

		return characterAttributes;
	}

}
