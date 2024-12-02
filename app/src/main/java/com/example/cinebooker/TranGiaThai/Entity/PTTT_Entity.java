package com.example.cinebooker.TranGiaThai.Entity;

public class PTTT_Entity {
    private int itemThanhtoan;
    private String TenPTTT;
    private String SoDu;
    private String NapThem;

    public PTTT_Entity(String tenPTTT, int itemThanhtoan, String soDu, String napThem) {
        TenPTTT = tenPTTT;
        this.itemThanhtoan = itemThanhtoan;
        SoDu = soDu;
        NapThem = napThem;
    }

    public int getItemThanhtoan() {
        return itemThanhtoan;
    }

    public void setItemThanhtoan(int itemThanhtoan) {
        this.itemThanhtoan = itemThanhtoan;
    }

    public String getTenPTTT() {
        return TenPTTT;
    }

    public void setTenPTTT(String tenPTTT) {
        TenPTTT = tenPTTT;
    }

    public String getSoDu() {
        return SoDu;
    }

    public void setSoDu(String soDu) {
        SoDu = soDu;
    }

    public String getNapThem() {
        return NapThem;
    }

    public void setNapThem(String napThem) {
        NapThem = napThem;
    }
}
