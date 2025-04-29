package org.example.Imanol.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.example.Imanol.dto.UserDTO;

@Document(collection = "users") // This maps to the MongoDB collection
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to convert from MongoDB entity to DTO
    public UserDTO toUserDTO() {
        return new UserDTO(this.id, this.name, this.email);
    }

    // Static method to convert from DTO to MongoDB entity
    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getEmail()); // No password in DTO
    }
}
