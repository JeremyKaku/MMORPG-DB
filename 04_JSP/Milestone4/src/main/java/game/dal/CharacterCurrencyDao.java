package game.dal;

import game.model.CharacterCurrency;
import game.model.Character;
import game.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CharacterCurrencyDao {
	protected ConnectionManager connectionManager;

	private static CharacterCurrencyDao instance = null;

	public static CharacterCurrencyDao getInstance() {
		if (instance == null) {
			instance = new CharacterCurrencyDao();
		}
		return instance;
	}

	protected CharacterCurrencyDao() {
		connectionManager = new ConnectionManager();
	}

	public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException {
		String insertCharacterCurrency = "INSERT INTO character_currency (character_id, currency_id, weekly_amount, total_amount) VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCharacterCurrency);
			insertStmt.setInt(1, characterCurrency.getCharacter().getCharacterID());
			insertStmt.setInt(2, characterCurrency.getCurrency().getCurrencyId());
			insertStmt.setInt(3, characterCurrency.getWeeklyAmount());
			insertStmt.setInt(4, characterCurrency.getTotalAmount());
			insertStmt.executeUpdate();

			return characterCurrency;

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
		}
	}

	public CharacterCurrency getCharacterCurrencyByIds(int characterId, int currencyId) throws SQLException {
		String selectCharacterCurrency = "SELECT character_id, currency_id, weekly_amount, total_amount FROM character_currency WHERE character_id = ? AND currency_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CharacterDao characterDao = CharacterDao.getInstance();
		CurrencyDao currencyDao = CurrencyDao.getInstance();

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCharacterCurrency);
			selectStmt.setInt(1, characterId);
			selectStmt.setInt(2, currencyId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCurrencyID = results.getInt("currency_id");
				int resultCharacterID = results.getInt("character_id");
				int weeklyAmount = results.getInt("weekly_amount");
				int totalAmount = results.getInt("total_amount");

				CharacterCurrency characterCurrency = new CharacterCurrency();
				characterCurrency.setCharacter(characterDao.getCharacterByID(resultCharacterID));
				characterCurrency.setCurrency(currencyDao.getCurrencyById(resultCurrencyID));
				characterCurrency.setWeeklyAmount(weeklyAmount);
				characterCurrency.setTotalAmount(totalAmount);

				return characterCurrency;
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
	
    public Currency getCurrencyByCharacterID(int characterID) throws SQLException {
        String selectCurrency = "SELECT c.currency_id, c.currency_name, c.maximum_amount, c.weekly_cap, c.availiability, cc.weekly_amount, cc.total_amount " +
                "FROM currency c JOIN character_currency cc ON c.currency_id = cc.currency_id WHERE character_id=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCurrency);
            selectStmt.setInt(1, characterID);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultCurrencyID = results.getInt("currency_id");
                String currencyName = results.getString("currency_name");
                int maximumAmount = results.getInt("maximum_amount");
                int weeklyCap = results.getInt("weekly_cap");
                boolean availability = results.getBoolean("availiability");

                Currency curr = new Currency(resultCurrencyID, currencyName, maximumAmount, weeklyCap, availability);
                return curr;
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

        return null; // Return null if currency with the provided ID is not found
    }
	
	
    public List<CharacterCurrency> getAllCharacterCurrencies(int characterID) throws SQLException {
        List<CharacterCurrency> characterCurrencies = new ArrayList<>();
        String selectAllCharacterCurrencies = "SELECT character_id, currency_id, weekly_amount, total_amount " +
                "FROM character_currency WHERE character_id=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAllCharacterCurrencies);
            selectStmt.setInt(1, characterID);
            results = selectStmt.executeQuery();
            CharacterDao characterDao = CharacterDao.getInstance();
            CurrencyDao currencyDao = CurrencyDao.getInstance();

            while (results.next()) {
                int resultCharacterID = results.getInt("character_id");
                int resultCurrencyID = results.getInt("currency_id");
                int weeklyAmount = results.getInt("weekly_amount");
                int totalAmount = results.getInt("total_amount");

                Character character = characterDao.getCharacterByID(resultCharacterID);
                Currency currency = currencyDao.getCurrencyById(resultCurrencyID);

                CharacterCurrency characterCurrency = new CharacterCurrency(character, currency, weeklyAmount, totalAmount);
                characterCurrencies.add(characterCurrency);
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

        return characterCurrencies;
    }
}
