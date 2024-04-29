package com.api.diningreview.repositories;

import com.api.diningreview.entities.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Repository for Restaurant entity for CRUD operations
 * doesRestaurantExist - check if restaurant exists by name and zip code
 * findAllByZipCode - find all restaurants by zip code
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    boolean doesRestaurantExist(String name, String zipCode);
    List<Restaurant> findAllByZipCode(String zipCode);
}
