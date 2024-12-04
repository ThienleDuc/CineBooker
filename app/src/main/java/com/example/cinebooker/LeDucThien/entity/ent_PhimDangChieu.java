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
    private Date ngayKhoiChieu;
    private Date ngayKetThuc;
    private String trangThaiChieu;
    private String thoiLuong;
    private float diemDanhGiaTrungBinh;


    public ent_PhimDangChieu() {
    }

    public ent_PhimDangChieu(int maPhim, String anhPhim, String tenPhim, int tuoi, String dinhDangPhim, String tenTheLoai, Date ngayKhoiChieu, Date ngayKetThuc, String trangThaiChieu, String thoiLuong, float diemDanhGiaTrungBinh) {
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
        return "R.drawable." + anhPhim;
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

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getFormattedNgayKhoiChieu() {
        if (ngayKhoiChieu != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(ngayKhoiChieu);
        }
        return null;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getFormattedNgayKetThuc() {
        if (ngayKetThuc != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(ngayKetThuc);
        }
        return null;
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
