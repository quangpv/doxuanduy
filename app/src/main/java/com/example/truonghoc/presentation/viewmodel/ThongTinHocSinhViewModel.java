package com.example.truonghoc.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.domain.HocSinhDangHoc;

import java.util.concurrent.Executors;

public class ThongTinHocSinhViewModel extends ViewModel {
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();


    public void capNhapLaiThongTinHocSinh(HocSinhDangHoc hocSinh) {
        Executors.newCachedThreadPool().execute(() -> database.hocSinhDAO().suaHocSinh(hocSinh));
    }
}
