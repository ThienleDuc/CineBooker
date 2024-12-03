package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketvoucherMoviesEntity {

    private String namedonvi_voucher; // Tên đơn vị
    private int icondonvi_voucher; // Resource ID cho icon
    private String mucgiam_voucher; // Nội dung giảm giá
    private String gioihangiam_voucher; // Giới hạn giảm giá
    private String date_voucher; // Hạn sử dụng
    private String dadung_voucher; // Đã dùng

    // Constructor với tất cả các tham số (không có btn_voucher)
    public ticketvoucherMoviesEntity(String namedonvi_voucher, int icondonvi_voucher, String mucgiam_voucher,
                                     String gioihangiam_voucher, String date_voucher, String dadung_voucher) {
        this.namedonvi_voucher = namedonvi_voucher;
        this.icondonvi_voucher = icondonvi_voucher;
        this.mucgiam_voucher = mucgiam_voucher;
        this.gioihangiam_voucher = gioihangiam_voucher;
        this.date_voucher = date_voucher;
        this.dadung_voucher = dadung_voucher;
    }

    // Getter và Setter

    public String getNamedonvi_voucher() {
        return namedonvi_voucher;
    }

    public void setNamedonvi_voucher(String namedonvi_voucher) {
        this.namedonvi_voucher = namedonvi_voucher;
    }

    public int getIcondonvi_voucher() {
        return icondonvi_voucher;
    }

    public void setIcondonvi_voucher(int icondonvi_voucher) {
        this.icondonvi_voucher = icondonvi_voucher;
    }

    public String getMucgiam_voucher() {
        return mucgiam_voucher;
    }

    public void setMucgiam_voucher(String mucgiam_voucher) {
        this.mucgiam_voucher = mucgiam_voucher;
    }

    public String getGioihangiam_voucher() {
        return gioihangiam_voucher;
    }

    public void setGioihangiam_voucher(String gioihangiam_voucher) {
        this.gioihangiam_voucher = gioihangiam_voucher;
    }

    public String getDate_voucher() {
        return date_voucher;
    }

    public void setDate_voucher(String date_voucher) {
        this.date_voucher = date_voucher;
    }

    public String getDadung_voucher() {
        return dadung_voucher;
    }

    public void setDadung_voucher(String dadung_voucher) {
        this.dadung_voucher = dadung_voucher;
    }
}