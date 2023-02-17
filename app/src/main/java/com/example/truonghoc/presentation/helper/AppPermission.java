package com.example.truonghoc.presentation.helper;


import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class AppPermission {
    private static AppPermission sAppPermission;
    private final Application application;
    public MutableLiveData<String> checkThatBai = new MutableLiveData<>();

    public AppPermission(Application applicationContext) {
        this.application = applicationContext;
    }

    public static AppPermission getInstance() {
        return sAppPermission;
    }

    public static void init(Application applicationContext) {
        sAppPermission = new AppPermission(applicationContext);
    }

    public List<String> dsQuyenCamera() {
        String[] quyenChinh = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        String[] quyenPhu = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        List<String> dsQuyen = Arrays.asList(quyenChinh);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            dsQuyen.add(Arrays.toString(quyenPhu));
        }
        return dsQuyen;
    }

    public boolean checkQuyenCamera() {
            for (String quyen : dsQuyenCamera()) {
                if (ContextCompat.checkSelfPermission(application, quyen) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
           return true;
    }
}
