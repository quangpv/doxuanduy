package com.example.quanlythuvien.view.feature.sach;

import androidx.lifecycle.MutableLiveData;

import com.example.quanlythuvien.domain.ui.ISachItemList;
import com.example.quanlythuvien.view.base.BaseViewModel;

import java.util.List;

public class SachViewModel extends BaseViewModel {
    MutableLiveData<List<ISachItemList>> danhSach = new MutableLiveData<>();
    private String sSeachl = "";

    public void search(String s) {
        sSeachl = s;
        appExecutors.execute(() -> danhSach.postValue(sachKho.laySach(s)));
    }

    public void khoiDong() {
        search(sSeachl);
    }

    public void xoaSach(ISachItemList itemList) {
        appExecutors.execute(() -> sachKho.xoaSachTheoMaSach(itemList.getMaSach()));

    }

}
