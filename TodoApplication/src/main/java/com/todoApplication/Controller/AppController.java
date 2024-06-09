package com.todoApplication.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.todoApplication.Entity.Task;
import com.todoApplication.Services.ApplicationService;


@Controller
public class AppController {
	
	@Autowired
	private ApplicationService service;
	
	private List<Task> activeTask = new ArrayList<Task>();
	
	private int indicator = 0;
	
	public void updateModel(Model model, String title, String name, String desc) {
		model.addAttribute("formName", title);
		model.addAttribute("taskName", name);
		model.addAttribute("taskDescription", desc);
	}
	
	@RequestMapping("/app")
	public String handleHome(Model model) {
		activeTask = service.getActiveTasks();
		Task[] dataArray = new Task[activeTask.size()];
		dataArray = activeTask.toArray(dataArray);
		System.out.println(activeTask);
		model.addAttribute("dataArray", dataArray);
		if(indicator == 0) {
			updateModel(model, "Enter task details", "", "");
		} else {
			Task t = new Task();
			for(Task obj : activeTask) {
				if(obj.getTaskId() == indicator) {
					t = obj;
					break;
				}
			}
			updateModel(model, "Edit task details", t.getTaskName(), t.getTaskDescription());
		}
		return "App";
	}
	
	@RequestMapping(path = "/addTask", method = RequestMethod.POST)
	public RedirectView handleForm(@ModelAttribute("task") Task task, HttpServletRequest request) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		if(indicator != 0) {
			task.setTaskId(indicator);
			indicator = 0;
		}
		service.addTask(task);
		return redirectView;
	}
	
	@RequestMapping(path = "/deleteTask/{taskId}")
	public RedirectView handleDelete(@PathVariable("taskId") int taskId, HttpServletRequest request) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		service.removeTask(taskId);
		return redirectView;
	}
	
	@RequestMapping(path = "/updateTask/{taskId}")
	public RedirectView updateTask(@PathVariable("taskId") int taskId, HttpServletRequest request, Model model) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		indicator = taskId;
		return redirectView;
	}
	
}
