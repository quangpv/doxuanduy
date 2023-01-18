package com.example.truonghoc.domain;

public class ThongTinTruongHoc {
    private String tenTruong,diaChiTruong,sdtTruong;

    public ThongTinTruongHoc(String tenTruong, String diaChiTruong, String sdtTruong) {
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
