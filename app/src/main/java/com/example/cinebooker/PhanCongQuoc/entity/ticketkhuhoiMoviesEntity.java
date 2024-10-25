package com.example.cinebooker.PhanCongQuoc.entity;

public class ticketkhuhoiMoviesEntity {

    private int icon_khuhoi; // Resource ID cho icon
    private String date_khuhoi; // Ngày chiếu
    private String date_1_khuhoi; // Ngày kết thúc
    private String age_khuhoi; // Giới hạn độ tuổi
    private String name_khuhoi; // Tên phim
    private String style_khuhoi; // Thể loại phim
    private String soluong_khuhoi; // Số lượng vé
    private String diachi_khuhoi; // Địa chỉ
    private int poster_khuhoi; // Resource ID cho poster
    private int icon_rap_khuhoi; // Resource ID cho icon rạp

    // Constructor với tất cả các tham số
    public ticketkhuhoiMoviesEntity(int icon_khuhoi, String date_khuhoi, String date_1_khuhoi,
                                    String age_khuhoi, String name_khuhoi, String style_khuhoi,
                                    String soluong_khuhoi, String diachi_khuhoi,
                                    int poster_khuhoi, int icon_rap_khuhoi) {
        this.icon_khuhoi = icon_khuhoi;
        this.date_khuhoi = date_khuhoi;
        this.date_1_khuhoi = date_1_khuhoi;
        this.age_khuhoi = age_khuhoi;
        this.name_khuhoi = name_khuhoi;
        this.style_khuhoi = style_khuhoi;
        this.soluong_khuhoi = soluong_khuhoi;
        this.diachi_khuhoi = diachi_khuhoi;
        this.poster_khuhoi = poster_khuhoi;
        this.icon_rap_khuhoi = icon_rap_khuhoi; // Thêm dòng này
    }

    // Getter và Setter
    public int getIcon_khuhoi() {
        return icon_khuhoi;
    }

    public void setIcon_khuhoi(int icon_khuhoi) {
        this.icon_khuhoi = icon_khuhoi;
    }

    public String getDate_khuhoi() {
        return date_khuhoi;
    }

    public void setDate_khuhoi(String date_khuhoi) {
        this.date_khuhoi = date_khuhoi;
    }

    public String getDate_1_khuhoi() {
        return date_1_khuhoi;
    }

    public void setDate_1_khuhoi(String date_1_khuhoi) {
        this.date_1_khuhoi = date_1_khuhoi;
    }

    public String getAge_khuhoi() {
        return age_khuhoi;
    }

    public void setAge_khuhoi(String age_khuhoi) {
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

    public String getSoluong_khuhoi() {
        return soluong_khuhoi;
    }

    public void setSoluong_khuhoi(String soluong_khuhoi) {
        this.soluong_khuhoi = soluong_khuhoi;
    }

    public String getDiachi_khuhoi() {
        return diachi_khuhoi;
    }

    public void setDiachi_khuhoi(String diachi_khuhoi) {
        this.diachi_khuhoi = diachi_khuhoi;
    }

    public int getPoster_khuhoi() {
        return poster_khuhoi;
    }

    public void setPoster_khuhoi(int poster_khuhoi) {
        this.poster_khuhoi = poster_khuhoi;
    }

    public int getIcon_rap_khuhoi() { // Thêm getter cho icon rap
        return icon_rap_khuhoi;
    }

    public void setIcon_rap_khuhoi(int icon_rap_khuhoi) { // Thêm setter cho icon rap
        this.icon_rap_khuhoi = icon_rap_khuhoi;
    }
}
