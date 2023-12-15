package game.servlet;

import game.dal.CharacterDao;
import game.dal.PlayerDao;
import game.model.Character;
import game.model.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/playerdashboard")
public class PlayerDashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CharacterDao characterDao;

	@Override
	public void init() throws ServletException {
		characterDao = CharacterDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Player loggedInPlayer = (Player) session.getAttribute("loggedInPlayer");

		if (loggedInPlayer == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		try {
			List<Character> characters = characterDao.getAllCharactersByPlayer(loggedInPlayer);

			request.setAttribute("characters", characters);
			request.setAttribute("loggedInPlayer", loggedInPlayer);
			request.getRequestDispatcher("PlayerDashboard.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
}
