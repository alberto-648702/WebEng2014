package servlets.opciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import formats.json.web.ListTasks;

/**
 * Servlet implementation class Filtrar
 */
@WebServlet("/Filtrar")
public class Filtrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Filtrar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String nombre = request.getParameter("nom");
		String contexto = request.getParameter("context");
		String proyecto = request.getParameter("pro");
		String prioridad = request.getParameter("prior");
		 

		Map <String, String> params = new HashMap<String, String>();
		params.put("nombre", nombre);
		params.put("contexto", contexto);
		params.put("proyecto", proyecto);
		params.put("prioridad", prioridad);
		
		String lista = "";
		try {
			lista = ListTasks.main(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("<html><head><title>Listar tareas</title></head>"
				+ "<body><h1>Ha elegido filtrar tareas!</h1><h3>"
				+ lista
				+ "</h3><br>"
				+ "<input type='button' value='Menu' onClick=\"location.href=\'index.html\'\"/></body></html>");
	}

}
