package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Replace with a proper user service or database check in real apps
    private final String validUsername = "admin";
    private final String validPassword = "admin123";

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (validUsername.equals(user.getUsername()) && validPassword.equals(user.getPassword())) {
            session.setAttribute("loggedInUser", user.getUsername());
            return "redirect:/tasks";
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
