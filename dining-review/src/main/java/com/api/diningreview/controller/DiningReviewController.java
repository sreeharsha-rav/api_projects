package com.api.diningreview.controller;

import com.api.diningreview.dto.DiningReviewDTO;
import com.api.diningreview.entities.DiningReview;
import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.entities.User;
import com.api.diningreview.service.DiningReviewService;
import com.api.diningreview.service.RestaurantService;
import com.api.diningreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

/*
 * Controller class for DiningReview entity to handle HTTP requests for dining review operations
 * addDiningReview - add a new dining review
 * getDiningReviewById - get a dining review by id
 * updateDiningReview - update a dining review
 * deleteDiningReview - delete a dining review
 */
@RestController
@RequestMapping("api/dining_review/reviews")
public class DiningReviewController {

    @Autowired
    private DiningReviewService diningReviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> addDiningReview(@RequestBody DiningReviewDTO diningReviewRequest) {
        // Check if user exists
        User user = userService.getUserByDisplayName(diningReviewRequest.getUserName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        // Check if restaurant exists
        Restaurant restaurant = restaurantService.getRestaurant(diningReviewRequest.getRestaurantId());
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
        // Create dining review
        DiningReview diningReview = new DiningReview();
        diningReview.setUserSubmit(user);
        diningReview.setRestaurant(restaurant);
        diningReview.setPeanutScore(diningReviewRequest.getPeanutScore());
        diningReview.setEggScore(diningReviewRequest.getEggScore());
        diningReview.setDairyScore(diningReviewRequest.getDairyScore());
        diningReview.setCommentary(diningReviewRequest.getCommentary());

        // Save the dining review
        DiningReview newDiningReview = diningReviewService.submitDiningReview(diningReview);
        if (newDiningReview == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Review already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newDiningReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiningReviewById(@PathVariable Long id) {
        DiningReview diningReview = diningReviewService.getDiningReview(id);
        if (diningReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dining review not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(diningReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiningReview(@PathVariable Long id, @RequestBody DiningReviewDTO diningReviewRequest) {
        // Check if dining review exists
        DiningReview diningReview = diningReviewService.getDiningReview(id);
        if (diningReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dining review not found");
        }
        // Check if user exists and matches the user who submitted the review
        User user = userService.getUserByDisplayName(diningReviewRequest.getUserName());
        if (user == null || !diningReview.getUserSubmit().equals(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or does not match the user who submitted the review");
        }
        // Check if restaurant exists and matches the restaurant being reviewed
        Restaurant restaurant = restaurantService.getRestaurant(diningReviewRequest.getRestaurantId());
        if (restaurant == null || !diningReview.getRestaurant().equals(restaurant)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found or does not match the restaurant being reviewed");
        }

        // Update dining review
        diningReview.setPeanutScore(diningReviewRequest.getPeanutScore());
        diningReview.setEggScore(diningReviewRequest.getEggScore());
        diningReview.setDairyScore(diningReviewRequest.getDairyScore());
        diningReview.setCommentary(diningReviewRequest.getCommentary());

        // Save the updated dining review
        DiningReview updatedDiningReview = diningReviewService.updateDiningReview(diningReview);
        if (updatedDiningReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dining review failed to update");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedDiningReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiningReview(@PathVariable Long id) {
        DiningReview diningReview = diningReviewService.deleteDiningReview(id);
        if (diningReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dining review not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(diningReview);
    }
}
