package com.example.quanlythuvien.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlythuvien.domain.Sach;
import com.example.quanlythuvien.domain.ThuThu;

import java.util.List;

@Dao
public interface ThuVienDao {
    @Insert
    void themIdThuThu(ThuThu thuThu);

    @Query("SELECT * FROM thuthu where tenTaiKhoan= :tk")
    boolean daTonTaiThuThu(String tk);

    @Query("SELECT * FROM thuthu where tenTaiKhoan= :tk and matKhau=:mk")
    boolean kiemTraDangNhapThuThu(String tk, String mk);


    @Query("SELECT * From sach")
    List<Sach> layTatCaSach();
    @Query("SELECT*From sach where maSach=:maSach")
    boolean kiemTraSachTonTai(String maSach);
    @Insert
    void themSach(Sach sach);
    @Update
    void capNhatSach(Sach sach);
    @Query("DELETE  FROM sach where maSach=:maSach")
    void xoaSachTheoId(String maSach);
    @Query("SELECT*From sach where maSach=:maSach")
    Sach laySachTheoMaSach(String maSach);
}
