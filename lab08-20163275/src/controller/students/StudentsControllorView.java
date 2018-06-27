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

import model.entity.Student;

public class StudentsControllorView extends HttpServlet{
	private final static int FETCH_MAX_RESULTS = 10;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String res =request.getParameter("action");
		
		Key k = KeyFactory.createKey(Student.class.getSimpleName(), new Long(request.getParameter("ID")).longValue());
		Student s =  pm.getObjectById(Student.class,k);
			
					
			request.setAttribute("student", s);
			
			request.getRequestDispatcher("../WEB-INF/Views/Students/view.jsp").forward(request, response);
		
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
