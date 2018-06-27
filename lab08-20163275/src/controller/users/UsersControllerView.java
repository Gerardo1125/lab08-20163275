package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.User;

public class UsersControllerView extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String res =request.getParameter("action");
		
		Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
		User s =  pm.getObjectById(User.class,k);
			
					
			request.setAttribute("roles", s);
			
			request.getRequestDispatcher("../WEB-INF/Views/Users/view.jsp").forward(request, response);
		
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
