
package com.example.cinebooker.PhanCongQuoc.entity;

public class ChiTietHuyEntity {

    // Fields
    private String dateChitietHuy;         // @id/date_chitiethuy
    private int posterChitietHuy;       // @id/poster_chitiethuy
    private String ageChitietHuy;          // @id/age_chitiethuy
    private String nameChitietHuy;         // @id/name_chitiethuy
    private String styleChitietHuy;        // @id/style_chitiethuy
    private String soLuongChitietHuy;      // @id/soluong_chitiethuy
    private int iconRapChitietHuy;      // @id/iconrap_chitiethuy
    private String diaChiChitietHuy;       // @id/diachi_chitiethuy
    private String time_batdau;
    private String time_ketthuc; //     // @id/time_phim_chitiethuy
    private String dateChitietHuy1;        // @id/date_chitiethuy_1
    private String dinhDangXuatPhim;       // @id/dinhdang_xuatphim
    private String gheChitietHuy;          // @id/ghe_chitiethuy
    private String phongChitietHuy;        // @id/phong_chitiethuy
    private String trangThaiChitietHuy;    // @id/trangthai_chitiethuy

    // Constructor
    public ChiTietHuyEntity(String dateChitietHuy, Integer posterChitietHuy, String ageChitietHuy,
                            String nameChitietHuy, String styleChitietHuy, String soLuongChitietHuy,
                            Integer iconRapChitietHuy, String diaChiChitietHuy, String time_batdau,String time_ketthuc,
                            String dateChitietHuy1, String dinhDangXuatPhim, String gheChitietHuy,
                            String phongChitietHuy, String trangThaiChitietHuy) {
        this.dateChitietHuy = dateChitietHuy;
        this.posterChitietHuy = posterChitietHuy;
        this.ageChitietHuy = ageChitietHuy;
        this.nameChitietHuy = nameChitietHuy;
        this.styleChitietHuy = styleChitietHuy;
        this.soLuongChitietHuy = soLuongChitietHuy;
        this.iconRapChitietHuy = iconRapChitietHuy;
        this.diaChiChitietHuy = diaChiChitietHuy;
        this.time_batdau = time_batdau;
        this.time_ketthuc = time_ketthuc;
        this.dateChitietHuy1 = dateChitietHuy1;
        this.dinhDangXuatPhim = dinhDangXuatPhim;
        this.gheChitietHuy = gheChitietHuy;
        this.phongChitietHuy = phongChitietHuy;
        this.trangThaiChitietHuy = trangThaiChitietHuy;
    }

    // Getters and Setters
    public String getDateChitietHuy() {
        return dateChitietHuy;
    }

    public void setDateChitietHuy(String dateChitietHuy) {
        this.dateChitietHuy = dateChitietHuy;
    }

    public int getPosterChitietHuy() {
        return posterChitietHuy;
    }

    public void setPosterChitietHuy(Integer posterChitietHuy) {
        this.posterChitietHuy = posterChitietHuy;
    }

    public String getAgeChitietHuy() {
        return ageChitietHuy;
    }

    public void setAgeChitietHuy(String ageChitietHuy) {
        this.ageChitietHuy = ageChitietHuy;
    }

    public String getNameChitietHuy() {
        return nameChitietHuy;
    }

    public void setNameChitietHuy(String nameChitietHuy) {
        this.nameChitietHuy = nameChitietHuy;
    }

    public String getStyleChitietHuy() {
        return styleChitietHuy;
    }

    public void setStyleChitietHuy(String styleChitietHuy) {
        this.styleChitietHuy = styleChitietHuy;
    }

    public String getSoLuongChitietHuy() {
        return soLuongChitietHuy;
    }

    public void setSoLuongChitietHuy(String soLuongChitietHuy) {
        this.soLuongChitietHuy = soLuongChitietHuy;
    }

    public int getIconRapChitietHuy() {
        return iconRapChitietHuy;
    }

    public void setIconRapChitietHuy(Integer iconRapChitietHuy) {
        this.iconRapChitietHuy = iconRapChitietHuy;
    }

    public String getDiaChiChitietHuy() {
        return diaChiChitietHuy;
    }

    public void setDiaChiChitietHuy(String diaChiChitietHuy) {
        this.diaChiChitietHuy = diaChiChitietHuy;
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
    public String getDateChitietHuy1() {
        return dateChitietHuy1;
    }

    public void setDateChitietHuy1(String dateChitietHuy1) {
        this.dateChitietHuy1 = dateChitietHuy1;
    }

    public String getDinhDangXuatPhim() {
        return dinhDangXuatPhim;
    }

    public void setDinhDangXuatPhim(String dinhDangXuatPhim) {
        this.dinhDangXuatPhim = dinhDangXuatPhim;
    }

    public String getGheChitietHuy() {
        return gheChitietHuy;
    }

    public void setGheChitietHuy(String gheChitietHuy) {
        this.gheChitietHuy = gheChitietHuy;
    }

    public String getPhongChitietHuy() {
        return phongChitietHuy;
    }

    public void setPhongChitietHuy(String phongChitietHuy) {
        this.phongChitietHuy = phongChitietHuy;
    }

    public String getTrangThaiChitietHuy() {
        return trangThaiChitietHuy;
    }

    public void setTrangThaiChitietHuy(String trangThaiChitietHuy) {
        this.trangThaiChitietHuy = trangThaiChitietHuy;
    }
}
