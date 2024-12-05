package com.example.cinebooker.LeDucThien.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ent_PhimDangChieu {

    private int maPhim;
    private String anhPhim;
    private String tenPhim;
    private int tuoi;
    private String dinhDangPhim;
    private String tenTheLoai;
    private String ngayKhoiChieu;  // Ngày khởi chiếu dưới dạng String
    private String ngayKetThuc;    // Ngày kết thúc dưới dạng String
    private String trangThaiChieu;
    private String thoiLuong;      // Thời lượng dưới dạng String (ví dụ: "02:30")
    private float diemDanhGiaTrungBinh;

    // Constructor mặc định
    public ent_PhimDangChieu() {
    }

    // Constructor đầy đủ
    public ent_PhimDangChieu(int maPhim, String anhPhim, String tenPhim, int tuoi, String dinhDangPhim,
                             String tenTheLoai, String ngayKhoiChieu, String ngayKetThuc, String trangThaiChieu,
                             String thoiLuong, float diemDanhGiaTrungBinh) {
        this.maPhim = maPhim;
        this.anhPhim = anhPhim;
        this.tenPhim = tenPhim;
        this.tuoi = tuoi;
        this.dinhDangPhim = dinhDangPhim;
        this.tenTheLoai = tenTheLoai;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThaiChieu = trangThaiChieu;
        this.thoiLuong = thoiLuong;
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }

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

    public String getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(String ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThaiChieu() {
        return trangThaiChieu;
    }

    public void setTrangThaiChieu(String trangThaiChieu) {
        this.trangThaiChieu = trangThaiChieu;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public float getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public void setDiemDanhGiaTrungBinh(float diemDanhGiaTrungBinh) {
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }
}
