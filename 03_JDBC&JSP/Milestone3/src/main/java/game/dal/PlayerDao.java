package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import game.model.Player;


public class PlayerDao {
	protected ConnectionManager connectionManager;
	private static PlayerDao instance = null;
	protected PlayerDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PlayerDao getInstance() {
		if(instance == null) {
			instance = new PlayerDao();
		}
		return instance;
	}
	
	public Player create(Player player) throws SQLException  {
		String insertPlayer =
				"INSERT INTO Player(player_id,player_name,email,player_password) " +
				"VALUES(?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertPlayer,Statement.RETURN_GENERATED_KEYS);
				insertStmt.setInt(1, player.getPlayerID());
				insertStmt.setString(2, player.getPlayerName());
				insertStmt.setString(3, player.getEmail());
				insertStmt.setString(4, player.getPlayerPassword());
				insertStmt.executeUpdate();
			
				resultKey = insertStmt.getGeneratedKeys();
				int playerID = -1;
				if(resultKey.next()) {
					playerID = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				player.setPlayerID(playerID);
				return player;
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
	
	
	public Player getPlayerByID(int playerID) throws SQLException   {
		String selectPerson = "SELECT player_id,player_name,email,player_password FROM Player WHERE player_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, playerID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultPlayerName = results.getString("player_name");
				String resultEmail = results.getString("email");
				String resultPlayerPassword = results.getString("player_password");
				Player player = new Player(playerID, resultPlayerName, resultEmail, resultPlayerPassword);
				return player;
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
	
	
	public Player delete(Player player) throws SQLException  {
		String deletePlayer = "DELETE FROM Player WHERE player_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlayer);
			deleteStmt.setInt(1, player.getPlayerID());
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
