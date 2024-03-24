package com.api.traveladventures.entities;

public class AdventureRequest {
    private String date;
    private String country;
    private String city;
    private String state;
    private Long numPhotos;
    private Boolean blogCompleted;

    public AdventureRequest() {
    }

    public AdventureRequest(String date, String country, String city, String state, Long numPhotos, Boolean blogCompleted) {
        this.date = date;
        this.country = country;
        this.city = city;
        this.state = state;
        this.numPhotos = numPhotos;
        this.blogCompleted = blogCompleted;
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
        return "AdventureRequest{" +
                "date='" + date + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", numPhotos=" + numPhotos +
                ", blogCompleted=" + blogCompleted +
                '}';
    }
}
