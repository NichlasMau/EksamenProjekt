package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.enums.customerEnum;
import com.example.alphasolutionseksamen.enums.userEnum;
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

import java.util.List;

@Controller
public class UserController {
    UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("pw") String pw,
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

}
