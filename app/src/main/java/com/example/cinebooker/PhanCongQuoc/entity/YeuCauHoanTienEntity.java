
package com.example.cinebooker.PhanCongQuoc.entity;




public class YeuCauHoanTienEntity {
    private Integer posterTrHang; // @id/poster_trhang
    private String namePhimTrHang; // @id/namephim_trhang
    private String dateTrHang; // @id/date_trhang
    private String time_batdau;
    private String time_ketthuc; // @id/time_trhang
    private String soLuongTrHang; // @id/soluong_trhang
    private Integer iconTrHang; // @id/icon_trhang
    private String diaChiTrHang; // @id/diachi_trhang

    private int soTienHoanTrHang; // @id/sotienhoan_trhang
    private int soDuTrHang; // @id/sodu_trhang

    // Constructor
    public YeuCauHoanTienEntity(Integer posterTrHang, String namePhimTrHang, String dateTrHang,
                                String time_batdau,String time_ketthuc, String soLuongTrHang, Integer iconTrHang,
                                String diaChiTrHang,
                                int soTienHoanTrHang, int soDuTrHang) {
        this.posterTrHang = posterTrHang;
        this.namePhimTrHang = namePhimTrHang;
        this.dateTrHang = dateTrHang;
        this.time_batdau = time_batdau;
        this.time_ketthuc = time_ketthuc;
        this.soLuongTrHang = soLuongTrHang;
        this.iconTrHang = iconTrHang;
        this.diaChiTrHang = diaChiTrHang;
        this.soTienHoanTrHang = soTienHoanTrHang;
        this.soDuTrHang = soDuTrHang;
    }

    // Getters and Setters
    public Integer getPosterTrHang() {
        return posterTrHang;
    }

    public void setPosterTrHang(Integer posterTrHang) {
        this.posterTrHang = posterTrHang;
    }

    public String getNamePhimTrHang() {
        return namePhimTrHang;
    }

    public void setNamePhimTrHang(String namePhimTrHang) {
        this.namePhimTrHang = namePhimTrHang;
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

    public String getSoLuongTrHang() {
        return soLuongTrHang;
    }

    public void setSoLuongTrHang(String soLuongTrHang) {
        this.soLuongTrHang = soLuongTrHang;
    }

    public Integer getIconTrHang() {
        return iconTrHang;
    }

    public void setIconTrHang(Integer iconTrHang) {
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

    public int getSoDuTrHang() {
        return soDuTrHang;
    }

    public void setSoDuTrHang(int soDuTrHang) {
        this.soDuTrHang = soDuTrHang;
    }
}
