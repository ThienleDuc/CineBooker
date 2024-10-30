package com.example.cinebooker.LeDucThien.entity;

public class timDiaChiRapChieuEntity {
    private int cinemalog0;
    private String location, name, map;

    public timDiaChiRapChieuEntity() {
    }

    public timDiaChiRapChieuEntity(int cinemalog0, String name, String location, String map) {
        this.cinemalog0 = cinemalog0;
        this.location = location;
        this.name = name;
        this.map = map;
    }

    public int getCinemalog0() {
        return cinemalog0;
    }

    public void setCinemalog0(int cinemalog0) {
        this.cinemalog0 = cinemalog0;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}

