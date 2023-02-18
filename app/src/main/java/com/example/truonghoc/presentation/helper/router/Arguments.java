package com.example.truonghoc.presentation.helper.router;

import android.content.Intent;

import java.io.Serializable;

public class Arguments {
    private static final String KEY_ARGS = "android:support:key:args";

    public static <T extends Serializable> T of(Intent intent) {
        return (T) intent.getSerializableExtra(KEY_ARGS);
    }

    public static void put(Intent intent, Serializable routing) {
        intent.putExtra(KEY_ARGS, routing);
    }
}
