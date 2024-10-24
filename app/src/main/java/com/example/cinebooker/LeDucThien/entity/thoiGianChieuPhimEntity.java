package com.example.cinebooker.LeDucThien.entity;

public class thoiGianChieuPhimEntity {
    private String begin, end;

    public thoiGianChieuPhimEntity() {
    }

    public thoiGianChieuPhimEntity(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
