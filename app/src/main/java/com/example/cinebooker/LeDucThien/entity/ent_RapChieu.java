package com.example.cinebooker.LeDucThien.entity;

public class ent_RapChieu {
    private int maRapChieu;
    private String anhRapChieu;
    private String tenRapChieu;
    private String moTaRapChieu;

    // Constructor không tham số
    public ent_RapChieu() {
    }

    // Constructor với tham số
    public ent_RapChieu(int maRapChieu, String anhRapChieu, String tenRapChieu, String moTaRapChieu) {
        this.maRapChieu = maRapChieu;
        this.anhRapChieu = anhRapChieu;
        this.tenRapChieu = tenRapChieu;
        this.moTaRapChieu = moTaRapChieu;
    }

    // Getter và Setter cho MaRapChieu
    public int getMaRapChieu() {
        return maRapChieu;
    }

    public void setMaRapChieu(int maRapChieu) {
        this.maRapChieu = maRapChieu;
    }

    // Getter và Setter cho AnhRapChieu
    public String getAnhRapChieu() {
        return anhRapChieu;
    }

    public void setAnhRapChieu(String anhRapChieu) {
        this.anhRapChieu = anhRapChieu;
    }

    // Getter và Setter cho TenRapChieu
    public String getTenRapChieu() {
        return tenRapChieu;
    }

    public void setTenRapChieu(String tenRapChieu) {
        this.tenRapChieu = tenRapChieu;
    }

    // Getter và Setter cho MoTaRapChieu
    public String getMoTaRapChieu() {
        return moTaRapChieu;
    }

    public void setMoTaRapChieu(String moTaRapChieu) {
        this.moTaRapChieu = moTaRapChieu;
    }
}
