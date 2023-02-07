package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

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

    public boolean kiemTraVaTaoThuMucAo() {
        thuMucTamThoi = application.getExternalFilesDir("TenThuMuc");
        if (!thuMucTamThoi.exists()) {
            return thuMucTamThoi.mkdir();
        }
        return true;
    }

    public boolean kiemTraVaTaoThuMucAnh() {
        thuMucAnh = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Avatar");
        if (!thuMucAnh.exists()) {
            return thuMucAnh.mkdir();
        }
        return false;
    }


    public Uri layUriThuMucAnh() {
        return Uri.fromFile(thuMucAnh);
    }
    public Uri layUriThuMucAnhTamThoi() {
        return Uri.fromFile(thuMucTamThoi);
    }
    public File layThuMucAnhTamThoi(){
        return thuMucTamThoi;
    }
    public File layThuMucAnh(){
        return thuMucAnh;
    }



    public void save(Bitmap bitmap, String s) throws IOException {
        //Vị Trí Thư Mục Lưu File
        String path = String.valueOf(application.getExternalFilesDir("TenThuMuc"));
        //Tiến Hành Lưu File
        OutputStream fOut;
        File file = new File(path, s + ".jpg");
        fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        fOut.flush();
        fOut.close();
    }

}