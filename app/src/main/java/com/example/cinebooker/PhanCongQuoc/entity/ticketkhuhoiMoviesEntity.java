
package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketkhuhoiMoviesEntity {
    private int MaVe;
    private String date_khuhoi; // Ngày chiếu
    private int age_khuhoi; // Giới hạn độ tuổi
    private String name_khuhoi; // Tên phim
    private String style_khuhoi; // Thể loại phim
    private int soluong_khuhoi; // Số lượng vé
    private String diachi_khuhoi; // Địa chỉ
    private String poster_khuhoi; // Resource ID cho poster
    private String icon_rap_khuhoi; // Resource ID cho icon rạp

    public ticketkhuhoiMoviesEntity(){}
    // Constructor với tất cả các tham số
    public ticketkhuhoiMoviesEntity( int MaVe,String date_khuhoi,
                                    int age_khuhoi, String name_khuhoi, String style_khuhoi,
                                    int soluong_khuhoi, String diachi_khuhoi,
                                    String poster_khuhoi, String icon_rap_khuhoi) {
          this.MaVe=MaVe;
        this.date_khuhoi = date_khuhoi;
        this.age_khuhoi = age_khuhoi;
        this.name_khuhoi = name_khuhoi;
        this.style_khuhoi = style_khuhoi;
        this.soluong_khuhoi = soluong_khuhoi;
        this.diachi_khuhoi = diachi_khuhoi;
        this.poster_khuhoi = poster_khuhoi;
        this.icon_rap_khuhoi = icon_rap_khuhoi; // Thêm dòng này
    }

    // Getter và Setter
    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }
    public String getDate_khuhoi() {
        return date_khuhoi;
    }

    public void setDate_khuhoi(String date_khuhoi) {
        this.date_khuhoi = date_khuhoi;
    }

    public int getAge_khuhoi() {
        return age_khuhoi;
    }

    public void setAge_khuhoi(int age_khuhoi) {
        this.age_khuhoi = age_khuhoi;
    }

    public String getName_khuhoi() {
        return name_khuhoi;
    }

    public void setName_khuhoi(String name_khuhoi) {
        this.name_khuhoi = name_khuhoi;
    }

    public String getStyle_khuhoi() {
        return style_khuhoi;
    }

    public void setStyle_khuhoi(String style_khuhoi) {
        this.style_khuhoi = style_khuhoi;
    }

    public int getSoluong_khuhoi() {
        return soluong_khuhoi;
    }

    public void setSoluong_khuhoi(int soluong_khuhoi) {
        this.soluong_khuhoi = soluong_khuhoi;
    }

    public String getDiachi_khuhoi() {
        return diachi_khuhoi;
    }

    public void setDiachi_khuhoi(String diachi_khuhoi) {
        this.diachi_khuhoi = diachi_khuhoi;
    }

    public String getPoster_khuhoi() {
        return poster_khuhoi;
    }

    public void setPoster_khuhoi(String poster_khuhoi) {
        this.poster_khuhoi = poster_khuhoi;
    }

    public String getIcon_rap_khuhoi() { // Thêm getter cho icon rap
        return icon_rap_khuhoi;
    }

    public void setIcon_rap_khuhoi(String icon_rap_khuhoi) { // Thêm setter cho icon rap
        this.icon_rap_khuhoi = icon_rap_khuhoi;
    }
}
