package com.api.diningreview.repositories;

import com.api.diningreview.entities.DiningReview;
import com.api.diningreview.entities.Restaurant;
import com.api.diningreview.entities.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repository for DiningReview entity to perform CRUD operations and custom queries
 * findBAllByRestaurantId - find dining reviews by restaurant id
 * findByReviewStatus - find dining reviews by review status
 * findByRestaurantAndReviewStatus - find dining reviews by restaurant and review status
 */
@Repository
public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {
    List<DiningReview> findBAllByRestaurantId(Long restaurantId);
    List<DiningReview> findByReviewStatus(ReviewStatus reviewStatus);
    List<DiningReview> findByRestaurantAndReviewStatus(Restaurant restaurant, ReviewStatus reviewStatus);
}
