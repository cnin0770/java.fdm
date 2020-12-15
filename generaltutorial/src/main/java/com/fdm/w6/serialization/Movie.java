package com.fdm.w6.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Movie {
    private String title;
    @JsonIgnore
    private int rating;
    private Director director;

    public Movie() {}

    public Movie(String title, int rating, Director director) {
        super();
        this.title = title;
        this.rating = rating;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
