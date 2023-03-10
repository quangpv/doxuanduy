package com.example.quanlythuvien.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quanlythuvien.domain.Sach;
import com.example.quanlythuvien.domain.ThuThu;

@Database(entities = {ThuThu.class, Sach.class}, version = 1)
public abstract class ThuVienDataBase extends RoomDatabase {
    private static final String DATA_NAME = "thuvien.db";
    private static ThuVienDataBase thuVienData;

    public static void init(Context context) {
        thuVienData = Room.databaseBuilder(context, ThuVienDataBase.class, DATA_NAME).build();
    }

    public static ThuVienDataBase getInstance() {
        return thuVienData;
    }
    public abstract ThuVienDao ThuVienDao();
}
