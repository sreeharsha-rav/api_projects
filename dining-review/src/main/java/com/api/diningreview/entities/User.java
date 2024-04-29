package com.api.diningreview.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class represents a user entity.
 * id - unique identifier for each user
 * displayName - the user's display name
 * city - the user's city
 * state - the user's state
 * zipCode - the user's zip code
 * hasPeanutAllergy - whether the user has a peanut allergy
 * hasEggAllergy - whether the user has an egg allergy
 * hasDairyAllergy - whether the user has a dairy allergy
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "display_name", unique = true)
    private String displayName;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "has_peanut_allergy")
    private boolean hasPeanutAllergy;
    @Column(name = "has_egg_allergy")
    private boolean hasEggAllergy;
    @Column(name = "has_dairy_allergy")
    private boolean hasDairyAllergy;
}
