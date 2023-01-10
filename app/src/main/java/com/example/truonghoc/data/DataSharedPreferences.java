package com.example.truonghoc.data;

import android.content.Context;
import android.content.SharedPreferences;

public class DataSharedPreferences {
    private static final String lanDau = "LAN_DAU_CAIDAT";
    private static final String TRUONGHOC = "TRUONG_HOC";
    private final Context mContext;

    public DataSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void nhapTruongHoc(String key, String value) {
        SharedPreferences info = mContext.getSharedPreferences(TRUONGHOC, Context.MODE_PRIVATE);
        info.edit().putString(key, value).apply();
    }

    public String layTruongHoc(String key){
        SharedPreferences info = mContext.getSharedPreferences(TRUONGHOC, Context.MODE_PRIVATE);
        return info.getString(key,"");
    }
}
