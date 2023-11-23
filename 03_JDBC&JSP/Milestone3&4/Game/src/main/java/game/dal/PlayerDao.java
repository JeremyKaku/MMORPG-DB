package game.dal;

import game.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data access object (DAO) class to interact with the underlying User table in
 * your MySQL instance. This is used to store {@link User} into your MySQL
 * instance and retrieve {@link User} from MySQL instance.
 */
public class PlayerDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
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

	/**
	 * Save the User instance by storing it in your MySQL instance. This runs a
	 * INSERT statement.
	 */
	public Player create(Player player) throws SQLException {
		String insertPlayer = "INSERT INTO player(player_name,email,player_password) VALUES(?,?,?);";
		ResultSet resultKey = null;
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement insertStmt = connection.prepareStatement(insertPlayer,
						Statement.RETURN_GENERATED_KEYS);) {

			insertStmt.setString(1, player.getPlayerName());
			insertStmt.setString(2, player.getEmail());
			insertStmt.setString(3, player.getPlayerPassword());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int userID = -1;
			if (resultKey.next()) {
				userID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			player.setPlayerId(userID);

			return player;
		}
	}
//
//	/**
//	 * Get the User record by fetching it from your MySQL instance. This runs a
//	 * SELECT statement and returns a single User instance.
//	 */
//	public User getUserByUserID(int userID) throws SQLException {
//		String selectUser = "SELECT username,firstName,lastName,email FROM User WHERE userID=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectUser);
//			selectStmt.setInt(1, userID);
//			// Note that we call executeQuery(). This is used for a SELECT statement
//			// because it returns a result set. For more information, see:
//			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
//			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
//			results = selectStmt.executeQuery();
//			// You can iterate the result set (although the example below only retrieves
//			// the first record). The cursor is initially positioned before the row.
//			// Furthermore, you can retrieve fields by id and by type.
//			if (results.next()) {
//				String userName = results.getString("username");
//				String firstName = results.getString("firstName");
//				String lastName = results.getString("lastName");
//				String email = results.getString("email");
//				User user = new User(userName, firstName, lastName, email);
//				return user;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
//			if (selectStmt != null) {
//				selectStmt.close();
//			}
//			if (results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * Delete the User instance. This runs a DELETE statement.
//	 */
//	public User delete(User user) throws SQLException {
//		String deleteUser = "DELETE FROM User WHERE userID=?;";
//		Connection connection = null;
//		PreparedStatement deleteStmt = null;
//		try {
//			connection = connectionManager.getConnection();
//			deleteStmt = connection.prepareStatement(deleteUser);
//			deleteStmt.setInt(1, user.getUserID());
//			deleteStmt.executeUpdate();
//
//			// Return null so the caller can no longer operate on the User instance.
//			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
//			if (deleteStmt != null) {
//				deleteStmt.close();
//			}
//		}
//	}

}
