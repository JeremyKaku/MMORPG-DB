package game.dal;

import game.model.CharacterCurrency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
