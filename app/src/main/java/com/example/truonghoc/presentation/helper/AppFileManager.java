package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
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

    public void kiemTraVaTaoThuMucTamThoi() {
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
            kiemTraVaTaoThuMucTamThoi();
        }
        return thuMucTamThoi;
    }

    public File layThuMucAnh() {
        if (thuMucAnh == null) {
            kiemTraVaTaoThuMucAnh();
        }
        return thuMucAnh;
    }


    public void saveAnhTamThoi(Bitmap bitmap, String s) throws IOException {
        this.tenAnhTamThoi.postValue(s);
        OutputStream fOut;
        File file = new File(String.valueOf(layThuMucAnhTamThoi()), s + ".jpg");
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


    public void saveDaCoMaHs(Bitmap bitmap, String maHocSinh) throws IOException{
        OutputStream fOut;
        File file = new File(String.valueOf(layThuMucAnh()), maHocSinh + ".jpg");
        fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        fOut.flush();
        fOut.close();
    }

    public void xoaAnhTamThoi() {
        File tenGoc = new File(layThuMucAnhTamThoi() + "/" + tenAnhTamThoi.getValue() + ".jpg");
        tenGoc.delete();
    }
}