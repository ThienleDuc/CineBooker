package com.example.cinebooker.LeDucThien.entity;

public class ent_DanhGiaPhim {
    private int maPhim;
    private String anhPhim;
    private String tenPhim;
    private int tuoi;
    private String tenTheLoai;
    private float diemDanhGiaTrungBinh;
    private int tongLuotMua;
    private int tongLuotDanhGia;
    private String anhKhachHang;
    private String tenKhachHang;
    private String ngayDanhGia; // Thay đổi kiểu thành String
    private String danhGia;
    private int luotThich;

    // Constructor không tham số
    public ent_DanhGiaPhim() {
    }

    // Constructor với tham số
    public ent_DanhGiaPhim(int maPhim, String anhPhim, String tenPhim, int tuoi, String tenTheLoai,
                           float diemDanhGiaTrungBinh, int tongLuotMua, int tongLuotDanhGia,
                           String anhKhachHang, String tenKhachHang, String ngayDanhGia, String danhGia, int luotThich) {
        this.maPhim = maPhim;
        this.anhPhim = anhPhim;
        this.tenPhim = tenPhim;
        this.tuoi = tuoi;
        this.tenTheLoai = tenTheLoai;
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
        this.tongLuotMua = tongLuotMua;
        this.tongLuotDanhGia = tongLuotDanhGia;
        this.anhKhachHang = anhKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngayDanhGia = ngayDanhGia;
        this.danhGia = danhGia;
        this.luotThich = luotThich;
    }

    // Getter và Setter cho MaPhim
    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    // Getter và Setter cho AnhPhim
    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    // Getter và Setter cho TenPhim
    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    // Getter và Setter cho TenTheLoai
    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    // Getter và Setter cho DiemDanhGiaTrungBinh
    public float getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public void setDiemDanhGiaTrungBinh(float diemDanhGiaTrungBinh) {
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }

    // Getter và Setter cho TongLuotMua
    public int getTongLuotMua() {
        return tongLuotMua;
    }

    public void setTongLuotMua(int tongLuotMua) {
        this.tongLuotMua = tongLuotMua;
    }

    // Getter và Setter cho TongLuotDanhGia
    public int getTongLuotDanhGia() {
        return tongLuotDanhGia;
    }

    public void setTongLuotDanhGia(int tongLuotDanhGia) {
        this.tongLuotDanhGia = tongLuotDanhGia;
    }

    // Getter và Setter cho TenKhachHang
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    // Getter và Setter cho NgayDanhGia (kiểu String)
    public String getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    // Getter và Setter cho DanhGia
    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    // Getter và Setter cho LuotThich
    public int getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(int luotThich) {
        this.luotThich = luotThich;
    }

    // Getter và Setter cho Tuoi
    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    // Getter và Setter cho AnhKhachHang
    public String getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(String anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }
}
