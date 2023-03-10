package com.example.quanlythuvien.view;

import android.app.Application;

import com.example.quanlythuvien.data.ThuVienDataBase;
import com.example.quanlythuvien.view.help.AppExecutors;

public class ChuongTrinh extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ThuVienDataBase.init(this);
    }
}
