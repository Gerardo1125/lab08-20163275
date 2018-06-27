package controller.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;

public class ResourcesControllerEdit  extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		com.google.appengine.api.users.User ugGoogle = UserServiceFactory.getUserService().getCurrentUser();
		String admin = "gerardo.22311@gmail.com";

		if(ugGoogle == null) {
			String error= "login";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
		}else {
			String query = "select from "+ User.class.getName() +
					" where email=='"+ugGoogle.getEmail()+"'" +
					" && status==true";
			List<User> uSearch = (List<User>)pm.newQuery(query).execute();
			if(uSearch.isEmpty()) {
				String error = "noExiste";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
			}else {
				String ad = "Administrador";
				Long idRR = Long.parseLong(uSearch.get(0).getRole());
				String query2 = "select from "+ Role.class.getName() +
						" where name=='"+ ad+"'" +
						" && status==true";
				List<Role> rSearch = (List<Role>)pm.newQuery(query2).execute();
				if(rSearch.isEmpty()) {
					String error= "adminP";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response); 
				}else {
					if(rSearch.get(0).getId().equals(idRR)) {
						String res =request.getParameter("action");
						if(res != null && request.getParameter("action").equals("edit")) {

							Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
							Resource s =  pm.getObjectById(Resource.class,k);
							
							s.setName(request.getParameter("name"));
							if(request.getParameter("status") != null) {
								s.setStatus(true);
							}else {
								s.setStatus(false);
							}
							response.sendRedirect("../resources");

						}else {
							Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
							Resource s =  pm.getObjectById(Resource.class,k);
							request.setAttribute("resource", s);

							request.getRequestDispatcher("/WEB-INF/Views/Resources/edit.jsp").forward(request, response);
						}
					}else {
						String error= "adminP";
						request.setAttribute("error", error);
						request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response); 
					}
				}
			}
		}

		
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


