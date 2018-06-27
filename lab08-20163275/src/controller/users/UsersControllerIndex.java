package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.User;

public class UsersControllerIndex extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(User.class);
			List<User> users = todosLosTutoriales();
			request.setAttribute("showAllUsers", users);
			request.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp").forward(request, response);
		
	}
	public static List<User> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(User.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<User>) query.execute();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
