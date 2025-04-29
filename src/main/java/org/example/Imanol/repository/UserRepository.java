package org.example.Imanol.repository;

import org.example.Imanol.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    // You can add custom query methods here, if needed
    User findByEmail(String email);
    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String query, String email);
}
