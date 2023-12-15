package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import game.model.Character;
import game.model.Player;
import game.model.Weapon;

public class CharacterDao {
	protected ConnectionManager connectionManager;
	private static CharacterDao instance = null;

	protected CharacterDao() {
		connectionManager = new ConnectionManager();
	}

	public static CharacterDao getInstance() {
		if (instance == null) {
			instance = new CharacterDao();
		}
		return instance;
	}

	public Character create(Character character) throws SQLException {
		String insertCharacter = "INSERT INTO game_character(first_name,last_name,player_id,weapon_id) "
				+ "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacter, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, character.getFirstName());
			insertStmt.setString(2, character.getLastName());
			insertStmt.setInt(3, character.getPlayer().getPlayerID());
			insertStmt.setInt(4, character.getWeapon().getItemID());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int characterID = -1;
			if (resultKey.next()) {
				characterID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			character.setCharacterID(characterID);
			return character;
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

	public Character getCharacterByID(int characterID) throws SQLException {
		String selectPerson = "SELECT character_id,first_name,last_name,player_id,weapon_id FROM game_character WHERE character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, characterID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultPlayerFirstName = results.getString("first_name");
				String resultPlayerLastName = results.getString("last_name");
				int resultPlayerID = results.getInt("player_id");
				int resultWeaponID = results.getInt("weapon_id");
				Player player = new Player(resultPlayerID);
				Weapon weapon = new Weapon(resultWeaponID);
				Character character = new Character(characterID, resultPlayerFirstName, resultPlayerLastName, player,
						weapon);
				return character;
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

	public Character getCharacterByPlayerID(int playerID) throws SQLException {
		String selectPerson = "SELECT character_id,first_name,last_name,player_id,weapon_id FROM game_character WHERE player_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, playerID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCharacterID = results.getInt("character_id");
				String resultPlayerFirstName = results.getString("first_name");
				String resultPlayerLastName = results.getString("last_name");
				int resultWeaponID = results.getInt("weapon_id");
				Player player = new Player(playerID);
				Weapon weapon = new Weapon(resultWeaponID);
				Character character = new Character(resultCharacterID, resultPlayerFirstName, resultPlayerLastName,
						player, weapon);
				return character;
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

	public Character delete(Character character) throws SQLException {
		String deleteCharacter = "DELETE FROM game_character WHERE character_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCharacter);
			deleteStmt.setInt(1, character.getCharacterID());
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

	public Character updateWeapon(Character character, Weapon newWeapon) throws SQLException {
		String updateWeapon = "UPDATE game_character SET weapon_id = ? WHERE character_id = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateWeapon);
			updateStmt.setInt(1, newWeapon.getItemID());
			updateStmt.setInt(2, character.getCharacterID());
			updateStmt.executeUpdate();

			character.setWeapon(newWeapon);
			return character;
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

	public List<Character> getAllCharactersByPlayer(Player player) throws SQLException {
		List<Character> characters = new ArrayList<>();
		String selectCharactersByPlayerID = "SELECT character_id, first_name, last_name, weapon_id FROM game_character WHERE player_id = ? ORDER BY first_name;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharactersByPlayerID);
			selectStmt.setInt(1, player.getPlayerID());
			results = selectStmt.executeQuery();

			WeaponDao weaponDao = WeaponDao.getInstance();

			while (results.next()) {
				int characterID = results.getInt("character_id");
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				int weaponID = results.getInt("weapon_id");

				Weapon weapon = weaponDao.getWeaponByID(weaponID);
				Character character = new Character(characterID, firstName, lastName, player, weapon);
				characters.add(character);
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

		return characters;
	}
	
	public boolean verifyFirstNameLastName(String firstName, String lastName) throws SQLException {
		String selectPerson = "SELECT character_id,first_name,last_name,player_id,weapon_id FROM game_character WHERE first_name=? And last_name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, firstName);
			selectStmt.setString(2, lastName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				return true;			}
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
		return false;
	}

}
