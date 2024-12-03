package com.example.cinebooker.TranGiaThai.Entity;

public class Ghe_Entity {
    private int item_ghe;
    private String ten_ghe;
    private String so_luong_ghe;

    public Ghe_Entity(int item_ghe, String ten_ghe, String so_luong_ghe) {
        this.item_ghe = item_ghe;
        this.ten_ghe = ten_ghe;
        this.so_luong_ghe = so_luong_ghe;
    }

    public int getItem_ghe() {
        return item_ghe;
    }

    public void setItem_ghe(int item_ghe) {
        this.item_ghe = item_ghe;
    }

    public String getTen_ghe() {
        return ten_ghe;
    }

    public void setTen_ghe(String ten_ghe) {
        this.ten_ghe = ten_ghe;
    }

    public String getSo_luong_ghe() {
        return so_luong_ghe;
    }

    public void setSo_luong_ghe(String so_luong_ghe) {
        this.so_luong_ghe = so_luong_ghe;
    }
}
