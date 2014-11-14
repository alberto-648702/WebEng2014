package bigws.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import formats.json.Task;
import formats.json.TaskList;

@WebService
public class ToDoServices {

	private final static String DEFAULT_FILE_NAME = "ToDoList.json";

	@WebMethod()
	public String addTask(String name, String context, String project,
			int priority) {
		String filename = DEFAULT_FILE_NAME;
		String estado = "Task successfully added";
		Gson gson = new Gson();
		TaskList taskList = new TaskList();

		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
		} catch (JsonSyntaxException | JsonIOException e1) {
			estado = "Something has gone wrong";
			e1.printStackTrace();
		} catch (FileNotFoundException e) {

		}

		// Create and fill a new task
		Task task = new Task();
		if (taskList.getLast() == null)
			task.setId(1);
		else
			task.setId(taskList.getLast().getId() + 1);
		task.setName(name);
		task.setContext(context);
		task.setProject(project);
		task.setPriority(priority);

		// Add an address.
		taskList.addTask(task);

		// Write the new address book back to disk.
		FileWriter output;
		try {
			output = new FileWriter(filename);
			output.write(gson.toJson(taskList));
			output.close();
		} catch (IOException e) {
			estado = "Something has gone wrong";
			e.printStackTrace();
		}
		return estado;
	}

	@WebMethod()
	public String removeTask(int id) {

		String estado = "Task successfully removed";
		String filename = DEFAULT_FILE_NAME;
		Gson gson = new Gson();
		TaskList taskList = null;

		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
			taskList.removeTask(id - 1);
			for (int i = 0; i < taskList.getLast().getId(); i++)
				taskList.get(i).setId(i + 1);

		} catch (JsonSyntaxException e1) {
			estado = "Something has gone wrong";
			e1.printStackTrace();
		} catch (JsonIOException e1) {
			estado = "Something has gone wrong";
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			estado = "Something has gone wrong";
			e1.printStackTrace();
		}catch (IndexOutOfBoundsException e1) {
			estado = "Task not found";
		}

		try {
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			output.write(gson.toJson(taskList));
			output.close();

		} catch (IOException e) {
			estado = "Something has gone wrong";
			e.printStackTrace();
		}

		return estado;
	}

	@WebMethod()
	public String listTasks() {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		String lista = "";
		TaskList taskList = null;

		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
			if (taskList != null && !taskList.isEmpty()) {
				for (Task task : taskList.getTaskList()) {
					lista = lista + "\n<br><br>\nNº: " + task.getId()
							+ "\n<br>Task: " + task.getName()
							+ "\n<br>Context: " + task.getContext()
							+ "\n<br>Project: " + task.getProject()
							+ "\n<br>Priority: " + task.getPriority();
				}
			} else
				lista = "";

		} catch (JsonSyntaxException e) {
			lista = "Something has gone wrong";
			e.printStackTrace();
		} catch (JsonIOException e) {
			lista = "Something has gone wrong";
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			lista = "Something has gone wrong";
			e.printStackTrace();
		}
		return lista;
	}

	@WebMethod
	public String removeAll() {
		String estado = "List cleared";

		try {
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			TaskList List = new TaskList();
			Gson gson = new Gson();
			output.write(gson.toJson(List));
			output.close();
		} catch (IOException e) {
			estado = "Something has gone wrong";
			e.printStackTrace();
		}

		return estado;
	}
}
