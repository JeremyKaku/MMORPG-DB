package game.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.dal.PlayerDao;
import game.model.Player;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected PlayerDao playerDao;

	@Override
	public void init() throws ServletException {
		playerDao = PlayerDao.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String playerName = request.getParameter("playerName");
		String email = request.getParameter("email");
		String playerPassword = request.getParameter("playerPassword");

		try {
			Player newPlayer = new Player(playerName, email, playerPassword);
			newPlayer = playerDao.create(newPlayer);
			response.sendRedirect("Login.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}
}
