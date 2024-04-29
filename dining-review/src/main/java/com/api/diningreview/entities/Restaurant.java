package com.api.diningreview.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Restaurant entity
 * id - unique identifier for each restaurant
 * name - name of the restaurant
 * address - address of the restaurant
 * zipCode - zip code of the restaurant
 * phone - phone number of the restaurant
 * peanutScore - score for peanut allergies from 1 to 5
 * eggScore - score for egg allergies from 1 to 5
 * dairyScore - score for dairy allergies from 1 to 5
 * overallScore - average of peanutScore, eggScore, and dairyScore
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "phone")
    private String phone;
    @Column(name = "peanut_score", nullable = true)
    private double peanutScore;
    @Column(name = "egg_score", nullable = true)
    private double eggScore;
    @Column(name = "dairy_score", nullable = true)
    private double dairyScore;
    @Column(name = "overall_score", nullable = true)
    private Double overallScore;

}
