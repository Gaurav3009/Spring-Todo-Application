package com.todoApplication.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todoApplication.Entity.Task;

@Repository
public class TaskDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Transactional
	public void insertTask(Task newTask) {
		this.hibernateTemplate.save(newTask);
	}
	
	public List<Task> getAllTasks() {
		return (ArrayList<Task>)this.hibernateTemplate.loadAll(Task.class);
	}
	
	@Transactional
	public void deleteTask(int taskId) {
		Task task = (Task) this.hibernateTemplate.load(Task.class, taskId);
		this.hibernateTemplate.delete(task);
	}
	
	@Transactional
	public void updateTask(Task task) {
		Task oldTask = (Task) this.hibernateTemplate.load(Task.class, task.getTaskId());
		oldTask.setTaskDescription(task.getTaskDescription());
		oldTask.setTaskName(task.getTaskName());
	}
	

}
