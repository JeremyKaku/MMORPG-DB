package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.Quality;

public class QualityDao {
	protected ConnectionManager connectionManager;
	private static QualityDao instance = null;
	protected QualityDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static QualityDao getInstance() {
		if(instance == null) {
			instance = new QualityDao();
		}
		return instance;
	}
	
	public Quality create(Quality quality) throws SQLException  {
		String insertCharacter =
				"INSERT INTO Quality(item_quality) " +
				"VALUES(?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertCharacter);
				insertStmt.setString(1, quality.getItemQuality());
				insertStmt.executeUpdate();
				return quality;
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
	
	
    public Quality getQualityByName(String itemQuality) throws SQLException {
        String selectQuality = "SELECT item_quality FROM quality WHERE item_quality=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuality);
            selectStmt.setString(1, itemQuality);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultItemQuality = results.getString("item_quality");
                Quality quality = new Quality(resultItemQuality);
                return quality;
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
