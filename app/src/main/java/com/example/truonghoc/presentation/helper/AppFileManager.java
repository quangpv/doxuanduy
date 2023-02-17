package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class AppFileManager {
    private static AppFileManager mAppFileManager;
    private final Application application;
    private File thuMucTamThoi, thuMucAnh;
    public MutableLiveData<Uri> anhTamThoi = new MutableLiveData<>();

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
            thuMucTamThoi.mkdirs();
        }
    }

    public void kiemTraVaTaoThuMucAnh() {
        thuMucAnh = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Avatar");
        if (!thuMucAnh.exists()) {
            thuMucAnh.mkdirs();
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

    public String luuAnhVaTraVeUriAvarta(String maHs) {
        String viTriLuu = layThuMucAnh() + "/" + maHs + ".jpg";
        Uri uri = anhTamThoi.getValue();
        if(uri==null) return viTriLuu;
        xuLyHinhAnh(uri,viTriLuu);
        xoaAnhTamThoi();
        return viTriLuu;
    }

    private boolean kiemTraUriThuocLoaiNao(Uri uri) {
        return uri.toString().contains("content://");
    }

    private void xuLyHinhAnh(Uri input, String outputPath) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if(kiemTraUriThuocLoaiNao(input))inputStream = application.getContentResolver().openInputStream(input);
            else inputStream = new FileInputStream(input.getPath());
            byte[] bytes = new byte[ inputStream.available()];
            //bytes có kích thước mảng là 138395byte. giá trị từng byte {-1,-40,1, vân vân}
            if (inputStream.read(bytes) > 0) {
                fileOutputStream = new FileOutputStream(outputPath);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
                if(!kiemTraUriThuocLoaiNao(input)) new File(input.getPath()).delete();
            } catch (IOException ignored) {
            }
        }
    }

    public void xoaAnhTamThoi() {
        anhTamThoi.postValue(Uri.parse(""));
    }
}