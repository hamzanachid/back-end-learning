package com.http.methods.status.codes.services;

import com.http.methods.status.codes.models.User;
import com.http.methods.status.codes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // New method to save a user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Integer id, User user) {
        // Check if the user exists
        if (userRepository.existsById(id)) {
            user.setId(id); // Set the ID of the user to update
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

