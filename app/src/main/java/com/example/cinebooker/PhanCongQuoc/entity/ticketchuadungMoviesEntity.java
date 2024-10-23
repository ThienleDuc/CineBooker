package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketchuadungMoviesEntity {
    private int icon17_chuadung; // Resource ID cho icon17
    private String date_chuadung; // Ngày giờ
    private int poster_chuadung; // Resource ID cho poster
    private String age_chuadung; // Độ tuổi
    private String name_chuadung; // Tên phim
    private String style_chuadung; // Thể loại phim
    private int soluong_chuadung; // Số lượng vé
    private String diachi_chuadung; // Địa chỉ
    private String btn_chuadung_text; // Văn bản của nút xuất vé




    // Constructor với tất cả các tham số
    public ticketchuadungMoviesEntity(int icon17_chuadung, String date_chuadung, int poster_chuadung, String age_chuadung, String name_chuadung, String style_chuadung, int soluong_chuadung, String diachi_chuadung, String btn_chuadung_text) {
        this.icon17_chuadung = icon17_chuadung;
        this.date_chuadung = date_chuadung;
        this.poster_chuadung = poster_chuadung;
        this.age_chuadung = age_chuadung;
        this.name_chuadung = name_chuadung;
        this.style_chuadung = style_chuadung;
        this.soluong_chuadung = soluong_chuadung;
        this.diachi_chuadung = diachi_chuadung;
        this.btn_chuadung_text = btn_chuadung_text;
    }

    // Getter và Setter
    public int getIcon17_chuadung() {
        return icon17_chuadung;
    }

    public void setIcon17_chuadung(int icon17_chuadung) {
        this.icon17_chuadung = icon17_chuadung;
    }

    public String getDate_chuadung() {
        return date_chuadung;
    }

    public void setDate_chuadung(String date_chuadung) {
        this.date_chuadung = date_chuadung;
    }

    public int getPoster_chuadung() {
        return poster_chuadung;
    }

    public void setPoster_chuadung(int poster_chuadung) {
        this.poster_chuadung = poster_chuadung;
    }

    public String getAge_chuadung() {
        return age_chuadung;
    }

    public void setAge_chuadung(String age_chuadung) {
        this.age_chuadung = age_chuadung;
    }

    public String getName_chuadung() {
        return name_chuadung;
    }

    public void setName_chuadung(String name_chuadung) {
        this.name_chuadung = name_chuadung;
    }

    public String getStyle_chuadung() {
        return style_chuadung;
    }

    public void setStyle_chuadung(String style_chuadung) {
        this.style_chuadung = style_chuadung;
    }

    public int getSoluong_chuadung() {
        return soluong_chuadung;
    }

    public void setSoluong_chuadung(int soluong_chuadung) {
        this.soluong_chuadung = soluong_chuadung;
    }

    public String getDiachi_chuadung() {
        return diachi_chuadung;
    }

    public void setDiachi_chuadung(String diachi_chuadung) {
        this.diachi_chuadung = diachi_chuadung;
    }

    public String getBtn_chuadung_text() {
        return btn_chuadung_text;
    }

    public void setBtn_chuadung_text(String btn_chuadung_text) {
        this.btn_chuadung_text = btn_chuadung_text;
    }
}
