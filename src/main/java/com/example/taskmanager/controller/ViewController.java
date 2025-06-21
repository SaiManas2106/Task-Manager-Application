package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    @Autowired
    private TaskService taskService;

    // Optional: Show form to update task
    @GetMapping("/tasks/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id); // assumes this method exists
        model.addAttribute("task", task);
        return "update-task"; // JSP to render update form
    }

    // Optional: Show form to add new task
    @GetMapping("/tasks/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task"; // JSP to render add task form
    }

    // Optional: Login page route
//    @GetMapping("/")
//    public String showLoginPage() {
//        return "login";
//    }
}
