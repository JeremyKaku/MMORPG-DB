package game.servlet;

import game.dal.PlayerDao;
import game.model.Player;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected PlayerDao playerDao;

	@Override
	public void init() throws ServletException {
		playerDao = PlayerDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		res.sendRedirect("Login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String email = req.getParameter("email");
		String playerPassword = req.getParameter("playerPassword");

		try {
			Player player = playerDao.getPlayerByEmailPassword(email, playerPassword);

			if (player != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loggedInPlayer", player);
				res.sendRedirect("playerdashboard");
			} else {
				String errorMessage = "Invalid username or password. Please try again.";
				res.sendRedirect("Login.jsp?error=true&message=" + URLEncoder.encode(errorMessage, "UTF-8"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
