package game.servlet;

import game.dal.*;
import game.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attributes")
public class Attributes extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected AttributeDao attributeDao;

    @Override
    public void init() throws ServletException {
        attributeDao = AttributeDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Attribute> attributes = null;
        try {
            attributes = attributeDao.getAllAttributes();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("attributes", attributes);
        request.getRequestDispatcher("Attributes.jsp").forward(request, response);
    }
}
