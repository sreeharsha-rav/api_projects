package com.api.diningreview.dto;

import lombok.Data;

/*
 * This class represents a user data transfer object.
 * displayName - the user's display name
 * city - the user's city
 * state - the user's state
 * zipCode - the user's zip code
 * hasPeanutAllergy - whether the user has a peanut allergy
 * hasEggAllergy - whether the user has an egg allergy
 * hasDairyAllergy - whether the user has a dairy allergy
 */
@Data
public class UserDTO {
    private String displayName;
    private String city;
    private String state;
    private String zipCode;
    private boolean hasPeanutAllergy;
    private boolean hasEggAllergy;
    private boolean hasDairyAllergy;
}
