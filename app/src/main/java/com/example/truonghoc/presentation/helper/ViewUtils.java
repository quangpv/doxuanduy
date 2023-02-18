package com.example.truonghoc.presentation.helper;

import android.view.View;

public class ViewUtils {
    public static void show(View view, boolean isShow) {
        int nextState = isShow ? View.VISIBLE : View.GONE;
        if (view.getVisibility() == nextState) return;
        view.setVisibility(nextState);
    }
}
