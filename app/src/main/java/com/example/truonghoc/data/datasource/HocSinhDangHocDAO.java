package com.example.truonghoc.data.datasource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.truonghoc.data.model.HocSinhDangHocEntity;

import java.util.List;

@Dao
public interface HocSinhDangHocDAO {
    @Insert
    void themHocSinh(HocSinhDangHocEntity hocSinh);

    @Query("SELECT * FROM hocSinhDangHoc where maHocSinh= :maHocSinh")
    boolean daTonTai(String maHocSinh);

    @Query("SELECT * FROM hocSinhDangHoc")
    List<HocSinhDangHocEntity> layTatCaHocSinh();

    @Update
    void suaHocSinh(HocSinhDangHocEntity hocSinhDangHoc);

    @Delete
    void xoaHocSinh(HocSinhDangHocEntity hocSinhDangHoc);

    @Query("delete from hocSinhDangHoc where maHocSinh=:id")
    void xoaHocSinhById(String id);

    @Query("select * from hocSinhDangHoc where maHocSinh=:id")
    HocSinhDangHocEntity getHocSinh(String id);
}
