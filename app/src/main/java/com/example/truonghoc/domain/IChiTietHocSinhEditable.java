package com.example.truonghoc.domain;

public interface IChiTietHocSinhEditable extends IChiTietHocSinh {
    void setName(String name);

    void setDob(String ngaySinh);

    void setLop(String lop);

    void setGioiTinh(String gioiTinh);
}
