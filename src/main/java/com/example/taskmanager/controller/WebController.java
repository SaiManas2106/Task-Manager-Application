package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


public class WebController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String viewTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }
}
