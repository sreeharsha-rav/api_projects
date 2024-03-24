package com.api.traveladventures.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * The Adventure entity class is used to represent the adventure entity in the database.
 * The adventure entity has the following attributes:
 * - id: the unique identifier of the adventure.
 * - date: the date of the adventure.
 * - country: the country of the adventure.
 * - city: the city of the adventure.
 * - state: the state of the adventure.
 * - numPhotos: the number of photos taken during the adventure.
 * - blogCompleted: a flag indicating whether the blog for the adventure has been completed.
 */
@Entity
@Table(name = "ADVENTURES")
public class Adventure {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "NUM_PHOTOS")
    private Long numPhotos;

    @Column(name = "BLOG_COMPLETED")
    private Boolean blogCompleted;

    public Adventure() {
    }

    public Adventure(String date, String country, String city, String state, Long numPhotos, Boolean blogCompleted) {
        this.date = date;
        this.country = country;
        this.city = city;
        this.state = state;
        this.numPhotos = numPhotos;
        this.blogCompleted = blogCompleted;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getNumPhotos() {
        return this.numPhotos;
    }

    public void setNumPhotos(Long numPhotos) {
        this.numPhotos = numPhotos;
    }

    public Boolean getBlogCompleted() {
        return this.blogCompleted;
    }

    public void setBlogCompleted(Boolean blogCompleted) {
        this.blogCompleted = blogCompleted;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", numPhotos=" + numPhotos +
                ", blogCompleted=" + blogCompleted +
                '}';
    }

}
