package com.example.truonghoc.presentation;

import android.app.Application;

import com.example.truonghoc.data.QuanLyData;

public class ChuongTrinh extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QuanLyData.init(getApplicationContext());
    }
}

