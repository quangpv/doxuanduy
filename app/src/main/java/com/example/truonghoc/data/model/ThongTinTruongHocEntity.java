package com.example.truonghoc.data.model;

public class ThongTinTruongHocEntity {
    private String tenTruong,diaChiTruong,sdtTruong;

    public ThongTinTruongHocEntity(String tenTruong, String diaChiTruong, String sdtTruong) {
        this.tenTruong = tenTruong;
        this.diaChiTruong = diaChiTruong;
        this.sdtTruong = sdtTruong;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public String getDiaChiTruong() {
        return diaChiTruong;
    }

    public String getSdtTruong() {
        return sdtTruong;
    }
}
