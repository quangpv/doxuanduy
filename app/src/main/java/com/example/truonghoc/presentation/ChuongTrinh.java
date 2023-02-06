package com.example.truonghoc.presentation;

import android.app.Application;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.presentation.helper.AppFileManager;
import com.example.truonghoc.presentation.helper.AppPermission;
import com.example.truonghoc.presentation.helper.AppResources;

public class ChuongTrinh extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppResources.init(this);
        QuanLyData.init(this);
        AppPermission.init(this);
        AppFileManager.init(this);
        HocSinhDangHocDataBase.init(this);
    }
}

