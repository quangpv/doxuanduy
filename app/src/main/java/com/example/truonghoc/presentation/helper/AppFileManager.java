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

    public String luuAnhVaTraVeUriAvarta(String maHs) {
        String viTriLuu = layThuMucAnh() + "/" + maHs + ".jpg";
        Uri uri = anhTamThoi.getValue();
        if(uri==null) return viTriLuu;
        if (kiemTraUriThuocLoaiNao(uri))
            xuLyAnhTuThuVien(uri, viTriLuu);
        else xuLyAnhTuCamera(uri.getPath(), viTriLuu);
        xoaAnhTamThoi();
        return viTriLuu;
    }

    private boolean kiemTraUriThuocLoaiNao(Uri uri) {
        return uri.toString().contains("content://");
    }


    private void xuLyAnhTuCamera(String input, String output) {
        File tenGoc = new File(input);
        File tenCanDoi = new File(output);
        if (tenGoc.renameTo(tenCanDoi)) Log.i("doiten", "ok");
        else Log.i("doiten", "sida");

        tenGoc.delete();
    }

    private void xuLyAnhTuThuVien(Uri input, String outputPath) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            // lấy đối tượng trong bộ nhớ
            inputStream = application.getContentResolver().openInputStream(input);
            // lấy kích thước của đối tượng - > chuyển sang byte
            int a = inputStream.available();
            byte[] bytes = new byte[a];
            //bytes có kích thước mảng là 138395byte. giá trị từng byte {-1,-40,1, vân vân}
            if (inputStream.read(bytes) > 0) {
                fileOutputStream = new FileOutputStream(outputPath,false);
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

    public void xoaAnhTamThoi() {
        anhTamThoi.postValue(Uri.parse(""));
    }
}