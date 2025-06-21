package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Show all tasks
    @GetMapping
    public String getAllTasks(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }

        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("task", new Task());
        return "index";
    }

    // Add new task
    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }

        taskRepository.save(task);
        return "redirect:/tasks";
    }

    // Delete task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }

        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    // Update task
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task updatedTask, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }

        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            taskRepository.save(existingTask);
        }

        return "redirect:/tasks";
    }
}
