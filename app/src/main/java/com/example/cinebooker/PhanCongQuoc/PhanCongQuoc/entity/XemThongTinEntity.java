
package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.entity;

public class XemThongTinEntity {
    private int MaVe;
    private  String dateXemThongTin;       // @id/date_xemthongtin
    private String posterXemThongTin;    // @id/poster_xemthongtin (URL hoặc tên tệp hình ảnh)
    private int ageXemThongTin;       // @id/age_xemthongtin
    private String nameXemThongTin;      // @id/name_xemthongtin
    private String styleXemThongTin;     // @id/style_xemthongtin
    private int soLuong;              // @id/soluong_xemthongtin
    private String iconRap;              // @id/iconrap_xemthongtin
    private String diaChi;               // @id/diachi_xemthongtin
    private String time_batdau;
    private String time_ketthuc; //              // @id/time_phim
    private String dateXemThongTin1;     // @id/date_xemthongtin_1
    private String dinhDang;             // @id/dinhdang_xemthongtin
    private int gheNgoi;              // @id/ghe_xemthongtin
    private int phongChieu;           // @id/phong_xemthongtin
    private String trangThai;            // @id/trangthai_xemthongtin

    // Constructor
    public XemThongTinEntity(int MaVe,String dateXemThongTin, String posterXemThongTin,
                             int ageXemThongTin, String nameXemThongTin, String styleXemThongTin,
                             int soLuong, String iconRap, String diaChi, String time_batdau,String time_ketthuc,
                             String dateXemThongTin1, String dinhDang, int gheNgoi,
                             int phongChieu, String trangThai) {
        this.MaVe=MaVe;
        this.dateXemThongTin = dateXemThongTin;
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

    public XemThongTinEntity() {

    }

    // Getters and Setters
    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }
    public String getDateXemThongTin() {
        return dateXemThongTin;
    }

    public void setDateXemThongTin(String dateXemThongTin) {
        this.dateXemThongTin = dateXemThongTin;
    }

    public String getPosterXemThongTin() {
        return posterXemThongTin;
    }

    public void setPosterXemThongTin(String posterXemThongTin) {
        this.posterXemThongTin = posterXemThongTin;
    }

    public int getAgeXemThongTin() {
        return ageXemThongTin;
    }

    public void setAgeXemThongTin(int ageXemThongTin) {
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIconRap() {
        return iconRap;
    }

    public void setIconRap(String iconRap) {
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

    public int getGheNgoi() {
        return gheNgoi;
    }

    public void setGheNgoi(int gheNgoi) {
        this.gheNgoi = gheNgoi;
    }

    public int getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(int phongChieu) {
        this.phongChieu = phongChieu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
