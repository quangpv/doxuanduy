package com.example.truonghoc.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.truonghoc.domain.HocSinhDangHoc;

@Database(entities = {HocSinhDangHoc.class},version = 1)
public abstract class HocSinhDangHocDataBase extends RoomDatabase {
    private static final String DATA_BASE_NAME = "hocSinh.db";
    private static HocSinhDangHocDataBase hocSinhData;
    public static synchronized HocSinhDangHocDataBase quanLyData(Context context){
        if (hocSinhData==null){
            hocSinhData = Room.databaseBuilder(context.getApplicationContext(),HocSinhDangHocDataBase.class,DATA_BASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return hocSinhData;
    }
    public abstract HocSinhDangHocDAO hocSinhDAO();
}
