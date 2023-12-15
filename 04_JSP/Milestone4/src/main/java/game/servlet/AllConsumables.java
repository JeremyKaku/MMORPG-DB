package game.servlet;

import game.dal.*;
import game.model.*;
import java.util.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllConsumables")
public class AllConsumables extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected ConsumableItemDao consumableItemDao;

    @Override
    public void init() throws ServletException {
        consumableItemDao = ConsumableItemDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Item> consumables = new ArrayList<>();
        try {
            consumables = consumableItemDao.getAllConsumableItems();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("consumables", consumables);
        request.getRequestDispatcher("/AllConsumables.jsp").forward(request, response);
    }
}
