package formats.json.web;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;

public class AddTask {

	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	// Main function: Reads the entire address book from a file,
	// adds one person based on user input, then writes it back out to the same
	// file.
	public static void main(String name, String context, String project,
			int priority) throws Exception {
		String filename = DEFAULT_FILE_NAME;

		TaskList taskList = new TaskList();
		Gson gson = new Gson();

		// Read the existing address book.
//		try {
//			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
//		} catch (FileNotFoundException e) {
//			System.out.println(filename
//					+ ": File not found.  Creating a new file.");
//		}

		// Create and fill a new task
		Task task = new Task();

		task.setName(name);
		task.setContext(context);
		task.setProject(project);
		task.setPriority(priority);

		// Add an address.
		taskList.addTask(task);

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(taskList));
		output.close();
	}
}
