package com.example.quanlythuvien.view.help;

import android.view.View;

import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;
import com.google.android.material.textfield.TextInputLayout;

public class HienThiView {
    public static void show(View view, boolean isShow) {
        int nextState = isShow ? View.VISIBLE : View.GONE;
        if (view.getVisibility() == nextState) return;
        view.setVisibility(nextState);
    }

    public static <T extends View> void hienThiTextLoi(T view, IKiemTraGiaTriNhap loiMess) {
        boolean value = !loiMess.kiemTraDieuKienNhap();
        String value2 = loiMess.layThongBaoLoi();
        if (!(view instanceof TextInputLayout)) return;
        if (value) {
            ((TextInputLayout) view).setError(value2);
            ((TextInputLayout) view).setErrorEnabled(true);
        } else {
            ((TextInputLayout) view).setErrorEnabled(false);
        }

    }
}
