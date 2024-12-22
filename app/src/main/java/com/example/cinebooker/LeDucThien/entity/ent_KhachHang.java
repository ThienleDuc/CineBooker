package com.example.cinebooker.LeDucThien.entity;

public class ent_KhachHang {
    private int maKhachHang;
    private String email;
    private String matKhau; // Should reflect proper length limit on DB side
    private String anhKhachHang; // Path or URL to the image
    private String tenKhachHang;
    private String lopHocPhan;
    private String maSinhVien;

    // Constructor không tham số
    public ent_KhachHang() {
    }

    // Constructor với tham số
    public ent_KhachHang(int maKhachHang, String email, String matKhau, String anhKhachHang,
                         String tenKhachHang, String lopHocPhan, String maSinhVien) {
        this.maKhachHang = maKhachHang;
        this.email = email;
        this.matKhau = matKhau;
        this.anhKhachHang = anhKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.lopHocPhan = lopHocPhan;
        this.maSinhVien = maSinhVien;
    }

    // Getter và Setter cho MaKhachHang
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    // Getter và Setter cho Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho MatKhau
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    // Getter và Setter cho AnhKhachHang
    public String getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(String anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }

    // Getter và Setter cho TenKhachHang
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    // Getter và Setter cho LopHocPhan
    public String getLopHocPhan() {
        return lopHocPhan;
    }

    public void setLopHocPhan(String lopHocPhan) {
        this.lopHocPhan = lopHocPhan;
    }

    // Getter và Setter cho MaSinhVien
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
}
