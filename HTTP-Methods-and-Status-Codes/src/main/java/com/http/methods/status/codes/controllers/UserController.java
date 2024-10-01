package com.http.methods.status.codes.controllers;

import com.http.methods.status.codes.models.User;
import com.http.methods.status.codes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();

            // If the list is not empty, return it with 200 OK
            if (!users.isEmpty()) {
                return ResponseEntity.ok(users);
            } else {
                // If the list is empty, return 204 No Content
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            // Handle any other exceptions and return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // New method to handle POST requests for creating a user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Return 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser
                .map(ResponseEntity::ok) // 200 OK with updated user
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Integer id, @RequestBody User user) {
        // In a real application, you'd have logic to merge the fields
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser
                .map(ResponseEntity::ok) // 200 OK with updated user
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/all")
    public ResponseEntity<Void> headUsers() {
        // HEAD just returns the headers, we can simulate this
        return ResponseEntity.ok().build(); // 200 OK with no content
    }
}
