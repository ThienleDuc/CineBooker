package com.example.cinebooker.LeDucThien.entity;

public class ent_TimKiemPhim {
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
    private int tongLuotMuaPhim;
    private int tongLuotDanhGiaPhim;

    public ent_TimKiemPhim() {
    }

    public ent_TimKiemPhim(int maPhim, String anhPhim, String tenPhim, int tuoi, String dinhDangPhim, String tenTheLoai, String ngayKhoiChieu,
                           String ngayKetThuc, String trangThaiChieu, String thoiLuong,
                           float diemDanhGiaTrungBinh, int tongLuotMuaPhim, int tongLuotDanhGiaPhim) {
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
        this.tongLuotMuaPhim = tongLuotMuaPhim;
        this.tongLuotDanhGiaPhim = tongLuotDanhGiaPhim;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public String getAnhPhim() {
        return anhPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getDinhDangPhim() {
        return dinhDangPhim;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public String getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public String getTrangThaiChieu() {
        return trangThaiChieu;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public float getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public int getTongLuotMuaPhim() {
        return tongLuotMuaPhim;
    }

    public int getTongLuotDanhGiaPhim() {
        return tongLuotDanhGiaPhim;
    }
}
