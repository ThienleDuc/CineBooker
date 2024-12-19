
package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketchuadungMoviesEntity {
    private int MaVe;
    private String date_chuadung; // Ngày giờ
    private String poster_chuadung; // Resource ID cho poster
    private int age_chuadung; // Độ tuổi
    private String name_chuadung; // Tên phim
    private String style_chuadung; // Thể loại phim
    private int soluong_chuadung;
    private String anhrap;// Số lượng vé
    private String diachi_chuadung; // Địa chỉ
    ; // Văn bản của nút xuất vé



    public ticketchuadungMoviesEntity( )
    {}
        // Constructor với tất cả các tham số
    public ticketchuadungMoviesEntity( int MaVe,String date_chuadung, String poster_chuadung, int age_chuadung, String name_chuadung, String style_chuadung, int soluong_chuadung,String anhrap, String diachi_chuadung) {
        this.MaVe = MaVe;
        this.date_chuadung = date_chuadung;
        this.poster_chuadung = poster_chuadung;
        this.age_chuadung = age_chuadung;
        this.name_chuadung = name_chuadung;
        this.style_chuadung = style_chuadung;
        this.soluong_chuadung = soluong_chuadung;
        this.anhrap=anhrap;
        this.diachi_chuadung = diachi_chuadung;

    }

    // Getter và Setter

    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }

    public String getDate_chuadung() {
        return date_chuadung;
    }

    public void setDate_chuadung(String date_chuadung) {
        this.date_chuadung = date_chuadung;
    }

    public String getPoster_chuadung() {
        return poster_chuadung;
    }

    public void setPoster_chuadung(String poster_chuadung) {
        this.poster_chuadung = poster_chuadung;
    }

    public int getAge_chuadung() {
        return age_chuadung;
    }

    public void setAge_chuadung(int age_chuadung) {
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
    public String getAnhrap() {
        return anhrap;
    }

    public void setAnhrap(String anhrap) {
        this.anhrap = anhrap;
    }

    public String getDiachi_chuadung() {
        return diachi_chuadung;
    }

    public void setDiachi_chuadung(String diachi_chuadung) {
        this.diachi_chuadung = diachi_chuadung;
    }


}
