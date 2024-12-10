
package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketdadungMoviesEntity {

    private String date_dadung; // Ngày giờ
    private String poster_dadung; // Resource ID cho poster
    private int age_dadung; // Độ tuổi
    private String name_dadung; // Tên phim
    private String style_dadung; // Thể loại phim
    private int soluong_dadung;
    private String anhrap_dadung;// Số lượng vé// Số lượng vé
    private String diachi_dadung; // Địa chỉ


    public ticketdadungMoviesEntity()
    {}
    // Constructor với tất cả các tham số
    public ticketdadungMoviesEntity(String date_dadung, String poster_dadung, int age_dadung, String name_dadung, String style_dadung, int soluong_dadung,String anhrap_dadung, String diachi_dadung) {
        this.date_dadung = date_dadung;
        this.poster_dadung = poster_dadung;
        this.age_dadung = age_dadung;
        this.name_dadung = name_dadung;
        this.style_dadung = style_dadung;
        this.soluong_dadung = soluong_dadung;
        this.anhrap_dadung=anhrap_dadung;
        this.diachi_dadung = diachi_dadung;
    }

    // Getter và Setter cho tất cả các trường
    public String getDate_dadung() {
        return date_dadung;
    }

    public void setDate_dadung(String date_dadung) {
        this.date_dadung = date_dadung;
    }


    public String getPoster_dadung() {
        return poster_dadung;
    }

    public void setPoster_dadung(String poster_dadung) {
        this.poster_dadung = poster_dadung;
    }

    public int getAge_dadung() {
        return age_dadung;
    }

    public void setAge_dadung(int age_dadung) {
        this.age_dadung = age_dadung;
    }

    public String getName_dadung() {
        return name_dadung;
    }

    public void setName_dadung(String name_dadung) {
        this.name_dadung = name_dadung;
    }

    public String getStyle_dadung() {
        return style_dadung;
    }

    public void setStyle_dadung(String style_dadung) {
        this.style_dadung = style_dadung;
    }

    public int getSoluong_dadung() {
        return soluong_dadung;
    }

    public void setSoluong_dadung(int soluong_dadung) {
        this.soluong_dadung = soluong_dadung;
    }

    public String getAnhrap_dadung () {
        return anhrap_dadung;
    }

    public void setAnhrap_dadung(String anhrap_dadung) {
        this.anhrap_dadung = anhrap_dadung;
    }

    public String getDiachi_dadung() {
        return diachi_dadung;
    }

    public void setDiachi_dadung(String diachi_dadung) {
        this.diachi_dadung = diachi_dadung;
    }
}
