package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;

import androidx.camera.core.internal.ByteBufferOutputStream;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


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
        Uri uri = anhTamThoi.getValue();
        if (uri == null) return "";
        String outputPath = layThuMucAnh() + "/" + maHs + ".jpg";
        if (uri.toString().contains("content://")) xuLyAnhTuThuVien(uri, outputPath);
        else xuLyAnhTuCamera(uri.getPath(), outputPath);
        return outputPath;
    }

    private void xuLyAnhTuCamera(String input, String output) {
        File tenGoc = new File(input);
        File tenCanDoi = new File(output);
        if (tenGoc.renameTo(tenCanDoi)) {
            Log.i("doiten", "ok");
        } else {
            Log.i("doiten", "sida");
        }
        tenGoc.delete();
    }

    private void xuLyAnhTuThuVien(Uri input, String outputPath) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = application.getContentResolver().openInputStream(input);
            byte[] bytes = new byte[inputStream.available()];
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
            } catch (IOException ignored) {
            }
        }
    }


    public void saveDaCoMaHs(Bitmap bitmap, String maHocSinh) throws IOException {
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