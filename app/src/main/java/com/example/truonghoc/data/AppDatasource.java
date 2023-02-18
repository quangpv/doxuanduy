package com.example.truonghoc.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.google.gson.Gson;

public class AppDatasource {
    private static final String TRUONGHOC = "TRUONG_HOC";
    private static AppDatasource sAppDatasource;
    private final SharedPreferences sharedPreferences;
    private final Gson parser = new Gson();

    public AppDatasource(Application application) {
        sharedPreferences = application.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public static void init(Application application) {
        sAppDatasource = new AppDatasource(application);
    }

    public static AppDatasource getInstance() {
        return sAppDatasource;
    }

    public void setThongTinTruong(ThongTinTruongHoc truongHoc) {
        sharedPreferences.edit().putString(TRUONGHOC, parser.toJson(truongHoc)).apply();
    }

    public ThongTinTruongHoc layThongTinTruong() {
        String truongHocStr = sharedPreferences.getString(TRUONGHOC, "");
        if (truongHocStr.isEmpty()) return new ThongTinTruongHoc("", "", "");
        return parser.fromJson(truongHocStr, ThongTinTruongHoc.class);
    }
}
