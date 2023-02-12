package com.example.truonghoc.presentation.dialog.addavatar;

import android.net.Uri;
import android.os.Bundle;


import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppFileManager;

public class AddAvatarBottomSheetViewModel extends ViewModel {
    private Bundle bundle;
    private String maHs;
    private final AppFileManager appFileManager = AppFileManager.getInstance();
    private final AppExecutors appExecutors = AppExecutors.getInstance();

    public void truyenMaHocSinh(Bundle bundle) {
        this.bundle = bundle;
    }

    public String layMaHocSinh() {
        if (bundle == null) {
            maHs = null;
        } else {
            HocSinhDangHoc hs = bundle.getParcelable("hs");
            maHs = hs.getHocSinh().getMaHocSinh();
        }
        return maHs;
    }


    public void luuAnhTuThuVien(Uri result) {
            appFileManager.anhTamThoi.setValue(result);
            String a = layMaHocSinh();
            appFileManager.luuAnhVaTraVeUriAvarta(layMaHocSinh());

    }
}
