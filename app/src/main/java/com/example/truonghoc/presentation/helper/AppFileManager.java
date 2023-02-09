package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


public class AppFileManager {
    private static AppFileManager mAppFileManager;
    private final Application application;
    private File thuMucTamThoi, thuMucAnh;
    public MutableLiveData<Uri> anhTamThoi =new MutableLiveData<>();

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
        OutputStream fOut;
        File file = new File(String.valueOf(layThuMucAnhTamThoi()), s + ".jpg");
        this.anhTamThoi.postValue(Uri.fromFile(file));
        fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
        fOut.flush();
        fOut.close();

    }

    public String xuLyAvatar(String maHs) {
        File tenGoc = new File(Objects.requireNonNull(anhTamThoi.getValue()).getPath());
        File tenCanDoi = new File(layThuMucAnh() + "/" + maHs + ".jpg");
        if(tenGoc.renameTo(tenCanDoi)){
            Log.i("doiten","ok");
        }else {
            Log.i("doiten","sida");
        }
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
        File tenGoc = new File(Objects.requireNonNull(anhTamThoi.getValue()).getPath());
        tenGoc.delete();
    }
}