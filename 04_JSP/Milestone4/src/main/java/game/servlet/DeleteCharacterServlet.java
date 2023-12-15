package game.servlet;

import game.model.Character;
import game.dal.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteCharacter")
public class DeleteCharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CharacterDao characterDao;

    @Override
    public void init() throws ServletException {
        characterDao = CharacterDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int characterID = Integer.parseInt(req.getParameter("characterID"));

        try {
            Character character = characterDao.getCharacterByID(characterID);

            if (character != null) {
                String characterName = character.getFirstName() + " " + character.getLastName();
                character = characterDao.delete(character);

                if (character == null) {
                    String successMessage = characterName + " is deleted.";
                    req.getSession().setAttribute("successMessage", successMessage);
                } else {
                    String errorMessage = "Failed to delete the character.";
                    req.getSession().setAttribute("errorMessage", errorMessage);
                }
            } else {
                String errorMessage = "Character not found.";
                req.getSession().setAttribute("errorMessage", errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while processing your request.";
            req.getSession().setAttribute("errorMessage", errorMessage);
        }

        resp.sendRedirect("playerdashboard");
    }
}