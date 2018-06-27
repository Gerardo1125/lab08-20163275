package controller.roles;

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
import model.entity.Role;

public class RolesControllerView extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String res =request.getParameter("action");
		
		Key k = KeyFactory.createKey(Role.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
		Role s =  pm.getObjectById(Role.class,k);
			
					
			request.setAttribute("roles", s);
			
			request.getRequestDispatcher("../WEB-INF/Views/Roles/view.jsp").forward(request, response);
		
	}
	public static List<Role> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Role.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Role>) query.execute();
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
