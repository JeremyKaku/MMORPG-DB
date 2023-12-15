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

@WebServlet("/aboutme")
public class AboutMeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the aboutme.jsp page
        request.getRequestDispatcher("AboutMe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String playerName = request.getParameter("playerName");
        String email = request.getParameter("email");
        String password = request.getParameter("playerPassword");

        Player loggedInPlayer = (Player) request.getSession().getAttribute("loggedInPlayer");
        loggedInPlayer.setPlayerName(playerName);
        loggedInPlayer.setEmail(email);
        loggedInPlayer.setPlayerPassword(password);

        PlayerDao playerDao = PlayerDao.getInstance();

        try {
            Player updatedPlayer = playerDao.update(loggedInPlayer);
            request.getSession().setAttribute("loggedInPlayer", updatedPlayer);
            request.setAttribute("updateSuccess", "true");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("updateSuccess", "false");
        }

        request.getRequestDispatcher("AboutMe.jsp").forward(request, response);
    }
}

