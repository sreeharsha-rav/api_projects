package com.api.diningreview.dto;

import lombok.Data;

/*
 * This class represents a restaurant data transfer object.
 * name - the restaurant's name
 * address - the restaurant's address
 * zipCode - the restaurant's zip code
 * peanutScore - score for peanut allergies from 1 to 5
 * eggScore - score for egg allergies from 1 to 5
 * dairyScore - score for dairy allergies from 1 to 5
 */
@Data
public class RestaurantDTO {
    private String name;
    private String address;
    private String zipCode;
    private int peanutScore;
    private int eggScore;
    private int dairyScore;
}
