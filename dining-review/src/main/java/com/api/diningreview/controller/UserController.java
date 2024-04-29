package com.api.diningreview.controller;

import com.api.diningreview.dto.UserDTO;
import com.api.diningreview.entities.User;
import com.api.diningreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controller class for User entity to handle HTTP requests for user operations
 * createUser - create a new user
 * getUserByName - get a user by name
 * updateUser - update a user
 * deleteUser - delete a user
 */
@RestController
@RequestMapping("api/dining_review/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userRequest) {
        User user = new User();
        user.setDisplayName(userRequest.getDisplayName());
        user.setCity(userRequest.getCity());
        user.setState(userRequest.getState());
        user.setZipCode(userRequest.getZipCode());
        // Check if allergies are present else set to false by default
        user.setHasPeanutAllergy(userRequest.isHasPeanutAllergy());
        user.setHasEggAllergy(userRequest.isHasEggAllergy());
        user.setHasDairyAllergy(userRequest.isHasDairyAllergy());

        // Save the user
        User newUser = userService.saveUser(user);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Display name already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Get a user by display name
    @GetMapping("/name/{displayName}")
    public ResponseEntity<?> getUserByName(@PathVariable String displayName) {
        User user = userService.getUserByDisplayName(displayName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userRequest) {
        User user = new User();
        user.setCity(userRequest.getCity());
        user.setState(userRequest.getState());
        user.setZipCode(userRequest.getZipCode());
        // If allergies are present, set values else ignore
        if (userRequest.isHasPeanutAllergy()) {
            user.setHasPeanutAllergy(userRequest.isHasPeanutAllergy());
        }
        if (userRequest.isHasEggAllergy()) {
            user.setHasEggAllergy(userRequest.isHasEggAllergy());
        }
        if (userRequest.isHasDairyAllergy()) {
            user.setHasDairyAllergy(userRequest.isHasDairyAllergy());
        }

        // Update the user
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.deleteUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
