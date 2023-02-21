package com.example.truonghoc.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hocSinhDangHoc")
public class HocSinhDangHocEntity {
    @PrimaryKey(autoGenerate = true)
    public long Id;
    @Embedded
    HocSinhEntity hocSinh;
    @Embedded
    KhoiEntity khoiLop;

    public HocSinhDangHocEntity(HocSinhEntity hocSinh, KhoiEntity khoiLop) {
        this.hocSinh = hocSinh;
        this.khoiLop = khoiLop;
    }

    public HocSinhEntity getHocSinh() {
        return hocSinh;
    }

    public KhoiEntity getKhoiLop() {
        return khoiLop;
    }

    public void setHocSinh(HocSinhEntity hocSinh) {
        this.hocSinh = hocSinh;
    }

    public void setKhoiLop(KhoiEntity khoiLop) {
        this.khoiLop = khoiLop;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
