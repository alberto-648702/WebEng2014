package formats.json.web;

import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;

public class ListTasks {
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	// Iterates though all people in the AddressBook and prints info about them.
	static String Print(TaskList taskList) {
		String lista = "";
		for (Task task : taskList.getTaskList()) {
			lista = lista + "<br><br>Task: " + task.getName() + "<br>Context: "
					+ task.getContext() + "<br>Project: " + task.getProject()
					+ "<br>Priority: " + task.getPriority();
		}
		return lista;
	}

	static String Print(TaskList taskList, Map<String, String> params) {
		String lista = "";
		boolean n = true, c = true, p1 = true, p2 = true;
		for (Task task : taskList.getTaskList()) {

			if (!params.get("nombre").equals("")
					&& !task.getName().contains(params.get("nombre"))) {
				n = false;
			}
			if (!params.get("contexto").equals("")
					&& !task.getContext().contains(params.get("contexto"))) {
				c = false;
			}
			if (!params.get("proyecto").equals("")
					&& !task.getProject().contains(params.get("proyecto"))) {
				p1 = false;
			}
			if (!params.get("prioridad").equals("")
					&& task.getPriority() != Integer.parseInt(params
							.get("prioridad"))) {
				p2 = false;

			}
			if (n && c && p1 && p2) {
				lista = lista + "<br><br>Task: " + task.getName()
						+ "<br>Context: " + task.getContext() + "<br>Project: "
						+ task.getProject() + "<br>Priority: "
						+ task.getPriority();
			}
			n = true;
			c = true;
			p1 = true;
			p2 = true;
		}

		return lista;
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static String main(Map<String, String> params) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		// Read the existing ToDo list.
		TaskList taskList = gson.fromJson(new FileReader(filename),
				TaskList.class);
		if (taskList != null && !taskList.isEmpty()) {
			if (params == null)
				return Print(taskList);
			else
				return Print(taskList, params);
		} else
			return "";

	}
}