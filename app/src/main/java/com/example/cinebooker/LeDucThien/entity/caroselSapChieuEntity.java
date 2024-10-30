package com.example.cinebooker.LeDucThien.entity;

public class caroselSapChieuEntity {
    private int moviePoster;
    private String age;
    private String movieName, styleMovie;

    public caroselSapChieuEntity() {
    }

    public caroselSapChieuEntity(int moviePoster, String age, String movieName, String styleMovie) {
        this.moviePoster = moviePoster;
        this.age = age;
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
