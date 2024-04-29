package com.api.diningreview.dto;

import lombok.Data;

/*
 * This class represents a dining review data transfer object.
 * userName - the user's name
 * restaurantId - the restaurant's ID
 * peanutScore - score for peanut allergies from 1 to 5
 * eggScore - score for egg allergies from 1 to 5
 * dairyScore - score for dairy allergies from 1 to 5
 * commentary - the user's commentary
 */
@Data
public class DiningReviewDTO {
    private String userName;
    private Long restaurantId;
    private int peanutScore;
    private int eggScore;
    private int dairyScore;
    private String commentary;
}
