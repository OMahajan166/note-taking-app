package com.noteapp.controller;

import com.noteapp.entity.User;
import com.noteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String homeRedirect() {

        return "redirect:/login";

    }

    // Login Page

    @GetMapping("/login")
    public String loginPage() {

        return "login";

    }

    // Signup Page

    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("user",
                new User());

        return "signup";

    }

    // Register User

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               Model model) {

        // Check Existing Email

        User existingUser =
                userService.findByEmail(user.getEmail());

        if(existingUser != null) {

            model.addAttribute("error",
                    "Email already registered");

            return "signup";

        }

        userService.registerUser(user);

        return "redirect:/login";
    }

    // Login Authentication

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String email,
                                   @RequestParam String password,
                                   Model model,
                                   HttpSession session) {

        User user = userService.findByEmail(email);

        // Valid Login

        if(user != null &&
                user.getPassword().equals(password)) {

            // Store user in session

            session.setAttribute("loggedInUser", user);

            return "redirect:/dashboard";

        }

        // Invalid Login

        model.addAttribute("error",
                "Invalid Email or Password");

        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}