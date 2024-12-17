package com.example.cinebooker.LeDucThien.entity;

public class ent_ThoiGianChieu {
    private int maPhim;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;

    // Constructor mặc định
    public ent_ThoiGianChieu() {
    }

    // Constructor với tham số
    public ent_ThoiGianChieu(int maPhim, String thoiGianBatDau, String thoiGianKetThuc) {
        this.maPhim = maPhim;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    // Getter và Setter
    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

}
