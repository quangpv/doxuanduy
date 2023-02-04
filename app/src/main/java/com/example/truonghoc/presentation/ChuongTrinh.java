package com.example.truonghoc.presentation;

import android.app.Application;

import com.example.truonghoc.data.HocSinhDangHocDataBase;
import com.example.truonghoc.data.QuanLyData;
import com.example.truonghoc.presentation.camera.FileManager;
import com.example.truonghoc.presentation.helper.AppFile;
import com.example.truonghoc.presentation.helper.AppPermission;
import com.example.truonghoc.presentation.helper.AppResources;

public class ChuongTrinh extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileManager.init(this);
        AppResources.init(this);
        QuanLyData.init(this);
        AppPermission.init(this);
        AppFile.init(this);
        HocSinhDangHocDataBase.init(getApplicationContext());
    }
}

