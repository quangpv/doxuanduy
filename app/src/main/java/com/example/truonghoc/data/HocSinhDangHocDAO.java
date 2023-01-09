package com.example.truonghoc.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
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
    @Query("SELECT *FROM hocSinhDangHoc where maHocSinh= :maHocSinh")
    boolean kiemTraHocSinh(String maHocSinh);
    @Query("SELECT * FROM hocSinhDangHoc")
    LiveData<List<HocSinhDangHoc>> layDanhSach();
    @Update
    void suaHocSinh(HocSinhDangHoc hocSinh);
}
