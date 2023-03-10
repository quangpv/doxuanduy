package com.example.truonghoc.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.truonghoc.domain.HocSinhDangHoc;

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
    int suaHocSinh(HocSinhDangHoc hocSinhDangHoc);

    @Query("select * from hocSinhDangHoc where id=:id")
    HocSinhDangHoc getHocSinh(long id);
}
