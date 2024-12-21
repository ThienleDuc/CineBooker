
package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.entity;




public class YeuCauHoanTienEntity {
    private int MaVe;
    private String posterTrHang; // @id/poster_trhang
    private String namePhimTrHang;
    private String stylehoantien;// @id/namephim_trhang
    private String dateTrHang; // @id/date_trhang
    private String time_batdau;
    private String time_ketthuc; // @id/time_trhang
    private int soLuongTrHang; // @id/soluong_trhang
    private String iconTrHang; // @id/icon_trhang
    private String diaChiTrHang; // @id/diachi_trhang

    private int soTienHoanTrHang; // @id/sotienhoan_trhang

    public YeuCauHoanTienEntity(){}
    // Constructor
    public YeuCauHoanTienEntity(int MaVe,String posterTrHang, String namePhimTrHang,String stylehoantien, String dateTrHang,
                                String time_batdau,String time_ketthuc, int soLuongTrHang, String iconTrHang,
                                String diaChiTrHang,
                                int soTienHoanTrHang) {
        this.MaVe = MaVe;
        this.posterTrHang = posterTrHang;
        this.namePhimTrHang = namePhimTrHang;
        this.stylehoantien=stylehoantien;
        this.dateTrHang = dateTrHang;
        this.time_batdau = time_batdau;
        this.time_ketthuc = time_ketthuc;
        this.soLuongTrHang = soLuongTrHang;
        this.iconTrHang = iconTrHang;
        this.diaChiTrHang = diaChiTrHang;
        this.soTienHoanTrHang = soTienHoanTrHang;

    }
    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }

    // Getters and Setters
    public String getPosterTrHang() {
        return posterTrHang;
    }

    public void setPosterTrHang(String posterTrHang) {
        this.posterTrHang = posterTrHang;
    }

    public String getNamePhimTrHang() {
        return namePhimTrHang;
    }

    public void setNamePhimTrHang(String namePhimTrHang) {
        this.namePhimTrHang = namePhimTrHang;
    }

    public String getStylehoantien() {
        return stylehoantien;
    }

    public void setStylehoantien(String stylehoantien) {
        this.stylehoantien = stylehoantien;
    }

    public String getDateTrHang() {
        return dateTrHang;
    }

    public void setDateTrHang(String dateTrHang) {
        this.dateTrHang = dateTrHang;
    }

    public String getTime_batdau() {
        return time_batdau;
    }

    public void setTime_batdau(String time_batdau) {
        this.time_batdau = time_batdau;
    }
    public String getTime_ketthuc() {
        return time_ketthuc;
    }

    public void setTime_ketthuc(String ketthuc) {
        this.time_ketthuc = ketthuc;
    }

    public int getSoLuongTrHang() {
        return soLuongTrHang;
    }

    public void setSoLuongTrHang(int soLuongTrHang) {
        this.soLuongTrHang = soLuongTrHang;
    }

    public String getIconTrHang() {
        return iconTrHang;
    }

    public void setIconTrHang(String iconTrHang) {
        this.iconTrHang = iconTrHang;
    }

    public String getDiaChiTrHang() {
        return diaChiTrHang;
    }

    public void setDiaChiTrHang(String diaChiTrHang) {
        this.diaChiTrHang = diaChiTrHang;
    }



    public int getSoTienHoanTrHang() {
        return soTienHoanTrHang;
    }

    public void setSoTienHoanTrHang(int soTienHoanTrHang) {
        this.soTienHoanTrHang = soTienHoanTrHang;
    }


}
