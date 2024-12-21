
package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.entity;

public class xuatveEntity {
    private int MaVe;
    private String qrXuatVe;  // @id/qr_xuatve
    private String dateXuatVe1;  // @id/date_xuatve_1
    private String posterXuatVe2; // @id/poster_xuatve_2
    private int ageXuatVe;  // @id/age_xuatve
    private String nameXuatVe; // @id/name_xuatve
    private String styleXuatVe; // @id/style_xuatve
    private int soLuongXuatVe;  // @id/soluong_xuatve
    private String iconRapXuatVe; // @id/icon_rap_xuatve
    private String diaChiXuatVe; // @id/diachi_xuatve
    private String time_batdau;
    private String time_ketthuc; // @id/time_phim
    private String dateXuatVe2; // @id/date_xuatve_2
    private String dinhDangXuatPhim; // @id/dinhdang_xuatphim
    private int gheXuatVe; // @id/ghe_xuatve
    private int phongXuatVe; // @id/phong_xuatve
    private String trangThaiXuatVe; // @id/trangthai_xuatve

    public xuatveEntity(){}
    // Constructor
    public xuatveEntity(int MaVe,String qrXuatVe,  String dateXuatVe1,
                        String posterXuatVe2, int ageXuatVe, String nameXuatVe, String styleXuatVe,
                        int soLuongXuatVe, String iconRapXuatVe, String diaChiXuatVe, String time_batdau,String time_ketthuc,
                        String dateXuatVe2, String dinhDangXuatPhim, int gheXuatVe, int phongXuatVe,
                        String trangThaiXuatVe) {
        this.MaVe = MaVe;
        this.qrXuatVe = qrXuatVe;
        this.dateXuatVe1 = dateXuatVe1;
        this.posterXuatVe2 = posterXuatVe2;
        this.ageXuatVe = ageXuatVe;
        this.nameXuatVe = nameXuatVe;
        this.styleXuatVe = styleXuatVe;
        this.soLuongXuatVe = soLuongXuatVe;
        this.iconRapXuatVe = iconRapXuatVe;
        this.diaChiXuatVe = diaChiXuatVe;
        this.time_batdau = time_batdau;
        this.time_ketthuc = time_ketthuc;
        this.dateXuatVe2 = dateXuatVe2;
        this.dinhDangXuatPhim = dinhDangXuatPhim;
        this.gheXuatVe = gheXuatVe;
        this.phongXuatVe = phongXuatVe;
        this.trangThaiXuatVe = trangThaiXuatVe;
    }


    // Getters và Setters

    public int getMaVe() {
        return MaVe;
    }

    public void setMaVe(int MaVe) {
        this.MaVe = MaVe;
    }

    public String getQrXuatVe() {
        return qrXuatVe;
    }

    public void setQrXuatVe(String qrXuatVe) {
        this.qrXuatVe = qrXuatVe;
    }


    public String getDateXuatVe1() {
        return dateXuatVe1;
    }

    public void setDateXuatVe1(String dateXuatVe1) {
        this.dateXuatVe1 = dateXuatVe1;
    }

    public String getPosterXuatVe2() {
        return posterXuatVe2;
    }

    public void setPosterXuatVe2(String posterXuatVe2) {
        this.posterXuatVe2 = posterXuatVe2;
    }

    public int getAgeXuatVe() {
        return ageXuatVe;
    }

    public void setAgeXuatVe(int ageXuatVe) {
        this.ageXuatVe = ageXuatVe;
    }

    public String getNameXuatVe() {
        return nameXuatVe;
    }

    public void setNameXuatVe(String nameXuatVe) {
        this.nameXuatVe = nameXuatVe;
    }

    public String getStyleXuatVe() {
        return styleXuatVe;
    }

    public void setStyleXuatVe(String styleXuatVe) {
        this.styleXuatVe = styleXuatVe;
    }

    public int getSoLuongXuatVe() {
        return soLuongXuatVe;
    }

    public void setSoLuongXuatVe(int soLuongXuatVe) {
        this.soLuongXuatVe = soLuongXuatVe;
    }

    public String getIconRapXuatVe() {
        return iconRapXuatVe;
    }

    public void setIconRapXuatVe(String iconRapXuatVe) {
        this.iconRapXuatVe = iconRapXuatVe;
    }

    public String getDiaChiXuatVe() {
        return diaChiXuatVe;
    }

    public void setDiaChiXuatVe(String diaChiXuatVe) {
        this.diaChiXuatVe = diaChiXuatVe;
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

    public String getDateXuatVe2() {
        return dateXuatVe2;
    }

    public void setDateXuatVe2(String dateXuatVe2) {
        this.dateXuatVe2 = dateXuatVe2;
    }

    public String getDinhDangXuatPhim() {
        return dinhDangXuatPhim;
    }

    public void setDinhDangXuatPhim(String dinhDangXuatPhim) {
        this.dinhDangXuatPhim = dinhDangXuatPhim;
    }

    public int getGheXuatVe() {
        return gheXuatVe;
    }

    public void setGheXuatVe(int gheXuatVe) {
        this.gheXuatVe = gheXuatVe;
    }

    public int getPhongXuatVe() {
        return phongXuatVe;
    }

    public void setPhongXuatVe(int phongXuatVe) {
        this.phongXuatVe = phongXuatVe;
    }

    public String getTrangThaiXuatVe() {
        return trangThaiXuatVe;
    }

    public void setTrangThaiXuatVe(String trangThaiXuatVe) {
        this.trangThaiXuatVe = trangThaiXuatVe;
    }
}
