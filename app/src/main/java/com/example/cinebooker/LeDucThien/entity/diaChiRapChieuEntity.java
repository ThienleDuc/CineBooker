package com.example.cinebooker.LeDucThien.entity;

public class diaChiRapChieuEntity {
    private int cinemalog0;
    private String loacation;

    public diaChiRapChieuEntity() {
    }

    public diaChiRapChieuEntity(int cinemalog0, String name) {
        this.cinemalog0 = cinemalog0;
        this.loacation = name;
    }

    public int getCinemalog0() {
        return cinemalog0;
    }

    public void setCinemalog0(int cinemalog0) {
        this.cinemalog0 = cinemalog0;
    }

    public String getName() {
        return loacation;
    }

    public void setName(String name) {
        this.loacation = name;
    }
}

