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

@WebServlet("/AllItems")
public class AllItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ItemDao itemDao;
	protected WeaponDao weaponDao;
	protected GearDao gearDao;
	protected MiscellaneousItemDao miscellaneousItemDao;
	protected ConsumableItemDao consumableItemDao;
	
	@Override
	public void init() throws ServletException {
		itemDao = ItemDao.getInstance();
		weaponDao = WeaponDao.getInstance();
		gearDao = GearDao.getInstance();
		miscellaneousItemDao = MiscellaneousItemDao.getInstance();
		consumableItemDao = ConsumableItemDao.getInstance();
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = req.getParameter("category");
        List<Item> items = new ArrayList<Item>();
        
        try {
        	if (category != null && !category.isEmpty()) {
                switch (category) {
                    case "Weapons":
                        items = weaponDao.getAllWeapons();
                        break;
                    case "Gears":
                        items = gearDao.getAllGears();
                        break;
                    case "Miscellaneous":
                        items = miscellaneousItemDao.getAllMiscellaneousItems();
                        break;
                    case "Consumables":
                        items = consumableItemDao.getAllConsumableItems();
                        break;
                    default:
                        items = itemDao.getAllItems();
                        break;
                }
            } else {
                items = itemDao.getAllItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("showitems", items);
        req.getRequestDispatcher("AllItems.jsp").forward(req, resp);
	}
}