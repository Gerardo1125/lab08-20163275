package controller.access;
import model.entity.*;  
import java.util.*;
import java.io.IOException;  
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.jws.WebService;
import javax.servlet.*;  
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import javax.jdo.Query;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;  
@SuppressWarnings("serial")
public class AccessControllerDelete extends HttpServlet {  
	@SuppressWarnings("unchecked")
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
						Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
						Access c = pm.getObjectById(Access.class,k);
						pm.deletePersistent(c);
						pm.close();
						response.sendRedirect("/access");
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