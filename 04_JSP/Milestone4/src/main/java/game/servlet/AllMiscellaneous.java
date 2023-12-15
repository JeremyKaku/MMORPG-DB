package game.servlet;

import game.dal.*;
import game.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllMiscellaneous")
public class AllMiscellaneous extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected MiscellaneousItemDao miscellaneousItemDao;

    @Override
    public void init() throws ServletException {
        miscellaneousItemDao = MiscellaneousItemDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Item> miscellaneousItems = new ArrayList<>();
        try {
            miscellaneousItems = miscellaneousItemDao.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("miscellaneousItems", miscellaneousItems);
        request.getRequestDispatcher("/AllMiscellaneous.jsp").forward(request, response);
    }
}
