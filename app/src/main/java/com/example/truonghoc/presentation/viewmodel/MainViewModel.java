package com.example.truonghoc.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.domain.ThongTinTruongHoc;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MainViewModel extends ViewModel {
    MutableLiveData<String> tieuDeTCC;
    MutableLiveData<ThongTinTruongHoc> thongTinTruong;


    public MutableLiveData<String> tieuDeTCC() {
        if (tieuDeTCC == null) {
            tieuDeTCC = new MutableLiveData<>();
        }
        return tieuDeTCC;
    }

    public MutableLiveData<ThongTinTruongHoc> thongTinTruong() {
        if (thongTinTruong == null) {
            thongTinTruong = new MutableLiveData<>();
        }
        return thongTinTruong;
    }

    public void luuThongTinTruong(String tenTruong, String diaChi, String sdtTruong) {
        ThongTinTruongHoc info = new ThongTinTruongHoc(tenTruong, diaChi, sdtTruong);
        Executors.newCachedThreadPool().execute(() -> {
            QuanLyData.setThongTinTruong(info);
            thongTinTruong.postValue(info);
        });
    }
}
