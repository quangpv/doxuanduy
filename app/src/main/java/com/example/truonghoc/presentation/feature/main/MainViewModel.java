package com.example.truonghoc.presentation.feature.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.R;
import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppResources;

public class MainViewModel extends ViewModel {
    private final AppResources appResources = AppResources.getInstance();
    private final QuanLyData quanLyData = QuanLyData.getInstance();
    private final AppExecutors executors = AppExecutors.getInstance();

    public MutableLiveData<String> tieuDeTCC = new MutableLiveData<>();
    public MutableLiveData<ThongTinTruongHoc> thongTinTruongHoc = new MutableLiveData<>();

    public void setLoai(int navId) {
        int stringId = navId == R.id.nav_hoc_sinh ? R.string.tieude_hoc_sinh : R.string.tieude_giao_vien;
        tieuDeTCC.setValue(appResources.getString(stringId));
    }

    public void luuThongTinTruongHoc(ThongTinTruongHoc thongTinTruongHoc) {
        executors.execute(() -> quanLyData.setThongTinTruong(thongTinTruongHoc));
    }

    public void layThongTinTruong() {
        executors.execute(() -> thongTinTruongHoc.postValue(quanLyData.layThongTinTruong()));
    }
}