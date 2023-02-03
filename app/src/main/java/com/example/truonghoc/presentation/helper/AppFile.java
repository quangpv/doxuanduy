package com.example.truonghoc.presentation.helper;

import android.app.Application;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class AppFile {
    private static AppFile mAppFile;
    private final Application application;
    private File thuMuc;

    public AppFile(Application applicationContext) {
        this.application = applicationContext;
    }

    public static AppFile getInstance() {
        return mAppFile;
    }

    public static void init(Application applicationContext) {
        mAppFile = new AppFile(applicationContext);
    }

    public void taoThuMuc() {
        thuMuc = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Avatar");
    }

    public boolean kiemTraFolderTonTai() {
        return thuMuc.exists();
    }
    public File layThuMuc(){
        return thuMuc;
    }
}
