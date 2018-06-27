package controller.resources;
import model.entity.Resource;
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
public class ResourcesControllerView extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String res =request.getParameter("id");
		if(res == null) {
			Query query = pm.newQuery(Resource.class);
			List<Resource> cursos = todosLosTutoriales();
			request.setAttribute("viewResource", cursos);
			request.getRequestDispatcher("../WEB-INF/Views/Resources/view.jsp").forward(request, response);
		}else {
			Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("id")).longValue());
			Resource c = pm.getObjectById(Resource.class,k);
			request.setAttribute("resource", c);

			request.getRequestDispatcher("../WEB-INF/Views/Resources/view.jsp").forward(request, response);
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
	public static List<Resource> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Resource.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Resource>) query.execute();
	}

}
