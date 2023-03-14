package com.example.quanlythuvien.domain.bo;


public class SoDienThoai extends ChuoiNhap {

    public SoDienThoai(String soDienThoai) {
        super(soDienThoai);
    }

    @Override
    public boolean kiemTraDieuKienNhap() {
        super.kiemTraDieuKienNhap();
        if (length() < 10) {
            loiThongBao = " Số Điện Thoại Ít Nhất 10 Số";
            return false;
        }
        return true;
    }
}
