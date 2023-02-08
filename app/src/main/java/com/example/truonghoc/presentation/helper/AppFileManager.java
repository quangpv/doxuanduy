package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class AppFileManager {
    private static AppFileManager mAppFileManager;
    private final Application application;
    private File thuMucTamThoi, thuMucAnh;
    public MutableLiveData<String> tenAnhTamThoi =new MutableLiveData<>();

    public AppFileManager(Application applicationContext) {
        this.application = applicationContext;
    }

    public static AppFileManager getInstance() {
        return mAppFileManager;
    }

    public static void init(Application applicationContext) {
        mAppFileManager = new AppFileManager(applicationContext);
    }

    public void kiemTraVaTaoThuMucAo() {
        thuMucTamThoi = application.getExternalFilesDir("TenThuMuc");
        if (!thuMucTamThoi.exists()) {
            thuMucTamThoi.mkdir();
        }
    }

    public void kiemTraVaTaoThuMucAnh() {
        thuMucAnh = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Avatar");
        if (!thuMucAnh.exists()) {
            thuMucAnh.mkdir();
        }
    }

    public File layThuMucAnhTamThoi() {
        if (thuMucTamThoi == null) {
            kiemTraVaTaoThuMucAo();
        }
        return thuMucTamThoi;
    }

    public File layThuMucAnh() {
        if (thuMucAnh == null) {
            kiemTraVaTaoThuMucAnh();
        }
        return thuMucAnh;
    }


    public void save(Bitmap bitmap, String s) throws IOException {
        this.tenAnhTamThoi.postValue(s);
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
    public String xuLyAvatar(String maHs) {
        File tenGoc = new File(layThuMucAnhTamThoi() + "/" + tenAnhTamThoi.getValue() + ".jpg");
        File tenCanDoi = new File(layThuMucAnh() + "/" + maHs + ".jpg");
        tenGoc.renameTo(tenCanDoi);
        tenGoc.delete();
        return tenCanDoi.getPath();
    }

}