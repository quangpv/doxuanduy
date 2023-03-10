package com.example.quanlythuvien.view.help;

import android.view.View;

public class HienThiView {
    public static void show(View view, boolean isShow) {
        int nextState = isShow ? View.VISIBLE : View.GONE;
        if (view.getVisibility() == nextState) return;
        view.setVisibility(nextState);
    }
}
