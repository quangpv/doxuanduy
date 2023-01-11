package com.example.truonghoc.presentation.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.HocSinhDangHoc;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FragmentHocSinhViewModel extends ViewModel {

    private MutableLiveData<List<HocSinhDangHoc>> danhSachTimKiem;

    public MutableLiveData<List<HocSinhDangHoc>> getDanhSach() {
        if (danhSachTimKiem == null) {
            danhSachTimKiem = new MutableLiveData<>();
        }
        return danhSachTimKiem;
    }

    public void timKiem(String s, List<HocSinhDangHoc> danhSachRoom) {
        List<HocSinhDangHoc> danhSachLoc = new ArrayList<>();
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60L, TimeUnit.SECONDS,linkedBlockingQueue);
        if (s.isEmpty()) {
            danhSachTimKiem.postValue(danhSachRoom);
        } else {
            for (HocSinhDangHoc hocSinh: danhSachRoom) {
                threadPoolExecutor.execute(() -> {
                    if(dieuKien(hocSinh,s)){
                        danhSachLoc.add(hocSinh);
                    }
                });
            }
            danhSachTimKiem.postValue(danhSachLoc);
        }
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