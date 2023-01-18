package com.example.truonghoc.presentation.feature.themhocsinh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.domain.HocSinh;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.domain.KhoiLop;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppResources;

import java.util.concurrent.Executors;

public class ThemHocSinhViewModel extends ViewModel {
    public MutableLiveData<String> themThanhCong = new MutableLiveData<>();
    public MutableLiveData<String> themThatBai = new MutableLiveData<>();
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();
    private final AppExecutors executors = AppExecutors.getInstance();

    public void themHocSinh(String maHs, String tenHs, String gioiTinh, String sinhNgay, String khoiLop) {
        executors.execute(() -> {
            if (checkThongTinToiThieu(maHs, tenHs)) {
                themThatBai.postValue("Tối Thiểu Cần Tên Và Mã Hs");
                return;
            }
            if (kiemTraHocSinhTonTai(maHs)) {
                themThatBai.postValue("Mã Học Sinh Đã Tồn Tại");
                return;
            }
            HocSinhDangHoc hocSinh = new HocSinhDangHoc(new HocSinh(maHs, tenHs, gioiTinh, sinhNgay), new KhoiLop(khoiLop));

            database.hocSinhDAO().themHocSinh(hocSinh);
            themThanhCong.postValue("Thêm học sinh thành công");
        });
    }

    private boolean kiemTraHocSinhTonTai(String maHs) {
        return database.hocSinhDAO().daTonTai(maHs);
    }

    private boolean checkThongTinToiThieu(String maHs, String tenHs) {
        return maHs.isEmpty() || tenHs.isEmpty();
    }
}
