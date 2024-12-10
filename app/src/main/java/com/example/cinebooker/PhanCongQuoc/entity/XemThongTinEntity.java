
package com.example.cinebooker.PhanCongQuoc.entity;

public class XemThongTinEntity {
    private String dateXemThongTin;       // @id/date_xemthongtin
    private String date1XemThongTin;     // @id/date_1_xemthongtin
    private Integer posterXemThongTin;    // @id/poster_xemthongtin (URL hoặc tên tệp hình ảnh)
    private String ageXemThongTin;       // @id/age_xemthongtin
    private String nameXemThongTin;      // @id/name_xemthongtin
    private String styleXemThongTin;     // @id/style_xemthongtin
    private String soLuong;              // @id/soluong_xemthongtin
    private Integer iconRap;              // @id/iconrap_xemthongtin
    private String diaChi;               // @id/diachi_xemthongtin
    private String time_batdau;
    private String time_ketthuc; //              // @id/time_phim
    private String dateXemThongTin1;     // @id/date_xemthongtin_1
    private String dinhDang;             // @id/dinhdang_xemthongtin
    private String gheNgoi;              // @id/ghe_xemthongtin
    private String phongChieu;           // @id/phong_xemthongtin
    private String trangThai;            // @id/trangthai_xemthongtin

    // Constructor
    public XemThongTinEntity(String dateXemThongTin, String date1XemThongTin, Integer posterXemThongTin,
                             String ageXemThongTin, String nameXemThongTin, String styleXemThongTin,
                             String soLuong, Integer iconRap, String diaChi, String time_batdau,String time_ketthuc,
                             String dateXemThongTin1, String dinhDang, String gheNgoi,
                             String phongChieu, String trangThai) {
        this.dateXemThongTin = dateXemThongTin;
        this.date1XemThongTin = date1XemThongTin;
        this.posterXemThongTin = posterXemThongTin;
        this.ageXemThongTin = ageXemThongTin;
        this.nameXemThongTin = nameXemThongTin;
        this.styleXemThongTin = styleXemThongTin;
        this.soLuong = soLuong;
        this.iconRap = iconRap;
        this.diaChi = diaChi;
        this.time_batdau = time_batdau;
        this.time_ketthuc = time_ketthuc;
        this.dateXemThongTin1 = dateXemThongTin1;
        this.dinhDang = dinhDang;
        this.gheNgoi = gheNgoi;
        this.phongChieu = phongChieu;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getDateXemThongTin() {
        return dateXemThongTin;
    }

    public void setDateXemThongTin(String dateXemThongTin) {
        this.dateXemThongTin = dateXemThongTin;
    }

    public String getDate1XemThongTin() {
        return date1XemThongTin;
    }

    public void setDate1XemThongTin(String date1XemThongTin) {
        this.date1XemThongTin = date1XemThongTin;
    }

    public int getPosterXemThongTin() {
        return posterXemThongTin;
    }

    public void setPosterXemThongTin(int posterXemThongTin) {
        this.posterXemThongTin = posterXemThongTin;
    }

    public String getAgeXemThongTin() {
        return ageXemThongTin;
    }

    public void setAgeXemThongTin(String ageXemThongTin) {
        this.ageXemThongTin = ageXemThongTin;
    }

    public String getNameXemThongTin() {
        return nameXemThongTin;
    }

    public void setNameXemThongTin(String nameXemThongTin) {
        this.nameXemThongTin = nameXemThongTin;
    }

    public String getStyleXemThongTin() {
        return styleXemThongTin;
    }

    public void setStyleXemThongTin(String styleXemThongTin) {
        this.styleXemThongTin = styleXemThongTin;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getIconRap() {
        return iconRap;
    }

    public void setIconRap(int iconRap) {
        this.iconRap = iconRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getDateXemThongTin1() {
        return dateXemThongTin1;
    }

    public void setDateXemThongTin1(String dateXemThongTin1) {
        this.dateXemThongTin1 = dateXemThongTin1;
    }

    public String getDinhDang() {
        return dinhDang;
    }

    public void setDinhDang(String dinhDang) {
        this.dinhDang = dinhDang;
    }

    public String getGheNgoi() {
        return gheNgoi;
    }

    public void setGheNgoi(String gheNgoi) {
        this.gheNgoi = gheNgoi;
    }

    public String getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(String phongChieu) {
        this.phongChieu = phongChieu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
