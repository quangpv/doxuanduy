package com.example.quanlythuvien.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sach")
public class Sach {
    @PrimaryKey(autoGenerate = true)
    public long id;
    private String maSach;
    private String tenSach;
    private String loaiSach;
    private String tenTacGia;
    private String nhaXuatBan;
    private String namXuatBan;
    private int choThue;
    private int tong;

    public Sach(String maSach, String tenSach, String loaiSach, String tenTacGia, String nhaXuatBan, String namXuatBan,int tong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.loaiSach = loaiSach;
        this.tenTacGia = tenTacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.tong = tong;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setChoThue(int choThue) {
        this.choThue = choThue;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public int getChoThue() {
        return choThue;
    }

    public int getTong() {
        return tong;
    }

    public int getSoLuongConLai() {
        return tong-choThue;
    }

}
