// controller/LoginController.java
package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        var userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && BCrypt.checkpw(password, userOpt.get().getPasswordHash())) {
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "<h1>Welcome to the Dashboard</h1>";
    }
}
