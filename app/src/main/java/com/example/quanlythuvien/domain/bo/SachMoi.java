package com.example.quanlythuvien.domain.bo;

import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;
import com.example.quanlythuvien.domain.ui.ISachGet;
import com.example.quanlythuvien.domain.ui.ISachSet;

import java.util.Objects;

public class SachMoi implements ISachGet, ISachSet{
    private ChuoiNhap maSach, tenSach;
    private String loaiSach, nhaXuatBan, tenTacGia, namXuatBan;
    private int tongSach, choThue;
    private ISachGet iSachGet;

    public SachMoi() {
        this.maSach = new ChuoiNhap("");
        this.tenSach = new ChuoiNhap("");
        this.loaiSach = "";
        this.nhaXuatBan = "";
        this.tenTacGia = "";
        this.namXuatBan = "";
        this.tongSach = -1;
        this.choThue = -1;
    }
    public SachMoi(ISachGet iSachGet) {
        this.iSachGet=iSachGet;
        this.maSach = new ChuoiNhap(iSachGet.getMaSach().toString());
        this.tenSach = new ChuoiNhap(iSachGet.getTenSach().toString());
        this.loaiSach = iSachGet.getLoaiSach();
        this.tenTacGia = iSachGet.getTenTacGia();
        this.nhaXuatBan = iSachGet.getNhaXuatBan();
        this.namXuatBan = iSachGet.getNhaXuatBan();
        this.tongSach = iSachGet.getTongSach();
        this.choThue = iSachGet.getChoThue();
    }

    public ISachGet laySachKhongSua(){
        return this.iSachGet;
    }

    public boolean kiemTraDaSuaChua(){
        return !Objects.equals(iSachGet.getMaSach(),maSach.toString())||
                !Objects.equals(iSachGet.getTenSach(),tenSach.toString())||
                !Objects.equals(iSachGet.getTenTacGia(),tenTacGia)||
                !Objects.equals(iSachGet.getLoaiSach(),loaiSach)||
                !Objects.equals(iSachGet.getNhaXuatBan(),nhaXuatBan)||
                !Objects.equals(iSachGet.getNamXuatBan(),namXuatBan)||
                !Objects.equals(iSachGet.getTongSach(),tongSach)||
                !Objects.equals(iSachGet.getChoThue(),choThue);

    }
    @Override
    public CharSequence getMaSach() {
        return this.maSach;
    }

    @Override
    public CharSequence getTenSach() {
        return this.tenSach;
    }

    @Override
    public String getLoaiSach() {
        return this.loaiSach;
    }

    @Override
    public String getTenTacGia() {
        return this.tenTacGia;
    }

    @Override
    public String getNhaXuatBan() {
        return this.nhaXuatBan;
    }

    @Override
    public String getNamXuatBan() {
        return this.namXuatBan;
    }

    @Override
    public int getTongSach() {
        return this.tongSach;
    }

    @Override
    public int getChoThue() {
        return this.choThue;
    }

    @Override
    public void setMaSach(String maSach) {
        this.maSach.setGiaTriNhapVao(maSach);
    }

    @Override
    public void setTenSach(String tenSach) {
        this.tenSach.setGiaTriNhapVao(tenSach);
    }

    @Override
    public void setLoaiSach(String loaiSach) {
        this.loaiSach=loaiSach;
    }

    @Override
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia=tenTacGia;
    }

    @Override
    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan =nhaXuatBan;
    }

    @Override
    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan=namXuatBan;
    }

    @Override
    public void setChoThue(int choThue) {
        this.choThue=choThue;
    }

    @Override
    public void setTongSach(int tongSach) {
        this.tongSach=tongSach;

    }
}
