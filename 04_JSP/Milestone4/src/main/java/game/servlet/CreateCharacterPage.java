package game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import game.dal.CharacterDao;
import game.model.Character;
import game.model.Player;
import game.model.Weapon;

@WebServlet("/create")
public class CreateCharacterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forward to the aboutme.jsp page
		req.getRequestDispatcher("CreateCharacterPage.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Map for storing messages.
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		// int playerID = Integer.parseInt(req.getParameter("playerID"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		Weapon weapon = new Weapon(27);
		HttpSession session = req.getSession();
		Player loggedInPlayer = (Player) session.getAttribute("loggedInPlayer");
		CharacterDao characterDao = CharacterDao.getInstance();
		boolean sameName;
		try {
			sameName = characterDao.verifyFirstNameLastName(firstName, lastName);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("createSuccess", "false");
			throw new IOException(e);
		}

		if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() ) {

			req.setAttribute("input", "false");
		} else if (sameName) {
			req.setAttribute("sameName", "true");
		} 
		else if(loggedInPlayer == null)
		{

			req.setAttribute("session", "false");
		}
		else {

			try {
				Player player = new Player(loggedInPlayer.getPlayerID());
				Character character = new Character(firstName, lastName, player, weapon);
				if (characterDao.verifyFirstNameLastName(firstName, lastName)) {
					req.setAttribute("createSuccess", "false");
				}
				character = characterDao.create(character);
				messages.put("success", "Successfully created " + firstName + lastName);
				req.setAttribute("createSuccess", "true");
				req.setAttribute("sameName", "false");
			} catch (SQLException e) {
				e.printStackTrace();
				req.setAttribute("createSuccess", "false");
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/CreateCharacterPage.jsp").forward(req, resp);
	}
}
