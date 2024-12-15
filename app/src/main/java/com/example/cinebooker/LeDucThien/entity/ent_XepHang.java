package com.example.cinebooker.LeDucThien.entity;

public class ent_XepHang {

    private int maPhim;
    private String anhPhim;
    private String tenPhim;
    private int tuoi;
    private String dinhDangPhim;
    private String tenTheLoai;
    private float diemDanhGiaTrungBinh;
    private int tongLuotMuaPhim;
    private int tongDanhGiaPhim;
    private float diemXepHang;

    // Constructor mặc định
    public ent_XepHang() {}

    // Constructor đầy đủ
    public ent_XepHang(int maPhim, String anhPhim, String tenPhim, int tuoi, String dinhDangPhim,
                           String tenTheLoai, float diemDanhGiaTrungBinh, int tongLuotMuaPhim,
                           int tongDanhGiaPhim, float diemXepHang) {
        this.maPhim = maPhim;
        this.anhPhim = anhPhim;
        this.tenPhim = tenPhim;
        this.tuoi = tuoi;
        this.dinhDangPhim = dinhDangPhim;
        this.tenTheLoai = tenTheLoai;
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
        this.tongLuotMuaPhim = tongLuotMuaPhim;
        this.tongDanhGiaPhim = tongDanhGiaPhim;
        this.diemXepHang = diemXepHang;
    }

    // Getters and Setters
    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getDinhDangPhim() {
        return dinhDangPhim;
    }

    public void setDinhDangPhim(String dinhDangPhim) {
        this.dinhDangPhim = dinhDangPhim;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public float getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public void setDiemDanhGiaTrungBinh(float diemDanhGiaTrungBinh) {
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }

    public int getTongLuotMuaPhim() {
        return tongLuotMuaPhim;
    }

    public void setTongLuotMuaPhim(int tongLuotMuaPhim) {
        this.tongLuotMuaPhim = tongLuotMuaPhim;
    }

    public int getTongDanhGiaPhim() {
        return tongDanhGiaPhim;
    }

    public void setTongDanhGiaPhim(int tongDanhGiaPhim) {
        this.tongDanhGiaPhim = tongDanhGiaPhim;
    }

    public float getDiemXepHang() {
        return diemXepHang;
    }

    public void setDiemXepHang(float diemXepHang) {
        this.diemXepHang = diemXepHang;
    }
}
