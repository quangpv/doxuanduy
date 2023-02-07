package com.example.truonghoc.data;

import android.net.Uri;

import androidx.room.TypeConverter;

public class DataConventer {
    @TypeConverter
    public static String uriToString(Uri uri) {
        return uri.toString();
    }
    @TypeConverter
    public static Uri stringToUri(String string) {
        if (string == null) {
            return null;
        } else {
            return Uri.parse(string);
        }
    }
}
