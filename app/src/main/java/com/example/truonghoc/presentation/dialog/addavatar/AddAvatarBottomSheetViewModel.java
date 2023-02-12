package com.example.truonghoc.presentation.dialog.addavatar;

import android.net.Uri;
import android.os.Bundle;


import androidx.lifecycle.ViewModel;

import com.example.truonghoc.domain.HocSinhDangHoc;
import com.example.truonghoc.presentation.helper.AppExecutors;
import com.example.truonghoc.presentation.helper.AppFileManager;

public class AddAvatarBottomSheetViewModel extends ViewModel {
    private String maHs;
    private final AppFileManager appFileManager = AppFileManager.getInstance();
    private final AppExecutors appExecutors = AppExecutors.getInstance();

    public void truyenMaHocSinh(Bundle bundle) {
       if(bundle==null){
           maHs=null;
       }else {
           HocSinhDangHoc hs = bundle.getParcelable("hs");
           maHs = hs.getHocSinh().getMaHocSinh();
       }
    }

    public String layMaHocSinh() {
        return maHs;
    }


    public void luuAnhTuThuVien(Uri result) {
            appFileManager.anhTamThoi.setValue(result);
            if(maHs!=null){
                appFileManager.luuAnhVaTraVeUriAvarta(maHs);
                maHs=null;
            }
    }
}
