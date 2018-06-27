package controller.students;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.User;
import model.entity.Resource;
import model.entity.Student;
import model.entity.Access;
public class StudentsControllorIndex extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
		
		com.google.appengine.api.users.User ugGoogle = UserServiceFactory.getUserService().getCurrentUser();
		
		if(ugGoogle == null) {
			String error= "login";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(req, resp);
		}else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from "+ User.class.getName() +
					" where email=='"+ugGoogle.getEmail()+"'" +
					" && status==true";
			List<User> uSearch = (List<User>)pm.newQuery(query).execute();
			if(uSearch.isEmpty()) {
				String error = "noExiste";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(req, resp);
			}else {
				System.out.println(req.getServletPath());
				String query2 = "select from "+ Resource.class.getName() +
						" where name=='" + req.getServletPath() + "'"+
						" && status==true";
				List<Resource> rSearch = (List<Resource>) pm.newQuery(query2).execute();
				if(rSearch.isEmpty()) {
					String error = "sinPermiso";
					req.setAttribute("error", error);
					req.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(req, resp);
				}else {	
					String IDResource = Long.toString(rSearch.get(0).getId());
					String query3 = "select from "+ Access.class.getName()+
							" where nameRole=='"+uSearch.get(0).getRole() +"'"+
							" && nameResource=='"+ IDResource+"'"+
							" && status==true";
					List<Access> aSearch = (List<Access>)pm.newQuery(query3).execute();
					if(aSearch.isEmpty()) {
						String error = "sinAcceso";
						req.setAttribute("error", error);
						req.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(req, resp);
						
					}else {
						List<Student> students = todosLosTutoriales();
						req.setAttribute("showAllStudents", students);
						req.getRequestDispatcher("/WEB-INF/Views/Students/index.jsp").forward(req, resp);
					}
				}
			}
		}
	}
	public static List<Student> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Student.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Student>) query.execute();
	}
}
