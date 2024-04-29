package com.api.diningreview.service;

import com.api.diningreview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.diningreview.entities.User;

import java.util.List;
import java.util.Optional;

/*
 * Service class for User entity to perform CRUD operations and custom queries
 * saveUser - save a new user, display name must be unique
 * getUserById - get a user by id
 * getUserByDisplayName - get a user by display name
 * getAllUsers - get all users
 * updateUser - update a user, cannot modify display name
 * deleteUser - delete a user
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CRUD methods for User entity
    public User saveUser(User user) {
        User newUser;
        String currDisplayName = user.getDisplayName();
        // Is display name unique?
        if (userRepository.doesDisplayNameExist(currDisplayName)) {
            return null;
        }
        newUser = userRepository.save(user);
        return newUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByDisplayName(String displayName) {
        return userRepository.findByDisplayName(displayName).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setCity(user.getCity());
            updatedUser.setState(user.getState());
            updatedUser.setZipCode(user.getZipCode());
            updatedUser.setHasPeanutAllergy(user.isHasPeanutAllergy());
            updatedUser.setHasEggAllergy(user.isHasEggAllergy());
            updatedUser.setHasDairyAllergy(user.isHasDairyAllergy());
            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            return null;
        }
    }

    public User deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            userRepository.delete(existingUser);
            return existingUser;
        } else {
            return null;
        }

    }

}
