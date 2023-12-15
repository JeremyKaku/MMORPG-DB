package game.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import game.model.Item;
import game.model.Weapon;

public class WeaponDao extends ItemDao {
	protected ConnectionManager connectionManager;
	private static WeaponDao instance = null;

	protected WeaponDao() {
		connectionManager = new ConnectionManager();
	}

	public static WeaponDao getInstance() {
		if (instance == null) {
			instance = new WeaponDao();
		}
		return instance;
	}

	public Weapon create(Weapon weapon) throws SQLException {
		Item newItem = create(new Item(weapon.getItemName(), weapon.getMaxStackSize(), weapon.getVendorPrice()));

		String insertWeapon = "INSERT INTO weapon(item_id,item_level,required_level,damage_done,auto_attack,attack_delay) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		weapon.setItemID(newItem.getItemID());
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWeapon);
			insertStmt.setInt(1, weapon.getItemID());
			insertStmt.setInt(2, weapon.getItemLevel());
			insertStmt.setInt(3, weapon.getRequiredLevel());
			insertStmt.setInt(4, weapon.getDamageDone());
			insertStmt.setBigDecimal(5, weapon.getAutoAttack());
			insertStmt.setBigDecimal(6, weapon.getAttackDelay());
			insertStmt.executeUpdate();
			return weapon;
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

	public Weapon getWeaponByID(int itemID) throws SQLException {
		String selectWeapon = "SELECT w.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,w.item_level as item_level,w.required_level as required_level,w.damage_done as damage_done,w.auto_attack as auto_attack,w.attack_delay as attack_delay "
				+ "FROM Weapon w INNER JOIN Item i " + "  ON w.item_id = i.item_id " + "WHERE i.item_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeapon);
			selectStmt.setInt(1, itemID);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultItemName = results.getString("item_name");
				int resultMaxStackSize = results.getInt("max_stack_size");
				int resultVendorPrize = results.getInt("vendor_price");
				int resultItemLevel = results.getInt("item_level");
				int resultReqLevel = results.getInt("required_level");
				int resultDamageDone = results.getInt("damage_done");
				BigDecimal resultAutoAttack = results.getBigDecimal("auto_attack");
				BigDecimal resultAutoDelay = results.getBigDecimal("attack_delay");
				Weapon reviewer = new Weapon(itemID, resultItemName, resultMaxStackSize, resultVendorPrize,
						resultItemLevel, resultReqLevel, resultDamageDone, resultAutoAttack, resultAutoDelay);
				return reviewer;
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
	
	public List<Item> getAllWeapons() throws SQLException {
        List<Item> weapons = new ArrayList<>();
        String selectAllWeaponsQuery = "SELECT w.item_id,i.item_name as item_name,i.max_stack_size as max_stack_size,i.vendor_price as vendor_price,w.item_level as item_level,w.required_level as required_level,w.damage_done as damage_done,w.auto_attack as auto_attack,w.attack_delay as attack_delay "
				+ "FROM Weapon w INNER JOIN Item i " + "  ON w.item_id = i.item_id ";
 
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectAllWeaponsQuery);
             ResultSet results = selectStmt.executeQuery()) {
 
            while (results.next()) {
                int itemID = results.getInt("item_id");
                String itemName = results.getString("item_name");
                int maxStackSize = results.getInt("max_stack_size");
                int vendorPrice = results.getInt("vendor_price");
                int itemLevel = results.getInt("item_level");
                int requiredLevel = results.getInt("required_level");
                int damageDone = results.getInt("damage_done");
                BigDecimal autoAttack = results.getBigDecimal("auto_attack");
                BigDecimal attackDelay = results.getBigDecimal("attack_delay");
 
                Weapon weapon = new Weapon(itemID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel, damageDone, autoAttack, attackDelay);
                weapons.add(weapon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return weapons;
    }

}
