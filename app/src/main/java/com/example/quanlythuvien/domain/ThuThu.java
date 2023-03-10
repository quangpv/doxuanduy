package com.example.quanlythuvien.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "thuthu")
public class ThuThu {
    @PrimaryKey(autoGenerate = true)
    public long id;
    private String tenTaiKhoan;
    private String matKhau;

    public ThuThu(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
