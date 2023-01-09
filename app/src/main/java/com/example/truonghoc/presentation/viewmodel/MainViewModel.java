package com.example.truonghoc.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.ThongTinTruongHoc;

public class MainViewModel extends ViewModel {
    MutableLiveData<String> tieuDeTCC;
    MutableLiveData<ThongTinTruongHoc> thongTinTruong;

    public MutableLiveData<String> tieuDeTCC() {
        if (tieuDeTCC == null) {
            tieuDeTCC = new MutableLiveData<>();
        }
        return tieuDeTCC;
    }
    public MutableLiveData<ThongTinTruongHoc> thongTinTruong(){
        if(thongTinTruong ==null){
            thongTinTruong = new MutableLiveData<>();
        }return thongTinTruong;
    }
}
