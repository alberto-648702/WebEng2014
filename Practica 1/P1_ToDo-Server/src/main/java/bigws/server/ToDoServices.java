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
	public String addTask(Task task) {
		String filename = DEFAULT_FILE_NAME;
		String estado = "Task successfully added";
		Gson gson = new Gson();
		TaskList taskList = new TaskList();
		
		taskList.addTask(new Task());
		
		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
		} catch (JsonSyntaxException | JsonIOException e1) {
			estado = "Something has gone wrong";
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			
		}

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
	public TaskList listTasks() {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		TaskList taskList = null;
		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);

		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			removeAll();
		}
		return taskList;
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
