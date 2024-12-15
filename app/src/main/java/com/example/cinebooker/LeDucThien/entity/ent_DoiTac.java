package com.example.cinebooker.LeDucThien.entity;

public class ent_DoiTac {
    private String anhDoiTac;  // Image of the partner
    private String tenDoiTac;  // Name of the partner
    private String moTaDoiTac;  // Description of the partner
    private float diemDanhGiaDoiTacTrungBinh;  // Average rating of the partner
    private int tongLuotDanhGia;  // Total number of ratings
    private int tongDiaDiemDoiTac;  // Total number of locations for the partner

    // Constructor không tham số
    public ent_DoiTac() {
    }

    // Constructor với tham số
    public ent_DoiTac(String anhDoiTac, String tenDoiTac, String moTaDoiTac,
                      float diemDanhGiaDoiTacTrungBinh, int tongLuotDanhGia, int tongDiaDiemDoiTac) {
        this.anhDoiTac = anhDoiTac;
        this.tenDoiTac = tenDoiTac;
        this.moTaDoiTac = moTaDoiTac;
        this.diemDanhGiaDoiTacTrungBinh = diemDanhGiaDoiTacTrungBinh;
        this.tongLuotDanhGia = tongLuotDanhGia;
        this.tongDiaDiemDoiTac = tongDiaDiemDoiTac;
    }

    // Getter và Setter cho AnhDoiTac
    public String getAnhDoiTac() {
        return anhDoiTac;
    }

    public void setAnhDoiTac(String anhDoiTac) {
        this.anhDoiTac = anhDoiTac;
    }

    // Getter và Setter cho TenDoiTac
    public String getTenDoiTac() {
        return tenDoiTac;
    }

    public void setTenDoiTac(String tenDoiTac) {
        this.tenDoiTac = tenDoiTac;
    }

    // Getter và Setter cho MoTaDoiTac
    public String getMoTaDoiTac() {
        return moTaDoiTac;
    }

    public void setMoTaDoiTac(String moTaDoiTac) {
        this.moTaDoiTac = moTaDoiTac;
    }

    // Getter và Setter cho DiemDanhGiaDoiTacTrungBinh
    public float getDiemDanhGiaDoiTacTrungBinh() {
        return diemDanhGiaDoiTacTrungBinh;
    }

    public void setDiemDanhGiaDoiTacTrungBinh(float diemDanhGiaDoiTacTrungBinh) {
        this.diemDanhGiaDoiTacTrungBinh = diemDanhGiaDoiTacTrungBinh;
    }

    // Getter và Setter cho TongLuotDanhGia
    public int getTongLuotDanhGia() {
        return tongLuotDanhGia;
    }

    public void setTongLuotDanhGia(int tongLuotDanhGia) {
        this.tongLuotDanhGia = tongLuotDanhGia;
    }

    // Getter và Setter cho TongDiaDiemDoiTac
    public int getTongDiaDiemDoiTac() {
        return tongDiaDiemDoiTac;
    }

    public void setTongDiaDiemDoiTac(int tongDiaDiemDoiTac) {
        this.tongDiaDiemDoiTac = tongDiaDiemDoiTac;
    }
}
