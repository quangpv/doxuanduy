package com.example.quanlythuvien.domain.bo;

import androidx.annotation.NonNull;

import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;
import com.example.quanlythuvien.domain.ui.IXuLyRunnable;

public class ChuoiNhap extends IXuLyRunnable.NhieuRunnable implements CharSequence, IKiemTraGiaTriNhap {

    public String giaTriNhapVao;
    public String loiThongBao = "";

    public ChuoiNhap(String chuoiNhap) {
        this.giaTriNhapVao = chuoiNhap;
    }

    public void setGiaTriNhapVao(String giaTriNhapVao) {
        this.giaTriNhapVao = giaTriNhapVao;
        chayRunnable();
    }

    @Override
    public boolean kiemTraDieuKienNhap() {
        boolean value = length() > 0;
        if (!value) loiThongBao = "Mục này không để trống";
        return value;
    }

    @Override
    public String layThongBaoLoi() {
        return loiThongBao;
    }

    @Override
    public int length() {
        return giaTriNhapVao.length();
    }

    @Override
    public char charAt(int index) {
        return giaTriNhapVao.charAt(index);
    }

    @NonNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return giaTriNhapVao.subSequence(start, end);
    }

    @NonNull
    @Override
    public String toString() {
        return giaTriNhapVao;
    }
}
