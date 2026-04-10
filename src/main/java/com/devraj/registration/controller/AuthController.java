package com.devraj.registration.controller;

import com.devraj.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new com.devraj.registration.entity.User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute com.devraj.registration.entity.User user) {
        userService.register(user.getEmail(), user.getPassword());
        return "redirect:/login";
    }
}