package com.example.cinebooker.LeDucThien.entity;

import java.util.List;

public class xepHangEntity {
    private int moviePoster;
    private String age;
    private Double vote, shoping, comment;
    private String movieName, styleMovie;
    public xepHangEntity() {
    }

    public xepHangEntity(int moviePoster, String age, Double vote, Double shoping, Double comment, String movieName, String styleMovie) {
        this.moviePoster = moviePoster;
        this.age = age;
        this.vote = vote;
        this.shoping = shoping;
        this.comment = comment;
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

    public Double getShoping() {
        return shoping;
    }

    public void setShoping(Double shoping) {
        this.shoping = shoping;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }
}
