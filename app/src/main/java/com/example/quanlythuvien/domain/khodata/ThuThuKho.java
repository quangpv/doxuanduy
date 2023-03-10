package com.example.quanlythuvien.domain.khodata;

import com.example.quanlythuvien.data.ThuVienDao;
import com.example.quanlythuvien.data.ThuVienDataBase;
import com.example.quanlythuvien.domain.ThuThu;
import com.example.quanlythuvien.domain.ui.IThuThuSet;

public class ThuThuKho {
    private static final ThuThuKho thuThuKho = new ThuThuKho();
    private final ThuVienDao thuVien = ThuVienDataBase.getInstance().ThuVienDao();
    public static ThuThuKho getInstance() {
        return thuThuKho;
    }

    public boolean checkTonTai(String tk) {
        return thuVien.daTonTaiThuThu(tk);
    }

    public void themThuThu(IThuThuSet thuThuEdit) {
        ThuThu thuThu = new ThuThu(thuThuEdit.getTk(),thuThuEdit.getMk());
        thuVien.themIdThuThu(thuThu);
    }

    public boolean kiemTraTaiKhoan(IThuThuSet thuThuEdit) {
        return thuVien.kiemTraDangNhapThuThu(thuThuEdit.getTk(),thuThuEdit.getMk());
    }
}
