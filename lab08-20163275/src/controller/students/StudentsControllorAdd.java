package controller.students;

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
import model.entity.Access;
import model.entity.Resource;
import model.entity.Student;
import model.entity.User;

public class StudentsControllorAdd extends HttpServlet{
private final static int FETCH_MAX_RESULTS = 10;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
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
		System.out.println(request.getServletPath());
		if(rSearch.isEmpty()) {
			String error = "sinPermisoEdit";
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
				String error = "sinAccesoAdd";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/Views/Errors/deny.jsp").forward(request, response);
			}else {
				String res = request.getParameter("action");
				boolean igual= false; 
				if(res != null &&request.getParameter("action").equals("create")) {
					List<Student> students = todosLosTutoriales();
					String condicion = "";
					 for(int i = 0; i<students.size();i++) {
						 Student s = (Student) students.get(i);
						 if(s.getDNI().equals(request.getParameter("dni"))) {
							 condicion= "dni";
							 igual = true;
							 break;
						 }else if(s.getEmail().equals(request.getParameter("email"))) {
							 condicion = "email";
							 igual = true;
							 break;
						 }else if(s.getPhone().equals(request.getParameter("phone"))){
							 condicion = "phone";
							 igual = true;
							 break;
						 }
					 }
					 if(!igual) {
						 Student a = new Student(
									request.getParameter("name"),
									request.getParameter("phone"),
									request.getParameter("email"),
									request.getParameter("gender"),
									request.getParameter("date"),
									request.getParameter("dni")
									);
							
								try {
									pm.makePersistent(a);
								} finally {
									pm.close();
								}
								response.sendRedirect("/students");
					 }else {
						 request.setAttribute("condicion", condicion);
						 request.getRequestDispatcher("../WEB-INF/Views/Students/view.jsp").forward(request, response);
					 }
				}else {
					request.getRequestDispatcher("../WEB-INF/Views/Students/add.jsp").forward(request, response);
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
}
