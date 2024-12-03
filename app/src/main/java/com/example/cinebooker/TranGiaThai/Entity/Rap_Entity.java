package com.example.cinebooker.TranGiaThai.Entity;

public class Rap_Entity {
    private int item_rap;
    private String tenrap;
    private int item_map;

    public Rap_Entity(int item_rap, String tenrap, int item_map) {
        this.item_rap = item_rap;
        this.tenrap = tenrap;
        this.item_map = item_map;
    }

    public int getItem_rap() {
        return item_rap;
    }

    public void setItem_rap(int item_rap) {
        this.item_rap = item_rap;
    }

    public String getTenrap() {
        return tenrap;
    }

    public void setTenrap(String tenrap) {
        this.tenrap = tenrap;
    }

    public int getItem_map() {
        return item_map;
    }

    public void setItem_map(int item_map) {
        this.item_map = item_map;
    }
}
