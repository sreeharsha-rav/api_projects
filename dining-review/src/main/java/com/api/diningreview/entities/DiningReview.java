package com.api.diningreview.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class represents a dining review entity.
 * id - unique identifier for each dining review
 * userSubmit - the user who submitted the review
 * restaurant - the restaurant being reviewed
 * peanutScore - score for peanut allergies from 1 to 5
 * eggScore - score for egg allergies from 1 to 5
 * dairyScore - score for dairy allergies from 1 to 5
 * commentary - additional comments or notes about the review
 * reviewStatus - status of the review (PENDING, ACCEPTED, REJECTED), default is PENDING
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dining_reviews")
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Column(name = "user_id")
    private User userSubmit;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "peanut_score", nullable = true)
    private Integer peanutScore;
    @Column(name = "egg_score", nullable = true)
    private Integer eggScore;
    @Column(name = "dairy_score", nullable = true)
    private Integer dairyScore;
    @Column(name = "commentary", nullable = true)
    private String commentary;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatus reviewStatus = ReviewStatus.PENDING;

}
