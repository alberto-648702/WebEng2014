package bigws.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigws.server.Task;
import bigws.server.TaskList;
import bigws.server.ToDoServices;
import bigws.server.ToDoServicesService;

/**
 * Servlet implementation class Opciones
 */
@WebServlet("/Opciones")
public class Opciones extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Opciones() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String opcion = request.getParameter("option");
		ToDoServicesService tdss = new ToDoServicesService();
		ToDoServices tds = tdss.getToDoServicesPort();

		if (opcion.equals("add")) {
			String nombre = request.getParameter("nom");
			String contexto = request.getParameter("context");
			String proyecto = request.getParameter("pro");
			String prioridad = request.getParameter("prior");
			int prio = 0;
			if(!prioridad.equals("") && prioridad != null)
				prio = Integer.parseInt(prioridad);
			
			Task task = new Task();
			TaskList tL = tds.listTasks();
			if(tL != null){
				List<Task> tl = tL.getTaskList();
				if(!tl.isEmpty())
					task.setId(tl.get(tl.size()-1).getId()+1);
				else
					task.setId(1);
			}
			else{
				task.setId(1);
			}
			
			task.setName(nombre);
			task.setContext(contexto);
			task.setProject(proyecto);
			task.setPriority(prio);
			tds.addTask(task);
			
			response.sendRedirect("index.jsp");
		} else if (opcion.equals("remove")) {
			String id = request.getParameter("id");
			int id2 = 0;
			if(!id.equals("") && id != null){
				id2 = Integer.parseInt(id);
				tds.removeTask(id2);
			}

			response.sendRedirect("index.jsp");
		} else if (opcion.equals("removeAll")) {
			tds.removeAll();
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}

	}
}
