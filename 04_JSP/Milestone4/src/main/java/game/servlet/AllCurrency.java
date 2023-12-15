package game.servlet;

import game.dal.CurrencyDao;
import game.model.Currency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllCurrency")
public class AllCurrency extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected CurrencyDao currencyDao;

    @Override
    public void init() throws ServletException {
        currencyDao = CurrencyDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Currency> currencies = null;
        try {
            currencies = currencyDao.getAllCurrencies();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("currencies", currencies);
        request.getRequestDispatcher("AllCurrency.jsp").forward(request, response);
    }
}
