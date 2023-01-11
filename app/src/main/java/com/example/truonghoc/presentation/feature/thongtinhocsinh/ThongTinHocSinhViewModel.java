package com.example.truonghoc.presentation.feature.thongtinhocsinh;

import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;

public class ThongTinHocSinhViewModel extends ViewModel {
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();
    private final AppExecutors executors = AppExecutors.getInstance();

    public void capNhapLaiThongTinHocSinh(HocSinhDangHoc hocSinh) {
        executors.execute(() -> {
            database.hocSinhDAO().suaHocSinh(hocSinh);
        });
    }
}
