package servlets.opciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import formats.json.web.ListTasks;
import formats.json.web.TaskList;

/**
 * Servlet implementation class Seleccion
 */
@WebServlet("/Seleccion")
public class Seleccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Seleccion() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String opcion = request.getParameter("opciones");
		PrintWriter out = response.getWriter();
		TaskList taskList = new TaskList();
		Gson gson = new Gson();
		try {
			taskList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskList.class);
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  Creating a new file.");
		}
		if (opcion.equals("listar")) {
			String lista = "";
			try {
				lista = ListTasks.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println("<html><head><title>Listar tareas</title></head>"
					+ "<body><h1>Ha elegido listar tareas!</h1><h3>"
					+ lista
					+ "</h3><br>"
					+ "<input type='button' value='Menu' onClick=\"location.href=\'index.html\'\"/></body></html>");
		} else if (opcion.equals("anadir")) {
			response.sendRedirect("anadir.html");
		} else if (opcion.equals("filtrar")) {
			response.sendRedirect("filtrar.html");
		} else if (opcion.equals("limpiar")) {
			
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			TaskList List = new TaskList();
			output.write(gson.toJson(List));
			output.close();
			
			out.println("<html><head><title>Limpio</title><META HTTP-EQUIV='REFRESH'"
					+ " CONTENT='4;URL=index.html'></head><body><br><h2>Lista completamente borrada!</h2>"
					+ "<h3>El navegador le redirigirá en unos segundos al menú principal, o pulse el botón.</h3>"
					+ "<p><input type='button' value='Continuar' onClick=\"location.href=\'index.html\'\"/></body></html>");
		}

		else {
			out.println("<html><head><title>Error</title><META HTTP-EQUIV='REFRESH'"
					+ " CONTENT='4;URL=index.html'></head><body><br><h2>ERROR 404 Not Found</h2>"
					+ "<h3>El navegador le redirigirá en unos segundos al menú principal, o pulse el botón.</h3>"
					+ "<p><input type='button' value='Continuar' onClick=\"location.href=\'index.html\'\"/></body></html>");
		}
	}

}
