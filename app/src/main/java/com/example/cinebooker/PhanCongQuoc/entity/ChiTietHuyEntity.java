
package com.example.cinebooker.PhanCongQuoc.entity;

public class ChiTietHuyEntity {
    private int MaVe;
    // Fields
    private String dateChitietHuy;         // @id/date_chitiethuy
    private String posterChitietHuy;       // @id/poster_chitiethuy
    private int ageChitietHuy;          // @id/age_chitiethuy
    private String nameChitietHuy;         // @id/name_chitiethuy
    private String styleChitietHuy;        // @id/style_chitiethuy
    private int soLuongChitietHuy;      // @id/soluong_chitiethuy
    private String iconRapChitietHuy;      // @id/iconrap_chitiethuy
    private String diaChiChitietHuy;       // @id/diachi_chitiethuy
    private String time_batdau;
    private String time_ketthuc; //     // @id/time_phim_chitiethuy
    private String dateChitietHuy1;        // @id/date_chitiethuy_1
    private String dinhDangXuatPhim;       // @id/dinhdang_xuatphim
    private int gheChitietHuy;          // @id/ghe_chitiethuy
    private int phongChitietHuy;        // @id/phong_chitiethuy
    private String trangThaiChitietHuy;    // @id/trangthai_chitiethuy


    public ChiTietHuyEntity(int MaVe,String dateChitietHuy, String posterChitietHuy, int ageChitietHuy,
                            String nameChitietHuy, String styleChitietHuy, int soLuongChitietHuy,
                            String iconRapChitietHuy, String diaChiChitietHuy, String time_batdau,String time_ketthuc,
                            String dateChitietHuy1, String dinhDangXuatPhim, int gheChitietHuy,
                            int phongChitietHuy, String trangThaiChitietHuy) {
        this.MaVe=MaVe;
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
    public ChiTietHuyEntity(){}
    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }

    public String getDateChitietHuy() {
        return dateChitietHuy;
    }

    public void setDateChitietHuy(String dateChitietHuy) {
        this.dateChitietHuy = dateChitietHuy;
    }

    public String getPosterChitietHuy() {
        return posterChitietHuy;
    }

    public void setPosterChitietHuy(String posterChitietHuy) {
        this.posterChitietHuy = posterChitietHuy;
    }

    public int getAgeChitietHuy() {
        return ageChitietHuy;
    }

    public void setAgeChitietHuy(int ageChitietHuy) {
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

    public int getSoLuongChitietHuy() {
        return soLuongChitietHuy;
    }

    public void setSoLuongChitietHuy(int soLuongChitietHuy) {
        this.soLuongChitietHuy = soLuongChitietHuy;
    }

    public String getIconRapChitietHuy() {
        return iconRapChitietHuy;
    }

    public void setIconRapChitietHuy(String iconRapChitietHuy) {
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

    public int getGheChitietHuy() {
        return gheChitietHuy;
    }

    public void setGheChitietHuy(int gheChitietHuy) {
        this.gheChitietHuy = gheChitietHuy;
    }

    public int getPhongChitietHuy() {
        return phongChitietHuy;
    }

    public void setPhongChitietHuy(int phongChitietHuy) {
        this.phongChitietHuy = phongChitietHuy;
    }

    public String getTrangThaiChitietHuy() {
        return trangThaiChitietHuy;
    }

    public void setTrangThaiChitietHuy(String trangThaiChitietHuy) {
        this.trangThaiChitietHuy = trangThaiChitietHuy;
    }
}
