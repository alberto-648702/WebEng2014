package formats.json.web;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

	private List<Task> taskList = new ArrayList<Task>();

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> task) {
		this.taskList = task;
	}

	public void addTask(Task task) {
		taskList.add(task);
	}
	public boolean isEmpty(){
		return taskList==null && taskList.isEmpty();
	}
}
