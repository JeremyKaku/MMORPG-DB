package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import game.model.Attribute;


public class AttributeDao {
	protected ConnectionManager connectionManager;
	private static AttributeDao instance = null;
	protected AttributeDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static AttributeDao getInstance() {
		if(instance == null) {
			instance = new AttributeDao();
		}
		return instance;
	}
	
	public Attribute create(Attribute attribute) throws SQLException  {
		String insertAttribute =
				"INSERT INTO Attribute(attribute_id,attribute_name) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertAttribute,Statement.RETURN_GENERATED_KEYS);
				insertStmt.setInt(1, attribute.getAttributeID());
				insertStmt.setString(2, attribute.getAttributesName());
				insertStmt.executeUpdate();
			
				resultKey = insertStmt.getGeneratedKeys();
				int attributeID = -1;
				if(resultKey.next()) {
					attributeID = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				attribute.setAttributeID(attributeID);
				return attribute;
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
	
	
	public Attribute getAttributeByID(int attributeID) throws SQLException   {
		String selectAttribute = "SELECT attribute_id,attribute_name FROM Attribute WHERE attribute_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttribute);
			selectStmt.setInt(1, attributeID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultAttributeName = results.getString("attribute_name");
				Attribute attribute = new Attribute(attributeID, resultAttributeName);
				return attribute;
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
}
