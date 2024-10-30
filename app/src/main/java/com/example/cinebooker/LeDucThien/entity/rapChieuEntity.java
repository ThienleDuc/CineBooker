package com.example.cinebooker.LeDucThien.entity;

public class rapChieuEntity {
    private int cinemalog0;
    private String name;

    public rapChieuEntity() {
    }

    public rapChieuEntity(int cinemalog0, String name) {
        this.cinemalog0 = cinemalog0;
        this.name = name;
    }

    public int getCinemalog0() {
        return cinemalog0;
    }

    public void setCinemalog0(int cinemalog0) {
        this.cinemalog0 = cinemalog0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
