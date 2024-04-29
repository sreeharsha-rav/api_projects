package com.api.diningreview.service;

import com.api.diningreview.entities.DiningReview;
import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.entities.ReviewStatus;
import com.api.diningreview.entities.User;
import com.api.diningreview.repositories.DiningReviewRepository;
import com.api.diningreview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Service class for DiningReview entity to perform CRUD operations and custom queries
 * submitDiningReview - submit a new dining review
 * getDiningReview - get a dining review by id
 * getAllDiningReviews - get all dining reviews
 * getDiningReviewsPendingApproval - get all dining reviews pending approval
 * getDiningReviewsByApprovedRestaurant - get all dining reviews by approved restaurant
 * findBAllByRestaurantId - find dining reviews by restaurant id
 * updateDiningReview - update a dining review
 * deleteDiningReview - delete a dining review
 */
@Service
public class DiningReviewService {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    @Autowired
    private UserRepository userRepository;

    // CRUD methods for the service
    public DiningReview submitDiningReview(DiningReview diningReview) {
        User userSubmit = diningReview.getUserSubmit();
        // Check if user exists
        if (userRepository.doesDisplayNameExist(userSubmit.getDisplayName())) {
            return null;
        } else {
            return diningReviewRepository.save(diningReview);
        }
    }

    public DiningReview getDiningReview(Long id) {
        return diningReviewRepository.findById(id).orElse(null);
    }

    public List<DiningReview> getAllDiningReviews() {
        return diningReviewRepository.findAll();
    }

    public List<DiningReview> getDiningReviewsPendingApproval() {
        return diningReviewRepository.findByReviewStatus(ReviewStatus.PENDING);
    }

    public List<DiningReview> getDiningReviewsByApprovedRestaurant(Restaurant restaurant) {
        return diningReviewRepository.findByRestaurantAndReviewStatus(restaurant, ReviewStatus.ACCEPTED);
    }

    public List<DiningReview> findBAllByRestaurantId(Long restaurantId) {
        return diningReviewRepository.findBAllByRestaurantId(restaurantId);
    }

    public DiningReview updateDiningReview(DiningReview diningReview) {
        Optional<DiningReview> existingDiningReview = diningReviewRepository.findById(diningReview.getId());
        if (existingDiningReview.isPresent()) {
            DiningReview updatedDiningReview = existingDiningReview.get();
            updatedDiningReview.setPeanutScore(diningReview.getPeanutScore());
            updatedDiningReview.setEggScore(diningReview.getEggScore());
            updatedDiningReview.setDairyScore(diningReview.getDairyScore());
            updatedDiningReview.setCommentary(diningReview.getCommentary());
            updatedDiningReview.setReviewStatus(diningReview.getReviewStatus());
            diningReviewRepository.save(updatedDiningReview);
            return updatedDiningReview;
        } else {
            return null;
        }
    }

    public DiningReview deleteDiningReview(Long id) {
        Optional<DiningReview> existingDiningReview = diningReviewRepository.findById(id);
        if (existingDiningReview.isPresent()) {
            diningReviewRepository.deleteById(id);
            return existingDiningReview.get();
        } else {
            return null;
        }
    }

}
