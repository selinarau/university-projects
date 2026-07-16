package com.planner.controller;

import com.planner.dto.LoginRequest;
import com.planner.model.User;
import com.planner.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.modifyUserDetails(user, userId);
    }

    @PutMapping("/password")
    public String changePassword(@RequestParam Long userId, @RequestParam String currentPassword, @RequestParam String newPassword) {
        return userService.changePassword(userId, currentPassword, newPassword);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}