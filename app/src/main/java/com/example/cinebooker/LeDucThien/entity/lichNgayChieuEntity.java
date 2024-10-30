package com.example.cinebooker.LeDucThien.entity;

public class lichNgayChieuEntity {
    private int day;
    private String dayName;

    public lichNgayChieuEntity() {
    }

    public lichNgayChieuEntity(int day, String dayName) {
        this.day = day;
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
