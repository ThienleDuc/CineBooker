package com.example.cinebooker.LeDucThien.entity;

public class binhLuanNoiBatEntity {
    private int moviePoster, avatar;
    private String age, userName;
    private Double vote, shopping, comment;
    private String movieName, styleMovie, day, userComment;

    public binhLuanNoiBatEntity() {
    }

    public binhLuanNoiBatEntity(int moviePoster, String age, String movieName,  String styleMovie,  Double vote, Double shopping, Double comment, int avatar, String userName, String day, String userComment) {
        this.moviePoster = moviePoster;
        this.avatar = avatar;
        this.age = age;
        this.userName = userName;
        this.vote = vote;
        this.shopping = shopping;
        this.comment = comment;
        this.movieName = movieName;
        this.styleMovie = styleMovie;
        this.day = day;
        this.userComment = userComment;
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public Double getShopping() {
        return shopping;
    }

    public void setShopping(Double shopping) {
        this.shopping = shopping;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
