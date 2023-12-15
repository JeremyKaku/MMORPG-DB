package game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import game.dal.InventoryDao;
import game.model.Inventory;

@WebServlet("/character/inventory")
public class InventoryPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InventoryDao inventoryDao;

	@Override
	public void init() throws ServletException {
		inventoryDao = InventoryDao.getInstance();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		// Retrieve character ID from request parameters
		int characterID = Integer.parseInt(req.getParameter("characterID"));

		try {
			// Retrieve character's attributes based on character ID
			List<Inventory> inventory = inventoryDao.getInventoryByCharacterID(characterID);

			// Set the attributes attribute in the request
			req.setAttribute("inventory", inventory);
			req.setAttribute("total", inventory.size());
			// Forward to the AttributePage.jsp
			req.getRequestDispatcher("/InventoryPage.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
