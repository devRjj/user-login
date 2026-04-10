package com.devraj.registration.controller;

import com.devraj.registration.entity.User;
import com.devraj.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, 
                           RedirectAttributes redirectAttributes) {
        User user = userService.validateLogin(username, password);
        
        if (user != null) {
            // Login successful - redirect to users page
            redirectAttributes.addFlashAttribute("loggedInUser", user.getEmail());
            return "redirect:/users";
        } else {
            // Login failed - redirect back with error
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new com.devraj.registration.entity.User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute com.devraj.registration.entity.User user, RedirectAttributes redirectAttributes) {
        try {
            userService.register(user.getEmail(), user.getPassword());
            // Add a flash attribute to show a success message on the login page
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (Exception e) {
            // Handle duplicate emails or other errors
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
}