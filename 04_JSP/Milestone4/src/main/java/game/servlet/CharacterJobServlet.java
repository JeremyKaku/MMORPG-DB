package game.servlet;

import game.dal.CharacterJobDao;
import game.model.CharacterJob;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/character/characterJob")
public class CharacterJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CharacterJobDao characterJobDao;

    @Override
    public void init() throws ServletException {
        characterJobDao = CharacterJobDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Retrieve character ID from request parameters
        int characterID = Integer.parseInt(req.getParameter("characterID"));

        try {
            // Retrieve character's jobs based on character ID
            List<CharacterJob> characterJobs = characterJobDao.getCharacterJobByCharacterID(characterID);

			DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
			List<Map<String, Object>> dataList = new ArrayList<>();
			for (CharacterJob item : characterJobs) {
				
			
				Map<String, Object> data = new HashMap<>();
				data.put("jobName", item.getJob().getJobName());
				data.put("jobLevel", item.getJobLevel());
				data.put("experiencePoint", decimalFormat.format(item.getExperiencePoint()));
				data.put("currentJob", item.isCurrentJob());
				dataList.add(data);
			}

            // Set the jobs attribute in the request
            req.setAttribute("characterJobs", characterJobs);
            req.setAttribute("dataList", dataList);

            // Forward to the CharacterJobPage.jsp
            req.getRequestDispatcher("/CharacterJobPage.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}
