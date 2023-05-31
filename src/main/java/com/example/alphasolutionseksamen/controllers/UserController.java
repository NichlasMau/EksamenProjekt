package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    @PostMapping("/login")
    public ResponseEntity < String > login(@RequestParam("email") String email, @RequestParam("pw") String pw,
                                           HttpSession session,
                                           Model model,
                                           HttpServletRequest request) {
        User user = userRepository.getUserEmail(email);
        if (user != null) {
            if (user.getPassword().equals(pw)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(600);
                return ResponseEntity.ok("SUCCESS");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/signup")
    public ResponseEntity < String > signup(@RequestParam("name") String name,
                                            @RequestParam("email") String email,
                                            @RequestParam("username") String username,
                                            @RequestParam("password") String password,
                                            HttpSession session, Model model) {
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Fill out all fields!");

        }

        try {
            User existingUser = userRepository.getUserEmail(email);
            if (existingUser != null) {
                return ResponseEntity.badRequest().body("Email is already registered.");
            }

            User newUser = new User(name, email, username, password);
            session.setAttribute("user", userRepository.getUserEmail(userRepository.createUser(newUser)));
            session.setMaxInactiveInterval(600);

            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}