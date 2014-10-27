package formats.json.desktop;

import java.io.FileReader;

import com.google.gson.Gson;


public class ListTasks {
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(TaskList taskList) {
		for (Task task: taskList.getTaskList()) {
			System.out.println("\n	Task: " + task.getName());
			System.out.println("	Context: " + task.getContext());
			System.out.println("	Project: " + task.getProject());
			System.out.println("	Priority: " + task.getPriority());
			
		}
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}
		// Read the existing ToDo list.
		TaskList taskList = gson.fromJson(new FileReader(
				filename), TaskList.class);
		Print(taskList);
	}
}