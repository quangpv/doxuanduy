package com.example.truonghoc.presentation;

import android.app.Application;

import com.example.truonghoc.data.datasource.HocSinhDangHocDataBase;
import com.example.truonghoc.data.datasource.AppDatasource;
import com.example.truonghoc.presentation.helper.AppFileManager;
import com.example.truonghoc.presentation.helper.AppPermission;
import com.example.truonghoc.presentation.helper.AppResources;

public class ChuongTrinh extends Application {
    private static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AppResources.init(this);
        AppDatasource.init(this);
        AppPermission.init(this);
        AppFileManager.init(this);
        HocSinhDangHocDataBase.init(this);
    }

    public static Application getInstance() {
        return ChuongTrinh.sInstance;
    }
}

