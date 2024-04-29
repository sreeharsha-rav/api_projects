package com.api.diningreview.service;

import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Service class for Restaurant entity to perform CRUD operations and custom queries
 * createRestaurant - create a new restaurant, check if restaurant name and zip code are unique
 * getRestaurant - get a restaurant by id
 * getAllRestaurants - get all restaurants
 * searchRestaurants - search restaurants by zip code and allergy
 * updateRestaurant - update a restaurant
 * deleteRestaurant - delete a restaurant
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        String currName = restaurant.getName();
        String currZipCode = restaurant.getZipCode();
        // Is restaurant name and zip code unique?
        if (restaurantRepository.doesRestaurantExist(currName, currZipCode)) {
            return null;
        }
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByZipCode(String zipCode) {
        List<Restaurant> restaurants = restaurantRepository.findAllByZipCode(zipCode);
        // Filter to get restaurants with at least one submitted allergy score by user
        List<Restaurant> filteredRestaurants = restaurants.stream()
                .filter(restaurant -> restaurant.getPeanutScore() != 0 || restaurant.getEggScore() != 0 || restaurant.getDairyScore() != 0)
                .toList();
        // Sort restaurants by overall score in descending order
        filteredRestaurants.sort((r1, r2) -> r2.getOverallScore().compareTo(r1.getOverallScore()));
        // Return filtered and sorted list of restaurants
        return filteredRestaurants;
    }

    public List<Restaurant> searchRestaurants (String zipCode, String allergy) {
        // Check if zip code is valid
        if (zipCode == null || zipCode.length() != 5) {
            return null;
        }
        // Get all restaurants by zip code
        List<Restaurant> restaurants = restaurantRepository.findAllByZipCode(zipCode);
        if (restaurants.isEmpty()) {
            return null;
        }
        // If no allergy specified, return all restaurants
        if (allergy == null) {
            return restaurants;
        }
        // Filter restaurants by allergy score greater than 0
        switch (allergy) {
            case "peanut":
                return restaurants.stream()
                        .filter(restaurant -> restaurant.getPeanutScore() > 0)
                        .toList();
            case "egg":
                return restaurants.stream()
                        .filter(restaurant -> restaurant.getEggScore() > 0)
                        .toList();
            case "dairy":
                return restaurants.stream()
                        .filter(restaurant -> restaurant.getDairyScore() > 0)
                        .toList();
            default:
                return null;
        }
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setAddress(restaurant.getAddress());
            updatedRestaurant.setPhone(restaurant.getPhone());
            // limit decimal places to 2
            updatedRestaurant.setPeanutScore(Math.round(restaurant.getPeanutScore() * 100.0) / 100.0);
            updatedRestaurant.setEggScore(Math.round(restaurant.getEggScore() * 100.0) / 100.0);
            updatedRestaurant.setDairyScore(Math.round(restaurant.getDairyScore() * 100.0) / 100.0);
            updatedRestaurant.setOverallScore(Math.round(restaurant.getOverallScore() * 100.0) / 100.0);
            restaurantRepository.save(updatedRestaurant);
            return updatedRestaurant;
        } else {
            return null;
        }
    }

    public Restaurant deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
        }
        return restaurant;
    }

}
