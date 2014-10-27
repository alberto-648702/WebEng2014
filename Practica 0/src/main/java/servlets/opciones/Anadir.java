package servlets.opciones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import formats.json.web.AddTask;

/**
 * Servlet implementation class Anadir
 */
@WebServlet("/Anadir")
public class Anadir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Anadir() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nom");
		String contexto = request.getParameter("context");
		String proyecto = request.getParameter("pro");
		String prioridad = request.getParameter("prior");
		int prio = 0;
		if(!prioridad.equals("") && prioridad != null)
			prio = Integer.parseInt(prioridad);
		
		
		try {
			AddTask.main(nombre, contexto, proyecto, prio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("<html><head><title>Tarea Añadida</title><META HTTP-EQUIV='REFRESH'"
				+ " CONTENT='3;URL=index.html'></head><body><br><h2>Se ha añadido correctamente la tarea!</h2>"
				+ "<h3>El navegador le redirigirá en unos segundos al menú principal, o pulse el boton.</h3>"
				+ "<p><input type='button' value='Continuar' onClick=\"location.href=\'index.html\'\"/></body></html>");
	}

}
