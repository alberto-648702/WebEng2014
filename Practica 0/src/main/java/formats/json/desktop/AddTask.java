package formats.json.desktop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;


public class AddTask {
	
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";

	// This function fills in a Person message based on user input.
	static Task PromptForTask(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		Task task = new Task();

		stdout.print("\n	Enter task name: ");
		task.setName(stdin.readLine());

		stdout.print("\n	Enter context (blank for none): ");
		String context = stdin.readLine();
		if (context.length() > 0) {
			task.setContext(context);
		}

		stdout.print("\n	Enter project: ");
		task.setProject(stdin.readLine());
		
		stdout.print("\n	Enter priority: ");
		task.setPriority(Integer.valueOf(stdin.readLine()));

		return task;
	}

	// Main function: Reads the entire address book from a file,
	// adds one person based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename=args[0];
		}

		TaskList taskList = new TaskList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			taskList = gson.fromJson(new FileReader(filename), TaskList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}

		// Add an address.
		taskList.addTask(PromptForTask(new BufferedReader(
				new InputStreamReader(System.in)), System.out));

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(taskList));
		output.close();
	}
}
