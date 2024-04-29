package com.api.diningreview.controller;

import com.api.diningreview.entities.AdminReviewAction;
import com.api.diningreview.entities.DiningReview;
import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.entities.ReviewStatus;
import com.api.diningreview.service.DiningReviewService;
import com.api.diningreview.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Controller class for Admin entity to handle HTTP requests for admin operations
 * acceptReview - accept or reject a dining review
 */
@RestController
@RequestMapping("api/dining_review/admin")
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DiningReviewService diningReviewService;

    /*
     * Accept or reject a dining review
     * If acceptReview is true, update the review status to ACCEPTED
     * If acceptReview is false, update the review status to REJECTED
     * Update the restaurant's allergy scores and overall score
     * Return a message indicating the action taken
     */
    @PostMapping("/{id}")
    public String acceptReview(@RequestBody AdminReviewAction adminReviewAction, @PathVariable Long reviewId) {
        if (adminReviewAction.isAcceptReview()) {
            // Get dining review by id
            DiningReview diningReview = diningReviewService.getDiningReview(reviewId);
            // Update review status to ACCEPTED
            diningReview.setReviewStatus(ReviewStatus.ACCEPTED);
            // Save the updated dining review
            diningReviewService.updateDiningReview(diningReview);

            // Get all dining reviews by restaurant id
            Long restaurantId = diningReview.getRestaurant().getId();
            List<DiningReview> diningReviews = diningReviewService.findBAllByRestaurantId(restaurantId);
            // Filter dining reviews by ACCEPTED status
            List<DiningReview> acceptedDiningReviews = diningReviews.stream()
                    .filter(review -> review.getReviewStatus().equals(ReviewStatus.ACCEPTED))
                    .toList();
            // Compute new allergy scores
            double peanutScore = acceptedDiningReviews.stream()
                    .mapToDouble(DiningReview::getPeanutScore)
                    .average()
                    .orElse(0);
            double eggScore = acceptedDiningReviews.stream()
                    .mapToDouble(DiningReview::getEggScore)
                    .average()
                    .orElse(0);
            double dairyScore = acceptedDiningReviews.stream()
                    .mapToDouble(DiningReview::getDairyScore)
                    .average()
                    .orElse(0);
            // Compute new overall score
            double overallScore = acceptedDiningReviews.stream()
                    .mapToDouble(review -> (review.getPeanutScore() + review.getEggScore() + review.getDairyScore()) / 3)
                    .average()
                    .orElse(0);
            // Update restaurant
            Restaurant updatedRestaurant = restaurantService.getRestaurant(restaurantId);
            updatedRestaurant.setPeanutScore(peanutScore);
            updatedRestaurant.setEggScore(eggScore);
            updatedRestaurant.setDairyScore(dairyScore);
            updatedRestaurant.setOverallScore(overallScore);
            restaurantService.updateRestaurant(restaurantId, updatedRestaurant);

            return "Review accepted";

        } else {
            return "Review rejected";
        }
    }

}
