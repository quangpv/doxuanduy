package com.example.truonghoc.data.model;

public class ThongTinTruongHocEntity {
    private String tenTruong, diaChiTruong, sdtTruong, imageUri;

    public ThongTinTruongHocEntity(String tenTruong, String diaChiTruong, String sdtTruong, String imageUri) {
        this.tenTruong = tenTruong;
        this.diaChiTruong = diaChiTruong;
        this.sdtTruong = sdtTruong;
        this.imageUri = imageUri;
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

    public String getImageUri() {
        return this.imageUri;
    }
}
