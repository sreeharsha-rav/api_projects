package com.api.diningreview.controller;

import com.api.diningreview.dto.RestaurantDTO;
import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Controller class for Restaurant entity to handle HTTP requests for restaurant operations
 * addRestaurant - add a new restaurant
 * getRestaurantById - get a restaurant by id
 * getRestaurantsBySearchParams - get restaurants by zip code with at least one submitted allergy score by user
 * updateRestaurant - update a restaurant
 * deleteRestaurant - delete a restaurant
 */
@RestController
@RequestMapping("api/dining_review/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setZipCode(restaurantRequest.getZipCode());
        restaurant.setPeanutScore(restaurantRequest.getPeanutScore());
        restaurant.setEggScore(restaurantRequest.getEggScore());
        restaurant.setDairyScore(restaurantRequest.getDairyScore());

        Double overallScore = (restaurantRequest.getPeanutScore() + restaurantRequest.getEggScore() + restaurantRequest.getDairyScore()) / 3.0;
        restaurant.setOverallScore(overallScore);

        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        if (newRestaurant == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restaurant name and zip code already exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<?> getRestaurantsBySearchParams(@RequestParam(required = false) String zipCode, @RequestParam(required = false) String allergy) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(zipCode, allergy);
        if (restaurants == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No restaurants found with the given search parameters");
        }
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setZipCode(restaurantRequest.getZipCode());
        restaurant.setPeanutScore(restaurantRequest.getPeanutScore());
        restaurant.setEggScore(restaurantRequest.getEggScore());
        restaurant.setDairyScore(restaurantRequest.getDairyScore());

        Double overallScore = (restaurantRequest.getPeanutScore() + restaurantRequest.getEggScore() + restaurantRequest.getDairyScore()) / 3.0;
        restaurant.setOverallScore(overallScore);

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        if (updatedRestaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.deleteRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

}
