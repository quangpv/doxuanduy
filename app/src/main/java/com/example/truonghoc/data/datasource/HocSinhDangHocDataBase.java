package com.example.truonghoc.data.datasource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.truonghoc.data.model.HocSinhDangHocEntity;

@Database(entities = {HocSinhDangHocEntity.class}, version = 1)
public abstract class HocSinhDangHocDataBase extends RoomDatabase {
    private static final String DATA_BASE_NAME = "hocSinh.db";
    private static HocSinhDangHocDataBase hocSinhData;

    public static void init(Context applicationContext) {
        hocSinhData = Room.databaseBuilder(applicationContext, HocSinhDangHocDataBase.class, DATA_BASE_NAME)
                .build();
    }

    public static HocSinhDangHocDataBase getInstance() {
        return hocSinhData;
    }

    public abstract HocSinhDangHocDAO hocSinhDAO();
}
