package com.example.truonghoc.presentation.feature.hocsinh;


import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public class HocSinhViewModel extends ViewModel {
    private final AppExecutors executors = AppExecutors.getInstance();
    private final HocSinhDangHocDataBase dataBase = HocSinhDangHocDataBase.getInstance();

    public final MediatorLiveData<List<HocSinhDangHoc>> danhSachHocSinh = new MediatorLiveData<>();

    public void timKiem(String tuKhoa) {
        executors.execute(() -> {
            List<HocSinhDangHoc> tatCaHocSinh = dataBase.hocSinhDAO().layTatCaHocSinh();

            List<HocSinhDangHoc> danhSachLoc = new ArrayList<>();
            if (tuKhoa.isEmpty()) {
                danhSachHocSinh.postValue(tatCaHocSinh);
                return;
            }

            for (HocSinhDangHoc hocSinh : tatCaHocSinh) {
                if (dieuKien(hocSinh, tuKhoa)) {
                    danhSachLoc.add(hocSinh);
                }
            }
            danhSachHocSinh.postValue(danhSachLoc);
        });
    }

    private boolean dieuKien(HocSinhDangHoc hocSinh, String s) {
        return xoaDauChu(hocSinh.getHocSinh().getHoVaTen()).contains(xoaDauChu(s));
    }

    private String xoaDauChu(String noiDungDau) {
        return Normalizer.normalize(noiDungDau, Normalizer.Form.NFD)
                .toLowerCase()
                .replaceAll("\\p{M}", "")
                .replace('đ', 'd')
                .replace('Đ', 'D');
    }
}
