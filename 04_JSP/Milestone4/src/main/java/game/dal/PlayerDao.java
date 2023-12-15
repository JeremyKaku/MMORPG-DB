package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import game.model.Player;

public class PlayerDao {
	protected ConnectionManager connectionManager;
	private static PlayerDao instance = null;

	protected PlayerDao() {
		connectionManager = new ConnectionManager();
	}

	public static PlayerDao getInstance() {
		if (instance == null) {
			instance = new PlayerDao();
		}
		return instance;
	}

	public Player create(Player player) throws SQLException {
		String insertPlayer = "INSERT INTO Player(player_name,email,player_password) " + "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, player.getPlayerName());
			insertStmt.setString(2, player.getEmail());
			insertStmt.setString(3, player.getPlayerPassword());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int playerID = -1;
			if (resultKey.next()) {
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

	public Player getPlayerByID(int playerID) throws SQLException {
		String selectPerson = "SELECT player_id,player_name,email,player_password FROM Player WHERE player_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, playerID);
			results = selectStmt.executeQuery();
			if (results.next()) {
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
	
	
	public Player getPlayerByEmailPassword(String email, String playerPassword) throws SQLException {
		String selectPerson = "SELECT player_id,player_name,email,player_password FROM Player WHERE email=? and player_password=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, email);
			selectStmt.setString(2, playerPassword);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultID = results.getInt("player_id");
				String resultPlayerName = results.getString("player_name");
				Player player = new Player(resultID, resultPlayerName, email, playerPassword);
				return player;
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

	public Player delete(Player player) throws SQLException {
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
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public List<Player> getAllPlayers() throws SQLException {
		String selectPlayers = "SELECT * FROM player;";
		List<Player> players = new ArrayList<>();

		try (Connection connection = connectionManager.getConnection();
				PreparedStatement selectStmt = connection.prepareStatement(selectPlayers);
				ResultSet results = selectStmt.executeQuery()) {

			while (results.next()) {
				int playerId = results.getInt("player_id");
				String playerName = results.getString("player_name");
				Player player = new Player();
				player.setPlayerID(playerId);
				player.setPlayerName(playerName);
				players.add(player);
			}
		}

		return players;
	}
	
	
    public Player update(Player player) throws SQLException {
        String updatePlayer = "UPDATE Player SET player_name=?, email=?, player_password=? WHERE player_id=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePlayer);
            updateStmt.setString(1, player.getPlayerName());
            updateStmt.setString(2, player.getEmail());
            updateStmt.setString(3, player.getPlayerPassword());
            updateStmt.setInt(4, player.getPlayerID());
            int affectedRows = updateStmt.executeUpdate();

            if (affectedRows > 0) {
                return player; // Successfully updated
            } else {
                throw new SQLException("Failed to update player details.");
            }
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
