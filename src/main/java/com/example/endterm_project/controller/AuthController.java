package com.example.endterm_project.controller;

import com.example.endterm_project.dto.LoginDto;
import com.example.endterm_project.dto.UserDto;
import com.example.endterm_project.entity.User;
import com.example.endterm_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth") // Base URL for auth-related endpoints
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to handle user registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        // Check if the email is already registered
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("There is already an account registered with the same email.");
        }

        // Save the user
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    // Endpoint to fetch all users
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        // Find the user by email
        User user = userService.findUserByEmail(loginDto.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        // Validate the password
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        // Login successful
        return ResponseEntity.ok("Login successful!");
    }
}
