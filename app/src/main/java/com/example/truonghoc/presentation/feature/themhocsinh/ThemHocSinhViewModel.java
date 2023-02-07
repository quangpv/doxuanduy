package com.example.truonghoc.presentation.feature.themhocsinh;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.domain.HocSinh;
import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.domain.KhoiLop;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppFileManager;

import java.io.File;

public class ThemHocSinhViewModel extends ViewModel {
    public MutableLiveData<String> themThanhCong = new MutableLiveData<>();
    public MutableLiveData<String> themThatBai = new MutableLiveData<>();
    public HocSinhDangHocDataBase database = HocSinhDangHocDataBase.getInstance();
    public String tenAnhTamThoi;
    private final AppExecutors executors = AppExecutors.getInstance();
    private final AppFileManager appFileManager = AppFileManager.getInstance();

    public void themHocSinh(String maHs, String tenHs, String gioiTinh, String sinhNgay, String khoiLop) {
        executors.execute(() -> {
            String uriAvatar;
            if (checkThongTinToiThieu(maHs, tenHs)) {
                themThatBai.postValue("Tối Thiểu Cần Tên Và Mã Hs");
                return;
            }
            if (kiemTraHocSinhTonTai(maHs)) {
                themThatBai.postValue("Mã Học Sinh Đã Tồn Tại");
                return;
            }
            uriAvatar = xuLyAvatar(maHs);
            HocSinhDangHoc hocSinh = new HocSinhDangHoc(new HocSinh(uriAvatar,maHs, tenHs, gioiTinh, sinhNgay), new KhoiLop(khoiLop));
            database.hocSinhDAO().themHocSinh(hocSinh);
            themThanhCong.postValue("Thêm học sinh thành công");
        });
    }

    private String  xuLyAvatar(String maHs) {
        appFileManager.kiemTraVaTaoThuMucAnh();
        File thuMucTamThoi = appFileManager.layThuMucAnhTamThoi();
        File thuMucAnh = appFileManager.layThuMucAnh();
        File tenGoc = new File(thuMucTamThoi,tenAnhTamThoi+".jpg");
        File tenCanDoi = new File(thuMucAnh,maHs+".jpg");

        if(doiTenVaDiChuyenAnh(tenGoc,tenCanDoi)){
            // Xóa Ảnh Ảnh
            if(tenGoc.delete()){
                Log.i("xoaAnhTamThoi","ok");
            }
            // Xóa Tên Ảnh Tạm Thời
            tenAnhTamThoi=null;
            return  tenCanDoi.getPath();
        }else {
            return null;
        }
    }

    private boolean doiTenVaDiChuyenAnh(File tenGoc, File tenCanDoi) {
        return tenGoc.renameTo(tenCanDoi);
    }


    private boolean kiemTraHocSinhTonTai(String maHs) {
        return database.hocSinhDAO().daTonTai(maHs);
    }

    private boolean checkThongTinToiThieu(String maHs, String tenHs) {
        return maHs.isEmpty() || tenHs.isEmpty();
    }

    public void truyenTenAnh(String tenAnhTamThoi) {
        this.tenAnhTamThoi = tenAnhTamThoi;
    }
}
