package com.example.truonghoc.data;

import android.content.Context;

import com.example.truonghoc.domain.ThongTinTruongHoc;
import com.google.gson.Gson;

public class QuanLyData {
    private static final String TRUONGHOC = "TRUONG_HOC";
    private static QuanLyData quanLyData;
    private DataSharedPreferences dataSharedPreferences;

    public static void init(Context context) {
        quanLyData = new QuanLyData();
        quanLyData.dataSharedPreferences = new DataSharedPreferences(context);
    }

    public static QuanLyData getInstance() {
        if (quanLyData == null) {
            synchronized (QuanLyData.class) {
                quanLyData = new QuanLyData();
            }
        }
        return quanLyData;
    }
    public static void setThongTinTruong(ThongTinTruongHoc truongHoc) {
        Gson truongHocLuu = new Gson();
        String infoTruong = truongHocLuu.toJson(truongHoc);
        QuanLyData.getInstance().dataSharedPreferences.nhapTruongHoc(TRUONGHOC, infoTruong);
    }

    public static ThongTinTruongHoc layThongTinTruong() {
        String thongTin = QuanLyData.getInstance().dataSharedPreferences.layTruongHoc(TRUONGHOC);
        Gson doiTuong = new Gson();
        return doiTuong.fromJson(thongTin, ThongTinTruongHoc.class);
    }
}
