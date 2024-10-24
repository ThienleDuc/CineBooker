package com.example.cinebooker.LeDucThien.entity;

import java.util.List;

public class heThongRapChieuEntity {
    private int moviePoster;
    private String ten, moTa;
    private Double vote,location, comment;

    public heThongRapChieuEntity() {
    }

    public heThongRapChieuEntity(int moviePoster, String ten, String moTa, Double vote, Double location, Double comment) {
        this.moviePoster = moviePoster;
        this.ten = ten;
        this.moTa = moTa;
        this.vote = vote;
        this.location = location;
        this.comment = comment;
    }

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }
}
