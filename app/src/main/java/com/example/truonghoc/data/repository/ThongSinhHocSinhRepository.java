package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.HocSinhDangHocDataBase;
import com.example.truonghoc.data.model.HocSinhDangHocEntity;
import com.example.truonghoc.data.model.HocSinhEntity;
import com.example.truonghoc.data.model.KhoiEntity;
import com.example.truonghoc.domain.bo.StringDate;
import com.example.truonghoc.domain.ui.IChiTietHocSinh;
import com.example.truonghoc.domain.ui.IDate;

public class ThongSinhHocSinhRepository {
    private static final ThongSinhHocSinhRepository sInstance = new ThongSinhHocSinhRepository();
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();

    public static ThongSinhHocSinhRepository getInstance() {
        return sInstance;
    }

    public IChiTietHocSinh findById(String id) {
        HocSinhDangHocEntity hocSinhDangHoc = database.hocSinhDAO().getHocSinh(id);
        if (hocSinhDangHoc == null) return null;
        return new IChiTietHocSinh() {
            @Override
            public String getLop() {
                return hocSinhDangHoc.getKhoiLop().getKhoiLop();
            }

            @Override
            public String getId() {
                return hocSinhDangHoc.getHocSinh().getMaHocSinh();
            }

            @Override
            public String getName() {
                return hocSinhDangHoc.getHocSinh().getHoVaTen();
            }

            @Override
            public String getGender() {
                return hocSinhDangHoc.getHocSinh().getGioiTinh();
            }

            @Override
            public IDate getDob() {
                return new StringDate(hocSinhDangHoc.getHocSinh().getSinhNgay());
            }
        };
    }

    public void save(IChiTietHocSinh hocSinh) {
        HocSinhDangHocEntity hocSinhDangHoc = database.hocSinhDAO().getHocSinh(hocSinh.getId());
        HocSinhDangHocEntity hoc = new HocSinhDangHocEntity(new HocSinhEntity(
                "",
                hocSinh.getId(),
                hocSinh.getName(),
                hocSinh.getGender(),
                hocSinh.getDob().toString()
        ), new KhoiEntity(hocSinh.getLop()));
        hoc.Id = hocSinhDangHoc.Id;
        database.hocSinhDAO().suaHocSinh(hoc);
    }

    public void add(IChiTietHocSinh hocSinh) {
        HocSinhDangHocEntity hoc = new HocSinhDangHocEntity(new HocSinhEntity(
                "",
                hocSinh.getId(),
                hocSinh.getName(),
                hocSinh.getGender(),
                hocSinh.getDob().toString()
        ), new KhoiEntity(hocSinh.getLop()));
        database.hocSinhDAO().themHocSinh(hoc);
    }

    public boolean exists(IChiTietHocSinh hocSinh1) {
        return database.hocSinhDAO().daTonTai(hocSinh1.getId());
    }

    public void delete(String id) {
        database.hocSinhDAO().xoaHocSinhById(id);
    }
}
