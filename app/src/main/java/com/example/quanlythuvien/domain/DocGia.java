package com.example.quanlythuvien.domain;

public class DocGia {
    private String maDocGia;
    private String idMaDocGia;
    private String tenDocGia;
    private String ngaySinh;
    private String soCMND;
    private String ngayHetHan;
    private String diaChi;

    public DocGia(String maDocGia, String idMaDocGia, String tenDocGia, String ngaySinh, String soCMND, String ngayHetHan, String diaChi) {
        this.maDocGia = maDocGia;
        this.idMaDocGia = idMaDocGia;
        this.tenDocGia = tenDocGia;
        this.ngaySinh = ngaySinh;
        this.soCMND = soCMND;
        this.ngayHetHan = ngayHetHan;
        this.diaChi = diaChi;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public String getIdMaDocGia() {
        return idMaDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public void setIdMaDocGia(String idMaDocGia) {
        this.idMaDocGia = idMaDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
