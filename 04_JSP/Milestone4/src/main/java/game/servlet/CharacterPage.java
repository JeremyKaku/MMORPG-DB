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

import game.dal.CharacterDao;
import game.dal.WeaponDao;
import game.model.Character;
import game.model.Weapon;

@WebServlet("/character")
public class CharacterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CharacterDao characterDao;
	private WeaponDao weaponDao;

	@Override
	public void init() throws ServletException {
		characterDao = CharacterDao.getInstance();
		weaponDao = WeaponDao.getInstance();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		// Retrieve character ID from request parameters
		int characterID = Integer.parseInt(req.getParameter("characterID"));

		try {
			// Retrieve character's attributes based on character ID
			Character character = characterDao.getCharacterByID(characterID);
			Weapon weapon = weaponDao.getWeaponByID(character.getCharacterID());

			// Set the attributes attribute in the request
			req.setAttribute("character", character);
			req.setAttribute("weapon", weapon);

			// Forward to the AttributePage.jsp
			req.getRequestDispatcher("/CharacterPage.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
