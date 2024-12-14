package com.example.cinebooker.LeDucThien.entity;

public class ent_RapChieuCon {
    private int MaRapChieuCon;
    private String AnhRapChieu;
    private String TenRapChieuCon;
    private String DiaChiRapChieu;
    private String map;

    public ent_RapChieuCon() {
    }

    public ent_RapChieuCon(int maRapChieuCon, String anhRapChieu, String tenRapChieuCon, String diaChiRapChieu, String map) {
        MaRapChieuCon = maRapChieuCon;
        AnhRapChieu = anhRapChieu;
        TenRapChieuCon = tenRapChieuCon;
        DiaChiRapChieu = diaChiRapChieu;
        this.map = map;
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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
