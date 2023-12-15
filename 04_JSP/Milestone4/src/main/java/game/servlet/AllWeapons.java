package game.servlet;

import game.dal.*;
import game.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllWeapons")
public class AllWeapons extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected WeaponDao weaponDao;

    @Override
    public void init() throws ServletException {
        weaponDao = WeaponDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 List<Item> weapons = new ArrayList<>();
        
        try {
            weapons = weaponDao.getAllWeapons();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("weapons", weapons);
        request.getRequestDispatcher("AllWeapons.jsp").forward(request, response);
    }
}
