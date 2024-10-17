package com.example.cinebooker.PhanCongQuoc.entity;

public class searchMoviesEntity {
    private int posster_movies; // Lưu trữ resource ID cho ảnh
    private String age, movieName, styleMovie;
    private Double vote, purchases, comment;

    public searchMoviesEntity() {
    }

    public searchMoviesEntity(int posster_movies, String age, String movieName, String styleMovie, Double vote, Double purchases, Double comment) {
        this.posster_movies = posster_movies;
        this.age = age;
        this.movieName = movieName;
        this.styleMovie = styleMovie;
        this.vote = vote;
        this.purchases = purchases;
        this.comment = comment;
    }

    public int getPosster_movies() {
        return posster_movies;
    }

    public void setPosster_movies(int posster_movies) {
        this.posster_movies = posster_movies;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getStyleMovie() {
        return styleMovie;
    }

    public void setStyleMovie(String styleMovie) {
        this.styleMovie = styleMovie;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public Double getPurchases() {
        return purchases;
    }

    public void setPurchases(Double purchases) {
        this.purchases = purchases;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }
}
