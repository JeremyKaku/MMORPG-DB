package game.servlet;

import game.dal.*;
import game.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/character/characterAttributes")
public class CharacterAttributePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected CharacterAttributeDao charAttributeDao;

    @Override
    public void init() throws ServletException {
        charAttributeDao = CharacterAttributeDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        int characterID = Integer.parseInt(req.getParameter("characterID"));

        try {
            List<CharacterAttribute> attributes = charAttributeDao.getAllCharacterAttributes(characterID);

            req.setAttribute("attributes", attributes);
            req.getRequestDispatcher("/CharacterAttributePage.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}


