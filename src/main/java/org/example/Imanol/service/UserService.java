package org.example.Imanol.service;

import org.example.Imanol.model.User;
import org.example.Imanol.dto.UserDTO;
import org.example.Imanol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*public UserDTO updateUser(String id, UserDTO userDTO) {
    Optional<User> existingUserOptional = userRepository.findById(id);

    if (existingUserOptional.isEmpty()) {
        throw new UserNotFoundException(id); // 404 Not Found
    }

    User existingUser = existingUserOptional.get();
    // Update fields from DTO
    existingUser.setName(userDTO.getName());
    existingUser.setEmail(userDTO.getEmail());

    User updatedUser = userRepository.save(existingUser); // Save updated user
    return updatedUser.toUserDTO(); // Convert back to DTO and return
}*/


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public long countUsers() {
        return userRepository.count();
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if ( user == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
        }

        return user.toUserDTO();

    }

    public List<UserDTO> searchUsers(String query) {
        List<User> users = userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
        return users.stream().map(User::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }

        User user = optionalUser.get();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        User updatedUser = userRepository.save(user);
        return updatedUser.toUserDTO();
    }

    // Fetch all users and return as UserDTO
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(User::toUserDTO)  // Convert User entity to UserDTO
                .collect(Collectors.toList());
    }

    // Fetch user by ID and return as UserDTO
    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }

        return user.get().toUserDTO(); // Convert User to UserDTO and return
    }

    // Save a new user (accept UserDTO, convert to User entity, and save to DB)
    public UserDTO saveUser(UserDTO userDTO) {
        // Assuming validation logic here (example: email should not be empty)
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        User user = new User().toUser(userDTO); // Convert DTO to User entity
        User savedUser = userRepository.save(user); // Save the user to the DB
        return savedUser.toUserDTO(); // Convert saved User back to UserDTO and return
    }

    // Delete user by ID
    public void deleteUser(String id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }

        userRepository.deleteById(id);
    }
}
