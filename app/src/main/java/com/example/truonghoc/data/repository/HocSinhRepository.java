package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.HocSinhDangHocDAO;
import com.example.truonghoc.data.datasource.HocSinhDangHocDataBase;
import com.example.truonghoc.data.model.HocSinhEntity;
import com.example.truonghoc.data.model.HocSinhDangHocEntity;
import com.example.truonghoc.domain.ui.IHocSinh;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HocSinhRepository {
    private static final HocSinhRepository sInstance = new HocSinhRepository();
    private final HocSinhDangHocDAO hocSinhDangHocDAO = HocSinhDangHocDataBase.getInstance().hocSinhDAO();

    public static HocSinhRepository getInstance() {
        return sInstance;
    }

    public List<IHocSinh> getHocSinhList(String searchKey) {
        String searchKey1 = searchKey.toLowerCase(Locale.ROOT);
        List<HocSinhDangHocEntity> hocSinhDangHocs = hocSinhDangHocDAO.layTatCaHocSinh();
        ArrayList<IHocSinh> hocSinhs = new ArrayList<>();
        for (HocSinhDangHocEntity hocSinhDangHoc : hocSinhDangHocs) {
            if (accept(searchKey1, hocSinhDangHoc)) hocSinhs.add(createHocSinh(hocSinhDangHoc));
        }
        return hocSinhs;
    }

    private boolean accept(String searchKey, HocSinhDangHocEntity hocSinhDangHoc) {
        if (searchKey.isEmpty()) return true;
        HocSinhEntity hocSinh = hocSinhDangHoc.getHocSinh();
        return hocSinhDangHoc.getHocSinh().getMaHocSinh().toLowerCase(Locale.ROOT).contains(searchKey)
                || hocSinh.getGioiTinh().contains(searchKey)
                || hocSinh.getHoVaTen().contains(searchKey);
    }

    private IHocSinh createHocSinh(HocSinhDangHocEntity hocSinhDangHoc) {
        return new IHocSinh() {
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
                String gioiTinh = hocSinhDangHoc.getHocSinh().getGioiTinh();
                if (gioiTinh.trim().length() != 0) return gioiTinh;
                return "Không rõ giới tính";
            }

            @Override
            public String getDob() {
                String ngaySinh = hocSinhDangHoc.getHocSinh().getSinhNgay();
                if (ngaySinh.isEmpty()) return "Không rõ ngày sinh";
                return ngaySinh;
            }
        };
    }
}
