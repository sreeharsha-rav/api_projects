package com.api.diningreview.repositories;

import com.api.diningreview.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Repository for User entity to perform CRUD operations and custom queries
 * findByDisplayName - find user by display name
 * doesDisplayNameExist - check if display name exists for a user
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDisplayName(String displayName);
    boolean doesDisplayNameExist(String displayName);
}
