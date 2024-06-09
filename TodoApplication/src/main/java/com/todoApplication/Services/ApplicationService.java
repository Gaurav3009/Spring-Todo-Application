package com.todoApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoApplication.DAO.TaskDao;
import com.todoApplication.Entity.Task;

@Service
public class ApplicationService {
	
	@Autowired
	private TaskDao taskDao;
	
	public void addTask(Task newTask) {
		List<Task> list = getActiveTasks();
		boolean check = false;
		for(Task t : list) {
			if(newTask.getTaskId() == t.getTaskId()) {
				check = true;
				break;
			}
		}
		if(check){
			taskDao.updateTask(newTask);
		}else {
			taskDao.insertTask(newTask);
		}
	}
	
	public List<Task> getActiveTasks() {
		return taskDao.getAllTasks();
	}
	
	public void removeTask(int taskId) {
		taskDao.deleteTask(taskId);
	}
	
	
}
