package com.example.cinebooker.TranGiaThai.Entity;

public class Thucan_Entity {
    private int item_thucan;
    private String name_thucan;
    private String money_thucan;
    private int item_cong;
    private int item_tru;
    private String soluong;

    public Thucan_Entity(String soluong) {
        this.soluong = soluong;
    }

    public Thucan_Entity(int item_thucan, String name_thucan, String money_thucan, int item_cong, int item_tru) {
        this.item_thucan = item_thucan;
        this.name_thucan = name_thucan;
        this.money_thucan = money_thucan;
        this.item_cong = item_cong;
        this.item_tru = item_tru;

    }

    public String getMoney_thucan() {
        return money_thucan;
    }

    public void setMoney_thucan(String money_thucan) {
        this.money_thucan = money_thucan;
    }

    public String getName_thucan() {
        return name_thucan;
    }

    public void setName_thucan(String name_thucan) {
        this.name_thucan = name_thucan;
    }

    public int getItem_thucan() {
        return item_thucan;
    }

    public void setItem_thucan(int item_thucan) {
        this.item_thucan = item_thucan;
    }

    public int getItem_tru() {
        return item_tru;
    }

    public void setItem_tru(int item_tru) {
        this.item_tru = item_tru;
    }

    public int getItem_cong() {
        return item_cong;
    }

    public void setItem_cong(int item_cong) {
        this.item_cong = item_cong;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
