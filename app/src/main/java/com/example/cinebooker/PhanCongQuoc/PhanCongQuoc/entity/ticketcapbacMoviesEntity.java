package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.entity;

public class ticketcapbacMoviesEntity {

    private String namedonvi_capbac; // Tên đơn vị
    private String icondonvi_capbac; // Resource ID cho icon
    private String mucgiam_capbac; // Nội dung giảm giá
    private String gioihangiam_capbac;
    private String dadung_capbac;// Giới hạn giảm giá
    private String date_capbac; // Hạn sử dụng
     // Đã dùng
     public ticketcapbacMoviesEntity()  {}
    // Construtor với tất cả các tham số (không có btn_capbac)
    public ticketcapbacMoviesEntity(String namedonvi_capbac, String icondonvi_capbac, String mucgiam_capbac,
                                    String gioihangiam_capbac,String dadung_capbac, String date_capbac) {
        this.namedonvi_capbac = namedonvi_capbac;
        this.icondonvi_capbac = icondonvi_capbac;
        this.mucgiam_capbac = mucgiam_capbac;
        this.gioihangiam_capbac = gioihangiam_capbac;
        this.dadung_capbac=dadung_capbac;
        this.date_capbac = date_capbac;

    }

    // Getter và Setter

    public String getNamedonvi_capbac() {
        return namedonvi_capbac;
    }

    public void setNamedonvi_capbac(String namedonvi_capbac) {
        this.namedonvi_capbac = namedonvi_capbac;
    }

    public String getIcondonvi_capbac() {
        return icondonvi_capbac;
    }

    public void setIcondonvi_capbac(String icondonvi_capbac) {
        this.icondonvi_capbac = icondonvi_capbac;
    }

    public String getMucgiam_capbac() {
        return mucgiam_capbac;
    }

    public void setMucgiam_capbac(String mucgiam_capbac) {
        this.mucgiam_capbac = mucgiam_capbac;
    }

    public String getGioihangiam_capbac() {
        return gioihangiam_capbac;
    }

    public void setGioihangiam_capbac(String gioihangiam_capbac) {
        this.gioihangiam_capbac = gioihangiam_capbac;
    }
    public String getDadung_capbac() {
        return dadung_capbac;
    }

    public void setDadung_capbac(String dadung_capbac) {
        this.dadung_capbac = dadung_capbac;
    }

    public String getDate_capbac() {
        return date_capbac;
    }

    public void setDate_capbac(String date_capbac) {
        this.date_capbac = date_capbac;
    }

    
}
