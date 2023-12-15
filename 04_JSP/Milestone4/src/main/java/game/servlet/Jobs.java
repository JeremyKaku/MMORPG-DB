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

@WebServlet("/Jobs")
public class Jobs extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected JobDao jobDao;

    @Override
    public void init() throws ServletException {
        jobDao = JobDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Job> jobs = null;
        try {
            jobs = jobDao.getAllJobs();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("Jobs.jsp").forward(request, response);
    }
}
