package com.example.cinebooker.LeDucThien.entity;

public class ent_NgayChieu {
    private int maThoiGianChieu;
    private String kieuNgay;
    private String ngayChieu;

    // Constructor mặc định
    public ent_NgayChieu() {
    }

    // Constructor có tham số
    public ent_NgayChieu(int maThoiGianChieu, String kieuNgay, String ngayChieu) {
        this.maThoiGianChieu = maThoiGianChieu;
        this.kieuNgay = kieuNgay;
        this.ngayChieu = ngayChieu;
    }

    // Getter và Setter cho KieuNgay
    public String getKieuNgay() {
        return kieuNgay;
    }

    public void setKieuNgay(String kieuNgay) {
        this.kieuNgay = kieuNgay;
    }

    // Getter và Setter cho NgayChieu
    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public int getMaThoiGianChieu() {
        return maThoiGianChieu;
    }

    public void setMaThoiGianChieu(int maThoiGianChieu) {
        this.maThoiGianChieu = maThoiGianChieu;
    }
}
