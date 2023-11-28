package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		if(instance == null) {
			instance = new CharacterDao();
		}
		return instance;
	}
	
	public Character create(Character character) throws SQLException  {
		String insertCharacter =
				"INSERT INTO game_character(character_id,first_name,last_name,player_id,weapon_id) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertCharacter,Statement.RETURN_GENERATED_KEYS);
				insertStmt.setInt(1, character.getCharacterID());
				insertStmt.setString(2, character.getFirstName());
				insertStmt.setString(3, character.getLastName());
				insertStmt.setInt(4, character.getPlayer().getPlayerID());
				insertStmt.setInt(5, character.getWeapon().getItemID());
				insertStmt.executeUpdate();
			
				resultKey = insertStmt.getGeneratedKeys();
				int characterID = -1;
				if(resultKey.next()) {
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
				if(connection != null) {
					connection.close();
				}
				if(insertStmt != null) {
					insertStmt.close();
				}
				if(resultKey != null) {
					resultKey.close();
				}
			}
		
	}
	
	
	public Character getCharacterByID(int characterID) throws SQLException   {
		String selectPerson = "SELECT character_id,first_name,last_name,player_id,weapon_id FROM Character WHERE character_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, characterID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultPlayerFirstName = results.getString("first_name");
				String resultPlayerLastName = results.getString("last_name");
				int resultPlayerID = results.getInt("player_id");
				int resultWeaponID = results.getInt("weapon_id");
				Player player = new Player(resultPlayerID);
				Weapon weapon = new Weapon(resultWeaponID);
				Character character = new Character(characterID, resultPlayerFirstName, resultPlayerLastName, player, weapon);
				return character;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	public Character delete(Character character) throws SQLException  {
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
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
