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


    public MutableLiveData<String> tieuDeTCC() {
        if (tieuDeTCC == null) {
            tieuDeTCC = new MutableLiveData<>();
        }
        return tieuDeTCC;
    }
}
