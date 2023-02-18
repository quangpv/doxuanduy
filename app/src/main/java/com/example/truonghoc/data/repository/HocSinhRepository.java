package com.example.truonghoc.data.repository;

import com.example.truonghoc.data.datasource.HocSinhDangHocDAO;
import com.example.truonghoc.data.datasource.HocSinhDangHocDataBase;
import com.example.truonghoc.data.model.HocSinh;
import com.example.truonghoc.data.model.HocSinhDangHoc;
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
        List<HocSinhDangHoc> hocSinhDangHocs = hocSinhDangHocDAO.layTatCaHocSinh();
        ArrayList<IHocSinh> hocSinhs = new ArrayList<>();
        for (HocSinhDangHoc hocSinhDangHoc : hocSinhDangHocs) {
            if (accept(searchKey1, hocSinhDangHoc)) hocSinhs.add(createHocSinh(hocSinhDangHoc));
        }
        return hocSinhs;
    }

    private boolean accept(String searchKey, HocSinhDangHoc hocSinhDangHoc) {
        if (searchKey.isEmpty()) return true;
        HocSinh hocSinh = hocSinhDangHoc.getHocSinh();
        return hocSinhDangHoc.getHocSinh().getMaHocSinh().toLowerCase(Locale.ROOT).contains(searchKey)
                || hocSinh.getGioiTinh().contains(searchKey)
                || hocSinh.getHoVaTen().contains(searchKey);
    }

    private IHocSinh createHocSinh(HocSinhDangHoc hocSinhDangHoc) {
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
