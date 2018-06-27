package controller.resources;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Resource;
import model.entity.Role;
public class ResourcesControllerIndex extends HttpServlet {
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Resource.class);
		List<Resource> resources = todosLosTutoriales();
		request.setAttribute("viewResource", resources);
		request.getRequestDispatcher("/WEB-INF/Views/Resources/index.jsp").forward(request, response);
		
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
