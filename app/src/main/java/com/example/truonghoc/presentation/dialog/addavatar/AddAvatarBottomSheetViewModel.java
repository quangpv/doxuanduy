package com.example.truonghoc.presentation.dialog.addavatar;

import android.net.Uri;
import android.os.Bundle;


import androidx.lifecycle.ViewModel;

import com.example.truonghoc.data.model.HocSinhDangHocEntity;
import com.example.truonghoc.presentation.helper.AppFileManager;

public class AddAvatarBottomSheetViewModel extends ViewModel {
    private String maHs;
    private final AppFileManager appFileManager = AppFileManager.getInstance();


    public void truyenMaHocSinh(Bundle bundle) {
       if(bundle==null){
           maHs=null;
       }else {
           HocSinhDangHocEntity hs = bundle.getParcelable("hs");
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
