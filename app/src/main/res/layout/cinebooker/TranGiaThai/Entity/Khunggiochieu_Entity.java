package com.example.cinebooker.TranGiaThai.Entity;

public class Khunggiochieu_Entity
{       private String giochieu;
        private String chotrong;

    public Khunggiochieu_Entity(String giochieu, String chotrong) {
        this.giochieu = giochieu;
        this.chotrong = chotrong;
    }

    public String getGiochieu() {
        return giochieu;
    }

    public void setGiochieu(String giochieu) {
        this.giochieu = giochieu;
    }

    public String getChotrong() {
        return chotrong;
    }

    public void setChotrong(String chotrong) {
        this.chotrong = chotrong;
    }
}
