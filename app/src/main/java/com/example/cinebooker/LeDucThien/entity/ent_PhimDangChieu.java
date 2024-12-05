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

    // Getter và Setter
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

    public String getFormattedNgayKhoiChieu() {
        if (ngayKhoiChieu != null) {
            // Định dạng lại ngày khởi chiếu theo định dạng dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayKhoiChieu);
                return sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
                return ngayKhoiChieu; // Trả về giá trị gốc nếu gặp lỗi
            }
        }
        return null;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getFormattedNgayKetThuc() {
        if (ngayKetThuc != null) {
            // Định dạng lại ngày kết thúc theo định dạng dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayKetThuc);
                return sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
                return ngayKetThuc; // Trả về giá trị gốc nếu gặp lỗi
            }
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

    public String getFormattedThoiLuong() {
        if (thoiLuong != null) {
            // Định dạng lại thời gian theo định dạng HH:mm
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                Date time = new SimpleDateFormat("HH:mm:ss").parse(thoiLuong);
                return sdf.format(time);
            } catch (Exception ex) {
                ex.printStackTrace();
                return thoiLuong; // Trả về giá trị gốc nếu gặp lỗi
            }
        }
        return null;
    }

    public float getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public void setDiemDanhGiaTrungBinh(float diemDanhGiaTrungBinh) {
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }
}
