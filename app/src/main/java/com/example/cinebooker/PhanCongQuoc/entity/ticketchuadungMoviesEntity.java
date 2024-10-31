package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketchuadungMoviesEntity {

    private String date_chuadung; // Ngày giờ
    private int poster_chuadung; // Resource ID cho poster
    private String age_chuadung; // Độ tuổi
    private String name_chuadung; // Tên phim
    private String style_chuadung; // Thể loại phim
    private int soluong_chuadung; // Số lượng vé
    private String diachi_chuadung; // Địa chỉ
    ; // Văn bản của nút xuất vé




    // Constructor với tất cả các tham số
    public ticketchuadungMoviesEntity( String date_chuadung, int poster_chuadung, String age_chuadung, String name_chuadung, String style_chuadung, int soluong_chuadung, String diachi_chuadung) {

        this.date_chuadung = date_chuadung;
        this.poster_chuadung = poster_chuadung;
        this.age_chuadung = age_chuadung;
        this.name_chuadung = name_chuadung;
        this.style_chuadung = style_chuadung;
        this.soluong_chuadung = soluong_chuadung;
        this.diachi_chuadung = diachi_chuadung;

    }

    // Getter và Setter


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


}
