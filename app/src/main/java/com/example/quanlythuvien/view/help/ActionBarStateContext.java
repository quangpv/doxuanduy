package com.example.quanlythuvien.view.help;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;

public class ActionBarStateContext {
    private final FrameLayout frameLayout;
    private TrangThai trangThai;

    public ActionBarStateContext(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    public void setTrangThai(TrangThai trangThai) {
        if (trangThai instanceof AutoCloseable) {
            try {
                ((AutoCloseable) trangThai).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        frameLayout.removeAllViews();
        ViewBinding binding = trangThai.onCreate(LayoutInflater.from(frameLayout.getContext()),frameLayout);
        trangThai.Apply();
        frameLayout.addView(binding.getRoot());
        this.trangThai = trangThai;
    }

    public interface TrangThai {
        ViewBinding onCreate(LayoutInflater inflater, ViewGroup parent);
        void Apply();
    }
}
