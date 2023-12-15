package game.dal;

import game.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CurrencyDao {
	protected ConnectionManager connectionManager;

	private static CurrencyDao instance = null;

	public static CurrencyDao getInstance() {
		if (instance == null) {
			instance = new CurrencyDao();
		}
		return instance;
	}

	protected CurrencyDao() {
		connectionManager = new ConnectionManager();
	}

	public Currency create(Currency currency) throws SQLException {
		String insertCurrency = "INSERT INTO currency (currency_name, maximum_amount, weekly_cap, availiability) VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCurrency, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, currency.getCurrencyName());
			insertStmt.setInt(2, currency.getMaximumAmount());
			insertStmt.setInt(3, currency.getWeeklyCap());
			insertStmt.setBoolean(4, currency.isAvailability());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int currencyId = -1;
			if (resultKey.next()) {
				currencyId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			currency.setCurrencyId(currencyId);

			return currency;
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

	public Currency getCurrencyById(int currencyId) throws SQLException {
		String selectCurrency = "SELECT currency_id, currency_name, maximum_amount, weekly_cap, availiability FROM currency WHERE currency_id = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCurrency);
			selectStmt.setInt(1, currencyId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCurrencyID = results.getInt("currency_id");
				String currencyName = results.getString("currency_name");
				int maximumAmount = results.getInt("maximum_amount");
				int weeklyCap = results.getInt("weekly_cap");
				boolean availability = results.getBoolean("availiability");

				Currency currency = new Currency();
				currency.setCurrencyId(resultCurrencyID);
				currency.setCurrencyName(currencyName);
				currency.setMaximumAmount(maximumAmount);
				currency.setWeeklyCap(weeklyCap);
				currency.setAvailability(availability);

				return currency;
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
	
	public List<Currency> getAllCurrencies() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        String selectAllCurrenciesQuery = "SELECT * FROM currency";
 
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectAllCurrenciesQuery);
             ResultSet results = selectStmt.executeQuery()) {
 
            while (results.next()) {
                int currencyId = results.getInt("currency_id");
                String currencyName = results.getString("currency_name");
                int maximumAmount = results.getInt("maximum_amount");
                int weeklyCap = results.getInt("weekly_cap");
                boolean availability = results.getBoolean("availiability");
 
                Currency currency = new Currency(currencyId, currencyName, maximumAmount, weeklyCap, availability);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return currencies;
    }
}
