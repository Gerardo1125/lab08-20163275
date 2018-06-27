package controller.access;
import model.entity.Access;
import java.util.*;
import java.io.IOException;  
import java.io.PrintWriter;
import com.google.appengine.api.datastore.Key;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jws.WebService;
import javax.servlet.*;  
import javax.servlet.http.*;

import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;  
@SuppressWarnings("serial")
public class AccessControllerView extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String res =request.getParameter("ID");
		if(res == null) {
			Query query = pm.newQuery(Access.class);
			List<Access> access = todosLosTutoriales();
			request.setAttribute("access", access);
			request.getRequestDispatcher("../WEB-INF/Views/Access2/view.jsp").forward(request, response);
		}else {
			Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
			Access c = pm.getObjectById(Access.class,k);
			request.setAttribute("access", c);

			request.getRequestDispatcher("../WEB-INF/Views/Access2/view.jsp").forward(request, response);
		}

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	} 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	public static List<Access> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Access.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Access>) query.execute();
	}

}
