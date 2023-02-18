package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.HocSinhDangHocDataBase;
import com.example.truonghoc.data.model.HocSinh;
import com.example.truonghoc.data.model.HocSinhDangHoc;
import com.example.truonghoc.domain.ui.IChiTietHocSinh;
import com.example.truonghoc.data.model.KhoiLop;

public class ThongSinhHocSinhRepository {
    private static final ThongSinhHocSinhRepository sInstance = new ThongSinhHocSinhRepository();
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();

    public static ThongSinhHocSinhRepository getInstance() {
        return sInstance;
    }

    public IChiTietHocSinh findById(String id) {
        HocSinhDangHoc hocSinhDangHoc = database.hocSinhDAO().getHocSinh(id);
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
            public String getDob() {
                return hocSinhDangHoc.getHocSinh().getSinhNgay();
            }
        };
    }

    public void save(IChiTietHocSinh hocSinh) {
        HocSinhDangHoc hocSinhDangHoc = database.hocSinhDAO().getHocSinh(hocSinh.getId());
        HocSinhDangHoc hoc = new HocSinhDangHoc(new HocSinh(
                "",
                hocSinh.getId(),
                hocSinh.getName(),
                hocSinh.getGender(),
                hocSinh.getDob()
        ), new KhoiLop(hocSinh.getLop()));
        hoc.Id = hocSinhDangHoc.Id;
        database.hocSinhDAO().suaHocSinh(hoc);
    }

    public void add(IChiTietHocSinh hocSinh) {
        HocSinhDangHoc hoc = new HocSinhDangHoc(new HocSinh(
                "",
                hocSinh.getId(),
                hocSinh.getName(),
                hocSinh.getGender(),
                hocSinh.getDob()
        ), new KhoiLop(hocSinh.getLop()));
        database.hocSinhDAO().themHocSinh(hoc);
    }

    public boolean exists(IChiTietHocSinh hocSinh1) {
        return database.hocSinhDAO().daTonTai(hocSinh1.getId());
    }

    public void delete(String id) {
        database.hocSinhDAO().xoaHocSinhById(id);
    }
}
