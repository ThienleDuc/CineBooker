package com.example.cinebooker.PhanCongQuoc.adapter.entity;

public class caroselDangChieuEntity {
    private int moviePoster;
    private String age;
    private Double vote;
    private String movieName, styleMovie;

    public caroselDangChieuEntity() {
    }

    public caroselDangChieuEntity(int moviePoster, String age, Double vote, String movieName, String styleMovie) {
        this.moviePoster = moviePoster;
        this.age = age;
        this.vote = vote;
        this.movieName = movieName;
        this.styleMovie = styleMovie;
    }

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
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
}
