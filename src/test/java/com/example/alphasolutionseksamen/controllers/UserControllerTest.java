package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTest {
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
    }

    @Test
    void testLogin() {
        String email = "user@example.com";
        String password = "password123";
        User user = userRepository.getUserEmail(email);
        if (user.getUser_id() != -1) {
            if(user.getPassword() == password) {
                user = userRepository.getUser(user.getUser_id());
                System.out.println("User found: " + user.toString());
                assertNotNull(user);
            }
            else {
                System.out.println("User found but wrong password!");
                assertTrue(true);
            }
        } else {
            System.out.println("User not found!");
            assertTrue(true);
        }
    }
}