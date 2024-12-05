package com.example.cinebooker.PhanCongQuoc.entity;

public class xuatveEntity {
    private int qrXuatVe;  // @id/qr_xuatve
    private String dateXuatVe1;  // @id/date_xuatve_1
    private int posterXuatVe2; // @id/poster_xuatve_2
    private String ageXuatVe;  // @id/age_xuatve
    private String nameXuatVe; // @id/name_xuatve
    private String styleXuatVe; // @id/style_xuatve
    private String soLuongXuatVe;  // @id/soluong_xuatve
    private int iconRapXuatVe; // @id/icon_rap_xuatve
    private String diaChiXuatVe; // @id/diachi_xuatve
    private String time_batdau;
    private String time_ketthuc; // @id/time_phim
    private String dateXuatVe2; // @id/date_xuatve_2
    private String dinhDangXuatPhim; // @id/dinhdang_xuatphim
    private String gheXuatVe; // @id/ghe_xuatve
    private String phongXuatVe; // @id/phong_xuatve
    private String trangThaiXuatVe; // @id/trangthai_xuatve

    // Constructor
    public xuatveEntity(Integer qrXuatVe,  String dateXuatVe1,
                        Integer posterXuatVe2, String ageXuatVe, String nameXuatVe, String styleXuatVe,
                        String soLuongXuatVe, Integer iconRapXuatVe, String diaChiXuatVe, String time_batdau,String time_ketthuc,
                        String dateXuatVe2, String dinhDangXuatPhim, String gheXuatVe, String phongXuatVe,
                        String trangThaiXuatVe) {
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
    public Integer getQrXuatVe() {
        return qrXuatVe;
    }

    public void setQrXuatVe(Integer qrXuatVe) {
        this.qrXuatVe = qrXuatVe;
    }


    public String getDateXuatVe1() {
        return dateXuatVe1;
    }

    public void setDateXuatVe1(String dateXuatVe1) {
        this.dateXuatVe1 = dateXuatVe1;
    }

    public Integer getPosterXuatVe2() {
        return posterXuatVe2;
    }

    public void setPosterXuatVe2(Integer posterXuatVe2) {
        this.posterXuatVe2 = posterXuatVe2;
    }

    public String getAgeXuatVe() {
        return ageXuatVe;
    }

    public void setAgeXuatVe(String ageXuatVe) {
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

    public String getSoLuongXuatVe() {
        return soLuongXuatVe;
    }

    public void setSoLuongXuatVe(String soLuongXuatVe) {
        this.soLuongXuatVe = soLuongXuatVe;
    }

    public Integer getIconRapXuatVe() {
        return iconRapXuatVe;
    }

    public void setIconRapXuatVe(Integer iconRapXuatVe) {
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

    public String getGheXuatVe() {
        return gheXuatVe;
    }

    public void setGheXuatVe(String gheXuatVe) {
        this.gheXuatVe = gheXuatVe;
    }

    public String getPhongXuatVe() {
        return phongXuatVe;
    }

    public void setPhongXuatVe(String phongXuatVe) {
        this.phongXuatVe = phongXuatVe;
    }

    public String getTrangThaiXuatVe() {
        return trangThaiXuatVe;
    }

    public void setTrangThaiXuatVe(String trangThaiXuatVe) {
        this.trangThaiXuatVe = trangThaiXuatVe;
    }
}
