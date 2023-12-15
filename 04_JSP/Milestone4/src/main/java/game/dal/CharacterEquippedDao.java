package game.dal;

import game.model.CharacterEquipped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterEquippedDao {
	protected ConnectionManager connectionManager;

	private static CharacterEquippedDao instance = null;

	public static CharacterEquippedDao getInstance() {
		if (instance == null) {
			instance = new CharacterEquippedDao();
		}
		return instance;
	}

	protected CharacterEquippedDao() {
		connectionManager = new ConnectionManager();
	}

	public CharacterEquipped create(CharacterEquipped characterEquipped) throws SQLException {
		String insertCharacterEquipped = "INSERT INTO character_equipped (character_id, gear_slot_id, item_id) VALUES (?, ?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacterEquipped);
			insertStmt.setInt(1, characterEquipped.getCharacter().getCharacterID());
			insertStmt.setInt(2, characterEquipped.getGearSlot().getGearSlotId());
			insertStmt.setInt(3, characterEquipped.getGear().getItemID());
			insertStmt.executeUpdate();

			return characterEquipped;
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

	public CharacterEquipped getCharacterEquippedByIds(int characterId, int gearSlotId) throws SQLException {
		String selectCharacterEquipped = "SELECT character_id, gear_slot_id, item_id FROM character_equipped WHERE character_id = ? AND gear_slot_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		GearSlotDao gearSlotDao = GearSlotDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacterEquipped);
			selectStmt.setInt(1, characterId);
			selectStmt.setInt(2, gearSlotId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				int resultCharacterId = results.getInt("character_id");
				int resultGearSlotId = results.getInt("gear_slot_id");
				int gearID = results.getInt("item_id");

				CharacterEquipped characterEquipped = new CharacterEquipped();
				characterEquipped.setCharacter(characterDao.getCharacterByID(resultCharacterId));
				characterEquipped.setGearSlot(gearSlotDao.getGearSlotById(resultGearSlotId));
				characterEquipped.setGear(GearDao.getInstance().getGearByID(gearID));

				return characterEquipped;
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

	public List<CharacterEquipped> getCharacterEquippedById(int characterId) throws SQLException {
		List<CharacterEquipped> characterEquippedLst = new ArrayList<>();
		String selectCharacterEquipped = "SELECT character_id, gear_slot_id, item_id FROM character_equipped WHERE character_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		GearSlotDao gearSlotDao = GearSlotDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacterEquipped);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int resultCharacterId = results.getInt("character_id");
				int resultGearSlotId = results.getInt("gear_slot_id");
				int gearID = results.getInt("item_id");

				CharacterEquipped characterEquipped = new CharacterEquipped();
				characterEquipped.setCharacter(characterDao.getCharacterByID(resultCharacterId));
				characterEquipped.setGearSlot(gearSlotDao.getGearSlotById(resultGearSlotId));
				characterEquipped.setGear(GearDao.getInstance().getGearByID(gearID));

				characterEquippedLst.add(characterEquipped);
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
		return characterEquippedLst;
	}
}
