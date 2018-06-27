package controller.students;

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
import model.entity.Access;
import model.entity.Resource;
import model.entity.Student;
import model.entity.User;

import com.google.appengine.api.users.UserServiceFactory;
public class StudentsControllorDelete extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		com.google.appengine.api.users.User ugGoogle = UserServiceFactory.getUserService().getCurrentUser();
		String query1 = "select from "+ User.class.getName() +
				" where email=='"+ugGoogle.getEmail()+"'" +
				" && status==true";
		List<User> uSearch = (List<User>)pm.newQuery(query1).execute();
		String query2 = "select from "+ Resource.class.getName() +
				" where name=='" + request.getServletPath() + "'"+
				" && status==true";
		List<Resource> rSearch = (List<Resource>) pm.newQuery(query2).execute();
		if(rSearch.isEmpty()) {
			String error = "sinPermisoDelete";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
		}else {
			String IDResource = Long.toString(rSearch.get(0).getId());
			String query3 = "select from "+ Access.class.getName()+
					" where nameRole=='"+uSearch.get(0).getRole() +"'"+
					" && nameResource=='"+ IDResource+"'"+
					" && status==true";
			List<Access> aSearch = (List<Access>)pm.newQuery(query3).execute();
			if(aSearch.isEmpty()) {
				String error = "sinAccesoDelete";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
			}else {
				Key k = KeyFactory.createKey(Student.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
				Student s =  pm.getObjectById(Student.class,k);

				Long id = s.getId();
				pm.deletePersistent(s);
				response.sendRedirect("/students");	
				pm.close();
			}
		}
	}
	public static List<Student> todosLosTutoriales(){
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Student.class);
		query.setRange(0, FETCH_MAX_RESULTS);
		return (List<Student>) query.execute();
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
