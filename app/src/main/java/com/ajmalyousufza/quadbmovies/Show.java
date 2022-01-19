package com.ajmalyousufza.quadbmovies;

public class Show {

    String name;
    String summary;
    image image;
    schedule schedule;
    rating rating;
    String language;

    public Show(String name, String summary, com.ajmalyousufza.quadbmovies.image image, com.ajmalyousufza.quadbmovies.schedule schedule, com.ajmalyousufza.quadbmovies.rating rating, String language) {
        this.name = name;
        this.summary = summary;
        this.image = image;
        this.schedule = schedule;
        this.rating = rating;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public com.ajmalyousufza.quadbmovies.image getImage() {
        return image;
    }

    public void setImage(com.ajmalyousufza.quadbmovies.image image) {
        this.image = image;
    }

    public com.ajmalyousufza.quadbmovies.schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(com.ajmalyousufza.quadbmovies.schedule schedule) {
        this.schedule = schedule;
    }

    public com.ajmalyousufza.quadbmovies.rating getRating() {
        return rating;
    }

    public void setRating(com.ajmalyousufza.quadbmovies.rating rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
