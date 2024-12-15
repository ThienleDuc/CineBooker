package com.example.cinebooker.LeDucThien.entity;

public class ent_TinhThanh {
    private int maTinhThanh; // Mã tỉnh thành
    private String tenTinhThanh; // Tên tỉnh thành

    public ent_TinhThanh() {
    }

    public ent_TinhThanh(int maTinhThanh, String tenTinhThanh) {
        this.maTinhThanh = maTinhThanh;
        this.tenTinhThanh = tenTinhThanh;
    }

    public ent_TinhThanh(String tenTinhThanh, int maTinhThanh) {
        this.tenTinhThanh = tenTinhThanh;
        this.maTinhThanh = maTinhThanh;
    }

    public String getTenTinhThanh() {
        return tenTinhThanh;
    }

    public void setTenTinhThanh(String tenTinhThanh) {
        this.tenTinhThanh = tenTinhThanh;
    }

    public int getMaTinhThanh() {
        return maTinhThanh;
    }

    public void setMaTinhThanh(int maTinhThanh) {
        this.maTinhThanh = maTinhThanh;
    }
}
