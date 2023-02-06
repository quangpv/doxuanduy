package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class AppFileManager {
    private static AppFileManager mAppFileManager;
    private final Application application;
    private File thuMucTamThoi, thuMucAnh;

    public AppFileManager(Application applicationContext) {
        this.application = applicationContext;
    }

    public static AppFileManager getInstance() {
        return mAppFileManager;
    }

    public static void init(Application applicationContext) {
        mAppFileManager = new AppFileManager(applicationContext);
    }

    public boolean kiemTraVaTaoThuMuc() {
        thuMucTamThoi = application.getExternalFilesDir("TenThuMuc");
        if (!thuMucTamThoi.exists()) {
            return thuMucTamThoi.mkdir();
        }
//        thuMuc = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Avatar");
        return true;
    }


    public File layThuMucTamThoi() {
        return thuMucTamThoi;
    }


    public Uri save(Bitmap bitmap, String s) throws IOException {
        //Vị Trí Thư Mục Lưu File
        String path = String.valueOf(application.getExternalFilesDir("TenThuMuc"));
        //Tiến Hành Lưu File
        OutputStream fOut;
        File file = new File(path, s + ".jpg");
        fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        fOut.flush();
        fOut.close();
        Log.e("Exists", file.exists() + "");
        return Uri.fromFile(file);
    }

}