package com.example.quanlythuvien.view.help;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.example.quanlythuvien.domain.ui.IHuyObseverOnDestroy;
import com.example.quanlythuvien.domain.ui.IKiemTraGiaTriNhap;
import com.example.quanlythuvien.domain.ui.IXuLyRunnable;


public class LangNgheSuKien {
    public static void theoDoiGiaTriNhap(LifecycleOwner vongDoi, CharSequence giaTri, Consumer<IKiemTraGiaTriNhap> xuLy) {
        if ((!(giaTri instanceof IXuLyRunnable) || !(giaTri instanceof IKiemTraGiaTriNhap))) return;
        IXuLyRunnable iXuLy = (IXuLyRunnable) giaTri;
        IKiemTraGiaTriNhap iKiemTra = (IKiemTraGiaTriNhap) giaTri;
        AutoCloseable closeable = iXuLy.dangKy(() -> xuLy.accept(iKiemTra));
        LifecycleOwner context = vongDoi instanceof Fragment ? ((Fragment) vongDoi).getViewLifecycleOwner() : vongDoi;
        context.getLifecycle().addObserver((IHuyObseverOnDestroy) closeable::close);
    }
}
