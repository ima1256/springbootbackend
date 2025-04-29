package org.example.Imanol.controller;

import org.example.Imanol.model.User;
import org.example.Imanol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.example.Imanol.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /*List<User> users = userRepository.findByNameContainingOrEmailContaining(query, query);
    return users.stream()
            .map(User::toUserDTO)
                .collect(Collectors.toList());*/

    @GetMapping("search/{query}")
    public List<UserDTO> searchUsers(@PathVariable String query) {
        return userService.searchUsers(query);
    }

    @GetMapping("count")
    public long countUsers() {
        return userService.countUsers();
    }

    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Create a new user
    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO user) {
        return userService.saveUser(user);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}

