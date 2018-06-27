package controller.access;

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
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Role;
import model.entity.User;
import model.entity.Access;
import model.entity.Resource;

public class AccessControllerEdit extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
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
							List<Role> roles = todosLosTutoriales2();
							List<Resource> resources = todosLosTutoriales3();
							
							Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
							Access s =  pm.getObjectById(Access.class,k);

							Role r = null;
							Resource reso = null;
							for (int i =0; i < roles.size();i++) {
								r = (Role)roles.get(i);
								if(r.getName().equals(request.getParameter("role"))) {
									break;
								}
							}
							for(int i = 0; i< resources.size(); i++) {
								reso = (Resource)resources.get(i);
								if(reso.getName().equals(request.getParameter("resource"))) {
									break;
								}
							}
							
							String rol = Long.toString(r.getId());
							String resou = Long.toString(reso.getId());
							s.setName(rol);
							s.setNameR(resou);
							boolean activo;
							 if(request.getParameter("status")!= null) {
								 activo = true;
							 }else {
								 activo = false;
							 }
							s.setStatus(activo);
							
							if(request.getParameter("status") != null) {
								s.setStatus(true);
							}else {
								s.setStatus(false);
							}

							response.sendRedirect("../access");

						}else {
							Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
							Access s =  pm.getObjectById(Access.class,k);
							List<Role> roles = todosLosTutoriales2();
							Role role = null;
							Resource resou = null;
							for(int i = 0; i < roles.size(); i++) {
								role = (Role)roles.get(i);
								String r = Long.toString(role.getId());
								if(r.equals(s.getName())) {
									break;
								}
							}
							List<Resource> resources = todosLosTutoriales3();
							for(int i = 0; i < resources.size(); i++) {
								resou = (Resource)resources.get(i);
								String r = Long.toString(resou.getId());
								if(r.equals(s.getNameR())) {
									break;
								}
							}
							request.setAttribute("student", s);
							request.setAttribute("role", role);
							request.setAttribute("resource", resou);
							request.setAttribute("showAllResources", resources);
							request.setAttribute("showAllRoles", roles);
							request.getRequestDispatcher("/WEB-INF/Views/Access2/edit.jsp").forward(request, response);
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
	public static List<Role> todosLosTutoriales2(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Role.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Role>) query.execute();
	}
	public static List<Resource> todosLosTutoriales3(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Resource.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Resource>) query.execute();
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
