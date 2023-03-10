package com.example.quanlythuvien.view.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

public class OpenActivity {
    public static <T> void open(LifecycleOwner owner, Class<T> classz, String id) {
        Context context;
        if (owner instanceof Fragment) {
            context = ((Fragment) owner).requireContext();
        } else if (owner instanceof Activity) {
            context = (Activity) owner;
        } else return;
        Intent intent = new Intent(context, classz);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
}
