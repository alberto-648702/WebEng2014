package formats.json;

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
	
	public void removeTask(int id){
		taskList.remove(id);
	}
	
	public Task get(int id){
		if(taskList.isEmpty()){
			return null;
		}
		else return taskList.get(id);
	}
	
	public Task getLast(){
		if(taskList.isEmpty()){
			return null;
		}
		else return taskList.get(taskList.size()-1);
	}
	
	public boolean isEmpty(){
		return taskList==null && taskList.isEmpty();
	}
}
