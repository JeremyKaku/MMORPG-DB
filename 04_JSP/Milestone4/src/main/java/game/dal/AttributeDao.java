package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import game.model.Attribute;

public class AttributeDao {
	protected ConnectionManager connectionManager;
	private static AttributeDao instance = null;

	protected AttributeDao() {
		connectionManager = new ConnectionManager();
	}

	public static AttributeDao getInstance() {
		if (instance == null) {
			instance = new AttributeDao();
		}
		return instance;
	}

	public Attribute create(Attribute attribute) throws SQLException {
		String insertAttribute = "INSERT INTO Attribute(attribute_name) " + "VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAttribute, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, attribute.getAttributesName());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int attributeID = -1;
			if (resultKey.next()) {
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

	public Attribute getAttributeByID(int attributeID) throws SQLException {
		String selectAttribute = "SELECT attribute_id,attribute_name FROM Attribute WHERE attribute_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttribute);
			selectStmt.setInt(1, attributeID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultAttributeName = results.getString("attribute_name");
				Attribute attribute = new Attribute(attributeID, resultAttributeName);
				return attribute;
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

	public Attribute updateAttributeName(Attribute attribute, String newName) throws SQLException {
		String updateAttributeName = "UPDATE Attribute SET attribute_name = ? WHERE attribute_id = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAttributeName);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, attribute.getAttributeID());
			updateStmt.executeUpdate();
			attribute.setAttributesName(newName);
			return attribute;
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

	public Attribute deleteAttribute(Attribute attribute) throws SQLException {
		String deleteAttribute = "DELETE FROM Attribute WHERE attribute_id = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAttribute);
			deleteStmt.setInt(1, attribute.getAttributeID());
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

	public List<Attribute> getAttributesByName(String attributeName) throws SQLException {
		List<Attribute> attributes = new ArrayList<>();
		String selectAttributesByName = "SELECT attribute_id, attribute_name FROM Attribute WHERE attribute_name LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttributesByName);
			selectStmt.setString(1, "%" + attributeName + "%");
			results = selectStmt.executeQuery();

			while (results.next()) {
				int attributeID = results.getInt("attribute_id");
				String name = results.getString("attribute_name");
				Attribute attribute = new Attribute(attributeID, name);
				attributes.add(attribute);
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
	
	public List<Attribute> getAllAttributes() throws SQLException {
        List<Attribute> attributes = new ArrayList<>();
        String selectAllAttributesQuery = "SELECT * FROM Attribute";
 
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectAllAttributesQuery);
             ResultSet results = selectStmt.executeQuery()) {
 
            while (results.next()) {
                int attributeID = results.getInt("attribute_id");
                String attributeName = results.getString("attribute_name");
 
                Attribute attribute = new Attribute(attributeID, attributeName);
                attributes.add(attribute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return attributes;
    }

}
