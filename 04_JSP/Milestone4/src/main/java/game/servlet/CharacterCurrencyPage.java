package game.servlet;

import game.dal.CharacterCurrencyDao;
import game.model.CharacterCurrency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

@WebServlet("/character/characterCurrency")
public class CharacterCurrencyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CharacterCurrencyDao characterCurrencyDao;

	@Override
	public void init() throws ServletException {
		characterCurrencyDao = CharacterCurrencyDao.getInstance();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		// Retrieve character ID from request parameters
		int characterID = Integer.parseInt(req.getParameter("characterID"));

		try {
			// Retrieve character's currency based on character ID
			List<CharacterCurrency> currencies = characterCurrencyDao.getAllCharacterCurrencies(characterID);

			DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
			List<Map<String, Object>> dataList = new ArrayList<>();
			for (CharacterCurrency item : currencies) {
				String weekTotal = "";
				if (item.getCurrency().getWeeklyCap() == 0 && item.getWeeklyAmount() == 0) {
					weekTotal = "-";
				} else {
					weekTotal = decimalFormat.format(item.getWeeklyAmount()) + " / "
							+ decimalFormat.format(item.getCurrency().getWeeklyCap());
				}

				Map<String, Object> data = new HashMap<>();
				data.put("currencyName", item.getCurrency().getCurrencyName());
				data.put("weekTotal", weekTotal);
				data.put("totalAmount", decimalFormat.format(item.getTotalAmount()));
				dataList.add(data);
			}

			// Set the currencies attribute in the request
			req.setAttribute("currencies", currencies);
			req.setAttribute("dataList", dataList);

			// Forward to the CurrencyPage.jsp
			req.getRequestDispatcher("/CharacterCurrencyPage.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
