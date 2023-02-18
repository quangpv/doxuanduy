package com.example.truonghoc.data.datasource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.truonghoc.data.model.HocSinhDangHoc;

import java.util.List;

@Dao
public interface HocSinhDangHocDAO {
    @Insert
    void themHocSinh(HocSinhDangHoc hocSinh);

    @Query("SELECT * FROM hocSinhDangHoc where maHocSinh= :maHocSinh")
    boolean daTonTai(String maHocSinh);

    @Query("SELECT * FROM hocSinhDangHoc")
    List<HocSinhDangHoc> layTatCaHocSinh();

    @Update
    void suaHocSinh(HocSinhDangHoc hocSinhDangHoc);

    @Delete
    void xoaHocSinh(HocSinhDangHoc hocSinhDangHoc);

    @Query("delete from hocSinhDangHoc where maHocSinh=:id")
    void xoaHocSinhById(String id);

    @Query("select * from hocSinhDangHoc where maHocSinh=:id")
    HocSinhDangHoc getHocSinh(String id);
}
