package game.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.model.Weapon;


public class WeaponDao {
	protected ConnectionManager connectionManager;
	private static WeaponDao instance = null;
	protected WeaponDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static WeaponDao getInstance() {
		if(instance == null) {
			instance = new WeaponDao();
		}
		return instance;
	}
	
	public Weapon create(Weapon weapon) throws SQLException  {
		String insertWeapon =
				"INSERT INTO weapon(item_id,item_level,required_level,damage_done,auto_attack,attack_delay) " +
				"VALUES(?,?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertWeapon);
				insertStmt.setInt(1, weapon.getItemID());
				insertStmt.setInt(2, weapon.getItemLevel());
				insertStmt.setInt(3, weapon.getRequiredLevel());
				insertStmt.setInt(4, weapon.getDamageDone());
				insertStmt.setFloat(5, weapon.getAutoAttack());
				insertStmt.setFloat(6, weapon.getAttackDelay());
				insertStmt.executeUpdate();
				return weapon;
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
	

		public Weapon getWeaponByID(int itemID) throws SQLException {
			String selectWeapon =
					"SELECT w.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,w.item_level as item_level,w.required_level as required_level,w.damage_done as damage_done,w.auto_attack as auto_attack,w.attack_delay as attack_delay " +
					"FROM Weapon w INNER JOIN Item i " +
					"  ON w.item_id = i.item_id " +
					"WHERE i.item_id=?;";
				Connection connection = null;
				PreparedStatement selectStmt = null;
				ResultSet results = null;
				try {
					connection = connectionManager.getConnection();
					selectStmt = connection.prepareStatement(selectWeapon);
					selectStmt.setInt(1, itemID);
					results = selectStmt.executeQuery();
					if(results.next()) 
					{
						String resultItemName = results.getString("item_name");
						int resultMaxStackSize = results.getInt("max_stack_size");
						Double resultVendorPrize = results.getDouble("vendor_price");
						int resultItemLevel = results.getInt("item_level");
						int resultReqLevel = results.getInt("required_level");
						int resultDamageDone = results.getInt("vendor_price");
						float resultAutoAttack = results.getFloat("email");
						float resultAutoDelay = results.getFloat("email");
						Weapon reviewer = new Weapon(itemID,resultItemName,resultMaxStackSize,resultVendorPrize,resultItemLevel,resultReqLevel,resultDamageDone,resultAutoAttack,resultAutoDelay);
						return reviewer;
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
