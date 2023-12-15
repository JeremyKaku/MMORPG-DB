package game.servlet;

import game.dal.*;
import game.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllGears")
public class AllGears extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
	protected GearDao gearDao;
    
    @Override
    public void init() throws ServletException {
        gearDao = GearDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Item> gears = new ArrayList<>();
        
        try {
            gears = gearDao.getAllGears(); 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("showgears", gears);
        req.getRequestDispatcher("AllGears.jsp").forward(req, resp);
    }
}