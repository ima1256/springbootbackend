package org.example.Imanol.controller;

import org.example.Imanol.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        return new User("Alice", 30);
    }

}
