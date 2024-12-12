package com.example.cinebooker.LeDucThien.entity;

public class ent_RapChieuCon {
    private int MaRapChieuCon;
    private String AnhRapChieu;
    private String TenRapChieuCon;
    private String DiaChiRapChieu;

    public ent_RapChieuCon() {
    }

    public ent_RapChieuCon(int maRapChieuCon, String anhRapChieu, String tenRapChieuCon, String diaChiRapChieu) {
        this.MaRapChieuCon = maRapChieuCon;
        this.AnhRapChieu = anhRapChieu;
        this.TenRapChieuCon = tenRapChieuCon;
        this.DiaChiRapChieu = diaChiRapChieu;
    }

    public int getMaRapChieuCon() {
        return MaRapChieuCon;
    }

    public void setMaRapChieuCon(int maRapChieuCon) {
        MaRapChieuCon = maRapChieuCon;
    }

    public String getAnhRapChieu() {
        return AnhRapChieu;
    }

    public void setAnhRapChieu(String anhRapChieu) {
        this.AnhRapChieu = anhRapChieu;
    }

    public String getTenRapChieuCon() {
        return TenRapChieuCon;
    }

    public void setTenRapChieuCon(String tenRapChieuCon) {
        this.TenRapChieuCon = tenRapChieuCon;
    }

    public String getDiaChiRapChieu() {
        return DiaChiRapChieu;
    }

    public void setDiaChiRapChieu(String diaChiRapChieu) {
        this.DiaChiRapChieu = diaChiRapChieu;
    }
}
