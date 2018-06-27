package controller.roles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Role;
import model.entity.User;

public class RolesControllerAdd extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		com.google.appengine.api.users.User ugGoogle = UserServiceFactory.getUserService().getCurrentUser();
		String admin = "gerardo.22311@gmail.com";
		if(ugGoogle == null) {
			String error= "login";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
		}else {
			String query1 = "select from "+ User.class.getName() +
					" where email=='"+ugGoogle.getEmail()+"'" +
					" && status==true";
			List<User> uSearch = (List<User>)pm.newQuery(query1).execute();
			if(uSearch.isEmpty() && !ugGoogle.getEmail().equals(admin)) {
				String error = "noExiste";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
			}else if(ugGoogle.getEmail().equals(admin)){
				String res = request.getParameter("action");
				boolean igual= false; 
				if(res != null ) {
					List<Role> students = todosLosTutoriales();
					String condicion = "";
					for(int i = 0; i<students.size();i++) {
						Role s = (Role) students.get(i);
						if(s.getName().equalsIgnoreCase(request.getParameter("name"))) {
							condicion= "name";
							igual = true;
							break;
						}
					}
					if(!igual) {
						boolean activo;
						if(request.getParameter("status")!= null) {
							activo = true;
						}else {
							activo = false;
						}
						Role a = new Role(
								request.getParameter("name"),
								activo
								);

						try {
							pm.makePersistent(a);
						} finally {
							pm.close();
						}
						response.sendRedirect("../roles");
					}else {
						request.setAttribute("condicion", condicion);
						request.getRequestDispatcher("../WEB-INF/Views/Roles/view.jsp").forward(request, response);
					}
				}else {
					request.getRequestDispatcher("../WEB-INF/Views/Roles/add.jsp").forward(request, response);
				}
			}else {
				String ad = "Administrador";
				Long idRR = Long.parseLong(uSearch.get(0).getRole());
				System.out.println(idRR);
				String query2 = "select from "+ Role.class.getName() +
						" where name=='"+ad+"'"+
						" && status==true";
				List<Role> rSearch = (List<Role>)pm.newQuery(query2).execute();
				System.out.println(rSearch.get(0).getId().equals(idRR));
				boolean pasar = rSearch.get(0).getId().equals(idRR);
				if(rSearch.isEmpty()) {
					String error= "adminP";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response); 
				}else {
					if(pasar) {
						String res = request.getParameter("action");
						boolean igual= false; 
						if(res != null ) {
							List<Role> students = todosLosTutoriales();
							String condicion = "";
							for(int i = 0; i<students.size();i++) {
								Role s = (Role) students.get(i);
								if(s.getName().equalsIgnoreCase(request.getParameter("name"))) {
									condicion= "name";
									igual = true;
									break;
								}
							}
							if(!igual) {
								boolean activo;
								if(request.getParameter("status")!= null) {
									activo = true;
								}else {
									activo = false;
								}
								Role a = new Role(
										request.getParameter("name"),
										activo
										);

								try {
									pm.makePersistent(a);
								} finally {
									pm.close();
								}
								response.sendRedirect("../roles");
							}else {
								request.setAttribute("condicion", condicion);
								request.getRequestDispatcher("../WEB-INF/Views/Roles/view.jsp").forward(request, response);
							}
						}else {
							request.getRequestDispatcher("../WEB-INF/Views/Roles/add.jsp").forward(request, response);
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

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	processRequest(request, response);
}
}
